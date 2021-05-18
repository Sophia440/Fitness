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
            <form action="${pageContext.request.contextPath}/controller?command=instructorActions&action=addDish" method="post">
                <label class="actions-form__select-label" for="newDishName">Name:</label>
                <input type="text" class="actions-form__field" id="newDishName" name="newDishName">
                <label class="actions-form__select-label" for="newDishMeal">Meal:</label>
                <select class="actions-form__select" id="newDishMeal" name="newDishMeal">
                    <option value="breakfast">Breakfast</option>
                    <option value="lunch">Lunch</option>
                    <option value="dinner">Dinner</option>
                    <option value="snack">Snack</option>
                </select>
                <div>
                    <button type="submit" class="actions-form__submit" value="submit">Add</button>
                </div>
            </form>
    </div>

</main>
</body>
</html>