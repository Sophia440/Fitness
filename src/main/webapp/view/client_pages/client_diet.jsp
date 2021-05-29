<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="text" var="local" />

<fmt:message bundle="${local}" key="account.title" var="title" />
<fmt:message bundle="${local}" key="account.diet" var="diet" />
<fmt:message bundle="${local}" key="account.info.diet" var="dietInfo" />
<fmt:message bundle="${local}" key="account.button.accept" var="accept" />
<fmt:message bundle="${local}" key="account.button.decline" var="decline" />

<html>
    <head>
      <meta charset="UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Fitness</title>
      <link rel="stylesheet" href="styles/style.css" />
      <script src="view/scripts/account.js"></script>
    </head>
    <body>
        <div class="header">
          <jsp:include page="../fragments/header.jsp"/>
        </div>
        <main class="account__main">
          <div class="wrapper">
              <h1 class="account__title">${dietInfo}</h1>
              <ul class="account__half-list">
                  <li class="account__first-half">
                      <c:if test="${not empty dishListFirstHalf}">
                          <c:forEach items="${dishListFirstHalf}" var="dish">
                              <p class="account__item">${dish.name}, ${dish.meal}</p>
                          </c:forEach>
                      </c:if>
                  </li>
                  <li>
                      <c:if test="${not empty dishListSecondHalf}">
                          <c:forEach items="${dishListSecondHalf}" var="dish">
                              <p class="account__item">${dish.name}, ${dish.meal}</p>
                          </c:forEach>
                      </c:if>
                  </li>
              </ul>
              <br><br>
              <c:if test="${dietStatus=='AWAITING_CLIENT_ANSWER'}">
                <div class="account-form__btn">
                    <form action="${pageContext.request.contextPath}/controller?command=clientActions&action=acceptDiet" method="post">
                      <button type="submit" class="account-form__accept" value="submit">${accept}</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/controller?command=clientActions&action=declineDiet" method="post">
                      <button type="submit" class="account-form__decline" value="submit">${decline}</button>
                    </form>
                  </div>
              </c:if>
          </div>
        </main>
    </body>
</html>
