package com.yunmu.core.dao.contract;

import com.yunmu.core.model.contract.Contract;
import com.yunmu.core.model.contract.ContractExample;
import com.yunmu.core.model.contract.ContractExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ContractMapperExt {

    List<ContractExt> getRentContractPage(Map<String, Object> params);

}