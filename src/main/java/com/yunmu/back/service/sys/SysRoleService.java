package com.yunmu.back.service.sys;

import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.model.sys.SysRoleExt;

import java.util.Map;

public interface SysRoleService {
    GenericPage<SysRoleExt> getRolePageByCondition(Map<String, Object> param);
}
