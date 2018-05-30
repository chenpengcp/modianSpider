<%--
  Created by IntelliJ IDEA.
  User: chen_
  Date: 2018/4/26
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>费沁源的小绿龟个站</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/unslider.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/canvas_bg.js"></script>
    <!-- 写点样式，让轮播好看点 -->
    <style>
        ul, ol {
            padding: 0;
        }

        .banner {
            position: relative;
            overflow: auto;
            text-align: center;
        }

        .banner li {
            list-style: none;
        }

        .banner ul li {
            float: left;
        }

        #b04 {
            width: 640px;
        }

        #b04 .dots {
            position: absolute;
            left: 0;
            right: 0;
            bottom: 20px;
        }

        #b04 .dots li {
            display: inline-block;
            width: 10px;
            height: 10px;
            margin: 0 4px;
            text-indent: -999em;
            border: 2px solid #fff;
            border-radius: 6px;
            cursor: pointer;
            opacity: .4;
            -webkit-transition: background .5s, opacity .5s;
            -moz-transition: background .5s, opacity .5s;
            transition: background .5s, opacity .5s;
        }

        #b04 .dots li.active {
            background: #fff;
            opacity: 1;
        }

        #b04 .arrow {
            position: absolute;
            top: 200px;
        }

        #b04 #al {
            left: 15px;
        }

        #b04 #ar {
            right: 15px;
        }

        #mm {
            position: absolute;
            top: 100px;
            left: 35%;
            cursor: pointer;
        }

        #mm:hover {

        }

        .time-item strong {
            background: #C71C60;
            color: #fff;
            line-height: 49px;
            font-size: 36px;
            font-family: Arial;
            padding: 0 10px;
            margin-right: 10px;
            border-radius: 5px;
            box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.2);
        }

        #day_show {
            float: left;
            line-height: 49px;
            color: #c71c60;
            font-size: 32px;
            margin: 0 10px;
            font-family: Arial, Helvetica, sans-serif;
        }

        .item-title .unit {
            background: none;
            line-height: 49px;
            font-size: 24px;
            padding: 0 10px;
            float: left;
        }
    </style>
</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <div class="span12" style="margin-top: 10px">
            <div class="tabbable"> <!-- Only required for left/right tabs -->
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#tab1" data-toggle="tab">首页</a></li>
                    <li><a href="#tab2" data-toggle="tab">集资</a></li>
                    <li><a href="#tab3" data-toggle="tab">相册</a></li>
                    <li><a href="#tab4" data-toggle="tab">微博</a></li>
                    <li><a href="#tab5" data-toggle="tab">口袋</a></li>
                    <li><a href="#tab6" data-toggle="tab">关于</a></li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="tab1">
                        <canvas id="c" style="position: absolute;z-index: -1;text-align: center;"></canvas>
                        <div id="mm">
                            <img src="${pageContext.request.contextPath}/image/fqy.jpg" class="img-circle">
                            <div style="margin-left: 40px"><h1 style="color: deepskyblue;font-family: Arial;">SNH48 Team
                                HII 费沁源</h1></div>

                        </div>
                    </div>
                    <div class="tab-pane" id="tab2" style="margin-left: 200px">
                        <div class="row-fluid">
                            <div class="span6">
                                <div class="input-append" style="margin-top: 20px">
                                    <input class="span2" id="appendedInputButton" type="text"
                                           style="width:250px ;margin-left: 50px">
                                    <button class="btn" type="button">输入摩点id查询自己的集资额</button>
                                </div>
                                <div id="main" style="width: 1800px;height:700px;"></div>
                            </div>
                            <%--<div class="span6">--%>
                            <%--<div><input type="submit" name="Submit" value="点击查看锅推十熊"--%>
                            <%--style="width: 300px;height: 100px;color: crimson;font-size: larger;font-weight: bold"--%>
                            <%--onclick=window.open("${pageContext.request.contextPath}/servlet/dataServlet")>--%>
                            <%--</div>--%>
                            <%--</div>--%>
                        </div>
                    </div>
                    <div class="tab-pane" id="tab3">
                        <!-- 把要轮播的地方写上来 -->
                        <div class="row-fluid">
                            <div class="span1"></div>
                            <div class="span4">
                                <div class="panel-group" id="accordion">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#accordion"
                                                   href="#collapseOne">
                                                    费沁源简介：
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapseOne" class="panel-collapse collapse in">
                                            <div class="panel-body">
                                                <p class="text-left">费沁源，2001年3月20日出生于上海市，中国内地流行乐女歌手、影视演员，原SNH48 Team
                                                    XII，现SNH48 Team HII成员 。</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#accordion"
                                                   href="#collapseTwo">
                                                    费沁源的经历：
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapseTwo" class="panel-collapse collapse">
                                            <div class="panel-body">
                                                <p class="text-left">
                                                    2015年7月25日，参加SNH48第二届“梦想高飞”总选举演唱会，并作为五期生亮相出道；同年12月4日，参加《剧场女神》公演正式出道。2016年1月，参加优酷土豆国民偶像养成真人秀《国民美少女》从而受到关注；随后因甜美清纯的外貌被日媒评为“四千年一遇的美少女”；同年4月，与张柏芝、Bigbang成员T.O.P共同主演动作片《失控·幽灵飞车》，在片中饰演张柏芝的妹妹，与张柏芝一起飞车脱险，上演速度与激情，这也是费沁源的首部电影作品；7月30日，参加
                                                    “SNH48第三届总选举”发布演唱会，获得第22名。2018年2月3日，SNH48首次大重组后，重新分队至SNH48 Team
                                                    HII。</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#accordion"
                                                   href="#collapseThree">
                                                    费沁源的作品：
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapseThree" class="panel-collapse collapse">
                                            <div class="panel-body">
                                                <p class="text-left">失控·幽灵飞车</p>
                                                <p class="text-left">贴身校花</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="span1"></div>
                            <div class="span6">
                                <div class="banner" id="b04">
                                    <ul>
                                        <li><img src="${pageContext.request.contextPath}/image/1.JPG" alt=""
                                                 width="640" height="480"></li>
                                        <li><img src="${pageContext.request.contextPath}/image/2.JPG" alt=""
                                                 width="640" height="480"></li>
                                        <li><img src="${pageContext.request.contextPath}/image/3.JPG" alt=""
                                                 width="640" height="480"></li>
                                        <li><img src="${pageContext.request.contextPath}/image/4.JPG" alt=""
                                                 width="640" height="480"></li>
                                        <li><img src="${pageContext.request.contextPath}/image/5.JPG" alt=""
                                                 width="640" height="480"></li>
                                        <li><img src="${pageContext.request.contextPath}/image/6.JPG" alt=""
                                                 width="640" height="480"></li>
                                    </ul>
                                    <a href="javascript:void(0);" class="unslider-arrow04 prev"><img
                                            class="arrow"
                                            id="al"
                                            src="${pageContext.request.contextPath}/image/arrowl.png"
                                            alt="prev"
                                            width="20"
                                            height="35"></a>
                                    <a href="javascript:void(0);" class="unslider-arrow04 next"><img
                                            class="arrow"
                                            id="ar"
                                            src="${pageContext.request.contextPath}/image/arrowr.png"
                                            alt="next"
                                            width="20"
                                            height="37"></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane" id="tab4">
                        <p>正在开发中...</p>
                    </div>
                    <div class="tab-pane" id="tab5">
                        <p>正在开发中...</p>
                    </div>
                    <div class="tab-pane" id="tab6">
                        <canvas id="canvas" width="100%" height="100%" style="position: absolute;z-index:-1"></canvas>
                        <div class="row-fluid">
                            <div class="span4"></div>
                            <div class="span4" style="margin-top: 200px">
                                <h1 style="color:#fff ;font-family: Arial">距离总选还有：</h1>
                                <div class="time-item">
                                    <span id="day_show">0天</span>
                                    <strong id="hour_show">0时</strong>
                                    <strong id="minute_show">0分</strong>
                                    <strong id="second_show">0秒</strong>
                                </div>
                            </div>
                            <div class="span4"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--<div><input type="submit" name="Submit" value="爬取搜索数据"--%>
        <%--style="width: 300px;height: 100px;color: crimson;font-size: larger;font-weight: bold"--%>
        <%--onclick=window.open("${pageContext.request.contextPath}/servlet/appSearchServlet")>--%>
        <%--</div>--%>
        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    </div>
</div>

<!-- 最后用js控制 -->
<script>
    $(document).ready(function (e) {
        var unslider04 = $('#b04').unslider({
                dots: true
            }),
            data04 = unslider04.data('unslider');

        $('.unslider-arrow04').click(function () {
            var fn = this.className.split(' ')[1];
            data04[fn]();
        });
    });
</script>

<script>
    var canvas = document.getElementById('canvas'),
        ctx = canvas.getContext('2d'),
        w = canvas.width = window.innerWidth,
        h = canvas.height = window.innerHeight,

        hue = 217,
        stars = [],
        count = 0,
        maxStars = 1200;

    var canvas2 = document.createElement('canvas'),
        ctx2 = canvas2.getContext('2d');
    canvas2.width = 100;
    canvas2.height = 100;
    var half = canvas2.width / 2,
        gradient2 = ctx2.createRadialGradient(half, half, 0, half, half, half);
    gradient2.addColorStop(0.025, '#fff');
    gradient2.addColorStop(0.1, 'hsl(' + hue + ', 61%, 33%)');
    gradient2.addColorStop(0.25, 'hsl(' + hue + ', 64%, 6%)');
    gradient2.addColorStop(1, 'transparent');

    ctx2.fillStyle = gradient2;
    ctx2.beginPath();
    ctx2.arc(half, half, half, 0, Math.PI * 2);
    ctx2.fill();

    // End cache
    $(window).resize(function () {
        w = canvas.width = window.innerWidth;
        h = canvas.height = window.innerHeight;
        canvas.width(w).height(h);

    });

    function random(min, max) {
        if (arguments.length < 2) {
            max = min;
            min = 0;
        }

        if (min > max) {
            var hold = max;
            max = min;
            min = hold;
        }

        return Math.floor(Math.random() * (max - min + 1)) + min;
    }

    function maxOrbit(x, y) {
        var max = Math.max(x, y),
            diameter = Math.round(Math.sqrt(max * max + max * max));
        return diameter / 2;
    }

    var Star = function () {

        this.orbitRadius = random(maxOrbit(w, h));
        this.radius = random(60, this.orbitRadius) / 12;
        this.orbitX = w / 2;
        this.orbitY = h / 2;
        this.timePassed = random(0, maxStars);
        this.speed = random(this.orbitRadius) / 900000;
        this.alpha = random(2, 10) / 10;

        count++;
        stars[count] = this;
    }

    Star.prototype.draw = function () {
        var x = Math.sin(this.timePassed) * this.orbitRadius + this.orbitX,
            y = Math.cos(this.timePassed) * this.orbitRadius + this.orbitY,
            twinkle = random(10);

        if (twinkle === 1 && this.alpha > 0) {
            this.alpha -= 0.05;
        } else if (twinkle === 2 && this.alpha < 1) {
            this.alpha += 0.05;
        }

        ctx.globalAlpha = this.alpha;
        ctx.drawImage(canvas2, x - this.radius / 2, y - this.radius / 2, this.radius, this.radius);
        this.timePassed += this.speed;
    }

    for (var i = 0; i < maxStars; i++) {
        new Star();
    }

    function animation() {
        ctx.globalCompositeOperation = 'source-over';
        ctx.globalAlpha = 0.8;
        ctx.fillStyle = 'hsla(' + hue + ', 64%, 6%, 1)';
        ctx.fillRect(0, 0, w, h)

        ctx.globalCompositeOperation = 'lighter';
        for (var i = 1, l = stars.length; i < l; i++) {
            stars[i].draw();
        }
        ;

        window.requestAnimationFrame(animation);
    }

    animation();
</script>

<script>
    var intDiff = parseInt(5097600); //倒计时总秒数量
    function timer(intDiff) {
        window.setInterval(function () {
            var day = 0,
                hour = 0,
                minute = 0,
                second = 0; //时间默认值
            if (intDiff > 0) {
                day = Math.floor(intDiff / (60 * 60 * 24));
                hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
                minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
                second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
            }
            if (minute <= 9) minute = '0' + minute;
            if (second <= 9) second = '0' + second;
            $('#day_show').html(day + "天");
            $('#hour_show').html('<s id="h"></s>' + hour + '时');
            $('#minute_show').html('<s></s>' + minute + '分');
            $('#second_show').html('<s></s>' + second + '秒');
            intDiff--;
        }, 1000);
    }

    $(function () {
        timer(intDiff);
    });
</script>
<script>
    $(document).ready(function () {
        $.get({
            url: "${pageContext.request.contextPath}/servlet/dataServlet",
            success: function (data) {
                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '丑锅的总集资'
                    },
                    tooltip: {},
                    legend: {
                        data: ['钱']
                    },
                    xAxis: {
                        data: ["${dataModel0.title}", "${dataModel1.title}", "${dataModel2.title}", "${dataModel3.title}", "${dataModel4.title}"]
                    },
                    yAxis: {},
                    series: [{
                        name: '钱',
                        type: 'bar',
                        data: [parseFloat("${dataModel0.totalMoney}"), parseFloat("${dataModel1.totalMoney}"), parseFloat("${dataModel2.totalMoney}"), parseFloat("${dataModel3.totalMoney}"), parseFloat("${dataModel4.totalMoney}")]
                    }]
                };
                // alert("数据：" + data + "\n状态：" + status);
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('main'));

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }
        });
    });
</script>
</body>
</html>
