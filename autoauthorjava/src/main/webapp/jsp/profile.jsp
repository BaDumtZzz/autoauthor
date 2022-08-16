<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Personal account</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="content">
    <div class="container" style="padding-bottom: 20vh;">
        <div style="margin: 90px 90px 20px 30px;" class="item">
            <div class="item__img"><img src="${pageContext.request.contextPath}/images/avatar.png" alt=""></div>
            <div style="margin-top: 40px;" class="item__parametrs">
                <ul class="item__parametrs_list">
                    <li>
                        <p class="item__parametr">Имя: ${nickname}</p>
                    </li>
                    <li>
                        <p class="item__parametr">E-mail: ${email}</p>
                    </li>
                    <c:forEach begin="0" end="${role.size()-1}" step="1" varStatus="loop">
                        <li>
                            <p class="item__parametr">Роль: ${role.get(loop.current)}</p>
                        </li>
                        <li>
                            <p class="item__parametr">Организация: ${groupName.get(loop.current)}</p>
                        </li>
                    </c:forEach>

                    <li>
                        <a class="button" href="controller?command=logout" class="logout">Выход</a>
                    </li>

                </ul>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>