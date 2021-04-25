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
    <jsp:include page="fragments/header.jsp"/>
</div>

<main class="service__main">
    <div class="wrapper">
        <h1 class="service__title">Services</h1>
        <p class="service__subtitle">
            Choose a service that you want to purchase:
        </p>

        <ul id="service__list">
            <li class="service__item">
                <a href="#" class="service__item-info">
                    Abonement
                </a>
            </li>

            <li class="service__item">
                <a href="#" class="service__item-info">
                    Sth else
                </a>
            </li>

            <li class="service__item">
                <a href="#" class="service__item-info">
                    Lorem, ipsum dolor sit amet consectetur adipisicing elit. Veritatis iste consectetur sed saepe, dolorem voluptatibus odit officia voluptatem, ipsam, consequatur magni! Modi quam natus consectetur ullam vitae nisi amet dicta.
                </a>
            </li>
        </ul>
    </div>
</main>
</body>
</html>