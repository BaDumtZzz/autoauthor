<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <title>Login</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="content">
    <div class="container">
        <div class = "profile">
        <form name="loginForm" method="POST" action="controller">
            <input type="hidden" name="command" value="registration"/>
            <label>Логин</label>
            <input type="text" name="new_nickname" placeholder="Enter your login" required>
            <label>E-mail</label>
            <input type="email" name="new_email" placeholder="Введите свой email" required>
            <label>Пароль</label>
            <input type="password" name="new_password" placeholder="Введите пароль" required>
            <label>Подтвердите пароль</label>
            <input type="password" name="new_password_confirm" placeholder="Повторите пароль" required>

            <p class="error">${errorAddUserMessage}${wrongAction}${nullPage}</p>
            <h1> ${testString} </h1>
            <input class="button_login" type="submit" value="Зарегистрироваться"/>
        </form>
        </div>
    </div>
</div>
<hr/>
<jsp:include page="footer.jsp"/>
</body>
</html>