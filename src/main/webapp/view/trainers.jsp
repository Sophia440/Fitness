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

<main class="trainers__main">
    <div class="wrapper">

        <h1 class="trainers__title">Trainers</h1>

        <ul id="trainers__list">
            <li class="trainers__item">
                Trainer 1
            </li>

            <li class="trainers__item">
                Trainer 2
            </li>

            <li class="trainers__item">
                Trainer 3
            </li>
        </ul>
    </div>
</main>
</body>
</html>