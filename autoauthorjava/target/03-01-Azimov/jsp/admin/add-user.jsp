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
<jsp:include page="sidenav-admin.jsp"/>
<div class="content">
    <div class="container">>
        <div class="profile">
            <form name="loginForm" method="POST" action="controller">
                <input type="hidden" name="command" value="add_user"/>
                <label>Логин</label>
                <input type="text" name="new_nickname" placeholder="Enter your login" required>
                <label>Организация</label>
                <div class="product__select">
                    <select name="new_role">
                        <p>Выберите организацию: </p>
                        <c:forEach items="${groups}" var="group">
                            <option value="${group.getId()}">${group.getName()}</option>
                        </c:forEach>
                    </select>
                </div>
                <label>E-mail</label>
                <input type="email" name="new_email" placeholder="Введите свой email" required>
                <label>Роль</label>
                <div class="product__select">
                <select name="new_role">
                    <p>Выберите роль: </p>
                    <c:forEach items="${roles}" var="role">
                        <option value="${role}">${role}</option>
                    </c:forEach>
                </select>
                </div>
                <label>Пароль</label>
                <input type="password" name="new_password" placeholder="Введите пароль" required>
                <label>Подтвердите пароль</label>
                <input type="password" name="new_password_confirm" placeholder="Повторите пароль" required>

                <p class="error">${errorAddUserMessage}${wrongAction}${nullPage}</p>
                <h1> ${testString} </h1>
                <input class="button_login" type="submit" value="Добавить"/>
            </form>
        </div>

    </div>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
