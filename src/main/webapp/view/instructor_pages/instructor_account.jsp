<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="text" var="local" />

<fmt:message bundle="${local}" key="account.title" var="title" />
<fmt:message bundle="${local}" key="account.membership" var="membership" />
<fmt:message bundle="${local}" key="account.program" var="program" />
<fmt:message bundle="${local}" key="account.diet" var="diet" />
<fmt:message bundle="${local}" key="account.info.membership" var="membershipInfo" />
<fmt:message bundle="${local}" key="account.info.program" var="programInfo" />
<fmt:message bundle="${local}" key="account.info.diet" var="dietInfo" />
<fmt:message bundle="${local}" key="account.membership.end" var="membershipEndInfo" />
<fmt:message bundle="${local}" key="account.button.accept" var="accept" />
<fmt:message bundle="${local}" key="account.button.decline" var="decline" />

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fitness</title>
    <link rel="stylesheet" href="static/style.css" />
    <script src="view/scripts/account.js"></script>
</head>
<body>
<div class="header">
    <jsp:include page="../fragments/header.jsp"/>
</div>

<main class="account__main">
    <div class="wrapper">
        <h1 class="account__title">${title}</h1>
        <ul id="account__list">
            <li class="account__item">
                <a href="${pageContext.request.contextPath}/controller?command=instructorActions&action=addExerciseForm"
                   class="account__item-info">
                    Add new exercise
                </a>
            </li>
            <li class="account__item">
                <a href="${pageContext.request.contextPath}/controller?command=instructorActions&action=addDishForm"
                   class="account__item-info">
                    Add new dish
                </a>
            </li>
            <li class="account__item">
                <a href="${pageContext.request.contextPath}/controller?command=instructorActions&action=addProgramForm"
                   class="account__item-info">
                    Add new program to a client
                </a>
            </li>
            <li class="account__item">
                <a href="${pageContext.request.contextPath}/controller?command=instructorActions&action=addDietForm"
                   class="account__item-info">
                    Add new diet to a client
                </a>
            </li>
        </ul>
    </div>
</main>
</body>
</html>