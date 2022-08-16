<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="sidenav">
    <a href="controller?command=to_main">All</a>

    <c:forEach items="${groups}" var="category">
        <a class="category" href="controller?command=to_posts_by_category&category=${category}">${category}</a>
    </c:forEach>
</div>