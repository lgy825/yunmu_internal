package com.yunmu.core.dao.hourse;

import com.yunmu.core.model.hourse.HourseType;
import com.yunmu.core.model.hourse.HourseTypeExample;
import com.yunmu.core.model.hourse.HourseTypeExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HourseTypeMapperExt {

    List<HourseTypeExt> getHourseTypePage(Map<String, Object> params);
}