<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="renderer" content="webkit">
    <title>创建会员卡</title>
    <%@include file="/commons/common.jspf" %>
</head>
<body>
<div class="p20">
    <input type="hidden" id="cardcode" value="${cardcode}" />
    <div class="bgc-ff full-white">
        <h1 class="b_title">合同录入</h1>
        <div class="hr">
            <hr>
        </div>
        <div class="p20">
            <div class="pl70">
                <div>
                    <span class="b_label lab_wid2">会员卡名称</span>
                    <input type="text" id="cardname" class="inpW inpWid1 mr8" maxlength="15" placeholder="卡名称">
                    <span class="color-lred none">* 请输入卡名称，不超过15个字</span>
                </div>
                <div class="mt12 cardFrebox">
                    <span class="b_label lab_wid2">会员卡类型</span>
                    <span class="cardtype checkBtn radio mr20" data-s="2">权益卡</span>
                    <span class="cardtype checkBtn radio on" data-s="1">储值卡</span>
                    <span class="color-lred">* 卡类型选择保存后不可再次修改</span>
                </div>
                <p class="cardtips color-lred mt12 pl80">卡储值金额=卡售价-工本费</p>
                <div class="mt12">
                    <div class="b_label lab_wid2">卡售价</div>
                    <input type="text" id="saleprice" class="inpW inpWid3 mr8" placeholder="大于0，小数点后2位"><span>元</span>
                    <span class="color-lred none">* 大于0，小数点后2位</span>
                </div>
                <div class="mt12 cardFree">
                    <div class="b_label lab_wid2">工本费</div>
                    <input type="text" id="cardfee" class="inpW inpWid3 mr8" placeholder="大于等于0，小数点后2位"><span>元</span>
                    <span class="color-lred none">* 大于等于0，小数点后2位。 且不可大于卡售价</span>
                </div>
                <div class="mt12 searchBox">
                    <span class="b_label lab_wid2 relative fl">使用规则说明</span>
                    <p class="pl80">
                        <textarea class="textareaW" id="rulecontent" maxlength="20" cols="50" rows="3" placeholder="例如：特殊场次结算价格低于影片最低限价时，需用户补足差额等……,最多20个字"></textarea>
                    </p>
                </div>
                <div class="mt12 searchBox">
                    <span class="b_label lab_wid2 relative fl">使用限制说明</span>
                    <p class="pl80">
                        <textarea class="textareaW" id="limitcontent" maxlength="20" cols="50" rows="3" placeholder="例如：特殊日期如周末等卡不可用,最多20个字"></textarea>
                    </p>
                </div>
            </div>
            <div class="tc mt30">
                <input id="savebtn" type="button" class="blue_btn blue_btn30" value="完成，下一步设置影票规则">
                <input id="onlysavebtn" type="button" class="blue_btn blue_btn30 ml20 keepSave" value="直接保存">
                <input id="returnbtn" type="button" class="gray_btn gray-btn30 ml20" value="返回会员卡列表">
            </div>
        </div>
    </div>
</div>
<div class="layerBox none">
    <div class="modality-layer"></div>
    <div class="baselayer-box layerwid1 p20">
        <div>
            <span class="color-gray8c">亲爱的用户：</span>
            <p class="line24 mt10">影票规则和卖品规则尚未创建，保存后可通过编辑再次完善规则，是否保存？</p>
        </div>
        <div class="mt30 tc">
            <input id="onlysavebtnconfirm" type="button" class="blue_btn blue_btn30 saveBtn" value="保存">
            <input id="keepedit" type="button" class="blue_btn blue_btn30 ml20" value="继续完善">
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/js/mod/card/newcard.js"></script>
<script>
    $(function () {
        // 白色背景铺满一屏
        fullScreen($('.full-white'), 42);
    });
</script>
</body>
</html>