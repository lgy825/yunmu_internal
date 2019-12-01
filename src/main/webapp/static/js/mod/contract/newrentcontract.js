$(function(){
    var timeContractStart= $("#timeContractStart").datetimepicker({
        format: 'Y-m-d',
        // minDate: 0,
        // onChangeDateTime: function (curDate) {
        //     var curDateTime = curDate.sformat("yyyy-MM-dd");
        //     $("#timeEpick").datetimepicker({
        //         minDate: curDateTime ? curDateTime : false
        //     });
        // },
        timepicker: false
    });
    var timeContractEnd=$("#timeContractEnd").datetimepicker({
        format: 'Y-m-d',
        // minDate: 0,
        // onChangeDateTime: function (curDate) {
        //     var curDateTime = curDate.sformat("yyyy-MM-dd");
        //     $("#timeEpick").datetimepicker({
        //         minDate: curDateTime ? curDateTime : false
        //     });
        // },
        timepicker: false
    });
    var timeContract=$("#timeContract").datetimepicker({
        format: 'Y-m-d',
        // minDate: 0,
        // onChangeDateTime: function (curDate) {
        //     var curDateTime = curDate.sformat("yyyy-MM-dd");
        //     $("#timeSpick").datetimepicker({
        //         maxDate: curDateTime ? curDateTime : false
        //     });
        // },
        timepicker: false
    });
    var timeRentFreeStart=$("#timeRentFreeStart").datetimepicker({
        format: 'Y-m-d',
        // minDate: 0,
        // onChangeDateTime: function (curDate) {
        //     var curDateTime = curDate.sformat("yyyy-MM-dd");
        //     $("#timeSpick").datetimepicker({
        //         maxDate: curDateTime ? curDateTime : false
        //     });
        // },
        timepicker: false
    });
    var timeRentFreeEnd=$("#timeRentFreeEnd").datetimepicker({
        format: 'Y-m-d',
        // minDate: 0,
        // onChangeDateTime: function (curDate) {
        //     var curDateTime = curDate.sformat("yyyy-MM-dd");
        //     $("#timeSpick").datetimepicker({
        //         maxDate: curDateTime ? curDateTime : false
        //     });
        // },
        timepicker: false
    });


    $("#saveBtn").click(function () {

        var contractName = $.trim($("#contractName").val());
        if (!contractName) {
            layer.msg("请输入合同名称");
            return;
        }

        // var contractType = $.trim($("#contractType").val());
        // if (!contractType) {
        //     layer.msg("请输入合同类型");
        //     return;
        // }

        var contractCode = $.trim($("#contractCode").val());
        if (!contractCode) {
            layer.msg("请输入合同编码");
            return;
        }

        var contractIdentity = $.trim($("#contractIdentity").val());
        if (!contractIdentity) {
            layer.msg("请选择我方身份");
            return;
        }



        var contractStartTime = timeContractStart.val();
        var contractEndTime = timeContractEnd.val();
        if (contractStartTime.length < 1 || contractEndTime.length < 1) {
            layer.msg("请选择起止时间");
            return;
        } else {
            if (Date.parse(contractStartTime) > Date.parse(contractEndTime)) {
                layer.msg("结束日期不能早于开始日期");
                return;
            }
        }

        var operativeWay = $.trim($("#operativeWay").val());
        if (!operativeWay) {
            layer.msg("请输入运营方式");
            return;
        }

        var contractTime = timeContract.val();
        if (contractTime.length < 1) {
            layer.msg("请选择签约时间");
            return;
        }

        var rentFreeCount = $.trim($("#rentFreeCount").val());

        if (!rentFreeCount) {
            layer.msg("请输入免租期");
            return;
        }else{
            if(!ValidUtils.validNum(rentFreeCount,5)){
                layer.msg("免租期只能输入为数字");
                return;
            }
        }

        var rentFreeStartTime = timeRentFreeStart.val();
        var rentFreeEndTime = timeRentFreeStart.val();
        if (rentFreeStartTime.length < 1 || rentFreeEndTime.length < 1) {
            layer.msg("请选择起止时间");
            return;
        } else {
            if (Date.parse(rentFreeStartTime) > Date.parse(rentFreeEndTime)) {
                layer.msg("结束日期不能早于开始日期");
                return;
            }
        }

        var rentAmount = $.trim($("#rentAmount").val());
        if (!rentAmount) {
            layer.msg("请输入租金");
            return;
        }else if (!ValidUtils.validMoney(rentAmount)) {
            layer.msg("租金不能包含特殊字符，保留一位有效数字");
            return;
        }

        var rentIncreaseWay = $.trim($("#rentIncreaseWay").val());
        if (!rentIncreaseWay) {
            layer.msg("请输入递增方式");
            return;
        }

        var contractExcute = $.trim($("#contractExcute").val());
        if (!contractExcute) {
            layer.msg("请输入甲方信息");
            return;
        }
        var excuteIDcard = $.trim($("#excuteIDcard").val());
        if (!excuteIDcard) {
            layer.msg("请输入甲方身份证号(统一社会信用代码)");
            return;
        }

        var excuteProxy = $.trim($("#excuteProxy").val());
        if (!excuteProxy) {
            layer.msg("请输入甲方代理人姓名");
            return;
        }

        var excuteTel = $.trim($("#excuteTel").val());
        if (!excuteTel) {
            layer.msg("请输入甲方联系方式");
            return;
        }

        var excuteAddr = $.trim($("#excuteAddr").val());
        if (!excuteAddr) {
            layer.msg("请输入甲方地址");
            return;
        }

        var excuteEmail = $.trim($("#excuteEmail").val());
        if (!excuteEmail) {
            layer.msg("请输入甲方邮箱");
            return;
        }else if (!ValidUtils.validEmail(orderActAmount)) {
            layer.msg("邮箱输入不合法");
            return;
        }

        var excuteOpeningBank = $.trim($("#excuteOpeningBank").val());
        if (!excuteOpeningBank) {
            layer.msg("请输入甲方银行卡开户行");
            return;
        }

        var banksName = $.trim($("#banksName").val());
        if (!banksName) {
            layer.msg("请输入甲方银行卡户名");
            return;
        }

        var bankNumber = $.trim($("#bankNumber").val());
        if (!bankNumber) {
            layer.msg("请输入甲方银行卡账号");
            return;
        }else if (!ValidUtils.validNum(orderActAmount)) {
            layer.msg("开户行只能是数字，不能包含特殊字符");
            return;
        }

        var contractEntrust = $.trim($("#contractEntrust").val());
        if (!contractEntrust) {
            layer.msg("请输入乙方名称");
            return;
        }

        var entrustIDcard = $.trim($("#entrustIDcard").val());
        if (!entrustIDcard) {
            layer.msg("请输入乙方身份证号(统一社会信用代码)");
            return;
        }

        var entrustProxy = $.trim($("#entrustProxy").val());
        if (!entrustProxy) {
            layer.msg("请输入乙方代理人姓名");
            return;
        }


        var entrustTel = $.trim($("#entrustTel").val());
        // if (!entrustProxy) {
        //     layer.msg("请输入乙方代理人姓名");
        //     return;
        // }
        var hourseAddr = $.trim($("#hourseAddr").val());
        if (!hourseAddr) {
            layer.msg("请输入房屋地址");
            return;
        }

        var hourseArea = $.trim($("#hourseArea").val());
        if (!hourseArea) {
            layer.msg("请输入房屋面积");
            return;
        }

        var hourseUses = $.trim($("#hourseUses").val());
        // if (!hourseUses) {
        //     layer.msg("请输入房屋用途");
        //     return;
        // }



        $.ajax({
            url: ctx + "contract/addRentContract",
            type: "POST",
            cache: false,
            async: false,
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify({
                //projectId:projectId,
                contractName:contractName,
                //contractType:contractType,
                contractCode:contractCode,
                contractIdentity:contractIdentity,
                contractStartTime:contractStartTime+" 00:00:00",
                contractEndTime:contractEndTime+" 23:59:59",
                operativeWay:operativeWay,
                contractTime:contractTime+" 00:00:00",
                rentFreeCount:rentFreeCount,
                rentFreeStartTime:rentFreeStartTime+" 00:00:00",
                rentFreeEndTime:rentFreeEndTime+" 23:59:59",
                rentAmount:rentAmount,
                rentIncreaseWay:rentIncreaseWay,
                contractExcute:contractExcute,
                excuteIDcard:excuteIDcard,
                excuteProxy:excuteProxy,
                excuteTel:excuteTel,
                excuteAddr:excuteAddr,
                excuteEmail:excuteEmail,
                excuteOpeningBank:excuteOpeningBank,
                banksName:banksName,
                bankNumber:bankNumber,
                contractEntrust:contractEntrust,
                entrustIDcard:entrustIDcard,
                entrustProxy:entrustProxy,
                entrustTel:entrustTel,
                hourseAddr:hourseAddr,
                hourseArea:hourseArea,
                hourseUses:hourseUses
            }),
            success: function (data) {
                if (data && data.resultCode === '0') {
                    layer.msg("保存成功");
                    location.href = ctx + "contrct/toRentContractlist";
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



});