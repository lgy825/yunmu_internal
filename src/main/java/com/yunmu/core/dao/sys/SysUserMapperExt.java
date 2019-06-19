package com.yunmu.core.dao.sys;

import com.yunmu.core.model.sys.SysUser;
import com.yunmu.core.model.sys.SysUserExample;
import com.yunmu.core.model.sys.SysUserExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysUserMapperExt {

    List<SysUserExt> getSysUserPage(Map<String, Object> params);
}