<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="text" var="local" />

<fmt:message bundle="${local}" key="account.choose.exercises" var="chooseExercises" />
<fmt:message bundle="${local}" key="account.start.date" var="start" />
<fmt:message bundle="${local}" key="account.end.date" var="end" />
<fmt:message bundle="${local}" key="account.add" var="add" />

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
        <main class="actions__main">
            <div class="wrapper">
                <form action="${pageContext.request.contextPath}/controller?command=instructorActions&action=addProgram" method="post">
                    <p class="actions__title">${chooseExercises}</p>
                    <c:if test="${not empty allExercises}">
                        <c:forEach items="${allExercises}" var="exercise">
                            <input type="checkbox" name="programExercisesList" value="${exercise.id}" class="actions-form__radio">
                            <label class="actions-form__select-radio">${exercise.name}</label><br>
                        </c:forEach>
                    </c:if>
                    <br><br>
                    <ul class="account__half-list">
                        <li class="account__first-half">
                            <label class="actions-form__select-label" for="programStartDate">${start}:</label>
                            <input class="actions-form__date" type="date" id="programStartDate" name="programStartDate">
                        </li>
                        <li>
                            <label class="actions-form__select-label" for="programEndDate">${end}:</label>
                            <input class="actions-form__date" type="date" id="programEndDate" name="programEndDate">
                        </li>
                    </ul>
                    <br><br>
                    <div>
                        <button type="submit" class="login-form__submit" value="submit">${add}</button>
                    </div>
                </form>
            </div>
        </main>
    </body>
</html>