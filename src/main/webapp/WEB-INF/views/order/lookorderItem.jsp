<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>订单项录入</title>
    <%@include file="/static/commons/common.jspf" %>
</head>
<body>
<div class="p20">
    <div class="bgc-ff">
        <!-- 广告投放 -->
        <div class="b_title">查看支出项</div>
        <div class="hr">
            <hr>
        </div>
        <div class="p20">
            <input id="dId" type="hidden" value="${dId}" />
            <div class="">
                <!-- 必填选项 -->
                <div class="mt12 clearfix">
                    <div class="align-r mr8 mt6">请选择订单号</div>
                    <select id="orderSel" class="select ml16 wid-238">
                    </select>
                    <span class="color-lred ml8 mt6">* 选择所属的订单*</span>
                </div>
                <div class="mt12">
                    <div class="align-r">名称</div>
                    <input id="dName" maxlength="15" type="text" class="inpW set-inpwid ml8" placeholder="请输入名称">
                    <span class="color-lred ml8 none">* 不超过15个字，不能出现其他特殊字符</span>
                </div>
                <div class="mt12">
                    <div class="align-r">金额</div>
                    <input id="dAmount" maxlength="5" type="text" class="inpW set-inpwid ml8" placeholder="请输入金额">
                    <span class="color-lred ml8 none">* 不超过5个字母或数字，不能出现其他特殊字符</span>
                </div>
                <div class="mt12 clearfix">
                    <div class="align-r fl relative">
                        描述
                        <i class="whats define-layer"></i>
                        <p class="modify-what">说明</p>
                    </div>
                    <div class="text-des ml8 fl"><textarea id="dDesc"></textarea></div>
                    <span class="color-lred fl ml8">* 不超过100个字</span>
                </div>
                <div class="pl88 mt30">
                    <a href="${ctx}/order/toOrderItemlist">
                        <input type="button" class="gray_btn gray-btn30 ml20" value="返回支出列表">
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<!-- scripts -->
<script type="text/javascript" src="${ctx}/static/js/mod/order/lookorderItem.js"></script>
</html>