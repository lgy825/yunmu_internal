<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>收房管理</title>
    <%@include file="/static/commons/common.jspf" %>
    <link href="${ctx}/static/css/mricode.pagination.css" rel="stylesheet" />
    <script src="${ctx}/static/js/lib/jsrender.min.js"></script>
    <script src="${ctx}/static/js/lib/mricode.pagination.js"></script>
    <script src="${ctx}/static/js/lib/jsrender-converts.js"></script>
    <script id="trTmpl" type="text/x-jrender">
        <thead>
                <tr>
                    <th style='width: 8%;'><div>房间号</div></th>
                    <th style='width: 8%;'><div>地区号</div></th>
                    <th style='width: 8%;'><div>房屋类型</div></th>
                    <th style='width: 8%;'><div>业主</div></th>
                    <th style='width: 10%;'><div>添加时间</div></th>
                    <th style='width: 18%;'><div>地址</div></th>
                    <th style='width: 18%;'><div>房子说明</div></th>
                    <th style='width: 22%;'><div>操作</div></th>
                </tr>
        </thead>
        <tbody>
        {{for list}}
            <tr>
                <td>
                    <div>{{:hourseNumber}}</div>
                </td>
                <td>
                    <div>{{:areaCode}}</div>
                </td>
                <td>
                    <div>{{:typeName}}</div>
                </td>
                <td>
                    <div>{{:ownerName}}</div>
                </td>
                <td>
                    <div>{{dateTime:hDate}}</div>
                </td>
                <td>
                    <div>{{:hourseArea}}</div>
                </td>
                <td>
                    <div>{{:hourseDesc}}</div>
                </td>
                <td>
                    <div class="">
                        <a href="${ctx}/hourse/tolook?hId={{:id}}">
                            <input type="button" class="lookbtn gray_btn mr10" value="查看">
                        </a>
                        <a href="${ctx}/hourse/toedit?hId={{:id}}">
                            <input type="button" class="editbtn gray_btn mr10" value="编辑">
                        </a>
                        <input type="button" class="deleteHourse gray_btn mr10" data-sid="{{:id}}" value="删除">
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
        <div class="b_title">房子管理</div>
        <div class="hr">
            <hr>
        </div>
        <div class="pdtrl20">
            <a href="${ctx}/hourse/toaddHourse">
                <input type="button" class="blue_btn" value="房子信息录入">
            </a>
            <a href="" class="none">
                <input type="button" class="gray_btn ml20" value="功能介绍">
            </a>
        </div>
        <div class="select-search pdtrl20">
            <form action="">
                <div>
                    <input id="hNumber" type="text" class="inpW ml20" placeholder="房间号">
                    <input id="hNumberArea" type="text" class="inpW ml20" placeholder="地区号">
                    <input id="searchBtn" type="button" class="blue_btn ml20" value="查询">
                    <input id="resetBtn" type="button" class="blue_btn ml20" value="重置">
                </div>
            </form>
        </div>
        <div class="pdtrl20">
            <div class="scroll-table">
                <table id="hourseTable" class="seller-lib sell-type vip-type tr-bg" cellpadding="0" cellspacing="0">
                </table>
            </div>
            <div class="page" id="tablPage">
                <div id="pagetotal" class="m-pagination"></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/js/mod/hourse/hourselist.js"></script>
<%--<script type="text/javascript" src="${ctx}/static/js/lib/ss_helper.js"></script>--%>
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