<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新建客户信息</title>
    <%@include file="/static/commons/common.jspf" %>
</head>
<body>
<div class="p20">
    <div class="bgc-ff">
        <!-- 广告投放 -->
        <div class="b_title">新建客户信息</div>
        <div class="hr">
            <hr>
        </div>
        <div class="p20">
            <input id="customerId" type="hidden" value="${customerId}" />
            <div class="">
                <!-- 必填选项 -->
                <div class="clearfix">
                    <div class="align-r mr8 mt6">项目</div>
                    <select id="companySel" class="select ml20 wid-238">
                    </select>
                    <span class="color-lred ml8 mt6">* 选择客户所属项目</span>
                </div>
                <div class="mt12">
                    <div class="align-r">客户代理人</div>
                    <input id="customerProxyName" maxlength="15" type="text" class="inpW set-inpwid ml8" placeholder="请输入客户代理人">
                    <span class="color-lred ml8 none">* 不超过15个字，不能出现其他特殊字符</span>
                </div>
                <div class="mt12">
                    <div class="align-r">姓名</div>
                    <input id="customerName" maxlength="15" type="text" class="inpW set-inpwid ml8" placeholder="请输入姓名">
                    <span class="color-lred ml8 none">* 不超过15个字，不能出现其他特殊字符</span>
                </div>
                <div class="mt12">
                    <div class="align-r">性别</div>
                    <select id="customerSex" class="select mr8">
                        <option value="1">男</option>
                        <option value="2">女</option>
                    </select>
                    <span class="color-lred ml8 none">* 不超过15个字，不能出现其他特殊字符</span>
                </div>
                <div class="mt12">
                    <div class="align-r">邮箱</div>
                    <input id="customerEmail" type="text" class="inpW set-inpwid ml8" placeholder="请输入邮箱">
                    <span class="color-lred ml8 none">请输入正确的邮箱格式，可为空</span>
                </div>
                <div class="mt12">
                    <div class="align-r">电话</div>
                    <input id="customerTel" maxlength="20" type="text" class="inpW set-inpwid ml8" placeholder="请输入联系电话">
                    <span class="color-lred ml8 none">请输入用户联系电话，可以为固话也可以为手机号，可为空</span>
                </div>
                <div class="mt12">
                    <div class="align-r">地址</div>
                    <input id="customerAddr" maxlength="50" type="text" class="inpW set-inpwid ml8" placeholder="请输入用户地址">
                    <span class="color-lred ml8">请输入用户的地址，可为空</span>
                </div>
                <div class="mt12">
                    <div class="align-r">社会统一代码</div>
                    <input id="idCard" maxlength="32" type="text" class="inpW set-inpwid ml8" placeholder="请输入客户社会统一代码">
                    <span class="color-lred ml8 none">请输入客户社会统一代码，不可为空</span>
                </div>
                <div class="mt12">
                    <div class="align-r">客户银行开户行</div>
                    <input id="openingBank" maxlength="32" type="text" class="inpW set-inpwid ml8" placeholder="请输入客户银行开户行">
                    <span class="color-lred ml8 none">请输入客户银行开户行，可为空</span>
                </div>
                <div class="mt12">
                    <div class="align-r">客户银行户名</div>
                    <input id="bankName" maxlength="32" type="text" class="inpW set-inpwid ml8" placeholder="请输入客户户名">
                    <span class="color-lred ml8 none">请输入客户银行户名，可为空</span>
                </div>
                <div class="mt12">
                    <div class="align-r">银行账号</div>
                    <input id="bankNumber" maxlength="32" type="text" class="inpW set-inpwid ml8" placeholder="请输入客户银行账号">
                    <span class="color-lred ml8 none">请输入客户银行账号，可为空</span>
                </div>
                <div class="pl88 mt30">
                    <input id="saveBtn" type="button" class="blue_btn blue_btn30" value="创建完成">
                    <a href="${ctx}/customer/toPersonnallist">
                        <input type="button" class="gray_btn gray-btn30 ml20" value="返回客户列表">
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<!-- scripts -->
<script type="text/javascript" src="${ctx}/static/js/mod/sys/user/newcustomer.js"></script>
</html>