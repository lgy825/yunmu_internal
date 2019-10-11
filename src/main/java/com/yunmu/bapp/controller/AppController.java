package com.yunmu.bapp.controller;

import com.yunmu.bapp.service.AppService;
import com.yunmu.core.base.BaseController;
import com.yunmu.core.base.Result;
import com.yunmu.core.model.order.OrderDetail;
import com.yunmu.core.model.owner.Owner;
import com.yunmu.core.util.AppRequestParam;
import com.yunmu.core.util.AppResponseObj;
import com.yunmu.core.util.IdUtils;
import com.yunmu.core.util.OrderDetailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 13544 on 2019/6/18.
 */
@Controller
@RequestMapping("app")
public class AppController extends BaseController{

    @Autowired
    private AppService appService;

    //用户登录接口
    @RequestMapping("/login")
    @ResponseBody
    public Result login(@RequestBody AppRequestParam appRequestParam) {
        if (appRequestParam.getOwnerPhone() == null && appRequestParam.getOwnerName() == null) {
            return createFailedResult("请输入手机号或用户名");
        }
        if (appRequestParam.getOwnerPwd() == null) {
            return createFailedResult("密码输入为空，请重新输入");
        }
        Owner owner = new Owner();
        owner.setOwnerPwd(appRequestParam.getOwnerPwd());
        owner.setOwnerName(appRequestParam.getOwnerName());
        owner.setOwnerTel(appRequestParam.getOwnerPhone());
        try {
            return createSuccessResult(appService.getOwnerByCondition(owner));
        } catch (Exception e) {
            return createFailedResult(e.getMessage());
        }
    }

    //用户修改密码接口
    @RequestMapping("/update")
    @ResponseBody
    public Result<Boolean> updatePwd(@RequestBody AppRequestParam appRequestParam) {
        if(appRequestParam.getToken()!=null && !"".equals(appRequestParam.getToken())){
            Owner owner=appService.getOwnerById(appRequestParam.getOwnerId());
            if(!appRequestParam.getToken().equals(owner.getToken())){
                return createFailedResult("登录过期");
            }
        }
        Owner owner = new Owner();
        if (appRequestParam.getOwnerId() == null) {
            return createFailedResult("用户id不能为空");
        }
        if (appRequestParam.getOwnerPwd() == null) {
            return createFailedResult("请输入您要修改的密码");
        }
        owner.setId(appRequestParam.getOwnerId());
        owner.setOwnerName(appRequestParam.getOwnerName());
        owner.setOwnerPwd(appRequestParam.getOwnerPwd());
        owner.setOwnerTel(appRequestParam.getOwnerPhone());
        owner.setToken(IdUtils.getId(11));
        return createSuccessResult(appService.update(owner));
    }

    //首页界面
    @RequestMapping("/getDateByCondition")
    @ResponseBody
    public Result<Map<String, Object>> getDateByCondition(@RequestBody AppRequestParam appRequestParam) {
        if(appRequestParam.getToken()!=null && !"".equals(appRequestParam.getToken())){
            Owner owner=appService.getOwnerById(appRequestParam.getOwnerId());
            if(!appRequestParam.getToken().equals(owner.getToken())){
                return createFailedResult("登录过期");
            }
        }

        Map<String, String> params = new HashMap<>();
        params.put("ownerId", appRequestParam.getOwnerId());
        params.put("beginTime", appRequestParam.getBeginTime());
        params.put("endTime", appRequestParam.getEndTime());

        return createSuccessResult(appService.getHomeDataByCondition(params));
    }

    //获取收益，根据时间区间
    @RequestMapping("/getIncomeByCondition")
    @ResponseBody
    public Result<Double> getIncomeByCondition(@RequestBody AppRequestParam appRequestParam) {
        if(appRequestParam.getToken()!=null && !"".equals(appRequestParam.getToken())){
            Owner owner=appService.getOwnerById(appRequestParam.getOwnerId());
            if(!appRequestParam.getToken().equals(owner.getToken())){
                return createFailedResult("登录过期");
            }
        }
        Map<String, String> params = new HashMap<>();
        params.put("ownerId", appRequestParam.getOwnerId());
        params.put("beginTime", appRequestParam.getBeginTime());
        params.put("endTime", appRequestParam.getEndTime());

        return createSuccessResult(appService.getIncomeByCondition(params));
    }

    //获取收益列表
    @RequestMapping("/getOrderPageByCondition")
    @ResponseBody
    public Result<List<AppResponseObj>> getOrderPageByCondition(@RequestBody AppRequestParam appRequestParam) {
        if(appRequestParam.getToken()!=null && !"".equals(appRequestParam.getToken())){
            Owner owner=appService.getOwnerById(appRequestParam.getOwnerId());
            if(!appRequestParam.getToken().equals(owner.getToken())){
                return createFailedResult("登录过期");
            }
        }

        Map<String, String> params = new HashMap<>();
        params.put("ownerId", appRequestParam.getOwnerId());
        params.put("beginTime", appRequestParam.getBeginTime());
        params.put("endTime", appRequestParam.getEndTime());

        return createSuccessResult(appService.getOrderPage(params));
    }

    //根据订单idcha'查看订单的详情
    @RequestMapping("/getOrderInfoById")
    @ResponseBody
    public Result<OrderDetailUtil> getOrderInfoById(@RequestBody AppRequestParam appRequestParam) {
        if (appRequestParam.getOrderId() == null) {
            return createFailedResult("500错误,订单id为空");
        }
        return createSuccessResult(appService.getOrderInfoById(appRequestParam.getOrderId()));

    }


}
