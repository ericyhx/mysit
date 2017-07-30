<%--
  Created by IntelliJ IDEA.
  User: dasun
  Date: 2017/7/26
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    ${sessionScope.user}:恭喜你登录成功;<a href="login?action=logout"> 注销</a>
</body>
</html>
