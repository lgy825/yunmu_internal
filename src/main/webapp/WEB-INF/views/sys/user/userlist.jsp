<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>员工管理</title>
    <%@include file="/static/commons/common.jspf" %>

    <link href="${ctx}/static/css/mricode.pagination.css" rel="stylesheet" />
    <script src="${ctx}/static/js/lib/jsrender.min.js"></script>
    <script src="${ctx}/static/js/lib/mricode.pagination.js"></script>
    <script src="${ctx}/static/js/lib/jsrender-converts.js"></script>
    <script id="trTmpl" type="text/x-jrender">
        <thead>
                <tr>
                    <th style='width: 10%;'><div>用户名</div></th>
                    <th style='width: 10%;'><div>所属公司</div></th>
                    <th style='width: 15%;'><div>管理项目名称</div></th>
                    <th style='width: 10%;'><div>电话</div></th>
                    <th style='width: 15%;'><div>邮箱</div></th>
                    <th style='width: 10%;'><div>添加时间</div></th>
                    <th style='width: 10%;'><div>状态</div></th>
                    <th style='width: 20%;'><div>操作</div></th>
                </tr>
        </thead>
        <tbody>
        {{for list}}
            <tr>
                <td>
                    <div>{{:loginName}}</div>
                </td>
                <td>
                    <div>{{:companyName}}</div>
                </td>
                <td>
                    <div>{{:projectName}}</div>
                </td>
                <td>
                    <div>{{:userTel}}</div>
                </td>
                <td>
                    <div>{{:userEmail}}</div>
                </td>
                <td>
                    <div>{{dateTime:createTime}}</div>
                </td>
                <td>
                    <div>
                        {{if status == '0'}}
                            使用中
                        {{else status == '1'}}
                             暂停使用
                        {{/if}}
                    </div>
                </td>
                <td>
                    <div class="">
                        <shiro:hasPermission name="sysmgr:usr:edit">
                        <a href="${ctx}/sysuser/toedit?id={{:id}}">
                            <input type="button" class="editbtn gray_btn mr10" value="编辑">
                        </a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="sysmgr:usr:startstop">
                        {{if disabledFlg == 1}}
                        <input type="button" class="shutbtn gray_btn mr10" data-sid="{{:id}}" value="停用">
                        {{else}}
                        <input type="button" class="openbtn gray_btn mr10" data-sid="{{:id}}" value="启用">
                        {{/if}}
                        </shiro:hasPermission>
                    </div>
                </td>
            </tr>
        {{/for}}
        </tbody>
    </script>
</head>
<body>
<div class="p20">
    <div class="bgc-ff min620">
        <div class="b_title">员工管理</div>
        <div class="hr">
            <hr>
        </div>
        <div class="pdtrl20">
            <shiro:hasPermission name="sysmgr:usr:add">
            <a href="${ctx}/sysuser/toadd">
                <input type="button" class="blue_btn" value="新建员工">
            </a>
            </shiro:hasPermission>
            <a href="" class="none">
                <input type="button" class="gray_btn ml20" value="功能介绍">
            </a>
        </div>
        <div class="select-search pdtrl20">
            <form action="">
                <div>
                    <input id="loginName" type="text" class="inpW ml20" placeholder="登录名">
                    <input id="searchBtn" type="button" class="blue_btn ml20" value="查询">
                    <input id="resetBtn" type="button" class="blue_btn ml20" value="重置">
                </div>
            </form>
        </div>
        <div class="pdtrl20">
            <div class="scroll-table">
                <table id="userTable" class="seller-lib sell-type vip-type tr-bg" cellpadding="0" cellspacing="0">
                </table>
            </div>
            <div class="page" id="tablPage">
                <div id="pagetotal" class="m-pagination"></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/js/mod/sys/user/userlist.js"></script>
<script>
    $(function () {
        fullScreen($('.scroll-table'),264);

        // 屏幕发生改变时重新加载
        $(window).on('resize', function () {
            fullScreen($('.scroll-table'), 264);
        });
    });
</script>
</body>
</html>