<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
    <title>Token</title>
</head>
<body>
<h1>codenpay</h1> 
<br>
<div class="pure-menu pure-menu-horizontal">
    <ul class="pure-menu-list">
        <li class="pure-menu-item"><a href="/checkout" class="pure-menu-link">Checkout (1st Phase)</a></li>
        <li class="pure-menu-item"><a href="/oneclick" class="pure-menu-link">One Click (2nd Phase)</a></li>
        <li class="pure-menu-item"><a href="/transactions" class="pure-menu-link">Transactions</a></li>
        <li class="pure-menu-item"><a href="#" class="pure-button pure-button-primary">Tokens</a></li>        
    </ul>
</div>
<table class="pure-table">
    <thead>
    	<tr>
	    	  <th>Identifier</th>
	        <th>Registration ID</th>
        </tr>
    </thead>
    <tbody>
<c:forEach var="token" items="${tokens}">
    <tr>
		    <td>${token.identifier}</td>	
       	<td>${token.registrationId}</td>
    </tr>
</c:forEach>
	<tbody>
</table>

</body>
</html>

