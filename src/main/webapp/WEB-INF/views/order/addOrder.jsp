<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>创建订单</title>
    <%@ include file="/static/commons/common.jspf" %>
    <link rel="stylesheet" href="${ctx}/static/css/jquery.datetimepicker.css">
</head>
<body>
<div class="p20">
    <div class="bgc-ff">
        <!-- 订单管理 -->
        <div class="b_title">创建订单</div>
        <div class="hr">
            <hr>
        </div>
        <div class="p20 relative clearfix">
            <div class="fl">
                <!-- 必填选项 -->
                <div class="mt12 clearfix">
                    <div class="align-r mr8 mt6">所属项目</div>
                    <select id="projectSel" class="select ml16 wid-238">
                    </select>
                    <span class="color-lred ml8 mt6">* 选择所属的项目，必选</span>
                </div>
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
                    <span class="color-lred ml8 mt6">* 请选择订单来源*</span>
                </div>
                <div class="mt12 clearfix">
                    <div class="align-r mr8 mt6">房屋类型</div>
                    <select id="typeCodeSel" class="select ml16 wid-238">
                    </select>
                    <span class="color-lred ml8 mt6">* 选择房子的类型*</span>
                </div>
                <!-- 必填选项 -->
                <div class="mt12 clearfix">
                    <div class="align-r mr8 mt6">房间号</div>
                    <select id="hourseSel" class="select ml16 wid-238">
                    </select>
                    <span class="color-lred ml8 mt6">* 请选择所属房间*</span>
                </div>
                <div class="mt12">
                    <div class="align-r">房费</div>
                    <input id="oRecAmount" maxlength="5" type="text" class="inpW set-inpwid ml8" placeholder="请输入房费">
                    <span class="color-lred ml8 mt6">* 不超过5个字，不能出现其他特殊字符</span>
                </div>
                <!-- 选择卖品 -->
                <div class="mt20">
                    <div class="sell_box">
                        <div class="align-r mr8 relative">选择支出<i class="whats"></i>
                            <p class="modify-what">设设置您活动生效的支出，可选择全部，可指定支出生效分别设置</p></div>
                        <span sellradio="0" class="allSell checkBtn radio mr20 on" data-i="0">全部支出</span>
                        <span sellradio="1" class="allSell checkBtn radio" data-i="1">指定支出</span>
                    </div>
                    <div class="pl88 mt10 none p_sellList">
                        <input type="text" id="sellSearch" class="inpW inpWid1 mr8" placeholder="请输入支出名称"/>
                        <span class="color-lred">* 请至少选择一种支出 </span>
                        <div class="p_sellList">
                            <div class="bore1 p_movBox p_movBox2">
                                <table class="oInfo_table">
                                    <thead>
                                    <tr>
                                        <th>
                                            <div>支出名称</div>
                                        </th>
                                        <th>
                                            <div>支出金额</div>
                                        </th>
                                        <th>
                                            <div>支出类型</div>
                                        </th>
                                        <th>
                                            <div>支出描述</div>
                                        </th>
                                        <th>
                                            <div>选择</div>
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody id="sellListDiv" class="modify-tbody">

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="mt20">
                    <div class="align-r mr8">订单时间</div>
                    <input type="text" class="inpW inpWid2 timer" id="timeSpick" placeholder="开始日期">
                    <span class="zhi">至</span>
                    <input type="text" class="inpW inpWid2 timer" id="timeEpick" placeholder="结束日期">
                    <span class="color-lred ml8">* 结束日期不能早于开始日期</span>
                </div>
                <div class="mt30 ml170">
                    <input type="button" class="blue_btn blue_btn30 " id="saveOrder"  value="完成创建">
                    <a href="${ctx}/order/toOrderlist">
                        <input type="button" class="gray_btn gray-btn30 ml20" value="返回列表">
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<input type="hidden" value="${orderId}" id="orderId"/>
</div>
</body>
<script type="text/javascript" src="${ctx}/static/js/lib/jquery.datetimepicker.js"></script>
<script src="${ctx}/static/js/mod/order/addorder.js"></script>
<script type="text/javascript" src="${ctx}/static/js/commons/common.js"></script>

</html>
