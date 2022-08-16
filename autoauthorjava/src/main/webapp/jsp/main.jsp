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
    <div class="container">

        <div class="articles margin-top">
            <h1>Определение автора</h1>

<%--            <div class="field__wrapper">--%>
<%--                <input name="file" type="file" name="file" id="field__file" class="field field__file" multiple>--%>

<%--                <label class="field__file-wrapper" for="field__file">--%>
<%--                    <div class="field__file-fake">Нет выбранных фалов</div>--%>
<%--                    <div class="field__file-button">Выбрать</div>--%>
<%--                </label>--%>
<%--            </div>--%>



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
                <label>${errorAddUserMessage} </label>

                <br>
                <input id="preloader_button" class="second-button margin-top db" type="submit" value="Определить">
            </form>


            <div class="preloader" id="preloader_back" style="display: none">
                <h2 class="result">Определение автора в процессе, пожалуйста, подождите</h2>
                <svg class="preloader__image" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
                    <path fill="currentColor"
                          d="M304 48c0 26.51-21.49 48-48 48s-48-21.49-48-48 21.49-48 48-48 48 21.49 48 48zm-48 368c-26.51 0-48 21.49-48 48s21.49 48 48 48 48-21.49 48-48-21.49-48-48-48zm208-208c-26.51 0-48 21.49-48 48s21.49 48 48 48 48-21.49 48-48-21.49-48-48-48zM96 256c0-26.51-21.49-48-48-48S0 229.49 0 256s21.49 48 48 48 48-21.49 48-48zm12.922 99.078c-26.51 0-48 21.49-48 48s21.49 48 48 48 48-21.49 48-48c0-26.509-21.491-48-48-48zm294.156 0c-26.51 0-48 21.49-48 48s21.49 48 48 48 48-21.49 48-48c0-26.509-21.49-48-48-48zM108.922 60.922c-26.51 0-48 21.49-48 48s21.49 48 48 48 48-21.49 48-48-21.491-48-48-48z">
                    </path>
                </svg>
            </div>

            <script>
                document.getElementById('preloader_button').onclick = function() {
                    document.getElementById('preloader_back').style.display = 'block';
                };
            </script>
            <script src="../js/main.js" charset="UTF-8"></script>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>