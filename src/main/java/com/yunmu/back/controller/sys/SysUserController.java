package com.yunmu.back.controller.sys;

import com.yunmu.back.service.sys.SysUserService;
import com.yunmu.core.base.BaseController;
import com.yunmu.core.base.Result;
import com.yunmu.core.constant.PageResult;
import com.yunmu.core.model.sys.SysUser;
import com.yunmu.core.model.sys.SysUserExt;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("sysuser")
@Controller
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/toUserlist")
    public String toList() {
        return "sys/user/userlist";
    }

    @RequestMapping("/toaddUser")
    public String toaddUser() {
        return "ys/user/newuser";
    }

    @RequestMapping("/getpage")
    @ResponseBody
    public PageResult<SysUserExt> getShopPageByCondition(HttpServletRequest request,
                                                         Integer pageIndex,
                                                         Integer pageSize,
                                                         String loginName) {
        Map<String, Object> params = new HashMap<>();
        params.put("loginName", loginName);
        params.put("pageIndex", pageIndex + 1);
        params.put("pageSize", pageSize);
        return createSuccessPageResult(sysUserService.getPageByCondition(params));
    }


    @RequestMapping("/nameexist")
    @ResponseBody
    public Result<Boolean> nameExist(SysUserExt sysUserExt) {
        return createSuccessResult(sysUserService.nameExist(sysUserExt));
    }

    @RequestMapping("/get")
    @ResponseBody
    public Result<SysUserExt> update(String id) {
        if(StringUtils.isBlank(id)) {
            return createFailedResult("查询失败");
        }
        return createSuccessResult(sysUserService.getUserExt(id));
    }


    @RequestMapping("/toadd")
    public String toAdd() {
        return "sys/user/newuser";
    }
}
