package com.yunmu.back.service.sys.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunmu.back.service.sys.SysRoleService;
import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.dao.sys.SysRoleMapperExt;
import com.yunmu.core.model.sys.SysRoleExt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapperExt sysRoleMapperExt;

    @Override
    public GenericPage<SysRoleExt> getRolePageByCondition(Map<String, Object> param) {
        int pageIndex = 1, pageSize = 10;
        if(param.containsKey("pageIndex")) {
            if(param.get("pageIndex") != null &&
                    StringUtils.isNotBlank(param.get("pageIndex").toString())) {
                pageIndex = Integer.valueOf(param.get("pageIndex").toString());
                if(pageIndex < 1) {
                    pageIndex = 1;
                }
            }
        }
        if(param.containsKey("pageSize")) {
            if(param.get("pageIndex") != null &&
                    StringUtils.isNotBlank(param.get("pageSize").toString())) {
                pageSize = Integer.valueOf(param.get("pageSize").toString());
                if(pageSize < 1) {
                    pageSize = 10;
                }
            }
        }
        Page<SysRoleExt> pageInfo = PageHelper.startPage(pageIndex, pageSize, true);
        List<SysRoleExt> sysUSerList = sysRoleMapperExt.getRolePage(param);
        return new GenericPage<>(pageIndex, pageSize, sysUSerList, pageInfo.getTotal());
    }
}
