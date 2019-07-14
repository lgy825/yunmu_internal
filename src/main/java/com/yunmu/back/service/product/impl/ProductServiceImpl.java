package com.yunmu.back.service.product.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunmu.back.service.product.ProductService;
import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.dao.product.ProductMapper;
import com.yunmu.core.dao.product.ProductMapperExt;
import com.yunmu.core.dao.project.ProjectMapper;
import com.yunmu.core.model.pay.PayExt;
import com.yunmu.core.model.product.Product;
import com.yunmu.core.model.product.ProductExt;
import com.yunmu.core.util.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductMapperExt productMapperExt;

    @Autowired
    private ProjectMapper projectMapper;
    @Override
    public GenericPage<ProductExt> getPageByCondition(Map<String, Object> params) {
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
        Page<ProductExt> pageInfo = PageHelper.startPage(pageIndex, pageSize, true);
        List<ProductExt> productExts=productMapperExt.getPayPage(params);
        for(ProductExt productExt:productExts ){
            productExt.setProjectName(projectMapper.selectByPrimaryKey(productExt.getProjectId()).getProjectName());
        }
        return new GenericPage<>(pageIndex, pageSize, productExts, pageInfo.getTotal());
    }

    @Override
    public boolean insert(Product product) {
        if(product!=null){
            product.setCreateBy(ShiroUtils.getUser().getUserName());
            product.setCreateTime(new Date());
            product.setDelFlag(0);
            try {
                productMapper.insert(product);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public Boolean update(Product product) {
        if(product!=null){
            product.setUpdateBy(ShiroUtils.getUser().getUserName());
            product.setUpdateTime(new Date());
            productMapper.updateByPrimaryKeySelective(product);
            return true;
        }
        return false;
    }

    @Override
    public Product getPayByIdById(String id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean deleteByPrimaryKey(String id) {
        if(!StringUtils.isBlank(id)){
            Product product=new Product();
            product.setDelFlag(1);
            product.setId(id);
            productMapper.updateByPrimaryKeySelective(product);
            return true;
        }
        return false;
    }
}
