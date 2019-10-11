package com.yunmu.bapp.service.impl;

import com.yunmu.bapp.service.AppService;
import com.yunmu.core.base.Result;
import com.yunmu.core.dao.hourse.HourseMapper;
import com.yunmu.core.dao.order.OrderDetailMapper;
import com.yunmu.core.dao.order.OrderMapper;
import com.yunmu.core.dao.order.OrderMapperExt;
import com.yunmu.core.dao.owner.OwnerMapper;
import com.yunmu.core.dao.owner.OwnerMapperExt;
import com.yunmu.core.dao.pay.PayMapper;
import com.yunmu.core.dao.pay.PayWayMapper;
import com.yunmu.core.dao.source.OrderSourceMapper;
import com.yunmu.core.exception.DataException;
import com.yunmu.core.model.order.Order;
import com.yunmu.core.model.order.OrderDetail;
import com.yunmu.core.model.order.OrderDetailExample;
import com.yunmu.core.model.order.OrderExt;
import com.yunmu.core.model.owner.Owner;
import com.yunmu.core.model.owner.OwnerExt;
import com.yunmu.core.model.pay.Pay;
import com.yunmu.core.model.pay.PayExample;
import com.yunmu.core.model.pay.PayWay;
import com.yunmu.core.model.source.OrderSource;
import com.yunmu.core.util.AppRequestParam;
import com.yunmu.core.util.AppResponseObj;
import com.yunmu.core.util.OrderDetailUtil;
import com.yunmu.core.util.OrderItem;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by 13544 on 2019/6/18.
 */
@Service
public class AppServiceImpl implements AppService{

    @Autowired
    private OwnerMapperExt ownerMapperExt;
    @Autowired
    private OwnerMapper ownerMapper;
    @Autowired
    private OrderMapperExt orderMapperExt;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private PayMapper payMapper;
    @Autowired
    private HourseMapper hourseMapper;
    @Autowired
    private PayWayMapper payWayMapper;
    @Autowired
    private OrderSourceMapper orderSourceMapper;

    @Override
    public OwnerExt getOwnerByCondition(Owner owner) {
        OwnerExt owner1=ownerMapperExt.getOwnerByCondition(owner);
        if(owner1==null){
            throw new DataException("500","手机号或密码输入错误，请重新输入");
        }
        if(owner1.getStatus()==1){
            throw new DataException("500","该用户还未启用，请与管理原联系");
        }
        return owner1;
    }

    @Override
    public Boolean update(Owner owner) {
        owner.setCreateTime(new Date());
        try {
            ownerMapper.updateByPrimaryKeySelective(owner);
            return true;
        } catch (Exception e) {
            throw new DataException("用户信息修改失败");
        }
    }

    @Override
    public Map<String, Object> getHomeDataByCondition(Map<String, String> params) {

        //根据用户Id和时间获取收益
        double recAmountAll=orderMapperExt.getRecAmountByCondition(params);
        //获取房子出租的天数
        int count=orderMapperExt.getCountByCondition(params);
        //获取房子出租率
        Calendar now = Calendar.getInstance();
        int day=now.get(Calendar.DAY_OF_MONTH);
        String houseRate=Math.round((count*100)/day)+"";
        //获取应收
        double actAmountAll=orderMapperExt.getActAmountByCondition(params);
        //获取支出
        BigDecimal bigDecimal=new BigDecimal(recAmountAll);
        double extraCosts=bigDecimal.subtract(new BigDecimal(actAmountAll)).doubleValue();

        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("incomeAll",actAmountAll);
        resultMap.put("count",count);
        resultMap.put("houseRate",houseRate);
        resultMap.put("extraCosts",extraCosts);

        return resultMap;
    }

    @Override
    public double getIncomeByCondition(Map<String, String> params) {
        double incomeAll=orderMapperExt.getActAmountByCondition(params);
        return incomeAll;
    }

    @Override
    public List<AppResponseObj> getOrderPage(Map<String, String> params) {
        List<AppResponseObj> orderExtList=orderMapperExt.getOrderPageBycondition(params);
        return orderExtList;
    }

    @Override
    public OrderDetailUtil getOrderInfoById(String orderId) {
        //先根据订单号获取订单信息
        Order order=orderMapper.selectByPrimaryKey(orderId);
        OrderExt orderExt=new OrderExt();
        if(order!=null){
            //根据订单id获取支出信息
            OrderDetailExample orderDetailExample=new OrderDetailExample();
            OrderDetailExample.Criteria criteria=orderDetailExample.createCriteria();
            criteria.andOrderCodeEqualTo(orderId);
            criteria.andDelFlagEqualTo(0);
            List<OrderDetail> orderDetails=orderDetailMapper.selectByExample(orderDetailExample);
            if(orderDetails.size()>0){
                List<String> payIds= new ArrayList<>();
                for(OrderDetail orderDetail:orderDetails){
                    payIds.add(orderDetail.getPayCode());
                }
                PayExample payExample=new PayExample();
                PayExample.Criteria criteria1=payExample.createCriteria();
                criteria1.andDelFlagEqualTo(0);
                criteria1.andPayIdIn(payIds);
                List<Pay> payList=payMapper.selectByExample(payExample);
                orderExt.setPayExts(payList);
            }
            BeanUtils.copyProperties(order,orderExt);
            Map<Integer,String> payWays=getAllPayWayMap();
            Map<String,String> orderSources=getAllOrderSourceMap();
            if(payWays.containsKey(Integer.parseInt(orderExt.getOrderWay()))){
                orderExt.setPayWay(payWays.get(Integer.parseInt(orderExt.getOrderWay())));
            }
            if(orderSources.containsKey(orderExt.getOrderSource())){
                orderExt.setSourceWay(orderSources.get(orderExt.getOrderSource()));
            }
            //获取房间号
            if(orderExt.getHourseCode()!=null){
                orderExt.setHourseNumber( hourseMapper.selectByPrimaryKey(orderExt.getHourseCode()).getHourseNumber());
            }
        }
        OrderDetailUtil orderDetailUtil=new OrderDetailUtil();
        orderDetailUtil.setoDate(orderExt.getCreateTime());
        orderDetailUtil.setoId(orderExt.getId());
        orderDetailUtil.setoRecAmount(orderExt.getOrderRecAmount().doubleValue());
        orderDetailUtil.setPayWay(orderExt.getPayWay());
        orderDetailUtil.setSourceWay(orderExt.getSourceWay());
        orderDetailUtil.sethNumber(orderExt.getHourseNumber());
        //BigDecimal bigDecimal=new BigDecimal(orderExt.getOrderRecAmount());
        double payAmount=orderExt.getOrderRecAmount().subtract(orderExt.getOrderActAmount()).doubleValue();
        orderDetailUtil.setOrderActAmount(orderExt.getOrderActAmount().doubleValue());
        orderDetailUtil.setPayAmount(payAmount);
        List<OrderItem> orderItemList=new ArrayList<>();
        if(orderExt.getPayExts()!=null && orderExt.getPayExts().size()>0){
            for(Pay pay:orderExt.getPayExts()){
                OrderItem orderItem=new OrderItem();
                orderItem.setdId(pay.getPayId());
                orderItem.setdAmount(pay.getPayAmount().doubleValue());
                orderItem.setdDate(pay.getCreateTime());
                orderItem.setdDesc(pay.getPayDesc());
                orderItem.setdName(pay.getPayName());
                orderItem.setdCount(1);
                orderItemList.add(orderItem);
            }

        }
        orderDetailUtil.setOrderItems(orderItemList);
        return orderDetailUtil;
    }

    @Override
    public Owner getOwnerById(String ownerId) {
        return ownerMapper.selectByPrimaryKey(ownerId);
    }

    public Map<Integer,String> getAllPayWayMap(){
        List<PayWay> payWays=payWayMapper.getPayWayAll();
        Map<Integer,String> stringMap=new HashMap<>();
        for(PayWay payWay:payWays){
            stringMap.put(payWay.getId(),payWay.getpName());
        }
        return stringMap;
    }

    public Map<String,String> getAllOrderSourceMap(){
        List<OrderSource> orderSources=orderSourceMapper.getOrderSourceAll();
        Map<String,String> stringMap=new HashMap<>();
        for(OrderSource orderSource:orderSources){
            stringMap.put(orderSource.getId(),orderSource.getName());
        }
        return stringMap;
    }


}
