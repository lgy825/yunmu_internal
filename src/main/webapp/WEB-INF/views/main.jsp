<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>云幕国际运营管理平台</title>
    <%@ include file="/static/commons/common.jspf" %>
    <style>
        .layui-layer {
            box-shadow: none !important
        }
    </style>
    <script>
        function logout() {
            layer.confirm("如果您使用了免登录功能，退出后，将取消免登陆。可以直接关闭该页面，继续使用免登录。", {title: '友情提示'}, function () {
                location.href = "${ctx}/logout";
            });
        }

        setInterval(function () {
            $.ajax({
                url: "${ctx}/isout",
                type: "GET",
                cache: false,
                async: false,
                dataType: 'json',
                success: function (data) {
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    var sessionstatus = XMLHttpRequest.getResponseHeader("session-status");
                    // 通过XMLHttpRequest取得响应头，sessionstatus，
                    if (sessionstatus == "timeout") {
                        layer.alert("登录超时! 点击确定重新登录！", {icon: 7}, function () {
                            location.href = "${ctx}/tologin";
                        });
                    }
                },
                beforeSend: function () {
                },
                complete: function () {
                }
            });
        }, 30000);

        if (${first}) {
            layer.open({
                type: 1,
                title: false,
                closeBtn: 0,
                shade: [0.7],
                area: ['1239px', '638px'],
                // area: [$(document.body).width(), $(document.body).height()],
                skin: 'layui-layer-nobg', //没有背景色
                // shadeClose: true,
                content: '<div>' +
                '<img src="${ctx}/static/img/guide.png" usemap ="#planetmap" />' +
                '<map name="planetmap"><area shape="rect" coords="535,545,700,700" href="javascript:layer.closeAll();" style="outline:none;"/></map>' +
                '</div>'
            });
        }
    </script>
</head>
<body>
<div class="platform-home">
    <!-- 顶部固定 -->
    <div class="header-wrap clearfix">
        <div class="header-fixed">
            <h1 class="logo fl">云幕国际管理平台</h1>
            <div class="fl head-title">云幕国际管理平台</div>
            <div class="login fr">
                <div class="login-user fl">欢迎您！</div>
                <a href="javascript:void(0);" onclick="logout()">
                    <div class="login-out cursor fr"></div>
                </a>
            </div>
        </div>
    </div>
    <!-- 侧边栏 -->
    <div class="out-container">
        <div class="inner-container">
            <div class="aside-list">
                <ul class="">
                    <li class="aside-title">
                            <a class="clearfix" href="javascript:;">
                                <i class="vip-white fl"></i>
                                <span class="fl">主户管理</span>
                                <i class="add-icon add-white fr"></i>
                            </a>
                            <ul class="child-list color-white none">
                                <li class="child-menu">
                                    <a href="${ctx}/owner/toOwnerlist" target="homepage">业主管理</a>
                                </li>
                                <li class="child-menu">
                                    <a href="${ctx}/hourse/toHourselist" target="homepage">房屋管理</a>
                                </li>
                                <li class="child-menu">
                                    <a href="${ctx}/hourseType/toHourseTypelist" target="homepage">房屋类型管理</a>
                                </li>
                            </ul>
                    </li>
                    <li class="aside-title">
                        <a class="clearfix" href="javascript:;">
                            <i class="chart-white fl"></i>
                            <span class="fl">项目管理</span>
                            <i class="add-icon add-white fr"></i>
                        </a>
                        <ul class="child-list color-white none">
                            <li class="child-menu">
                                <a href="${ctx}/project/toProjectlist" target="homepage">项目列表</a>
                            </li>
                            <li class="child-menu">
                                <a href="javascript:layer.alert('功能开发中，请耐心等待!');" target="homepage">管理项目</a>
                            </li>
                        </ul>
                    </li>
                    <li class="aside-title">
                        <a class="clearfix" href="javascript:;">
                            <i class="order-white fl"></i>
                            <span class="fl">订单管理</span>
                            <i class="add-icon add-white fr"></i>
                        </a>
                        <ul class="child-list color-white none">
                            <li class="child-menu">
                                <a href="${ctx}/order/toOrderlist" target="homepage">订单管理</a>
                            </li>
                            <li class="child-menu">
                                <a href="${ctx}/order/toOrderItemlist" target="homepage">支出管理</a>
                            </li>
                        </ul>
                    </li>
                    <li class="aside-title">
                        <a class="clearfix" href="javascript:;">
                            <i class="setting-white fl"></i>
                            <span class="fl">系统管理</span>
                            <i class="add-icon add-white fr"></i>
                        </a>
                        <ul class="child-list color-white none">
                            <li class="child-menu">
                                <a href="${ctx}/company/toCompanylist" target="homepage">公司管理</a>
                            </li>
                            <li class="child-menu">
                                <a href="${ctx}/sysuser/toUserlist" target="homepage">员工管理</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- 嵌套页面 -->
    <div class="iframe-wrap">
        <iframe src="${ctx}/order/toOrderlist" name="homepage" id="iframe_wrap" frameborder="0" scrolling="auto"></iframe>
    </div>
</div>

</body>
</html>