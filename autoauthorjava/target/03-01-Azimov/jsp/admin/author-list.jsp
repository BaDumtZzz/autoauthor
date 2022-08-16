<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="users" class="ru.rsreu.autoauthor.dao.oracle.OracleUserDao" scope="request"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/custom-functions.tld" prefix="cstm" %>


<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css"/>
    <title>Welcome</title>

</head>
<body>
<jsp:include page="../header.jsp"/>
<jsp:include page="../header.jsp"/>
<c:choose>
    <c:when test="${(cstm:contains( role, 'admin' ))}">
        <jsp:include page="sidenav-admin.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="../leader/sidenav-leader.jsp"/>
    </c:otherwise>
</c:choose>
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
                <c:forEach items="${authors}" var="author">
                    <tr>
                        <td>${author.getName()}</td>
                        <td>${author.getGroup()}</td>
                        <td><a href="controller?command=delete_author&delete_author_id=${author.getId()}"> <img src="../../images/close.png" alt=""></a>
                            <a href="controller?command=to_change_group"> <img src="../../images/pen.png" alt=""> </a>
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
