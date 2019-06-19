package com.yunmu.back.service.project.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunmu.back.service.project.ProjectService;
import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.dao.project.ProjectMapper;
import com.yunmu.core.dao.project.ProjectMapperExt;
import com.yunmu.core.dao.project.ProjectTypeMapper;
import com.yunmu.core.model.project.Project;
import com.yunmu.core.model.project.ProjectExt;
import com.yunmu.core.model.project.ProjectType;
import com.yunmu.core.util.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapperExt projectMapperExt;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProjectTypeMapper projectTypeMapper;

    @Override
    public GenericPage<ProjectExt> getPageByCondition(Map<String, Object> params) {
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
        Page<ProjectExt> pageInfo = PageHelper.startPage(pageIndex, pageSize, true);
        List<ProjectExt> projectExts=projectMapperExt.getProjectPage(params);

        for(ProjectExt projectExt:projectExts){
            ProjectType projectType=projectTypeMapper.selectByPrimaryKey(projectExt.getTypeCode());
            projectExt.setTypaName(projectType.getTypeName());
        }
        return new GenericPage<>(pageIndex, pageSize, projectExts, pageInfo.getTotal());
    }

    @Override
    public Boolean insert(Project project) {
        if(project!=null) {
            project.setCreateBy("lgy");
            project.setCreateTime(new Date());
            project.setDelFlag(0);
            project.setStatus(1);
            try {
                projectMapper.insert(project);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public Boolean update(Project project) {
        try {
            project.setUpdateBy("Lgy");
            project.setUpdateTime(new Date());
            projectMapper.updateByPrimaryKeySelective(project);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ProjectType> getProjectType() {
        return projectTypeMapper.getProjectTypeAll();
    }

    @Override
    public Project getProjectById(String id) {
        return projectMapper.selectByPrimaryKey(id);
    }
}