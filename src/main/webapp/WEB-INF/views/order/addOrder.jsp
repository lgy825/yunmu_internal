<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="renderer" content="webkit">
    <title>添加订单</title>
    <%@include file="/static/commons/common.jspf" %>
    <link rel="stylesheet" href="${ctx}/static/css/jquery.datetimepicker.css">

</head>
<body>
<div class="p20">
    <div class="bgc-ff">
        <!-- 广告投放 -->
        <div class="b_title">添加订单</div>
        <div class="hr">
            <hr>
        </div>
        <div class="p20">
            <input id="oId" type="hidden" value="${oId}" />
            <div class="bgc-ff full-white">
                <!-- 必填选项 -->
                <div class="mt12 clearfix">
                    <div class="align-r mr8 mt6">支付方式</div>
                    <select id="paySel" class="select ml16 wid-238">

                    </select>
                    <span class="color-lred ml8 mt6">* 支付方式*</span>
                </div>
                <!-- 必填选项 -->
                <div class="mt12 clearfix">
                    <div class="align-r mr8 mt6">订单来源</div>
                    <select id="sourceSel" class="select ml16 wid-238">

                    </select>
                    <span class="color-lred ml8 mt6">*请选择订单来源*</span>
                </div>
                <!-- 必填选项 -->
                <div class="mt12 clearfix">
                    <div class="align-r mr8 mt6">房间号</div>
                    <select id="hourseSel" class="select ml16 wid-238">
                    </select>
                    <span class="color-lred ml8 mt6">*请选择所属房间*</span>
                </div>
                <div class="mt12 searchBox">
                    <div class="b_label lab_wid1 relative">订单日期<i class="whats"></i><p class="modify-what">设置订单的开始时间和结束时间</p></div>
                    <input type="text" class="inpW inpWid2 timer" id="timeSpick" placeholder=""/>
                    <input type="hidden" id="startTime" value=""/>
                    <span class="zhi">至</span>
                    <input type="text" class="inpW inpWid2 timer mr8" id="timeEpick" placeholder=""/>
                    <input type="hidden" id="endTime" value="" />
                    <span class="color-lred none">* 结束日期不能早于开始日期 </span>
                </div>
                <div class="mt12">
                    <div class="align-r">房费</div>
                    <input id="oRecAmount" maxlength="5" type="text" class="inpW set-inpwid ml8" placeholder="请输入房费">
                    <span class="color-lred ml8 none">* 不超过5个字，不能出现其他特殊字符</span>
                </div>
                <div class="pl88 mt30">
                    <input id="saveBtn" type="button" class="blue_btn blue_btn30" value="创建完成">
                    <a href="${ctx}/order/toOrderlist">
                        <input type="button" class="gray_btn gray-btn30 ml20" value="返回订单列表">
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<!-- scripts -->
<script type="text/javascript" src="${ctx}/static/js/lib/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="${ctx}/static/js/mod/order/addorder.js"></script>
</html>