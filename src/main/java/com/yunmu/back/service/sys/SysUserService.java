package com.yunmu.back.service.sys;

import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.model.sys.SysUser;
import com.yunmu.core.model.sys.SysUserExt;

import java.util.Map;

public interface SysUserService {

    GenericPage<SysUserExt> getPageByCondition(Map<String, Object> params);

    Boolean insert(SysUser sysUser);

}
