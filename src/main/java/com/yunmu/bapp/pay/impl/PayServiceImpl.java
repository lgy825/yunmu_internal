package com.yunmu.bapp.pay.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunmu.bapp.pay.PayService;
import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.dao.pay.PayMapper;
import com.yunmu.core.dao.pay.PayMapperExt;
import com.yunmu.core.model.pay.Pay;
import com.yunmu.core.util.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 13544 on 2019/6/18.
 */
@Service
public class PayServiceImpl implements PayService{

    @Autowired
    private PayMapperExt payMapperExt;
    @Autowired
    private PayMapper payMapper;

    @Override
    public GenericPage<Pay> getPageByCondition(Map<String, Object> params) {
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
        Page<Pay> pageInfo = PageHelper.startPage(pageIndex, pageSize, true);
        List<Pay> hourseTypeExts=payMapperExt.getPayPage(params);
        return new GenericPage<>(pageIndex, pageSize, hourseTypeExts, pageInfo.getTotal());
    }

    @Override
    public boolean insert(Pay pay) {
        if(pay!=null){
            pay.setCreateBy(ShiroUtils.getUserId());
            pay.setCreateTime(new Date());
            pay.setDelFlag(0);
            try {
                payMapper.insert(pay);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public Boolean update(Pay pay) {
        if(pay!=null){
            pay.setUpdateBy(ShiroUtils.getUserId());
            pay.setUpdateTime(new Date());
            payMapper.updateByPrimaryKey(pay);
            return true;
        }
        return false;
    }

    @Override
    public Pay getPayByIdById(String id) {
        return payMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean deleteByPrimaryKey(String id) {
        if(!StringUtils.isBlank(id)){
            Pay pay=new Pay();
            pay.setDelFlag(1);
            pay.setPayId(id);
            payMapper.updateByPrimaryKey(pay);
            return true;
        }
        return false;
    }
}
