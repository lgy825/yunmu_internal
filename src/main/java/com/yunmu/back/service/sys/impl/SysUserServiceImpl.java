package com.yunmu.back.service.sys.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Joiner;
import com.yunmu.back.service.sys.SysUserService;
import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.dao.sys.SysUserMapper;
import com.yunmu.core.dao.sys.SysUserMapperExt;
import com.yunmu.core.dao.sys.SysUserProjectMapper;
import com.yunmu.core.dao.sys.SysUserRoleMapper;
import com.yunmu.core.model.project.Project;
import com.yunmu.core.model.project.ProjectExample;
import com.yunmu.core.model.sys.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapperExt sysUserMapperExt;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserProjectMapper projectMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
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
        return new GenericPage<>(pageIndex, pageSize, sysUserExts, pageInfo.getTotal());
    }

    @Override
    public Boolean insert(SysUser sysUser) {
        return null;
    }

    @Override
    public Boolean nameExist(SysUserExt sysUserExt) {
        int compare = 0;
        if(org.apache.commons.lang3.StringUtils.isNotBlank(sysUserExt.getId())) {
            // 修改时候判断
            SysUser dbUser = sysUserMapper.selectByPrimaryKey(sysUserExt.getId());
            if(dbUser.getLoginName().equals(sysUserExt.getLoginName())) {
                compare = 1;
            }
        }

        SysUserExample sysUserExample = new SysUserExample();
        SysUserExample.Criteria sysUserCri = sysUserExample.createCriteria();
        sysUserCri.andDelFlagEqualTo(0);
        sysUserCri.andLoginNameEqualTo(sysUserExt.getLoginName());

        if(sysUserMapper.countByExample(sysUserExample) > compare) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public SysUserExt getUserExt(String id) {
        SysUserExt sysUserExt = new SysUserExt();

        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(sysUser, sysUserExt);

        SysUserProjectExample projectExample = new SysUserProjectExample();
        SysUserProjectExample.Criteria cinemaCri = projectExample.createCriteria();
        cinemaCri.andUserIdEqualTo(id);
        List<SysUserProject> sysUserCinemaList = projectMapper.selectByExample(projectExample);
        if(sysUserCinemaList != null && !sysUserCinemaList.isEmpty()) {
            List<String> ccodes = sysUserCinemaList.stream().map(SysUserProject::getProjectId).collect(Collectors.toList());
            sysUserExt.setCinemas(Joiner.on(",").join(ccodes));
        }

        SysUserRoleExample roleExample = new SysUserRoleExample();
        SysUserRoleExample.Criteria roleCri = roleExample.createCriteria();
        roleCri.andUserIdEqualTo(id);
        List<SysUserRole> sysUserRoleList = sysUserRoleMapper.selectByExample(roleExample);
        if(sysUserRoleList != null && !sysUserRoleList.isEmpty()) {
            List<String> ccodes = sysUserRoleList.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
            sysUserExt.setRoles(Joiner.on(",").join(ccodes));
        }
        return sysUserExt;
    }


}
