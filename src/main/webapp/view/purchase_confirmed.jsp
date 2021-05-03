<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="text" var="local" />

<fmt:message bundle="${local}" key="payment.confirmation" var="confirmation" />
<fmt:message bundle="${local}" key="return.button" var="returnBtn" />

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

<main class="main">
    <div class="wrapper">
        <h1 class="about__title">${confirmation}</h1>
        <button type="submit" class="return-btn" onclick="location.href='${pageContext.request.contextPath}/controller?command=main'">
            ${returnBtn}
        </button>
    </div>
</main>
</body>
</html>