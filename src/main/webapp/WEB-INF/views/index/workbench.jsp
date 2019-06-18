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
                <select class="select">
                    <option value="0">北京</option>
                    <option value="1">天津</option>
                    <option value="2">上海</option>
                    <option value="3">重庆</option>
                </select>
                <input type="text" class="inpW inpWid1 ml20" placeholder="请选择影院可多选"/>
                <input type="button" class="blue_btn ml20" value="更新数据"/>
            </div>
        </form>
    </div>
    <!-- 票务数据 -->
    <div class="bgc-ff mt20">
        <div class="clearfix b_title">
            <div class="fl mr20">票务数据</div>
            <span class="title-des fl color-gray8c">数据为今日数据，实时更新</span>
            <a class="title-des fr color-blue" href="javscript:;">查看更多</a>
        </div>
        <div class="hr">
            <hr>
        </div>
        <div class="data-wrap p20">
            <ul class="data-list-wrap clearfix">
                <li class="data-list list-hover relative">
                    <div class="data-content mr10 relative list-boxoffice">
                        <p class="mt20 color-gray8c">票房(元)</p>
                        <p class="mt30 color-blue fw f24">98000</p>
                        <div class="mt30">
                            <span class="color-gray8c">比前一天</span>
                            <span class="data-up color-red"><i class="up-rise">12100</i></span>
                        </div>
                        <input type="button" class="blue_wid" value="提高票房"/>
                    </div>
                    <div class="data-content mr10 relative list-activity none">
                        <div class="empty"></div>
                        <p class="mt20 tc color-gray8c">创建活动，有效提高票房</p>
                        <input type="button" class="blue_wid" value="创建活动"/>
                    </div>
                </li>
                <li class="data-list">
                    <div class="data-content mr10 relative">
                        <p class="mt20 color-gray8c">大盘占比</p>
                        <p class="mt30 color-blue fw f24">6.8%</p>
                        <div class="mt30">
                            <span class="color-gray8c">比前一天</span>
                            <span class="data-up color-red"><i class="up-rise">1.2%</i></span>
                        </div>
                    </div>
                </li>
                <li class="data-list">
                    <div class="data-content mr10 relative">
                        <p class="mt20 color-gray8c">人次</p>
                        <p class="mt30 color-blue fw f24">386</p>
                        <div class="mt30">
                            <span class="color-gray8c">比前一天</span>
                            <span class="data-down color-green"><i class="up-rise">1.5%</i></span>
                        </div>
                        <input type="button" class="blue_wid" value="吸引购票"/>
                    </div>
                </li>
                <li class="data-list">
                    <div class="data-content mr10">
                        <p class="mt20 color-gray8c">人次占比</p>
                        <p class="mt30 color-blue fw f24">12.6%</p>
                        <div class="mt30">
                            <span class="color-gray8c">比前一天</span>
                            <span class="data-up color-red"><i class="up-rise">1.2%</i></span>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <!-- 我的会员 -->
    <div class="mt20">
        <div class="vip-wrap clearfix">
            <div class="vip-data ">
                <div class="vip-single mr20 clearfix">
                    <div class="clearfix b_title">
                        <div class="fl mr20">我的会员</div>
                        <span class="fl color-gray8c title-des">数据为今日数据，实时更新</span>
                        <a class="fr color-blue title-des" href="javscript:;">查看更多</a>
                    </div>
                    <div class="hr">
                        <hr>
                    </div>
                    <div class="p20 clearfix">
                        <div class="vip-left fl"></div>
                        <div class="vip-right fl">
                            <div class="vip-info">
                                <div class="">
                                    <span class="fixed-wid color-gray8c">会员总数</span>
                                    <span class="color-gray8c">新增会员数</span>
                                </div>
                                <div class="">
                                    <span class="fixed-wid f18 mt12 fw">3890</span>
                                    <span class="f18 mt12 color-lred fw">230</span>
                                </div>
                            </div>
                            <div class="vip-info mt20">
                                <div class="">
                                    <span class="fixed-wid color-gray8c">持卡会员总数</span>
                                    <span class="color-gray8c">新增持卡会员数</span>
                                </div>
                                <div class="">
                                    <span class="fixed-wid f18 mt12 fw">3890</span>
                                    <span class="f18 mt12 color-lred fw">230</span>
                                </div>
                            </div>
                            <div class="vip-info mt20">
                                <div class="">
                                    <span class="fixed-wid color-gray8c">活跃会员数</span>
                                    <span class="color-gray8c ">新增会员贡献票房（元）</span>
                                </div>
                                <div class="">
                                    <span class="fixed-wid f18 mt12 fw">3890</span>
                                    <span class="f18 mt12 color-lred fw">230</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="vip-operation p20">
                        <span class="vip-notice pl20 color-lred">有效的会员关怀可提升会员转化率和活跃度</span>
                        <a href="../会员营销/会员关怀/会员关怀.html">
                            <input type="button" class="blue_btn blue_btn30 fr" value="会员关怀"/>
                        </a>
                    </div>
                </div>
            </div>
            <div class="vip-data ">
                <div class="vip-single clearfix">
                    <div class="clearfix b_title">
                        <div class="fl mr20">会员卡</div>
                        <span class="fl color-gray8c title-des">数据为今日数据，实时更新</span>
                        <a class="fr color-blue title-des" href="javscript:;">查看更多</a>
                    </div>
                    <div class="hr">
                        <hr>
                    </div>
                    <div class="p20 clearfix">
                        <div class="vip-left vip-card fl"></div>
                        <div class="vip-right fl">
                            <div class="vip-info">
                                <div class="">
                                    <span class="fixed-wid color-gray8c">开卡总数</span>
                                </div>
                                <div class="">
                                    <span class="fixed-wid f18 mt12 fw">3890</span>
                                </div>
                            </div>
                            <div class="vip-info mt20">
                                <div class="">
                                    <span class="fixed-wid color-gray8c">新增开卡数</span>
                                </div>
                                <div class="mt12">
                                    <span class="f18 color-lred fw">3890</span>
                                </div>
                            </div>
                            <div class="vip-info mt20">
                                <div class="">
                                    <span class="fixed-wid color-gray8c">卡费收益（元）</span>
                                </div>
                                <div class="mt12">
                                    <span class="f18  color-blue fw">230</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="vip-operation p20">
                        <span class="vip-notice pl20 color-lred">设置会员卡促销可增加办卡量</span>
                        <a href="../会员营销/会员卡促销/会员卡促销.html">
                            <input type="button" class="blue_btn blue_btn30 fr" value="会员卡促销"/>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 卖品数据 -->
    <div class="bgc-ff mt20">
        <div class="clearfix b_title">
            <div class="fl mr20">卖品数据</div>
            <span class="fl color-gray8c title-des">数据为今日数据，实时更新</span>
            <a class="fr color-blue title-des" href="javscript:;">查看更多</a>
        </div>
        <div class="hr">
            <hr>
        </div>
        <div class="data-wrap p20">
            <ul class="data-list-wrap clearfix">
                <li class="data-list">
                    <div class="data-content mr10 relative list-boxoffice">
                        <p class="mt20 color-gray8c">销售额(元)</p>
                        <p class="mt30 color-blue fw f24">800</p>
                        <div class="mt30">
                            <span class="color-gray8c">比前一天</span>
                            <span class="data-up color-red"><i class="up-rise">4522</i></span>
                        </div>
                        <input type="button" class="blue_wid" value="提高销售额"/>
                    </div>
                    <div class="data-content mr10 relative none list-activity">
                        <div class="empty"></div>
                        <p class="mt20 tc color-gray8c">创建卖品促销活动，有效提高销售额</p>
                        <input type="button" class="blue_wid" value="创建活动"/>
                    </div>
                </li>
                <li class="data-list">
                    <div class="data-content mr10 relative">
                        <p class="mt20 color-gray8c">卖品SPP</p>
                        <p class="mt30 color-blue fw f24">0.63</p>
                        <div class="mt30">
                            <span class="color-gray8c">比前一天</span>
                            <span class="data-up color-red"><i class="up-rise">1.2%</i></span>
                        </div>
                    </div>
                </li>
                <li class="data-list">
                    <div class="data-content mr10 relative">
                        <p class="mt20 color-gray8c">点购率</p>
                        <p class="mt30 color-blue fw f24">38.6%</p>
                        <div class="mt30">
                            <span class="color-gray8c">比前一天</span>
                            <span class="data-down color-green"><i class="up-rise">1.5%</i></span>
                        </div>
                        <input type="button" class="blue_wid" value="提高点购率"/>
                    </div>
                </li>
                <li class="data-list ">
                    <div class="data-content mr10">
                        <p class="mt20 color-gray8c">客单价</p>
                        <p class="mt30 color-blue fw f24">24.8</p>
                        <div class="mt30">
                            <span class="color-gray8c">比前一天</span>
                            <span class="data-up color-red"><i class="up-rise">1.2%</i></span>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <!-- 票务营销 -->
    <div class="mt20">
        <div class="vip-wrap clearfix">
            <div class="vip-data ">
                <div class="vip-single mr20 clearfix">
                    <div class="clearfix b_title">
                        <div class="fl mr20">票务营销</div>
                        <span class="fl color-gray8c title-des">展示正在进行中的活动，最多显示3条数据</span>
                        <a class="fr color-blue title-des" href="javscript:;">查看更多</a>
                    </div>
                    <div class="hr">
                        <hr>
                    </div>
                    <div class="">
                        <table class="ticket-sell" cellpadding="0" cellspacing="0">
                            <tr class="">
                                <th class="">
                                    <div class="">活动</div>
                                </th>
                                <th class="">
                                    <div class="">状态</div>
                                </th>
                                <th class="">
                                    <div class="">影院</div>
                                </th>
                                <th class="">
                                    <div class="">有效期</div>
                                </th>
                                <th class="">
                                    <div class="tr">出票数量</div>
                                </th>
                                <th class=""></th>
                            </tr>
                            <tr>
                                <td>
                                    <div class="">蜘蛛侠19.8抢票</div>
                                </td>
                                <td>
                                    <div class="">
                                        <i class="state bgc-blue"></i>
                                        <span>进行中</span>
                                    </div>
                                </td>
                                <td>
                                    <div class="">美嘉朝阳三里屯店</div>
                                </td>
                                <td>
                                    <div class="">9.13-9.18</div>
                                </td>
                                <td>
                                    <div class="tr">1280</div>
                                </td>
                                <td>
                                    <div class="">
                                        <a href="javascript:;" class="color-blue">查看效果</a>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="">蜘蛛侠19.8抢票</div>
                                </td>
                                <td>
                                    <div class="">
                                        <i class="state bgc-blue"></i>
                                        <span>进行中</span>
                                    </div>
                                </td>
                                <td>
                                    <div class="">美嘉朝阳三里屯店</div>
                                </td>
                                <td>
                                    <div class="">29.13-9.18</div>
                                </td>
                                <td>
                                    <div class="tr">1280</div>
                                </td>
                                <td>
                                    <div class=""><a href="javascript:;" class="color-blue">查看效果</a></div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="">蜘蛛侠19.8抢票</div>
                                </td>
                                <td>
                                    <div class="">
                                        <i class="state bgc-lred"></i>
                                        <span>已结束</span>
                                    </div>
                                </td>
                                <td>
                                    <div class="">美嘉三里屯店</div>
                                </td>
                                <td>
                                    <div class="">9.13-9.18</div>
                                </td>
                                <td>
                                    <div class="tr">1280</div>
                                </td>
                                <td>
                                    <div class=""><a href="javascript:;" class="color-blue">查看效果</a></div>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="vip-operation vip-operation-solid p20">
                        <a href="../会员营销/票务红包/票务红包.html">
                            <input type="button" class="blue_btn blue_btn30 fr ml30" value="票务红包"/>
                        </a>
                        <a href="../会员营销/票务营销/票务营销.html">
                            <input type="button" class="blue_btn blue_btn30 fr" value="票务营销"/>
                        </a>
                    </div>
                </div>
            </div>
            <div class="vip-data ">
                <div class="vip-single clearfix">
                    <div class="clearfix b_title">
                        <div class="fl mr20">卖品营销</div>
                        <span class="fl color-gray8c title-des">展示正在进行中的活动，最多显示3条数据</span>
                        <a class="fr color-blue title-des" href="javscript:;">查看更多</a>
                    </div>
                    <div class="hr">
                        <hr>
                    </div>
                    <div class="sell-type">
                        <table class="ticket-sell" cellpadding="0" cellspacing="0">
                            <tr class="">
                                <th class="">
                                    <div class="">活动</div>
                                </th>
                                <th class="">
                                    <div class="">状态</div>
                                </th>
                                <th class="">
                                    <div class="">影院</div>
                                </th>
                                <th class="">
                                    <div class="">有效期</div>
                                </th>
                                <th class="">
                                    <div class="tr">出票数量</div>
                                </th>
                                <th class=""></th>
                            </tr>
                            <tr>
                                <td>
                                    <div class="">蜘蛛侠19.8抢票</div>
                                </td>
                                <td>
                                    <div class="">
                                        <i class="state bgc-blue"></i>
                                        <span>进行中</span>
                                    </div>
                                </td>
                                <td>
                                    <div class="">美嘉朝阳三里屯店</div>
                                </td>
                                <td>
                                    <div class="">9.13-9.18</div>
                                </td>
                                <td>
                                    <div class="tr">1280</div>
                                </td>
                                <td>
                                    <div class="">
                                        <a href="javascript:;" class="color-blue">查看效果</a>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="">蜘蛛侠19.8抢票</div>
                                </td>
                                <td>
                                    <div class="">
                                        <i class="state bgc-blue"></i>
                                        <span>进行中</span>
                                    </div>
                                </td>
                                <td>
                                    <div class="">美嘉朝阳三里屯店</div>
                                </td>
                                <td>
                                    <div class="">29.13-9.18</div>
                                </td>
                                <td>
                                    <div class="tr">1280</div>
                                </td>
                                <td>
                                    <div class=""><a href="javascript:;" class="color-blue">查看效果</a></div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="">蜘蛛侠19.8抢票</div>
                                </td>
                                <td>
                                    <div class="">
                                        <i class="state bgc-lred"></i>
                                        <span>已结束</span>
                                    </div>
                                </td>
                                <td>
                                    <div class="">美嘉三里屯店</div>
                                </td>
                                <td>
                                    <div class="">9.13-9.18</div>
                                </td>
                                <td>
                                    <div class="tr">1280</div>
                                </td>
                                <td>
                                    <div class=""><a href="javascript:;" class="color-blue">查看效果</a></div>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="vip-operation vip-operation-solid p20">
                        <a href="../会员营销/票务红包/票务红包.html">
                            <input type="button" class="blue_btn blue_btn30 fr ml30" value="票务红包"/>
                        </a>
                        <a href="../会员营销/卖品营销/卖品营销.html">
                            <input type="button" class="blue_btn blue_btn30 fr" value="卖品营销"/>
                        </a>
                    </div>
                </div>
            </div>
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