package com.yunmu.back.service.order;

import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.model.order.OrderExt;

import java.util.Map;

public interface OrderService {

    GenericPage<OrderExt> getPageByCondition(Map<String, Object> params);
}
