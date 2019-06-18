<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>订单管理</title>
    <%@include file="/static/commons/common.jspf" %>
    <link href="${ctx}/static/css/mricode.pagination.css" rel="stylesheet" />
    <script src="${ctx}/static/js/lib/jsrender.min.js"></script>
    <script src="${ctx}/static/js/lib/mricode.pagination.js"></script>
    <script src="${ctx}/static/js/lib/jsrender-converts.js"></script>
    <script id="trTmpl" type="text/x-jrender">
        <thead>
                <tr>
                    <th style='width: 10%;'><div>订单号</div></th>
                    <th style='width: 8%;'><div>房间信息</div></th>
                    <th style='width: 8%;'><div>开始时间</div></th>
                    <th style='width: 8%;'><div>结束时间</div></th>
                    <th style='width: 9%;'><div>订单来源</div></th>
                    <th style='width: 10%;'><div>实收金额(元)</div></th>
                    <th style='width: 8%;'><div>成本(元)</div></th>
                    <th style='width: 8%;'><div>订单状态</div></th>
                    <th style='width: 8%;'><div>支付方式</div></th>
                    <th style='width: 22%;'><div>操作</div></th>
                </tr>
        </thead>
        <tbody>
        {{for list}}
            <tr>
                <td>
                    <div>{{:oId}}</div>
                </td>
                <td>
                    <div>{{:hNumber}}</div>
                </td>
                <td>
                    <div>
                           {{dateTime:oStartDate}}
                    </div>
                </td>
                <td>
                    <div>
                           {{dateTime:oEndDate}}
                    </div>
                </td>
                <td>
                    <div>{{:sourceWay}}</div>
                </td>
                <td>
                    <div>
                    {{if oRecAmount == null || oRecAmount=='' }}
                            0
                    {{else}}
                           {{:oRecAmount}}
                    {{/if}}
                    </div>
                </td>
                <td>
                    <div>
                    {{if oActAmount == null || oActAmount ==''}}
                            0
                    {{else}}
                           {{:oActAmount}}
                    {{/if}}
                    </div>
                </td>
                <td>
                    <div>
                        {{if oStatus == '0'}}
                            成功
                        {{else oStatus == '1'}}
                             失败
                        {{/if}}
                    </div>
                </td>
                <td>
                    <div>
                        {{:payWay}}
                    </div>
                </td>
                <td>
                    <div class="">
                        <a href="${ctx}/order/tolook?id={{:oId}}">
                            <input type="button" class="lookbtn gray_btn mr10" value="订单详情">
                        </a>
                        <a href="${ctx}/order/toedit?id={{:oId}}">
                            <input type="button" class="editbtn gray_btn mr10" value="编辑">
                        </a>
                        <input type="button" class="deleteOrder gray_btn mr10" data-sid="{{:oId}}" value="删除">
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
        <div class="b_title">订单管理</div>
        <div class="hr">
            <hr>
        </div>
        <div class="pdtrl20">
            <a href="${ctx}/order/toaddOrder">
                <input type="button" class="blue_btn" value="新建订单">
            </a>
            <a href="" class="none">
                <input type="button" class="gray_btn ml20" value="功能介绍">
            </a>
        </div>
        <div class="select-search pdtrl20">
            <form action="">
                <div>
                    <input id="oId" type="text" class="inpW ml20" placeholder="订单号">
                    <%--<input id="hId" type="text" class="inpW ml20" placeholder="房间号">--%>
                    <input id="searchBtn" type="button" class="blue_btn ml20" value="查询">
                    <input id="resetBtn" type="button" class="blue_btn ml20" value="重置">
                </div>
            </form>
        </div>
        <div class="pdtrl20">
            <div class="scroll-table">
                <table id="orderTable" class="seller-lib sell-type vip-type tr-bg" cellpadding="0" cellspacing="0">
                </table>
            </div>
            <div class="page" id="tablPage">
                <div id="pagetotal" class="m-pagination"></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/js/mod/order/orderlist.js"></script>
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