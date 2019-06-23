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
                                           url: ctx + 'company/getpage',
                                           params:{
                                               projectName: $.trim($("#projectName").val())
                                           },
                                           success: function (data) {
                                               // data为ajax返回数据
                                               $("#companyTable").empty().html($("#trTmpl").render(data.resultData));
                                           },
                                           totalName: 'total'
                                       }
                                   });
    }

    $("#resetBtn").click(function () {
        $("#companyName").val("").trigger('change');
    });



});