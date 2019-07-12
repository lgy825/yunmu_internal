package com.yunmu.back.service.sys.impl;
import com.yunmu.back.service.sys.SysMenuService;
import com.yunmu.core.model.sys.SysMenu;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 菜单业务处理类 ClassName:SysMunuServiceImpl
 *
 * @author ligy-008494
 * @create 2019-07-12 11:35
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Override
    public List<SysMenu> getMenuList() {
        return null;
    }

    @Override
    public boolean saveMenu(SysMenu sysMenu) {
        return false;
    }

    @Override
    public boolean updateMenu(SysMenu sysMenu) {
        return false;
    }

    @Override
    public SysMenu getMenu(String menuId) {
        return null;
    }

    @Override
    public boolean disableMenu(String menuId, String userId) {
        return false;
    }

    @Override
    public boolean unDisableMenu(String menuId, String userId) {
        return false;
    }

    @Override
    public boolean del(String menuId, String userId) {
        return false;
    }


}

