<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <title>Login</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="content">
    <div class="container">
        <div class="profile">
            <div class="column">
            <form name="loginForm" method="POST" action="controller">
                <input type="hidden" name="command" value="login"/>
                Логин:<br/>
                <input type="text" name="login" value="" placeholder="Введите логин"/>
                <br/>Пароль:<br/>
                <input type="password" name="password" value="" placeholder="Введите пароль"/>
                <br/>
                <p class="error">${errorLoginPassMessage}${wrongAction}${nullPage}</p>
                <h1> ${testString} </h1>
                <input class="button_login" type="submit" value="Логин"/>
            </form>
            <form name="loginForm" method="POST" action="controller?command=to_registration">
                <input class="button_login" type="submit" value="Зарегистрироваться"/>
            </form>
            </div>
        </div>
    </div>
</div>
<hr/>
<jsp:include page="footer.jsp"/>
</body>
</html>