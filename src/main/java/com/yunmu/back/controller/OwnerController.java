package com.yunmu.back.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yunmu.back.service.order.OrderService;
import com.yunmu.back.service.owner.OwnerService;
import com.yunmu.core.base.BaseController;
import com.yunmu.core.base.Result;
import com.yunmu.core.constant.PageResult;
import com.yunmu.core.model.order.Order;
import com.yunmu.core.model.order.OrderExt;
import com.yunmu.core.model.owner.Owner;
import com.yunmu.core.model.owner.OwnerExt;
import com.yunmu.core.model.project.Project;
import com.yunmu.core.util.Encodes;
import com.yunmu.core.util.IdUtils;
import com.yunmu.core.util.ShiroUtils;
import com.yunmu.core.util.excel.ExportExcel;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by 13544 on 2019/5/19.
 */
@Controller
@RequestMapping("/owner")
public class OwnerController extends BaseController {

    @Autowired
    private OwnerService ownerService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/toOwnerlist")
    public String toList() {
        return "owner/ownerlist";
    }

    @RequestMapping("/toaddOwner")
    public String toaddOwner() {
        return "owner/newowner";
    }

    @RequestMapping("/getpage")
    @ResponseBody
    public PageResult<OwnerExt> getShopPageByCondition(HttpServletRequest request,
                                                       Integer pageIndex,
                                                       Integer pageSize,
                                                       String ownerTel,
                                                       String ownerName) {
        Map<String, Object> params = new HashMap<>();
        List<Project> projects = ShiroUtils.getAllMyCinemaList();
        List<String> projectIds = projects.stream().map(cinema -> cinema.getId()).collect(Collectors.toList());
        params.put("projectIds", projectIds);
        params.put("ownerTel", ownerTel);
        params.put("ownerName", ownerName);
        params.put("pageIndex", pageIndex + 1);
        params.put("pageSize", pageSize);
        return createSuccessPageResult(ownerService.getPageByCondition(params));
    }

    @RequestMapping("/saveOwner")
    @ResponseBody
    public Result<Boolean> save(Owner owner) {
        if (StringUtils.isBlank(owner.getId())) {
            owner.setId(IdUtils.getId(11));
            try {
                ownerService.insert(owner);
            } catch (Exception e1) {
                return createFailedResult(e1.getMessage(), false);
            }
        } else {
            return createSuccessResult(ownerService.update(owner));
        }
        return createSuccessResult(true);
    }

    @RequestMapping("/tolook")
    public String toLook(String id, Model model) {
        if (StringUtils.isBlank(id)) {
            return "owner/ownerlist";
        }
        model.addAttribute("ownerId", id);
        return "owner/lookowner";
    }

    @RequestMapping("/get")
    @ResponseBody
    public Result<Owner> update(String id) {
        if (StringUtils.isBlank(id)) {
            return createFailedResult("查询失败");
        }
        return createSuccessResult(ownerService.getOwnerById(id));
    }

    @RequestMapping("/toedit")
    public String toEdit(String id, Model model) {
        if (StringUtils.isBlank(id)) {
            return "owner/ownerlist";
        }
        model.addAttribute("ownerId", id);
        return "owner/newowner";
    }

    @RequestMapping("/disableowner")
    @ResponseBody
    public Result<Boolean> disableOwner(String id) {
        Owner owner = new Owner();
        owner.setId(id);
        owner.setStatus(1);
        return createSuccessResult(ownerService.update(owner));
    }

    @RequestMapping("/undisableowner")
    @ResponseBody
    public Result<Boolean> undisableowner(String id) {
        Owner owner = new Owner();
        owner.setId(id);
        owner.setStatus(0);
        return createSuccessResult(ownerService.update(owner));
    }

    @RequestMapping("/toExport")
    @ResponseBody
    public void toExport(String id, HttpServletResponse response) {
        OutputStream os = null;
        ExportExcel ee = null;
        //获取当月一号开始到当前时间的订单详情表
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        String endTime = sdf.format(new Date());
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String beginTime = sdf2.format(c.getTime()) + " 00:00:00";
        Map<String, String> params = Maps.newHashMap();
        params.put("ownerCode", id);
        params.put("beginTime", beginTime);
        params.put("endTime", endTime);
        try {
            List<OrderExt> orderList = orderService.getOrdersByDate(params);
            Owner owner = ownerService.getOwnerById(id);
            List<String> headList = Lists.newArrayList();
            headList.add("订单号");
            headList.add("房间号");
            headList.add("订单创建时间");
            headList.add("订单来源");
            headList.add("支付方式");
            headList.add("实收金额(元)");
            headList.add("支出金额(元)");

            List<String> parmList = Lists.newArrayList();
            parmList.add("用户名:" + owner.getOwnerName());
            parmList.add("导出数量:" + orderList.size());
            ee = new ExportExcel("业主收支列表", headList, parmList);
            if (orderList != null) {
                List<List<String>> list = new ArrayList<List<String>>();
                for (OrderExt orderExt : orderList) {
                    List<String> list1 = new ArrayList<String>();
                    list1.add(orderExt.getId());
                    list1.add(orderExt.getHourseNumber());
                    list1.add(sdf.format(orderExt.getCreateTime()));
                    list1.add(orderExt.getSourceWay());
                    list1.add(orderExt.getPayWay());
                    list1.add(orderExt.getOrderRecAmount().toString());
                    list1.add(orderExt.getOrderActAmount().toString());
                    list.add(list1);
                }
                for (int i = 0; i < list.size(); i++) {
                    Row row = ee.addRow();
                    for (int j = 0; j < list.get(i).size(); j++) {
                        ee.addCell(row, j, list.get(i).get(j));
                    }
                }
                response.reset();
                response.setHeader("Content-Disposition",
                        "attachment; filename=" + Encodes.urlEncode(owner.getOwnerName() + "业主.xls"));
                response.setContentType("application/msexcel;charset=utf-8");
                os = new BufferedOutputStream(response.getOutputStream());
                ee.write(os);
                ee.dispose();
                os.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ee != null) {
                ee.dispose();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

