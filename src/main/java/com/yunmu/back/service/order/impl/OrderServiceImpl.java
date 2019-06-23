package com.yunmu.back.service.order.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunmu.back.service.order.OrderService;
import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.dao.hourse.HourseMapper;
import com.yunmu.core.dao.order.OrderMapper;
import com.yunmu.core.dao.order.OrderMapperExt;
import com.yunmu.core.dao.pay.PayWayMapper;
import com.yunmu.core.dao.source.OrderSourceMapper;
import com.yunmu.core.model.order.OrderExt;
import com.yunmu.core.model.pay.PayWay;
import com.yunmu.core.model.source.OrderSource;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            if(payWays.containsKey(orderExt.getPayWay())){
                orderExt.setPayWay(payWays.get(orderExt.getPayWay()));
            }
            if(orderSources.containsKey(orderExt.getOrderSource())){
                orderExt.setSourceWay(orderSources.get(orderExt.getOrderSource()));
            }

        }
        return new GenericPage<>(pageIndex, pageSize, orderExts, pageInfo.getTotal());

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
