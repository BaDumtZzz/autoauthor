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
<jsp:include page="sidenav-leader.jsp"/>
<div class="content">
    <div class="container">>
        <div class="articles">
            <table class="table">
                <thead>
                <tr>
                    <th>Имя</th>
                    <th>Группа</th>
                    <th>Действие</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.getNickname()}</td>
                        <td>${user.getSingleGroupName()}</td>
                        <td>

                            <c:if test="${not (user.getRole() eq 'leader')}">
                                <a href="controller?command=kick_user&kick_user_id=${user.getId()}&kick_group_id=${user.getGroupId()}"> <img src="../../images/close.png" alt=""></a>
                            </c:if>
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
