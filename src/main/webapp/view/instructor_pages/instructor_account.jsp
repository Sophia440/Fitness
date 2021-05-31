<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="text" var="local" />

<fmt:message bundle="${local}" key="account.title" var="title" />
<fmt:message bundle="${local}" key="account.add.exercise" var="addExercise" />
<fmt:message bundle="${local}" key="account.add.dish" var="addDish" />
<fmt:message bundle="${local}" key="account.add.program" var="addProgram" />
<fmt:message bundle="${local}" key="account.add.diet" var="addDiet" />

<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Fitness</title>
        <link rel="stylesheet" href="styles/style.css" />
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
                            ${addExercise}
                        </a>
                    </li>
                    <li class="account__item">
                        <a href="${pageContext.request.contextPath}/controller?command=instructorActions&action=addDishForm"
                           class="account__item-info">
                            ${addDish}
                        </a>
                    </li>
                    <li class="account__item">
                        <a href="${pageContext.request.contextPath}/controller?command=instructorActions&action=chooseClientForProgram"
                           class="account__item-info">
                            ${addProgram}
                        </a>
                    </li>
                    <li class="account__item">
                        <a href="${pageContext.request.contextPath}/controller?command=instructorActions&action=chooseClientForDiet"
                           class="account__item-info">
                            ${addDiet}
                        </a>
                    </li>
                </ul>
            </div>
        </main>
    </body>
</html>