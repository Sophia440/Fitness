<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="text" var="local" />

<fmt:message bundle="${local}" key="account.title" var="title" />
<fmt:message bundle="${local}" key="account.membership" var="membership" />
<fmt:message bundle="${local}" key="account.program" var="program" />
<fmt:message bundle="${local}" key="account.diet" var="diet" />
<fmt:message bundle="${local}" key="account.info.membership" var="membershipInfo" />
<fmt:message bundle="${local}" key="account.info.program" var="programInfo" />
<fmt:message bundle="${local}" key="account.info.diet" var="dietInfo" />
<fmt:message bundle="${local}" key="account.membership.end" var="membershipEndInfo" />
<fmt:message bundle="${local}" key="account.button.accept" var="accept" />
<fmt:message bundle="${local}" key="account.button.decline" var="decline" />

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

                <p class="account__subtitle">Your discount: ${clientDiscount}%</p>

                <div class="grid__row">
                    <div class="grid__column-membership" onclick="openTab('b1');">${membership}</div>
                    <div class="grid__column-program" onclick="openTab('b2');">${program}</div>
                    <div class="grid__column-diet" onclick="openTab('b3');">${diet}</div>
                </div>

                <div id="b1" class="grid__container-tab">
                    <span onclick="this.parentElement.style.display='none'" class="grid__closebtn">x</span>
                    <p class="grid__container-info-title">${membershipInfo}</p>
                    <c:if test="${not empty membershipEndDate}">
                        <p class="grid__container-info">${membershipEndInfo}: ${membershipEndDate}</p>
                    </c:if>
                </div>

                <div id="b2" class="grid__container-tab">
                    <span onclick="this.parentElement.style.display='none'" class="grid__closebtn">x</span>
                    <p class="grid__container-info-title">${programInfo}</p>
                    <c:if test="${not empty exerciseList}">
                        <c:forEach items="${exerciseList}" var="exercise">
                            <p class="grid__container-info">${exercise.name}</p>
                        </c:forEach>
                    </c:if>
                    <c:if test="${programStatus=='AWAITING_CLIENT_ANSWER'}">
                        <form action="${pageContext.request.contextPath}/controller?command=acceptProgram" method="post">
                            <button type="submit" class="account-form__accept" value="submit">${accept}</button>
                        </form>
                        <form action="${pageContext.request.contextPath}/controller?command=declineProgram" method="post">
                            <button type="submit" class="account-form__decline" value="submit">${decline}</button>
                        </form>
                    </c:if>
                </div>

                <div id="b3" class="grid__container-tab">
                    <span onclick="this.parentElement.style.display='none'" class="grid__closebtn">x</span>
                    <p class="grid__container-info-title">${dietInfo}</p>
                    <c:if test="${not empty dishList}">
                        <c:forEach items="${dishList}" var="dish">
                            <p class="grid__container-info">${dish.name}, ${dish.meal}</p>
                        </c:forEach>
                    </c:if>
                    <c:if test="${dietStatus=='AWAITING_CLIENT_ANSWER'}">
                        <form action="${pageContext.request.contextPath}/controller?command=acceptDiet" method="post">
                            <button type="submit" class="account-form__accept" value="submit">${accept}</button>
                        </form>
                        <form action="${pageContext.request.contextPath}/controller?command=declineDiet" method="post">
                            <button type="submit" class="account-form__decline" value="submit">${decline}</button>
                        </form>
                    </c:if>
                </div>
            </div>
        </main>
    </body>
</html>