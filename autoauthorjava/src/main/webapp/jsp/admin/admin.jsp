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
        <div class="articles">
            <table class="table">
                <thead>
                <tr>
                    <th>Имя</th>
                    <th>Группа</th>
                    <th>Email</th>
                    <th>Роль</th>
                    <th>Статус</th>
                    <th>Действие</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.getNickname()}</td>
                        <td>${user.getGroupName()}</td>
                        <td>${user.getEmail()}</td>
                        <td>${user.getRole()}</td>
                        <td>${user.getStatus()}</td>
                        <td>

                            <c:if test="${not (user.getRole() eq 'admin')}">
                                <a href="controller?command=delete_user&delete_user_id=${user.getId()}"> <img src="../../images/close.png" alt=""></a>
                            </c:if>
                            <c:choose>
                                <c:when test="${(user.getStatus() eq 'blocked')}">
                                    <a href="controller?command=unlock_user&unlock_user_id=${user.getId()}"> <img
                                            src="../../images/unlock.png" alt=""> </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="controller?command=block_user&block_user_id=${user.getId()}"> <img
                                            src="../../images/lock.png" alt=""></a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>


                </tbody>
            </table>
        </div>

    </div>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
