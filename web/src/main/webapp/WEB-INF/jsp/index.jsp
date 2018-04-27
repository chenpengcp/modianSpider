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
    <title>丑锅的集资</title>
    <script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 1800px;height:700px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

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

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</body>
</html>
