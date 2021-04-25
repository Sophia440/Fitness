<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="text" var="local" />

<fmt:message bundle="${local}" key="error" var="error" />
<fmt:message bundle="${local}" key="header.language" var="language" />

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fitness</title>
    <link rel="stylesheet" href="static/style.css" />
</head>

<html>
    <body>
        <div class="header">
            <header class="header">
                <div class="wrapper">
                    <div class="header__wrapper">
                        <div class="header__logo">
                            <a href="${pageContext.request.contextPath}/controller?command=logout" class="header__logo-link">
                                <p>Fitness</p>
                            </a>
                        </div>
                        <div class="header__language">
                            <button class="header__language-dropbtn">${language}</button>
                            <div class="header__language-content">
                                <a href="${pageContext.servletContext.contextPath}?command=changeLanguage&language=en" class="header__language-link">EN</a>
                                <a href="${pageContext.servletContext.contextPath}?command=changeLanguage&language=ru" class="header__language-link">RU</a>
                                <a href="${pageContext.servletContext.contextPath}?command=changeLanguage&language=by" class="header__language-link">BY</a>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
        </div>

        <main class="main">
            <div class="wrapper">
                <h1 class="error__title">${error}</h1>
                <p class="error__subtitle">${errorMessage}</p>
            </div>
        </main>
    </body>
</html>