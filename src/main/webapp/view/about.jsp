<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="text" var="local" />

<fmt:message bundle="${local}" key="header.account" var="account" />
<fmt:message bundle="${local}" key="header.trainers" var="trainers" />
<fmt:message bundle="${local}" key="header.services" var="services" />
<fmt:message bundle="${local}" key="header.about" var="about" />
<fmt:message bundle="${local}" key="header.language" var="language" />
<fmt:message bundle="${local}" key="header.logout" var="logout" />

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
    <div class="wrapper"><h2>
        About text
    </h2>
</div>
</main>
</body>
</html>