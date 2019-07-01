package com.yunmu.back.service.order;

import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.model.order.OrderExt;
import com.yunmu.core.model.pay.PayWay;
import com.yunmu.core.model.source.OrderSource;

import java.util.List;
import java.util.Map;

public interface OrderService {

    GenericPage<OrderExt> getPageByCondition(Map<String, Object> params);

    List<PayWay> getPayWay();

    List<OrderSource> getOrderSource();

    boolean saveOrder(OrderExt orderExt);

    boolean updateOrder(OrderExt orderExt);

    OrderExt getOrderDetail(String id);

    boolean delete(String orderId);

    OrderExt get(String id);
}
