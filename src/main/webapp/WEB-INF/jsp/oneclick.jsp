<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>One Click</title>
</head>
<script src="https://test.oppwa.com/v1/paymentWidgets.js?checkoutId=${transaction.checkoutId}"></script>
<body>
<% 
	ServletRequest req = pageContext.getRequest();
	String baseURL = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort();
%>
<form action="<%=baseURL %>/checkstatus/${transaction.identifier}" class="paymentWidgets">${brands}</form>
</body>
</html>

