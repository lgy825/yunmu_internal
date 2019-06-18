package com.yunmu.core.dao.pay;

import com.yunmu.core.model.pay.Pay;
import com.yunmu.core.model.pay.PayExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PayMapperExt {

    List<Pay> getPayPage(Map<String, Object> params);
}