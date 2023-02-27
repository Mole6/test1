<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑图书</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <link type="text/css" rel="stylesheet" href="static/css/style.css">
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }

        input {
            text-align: center;
        }

        td.check {
            color: #C00;
            font-weight: bold;
        }
    </style>

</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
%>
<jsp:include page="header.jsp">
    <jsp:param name="msg" value="编辑图书"/>
</jsp:include>

<div id="main">
    <form action="BookServlet?action=update&id=${book.id}&pageNo=${pageNo}" enctype="multipart/form-data" method="post"
          onsubmit="return checkForm()">
        <!-- 主键id -->
        <input name="id" type="hidden" value="${requestScope.book.id}"/>
       <%-- <input name="pageNo" type="hidden" value="${pageNo}">--%>
        <!-- 图片源地址 -->
        <%-- <input name="oldPath" type="hidden" value="${requestScope.book.imgPath}"/>--%>
        <table>
            <tr>
                <td>名称</td>
                <td><input name="name" type="text" value="${book.name}"/></td>
            </tr>
            <tr>
                <td>价格</td>
                <td><input id="price" name="price" type="text" value="${book.price}"/></td>
            </tr>
            <tr>
                <td>作者</td>
                <td><input name="author" type="text" value="${book.author}"/></td>
            </tr>
            <tr>
                <td>销量</td>
                <td><input id="sales" name="sales" type="text" value="${book.sales}"/></td>
            </tr>
            <tr>
                <td>库存</td>
                <td><input id="stock" name="stock" type="text" value="${book.stock}"/></td>
            </tr>
            <tr>
                <td>封面</td>
                <td>
                    <input name="imgPath" type="file" id="file_input" onchange="show_image()"/>
                    <img src="${book.imgPath}" alt="" id="show_img" width="100px" height="100px"/></span>
                </td>
            </tr>
            <%--id="file_input" onchange="show_image()"--%>
            <tr>
                <td colspan="2"><input type="submit" value="提交"/></td>
            </tr>
        </table>
    </form>


</div>

<%--		<%@include file="/pages/common/bottom.jsp" %>--%>
<jsp:include page="/pages/common/bottom.jsp"></jsp:include>
</body>
</html>
<script>
    function show_image() {
        //抓取到上传图片的input标签的信息
        file_input = document.getElementById("file_input");
        //抓取到需要展示预览图的img标签信息
        show_img = document.getElementById("show_img");
        //回去预览图的src属性信息
        show_img.src = window.URL.createObjectURL(file_input.files[0]);
        //改变style属性中block的值
        show_img.style.display = 'block';
    }


    /*window.onload = function () {
        alert(priceObjText);
        alert(salesObjText);
        alert(stockObjText);
    }*/
    function checkForm() {
        priceObj = document.getElementById("price");
        priceObjText = priceObj.value;
        salesObj = document.getElementById("sales");
        salesObjText = salesObj.value;
        stockObj = document.getElementById("stock");
        stockObjText = stockObj.value;

        price = /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/;
        int = /^[+]{0,1}(\d+)$/;
        if (!price.test(priceObjText)) {
            alert("价格格式错误！！！应为正数");
            return false;
        }
        if (!int.test(salesObjText)) {
            alert("销量格式错误！！！应为正整数");
            return false;
        }
        if (!int.test(stockObjText)) {
            alert("库存量格式错误！！！应为正整数");
            return false;
        }
        return true;
    }

</script>