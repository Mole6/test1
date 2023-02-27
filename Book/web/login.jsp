<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理员登录页面</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <link type="text/css" rel="stylesheet" href="static/css/style.css">
</head>
<body>
<div id="login_header">
    <a href="index.jsp">
    <img class="logo_img" alt="" src="static/img/logo.gif" width="230px" height="80px">
    </a>
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎登录后台管理</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>管理员</h1>
                    <%--<a href="pages/user/regist.jsp">立即注册</a>--%>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <span class="errorMsg">
                        <c:out value="${msg}" default="请输入管理员用户名和密码"></c:out>
                    </span>
                </div>
                <div class="form">
                    <form action="AdminServlet" method="post">
                        <input type="hidden" value="login" name="action">
                        <label>管理员名称：</label>
                        <input value="${adminName}" class="itxt" type="text" placeholder="请输入管理员名称" autocomplete="off" tabindex="1"
                               name="adminName"/>
                        <br/>
                        <br/>
                        <label>管理员密码：</label>
                        <input value="${password}" class="itxt" type="password" placeholder="请输入管理员密码" autocomplete="off" tabindex="1"
                               name="password"/>
                        <br/>
                        <br/>
                        <input type="submit" value="登录" id="sub_btn"/>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%@ include file="/pages/common/bottom.jsp" %>
</body>
</html>