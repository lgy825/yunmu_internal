<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>工作台</title>
    <%@include file="/static/commons/common.jspf"%>
</head>
<body>
<!-- 工作台 -->
<div class="p20">
    <!-- 搜索框 -->
    <div class="select-search">
        <form action="">
            <div>
                <input type="text" class="inpW inpWid1 ml20" placeholder="请选择项目可多选"/>
                <input type="button" class="blue_btn ml20" value="更新数据"/>
            </div>
        </form>
    </div>
    <!-- 票务数据 -->
    <div class="bgc-ff mt20">
        <div class="clearfix b_title">
            <div class="fl mr20">订单数据</div>
            <span class="title-des fl color-gray8c">数据为今日数据，实时更新</span>
        </div>
        <div class="hr">
            <hr>
        </div>
        <div class="data-wrap p20">
            <ul class="data-list-wrap clearfix">
                <li class="data-list">
                    <div class="data-content mr10 relative">
                        <p class="mt20 color-gray8c">订单总收益(元)</p>
                        <p class="mt30 color-blue fw f24">12340</p>
                        <div class="mt30">
                            <span class="color-gray8c">比前一天</span>
                            <span class="data-up color-red"><i class="up-rise">1200</i></span>
                        </div>
                    </div>
                </li>
                <li class="data-list">
                    <div class="data-content mr10 relative">
                        <p class="mt20 color-gray8c">房间总收益(元)</p>
                        <p class="mt30 color-blue fw f24">9800</p>
                        <div class="mt30">
                            <span class="color-gray8c">比前一天</span>
                            <span class="data-up color-red"><i class="up-rise">800</i></span>
                        </div>
                    </div>
                </li>
                <li class="data-list">
                    <div class="data-content mr10 relative">
                        <p class="mt20 color-gray8c">支出总额(元)</p>
                        <p class="mt30 color-blue fw f24">890</p>
                        <div class="mt30">
                            <span class="color-gray8c">比前一天</span>
                            <span class="data-down color-green"><i class="up-rise">1.5%</i></span>
                        </div>
                    </div>
                </li>
                <li class="data-list">
                    <div class="data-content mr10">
                        <p class="mt20 color-gray8c">商品收益(元)</p>
                        <p class="mt30 color-blue fw f24">3</p>
                        <div class="mt30">
                            <span class="color-gray8c">比前一天</span>
                            <span class="data-up color-red"><i class="up-rise">1.2%</i></span>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <!-- 卖品数据 -->
    <div class="bgc-ff mt20">
        <div class="clearfix b_title">
            <div class="fl mr20">房间数据</div>
            <span class="fl color-gray8c title-des">数据为今日数据，实时更新</span>
        </div>
        <div class="hr">
            <hr>
        </div>
        <div class="data-wrap p20">
            <ul class="data-list-wrap clearfix">
                <li class="data-list">
                    <div class="data-content mr10 relative">
                        <p class="mt20 color-gray8c">总房间数</p>
                        <p class="mt30 color-blue fw f24">50</p>
                    </div>
                </li>
                <li class="data-list">
                    <div class="data-content mr10 relative">
                        <p class="mt20 color-gray8c">出租数</p>
                        <p class="mt30 color-blue fw f24">38</p>
                        <div class="mt30">
                            <span class="color-gray8c">比前一天</span>
                            <span class="data-down color-green"><i class="up-rise">1.5%</i></span>
                        </div>
                    </div>
                </li>
                <li class="data-list ">
                    <div class="data-content mr10">
                        <p class="mt20 color-gray8c">房间剩余</p>
                        <p class="mt30 color-blue fw f24">12</p>
                        <div class="mt30">
                            <span class="color-gray8c">比前一天</span>
                            <span class="data-up color-red"><i class="up-rise">1.2%</i></span>
                        </div>
                    </div>
                </li>
                <li class="data-list ">
                    <div class="data-content mr10">
                        <p class="mt20 color-gray8c">出租率</p>
                        <p class="mt30 color-blue fw f24">82%</p>
                        <div class="mt30">
                            <span class="color-gray8c">比前一天</span>
                            <span class="data-up color-red"><i class="up-rise">1.2%</i></span>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>

<!-- 引导层 -->
<div class="modality-layer none">
    <div class="mask-box">
        <div class="guide-bg">
            <span class="iknow"></span>
        </div>
    </div>
</div>

</body>
<script>
    $(function () {
        $('.list-boxoffice').on('mouseenter', function () {
            $(this).hide();
            $(this).next().show();
        });
        $('.list-activity').on('mouseleave', function () {
            $(this).hide();
            $(this).prev().show();
        });

        // 引导层关闭
        $('.iknow').on('click', function () {
            $('.modality-layer').hide();
        });

    });
</script>
</html>