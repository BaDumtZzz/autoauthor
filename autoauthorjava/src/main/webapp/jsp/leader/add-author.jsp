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
            <form name="loginForm" method="POST" action="controller" accept-charset="UTF-8">
                <input type="hidden" name="command" value="add_author"/>
                <label>Введите имя автора</label>
                <input type="text" name="new_nickname" placeholder="Введите имя автора" required>
                <label>Группа </label>
                <select name="new_group">
                    <p>Выберите группу: </p>
                    <c:forEach items="${groups}" var="group">
                        <option value="${group.getId()}">${group.getName()}</option>
                    </c:forEach>
                </select>
                <input class="button_login" type="submit" value="Добавить"/>
                <p class="error">${errorLoginPassMessage}${wrongAction}${nullPage}</p>
                <p class="error">${errorAddCategoryMessage}</p>
            </form>
        </div>

    </div>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
