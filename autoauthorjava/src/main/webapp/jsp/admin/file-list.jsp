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
                    <th>Название</th>
                    <th>Автор</th>
                    <th>Группа</th>
                    <th>Действие</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${files}" var="file">
                    <tr>
                        <td>${file.getName()}</td>
                        <td>${file.getAuthorName()}</td>
                        <td>${file.getGroup()}</td>
                        <td><a href="controller?command=delete_file&delete_file_id=${file.getId()}"> <img src="../../images/close.png" alt=""></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <label>${messageAboutTraining}</label>
            <a class="second-button margin-top db" href="controller?command=retrain" value="Переобучить"> Переобучить </a>

        </div>

    </div>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
