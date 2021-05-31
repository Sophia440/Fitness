<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="text" var="local" />

<fmt:message bundle="${local}" key="account.button.submit" var="submit" />
<fmt:message bundle="${local}" key="account.delete.exercise.title" var="title" />

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
                <p class="actions__title">${title}</p>
                <form action="${pageContext.request.contextPath}/controller?command=adminActions&action=deleteExercise" method="post">
                    <c:if test="${not empty allExercises}">
                        <c:forEach items="${allExercises}" var="exercise">
                            <input type="radio" name="exerciseToDelete" value="${exercise.id}" class="actions-form__radio">
                            <label class="actions-form__select-radio">${exercise.name}</label><br>
                        </c:forEach>
                    </c:if>
                    <div>
                        <input type="submit" value="${submit}" class="actions-form__submit">
                    </div>
                </form>
            </div>
        </main>
    </body>
</html>