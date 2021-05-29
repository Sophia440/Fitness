<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="text" var="local" />

<fmt:message bundle="${local}" key="account.enter.new.dish" var="title" />
<fmt:message bundle="${local}" key="account.name" var="name" />
<fmt:message bundle="${local}" key="account.meal" var="meal" />
<fmt:message bundle="${local}" key="account.breakfast" var="breakfast" />
<fmt:message bundle="${local}" key="account.lunch" var="lunch" />
<fmt:message bundle="${local}" key="account.dinner" var="dinner" />
<fmt:message bundle="${local}" key="account.snack" var="snack" />
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
                <h2 class="actions__title">${title}</h2>
                <form action="${pageContext.request.contextPath}/controller?command=instructorActions&action=addDish" method="post">
                    <label class="actions-form__select-label" for="newDishName">${name}:</label>
                    <input type="text" class="actions-form__field" id="newDishName" name="newDishName">
                    <label class="actions-form__select-label" for="newDishMeal">${meal}:</label>
                    <select class="actions-form__select" id="newDishMeal" name="newDishMeal">
                        <option value="breakfast">${breakfast}</option>
                        <option value="lunch">${lunch}</option>
                        <option value="dinner">${dinner}</option>
                        <option value="snack">${snack}</option>
                    </select>
                    <div>
                        <button type="submit" class="actions-form__submit" value="submit">${add}</button>
                    </div>
                </form>
            </div>
        </main>
    </body>
</html>