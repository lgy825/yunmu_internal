<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新建客</title>
    <%@include file="/static/commons/common.jspf" %>
</head>
<body>
<div class="p20">
    <div class="bgc-ff">
        <!-- 广告投放 -->
        <div class="b_title">新建业务员</div>
        <div class="hr">
            <hr>
        </div>
        <div class="p20">
            <input id="personnalId" type="hidden" value="${personnalId}" />
            <div class="">
                <!-- 必填选项 -->
                <div class="clearfix">
                    <div class="align-r mr8 mt6">公司</div>
                    <select id="companySel" class="select ml20 wid-238">
                    </select>
                    <span class="color-lred ml8 mt6">* 选择员工所属公司</span>
                </div>
                <div class="mt12">
                    <div class="align-r">姓名</div>
                    <input id="personnalName" maxlength="15" type="text" class="inpW set-inpwid ml8" placeholder="请输入客户姓名">
                    <span class="color-lred ml8 none">* 不超过15个字，不能出现其他特殊字符</span>
                </div>

                <div class="mt12">
                    <div class="align-r">性别</div>
                    <select id="personnalSex" class="select mr8">
                        <option value="1">男</option>
                        <option value="2">女</option>
                    </select>
                    <span class="color-lred ml8 none">* 不超过15个字，不能出现其他特殊字符</span>
                </div>
                <div class="mt12">
                    <div class="align-r">邮箱</div>
                    <input id="personnalEmail" type="text" class="inpW set-inpwid ml8" placeholder="请输入邮箱">
                    <span class="color-lred ml8 none">请输入正确的邮箱格式，可为空</span>
                </div>
                <div class="mt12">
                    <div class="align-r">电话</div>
                    <input id="personnalTel" maxlength="20" type="text" class="inpW set-inpwid ml8" placeholder="请输入联系电话">
                    <span class="color-lred ml8 none">请输入用户联系电话，可以为固话也可以为手机号，可为空</span>
                </div>
                <div class="mt12">
                    <div class="align-r">地址</div>
                    <input id="personnalAddr" maxlength="50" type="text" class="inpW set-inpwid ml8" placeholder="请输入用户地址">
                    <span class="color-lred ml8">请输入用户的地址，可为空</span>
                </div>
                <div class="mt20">
                    <div class="align-r mr8">入职日期</div>
                    <input type="text" class="inpW inpWid5 timer" id="timeJoined" placeholder="入职日期">
                </div>
                <div class="pl88 mt30">
                    <input id="saveBtn" type="button" class="blue_btn blue_btn30" value="创建完成">
                    <a href="${ctx}/personnal/toPersonnallist">
                        <input type="button" class="gray_btn gray-btn30 ml20" value="返回业务员列表">
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<!-- scripts -->
<script type="text/javascript" src="${ctx}/static/js/mod/sys/user/newuser.js"></script>
</html>