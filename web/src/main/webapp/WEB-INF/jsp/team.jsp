<%--
  Created by IntelliJ IDEA.
  User: chen_
  Date: 2018/4/26
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <name>全团集资情况</name>
    <script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
        <div id="main" style="width: 1600px;height:600px;"></div>
    </div>
</div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '全团集资情况',
            subtext: '抓取饺子榜',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ["${data0.xm}", "${data1.xm}", "${data2.xm}", "${data3.xm}", "${data4.xm}", "${data5.xm}", "${data6.xm}", "${data7.xm}", "${data8.xm}", "${data9.xm}", "${data10.xm}", "${data11.xm}", "${data12.xm}", "${data13.xm}"]
        },
        series: [
            {
                name: '全团集资情况',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: [
                    {value: parseFloat("${data0.je}"), name: "${data0.xm}"},
                    {value: parseFloat("${data1.je}"), name: "${data1.xm}"},
                    {value: parseFloat("${data2.je}"), name: "${data2.xm}"},
                    {value: parseFloat("${data3.je}"), name: "${data3.xm}"},
                    {value: parseFloat("${data4.je}"), name: "${data4.xm}"},
                    {value: parseFloat("${data5.je}"), name: "${data5.xm}"},
                    {value: parseFloat("${data6.je}"), name: "${data6.xm}"},
                    {value: parseFloat("${data7.je}"), name: "${data7.xm}"},
                    {value: parseFloat("${data8.je}"), name: "${data8.xm}"},
                    {value: parseFloat("${data9.je}"), name: "${data9.xm}"},
                    {value: parseFloat("${data10.je}"), name: "${data10.xm}"},
                    {value: parseFloat("${data11.je}"), name: "${data11.xm}"},
                    {value: parseFloat("${data12.je}"), name: "${data12.xm}"},
                    {value: parseFloat("${data13.je}"), name: "${data13.xm}"}
                    // {value: 310, name: '邮件营销'},
                    // {value: 234, name: '联盟广告'},
                    // {value: 135, name: '视频广告'},
                    // {value: 1548, name: '搜索引擎'}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</body>
</html>
