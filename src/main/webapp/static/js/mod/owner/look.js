$(function () {

    // 加载数据 -------------
    if ($("#ownerId").val()) {
        $.ajax({
            url: ctx + "owner/get",
            type: "GET",
            cache: false,
            async: false,
            dataType: 'json',
            data: {
                id: $("#ownerId").val(),
            },
            success: function (data) {
                if (data && data.resultCode === '0') {
                    su = data.resultData;
                    $("#ownerName").val(su.ownerName);
                    $("#ownerPwd").val("******");
                    $("#ownerEmail").val(su.ownerEmail);
                    $("#ownerTel").val(su.ownerTel);
                    $("#ownerAddr").val(su.ownerAddr);
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