<%@ page import="com.softeem.bean.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <link type="text/css" rel="stylesheet" href="static/css/style.css">

    <%--	<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">--%>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" width="230px" height="80px">
    <span class="wel_word">网上书城</span>
    <div>
        <c:if test="${empty user}">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        </c:if>
        <c:if test="${not empty user}">
            <span>欢迎<span class="um_span">${user.username}</span>光临书城</span>
            <a href="OrderServlet?action=listOrder">我的订单</a>
            <a href="UserServlet?action=logout">注销</a>
        </c:if>
        <a href="pages/cart/cart.jsp">购物车</a>
        <a href="login.jsp">后台管理</a>
    </div>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="BookServlet?action=searchPage" method="post">
                图书名称：<input id="name" type="text" name="name" value="${name}">&emsp;
                图书作者：<input id="author" type="text" name="author" value="${author}">&emsp;
                价格：<input id="min" type="text" name="min" value="${min}"> 元 -
                <input id="max" type="text" name="max" value="${max}"> 元
                <input type="submit" value="查询"/>
            </form>
        </div>
        <c:choose>
            <c:when test="${empty page.items}">
                <div>&nbsp</div>
                <div>&nbsp</div>
                <div>&nbsp</div>
                <div>&nbsp</div>
                <div>&nbsp</div>
                <div>
                    <h1 style="color: red" align="center">未查到图书信息！！！</h1>
                </div>
            </c:when>
            <c:otherwise>
                <div style="text-align: center">
                    <c:if test="${empty user}">
                        <span> </span>
                        <div>
                            <span style="color: red">请登录您的账号！</span>
                        </div>
                    </c:if>
                    <c:if test="${not empty user}">
                        <c:if test="${empty sessionScope.cart.items}">
                            <%--购物车为空的输出--%>
                            <span> </span>
                            <div>
                                <span style="color: red">当前购物车为空</span>
                            </div>
                        </c:if>
                        <c:if test="${not empty sessionScope.cart.items}">
                            <%--购物车非空的输出--%>
                            <span>您的购物车中有 ${sessionScope.cart.totalCount} 件商品</span>
                            <div>
                                您刚刚将<span style="color: red">${sessionScope.lastname}</span>加入到了购物车中
                            </div>
                        </c:if>
                    </c:if>
                </div>
                <c:forEach items="${page.items}" var="book">
                    <div class="b_list">
                        <div class="img_div">
                            <img class="book_img" alt="" src="${book.imgPath}"/>
                        </div>
                        <div class="book_info">
                            <div class="book_name">
                                <span class="sp1">书名:</span>
                                <span class="sp2">${book.name}</span>
                            </div>
                            <div class="book_author">
                                <span class="sp1">作者:</span>
                                <span class="sp2">${book.author}</span>
                            </div>
                            <div class="book_price">
                                <span class="sp1">价格:</span>
                                <span class="sp2">￥${book.price}</span>
                            </div>
                            <div class="book_sales">
                                <span class="sp1">销量:</span>
                                <span class="sp2">${book.sales}</span>
                            </div>
                            <div class="book_amount">
                                <span class="sp1">库存:</span>
                                <span class="sp2">${book.stock}</span>
                            </div>
                            <div class="book_add">
                                <input onclick="addToCart('${book.id}')" type="button" value="加入购物车">
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
    <jsp:include page="/pages/common/page.jsp"></jsp:include>
</div>

<%--静态包含--%>
<%@include file="/pages/common/bottom.jsp" %>
</body>
</html>

<script>
    function addToCart(bookId) {
        if ('${sessionScope.user}' != '') {
            window.location.href = "CartServlet?action=addItem&id=" + bookId
        }else {
            alert("请先登录在加入购物车！！！")
        }
    }
</script>