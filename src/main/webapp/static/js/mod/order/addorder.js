var hourseTypeMap={}
$(function () {

    //出事化日其插件
    var timeSpick = $("#timeSpick").datetimepicker({
        format: 'Y-m-d',
        minDate: 0,
        onChangeDateTime: function (curDate) {
            var curDateTime = curDate.sformat("yyyy-MM-dd");
            $("#timeEpick").datetimepicker({
                minDate: curDateTime ? curDateTime : false
            });
        },
        timepicker: false
    });
    var timeEpick = $("#timeEpick").datetimepicker({
        format: 'Y-m-d',
        minDate: 0,
        onChangeDateTime: function (curDate) {
            var curDateTime = curDate.sformat("yyyy-MM-dd");
            $("#timeSpick").datetimepicker({
                maxDate: curDateTime ? curDateTime : false
            });
        },
        timepicker: false
    });

    loadOrderSource();
    loadPayWay();
    loadProject();
    loadHourseAndType();

    $("#saveBtn").click(function () {

        if($("#paySel").val() == -1) {
            layer.msg("请选择订单支付方式");
            return;
        }
        var oWay = $("#paySel").val();

        if($("#sourceSel").val() == -1) {
            layer.msg("请选择订单来源");
            return;
        }
        var oSource = $("#sourceSel").val();

        if($("#hourseSel").val() == -1) {
            layer.msg("请选择所属房间");
            return;
        }
        var hourseSel = $("#hourseSel").val();
        var startTime = timeSpick.val();
        var endTime = timeEpick.val();
        if (startTime.length < 1 || endTime.length < 1) {
            layer.msg("请选择起止时间");
            return;
        } else {
            if (Date.parse(startTime) > Date.parse(endTime)) {
                layer.msg("结束日期不能早于开始日期");
                return;
            }
        }
        var oRecAmount = $.trim($("#oRecAmount").val());
        if(oRecAmount==null){
            layer.msg("输入不能为空");
        }
        var tr= /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
        if(!tr.test(oRecAmount)){
            layer.msg('金额输入有误请重新输入 !');
            return;
        }
        $.ajax({
            url: ctx + "order/addOrder",
            type: "POST",
            cache: false,
            dataType: 'json',
            data: {
                oId:$.trim($("#oId").val()),
                oWay: oWay,
                oSource: oSource,
                hId: hourseSel,
                oStartDate:startTime+ " 00:00:00",
                oEndDate:endTime+ " 23:59:59",
                oRecAmount:oRecAmount
            },
            success: function (data) {
                if (data && data.resultCode === '0') {
                    layer.msg("保存成功");
                    location.href = ctx + "order/toOrderlist";
                } else {
                    if (data.resultDesc) {
                        layer.msg(data.resultDesc);
                    } else {
                        layer.msg('保存失败 !');
                    }
                }
            },
            error: function () {
                layer.msg('保存失败 !');
            },
            beforeSend: function () {
                layer.load(1, {shade:[0.3]})
            }
        });
    });

    function loadPayWay() {
        $.ajax({
            url: ctx + "order/getPayWayAll",
            type: "GET",
            cache: false,
            async: false,
            dataType: 'json',
            success: function (data) {
                if (data && data.resultCode === '0') {
                    //
                    $("#paySel").select2({placeholder: '*选择支付方式*'});
                    $("#paySel").append("<option value='-1'>*选择支付方式*</option>");
                    $(data.resultData).each(function (idx, item) {
                        $("#paySel").append("<option value='" + item.pId + "'>" + item.pName + "</option>");
                    });
                    // 加载数据 -------------
                    if ($("#oId").val()) {
                        $.ajax({
                            url: ctx + "order/get",
                            type: "GET",
                            cache: false,
                            async: false,
                            dataType: 'json',
                            data: {
                                oId: $("#oId").val(),
                            },
                            success: function (data) {
                                if (data && data.resultCode === '0') {
                                    su = data.resultData;
                                    //alert(su.oWay);
                                    $("#sourceSel").val(su.oSource);
                                    $("#paySel").val(su.oWay);
                                    $("#hourseSel").val(su.hId);
                                    $("#oRecAmount").val(su.oRecAmount);
                                    timeSpick.val(su.oStartDate.split(" ")[0]);
                                    timeEpick.val(su.oEndDate.split(" ")[0]);
                                } else {
                                    if (data.resultDesc) {
                                        layer.msg(data.resultDesc);
                                    } else {
                                        layer.msg('查询失败 !');
                                    }
                                }
                            },
                            error: function () {
                                layer.msg('查询失败 !');
                            }
                        });
                    }
                } else {
                    if (data.resultDesc) {
                        layer.msg(data.resultDesc);
                    } else {
                        layer.msg('查询失败 !');
                    }
                }
            },
            error: function () {
                layer.msg('查询失败 !');
            }
        });
    }

    function loadOrderSource() {
        $.ajax({
            url: ctx + "order/getOrdeSourceAll",
            type: "GET",
            cache: false,
            async: false,
            dataType: 'json',
            success: function (data) {
                if (data && data.resultCode === '0') {
                    //
                    $("#sourceSel").select2({placeholder: '*选择订单来源*'});
                    $("#sourceSel").append("<option value='-1'>*选择订单来源*</option>");
                    $(data.resultData).each(function (idx, item) {
                        $("#sourceSel").append("<option value='" + item.sId + "'>" + item.sName + "</option>");
                    });

                } else {
                    if (data.resultDesc) {
                        layer.msg(data.resultDesc);
                    } else {
                        layer.msg('查询失败 !');
                    }
                }
            },
            error: function () {
                layer.msg('查询失败 !');
            }
        });
    }

    function loadProject() {
        $.ajax({
            url: ctx + "project/getpage",
            type: "GET",
            cache: false,
            async: false,
            dataType: 'json',
            data: {
                pageIndex: 1,
                pageSize: 99999
            },
            success: function (data) {
                if (data && data.resultCode === '0') {
                    // // 城市列表
                    $("#projectSel").select2({placeholder: '请选择所属项目'});
                    $("#projectSel").append("<option value='-1'>*所属项目*</option>");
                    $(data.resultData.list).each(function (idx, pro) {
                        $("#projectSel").append("<option value='" + pro.id + "'>" + pro.projectName + "</option>");
                    });
                }else {
                    if (data.resultDesc) {
                        layer.msg(data.resultDesc);
                    } else {
                        layer.msg('查询失败 !');
                    }
                }
            },
            error: function () {
                layer.msg('查询失败 !');
            }
        });
    }


    //加载房屋类型和房间号
    function loadHourseAndType() {
        $.ajax({
            url: ctx + "hourse/getHourseAndType",
            type: "GET",
            cache: false,
            async: false,
            dataType: 'json',
            success: function (data) {

                if (data && data.resultCode === '0') {
                    hourseTypeMap = data.resultData.hourseTypeMap;
                    var hourseType = data.resultData.hourseTypeList;
                    // 城市列表
                    $("#typeCodeSel").select2({placeholder: '请房间类型'});
                    $("#typeCodeSel").append("<option value='-1'>*请房间类型*</option>");
                    $(hourseType).each(function (idx, type) {
                        $("#typeCodeSel").append("<option value='" + type.id + "'>" +type.typeName + "</option>");
                    });

                    $("#typeCodeSel").change(function () {
                        $("#hourseSel").select2({placeholder: '请选择房间,可多选', multiple: true});
                        $("#hourseSel").empty();
                        if($("#typeCodeSel").val() == -1) {
                            $(_.values(hourseTypeMap)).each(function (idx, hourses) {
                                $(hourses).each(function (idxtmp, hourse) {
                                    $("#hourseSel").append("<option value='" + hourse.id + "'>" + hourse.hourseNumber + "</option>");
                                });
                            });
                        } else {
                            $(hourseTypeMap[$("#typeCodeSel").val()]).each(function (idx, hourse) {
                                $("#hourseSel").append("<option value='" + hourse.id + "'>" + hourse.hourseNumber + "</option>");
                            });
                        }
                    });
                    $("#typeCodeSel").trigger('change');

                } else {
                    if (data.resultDesc) {
                        layer.msg(data.resultDesc);
                    } else {
                        layer.msg('查询失败 !');
                    }
                }
            },
            error: function () {
                layer.msg('查询失败 !');
            }
        });
    }

});


$('.sell_box').on('click', '.radio', function () {
    //debugger;
    var _this = $(this),
        $data_i = _this.attr('data-i'),
        p_sellList = $('.p_sellList'),
        p_sellAll = $('.p_sellAll');
    _this.parent().find(".radio").removeClass("on");
    if ($data_i == 1) {
        p_sellList.removeClass('none');
        p_sellAll.addClass('none');
        _this.toggleClass('on');
        loadSell();
    } else {
        p_sellList.addClass('none');
        p_sellAll.removeClass('none');
        _this.toggleClass('on');
        //clearSearch();

    }
});

$('#sellListDiv').on('blur', '.discount', function () {
    debugger;
    var _this = $(this);
    var singePrice = _this.parent().parent().find(".singe_price").attr('sp');
    var discount = _this.parent().parent().find(".discount").val();

    if(!ValidUtils.validMoney(discount)){
        layer.msg("卖品立减金额不能为0元或者负数，小数点不能超过2位!");
        _this.parent().parent().find(".discount").val("0");
        _this.parent().parent().find(".discount_price").html(singePrice + "元");
        return;
    }

    singePrice = (Math.floor(singePrice * 100) - Math.floor(discount * 100)) / 100;

    if(singePrice<0){
        singePrice = 0;
    }

    _this.parent().parent().find(".discount_price").html(singePrice + "元");
    _this.parent().parent().find(".discount_price").attr('dsp',singePrice);
})

$('#sellListDiv').on('click', '.check', function () {
    //debugger;
    var _this = $(this)
    if (!_this.hasClass("cur")) {
        var stock = _this.attr("stock");
        var select_stock = $('.stock').text();
        select_stock = Number(select_stock) + Number(stock);
        $('.stock').text(select_stock);
        var discount = _this.parent().parent().find(".discount").val();
        var sellId=$(this).attr("sellId");
        var sellInfoCache ={};
        sellInfoCache.SellId=sellId;
        sellInfoCache.disCount=discount;
        sellInfoCache.stockNum =stock;
        sellArr.push(sellInfoCache)
        _this.toggleClass('cur');
    } else {
        var stock = _this.attr("stock");
        var select_stock = $('.stock').text();
        select_stock = Number(select_stock) - Number(stock);
        $('.stock').text(select_stock);
        var discount = _this.parent().parent().find(".discount").val();
        var sellId=$(this).attr("sellId");
        _.pullAllWith(sellArr,[{ SellId:sellId,disCount: discount,stockNum:stock}], _.isEqual);
        _this.toggleClass('cur');
    }
})

$("#sellSearch").on('keyup', function () {
    loadSell();
});
var eventInfo;
function loadSell() {
    var projectId=$("#projectSel").val();
    if (projectId == null || projectId == undefined || projectId == "") {
        layer.msg('请选择适用项目！');
        return;
    }

    var payName = $.trim($("#sellSearch").val());
    if(payName !=null && payName !='' ){
        eventInfo = null;
    }

    $('.stock').text("0");

    $.ajax({
        url: ctx + "sell_event/get_sell_list.do",
        type: "GET",
        cache: false,
        dataType: 'json',
        contentType: "application/json",
        data: {
            "sellName": sellName,
            "cinemaCode": cinemaCode
        },
        success: function (data) {
            var result = data;
            if (result && result.resultCode == '0') {
                var resultData = result.resultData.list;
                $(resultData).each(function (index, item) {
                    if(eventInfo!=null){

                    }
                })
            }else {
                if (result.resultDesc) {
                    layer.msg(result.resultDesc);
                } else {
                    layer.msg('没有查询到支出信息!');
                }
            }

        } , error: function () {
            //debugger;
            layer.msg('系统错误！');
        }
    })


}

