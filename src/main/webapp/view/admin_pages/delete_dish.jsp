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
            <form action="${pageContext.request.contextPath}/controller?command=adminActions&action=deleteDish" method="post">
                <p class="actions__subtitle">Choose dish to delete:</p>
                <c:if test="${not empty allDishes}">
                    <c:forEach items="${allDishes}" var="dish">
                        <input type="radio" name="dishToDelete" value="${dish.id}">
                        <label class="actions-form__select-radio">${dish.name}</label><br>
                    </c:forEach>
                </c:if>
                <div>
                    <input type="submit" value="submit" class="actions-form__submit">
                </div>
            </form> 
    </div>

</main>
</body>
</html>