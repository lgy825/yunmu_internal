var hourseTypeMap={};
var projectId="";
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

    loadPay();
    loadOrderSource();
    loadHourseAndType();
    loadPayWay();
    loadProject();

    // 加载数据 -------------
    if ($("#orderId").val()) {
        var canedit = setInterval(function () {
            $.ajax({
                url: ctx + "order/get",
                type: "GET",
                cache: false,
                async: false,
                dataType: 'json',
                data: {
                    id: $("#orderId").val(),
                },
                success: function (data) {
                    if (data && data.resultCode === '0') {
                        clearInterval(canedit);
                        su = data.resultData;
                        $(_.filter(su.payExts)).each(function (idx, elem) {
                            console.info(".paycheck[data-payid=" + elem.payId );
                            $(".paycheck[data-payid='" + elem.payId + "']").click();
                        });
                        $("#orderActAmount").val(su.orderRecAmount);
                        timeSpick.val(su.orderStartDate.split(" ")[0]);
                        timeEpick.val(su.orderEndTime.split(" ")[0]);

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
        }, 50);

    }


    $("#saveBtn").click(function () {

        var cinemaChooseWay = $(".cinemar.on").attr("cinemaradio");
        var isChoose;
        if (cinemaChooseWay == 1) {
            isChoose=1;
        }else{
            isChoose=2;
            var $vous = $("#payTbody").find(".paycheck.cur");
            if($vous.length < 1) {
                layer.msg("请选择支出");
                return;
            }
            var relates = [];
            $($vous).each(function (idx, elem) {
                relates.push({
                    payId: $(elem).data("payid"),
                    amount:$vous.parent().parent().parent().find(".params").val()
                });

            });
        }

        if ($("#projectSel").val() == -1) {
            layer.msg("请选择房子所属的项目");
            return;
        }

        if ($("#paySel").val() == -1) {
            layer.msg("请选择支付方式");
            return;
        }

        if ($("#sourceSel").val() == -1) {
            layer.msg("请选择订单来源");
            return;
        }

        if ($("#hourseSel").val() == -1) {
            layer.msg("请选择房间");
            return;
        }

        var orderActAmount = $.trim($("#orderActAmount").val());
        if (!orderActAmount) {
            layer.msg("请输入房费");
            return;
        } else if (!ValidUtils.validMoney(orderActAmount)) {
            layer.msg("房费不能包含特殊字符，保留一位有效数字");
            return;
        }

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

        $.ajax({
            url: ctx + "order/addOrder",
            type: "POST",
            cache: false,
            async: false,
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify({
                id:$.trim($("#orderId").val()),
                orderRecAmount:orderActAmount,
                paramVos:relates,
                projectId: $.trim($("#projectSel").val()),
                orderWay: $.trim($("#paySel").val()),
                orderSource: $.trim($("#sourceSel").val()),
                hourseCodes: $.isArray($("#hourseSel").val())  ? $("#hourseSel").val().join(",") : ($("#hourseSel").val() == -1 ? "" : $("#hourseSel").val()),
                orderStartDate:startTime+ " 00:00:00",
                orderEndTime:endTime+ " 23:59:59",
                orderActAmount:orderActAmount,
                isChoose:isChoose
            }),
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
                        $("#paySel").append("<option value='" + item.id + "'>" + item.pName + "</option>");
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
                            $("#sourceSel").append("<option value='" + item.id + "'>" + item.name + "</option>");
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

                    if ($("#orderId").val()) {
                            $.ajax({
                                url: ctx + "order/get",
                                type: "GET",
                                cache: false,
                                async: false,
                                dataType: 'json',
                                data: {
                                    id: $("#orderId").val(),
                                },
                                success: function (data) {
                                    if (data && data.resultCode === '0') {
                                        su = data.resultData;
                                        $("#projectSel").val(su.projectId);
                                        $("#typeCodeSel").val(su.typeCode);
                                        $("#hourseSel").val(su.hourseCode);
                                        $("#paySel").val(su.orderWay);
                                        $("#sourceSel").val(su.orderSource);
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


    $("#payTbody").on('click', '.check', function () {
        var _this = $(this);
        var ccode = _this.attr("ccode");
        _this.toggleClass('cur');

    });
    $("#paySearch").on('keyup',function () {
        loadPay();
    });

    function loadPay() {
        var paySearch=$.trim($("#paySearch").val());
        $.ajax({
            url:ctx+"pay/getpage",
            type:"GET",
            cache: false,
            dataType: 'json',
            contentType: "application/json",
            data:{
                pageIndex: 1,
                pageSize: 99999,
                payName:paySearch
            },
            success: function (result) {
                if (result && result.resultCode === '0') {
                    $("#payTbody").empty();
                    if(result.resultData && result.resultData.list) {
                        $(result.resultData.list).each(function (index, item) {
                            $("#payTbody").append(
                                '<tr>' +
                                '<td><div><span class="paycheck checkBtn check w14" data-payid="'+item.payId+'"></span></div></td>' +
                                '<td><div>' + (_.isUndefined(item.payName) ? '' : item.payName) + '</div></td>' +
                                '<td><div><span class="relative"><span class="rename-inp inline-block">' + (item.payAmount ? item.payAmount : 0) + '</span>'
                                + '<i class="rename"></i><input  type="text"  value="'+item.payAmount+'" class="params rename-inp none"></span></div></td>' +
                                '<td><div title="'+item.payDesc+'">'+item.payDesc+'</div></td>' +
                                ' </tr>'
                            );
                        });
                    }
                } else {
                    if (result.resultDesc) {
                        layer.msg(result.resultDesc);
                    } else {
                        layer.msg('没有查询支出信息!');
                    }
                }
            },
            error: function () {
                layer.msg('查询支出失败!');
            }

        })
    }

    $('.oInfo_table').on('click', '.rename', function () {
        $(this).prev().hide();
        $(this).next().show().addClass('border-el');
    });


    $('.cinema_box').on('click', '.radio', function () {
        var _this = $(this),
            $data_i = _this.attr('data-i'),
            $p_selCimema = $('#p_selCimemaPan');

        if ($data_i == 1) {
            $p_selCimema.removeClass('none');
        } else {
            // 全部
            $p_selCimema.addClass('none');
            //clearSearch();
        }
        $(".cinemar").removeClass("on");
        $(this).addClass("on");
        clearSearch();
    });

    function clearSearch() {
        $("#paySearch").val("");
    }


    // $('.oInfo_table').on('click', 'tr', function () {
    //     $(this).find(".params").addClass("on");
    // });


});



