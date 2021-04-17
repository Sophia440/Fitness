<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="ru_RU" scope="session" />
<fmt:setBundle basename="resources.pagecontent" var="rb" />
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
        <jsp:include page="view/fragments/header.jsp"/>
    </div>
        <main class="main">

            <div class="wrapper">
                <h1 class="welcome__title">
                    <fmt:message key="welcome.title" bundle="${ rb }" />
                </h1>
                <p class="welcome__subtitle">
                    <fmt:message key="welcome.subtitle" bundle="${ rb }" />
                </p>
                <form class="login-form" action="/Fitness/controller" method="post">
                    <fieldset class="login-form__wrap">
                        <p class="login-form__info">
                            <input type="hidden" name="command" value="login" />
                            <input type="text" name="username" class="login-form__field" placeholder="Username">
                            <input type="text" name="password" class="login-form__field" placeholder="Password">
                            <button type="submit" class="login-form__submit" value="submit">Log In</button>
                        </p>
                    </fieldset>
                </form>
            </div>
        </main>
    </body>
</html>

