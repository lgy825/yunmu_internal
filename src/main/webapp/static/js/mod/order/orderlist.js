$(function(){

    loadPage();
    $("#searchBtn").click(function () {
        loadPage();
    });

    $("#timeSpick").datetimepicker({
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
    $("#timeEpick").datetimepicker({
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
                url: ctx + 'order/getpage',
                params:{
                    orderId: $.trim($("#orderId").val()),
                    beginTime:$("#timeSpick").val(),
                    hourseNumber:$.trim($("#hourseNumber").val()),
                    endTime:$("#timeEpick").val()
                },
                success: function (data) {
                    // data为ajax返回数据
                    $("#orderTable").empty().html($("#trTmpl").render(data.resultData));
                },
                totalName: 'total'
            }
        });
    }

    $("#resetBtn").click(function () {
        $("#orderId").val("");

        $("#hourseNumber").val("");
        $("#timeSpick").val("");
        $("#timeEpick").val("");
        $("#searchBtn").click();
    });

    $("#orderTable").on("click", ".delete", function (){
        var orderId = $(this).data("sid");
        layer.confirm('删除后将无法恢复，是否继续？', function () {
            $.ajax({
                url: ctx + "order/delete",
                type: "GET",
                cache: false,
                // async: false,
                dataType: 'json',
                data: {id: orderId},
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

    $("#exportBtn").click(function () {
        var orderId= $("#orderId").val();
        var hourseNumber=$("#hourseNumber").val();
        var beginTime=$("#timeSpick").val();
        var endTime =$("#timeEpick").val();
        if ($.trim(hourseNumber)=='' || $.trim(hourseNumber)==null) {
            layer.msg("请至少输入房间号再导出");
            return;
        }
        if (beginTime.length < 1 || endTime.length < 1) {
            layer.confirm('您没有选择导出起止时间，是否继续？', function () {
                location.href = ctx + "order/exportOrder?hourseNumber="+hourseNumber+"&beginTime="+beginTime+"&endTime="+endTime;
            });
        } else {
            if (Date.parse(beginTime) > Date.parse(endTime)) {
                layer.msg("结束日期不能早于开始日期");
                return;
            }
            location.href = ctx + "order/exportOrder?hourseNumber="+hourseNumber+"&beginTime="+beginTime+"&endTime="+endTime;
        }
    });

});