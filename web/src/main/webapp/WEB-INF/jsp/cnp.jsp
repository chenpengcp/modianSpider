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
    <title>霸占那丑锅</title>
</head>
<body>
<h3 class="title"><span>霸占那丑锅-丑锅个站</span></h3>
<img src="${pageContext.request.contextPath}/image/cnp.JPG">
<div><input type="submit" name="Submit" value="点击查看丑锅集资"
            style="width: 300px;height: 100px;color: crimson;font-size: larger;font-weight: bold"
            onclick=window.open("${pageContext.request.contextPath}/servlet/dataServlet")>
</div>
<div><input type="submit" name="Submit" value="爬取搜索数据"
            style="width: 300px;height: 100px;color: crimson;font-size: larger;font-weight: bold"
            onclick=window.open("${pageContext.request.contextPath}/servlet/appSearchServlet")>
</div>
</body>
</html>
