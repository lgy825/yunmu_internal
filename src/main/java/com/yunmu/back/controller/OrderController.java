package com.yunmu.back.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.yunmu.back.service.order.OrderService;
import com.yunmu.core.base.BaseController;
import com.yunmu.core.base.Result;
import com.yunmu.core.constant.PageResult;
import com.yunmu.core.model.order.OrderExt;
import com.yunmu.core.model.owner.Owner;
import com.yunmu.core.model.pay.PayWay;
import com.yunmu.core.model.project.Project;
import com.yunmu.core.model.source.OrderSource;
import com.yunmu.core.util.Encodes;
import com.yunmu.core.util.IdUtils;
import com.yunmu.core.util.RegxUtils;
import com.yunmu.core.util.ShiroUtils;
import com.yunmu.core.util.excel.ExportExcel;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 13544 on 2019/6/18.
 */
@RequestMapping("order")
@Controller
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderSercvice;

    @RequestMapping("/toOrderlist")
    public String toOrderlist() {
        return "order/orderlist";
    }

    @RequestMapping("/toDelOrderlist")
    public String toDelOrderlist() {
        return "order/orderdellist";
    }


    @RequestMapping("/getpage")
    @ResponseBody
    public PageResult<OrderExt> getShopPageByCondition(HttpServletRequest request,
                                                       Integer pageIndex,
                                                       Integer pageSize,
                                                       String beginTime,
                                                       String endTime,
                                                       String orderId,String hourseNumber) {
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        params.put("hourseNumber",hourseNumber);
        params.put("pageIndex", pageIndex + 1);
        params.put("pageSize", pageSize);
        if(RegxUtils.valid("^\\d{4}\\-\\d{2}\\-\\d{2}$", beginTime)) {
            params.put("beginTime", beginTime);
        }
        if(RegxUtils.valid("^\\d{4}\\-\\d{2}\\-\\d{2}$", endTime)) {
            params.put("searchTimeEnd", endTime);
        }
        params.put("delFlag",0);
        List<Project> projects= ShiroUtils.getAllMyCinemaList();
        List<String> projectIds=projects.stream().map(cinema -> cinema.getId()).collect(Collectors.toList());
        params.put("projectIds",projectIds);
        return createSuccessPageResult(orderSercvice.getPageByCondition(params));
    }

    @RequestMapping("/getdelpage")
    @ResponseBody
    public PageResult<OrderExt> getPageByCondition(HttpServletRequest request,
                                                       Integer pageIndex,
                                                       Integer pageSize,
                                                       String beginTime,
                                                       String endTime,
                                                       String orderId,String hourseNumber) {
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        params.put("hourseNumber",hourseNumber);
        params.put("pageIndex", pageIndex + 1);
        params.put("pageSize", pageSize);
        if(RegxUtils.valid("^\\d{4}\\-\\d{2}\\-\\d{2}$", beginTime)) {
            params.put("beginTime", beginTime);
        }
        if(RegxUtils.valid("^\\d{4}\\-\\d{2}\\-\\d{2}$", endTime)) {
            params.put("searchTimeEnd", endTime);
        }
        List<Project> projects= ShiroUtils.getAllMyCinemaList();
        List<String> projectIds=projects.stream().map(cinema -> cinema.getId()).collect(Collectors.toList());
        params.put("projectIds",projectIds);
        params.put("delFlag",1);
        return createSuccessPageResult(orderSercvice.getPageByCondition(params));
    }

    @RequestMapping("/toedit")
    public String toEdit(String id, Model model) {
        if(StringUtils.isBlank(id)) {
            return "order/orderlist";
        }
        model.addAttribute("orderId", id);
        return "order/addOrder";
    }

    @RequestMapping("/toaddOrder")
    public String toaddOrder() {
        return "order/addOrder";
    }

    @RequestMapping("/getPayWayAll")
    @ResponseBody
    public Result<List<PayWay>> getPayWayAll(){
        return createSuccessResult(orderSercvice.getPayWay());
    }

    @RequestMapping("/getOrdeSourceAll")
    @ResponseBody
    public Result<List<OrderSource>> getOrdeSourceAll(){
        return createSuccessResult(orderSercvice.getOrderSource());
    }

    //addOrder
    @RequestMapping("/addOrder")
    @ResponseBody
    public Result<Boolean> addOrder(@RequestBody  OrderExt orderExt) {
        if(StringUtils.isBlank(orderExt.getId())) {
            try {
                orderSercvice.saveOrder(orderExt);
            } catch (Exception e1) {
                return createFailedResult(e1.getMessage(), false);
            }
        } else {
            return  createSuccessResult(orderSercvice.updateOrder(orderExt));
        }
        return createSuccessResult(true);
    }

    //跳转订单详情页面
    @RequestMapping("/tolook")
    public String tolook(String id, Model model) {
        if(StringUtils.isBlank(id)) {
            return "order/orderlist";
        }
        OrderExt orderExt=orderSercvice.getOrderDetail(id);
        model.addAttribute("orderExt",orderExt);
        return "order/orderDetail";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result<Boolean> delete(String id) {

        return createSuccessResult(orderSercvice.delete(id));
    }


    @RequestMapping("/get")
    @ResponseBody
    public Result<OrderExt> get(String id){
        return createSuccessResult(orderSercvice.get(id));
    }

    @RequestMapping("/exportOrder")
    @ResponseBody
    public void toExport(String orderId,String hourseNumber,String beginTime,String endTime,
                         HttpServletResponse response) {
        OutputStream os = null;
        ExportExcel ee = null;
        Map<String, String> params = Maps.newHashMap();
        if(orderId!=null && !"".equals(orderId)){
            params.put("orderId", orderId);
        }
        if(!"".equals(hourseNumber) && hourseNumber!=null){
            params.put("hourseNumber", hourseNumber);
        }
        if(beginTime!=null && !"".equals(beginTime)){
            params.put("beginTime", beginTime);
        }
        if(endTime!=null && !"".equals(endTime)){
            params.put("endTime", endTime);
        }
        try {
            //根据参数获取导出的订单列表
            List<OrderExt> orderList = orderSercvice.getOrdersByDate(params);
            //计算总收益
            double orderRec=orderSercvice.getAllRecByParam(params);
            double orderAct=orderSercvice.getAllActByParam(params);
            BigDecimal bigDecimal=new BigDecimal(orderAct);
            double orderPay=bigDecimal.subtract(new BigDecimal(orderAct)).doubleValue();
            //计算总支出
            List<String> headList = Lists.newArrayList();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            headList.add("订单号");
            headList.add("房间号");
            headList.add("订单创建时间");
            headList.add("订单来源");
            headList.add("支付方式");
            headList.add("实收金额(元)");
            headList.add("支出金额(元)");

            List<String> parmList = Lists.newArrayList();
            parmList.add("房间号:" + hourseNumber);
            parmList.add("导出数量:" + orderList.size());
            parmList.add("订单总收益:"+orderRec);
            parmList.add("应收总金额:"+orderAct);
            parmList.add("支出总金额:"+orderPay);
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
                        "attachment; filename=" + Encodes.urlEncode(hourseNumber + "房间收益.xls"));
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
