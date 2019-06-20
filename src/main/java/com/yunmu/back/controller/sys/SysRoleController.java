package com.yunmu.back.controller.sys;

import com.yunmu.back.service.sys.SysRoleService;
import com.yunmu.core.base.BaseController;
import com.yunmu.core.constant.PageResult;
import com.yunmu.core.model.sys.SysRoleExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/sysrole")
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("/getpage")
    @ResponseBody
    public PageResult<SysRoleExt> getShopPageByCondition(HttpServletRequest request,
                                                         Integer pageIndex,
                                                         Integer pageSize,
                                                         String roleName,
                                                         String companyCode) {
        Map<String, Object> params = new HashMap<>();
        params.put("roleName", roleName);
        params.put("companyCode", companyCode);
        params.put("pageIndex", pageIndex + 1);
        params.put("pageSize", pageSize);
        return createSuccessPageResult(sysRoleService.getRolePageByCondition(params));
    }

}
