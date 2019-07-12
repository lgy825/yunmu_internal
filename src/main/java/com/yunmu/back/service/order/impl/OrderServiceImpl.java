package com.yunmu.back.service.order.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunmu.back.service.order.OrderService;
import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.dao.hourse.HourseMapper;
import com.yunmu.core.dao.order.OrderDetailMapper;
import com.yunmu.core.dao.order.OrderMapper;
import com.yunmu.core.dao.order.OrderMapperExt;
import com.yunmu.core.dao.pay.PayMapper;
import com.yunmu.core.dao.pay.PayWayMapper;
import com.yunmu.core.dao.source.OrderSourceMapper;
import com.yunmu.core.model.hourse.Hourse;
import com.yunmu.core.model.hourse.HourseExample;
import com.yunmu.core.model.order.*;
import com.yunmu.core.model.pay.Pay;
import com.yunmu.core.model.pay.PayExample;
import com.yunmu.core.model.pay.PayWay;
import com.yunmu.core.model.source.OrderSource;
import com.yunmu.core.util.IdUtils;
import com.yunmu.core.util.ParamVo;
import com.yunmu.core.util.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private PayWayMapper payWayMapper;
    @Autowired
    private OrderSourceMapper orderSourceMapper;
    @Autowired
    private OrderMapperExt orderMapperExt;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private HourseMapper hourseMapper;
    @Autowired
    private PayMapper payMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public GenericPage<OrderExt> getPageByCondition(Map<String, Object> params) {
        int pageIndex = 1, pageSize = 10;
        if(params.containsKey("pageIndex")) {
            if(params.get("pageIndex") != null &&
                    StringUtils.isNotBlank(params.get("pageIndex").toString())) {
                pageIndex = Integer.valueOf(params.get("pageIndex").toString());
                if(pageIndex < 1) {
                    pageIndex = 1;
                }
            }
        }
        if(params.containsKey("pageSize")) {
            if(params.get("pageIndex") != null &&
                    StringUtils.isNotBlank(params.get("pageSize").toString())) {
                pageSize = Integer.valueOf(params.get("pageSize").toString());
                if(pageSize < 1) {
                    pageSize = 10;
                }
            }
        }
        Map<Integer,String> payWays=getAllPayWayMap();
        Map<String,String> orderSources=getAllOrderSourceMap();
        Page<OrderExt> pageInfo = PageHelper.startPage(pageIndex, pageSize, true);
        List<OrderExt> orderExts=orderMapperExt.getOrderPage(params);
        for(OrderExt orderExt:orderExts){
            orderExt.setHourseNumber(hourseMapper.selectByPrimaryKey(orderExt.getHourseCode()).getHourseNumber());
            if(payWays.containsKey(Integer.parseInt(orderExt.getOrderWay()))){
                orderExt.setPayWay(payWays.get(Integer.parseInt(orderExt.getOrderWay())));
            }
            if(orderSources.containsKey(orderExt.getOrderSource())){
                orderExt.setSourceWay(orderSources.get(orderExt.getOrderSource()));
            }

        }
        return new GenericPage<>(pageIndex, pageSize, orderExts, pageInfo.getTotal());

    }

    @Override
    public List<OrderSource> getOrderSource() {
        List<OrderSource> orderSources=orderSourceMapper.getOrderSourceAll();
        return orderSources;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrder(OrderExt orderExt) {
        if(orderExt!=null){
            //获取房间号
            String codes=orderExt.getHourseCodes();
            String[] hourseCodes=codes.split(",");
            Double pauSum=0.0;
            List<String> payIds= new ArrayList<>();
            //如果选择的是暂不选择支出
            if(orderExt.getIsChoose()==2){
                //获取支出
                List<ParamVo> paramVos=orderExt.getParamVos();
                for(ParamVo paramVo:paramVos){
                    payIds.add(paramVo.getPayId());
                }
                //根据支出ids获取所有的支出对象
                PayExample payExample=new PayExample();
                PayExample.Criteria criteria=payExample.createCriteria();
                criteria.andPayIdIn(payIds);
                List<Pay> pays=payMapper.selectByExample(payExample);
                //计算支持总金额
                for(Pay pay:pays){
                    pauSum+=pay.getPayAmount();
                }
            }
            BigDecimal bigDecimal=new BigDecimal(orderExt.getOrderRecAmount());
            Long actAmount=bigDecimal.subtract(new BigDecimal(pauSum)).longValue();
            Date date=new Date();
            //保存订单
            for(int i=0;i<hourseCodes.length;i++){
                Order order=new Order();
                BeanUtils.copyProperties(orderExt, order);
                order.setCreateBy(ShiroUtils.getUser().getUserName());
                order.setCreateTime(date);
                order.setDelFlag(0);
                order.setHourseCode(hourseCodes[i]);
                order.setId(IdUtils.orderCodeGeneration());
                order.setOrderStatus(0);
                order.setOrderActAmount(actAmount);
                orderMapper.insertSelective(order);
                if(payIds.size()>0){
                    //缓存到订单详情表
                    for(String payId:payIds){
                        OrderDetail orderDetail=new OrderDetail();
                        orderDetail.setId(IdUtils.getId(11));
                        orderDetail.setDelFlag(0);
                        orderDetail.setCreateBy(ShiroUtils.getUserId());
                        orderDetail.setCreateTime(date);
                        orderDetail.setOrderCode(order.getId());
                        orderDetail.setPayCode(payId);
                        orderDetailMapper.insertSelective(orderDetail);
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateOrder(OrderExt orderExt) {
        if(orderExt!=null){
            //获取房间号
            Date date=new Date();
            String codes=orderExt.getHourseCodes();
            String[] hourseCodes=codes.split(",");
            //获取支出
            List<ParamVo> paramVos=orderExt.getParamVos();
            List<String> payIds= new ArrayList<>();
            for(ParamVo paramVo:paramVos){
                payIds.add(paramVo.getPayId());
            }
            //根据支出ids获取所有的支出对象
            PayExample payExample=new PayExample();
            PayExample.Criteria criteria=payExample.createCriteria();
            criteria.andPayIdIn(payIds);
            List<Pay> pays=payMapper.selectByExample(payExample);
            //计算支持总金额
            Double pauSum=0.0;
            for(Pay pay:pays){
                pauSum+=pay.getPayAmount();
            }
            BigDecimal bigDecimal=new BigDecimal(orderExt.getOrderRecAmount());
            Long actAmount=bigDecimal.subtract(new BigDecimal(pauSum)).longValue();
            //保存订单
            for(int i=0;i<hourseCodes.length;i++){
                String orderId=orderExt.getId();
                Order order=new Order();
                BeanUtils.copyProperties(orderExt,order);
                order.setOrderActAmount(actAmount);
                orderMapper.updateByPrimaryKeySelective(order);

                //删除订单关联的支出
                orderMapperExt.deleteOrderDetail(orderId);
                //缓存到订单详情表
                for(String payId:payIds){
                    OrderDetail orderDetail=new OrderDetail();
                    orderDetail.setId(IdUtils.getId(11));
                    orderDetail.setDelFlag(0);
                    orderDetail.setCreateBy("lgy");
                    orderDetail.setCreateTime(date);
                    orderDetail.setOrderCode(order.getId());
                    orderDetail.setPayCode(payId);
                    orderDetailMapper.insertSelective(orderDetail);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public OrderExt getOrderDetail(String id) {
        //先根据订单号获取订单信息
        Order order=orderMapper.selectByPrimaryKey(id);
        OrderExt orderExt=new OrderExt();
        if(order!=null){
            //根据订单id获取支出信息
            if(order.getIsChoose()==2){
                OrderDetailExample orderDetailExample=new OrderDetailExample();
                OrderDetailExample.Criteria criteria=orderDetailExample.createCriteria();
                criteria.andOrderCodeEqualTo(id);
                criteria.andDelFlagEqualTo(0);
                List<OrderDetail> orderDetails=orderDetailMapper.selectByExample(orderDetailExample);
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
            BigDecimal bigDecimal=new BigDecimal(order.getOrderRecAmount());
            Long payAmount=bigDecimal.subtract(new BigDecimal(orderExt.getOrderRecAmount())).longValue();
            orderExt.setPayAmount(payAmount);
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
        return orderExt;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(String orderId) {
        Order order=new Order();
        order.setDelFlag(1);
        order.setId(orderId);
        order.setCreateBy("lgy");
        order.setCreateTime(new Date());
        orderMapper.updateByPrimaryKeySelective(order);
        orderMapperExt.deleteOrderDetail(orderId);
        return true;
    }

    @Override
    public OrderExt get(String id) {
        //根据id获取订单信息
        Order order=orderMapper.selectByPrimaryKey(id);
        OrderExt orderExt=new OrderExt();
        BeanUtils.copyProperties(order,orderExt);
        //根据订单获取支出列表
        if(order!=null) {
            //根据订单id获取支出信息
            OrderDetailExample orderDetailExample = new OrderDetailExample();
            OrderDetailExample.Criteria criteria = orderDetailExample.createCriteria();
            criteria.andOrderCodeEqualTo(id);
            criteria.andDelFlagEqualTo(0);
            List<OrderDetail> orderDetails = orderDetailMapper.selectByExample(orderDetailExample);
            List<String> payIds = new ArrayList<>();
            for (OrderDetail orderDetail : orderDetails) {
                payIds.add(orderDetail.getPayCode());
            }
            //获取房间类型
            Hourse hourse=hourseMapper.selectByPrimaryKey(orderExt.getHourseCode());
            orderExt.setTypeCode(hourse.getTypeCode());
            PayExample payExample = new PayExample();
            PayExample.Criteria criteria1 = payExample.createCriteria();
            criteria1.andDelFlagEqualTo(0);
            criteria1.andPayIdIn(payIds);
            List<Pay> payList = payMapper.selectByExample(payExample);
            orderExt.setPayExts(payList);
            BeanUtils.copyProperties(order, orderExt);


        }
        return orderExt;
    }

    @Override
    public List<OrderExt> getOrdersByDate(Map<String, String> params) {
        List<OrderExt> orderList=orderMapperExt.getOrderExport(params);
        if(orderList!=null){
            for(OrderExt orderExt:orderList){
                if(orderExt.getHourseCode()!=null){
                    orderExt.setHourseNumber( hourseMapper.selectByPrimaryKey(orderExt.getHourseCode()).getHourseNumber());
                }
                Map<Integer,String> payWays=getAllPayWayMap();
                Map<String,String> orderSources=getAllOrderSourceMap();
                if(payWays.containsKey(Integer.parseInt(orderExt.getOrderWay()))){
                    orderExt.setPayWay(payWays.get(Integer.parseInt(orderExt.getOrderWay())));
                }
                if(orderSources.containsKey(orderExt.getOrderSource())){
                    orderExt.setSourceWay(orderSources.get(orderExt.getOrderSource()));
                }
            }
        }
        return orderList;
    }

    @Override
    public List<PayWay> getPayWay() {
        List<PayWay> payWays=payWayMapper.getPayWayAll();
        return payWays;
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
