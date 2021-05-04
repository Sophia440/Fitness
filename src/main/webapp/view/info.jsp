<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="text" var="local" />

<fmt:message bundle="${local}" key="info.exercisesTitle" var="exercisesTitle" />
<fmt:message bundle="${local}" key="info.dishesTitle" var="dishesTitle" />

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fitness</title>
    <link rel="stylesheet" href="static/style.css" />
</head>
<body>
<div class="header">
    <jsp:include page="fragments/header.jsp"/>
</div>

<main class="info__main">
    <div class="wrapper">

        <h1 class="info__title">${exercisesTitle}</h1>
        <ul class="info__list">
            <c:if test="${not empty allExercises}">
                <c:forEach items="${allExercises}" var="exercise">
                <li class="trainers__item">${exercise.name}</li>
                </c:forEach>
            </c:if>
        </ul>

        <h1 class="info__title">${dishesTitle}</h1>
        <ul class="info__list">
            <c:if test="${not empty allDishes}">
                <c:forEach items="${allDishes}" var="dish">
                    <li class="trainers__item">${dish.name}</li>
                </c:forEach>
            </c:if>
        </ul>
    </div>
</main>
</body>
</html>