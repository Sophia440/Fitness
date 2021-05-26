<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="text" var="local" />

<fmt:message bundle="${local}" key="info.exercisesTitle" var="exercisesTitle" />
<fmt:message bundle="${local}" key="info.of" var="of" />

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
            <c:if test="${not empty exerciseSublist}">
                <c:forEach items="${exerciseSublist}" var="exercise">
                    <li class="info__item">${exercise.name}</li>
                </c:forEach>
            </c:if>
        </ul>
        <form action="${pageContext.request.contextPath}/controller?command=info&section=exercisesChangePage" method="post">
            <input type="hidden" name="firstRow" value="${firstRow}">
            <input type="hidden" name="rowCount" value="${rowCount}">
            <input type="hidden" name="pageCount" value="${pageCount}">
            <div class="info-form">
                <c:if test="${pageCount != 1}">
                    <button type="submit" class="info__form-previous" name="page" value="previous"><-</button>
                </c:if>
                <p class="info__form-text">${pageCount} ${of} ${totalExercisePageCount}</p>
                <c:if test="${pageCount < totalExercisePageCount}">
                    <button type="submit" class="info__form-next" name="page" value="next">-></button>
                </c:if>
            </div>
        </form>
    </div>
</main>
</body>
</html>