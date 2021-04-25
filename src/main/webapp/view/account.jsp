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
            <jsp:include page="fragments/header.jsp"/>
        </div>

        <main class="account__main">
            <div class="wrapper">

                <h1 class="account__title">${title}</h1>

                <div class="grid__row">
                    <div class="grid__column-membership" onclick="openTab('b1');">${membership}</div>
                    <div class="grid__column-program" onclick="openTab('b2');">${program}</div>
                    <div class="grid__column-diet" onclick="openTab('b3');">${diet}</div>
                </div>

                <div id="b1" class="grid__container-tab">
                    <span onclick="this.parentElement.style.display='none'" class="grid__closebtn">x</span>
                    <p class="grid__container-info-title">${membershipInfo}</p>
                    <p class="grid__container-info">
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Porro, dolorem ea. Laudantium ad deleniti alias molestiae ipsa vel excepturi debitis in voluptates incidunt, pariatur corrupti iste amet natus repellendus numquam?
                    </p>
                </div>

                <div id="b2" class="grid__container-tab">
                    <span onclick="this.parentElement.style.display='none'" class="grid__closebtn">x</span>
                    <p class="grid__container-info-title">${programInfo}</p>
                    <p class="grid__container-info">
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Porro, dolorem ea. Laudantium ad deleniti alias molestiae ipsa vel excepturi debitis in voluptates incidunt, pariatur corrupti iste amet natus repellendus numquam?
                    </p>
                </div>

                <div id="b3" class="grid__container-tab">
                    <span onclick="this.parentElement.style.display='none'" class="grid__closebtn">x</span>
                    <p class="grid__container-info-title">${dietInfo}</p>
                    <p class="grid__container-info">
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Porro, dolorem ea. Laudantium ad deleniti alias molestiae ipsa vel excepturi debitis in voluptates incidunt, pariatur corrupti iste amet natus repellendus numquam?
                    </p>
                </div>
            </div>
        </main>
    </body>
</html>