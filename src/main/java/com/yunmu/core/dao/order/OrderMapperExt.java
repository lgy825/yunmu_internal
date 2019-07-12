package com.yunmu.core.dao.order;

import com.yunmu.core.model.order.Order;
import com.yunmu.core.model.order.OrderExt;
import com.yunmu.core.util.AppResponseObj;

import java.util.List;
import java.util.Map;

public interface OrderMapperExt {

    List<OrderExt> getOrderPage(Map<String, Object> params);

    double getRecAmountByCondition(Map<String,String> params);

    double getActAmountByCondition(Map<String,String> params);

    int getCountByCondition(Map<String,String> params);


    List<AppResponseObj> getOrderPageBycondition(Map<String,String> params);

    void deleteOrderDetail(String orderCode);

    List<OrderExt> getOrderExport(Map<String,String> params);

    double getAllRecByParam(Map<String,String> params);

    double getAllActByParam(Map<String,String> params);
}