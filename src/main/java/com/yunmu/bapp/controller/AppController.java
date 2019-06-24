package com.yunmu.bapp.controller;

import com.yunmu.bapp.service.AppService;
import com.yunmu.core.base.BaseController;
import com.yunmu.core.base.Result;
import com.yunmu.core.model.owner.Owner;
import com.yunmu.core.util.AppRequestParam;
import com.yunmu.core.util.AppResponseObj;
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
        Owner owner = new Owner();
        if (appRequestParam.getOwnerId() == null) {
            return createFailedResult("用户id不能为空");
        }
        if (appRequestParam.getOwnerPwd() == null) {
            return createFailedResult("请输入您要修改的密码");
        }
        owner.setOwnerName(appRequestParam.getOwnerName());
        owner.setOwnerPwd(appRequestParam.getOwnerPwd());
        owner.setOwnerTel(appRequestParam.getOwnerPhone());
        return createSuccessResult(appService.update(owner));
    }

    //首页界面
    @RequestMapping("/getDateByCondition")
    @ResponseBody
    public Result<Map<String, Object>> getDateByCondition(@RequestBody AppRequestParam appRequestParam) {
        if (appRequestParam.getOwnerId() == null) {
            return createFailedResult("500错误，用户id为空");
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
        if (appRequestParam.getOwnerId() == null) {
            return createFailedResult("500错误，用户id为空");
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
        if (appRequestParam.getOwnerId() == null) {
            return createFailedResult("500错误，用户id为空");
        }

        Map<String, String> params = new HashMap<>();
        params.put("ownerId", appRequestParam.getOwnerId());
        params.put("beginTime", appRequestParam.getBeginTime());
        params.put("endTime", appRequestParam.getEndTime());

        return createSuccessResult(appService.getOrderPage(params));
    }
}
