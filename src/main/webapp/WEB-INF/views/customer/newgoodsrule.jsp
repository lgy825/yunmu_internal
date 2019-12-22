<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="renderer" content="webkit">
    <title>创建会员卡</title>
    <%@include file="/static/commons/common.jspf" %>
</head>
<body>
<input type="hidden" id="cid" value="${cid}"/>
<input type="hidden" id="rid" value="${ruleid}" />
<input type="hidden" id="pagetype" value="${pagetype}" />
<div class="p20">
    <div class="bgc-ff min620">
        <h1 class="b_title">创建会员卡</h1>
        <div class="hr">
            <hr>
        </div>
        <div class="p20">
            <div class="pl70">
                <p>
                    <div class="b_label lab_wid1 relative">规则名称
                        <i class="whats"></i>
                        <p class="modify-what">为了查询和维护方便，名称尽量包含主要规则属性或影院名称等</p>
                    </div>
                    <input id="rulename" type="text" class="inpW inpWid1 mr8" maxlength="15" placeholder="规则名称">
                    <span class="color-lred none">* 请输入规则名称，不超过15字 </span>
                </p>
                <p class="mt12">
                    <div class="b_label lab_wid1 relative">优先级
                        <i class="whats"></i>
                        <p class="modify-what">例如创建有影院、影厅、时间等相同条件的规则时，冲突时以优先级高的为准执行</p>
                    </div>
                    <select id="rulepriority" class="select mr8">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                    </select>
                    <span class="color-lred none">* 请选择优先级，数字越大优先级越高</span>
                </p>
            </div>
        </div>
        <div class="dashed">
            <hr>
        </div>
        <div class="p20">
            <div class="pl70">
                <div class="cinema_box">
                    <div class="b_label lab_wid1 relative">选择影院<i class="whats"></i>
                        <p class="modify-what">设置您规则生效的影院，可选择全部，可指定影院生效</p>
                    </div>
                    <span rctype="1" class="rulecinematype checkBtn radio mr20 on" data-i="0">全部影院</span>
                    <span rctype="2" class="rulecinematype checkBtn radio" data-i="1">指定影院</span>
                </div>
                <div class="pl88 mt10 none p_selCimema">
                    <input id="cinemanamesearch" type="text" class="inpW inpWid1 mr8" placeholder="请输入影院名称"/>
                    <span class="color-lred">* 请至少选择1家影院 </span>
                    <div id="cinemaDiv" class="bore1 p_movBox">
                        <%--<p><span class="rcinema checkBtn check">美嘉三里屯店</span></p>--%>
                        <%--<p><span class="rcinema checkBtn check">美嘉三里屯店</span></p>--%>
                        <%--<p><span class="rcinema checkBtn check">美嘉三里屯店</span></p>--%>
                        <%--<p><span class="rcinema checkBtn check">美嘉三里屯店</span></p>--%>
                        <%--<p><span class="rcinema checkBtn check">美嘉三里屯店</span></p>--%>
                        <%--<p><span class="rcinema checkBtn check">美嘉三里屯店</span></p>--%>
                        <%--<p><span class="rcinema checkBtn check">美嘉三里屯店</span></p>--%>
                        <%--<p><span class="rcinema checkBtn check">美嘉三里屯店</span></p>--%>
                        <%--<p><span class="rcinema checkBtn check">美嘉三里屯店</span></p>--%>
                    </div>
                </div>
            </div>
        </div>
        <div class="dashed">
            <hr>
        </div>
        <div class="p20">
            <div class="pl70 clearfix">
                <span class="b_label lab_wid1 fl">权益设置</span>
                <div class="pl88 w764">
                    <div class="p_selBox">
                        <div class="bore6 p_selingBox mb20 p20 relative">
                            <span class="close-set" style="display: none;"></span>
                            <span class="b_label lab_wid4">每周限天</span>
                            <span class="alldaycheck checkBtn check">全选</span>
                            <div class="pl75 mb20">
                                <p class="mt12">
                                    <span class="daycheck checkBtn check">周一</span>
                                    <span class="daycheck checkBtn check">周二</span>
                                    <span class="daycheck checkBtn check">周三</span>
                                    <span class="daycheck checkBtn check">周四</span>
                                    <span class="daycheck checkBtn check">周五</span>
                                    <span class="daycheck checkBtn check">周六</span>
                                    <span class="daycheck checkBtn check">周日</span>
                                </p>
                                <p class="mt12 color-graya8">
                                至少选择一天，如只勾选周一，则一周里只有周一可使用卡，其他时间不可使用
                                </p>
                            </div>
                            <span class="b_label lab_wid4 fl">每天限时</span>
                            <div class="pl75">
                               <div class="limitTime">
                                    <div class="limitclo mb10">
                                        <input type="text" class="timechooser timechooserstart select mr8"/>
                                        <span class="mr8">00分00秒开始，至</span>
                                        <input type="text" class="timechooser timechooserend select mr8"/>
                                        <span class="mr8">59分59秒结束</span>
                                        <span class="deltimechooser color-blue none">删除</span>
                                    </div>
                                </div>
                                <input type="button" class="blue_btn new-add addtime" value="添加时间段">
                                <p class="mt12 color-graya8">
                                促销时间段（00:00-23:59），每天只在开始时间和结束时间内可使用
                                </p> 
                            </div>
                            <div class="mt20">
                                <span class="b_label lab_wid4 fl">设置优惠</span>
                            </div>
                            <div class="pl75 sandtype">
                                <div class="mt12">
                                    <span class="pricetype checkBtn radio mr8 salTic on" data-p="1">不限卖品类型</span>
                                    <span class="txt-des">所有卖品类型执行统一的优惠规则，所有折扣和立减基于卖品自有电商价格</span>
                                </div>
                                <div class="ml20 mt20">
                                    <p class="sandprice">
                                        <span class="unlimitdisradio checkBtn radio mr20 on" data-p="1">折扣</span>
                                        <span class="unlimitdisradio checkBtn radio mr20" data-p="2">立减</span>
                                    </p>
                                    <div>
                                        <div class="ptypeBox ptypeBox4 p20 mt12">
                                            <p class="color-lred">
                                                卡权益售价=卖品自有电商价*折扣（活动场次除外）
                                            </p>
                                        </div>
                                        <div class="ptypeBox ptypeBox5 p20 mt12 none">
                                            <p class="color-lred">
                                                卡权益售价=卖品自有电商价-立减（活动场次除外）
                                            </p>
                                        </div>
                                    </div>
                                    <p class="mt12">
                                        <input type="text" class="disinput inpW inpWid2" />
                                        <span class="disdanwei mr8">折</span>
                                        <span class="color-graya8">折扣和立减金额必须为正数，折扣保留小数点后1位，立减保留2位</span>
                                    </p>
                                </div>
                                <div class="mt12">
                                    <span class="pricetype checkBtn radio mr8 salTic" data-p="2">按卖品类型优惠</span>
                                    <span class="txt-des">按卖品类型设置优惠规则，所有折扣和立减基于卖品自有电商价格</span>
                                </div>
                                <div class="dispanel ml20">
                                    <%--<div class="disbox mt12" data-ccode="">--%>
                                        <%--<span>美嘉三里屯</span>--%>
                                        <%--<p class="mt12">--%>
                                            <%--<span class="disradio checkBtn radio mr10" data-p="1">折扣</span>--%>
                                            <%--<span class="disradio checkBtn radio mr10" data-p="2">立减</span>--%>
                                        <%--</p>--%>
                                        <%--<p class="mt12">--%>
                                            <%--<span class="b_label lab_wid4" data-tid="">爆米花</span>--%>
                                            <%--<input type="text" data-tid="" class="disinputtmp inpW inpWid2" />--%>
                                            <%--<span class="disdanwei mr8">折</span>--%>
                                            <%--<span class="color-graya8">折扣和立减金额必须为正数，保留小数点后1位</span>--%>
                                        <%--</p>--%>
                                        <%--<p class="mt12">--%>
                                            <%--<span class="b_label lab_wid4">饮品类</span>--%>
                                            <%--<input type="text" class="inpW inpWid2" />--%>
                                            <%--<span class="mr8">折</span>--%>
                                            <%--<span class="color-graya8">折扣和立减金额必须为正数，保留小数点后1位</span>--%>
                                        <%--</p>--%>
                                    <%--</div>--%>
                                    <%--<div class="disbox mt12">--%>
                                        <%--<span>美嘉中关村</span>--%>
                                        <%--<p class="mt12">--%>
                                            <%--<span class="checkBtn radio mr10">折扣</span>--%>
                                            <%--<span class="checkBtn radio mr10">立减</span>--%>
                                        <%--</p>--%>
                                        <%--<p class="mt12">--%>
                                            <%--<span class="b_label lab_wid4">饮品类</span>--%>
                                            <%--<input type="text" class="inpW inpWid2" />--%>
                                            <%--<span class="mr8">折</span>--%>
                                            <%--<span class="color-graya8">折扣和立减金额必须为正数，保留小数点后1位</span>--%>
                                        <%--</p>--%>
                                        <%--<p class="mt12">--%>
                                            <%--<span class="b_label lab_wid4">爆米花</span>--%>
                                            <%--<input type="text" class="inpW inpWid2" />--%>
                                            <%--<span class="mr8">折</span>--%>
                                            <%--<span class="color-graya8">折扣和立减金额必须为正数，保留小数点后1位</span>--%>
                                        <%--</p>--%>
                                    <%--</div>--%>
                                </div>     
                            </div>
                        </div>
                    </div>
                    <div class="clearfix">
                        <input type="button" class="blue_btn new-add fr p_addBtn" value="添加会员卡权益">
                    </div>
                </div>
            </div>
            <div class="tc mt30">
                <input id="savebtn" type="button" class="blue_btn blue_btn30" value="完成，返回卖品规则列表">
                <input id="returnBtn" type="button" class="gray_btn ml20 gray-btn30" value="返回卖品规则列表">
                <input id="returnCardListBtn" type="button" class="gray_btn ml20 gray-btn30" value="返回会员卡列表">
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/js/lib/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="${ctx}/js/mod/card/newgoodsrule.js"></script>
<%--<script type="text/javascript">--%>
    <%--$(function(){--%>
    <%--//     //选择影院--%>
    <%--//     $('.cinema_box').on('click', '.radio', function () {--%>
    <%--//         var _this = $(this),--%>
    <%--//             index = _this.attr('data-i');--%>
    <%--//         if (index == 0) {--%>
    <%--//             _this.parent().next().addClass('none');--%>
    <%--//         }--%>
    <%--//         else {--%>
    <%--//             _this.parent().next().removeClass('none');--%>
    <%--//         }--%>
    <%--//     });--%>
    <%--//     //价格策略价--%>
    <%--//     $('.sandprice').on('click','.radio',function(){--%>
    <%--//         var _this = $(this),--%>
    <%--//             $datap = _this.attr('data-p');--%>
    <%--//             _this.parent().next().children('.ptypeBox').addClass('none');--%>
    <%--//             _this.parent().next().children('.ptypeBox').eq($datap).removeClass('none');--%>
    <%--//     });--%>
    <%--//     //设置优惠--%>
    <%--//     $('.sandtype').on('click','.salTic',function(){--%>
    <%--//         $(this).addClass('on');--%>
    <%--//         $(this).parent().siblings().find('.salTic').removeClass('on');--%>
    <%--//     })--%>
    <%--//     //追加时间--%>
    <%--//     $('.addtime').on('click',function(){--%>
    <%--//         var $limitclo = $(this).prev('.limitTime').find('.limitclo:last'),--%>
    <%--//             $limitTime =$(this).prev('.limitTime');--%>
    <%--//         $limitTime.append($limitclo.clone(true));--%>
    <%--//         $('.limitTime .limitclo:first').children('.color-blue').css('display','none')--%>
    <%--//         /*$('.limitTime').find('.limitclo').eq(0).children('.color-blue').css('display','none')*/;--%>
    <%--//     });--%>
    <%--//     //追加会员卡规则--%>
    <%--//     $('.p_addBtn').on('click',function(){--%>
    <%--//         $('.p_selBox').append($('.p_selingBox:last').clone(true));--%>
    <%--//     });--%>
    <%--// })--%>
<%--</script>--%>
</body>
</html>