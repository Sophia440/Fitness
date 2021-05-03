<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text" var="local"/>

<fmt:message bundle="${local}" key="welcome.title" var="title" />
<fmt:message bundle="${local}" key="welcome.subtitle" var="subtitle" />
<fmt:message bundle="${local}" key="login.button" var="login" />
<fmt:message bundle="${local}" key="header.language" var="language" />

<html lang="${language}">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Fitness</title>
        <link rel="stylesheet" href="static/style.css" />
    </head>
    <body>
        <main class="main">
            <div class="header">
                <header class="header">
                    <div class="wrapper">
                        <div class="header__wrapper">
                            <div class="header__logo">
                                <a href="${pageContext.request.contextPath}/controller?command=logout" class="header__logo-link">
                                    <p>Fitness</p>
                                </a>
                            </div>
                            <div class="header__language">
                                <button class="header__language-dropbtn">${language}</button>
                                <div class="header__language-content">
                                    <a href="${pageContext.servletContext.contextPath}?language=en" class="header__language-link">EN</a>
                                    <a href="${pageContext.servletContext.contextPath}?language=ru" class="header__language-link">RU</a>
                                    <a href="${pageContext.servletContext.contextPath}?language=by" class="header__language-link">BY</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </header>
            </div>
            <div class="wrapper">
                <h1 class="welcome__title">${title}</h1>
                <p class="welcome__subtitle">${subtitle}</p>
                <form class="login-form" action="${pageContext.request.contextPath}/controller?command=login" method="post">
                    <fieldset class="login-form__wrap">
                        <p class="login-form__info">
                            <input type="hidden" name="command" value="login" />
                            <input type="text" name="username" class="login-form__field" placeholder="Username">
                            <input type="text" name="password" class="login-form__field" placeholder="Password">
                            <button type="submit" class="login-form__submit" value="submit">${login}</button>
                        </p>
                    </fieldset>
                </form>
            </div>
        </main>
    </body>
</html>

