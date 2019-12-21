$(function(){


    loadPage();
    $("#searchBtn").click(function () {
        loadPage();
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
                url: ctx + 'sysuser/getpage',
                params:{
                    customerName: $.trim($("#customerName").val())
                },
                success: function (data) {
                    // data为ajax返回数据
                    $("#customerTable").empty().html($("#trTmpl").render(data.resultData));
                },
                totalName: 'total'
            }
        });
    }

    $("#resetBtn").click(function () {
        $("#customerName").val("");
    });


    $("#customerTable").on("click", ".shutbtn", function () {
        var sid = $(this).data("sid");
        layer.confirm('停用后该客户将无法关联合同，是否继续？', function () {
            $.ajax({
                url: ctx + "customer/disable",
                type: "GET",
                cache: false,
                async: false,
                dataType: 'json',
                data: {id: sid},
                success: function (data) {
                    if (data && data.resultCode === '0') {
                        layer.msg('停用成功 !');
                        loadPage();
                    } else {
                        if (data.resultDesc) {
                            layer.msg(data.resultDesc);
                        } else {
                            layer.msg('停用失败 !');
                        }
                    }
                },
                error: function () {
                    layer.msg('停用失败 !');
                }
            });
        });
    });

    $("#customerTable").on("click", ".openbtn", function () {
        var sid = $(this).data("sid");
        layer.confirm('是否继续启用该客户？', function () {
            $.ajax({
                url: ctx + "customer/undisable",
                type: "GET",
                cache: false,
                async: false,
                dataType: 'json',
                data: {id: sid},
                success: function (data) {
                    if (data && data.resultCode === '0') {
                        layer.msg('启用成功 !');
                        loadPage();
                    } else {
                        if (data.resultDesc) {
                            layer.msg(data.resultDesc);
                        } else {
                            layer.msg('启用失败 !');
                        }
                    }
                },
                error: function () {
                    layer.msg('启用失败 !');
                }
            });
        });
    });
});