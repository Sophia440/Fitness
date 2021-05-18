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
        <form action="${pageContext.request.contextPath}/controller?command=instructorActions&action=checkDiet" method="post">
            <p class="actions__subtitle">Choose client to add diet:</p>
            <c:if test="${not empty allClients}">
                <c:forEach items="${allClients}" var="client">
                    <input type="radio" name="clientToAddDiet" value="${client.id}">
                    <label class="actions-form__select-radio">${client.login}</label><br>
                </c:forEach>
            </c:if>
            <div>
                <input type="submit" value="Submit" class="actions-form__submit">
            </div>
        </form>
    </div>

</main>
</body>
</html>