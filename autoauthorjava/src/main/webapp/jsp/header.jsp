<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/custom-functions.tld" prefix="cstm" %>

<header class="header">

    <div class="header__body">
        <c:choose>
            <c:when test="${not (role eq 'admin')}">
                <a href="controller?command=to_main" class="header__logo">
                    <img src="${pageContext.request.contextPath}/images/logo.png" alt="">
                </a>
            </c:when>
            <c:otherwise>
                <a href="controller?command=to_admin" class="header__logo">
                    <img src="${pageContext.request.contextPath}/images/logo.png" alt="">
                </a>
            </c:otherwise>
        </c:choose>
        <nav class="header__menu ml">
            <c:if test="${not (role eq null)}">
            <ul class="header__list">
                <li>
                    <a href="controller?command=to_about" class="header__link">Главная</a>
                </li>
                <li>
                    <a href="controller?command=to_identification" class="header__link">Идентификация</a>
                </li>
            </ul>
            </c:if>
        </nav>

        <nav class="header__menu mla">
            <ul class="header__list">
<%--                <c:if test="${(cstm:contains( role, 'writer' ))||(cstm:contains( role, 'moderator' ))}">--%>
<%--                    <a class="button" href="controller?command=to_add_post" class="logout">New post</a>--%>
<%--                </c:if>--%>
                <li>
                    <a href="controller?command=to_profile" class="header__link">Профиль</a>
                </li>
                <c:if test="${(cstm:contains( role, 'admin' ))}">
                    <li>
                        <a href="controller?command=to_admin" class="header__link">Admin-menu</a>
                    </li>
                </c:if>
                <c:if test="${(cstm:contains( role, 'leader' ))}">
                    <li>
                        <a href="controller?command=to_leader" class="header__link">Leader-menu</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>

</header>