<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css"/>
    <title>Welcome</title>

</head>
<body>
<jsp:include page="../header.jsp"/>
<jsp:include page="../leader/sidenav-leader.jsp"/>
<div class="content">
    <div class="container">>
        <div class="profile">
<%--            <form name="loginForm" method="POST" action="controller" accept-charset="UTF-8">--%>
<%--                <input type="hidden" name="command" value="add_author"/>--%>

    <form name="loginForm" method="POST" action="controller?command=upload_author_file" accept-charset="UTF-8" enctype="multipart/form-data">
        <label>Группа </label>
        <select name="new_author_group_id">
            <p>Выберите группу: </p>
            <c:forEach items="${groups}" var="group">
                <option value="${group.getId()}">${group.getName()}</option>
            </c:forEach>
        </select>
        <label>Введите имя автора</label>
        <input type="text" name="new_author_name" placeholder="Введите имя автора">
        <label style="margin-bottom: 10px">Или</label>
        <label>Выберите автора: </label>
        <select name="selected_author" required>
            <c:forEach items="${authors}" var="author">
                <option value="${author.getId()}">${author.getName()}</option>
            </c:forEach>
        </select>
        Выберите файл для загрузки:
        <input type="file" name="fileName">
        <input class="button_login" type="submit" value="Загрузить"/>
        <p class="error">${errorAddUserMessage}${wrongAction}${nullPage}</p>
        <p class="error">${errorAddCategoryMessage}</p>
    </form>

<%--                <form action="controller" method="post" enctype="multipart/form-data">--%>
<%--                    <input type="hidden" name="command" value="upload_author_file"/>--%>
<%--                    <label>Выберите автора: </label>--%>
<%--                    <select name="new_author" required>--%>
<%--                        <c:forEach items="${authors}" var="author">--%>
<%--                            <option value="${author.getId()}">${author.getName()}</option>--%>
<%--                        </c:forEach>--%>
<%--                    </select>--%>
<%--                    Select File to Upload:--%>
<%--                    <input type="file" name="fileName">--%>
<%--                    <br>--%>
<%--                    <input type="submit" value="Upload">--%>
<%--                </form>--%>


<%--                <input class="button_login" type="submit" value="Добавить"/>--%>
<%--            </form>--%>
        </div>

    </div>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
