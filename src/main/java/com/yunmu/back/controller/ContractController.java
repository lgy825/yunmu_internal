package com.yunmu.back.controller;

import com.yunmu.back.service.contract.ContractService;
import com.yunmu.back.service.pay.PayService;
import com.yunmu.core.base.BaseController;
import com.yunmu.core.base.Result;
import com.yunmu.core.constant.PageResult;
import com.yunmu.core.model.contract.Contract;
import com.yunmu.core.model.contract.ContractExt;
import com.yunmu.core.model.pay.Pay;
import com.yunmu.core.model.pay.PayExt;
import com.yunmu.core.model.project.Project;
import com.yunmu.core.util.IdUtils;
import com.yunmu.core.util.RegxUtils;
import com.yunmu.core.util.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 13544 on 2019/6/18.
 */
@RequestMapping("contract")
@Controller
public class ContractController extends BaseController{

    @Autowired
    private ContractService contractService;

    @RequestMapping("/toRentContractlist")
    public String toRentContractlist() {
        return "contract/rentcontractlist";
    }

    @RequestMapping("/getpage")
    @ResponseBody
    public PageResult<ContractExt> getShopPageByCondition(HttpServletRequest request,
                                                          Integer pageIndex, Integer pageSize,
                                                          String contractCode, String contracName,
                                                          String beginTime, String endTime, String projectId) {
        Map<String, Object> params = new HashMap<>();
        params.put("contractCode", contractCode);
        params.put("contracName", contracName);
        params.put("projectId", projectId);
        params.put("pageIndex", pageIndex + 1);
        params.put("pageSize", pageSize);
        if(RegxUtils.valid("^\\d{4}\\-\\d{2}\\-\\d{2}$", beginTime)) {
            params.put("beginTime", beginTime+"00:00:00");
        }
        if(RegxUtils.valid("^\\d{4}\\-\\d{2}\\-\\d{2}$", endTime)) {
            params.put("endTime", endTime+"23:59:59");
        }
        if(projectId!=null){
            params.put("projectId", projectId);
        }else{
            List<Project> projects= ShiroUtils.getAllMyCinemaList();
            List<String> projectIds=projects.stream().map(cinema -> cinema.getId()).collect(Collectors.toList());
            params.put("projectIds",projectIds);
        }

        return createSuccessPageResult(contractService.getPageByCondition(params));
    }

    @RequestMapping("/toaddRent")
    public String toaddRent() {
        return "contract/newrentcontract";
    }

    @RequestMapping("/tolookRent")
    public String tolookRent() {
        return "contract/lookrentcontract";
    }



    //addOrder
    @RequestMapping("/addRentContract")
    @ResponseBody
    public Result<Boolean> addRentContract(@RequestBody Contract contract) {
        if(StringUtils.isBlank(contract.getId())) {
            try {
                contractService.addRentContract(contract);
            } catch (Exception e1) {
                return createFailedResult(e1.getMessage(), false);
            }
        } else {
            return  createSuccessResult(contractService.updateRentContract(contract));
        }
        return createSuccessResult(true);
    }

    @RequestMapping("/toeditRent")
    public String toEdit(String id, Model model) {
        if(StringUtils.isBlank(id)) {
            return "contract/toRentContractlist";
        }
        model.addAttribute("contractId", id);
        return "contract/newrentcontract";
    }





}
