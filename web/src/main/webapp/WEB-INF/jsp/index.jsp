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
    <name>源推前十榜：</name>
    <script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
        <div id="main" style="width: 1400px;height:600px;"></div>
    </div>
</div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        name: {
            text: '源推集资前十'
        },
        tooltip: {},
        legend: {
            data: ['金额']
        },
        xAxis: {
            data: ["${dataModel0.name}", "${dataModel1.name}", "${dataModel2.name}", "${dataModel3.name}", "${dataModel4.name}", "${dataModel5.name}", "${dataModel6.name}", "${dataModel7.name}", "${dataModel8.name}", "${dataModel9.name}"]
        },
        yAxis: {},
        series: [{
            name: '金额',
            type: 'bar',
            data: [parseFloat("${dataModel0.money}"), parseFloat("${dataModel1.money}"), parseFloat("${dataModel2.money}"), parseFloat("${dataModel3.money}"), parseFloat("${dataModel4.money}"), parseFloat("${dataModel5.money}"), parseFloat("${dataModel6.money}"), parseFloat("${dataModel7.money}"), parseFloat("${dataModel8.money}"), parseFloat("${dataModel9.money}")]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</body>
</html>
