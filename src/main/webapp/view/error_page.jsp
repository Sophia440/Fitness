<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fitness</title>
    <link rel="stylesheet" href="static/style.css" />
</head>

<html lang="${language}">
    <body>
        <div class="header">
            <jsp:include page="fragments/header.jsp"/>
        </div>

        <main class="main">
            <div class="wrapper">
                <h1 class="error__title">
                    <fmt:message key="error"/>
                </h1>
                <p class="error__subtitle">
                    ${errorMessage}
                </p>
            </div>
        </main>
    </body>
</html>