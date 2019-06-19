package com.yunmu.back.service.sys.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunmu.back.service.sys.SysUserService;
import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.dao.sys.SysUserMapperExt;
import com.yunmu.core.model.sys.SysUser;
import com.yunmu.core.model.sys.SysUserExt;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapperExt sysUserMapperExt;
    @Override
    public GenericPage<SysUserExt> getPageByCondition(Map<String, Object> params) {
        int pageIndex = 1, pageSize = 10;
        if(params.containsKey("pageIndex")) {
            if(params.get("pageIndex") != null &&
                    StringUtils.isNotBlank(params.get("pageIndex").toString())) {
                pageIndex = Integer.valueOf(params.get("pageIndex").toString());
                if(pageIndex < 1) {
                    pageIndex = 1;
                }
            }
        }
        if(params.containsKey("pageSize")) {
            if(params.get("pageIndex") != null &&
                    StringUtils.isNotBlank(params.get("pageSize").toString())) {
                pageSize = Integer.valueOf(params.get("pageSize").toString());
                if(pageSize < 1) {
                    pageSize = 10;
                }
            }
        }
        Page<SysUserExt> pageInfo = PageHelper.startPage(pageIndex, pageSize, true);
        List<SysUserExt> sysUserExts=sysUserMapperExt.getSysUserPage(params);

        for(SysUserExt sysUserExt:sysUserExts){

        }
        return new GenericPage<>(pageIndex, pageSize, sysUserExts, pageInfo.getTotal());
    }

    @Override
    public Boolean insert(SysUser sysUser) {
        return null;
    }
}
