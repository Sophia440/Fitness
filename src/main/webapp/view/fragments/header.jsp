<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="text" var="local" />

<fmt:message bundle="${local}" key="header.account" var="account" />
<fmt:message bundle="${local}" key="account.membership" var="membership" />
<fmt:message bundle="${local}" key="account.program" var="program" />
<fmt:message bundle="${local}" key="account.diet" var="diet" />
<fmt:message bundle="${local}" key="header.information" var="information" />
<fmt:message bundle="${local}" key="info.exercisesTitle" var="exercises" />
<fmt:message bundle="${local}" key="info.dishesTitle" var="dishes" />
<fmt:message bundle="${local}" key="header.services" var="services" />
<fmt:message bundle="${local}" key="header.about" var="about" />
<fmt:message bundle="${local}" key="header.language" var="language" />
<fmt:message bundle="${local}" key="header.logout" var="logout" />

<html>
    <head>
        <link rel="stylesheet" href="styles/style.css">
    </head>
    <body>
        <header class="header">
            <div class="wrapper">
                <div class="header__wrapper">
                    <div class="header__logo">
                        <c:if test="${role=='CLIENT'}">
                            <a href="${pageContext.request.contextPath}/controller?command=clientMain" class="header__logo-link">
                                <p>Fitness</p>
                            </a>
                        </c:if>
                        <c:if test="${role=='ADMIN'}">
                            <a href="${pageContext.request.contextPath}/controller?command=adminMain" class="header__logo-link">
                                <p>Fitness</p>
                            </a>
                        </c:if>
                        <c:if test="${role=='INSTRUCTOR'}">
                            <a href="${pageContext.request.contextPath}/controller?command=instructorMain" class="header__logo-link">
                                <p>Fitness</p>
                            </a>
                        </c:if>
                    </div>
                    <nav class="header__nav">
                    <ul class="header__list">
                        <li class="dropdown">
                            <c:if test="${role=='CLIENT'}">
                                <button class="dropbtn">${account}</button>
                                <div class="dropdown-content">
                                    <a href="${pageContext.request.contextPath}/controller?command=clientAccount&page=membership">${membership}</a>
                                    <a href="${pageContext.request.contextPath}/controller?command=clientAccount&page=program">${program}</a>
                                    <a href="${pageContext.request.contextPath}/controller?command=clientAccount&page=diet">${diet}</a>
                                </div>
                            </c:if>
                            <c:if test="${role=='ADMIN'}">
                            <button class="dropbtn">
                                <a href="${pageContext.request.contextPath}/controller?command=adminAccount" class="header__link">
                                    ${account}
                                </a>
                            </button>    
                            </c:if>
                            <c:if test="${role=='INSTRUCTOR'}">
                            <button class="dropbtn">
                                <div class="dropdown-content"></div>
                                <a href="${pageContext.request.contextPath}/controller?command=instructorAccount" class="header__link">
                                        ${account}
                                </a>
                            </button>    
                            </c:if>
                        </li>
                        <li class="dropdown">
                            <button class="dropbtn">${information}</button>
                            <div class="dropdown-content">
                                <a href="${pageContext.request.contextPath}/controller?command=info&section=exercisesFirst">${exercises}</a>
                                <a href="${pageContext.request.contextPath}/controller?command=info&section=dishesFirst">${dishes}</a>
                            </div>
                        </li>
                        <li class="dropdown">
                            <c:if test="${role=='CLIENT'}">
                                <button class="dropbtn">
                                    <a href="${pageContext.request.contextPath}/controller?command=services" class="header__link">
                                        ${services}
                                    </a>
                                </button>
                            </c:if>
                        </li>
                        <li class="dropdown">
                            <button class="dropbtn">
                                <a href="${pageContext.request.contextPath}/controller?command=about"  class="header__link">
                                    ${about}
                                </a>
                            </button>
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