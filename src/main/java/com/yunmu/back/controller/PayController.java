package com.yunmu.back.controller;

import com.yunmu.bapp.pay.PayService;
import com.yunmu.core.base.BaseController;
import com.yunmu.core.base.Result;
import com.yunmu.core.constant.PageResult;
import com.yunmu.core.model.pay.Pay;
import com.yunmu.core.util.IdUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 13544 on 2019/6/18.
 */
@RequestMapping("pay")
@Controller
public class PayController extends BaseController{

    @Autowired
    private PayService payService;

    @RequestMapping("/toPaylist")
    public String toPaylist() {
        return "pay/toPaylist";
    }

    @RequestMapping("/getpage")
    @ResponseBody
    public PageResult<Pay> getShopPageByCondition(HttpServletRequest request,
                                                  Integer pageIndex,
                                                  Integer pageSize,
                                                  String payName) {
        Map<String, Object> params = new HashMap<>();
        params.put("payName", payName);
        params.put("pageIndex", pageIndex + 1);
        params.put("pageSize", pageSize);
        return createSuccessPageResult(payService.getPageByCondition(params));
    }

    @RequestMapping("/saveHourse")
    @ResponseBody
    public Result<Boolean> save(Pay pay) {
        if(StringUtils.isBlank(pay.getPayId())) {
            pay.setPayId(IdUtils.getId(11));
            try {
                payService.insert(pay);
            } catch (Exception e1) {
                return createFailedResult(e1.getMessage(), false);
            }
        } else {
            return  createSuccessResult(payService.update(pay));
        }
        return createSuccessResult(true);
    }

    @RequestMapping("/tolook")
    public String toLook(String id, Model model) {
        if(StringUtils.isBlank(id)) {
            return "pay/paylist";
        }
        model.addAttribute("payId", id);
        return "pay/lookpay";
    }

    @RequestMapping("/toedit")
    public String toEdit(String id, Model model) {
        if(StringUtils.isBlank(id)) {
            return "pay/paylist";
        }
        model.addAttribute("payId", id);
        return "pay/newpay";
    }

    @RequestMapping("/get")
    @ResponseBody
    public Result<Pay> update(String id) {
        if(StringUtils.isBlank(id)) {
            return createFailedResult("查询失败");
        }
        return createSuccessResult(payService.getPayByIdById(id));
    }

    @RequestMapping("/toaddHourse")
    public String toaddHourse() {
        return "hourse/newhourse";
    }

    @RequestMapping("/deleteHourse")
    @ResponseBody
    public Result<Boolean> deleteHourse(String payId) {

        return createSuccessResult(payService.deleteByPrimaryKey(payId));
    }

}
