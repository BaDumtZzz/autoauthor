<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <meta charset="UTF-8"/>
    <title>Welcome</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="content">
    <div class="container">

        <div class="articles margin-top">
            <h1>Определение автора</h1>


            <form name="loginForm" class="margin-top" method="POST" action="controller?command=upload_unknown_file" accept-charset="UTF-8" enctype="multipart/form-data">
                <label>Выберите группу авторов: </label>
                <select class="select" name="new_group" required>
                    <c:forEach items="${groups}" var="group">
                        <option value="${group.getId()}">${group.getName()}</option>
                    </c:forEach>
                </select>

                <input name="fileName" type="file" name="fileName" id="field__file" class="field field__file">

                <label class="field__file-wrapper" for="field__file">
                    <div class="field__file-fake">Нет выбранных фалов</div>
                    <div class="field__file-button">Выбрать</div>
                </label>
                <br>
                <input id="preloader_button" class="second-button margin-top db" type="submit" value="Определить">
            </form>

            <label class="mt" for="file">Определение автора завершено :</label>
            <div class="progress">
                <progress value="100" max="100"></progress>
                <b> 100%</b>
            </div>

            <h2 class="result">Результаты: ${message}</h2>

            <table class="table">
                <thead>
                <tr>
                    <th>Позиция</th>
                    <th>Имя</th>
                    <th>Вероятность</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="author" items="${authorProbabilityMap}" varStatus="status">
                    <tr>
                        <td>1</td>
                        <td><c:out value="${author.key}"/></td>
                        <td><c:out value="${author.value}"/>%</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <script src="../js/main.js"></script>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>