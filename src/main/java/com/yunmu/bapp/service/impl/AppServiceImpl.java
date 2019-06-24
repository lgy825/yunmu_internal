package com.yunmu.bapp.service.impl;

import com.yunmu.bapp.service.AppService;
import com.yunmu.core.base.Result;
import com.yunmu.core.dao.order.OrderMapper;
import com.yunmu.core.dao.order.OrderMapperExt;
import com.yunmu.core.dao.owner.OwnerMapper;
import com.yunmu.core.dao.owner.OwnerMapperExt;
import com.yunmu.core.exception.DataException;
import com.yunmu.core.model.owner.Owner;
import com.yunmu.core.model.owner.OwnerExt;
import com.yunmu.core.util.AppRequestParam;
import com.yunmu.core.util.AppResponseObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by 13544 on 2019/6/18.
 */
@Service
public class AppServiceImpl implements AppService{

    @Autowired
    private OwnerMapperExt ownerMapperExt;
    @Autowired
    private OwnerMapper ownerMapper;
    @Autowired
    private OrderMapperExt orderMapperExt;

    @Override
    public OwnerExt getOwnerByCondition(Owner owner) {
        OwnerExt owner1=ownerMapperExt.getOwnerByCondition(owner);
        if(owner1==null){
            throw new DataException("500","手机号或密码输入错误，请重新输入");
        }
        if(owner1.getStatus()==1){
            throw new DataException("500","该用户还未启用，请与管理原联系");
        }
        return owner1;
    }

    @Override
    public Boolean update(Owner owner) {
        owner.setCreateTime(new Date());
        try {
            ownerMapper.updateByPrimaryKeySelective(owner);
            return true;
        } catch (Exception e) {
            throw new DataException("用户信息修改失败");
        }
    }

    @Override
    public Map<String, Object> getHomeDataByCondition(Map<String, String> params) {

        //根据用户Id和时间获取收益
        double incomeAll=orderMapperExt.getAmountByCondition(params);
        //获取房子出租的天数
        int count=orderMapperExt.getCountByCondition(params);
        //获取房子出租率
        Calendar now = Calendar.getInstance();
        int day=now.get(Calendar.DAY_OF_MONTH);
        String houseRate=Math.round((count*100)/day)+"";
        //获取支出
        double extraCosts=orderMapperExt.getPayAmountByCondition(params);

        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("incomeAll",incomeAll);
        resultMap.put("count",count);
        resultMap.put("houseRate",houseRate);
        resultMap.put("extraCosts",extraCosts);

        return resultMap;
    }

    @Override
    public double getIncomeByCondition(Map<String, String> params) {
        double incomeAll=orderMapperExt.getAmountByCondition(params);
        return incomeAll;
    }

    @Override
    public List<AppResponseObj> getOrderPage(Map<String, String> params) {
        List<AppResponseObj> orderExtList=orderMapperExt.getOrderPageBycondition(params);
        return orderExtList;
    }


}
