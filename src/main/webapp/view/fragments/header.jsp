<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="text" var="local" />

<fmt:message bundle="${local}" key="header.account" var="account" />
<fmt:message bundle="${local}" key="header.trainers" var="trainers" />
<fmt:message bundle="${local}" key="header.services" var="services" />
<fmt:message bundle="${local}" key="header.about" var="about" />
<fmt:message bundle="${local}" key="header.language" var="language" />
<fmt:message bundle="${local}" key="header.logout" var="logout" />

<html>

    <head>
        <link rel="stylesheet" href="static/style.css">
    </head>

    <body>
        <header class="header">
            <div class="wrapper">
                <div class="header__wrapper">
                    <div class="header__logo">
                        <a href="${pageContext.request.contextPath}/controller?command=main" class="header__logo-link">
                            <p>Fitness</p>
                        </a>
                    </div>

                    <nav class="header__nav">
                        <ul class="header__list">
                            <li class="header__item">
                                <a href="${pageContext.request.contextPath}/controller?command=account" class="header__link">
                                    ${account}
                                </a>
                            </li>
                            <li class="header__item">
                                <a href="${pageContext.request.contextPath}/controller?command=trainers" class="header__link">
                                    ${trainers}
                                </a>
                            </li>
                            <li class="header__item">
                                <a href="${pageContext.request.contextPath}/controller?command=services" class="header__link">
                                    ${services}
                                </a>
                            </li>
                            <li class="header__item">
                                <a href="${pageContext.request.contextPath}/controller?command=about" class="header__link">
                                    ${about}
                                </a>
                            </li>
                        </ul>
                    </nav>

                    <div class="header__language">
                        <button class="header__language-dropbtn">${language}</button>
                        <div class="header__language-content">
                            <a href="${pageContext.servletContext.contextPath}?command=changeLanguage&language=en" class="header__language-link">EN</a>
                            <a href="${pageContext.servletContext.contextPath}?command=changeLanguage&language=ru" class="header__language-link">RU</a>
                            <a href="${pageContext.servletContext.contextPath}?command=changeLanguage&language=by" class="header__language-link">BY</a>
                        </div>
                    </div>

                    <div class="header__language">
                        <a href="${pageContext.request.contextPath}/controller?command=logout">
                            <button class="header__language-dropbtn">${logout}</button>
                        </a>
                    </div>
                </div>
            </div>
        </header>
    </body>
</html>