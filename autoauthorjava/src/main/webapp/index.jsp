<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Index</title></head>
<body>
<c:choose>
    <c:when test="${empty role}">
        <jsp:forward page="/jsp/login.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:forward page="/jsp/main.jsp?command=to_main"/>
    </c:otherwise>
</c:choose>

</body>
</html>
