package com.yunmu.back.service.order.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunmu.back.service.order.OrderService;
import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.dao.hourse.HourseMapper;
import com.yunmu.core.dao.order.OrderDetailMapper;
import com.yunmu.core.dao.order.OrderMapper;
import com.yunmu.core.dao.order.OrderMapperExt;
import com.yunmu.core.dao.order.OrderProductMapper;
import com.yunmu.core.dao.owner.OwnerMapper;
import com.yunmu.core.dao.pay.PayMapper;
import com.yunmu.core.dao.pay.PayWayMapper;
import com.yunmu.core.dao.product.ProductMapper;
import com.yunmu.core.dao.source.OrderSourceMapper;
import com.yunmu.core.dao.sys.SysUserMapper;
import com.yunmu.core.model.hourse.Hourse;
import com.yunmu.core.model.hourse.HourseExample;
import com.yunmu.core.model.order.*;
import com.yunmu.core.model.pay.Pay;
import com.yunmu.core.model.pay.PayExample;
import com.yunmu.core.model.pay.PayWay;
import com.yunmu.core.model.product.Product;
import com.yunmu.core.model.product.ProductExample;
import com.yunmu.core.model.source.OrderSource;
import com.yunmu.core.util.IdUtils;
import com.yunmu.core.util.ParamVo;
import com.yunmu.core.util.ProductObj;
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
    @Autowired
    private OrderProductMapper orderProductMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private SysUserMapper  sysUserMapper;

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
            orderExt.setOperaterBy(sysUserMapper.selectByPrimaryKey(orderExt.getCreateBy()).getUserName());

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
            Date date=new Date();
            //List<String> payIds= new ArrayList<>();
            //如果选择的是暂不选择支出
            if(orderExt.getIsChoose()==2){
                //获取支出
                List<ParamVo> paramVos=orderExt.getParamVos();
                for(ParamVo paramVo:paramVos){
                    //payIds.add(paramVo.getPayId());
                    pauSum+=paramVo.getAmount();

                }
            }
            //判断订单是否选中了商品
            List<String> productIds= new ArrayList<>();
            if(orderExt.getIsChooseProduct()==2){
                List<ProductObj> productObjs=orderExt.getProductObjs();
                double proAmount=0.0;
                for(ProductObj productObj:productObjs){
                    proAmount+=productObj.getAmount();
                    productIds.add(productObj.getProductId());
                }
                orderExt.setOrderProAmount(new BigDecimal(proAmount).divide(new BigDecimal(100)));
            }
            //BigDecimal bigDecimal=new BigDecimal(orderExt.getOrderRecAmount());
            BigDecimal actAmount=orderExt.getOrderRecAmount().subtract(new BigDecimal(pauSum).divide(new BigDecimal(100)));

            //保存订单
            for(int i=0;i<hourseCodes.length;i++){
                Order order=new Order();
                BeanUtils.copyProperties(orderExt, order);
                order.setCreateBy(ShiroUtils.getUser().getId());
                order.setCreateTime(date);
                order.setDelFlag(0);
                order.setHourseCode(hourseCodes[i]);
                order.setId(IdUtils.orderCodeGeneration());
                //order.setOrderStatus(0);
                order.setOrderActAmount(actAmount);
                orderMapper.insertSelective(order);
                if(orderExt.getIsChoose()==2){
                    List<ParamVo> paramVos=orderExt.getParamVos();
                    for(ParamVo paramVo:paramVos) {
                        OrderDetail orderDetail = new OrderDetail();
                        orderDetail.setId(IdUtils.getId(11));
                        orderDetail.setDelFlag(0);
                        orderDetail.setCreateBy(ShiroUtils.getUserId());
                        orderDetail.setCreateTime(date);
                        orderDetail.setOrderCode(order.getId());
                        orderDetail.setAmount(new BigDecimal(paramVo.getAmount()).divide(new BigDecimal(100)));
                        orderDetail.setPayCode(paramVo.getPayId());
                        orderDetailMapper.insertSelective(orderDetail);
                    }
                }
                if(orderExt.getIsChooseProduct()==2){
                    List<ProductObj> productObjs=orderExt.getProductObjs();
                    for(ProductObj productObj:productObjs){
                        OrderProduct orderProduct=new OrderProduct();
                        orderProduct.setId(IdUtils.getId(11));
                        orderProduct.setCreateTime(date);
                        orderProduct.setOrderCode(order.getId());
                        orderProduct.setAmount(new BigDecimal(productObj.getAmount()).divide(new BigDecimal(100)));
                        orderProduct.setProductCode(productObj.getProductId());
                        orderProduct.setCreateBy(ShiroUtils.getUserId());
                        orderProductMapper.insertSelective(orderProduct);
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
            //计算支持总金额
            Double pauSum=0.0;
            //判断之前支出详情有没存储该订单的数据，有则删除
            OrderDetailExample orderDetailExample=new OrderDetailExample();
            OrderDetailExample.Criteria criteria2=orderDetailExample.createCriteria();
            criteria2.andOrderCodeEqualTo(orderExt.getId());
            List<OrderDetail> orderDetails=orderDetailMapper.selectByExample(orderDetailExample);
            if(orderDetails.size()>0){
                orderDetailMapper.deleteByExample(orderDetailExample);
            }
            //判断之前有没商品选项的存储的数据，有则删除
            OrderProductExample orderProductExample=new OrderProductExample();
            OrderProductExample.Criteria criteria1=orderProductExample.createCriteria();
            criteria1.andOrderCodeEqualTo(orderExt.getId());
            List<OrderProduct> orderProducts=orderProductMapper.selectByExample(orderProductExample);
            if(orderProducts.size()>0){
                orderProductMapper.deleteByExample(orderProductExample);
            }

            //获取支出
            //判断是否选择支出
            if(orderExt.getIsChoose()!=null && orderExt.getIsChoose()==2){
                //获取支出
                List<ParamVo> paramVos=orderExt.getParamVos();
                for(ParamVo paramVo:paramVos){
                    //payIds.add(paramVo.getPayId());
                    pauSum+=paramVo.getAmount();
                }
            }
            //判断订单是否选中了商品
            List<String> productIds= new ArrayList<>();
            if(orderExt.getIsChooseProduct()==2){
                List<ProductObj> productObjs=orderExt.getProductObjs();
                double proAmount=0.0;
                for(ProductObj productObj:productObjs){
                    proAmount+=productObj.getAmount();
                    productIds.add(productObj.getProductId());
                }
                orderExt.setOrderProAmount(new BigDecimal(proAmount).divide(new BigDecimal(100)));
            }
            //BigDecimal bigDecimal=new BigDecimal(orderExt.getOrderRecAmount());
            BigDecimal actAmount=orderExt.getOrderRecAmount().subtract(new BigDecimal(pauSum).divide(new BigDecimal(100)));
            //保存订单
            for(int i=0;i<hourseCodes.length;i++) {
                Order order = new Order();
                BeanUtils.copyProperties(orderExt, order);
                order.setHourseCode(hourseCodes[i]);
                order.setOrderActAmount(actAmount);
                order.setCreateBy(ShiroUtils.getUser().getId());
                orderMapper.updateByPrimaryKeySelective(order);
                if (orderExt.getIsChoose() == 2) {
                    List<ParamVo> paramVos = orderExt.getParamVos();
                    for (ParamVo paramVo : paramVos) {
                        OrderDetail orderDetail = new OrderDetail();
                        orderDetail.setId(IdUtils.getId(11));
                        orderDetail.setDelFlag(0);
                        orderDetail.setAmount(new BigDecimal(paramVo.getAmount()).divide(new BigDecimal(100)));
                        orderDetail.setCreateBy(ShiroUtils.getUserId());
                        orderDetail.setCreateTime(date);
                        orderDetail.setOrderCode(order.getId());
                        orderDetail.setPayCode(paramVo.getPayId());
                        orderDetailMapper.insertSelective(orderDetail);
                    }
                }
                if (orderExt.getIsChooseProduct() == 2) {
                    List<ProductObj> productObjs = orderExt.getProductObjs();
                    for (ProductObj productObj : productObjs) {
                        OrderProduct orderProduct = new OrderProduct();
                        orderProduct.setId(IdUtils.getId(11));
                        orderProduct.setCreateTime(date);
                        orderProduct.setOrderCode(order.getId());
                        orderProduct.setAmount(new BigDecimal(productObj.getAmount()).divide(new BigDecimal(100)));
                        orderProduct.setProductCode(productObj.getProductId());
                        orderProduct.setCreateBy(ShiroUtils.getUserId());
                        orderProductMapper.insertSelective(orderProduct);
                    }
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
            if(order.getIsChoose()!=null && order.getIsChoose()==2){
                OrderDetailExample orderDetailExample=new OrderDetailExample();
                OrderDetailExample.Criteria criteria=orderDetailExample.createCriteria();
                criteria.andOrderCodeEqualTo(id);
                criteria.andDelFlagEqualTo(0);
                List<OrderDetail> orderDetails=orderDetailMapper.selectByExample(orderDetailExample);
                for(OrderDetail orderDetail:orderDetails){
                    Pay pay=payMapper.selectByPrimaryKey(orderDetail.getPayCode());
                    orderDetail.setPayName(pay.getPayName());
                    orderDetail.setPayDesc(pay.getPayDesc());
                }
                orderExt.setOrderDetails(orderDetails);
            }
            //根据订单id获取商品信息
            if(order.getIsChooseProduct()!=null && order.getIsChooseProduct()==2){
                OrderProductExample orderProductExample=new OrderProductExample();
                OrderProductExample.Criteria criteria=orderProductExample.createCriteria();
                criteria.andOrderCodeEqualTo(order.getId());
                List<OrderProduct> orderProducts=orderProductMapper.selectByExample(orderProductExample);
                for(OrderProduct orderProduct:orderProducts){
                    Product product=productMapper.selectByPrimaryKey(orderProduct.getProductCode());
                    orderProduct.setProductName(product.getProductName());
                    orderProduct.setProductDesc(product.getProductDesc());
                }
                orderExt.setOrderProducts(orderProducts);
            }
            BeanUtils.copyProperties(order,orderExt);
            //BigDecimal bigDecimal=new BigDecimal();
            BigDecimal payAmount=order.getOrderRecAmount().subtract(orderExt.getOrderRecAmount());
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
        order.setCreateBy(ShiroUtils.getUserId());
        order.setCreateTime(new Date());
        orderMapper.updateByPrimaryKeySelective(order);
        orderMapperExt.deleteOrderDetail(orderId);
        return true;
    }

    @Override
    public boolean revoke(String orderId) {
        Order order=new Order();
        order.setDelFlag(0);
        order.setId(orderId);
        order.setCreateBy(ShiroUtils.getUserId());
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
            //获取房间类型
            Hourse hourse=hourseMapper.selectByPrimaryKey(orderExt.getHourseCode());
            orderExt.setTypeCode(hourse.getTypeCode());
            //根据订单id获取支出信息
            //查看订单是否选择了支出
            if(order.getIsChoose()!=null && order.getIsChoose()==2){
                OrderDetailExample orderDetailExample = new OrderDetailExample();
                OrderDetailExample.Criteria criteria = orderDetailExample.createCriteria();
                criteria.andOrderCodeEqualTo(id);
                criteria.andDelFlagEqualTo(0);
                List<OrderDetail> orderDetails = orderDetailMapper.selectByExample(orderDetailExample);
                orderExt.setOrderDetails(orderDetails);
            }
            //判断订单是否选择了商品
            if(order.getIsChooseProduct()!=null && order.getIsChooseProduct()==2){
                OrderProductExample orderProductExample=new OrderProductExample();
                OrderProductExample.Criteria criteria=orderProductExample.createCriteria();
                criteria.andOrderCodeEqualTo(order.getId());
                List<OrderProduct> orderProducts=orderProductMapper.selectByExample(orderProductExample);
                orderExt.setOrderProducts(orderProducts);

            }
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
    public double getAllRecByParam(Map<String, String> params) {

        return orderMapperExt.getAllRecByParam(params);
    }

    @Override
    public double getAllActByParam(Map<String, String> params) {
        return orderMapperExt.getAllActByParam(params);
    }

    @Override
    public boolean updateOrderStatus(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
        return true;
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
