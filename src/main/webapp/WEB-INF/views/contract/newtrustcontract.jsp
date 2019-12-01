<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="renderer" content="webkit">
    <title>新增委托合同</title>
    <%@include file="/static/commons/common.jspf" %>
    <link rel="stylesheet" href="${ctx}/static/css/jquery.datetimepicker.css">
</head>
<body>
<input type="hidden" id="cid" value="${cid}"/>
<div class="p20">
    <div class="bgc-ff min620">
        <h1 class="b_title">创建合同</h1>
        <div class="dashed">
            <hr>
        </div>
        <div class="p20">
            <div class="pl70 clearfix">
                <span class="b_label lab_wid1 fl">合同基本信息</span>
                <p>
                    <span class="<%--b_label--%> lab_wid1">合同名称:</span>
                    <input id="contractName" type="text" class="inpW"/>
                </p>
                <p class="mt12">
                    <span class="<%--b_label--%> lab_wid1">合同类型:</span>
                    <input id="contractType" type="text" class="inpW"/>
                </p>
                <p class="mt12">
                    <span class="<%--b_label--%> lab_wid1">合同编码:</span>
                    <input id="contractCode" type="text" class="inpW"/>
                </p>
                <p class="mt12">
                    <span class="<%--b_label--%> lab_wid1">我方身份:</span>
                    <input id="contractIdentity" type="text" class="inpW"/>
                </p>
                <p class="mt12">
                    <span class="<%--b_label--%> lab_wid1">合同开始时间:</span>
                    <input id="contractStartTime" type="text" class="inpW"/>
                    <span class="<%--b_label--%> lab_wid1">合同结束时间:</span>
                    <input id="contractEndTime" type="text" class="inpW"/>
                </p>
                <p class="mt12">
                    <span class="<%--b_label--%> lab_wid1">运营方式:</span>
                    <input id="operativeWay" type="text" class="inpW"/>
                </p>
                <p class="mt12">
                    <span class="<%--b_label--%> lab_wid1">签约时间:</span>
                    <input id="contractTime" type="text" class="inpW"/>
                </p>
            </div>
        </div>
        <div class="dashed">
            <hr>
        </div>
        <div class="p20">
            <div class="pl70 clearfix">
                <span class="b_label lab_wid1 fl">服务内容及服务费用</span>
                <p>
                    <span class="<%--b_label--%> lab_wid1">收费方式:</span>
                    <input id="rentFreeCount" type="text" class="inpW"/>
                </p>
                <p>
                    <span class="<%--b_label--%> lab_wid1">订单扣除项:</span>
                    <span class="alldaycheck checkBtn check">全选</span>
                    <div class="pl75 mb20">
                        <p class="mt12">
                            <span class="daycheck checkBtn check">布条洗涤</span>
                            <span class="daycheck checkBtn check">房屋保洁</span>
                            <span class="daycheck checkBtn check">客用品</span>
                            <span class="daycheck checkBtn check">房屋保险</span>
                            <span class="daycheck checkBtn check">房屋摄影</span>
                            <span class="daycheck checkBtn check">水电费</span>
                            <span class="daycheck checkBtn check">物业费</span>
                            <span class="daycheck checkBtn check">宽带费</span>
                        </p>
                        <p class="mt12 color-graya8">
                            至少选择一项，如勾选布条洗涤
                        </p>
                    </div>
                </p>
            </div>
        </div>
        <div class="dashed">
            <hr>
        </div>
        <div class="p20">
            <div class="pl70 clearfix limitBox">
                <span class="b_label lab_wid1 fl">甲方基本信息</span>
                <div class="pl88 w764">
                    <div class="bore6 p20">
                        <p>
                            <span class="<%--b_label--%> lab_wid1">甲方(委托方):</span>
                            <input id="contractExcute" type="text" class="inpW"/>
                        </p>
                        <p class="mt12">
                            <span class="<%--b_label--%> lab_wid1">身份证号(统一社会信用代码):</span>
                            <input id="excuteIDcard" type="text" class="inpW"/>
                        </p>
                        <p class="mt12">
                            <span class="<%--b_label--%> lab_wid1">代理人:</span>
                            <input id="excuteProxy" type="text" class="inpW"/>
                        </p>
                        <p class="mt12">
                            <span class="<%--b_label--%> lab_wid1">联系电话:</span>
                            <input id="excuteTel" type="text" class="inpW"/>
                        </p>
                        <p class="mt12">
                            <span class="<%--b_label--%> lab_wid1">地址:</span>
                            <input id="excuteAddr" type="text" class="inpW"/>
                        </p>
                        <p class="mt12">
                            <span class="<%--b_label--%> lab_wid1">邮箱:</span>
                            <input id="excuteEmail" type="text" class="inpW"/>
                        </p>
                        <p class="mt12">
                            <span class="<%--b_label--%> lab_wid1">开户行:</span>
                            <input id="excuteOpeningBank" type="text" class="inpW"/>
                        </p>
                        <p class="mt12">
                            <span class="<%--b_label--%> lab_wid1">户名:</span>
                            <input id="banksName" type="text" class="inpW"/>
                        </p>
                        <p class="mt12">
                            <span class="<%--b_label--%> lab_wid1">账号:</span>
                            <input id="bankNumber" type="text" class="inpW"/>
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <div class="dashed">
            <hr>
        </div>
        <div class="p20">
            <div class="pl70 clearfix">
                <span class="b_label lab_wid1 fl">乙方基本信息</span>
                    <p>
                        <span class="<%--b_label--%> lab_wid1">乙方(受委托方):</span>
                        <input id="contractEntrust" type="text" class="inpW"/>
                    </p>
                    <p class="mt12">
                        <span class="<%--b_label--%> lab_wid1">身份证号(统一社会信用代码):</span>
                        <input id="entrustIDcard" type="text" class="inpW"/>
                    </p>
                    <p class="mt12">
                        <span class="<%--b_label--%> lab_wid1">代理人:</span>
                        <input id="entrustProxy" type="text" class="inpW"/>
                    </p>
                    <p class="mt12">
                        <span class="<%--b_label--%> lab_wid1">联系电话:</span>
                        <input id="entrustTel" type="text" class="inpW"/>
                    </p>
             </div>
         </div>
        <div class="dashed">
            <hr>
        </div>
        <div class="p20">
            <div class="pl70 clearfix">
                <span class="b_label lab_wid1 fl">房屋基本信息</span>
                <p>
                    <span class="<%--b_label--%> lab_wid1">房屋地址:</span>
                    <input id="hourseAddr" type="text" class="inpW"/>
                </p>
                <p class="mt12">
                    <span class="<%--b_label--%> lab_wid1">房屋面积(建筑面积):</span>
                    <input id="hourseArea" type="text" class="inpW"/>
                </p>
                <p class="mt12">
                    <span class="<%--b_label--%> lab_wid1">房屋用途:</span>
                    <input id="hourseUses" type="text" class="inpW"/>
                </p>
            </div>
        </div>
            <div class="tc mt30">
                <input id="nextBtn" type="button" class="blue_btn blue_btn30" value="完成，返回影票规则列表">
                <input id="returnBtn" type="button" class="gray_btn ml20 gray-btn30" value="返回影票规则列表">
                <input id="returnCardListBtn" type="button" class="gray_btn ml20 gray-btn30" value="返回会员卡列表">
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/js/lib/jquery.datetimepicker.js"></script>
<script src="${ctx}/static/js/mod/contract/newtructcontract.js"></script>
</body>
</html>