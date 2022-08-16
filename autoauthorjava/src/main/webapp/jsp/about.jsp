<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <meta charset="UTF-8" />
    <title>Welcome</title>
</head>
<body>
<jsp:include page="header.jsp"/>


<<div class="content">
    <div class="wide-container">
        <div class="about">
            Вашему вниманию предлагается программа АвтоАвтор. <br>
            На данной странице вам предлагается воспользоваться возможностью автоматического определения авторства текста при помощи нейросетей.<br>
            Программа анализирует текст и выдаёт наиболее подходящих авторов.<br>
            Для начала перейдите на вкладку «Идентификация».<br>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>