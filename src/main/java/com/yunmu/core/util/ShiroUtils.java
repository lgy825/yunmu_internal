package com.yunmu.core.util;


import com.yunmu.core.model.sys.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;


/**
 * 获取当前用户信息 ClassName:ShiroUtils
 *
 * @author yangbin
 * @create 2017-11-24 17:16
 */
public class ShiroUtils {

        /*
         * 判断是否存在某权限
         */
        public static Boolean hasPermission(String permission) {
            Subject currentUser = SecurityUtils.getSubject();
            return currentUser.isPermitted(permission);
        }

        /**
         * 获取用户id
         */
        public static String getUserId() {
            Subject currentUser = SecurityUtils.getSubject();
            SysUser user = (SysUser) currentUser.getPrincipal();
            return user.getId();
        }


        /**
         * 获取用户
         */
        public static SysUser getUser() {
            Subject currentUser = SecurityUtils.getSubject();
            return  (SysUser) currentUser.getPrincipal();
        }


}