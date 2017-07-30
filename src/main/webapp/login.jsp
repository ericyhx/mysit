
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String username= (String) session.getAttribute("user");
    if(username!=null){
        %>
        您已经登录，用户名是<%=username%>,直接<a href="success.jsp"> 进入</a>或<a href="login?action=logout"> 注销</a>;
<%
        return;
    }
%>
    <form action="login" method="post">
        用户名：<input type="text" name="username"><br>
        密  码：<input type="password" name="password"><br>
        <input type="checkbox" name="autoLogin">自动登录
        <input type="submit">

    </form>
</body>
</html>
