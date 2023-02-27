<%--
  Created by IntelliJ IDEA.
  User: GZC
  Date: 2022/8/9
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的订单详情</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <link type="text/css" rel="stylesheet" href="static/css/style.css">
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">

    <a href="index.jsp">
        <img class="logo_img" alt="" src="static/img/logo.gif" width="230px" height="80px">
    </a>
    <span class="wel_word">我的订单详情</span>
    <div>
        <c:if test="${empty user}">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        </c:if>
        <c:if test="${not empty user}">
            <span>欢迎<span class="um_span">${user.username}</span>光临网上书城</span>
            <a href="OrderServlet?action=listOrder">我的订单</a>
            <a href="UserServlet?action=logout">注销</a>&nbsp;&nbsp;
        </c:if>
        <a href="index.jsp">返回</a>
    </div>
</div>

<div id="main">

    <table>
        <tr>
            <td>书名</td>
            <td>件数</td>
            <td>价格</td>
            <td>总金额</td>
            <td>订单号</td>
        </tr>

        <c:if test="${empty requestScope.page}">
            <%-- 如果商品订单空的情况 --%>
            <tr>
                <td colspan="5">
                    <a href="index.jsp">亲,当前商品订单详情为空!快跟小伙伴们去浏览商品去吧!!!</a>
                </td>
            </tr>
        </c:if>
        <c:if test="${not empty requestScope.page}">
            <%-- 如果商品订单详情非空的情况 --%>
            <c:forEach items="${page.items}" var="orderItem">
                <tr>
                    <td>${orderItem.name}</td>
                    <td>${orderItem.count}</td>
                    <td>${orderItem.price}</td>
                    <td>${orderItem.totalPrice}</td>
                    <td>${orderItem.orderId}</td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>
<div style="margin-top: -80px;">
    <%-- 动态包含 分页跳转 --%>
    <jsp:include page="/pages/common/page.jsp"></jsp:include>
</div>

<%@include file="/pages/common/bottom.jsp" %>
</body>
</html>