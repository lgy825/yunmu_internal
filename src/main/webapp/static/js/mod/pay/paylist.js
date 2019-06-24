$(function(){


    loadPage();
    $("#searchBtn").click(function () {
        loadPage();
    });

    $("#resetBtn").click(function () {
        $("#payName").val("");
    });

    $("#payTable").on("click", ".delete", function (){
        var sid = $(this).data("sid");
        layer.confirm('删除后将无法恢复，是否继续？', function () {
            $.ajax({
                url: ctx + "pay/delete",
                type: "GET",
                cache: false,
                // async: false,
                dataType: 'json',
                data: {hId: sid},
                success: function (data) {
                    if (data && data.resultCode === '0') {
                        layer.msg('删除成功 !');
                        loadPage();
                    } else {
                        if (data.resultDesc) {
                            layer.msg(data.resultDesc);
                        } else {
                            layer.msg('删除失败 !');
                        }
                    }
                },
                error: function () {
                    layer.msg('删除失败 !');
                }
            });
        });
    });

    $("#payNames")
        .focus(function () {
            $(this).val($.trim($(this).val()));
            $(this).removeClass("inputBg");
            $(this).siblings('.color-lred').removeClass("none");
            if($(this).val().length > 10) {
                $(this).siblings('.color-lred').text("* 名称输入过长，不超过10字");
            } else {
                $(this).siblings('.color-lred').text("* 名称输入过长，不超过10字");
            }
        })
        .blur(function () {
            $(this).val($.trim($(this).val()));
            if ($(this).val() &&
                $(this).val().length > 0 &&
                $(this).val().length <= 10) {
                $(this).addClass("inputBg");
                $(this).siblings('.color-lred').addClass("none");
            } else {
                $(this).removeClass("inputBg");
                if($(this).val().length > 0) {
                    $(this).siblings('.color-lred').text("* 名称输入过长，不超过10字");
                } else {
                    $(this).siblings('.color-lred').text("* 名称输入过长，不超过10字");
                }
                $(this).siblings('.color-lred').removeClass("none");
            }
        });

    $("#payAmount")
        .focus(function () {
            $(this).val($.trim($(this).val()));
            $(this).removeClass("inputBg");
            $(this).siblings('.color-lred').removeClass("none");
            if(!ValidUtils.validMoney($(this).val())) {
                $(this).siblings('.color-lred').text("* 金额输入有误，请重新输入");
            }
        })
        .blur(function () {
            $(this).val($.trim($(this).val()));
            if ($(this).val() &&
                $(this).val().length > 0 &&
                $(this).val().length <= 5) {
                $(this).addClass("inputBg");
                $(this).siblings('.color-lred').addClass("none");
            } else {
                $(this).removeClass("inputBg");
                if(!ValidUtils.validMoney($(this).val())) {
                    $(this).siblings('.color-lred').text("* 金额输入有误，请重新输入");
                }
                $(this).siblings('.color-lred').removeClass("none");
            }
        });
    
    


});

function loadPage() {
    if($("#pagetotal").pagination()) {
        $("#pagetotal").pagination('destroy');
    }
    $("#pagetotal").pagination({
        pageSize: 10,
        pageElementSort: ['$info', '$page', '$size', '$jump'],
        showInfo: true,
        infoFormat: '共 {total} 数据',
        showJump: true,
        jumpBtnText: '跳转',
        noInfoText: '无数据',
        showPageSizes: true,
        pageSizeItems: [10, 30, 50],
        firstBtnText: '首页',
        lastBtnText: '尾页',
        prevBtnText: '上一页',
        nextBtnText: '下一页',
        remote: {
            url: ctx + 'pay/getpage',
            params:{
                typeName: $.trim($("#typeName").val())
            },
            success: function (data) {
                // data为ajax返回数据
                $("#payTable").empty().html($("#trTmpl").render(data.resultData));
            },
            totalName: 'total'
        }
    });
}
function savePay() {
    var payName = $.trim($("#payNames").val());
    if (!payName) {
        layer.msg("请输入支出名称");
        return;
    } else if (!ValidUtils.validText(typeName, 1, 10)) {
        layer.msg("名称不超过10个字母或数字，不能出现其他特殊字符");
        return;
    }
    var payAmount=$.trim($("#payAmount").val());
    if (!payAmount) {
        layer.msg("请输入支出金额");
        return;
    }
    var projectId = $.trim($("#projectSel").val());
    if ($("#projectSel").val() == -1) {
        layer.msg("请选择支出所属的项目");
        return;
    }
    var payType=$.trim($("#payType").val());
    if ($("#payType").val() == -1) {
        layer.msg("请选择支出所属的类型");
        return;
    }
    var payDesc=$.trim($("#payDesc").val());
    $.ajax({
        url: ctx + "pay/save",
        type: "POST",
        cache: false,
        dataType: 'json',
        data: {
            payName: payName,
            projectId:projectId,
            payAmount:payAmount,
            payType:payType,
            payDesc:payDesc
        },
        success: function (data) {
            if (data && data.resultCode === '0') {
                layer.msg("保存成功");
                $('.modality-layer').hide();
                loadPage();
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
}

function updatePay() {
    var payName = $.trim($("#payNames").val());
    if (!payName) {
        layer.msg("请输入支出名称");
        return;
    } else if (!ValidUtils.validText(typeName, 1, 10)) {
        layer.msg("名称不超过10个字母或数字，不能出现其他特殊字符");
        return;
    }
    var payAmount=$.trim($("#payAmount").val());
    if (!payAmount) {
        layer.msg("请输入支出金额");
        return;
    }
    var projectId=$("#projectSel").attr('code');
    var payType=$("#typeSel").attr('code');
    var id=$(".ids").val();
    var payDesc=$.trim($("#payDesc").val());
    $.ajax({
        url: ctx + "hourseType/save",
        type: "POST",
        cache: false,
        dataType: 'json',
        data: {
            id:id,
            payName: payName,
            projectId:projectId,
            payAmount:payAmount,
            payType:payType,
            payDesc:payDesc
        },
        success: function (data) {
            if (data && data.resultCode === '0') {
                layer.msg("保存成功");
                $('.modality-layer').hide();
                loadPage();
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
}
function deletePay(id) {
    layer.confirm('删除后将无法恢复，是否继续？', function () {
        $.ajax({
            url: ctx + "pay/delete",
            type: "GET",
            cache: false,
            // async: false,
            dataType: 'json',
            data: {id: id},
            success: function (data) {
                if (data && data.resultCode === '0') {
                    layer.msg('删除成功 !');
                    loadPage();
                } else {
                    if (data.resultDesc) {
                        layer.msg(data.resultDesc);
                    } else {
                        layer.msg('删除失败 !');
                    }
                }
            },
            error: function () {
                layer.msg('删除失败 !');
            }
        });
    });

}