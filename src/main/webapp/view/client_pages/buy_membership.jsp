<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="text" var="local"/>

<fmt:message bundle="${local}" key="buy.membership.title" var="title" />
<fmt:message bundle="${local}" key="buy.membership.choice" var="choice" />
<fmt:message bundle="${local}" key="buy.membership.month" var="month" />
<fmt:message bundle="${local}" key="buy.membership.months1" var="months1" />
<fmt:message bundle="${local}" key="buy.membership.months2" var="months2" />
<fmt:message bundle="${local}" key="buy.membership.discount" var="discountInfo" />
<fmt:message bundle="${local}" key="buy.membership.buy.button" var="buyBtn" />

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fitness</title>
    <link rel="stylesheet" href="static/style.css"/>
    <script src="view/scripts/buy_membership.js"></script>
</head>
<body>
<div class="header">
    <jsp:include page="../fragments/header.jsp"/>
</div>

<main class="service__main">
    <div class="wrapper">
        <h1 class="service__title">${title}</h1>
        <form action="${pageContext.request.contextPath}/controller?command=buyMembership" method="post">
            <label for="membershipDuration" class="membership__title">${choice}:</label>
            <select id="membershipDuration" name="membershipDuration" onchange="getPrice(${discount})"
                    class="membership__select">
                <option value="1">1 ${month}</option>
                <option value="3">3 ${months1}</option>
                <option value="6">6 ${months2}</option>
                <option value="12">12 ${months2}</option>
            </select>
            <c:if test="${(discount != 0.0)}">
                <p class="membership__subtitle">${discountInfo}: ${discount}%</p>
            </c:if>
            <p class="membership__subtitle" id="membershipPrice"></p>
            <button type="submit" class="login-form__submit" value="submit">${buyBtn}</button>
        </form>
    </div>
</main>
</body>
</html>