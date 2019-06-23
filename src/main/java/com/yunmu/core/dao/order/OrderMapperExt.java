package com.yunmu.core.dao.order;

import com.yunmu.core.model.order.OrderExt;

import java.util.List;
import java.util.Map;

public interface OrderMapperExt {

    List<OrderExt> getOrderPage(Map<String, Object> params);
}