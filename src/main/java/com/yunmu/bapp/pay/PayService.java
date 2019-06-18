package com.yunmu.bapp.pay;

import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.model.pay.Pay;

import java.util.Map;

/**
 * Created by 13544 on 2019/6/18.
 */
public interface PayService {

    GenericPage<Pay> getPageByCondition(Map<String, Object> params);

    boolean insert(Pay pay);

    Boolean update(Pay pay);

    Pay getPayByIdById(String id);

    Boolean deleteByPrimaryKey(String id);
}
