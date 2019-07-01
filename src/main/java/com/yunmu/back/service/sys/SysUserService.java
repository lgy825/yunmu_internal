package com.yunmu.back.service.sys;

import com.yunmu.core.base.Result;
import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.model.project.Project;
import com.yunmu.core.model.sys.SysUser;
import com.yunmu.core.model.sys.SysUserExt;

import java.util.List;
import java.util.Map;

public interface SysUserService {

    GenericPage<SysUserExt> getPageByCondition(Map<String, Object> params);

    Boolean insert(SysUserExt sysUserExt);

    Boolean update(SysUserExt sysUserExt);

    Boolean nameExist(SysUserExt sysUserExt);

    SysUserExt getUserExt(String id);

    List<Project> getProjectByUserId(String userId);

    List<SysUser> getUsersByCondition(Map<String, Object> param);

    boolean getUsersBeDisabled(String userId);

    boolean updatePassWord(SysUser sysUser);

    int getSysUserByCompanyCode(String companyCode);

}
