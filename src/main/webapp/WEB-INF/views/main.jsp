<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
                <div class="login-user fl"><shiro:principal property="userName"/>，欢迎您！</div>
                <div class="modify-psd fl" onclick="showPassWord()"><span class="pas-text">修改密码</span></div>
                <a href="javascript:void(0);" onclick="logout()"><div class="login-out cursor fr"></div></a>
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
                                <a href="${ctx}/pay/toPaylist" target="homepage">支出管理</a>
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

<!-- 修改密码 -->
<div class="modality-layer psd-layer " style="z-index:99999;display: none">
    <div class="modality-box pad-box">
        <div class="modality-title clearfix">
            <span class="fl layer-title">修改密码</span>
        </div>
        <div class="layer-line">
            <hr>
        </div>
        <div class="p20">
            <div class="">
                <span class="align-r w50">原密码</span>
                <input id="oldpass" type="password" AUTOCOMPLETE="off" class="inpW ml8">
            </div>
            <div class="mt20">
                <span class="align-r w50">新密码</span>
                <input id="newpass" type="password" class="inpW ml8" AUTOCOMPLETE="off" placeholder="字母与数字的组合至少8位">
            </div>
            <div class="mt20">
                <span class="align-r w50">确认密码</span>
                <input id="repass" type="password" AUTOCOMPLETE="off" class="inpW ml8">
            </div>
            <div class="layer-prompt">
                <input id="modifypass" type="button" class="blue_btn blue_btn30 mt20 mb10" onclick="modify()" value="修改">
                <input id="close_btn" type="button" class="gray_btn gray-btn30 ml20 cancel" onclick="hidepassword()" value="取消">
            </div>
        </div>
    </div>
</div>

</body>
<script>
    $(function () {
        setInterval(function () {
            $.ajax({
                url: "${ctx}/index/isout",
                type: "GET",
                cache: false,
                async: false,
                dataType: 'json',
                success: function (data) {
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    var sessionstatus = XMLHttpRequest.getResponseHeader("session-status");
                    // 通过XMLHttpRequest取得响应头，sessionstatus，
                    if(sessionstatus == "timeout"){
                        layer.alert("登录超时! 点击确定重新登录！", {icon: 7}, function () {
                            location.href = "${ctx}/tologin";
                        });
                    }
                },
                beforeSend: function () {},
                complete: function () {}
            });
        }, 30000);

        if(false) {
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
                    '<img src="${ctx}/img/guide.png" usemap ="#planetmap" />' +
                    '<map name="planetmap"><area shape="rect" coords="535,545,700,700" href="javascript:layer.closeAll();javascript:firstShowPassWord();" style="outline:none;"/></map>' +
                    '</div>'
            });

        }
    });

    function firstShowPassWord(){
        $(".modality-layer").show();
        $("#close_btn").hide();
    }

    function showPassWord(){
        $(".modality-layer").show();
        $("#close_btn").show();
    }

    function hidepassword(){
        $(".modality-layer").hide();
    }

    function modify() {
        var oldpass = $.trim($("#oldpass").val());
        var newpass = $.trim($("#newpass").val());
        var repass = $.trim($("#repass").val());
        if (!oldpass) {
            layer.msg("请输入原密码");
            return;
        }
        if (!newpass) {
            layer.msg("请输入新密码");
            return;
        }
        if (!repass) {
            layer.msg("请输入确认密码");
            return;
        }
        if (newpass != repass) {
            layer.msg("两次密码输入不一致");
            return;
        }
    }

    function repassword() {
        var newpass = $.trim($("#newpass").val());
        $.ajax({
            url: ctx + "index/updatePassWord",
            type: "POST",
            cache: false,
            async: false,
            dataType: 'json',
            data: {
                password: newpass
            },
            success: function (data) {
                if (data && data.resultCode === '0') {
                    location.href = "${ctx}/logout";
                } else {
                    if (data.resultDesc) {
                        layer.msg(data.resultDesc);
                    } else {
                        layer.msg('密码修改失败 !');
                    }
                }
            },
            error: function () {
                layer.msg('密码修改失败 !');
            }
        });
    }

    function logout() {
        layer.confirm("如果您使用了免登录功能，退出后，将取消免登陆。可以直接关闭该页面，继续使用免登录。", {title:'友情提示'}, function() {
            sessionStorage.clear();
            location.href = "${ctx}/logout";
        });
    }


</script>
</html>