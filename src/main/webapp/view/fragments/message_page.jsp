<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="text" var="local" />

<fmt:message bundle="${local}" key="message.exercise.added" var="exerciseAdded" />
<fmt:message bundle="${local}" key="message.dish.added" var="dishAdded" />
<fmt:message bundle="${local}" key="message.client.has.program" var="clientHasProgram" />
<fmt:message bundle="${local}" key="message.no.selected.exercises" var="noSelectedExercises" />
<fmt:message bundle="${local}" key="message.invalid.date" var="invalidDate" />
<fmt:message bundle="${local}" key="message.program.added" var="programAdded" />
<fmt:message bundle="${local}" key="message.client.has.diet" var="clientHasDiet" />
<fmt:message bundle="${local}" key="message.no.selected.dishes" var="noSelectedDishes" />
<fmt:message bundle="${local}" key="message.diet.added" var="dietAdded" />
<fmt:message bundle="${local}" key="message.payment.confirmation" var="paymentConfirmed" />
<fmt:message bundle="${local}" key="message.exercise.deleted" var="exerciseDeleted" />
<fmt:message bundle="${local}" key="message.dish.deleted" var="dishDeleted" />
<fmt:message bundle="${local}" key="message.discount.added" var="discountAdded" />
<fmt:message bundle="${local}" key="message.empty.name" var="emptyName" />
<fmt:message bundle="${local}" key="message.empty.discount" var="emptyDiscount" />
<fmt:message bundle="${local}" key="return.button" var="returnBtn" />


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
            <jsp:include page="header.jsp"/>
        </div>
        <main class="main">
            <div class="wrapper">
                <c:choose>
                    <c:when test="${ message == 'exerciseAdded' }" >
                        <h1 class="about__title">${exerciseAdded}</h1>
                    </c:when>
                    <c:when test="${ message == 'dishAdded' }" >
                        <h1 class="about__title">${dishAdded}</h1>
                    </c:when>
                    <c:when test="${ message == 'clientHasProgram' }" >
                        <h1 class="about__title">${clientHasProgram}</h1>
                    </c:when>
                    <c:when test="${ message == 'noSelectedExercises' }" >
                        <h1 class="about__title">${noSelectedExercises}</h1>
                    </c:when>
                    <c:when test="${ message == 'invalidDate' }" >
                        <h1 class="about__title">${invalidDate}</h1>
                    </c:when>
                    <c:when test="${ message == 'programAdded' }" >
                        <h1 class="about__title">${programAdded}</h1>
                    </c:when>
                    <c:when test="${ message == 'clientHasDiet' }" >
                        <h1 class="about__title">${clientHasDiet}</h1>
                    </c:when>
                    <c:when test="${ message == 'noSelectedDishes' }" >
                        <h1 class="about__title">${noSelectedDishes}</h1>
                    </c:when>
                    <c:when test="${ message == 'dietAdded' }" >
                        <h1 class="about__title">${dietAdded}</h1>
                    </c:when>
                    <c:when test="${ message == 'exerciseDeleted' }" >
                        <h1 class="about__title">${dietAdded}</h1>
                    </c:when>
                    <c:when test="${ message == 'dishDeleted' }" >
                        <h1 class="about__title">${dietAdded}</h1>
                    </c:when>
                    <c:when test="${ message == 'discountAdded' }" >
                        <h1 class="about__title">${dietAdded}</h1>
                    </c:when>
                    <c:when test="${ message == 'paymentConfirmed' }" >
                        <h1 class="about__title">${paymentConfirmed}</h1>
                    </c:when>
                    <c:when test="${ message == 'emptyDiscount' }" >
                        <h1 class="about__title">${emptyDiscount}</h1>
                    </c:when>
                    <c:when test="${ message == 'emptyName' }" >
                        <h1 class="about__title">${emptyName}</h1>
                    </c:when>
<%--                    <c:otherwise></c:otherwise>--%>
                </c:choose>
                <c:if test="${role=='CLIENT'}">
                    <button type="submit" class="return-btn" onclick="location.href='${pageContext.request.contextPath}/controller?command=clientMain'">
                            ${returnBtn}
                    </button>
                </c:if>
                <c:if test="${role=='ADMIN'}">
                    <button type="submit" class="return-btn" onclick="location.href='${pageContext.request.contextPath}/controller?command=adminMain'">
                            ${returnBtn}
                    </button>
                </c:if>
                <c:if test="${role=='INSTRUCTOR'}">
                    <button type="submit" class="return-btn" onclick="location.href='${pageContext.request.contextPath}/controller?command=instructorMain'">
                            ${returnBtn}
                    </button>
                </c:if>
            </div>
        </main>
    </body>
</html>