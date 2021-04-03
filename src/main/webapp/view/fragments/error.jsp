<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <link rel="stylesheet" href="static/style.css">
</head>

<div class="wrapper">
    <h1 class="error__title">
        Error!
    </h1>
    <p class="error__subtitle">
        ${errorMessage}
    </p>
    <button type="submit" class="return-btn" onclick="document.location='index.jsp'">Return</button>

</div>