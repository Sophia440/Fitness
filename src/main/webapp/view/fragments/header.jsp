<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html lang="${language}">

    <head>
        <link rel="stylesheet" href="static/style.css">
    </head>

    <body>
        <header class="header">
            <div class="wrapper">
                <div class="header__wrapper">
                    <div class="header__logo">
                        <a href="/" class="header__logo-link">
                            <p>Fitness</p>
                        </a>
                    </div>

                    <nav class="header__nav">
                        <ul class="header__list">
                            <li class="header__item">
                                <a href="${pageContext.request.contextPath}/controller?command=account" class="header__link">
                                    <fmt:message key="header.account" />
                                </a>
                            </li>
                            <li class="header__item">
                                <a href="#!" class="header__link">
                                    <fmt:message key="header.trainers" />
                                </a>
                            </li>
                            <li class="header__item">
                                <a href="#!" class="header__link">
                                    <fmt:message key="header.services" />
                                </a>
                            </li>
                            <li class="header__item">
                                <a href="#!" class="header__link">
                                    <fmt:message key="header.about" />
                                </a>
                            </li>
                        </ul>
                    </nav>

                    <div class="header__language">
                        <button class="header__language-dropbtn">
                            <fmt:message key="header.language" />
                        </button>
                        <div class="header__language-content">
                            <a href="${pageContext.servletContext.contextPath}?language=en" class="header__language-link">EN</a>
                            <a href="${pageContext.servletContext.contextPath}?language=ru" class="header__language-link">RU</a>
                            <a href="${pageContext.servletContext.contextPath}?language=by" class="header__language-link">BY</a>
                        </div>
                    </div>

                    <div class="header__language">
                        <a href="${pageContext.request.contextPath}/controller?command=logout">
                            <button class="header__language-dropbtn">
                                <fmt:message key="header.logout" />
                            </button>
                        </a>
                    </div>

                </div>
            </div>
        </header>
    </body>
</html>