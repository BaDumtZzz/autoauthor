<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                        <p class="item__parametr">Name: ${user.getNickname()}</p>
                    </li>
                    <li>
                        <p class="item__parametr">Role: ${user.getRole()}</p>
                    </li>
                    <li>
                        <p class="item__parametr">Date created: ${user.getDateCreated()}</p>
                    </li>

                </ul>
            </div>
        </div>
        <div class="order__title order__basket">Articles: </div>
        He don't has any articles uploaded

    </div>
</div>
</body>
</html>