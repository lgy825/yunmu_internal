package com.yunmu.bapp.service;

import com.yunmu.core.model.owner.Owner;
import com.yunmu.core.model.owner.OwnerExt;

/**
 * Created by 13544 on 2019/6/18.
 */
public interface AppService {

    OwnerExt getOwnerByCondition(Owner owner);
}
