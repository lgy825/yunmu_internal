package com.yunmu.back.controller;

import com.yunmu.back.service.order.OrderService;
import com.yunmu.core.base.BaseController;
import com.yunmu.core.constant.PageResult;
import com.yunmu.core.model.order.OrderExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 13544 on 2019/6/18.
 */
@RequestMapping("order")
@Controller
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderSercvice;

    @RequestMapping("/toOrderlist")
    public String toOrderlist() {
        return "order/orderlist";
    }


    @RequestMapping("/getpage")
    @ResponseBody
    public PageResult<OrderExt> getShopPageByCondition(HttpServletRequest request,
                                                       Integer pageIndex,
                                                       Integer pageSize,
                                                       String beginTime,
                                                       String endTime,
                                                       String orderId) {
        Map<String, Object> params = new HashMap<>();
        params.put("beginTime", beginTime);
        params.put("endTime",endTime);
        params.put("orderId", orderId);
        params.put("pageIndex", pageIndex + 1);
        params.put("pageSize", pageSize);
        return createSuccessPageResult(orderSercvice.getPageByCondition(params));
    }



}
