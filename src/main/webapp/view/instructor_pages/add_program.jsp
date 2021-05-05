<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="text" var="local" />

<fmt:message bundle="${local}" key="main.hello" var="hello" />

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
    <jsp:include page="../fragments/header.jsp"/>
</div>

<main class="main">
    <div class="wrapper">
        <form action="${pageContext.request.contextPath}/controller?command=instructorActions&action=addProgram" method="post">
            <p class="actions__subtitle">Choose exercises to add:</p>
            <c:if test="${not empty allExercises}">
                <c:forEach items="${allExercises}" var="exercise">
                    <input type="checkbox" name="programExercisesList" value="${exercise.id}">
                    <label class="actions-form__select-radio">${exercise.name}</label><br>
                </c:forEach>
            </c:if>
            <br><br>
            <p class="actions__subtitle">Choose client to add program:</p>
            <c:if test="${not empty allClients}">
                <c:forEach items="${allClients}" var="client">
                    <input type="radio" name="clientToAddProgram" value="${client.id}">
                    <label class="actions-form__select-radio">${client.login}</label><br>
                </c:forEach>
            </c:if>
            <div>
                <button type="submit" class="login-form__submit" value="submit">Add</button>
            </div>
        </form>
    </div>

</main>
</body>
</html>