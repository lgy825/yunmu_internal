package com.yunmu.back.service.contract.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunmu.back.service.contract.ContractService;
import com.yunmu.back.service.pay.PayService;
import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.dao.contract.ContractMapper;
import com.yunmu.core.dao.contract.ContractMapperExt;
import com.yunmu.core.dao.pay.PayMapper;
import com.yunmu.core.dao.pay.PayMapperExt;
import com.yunmu.core.dao.project.ProjectMapper;
import com.yunmu.core.dao.sys.SysUserMapper;
import com.yunmu.core.exception.DataException;
import com.yunmu.core.model.contract.Contract;
import com.yunmu.core.model.contract.ContractExt;
import com.yunmu.core.model.pay.Pay;
import com.yunmu.core.model.pay.PayExample;
import com.yunmu.core.model.pay.PayExt;
import com.yunmu.core.util.IdUtils;
import com.yunmu.core.util.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 13544 on 2019/6/18.
 */
@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractMapperExt contractMapperExt;
    @Autowired
    private ContractMapper contractMapper;
    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public GenericPage<ContractExt> getPageByCondition(Map<String, Object> params) {
        int pageIndex = 1, pageSize = 10;
        if(params.containsKey("pageIndex")) {
            if(params.get("pageIndex") != null &&
                    StringUtils.isNotBlank(params.get("pageIndex").toString())) {
                pageIndex = Integer.valueOf(params.get("pageIndex").toString());
                if(pageIndex < 1) {
                    pageIndex = 1;
                }
            }
        }
        if(params.containsKey("pageSize")) {
            if(params.get("pageIndex") != null &&
                    StringUtils.isNotBlank(params.get("pageSize").toString())) {
                pageSize = Integer.valueOf(params.get("pageSize").toString());
                if(pageSize < 1) {
                    pageSize = 10;
                }
            }
        }
        Page<ContractExt> pageInfo = PageHelper.startPage(pageIndex, pageSize, true);
        List<ContractExt> contractExts=contractMapperExt.getRentContractPage(params);
        for(ContractExt contractExt:contractExts ){
            contractExt.setOperaterBy(sysUserMapper.selectByPrimaryKey(contractExt.getCreateBy()).getUserName());
            if(contractExt.getContractType()==10){
                contractExt.setTypeName("租赁合同");
            }else if(contractExt.getContractType()==11){
                contractExt.setTypeName("委托合同");
            }
        }
        return new GenericPage<>(pageIndex, pageSize, contractExts, pageInfo.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addRentContract(Contract contract) {

        contract.setCreateTime(new Date());
        contract.setCreateBy(ShiroUtils.getUserId());
        contract.setId(IdUtils.getId(11));
        contract.setContractType(10);
        contract.setDelFlag(0);

        contractMapper.insertSelective(contract);
        return true;
    }

    @Override
    public boolean updateRentContract(Contract contract) {

        contract.setUpdateBy(ShiroUtils.getUserId());
        contract.setUpdateTime(new Date());
        contractMapper.updateByPrimaryKeySelective(contract);
        return true;
    }

    @Override
    public Contract getContract(String id) {
        return contractMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean delete(String contractId) {
        Contract contract=new Contract();
        contract.setDelFlag(1);
        contract.setUpdateBy(ShiroUtils.getUserId());
        contract.setUpdateTime(new Date());
        contract.setId(contractId);
        contractMapper.updateByPrimaryKey(contract);
        return true;
    }
}
