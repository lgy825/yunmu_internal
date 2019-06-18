package com.yunmu.bapp.service.impl;

import com.yunmu.bapp.service.AppService;
import com.yunmu.core.dao.owner.OwnerMapperExt;
import com.yunmu.core.exception.DataException;
import com.yunmu.core.model.owner.Owner;
import com.yunmu.core.model.owner.OwnerExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 13544 on 2019/6/18.
 */
@Service
public class AppServiceImpl implements AppService{

    @Autowired
    private OwnerMapperExt ownerMapperExt;

    @Override
    public OwnerExt getOwnerByCondition(Owner owner) {
        String ownerPwd=owner.getOwnerPwd();

        OwnerExt owner1=ownerMapperExt.getOwnerByCondition(owner);
        if(owner1==null){
            throw new DataException("500","手机号或密码输入错误，请重新输入");
        }
        if(owner1.getStatus()==1){
            throw new DataException("500","该用户还未启用，请与管理原联系");
        }
        return owner1;
    }
}
