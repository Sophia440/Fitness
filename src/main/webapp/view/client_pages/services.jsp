<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="text" var="local" />

<fmt:message bundle="${local}" key="services.choice" var="choice" />
<fmt:message bundle="${local}" key="services.membership" var="membership" />
<fmt:message bundle="${local}" key="services.title" var="title" />

<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Fitness</title>
        <link rel="stylesheet" href="styles/style.css" />
    </head>
    <body>
        <div class="header">
            <jsp:include page="../fragments/header.jsp"/>
        </div>
        <main class="service__main">
            <div class="wrapper">
                <h1 class="service__title">${title}</h1>
                <p class="service__subtitle">${choice}</p>
                <ul id="service__list">
                    <li class="service__item">
                        <a href="${pageContext.request.contextPath}/controller?command=chooseDuration" class="service__item-info">
                            ${membership}
                        </a>
                    </li>
                </ul>
            </div>
        </main>
    </body>
</html>