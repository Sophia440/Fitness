<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="text" var="local" />

<fmt:message bundle="${local}" key="account.title" var="title" />
<fmt:message bundle="${local}" key="account.membership" var="membership" />
<fmt:message bundle="${local}" key="account.info.membership" var="membershipInfo" />
<fmt:message bundle="${local}" key="account.info.discount" var="discountInfo" />
<fmt:message bundle="${local}" key="account.membership.end" var="membershipEndInfo" />

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fitness</title>
    <link rel="stylesheet" href="static/style.css" />
    <script src="view/scripts/account.js"></script>
</head>
<body>
<div class="header">
    <jsp:include page="../fragments/header.jsp"/>
</div>

<main class="account__main">
    <div class="wrapper">

        <h1 class="account__title">${title}</h1>

        <p class="account__subtitle">${discountInfo}: ${clientDiscount}%</p>

        <p class="account__subtitle">${membershipInfo}</p>
        <c:if test="${not empty membershipEndDate}">
            <p class="account__subtitle">${membershipEndInfo}: ${membershipEndDate}</p>
        </c:if>
    </div>
</main>
</body>
</html>