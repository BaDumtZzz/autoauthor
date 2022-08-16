<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
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
            <form name="loginForm" method="POST" action="controller">
                <input type="hidden" name="command" value="create_group"/>
                <label>Название</label>
                <input type="text" name="new_group" placeholder="Введите название группы" required>
                <input class="button_login" type="submit" value="Добавить"/>
                <p class="error">${errorAddCategoryMessage}</p>
            </form>
        </div>

    </div>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
