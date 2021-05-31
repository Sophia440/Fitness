<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="text" var="local" />

<fmt:message bundle="${local}" key="account.choose.client" var="chooseClient" />
<fmt:message bundle="${local}" key="account.button.submit" var="submit" />

<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Fitness</title>
        <link rel="stylesheet" href="styles/style.css" />
    </head>
    <body>
        <div class="header">
            <jsp:include page="../fragments/header.jsp"/>
        </div>
        <main class="actions__main">
            <div class="wrapper">
                <p class="actions__title">${chooseClient}</p>
                <form action="${pageContext.request.contextPath}/controller?command=instructorActions&action=checkDiet" method="post">
                    <c:if test="${not empty allClients}">
                        <c:forEach items="${allClients}" var="client">
                            <input type="radio" name="clientToAddDiet" value="${client.id}" class="actions-form__radio">
                            <label class="actions-form__select-radio">${client.login}</label><br>
                        </c:forEach>
                    </c:if>
                    <div>
                        <input type="submit" value="${submit}" class="actions-form__submit">
                    </div>
                </form>
            </div>
        </main>
    </body>
</html>