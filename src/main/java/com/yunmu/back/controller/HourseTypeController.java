package com.yunmu.back.controller;

import com.yunmu.back.service.hourse.HourseService;
import com.yunmu.back.service.hourse.HourseTypeService;
import com.yunmu.core.base.BaseController;
import com.yunmu.core.base.Result;
import com.yunmu.core.constant.PageResult;
import com.yunmu.core.model.hourse.Hourse;
import com.yunmu.core.model.hourse.HourseType;
import com.yunmu.core.model.hourse.HourseTypeExt;
import com.yunmu.core.util.IdUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 13544 on 2019/6/18.
 */
@Controller
@RequestMapping("hourseType")
public class HourseTypeController extends BaseController {


    @Autowired
    private HourseTypeService hourseTypeService;

    @RequestMapping("/toHourseTypelist")
    public String toHourseTypelist() {
        return "hourse/toHourseTypelist";
    }

    @RequestMapping("/getpage")
    @ResponseBody
    public PageResult<HourseTypeExt> getShopPageByCondition(HttpServletRequest request,
                                                            Integer pageIndex,
                                                            Integer pageSize,
                                                            String typeName) {
        Map<String, Object> params = new HashMap<>();
        params.put("typeName", typeName);
        params.put("pageIndex", pageIndex + 1);
        params.put("pageSize", pageSize);
        return createSuccessPageResult(hourseTypeService.getPageByCondition(params));
    }

    @RequestMapping("/saveHourse")
    @ResponseBody
    public Result<Boolean> save(HourseType hourseType) {
        if(StringUtils.isBlank(hourseType.getId())) {
            hourseType.setId(IdUtils.getId(11));
            try {
                hourseTypeService.insert(hourseType);
            } catch (Exception e1) {
                return createFailedResult(e1.getMessage(), false);
            }
        } else {
            return  createSuccessResult(hourseTypeService.update(hourseType));
        }
        return createSuccessResult(true);
    }

    @RequestMapping("/tolook")
    public String toLook(String id, Model model) {
        if(StringUtils.isBlank(id)) {
            return "hourseType/hourseTypelist";
        }
        model.addAttribute("hourseId", id);
        return "hourse/lookhourse";
    }

    @RequestMapping("/toedit")
    public String toEdit(String id, Model model) {
        if(StringUtils.isBlank(id)) {
            return "hourseType/hourseTypelist";
        }
        model.addAttribute("hourseId", id);
        return "hourseType/newhourseType";
    }

    @RequestMapping("/get")
    @ResponseBody
    public Result<HourseType> update(String id) {
        if(StringUtils.isBlank(id)) {
            return createFailedResult("查询失败");
        }
        return createSuccessResult(hourseTypeService.getHourseByIdById(id));
    }

    @RequestMapping("/toaddHourse")
    public String toaddHourse() {
        return "hourse/newhourse";
    }

    @RequestMapping("/deleteHourse")
    @ResponseBody
    public Result<Boolean> deleteHourse(String hId) {

        return createSuccessResult(hourseTypeService.deleteByPrimaryKey(hId));
    }


}
