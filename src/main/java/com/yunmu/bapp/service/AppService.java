package com.yunmu.bapp.service;

import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.model.order.Order;
import com.yunmu.core.model.order.OrderExt;
import com.yunmu.core.model.owner.Owner;
import com.yunmu.core.model.owner.OwnerExt;
import com.yunmu.core.util.AppResponseObj;
import com.yunmu.core.util.OrderDetailUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by 13544 on 2019/6/18.
 */
public interface AppService {

    OwnerExt getOwnerByCondition(Owner owner);

    Boolean update(Owner owner);

    Map<String,Object> getHomeDataByCondition(Map<String,String> params);

    double getIncomeByCondition(Map<String,String> params);

    List<AppResponseObj> getOrderPage(Map<String,String> params);

    OrderDetailUtil getOrderInfoById(String orderId);

    Owner getOwnerById(String ownerId);

    GenericPage<OrderExt> getOrderListByCondition(Map<String, Object> params);
}
