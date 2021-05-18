<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="text" var="local" />

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
        <form action="${pageContext.request.contextPath}/controller?command=instructorActions&action=addDiet" method="post">
            <p class="actions__subtitle">Choose dishes to add:</p>
            <c:if test="${not empty allDishes}">
                <c:forEach items="${allDishes}" var="dish">
                    <input type="checkbox" name="dietDishesList" value="${dish.id}">
                    <label class="actions-form__select-radio">${dish.name}</label><br>
                </c:forEach>
            </c:if>
            <br><br>
            <label for="dietStartDate">Start date:</label>
            <input type="date" id="dietStartDate" name="dietStartDate">
            <br><br>
            <label for="dietEndDate">End date:</label>
            <input type="date" id="dietEndDate" name="dietEndDate">
            <br><br>
            <div>
                <button type="submit" class="login-form__submit" value="submit">Add</button>
            </div>
        </form>
    </div>

</main>
</body>
</html>