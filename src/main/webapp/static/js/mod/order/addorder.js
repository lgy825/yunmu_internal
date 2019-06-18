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

    loadOHourse();
    loadOrderSource();
    loadPayWay();
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

    function loadOHourse() {
        $.ajax({
            url: ctx + "hourse/getpage",
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
                    //
                    $("#hourseSel").select2({placeholder: '*选择房间*'});
                    $("#hourseSel").append("<option value='-1'>*选择房间*</option>");
                    $(data.resultData.list).each(function (idx, hourse) {
                        $("#hourseSel").append("<option value='" + hourse.hId + "'>" + hourse.hNumber + "</option>");
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



    

});
