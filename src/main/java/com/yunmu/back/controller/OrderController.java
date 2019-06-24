package com.yunmu.back.controller;

import com.yunmu.back.service.order.OrderService;
import com.yunmu.core.base.BaseController;
import com.yunmu.core.base.Result;
import com.yunmu.core.constant.PageResult;
import com.yunmu.core.model.order.OrderExt;
import com.yunmu.core.model.pay.PayWay;
import com.yunmu.core.model.source.OrderSource;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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

    @RequestMapping("/toedit")
    public String toEdit(String id, Model model) {
        if(StringUtils.isBlank(id)) {
            return "order/orderlist";
        }
        model.addAttribute("orderId", id);
        return "order/addOrder";
    }

    @RequestMapping("/toaddOrder")
    public String toaddOrder() {
        return "order/addOrder";
    }

    @RequestMapping("/getPayWayAll")
    @ResponseBody
    public Result<List<PayWay>> getPayWayAll(){
        return createSuccessResult(orderSercvice.getPayWay());
    }

    @RequestMapping("/getOrdeSourceAll")
    @ResponseBody
    public Result<List<OrderSource>> getOrdeSourceAll(){
        return createSuccessResult(orderSercvice.getOrderSource());
    }
}
