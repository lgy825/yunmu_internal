<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="renderer" content="webkit">
    <title>订单详情</title>
    <%@ include file="/static/commons/common.jspf"%>
</head>
<body>
<div class="p20">
    <div class="bgc-ff">
        <h1 class="b_title">基本信息</h1>
        <div class="hr">
            <hr>
        </div>
        <input id="oId" type="hidden" value="${oId}" />
        <div class="movBox movBox1 clearfix p20 modify-percent">
            <div>
                <div>
                    <span class="color-graya8">订单号</span>
                    <p class="mt10">${orderExt.oId}</p>
                </div>
            </div>
            <div>
                <div>
                    <span class="color-graya8">房间信息</span>
                    <p class="mt10">${orderExt.hNumber}</p>
                </div>
            </div>
            <div>
                <div>
                    <span class="color-graya8">订单创建时间</span>
                    <p class="mt10">
                    <fmt:formatDate value="${orderExt.oDate}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                </div>
            </div>
            <div>
                <div>
                    <span class="color-graya8">订单来源</span>
                    <p class="mt10">${orderExt.sourceWay}</p>
                </div>
            </div>
            <div class="w17">
                <div>
                    <span class="color-graya8">实收金额(元)</span>
                    <p class="mt10">${orderExt.oRecAmount}</p>
                </div>
            </div>
            <div>
                <div>
                    <span class="color-graya8">开支</span>
                    <p class="mt10 color-blue">${orderExt.oActAmount==''||orderExt.oActAmount==null ? "0" :orderExt.oActAmount}</p>
                </div>
            </div>
            <div>
                <div>
                    <span class="color-graya8">支付方式</span>
                    <p class="mt10 color-blue">${orderExt.payWay}</p>
                </div>
            </div>
        </div>
    </div>
    <div class="bgc-ff mt20">
        <h1 class="b_title">订单附加项信息</h1>
        <div class="hr">
            <hr>
        </div>
        <div class="p20">
            <div class="movBox movBox1 clearfix modify-percent">
                <div>
                    <div>
                        <span class="color-graya8">附加项名称</span>
                    </div>
                </div>
                <div>
                    <div>
                        <span class="color-graya8">金额(元)</span>

                    </div>
                </div>
                <div>
                    <div>
                        <span class="color-graya8">描述</span>

                    </div>
                </div>
                <div>
                    <div>
                        <span class="color-graya8">创建时间</span>
                    </div>
                </div>
            </div>
            <c:forEach items="${orderExt.orderItems}" var="item">
                <div class="movBox movBox1 clearfix modify-percent mt10">
                    <div>
                        <div>
                            <p>${item.dName}</p>
                        </div>
                    </div>
                    <div>
                        <div>
                            <p><fmt:formatNumber value="${item.dAmount}" maxFractionDigits="2" minFractionDigits="2"/></p>
                        </div>
                    </div>
                    <div>
                        <div>
                            <p>${item.dDesc==''||item.dDesc==null ? "--" :item.dDesc}</p>
                        </div>
                    </div>
                    <div>
                        <div>
                            <p><fmt:formatDate value="${item.dDate}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    </div>
    <div>
        <div>
            <div class="tc mt30">
                <input type="button" id="rtBtn" class="blue_btn blue_btn30" value="返回"/>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $("#rtBtn").on("click",function(event){
        window.history.back();
    });
</script>
</body>
</html>