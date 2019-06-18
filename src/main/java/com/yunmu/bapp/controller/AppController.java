package com.yunmu.bapp.controller;

import com.yunmu.bapp.service.AppService;
import com.yunmu.core.base.BaseController;
import com.yunmu.core.base.Result;
import com.yunmu.core.model.owner.Owner;
import com.yunmu.core.util.AppRequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
