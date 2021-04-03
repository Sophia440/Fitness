<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <body>
        <div class="header">
            <jsp:include page="fragments/header.jsp"/>
        </div>

        <main class="main">
            <c:if test="${name != null}">
                <h2>
                    Hello, ${name}!
                </h2>
            </c:if>
            <c:if test="${errorMessage != null}">
                <jsp:include page="fragments/error.jsp"/>
            </c:if>
        </main>
    </body>
</html>