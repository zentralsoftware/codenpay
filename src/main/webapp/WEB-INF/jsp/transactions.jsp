<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
    <title>Transactions</title>
</head>
<body>
<h1>codenpay</h1> 
<br>
<div class="pure-menu pure-menu-horizontal">
    <ul class="pure-menu-list">
        <li class="pure-menu-item"><a href="/checkout" class="pure-menu-link">Checkout (1st Phase)</a></li>
        <li class="pure-menu-item"><a href="/oneclick" class="pure-menu-link">One Click (2nd Phase)</a></li>
        <li class="pure-menu-item"><a href="#" class="pure-button pure-button-primary">Transactions</a></li>
    </ul>
</div>
<table class="pure-table">
    <thead>
    	<tr>
	    	  <th>Checkout Id</th>
	        <th>Status Id</th>
	        <th>Brand</th>	        
	        <th>Amount</th>	 	        	        	        
	        <th>Card Bin</th>
	        <th>Card Last 4 Digits</th>
	        <th>Card Holder</th>
	        <th>Card Expire</th>	        	        	        
	        <th>Status</th>
          <th>Timestamp</th>
        </tr>
    </thead>
    <tbody>
<c:forEach var="transaction" items="${transactions}">
    <tr>
		    <td>${transaction.checkoutId}</td>	
       	<td>${transaction.statusId}</td>
       	<td>${transaction.basicPayment.paymentBrand}</td> 
       	<td>${transaction.basicPayment.currency} ${transaction.basicPayment.amount}</td>        	
       	<td>${transaction.card.bin}</td> 
       	<td>${transaction.card.last4Digits}</td>
       	<td>${transaction.card.holder}</td>
       	<td>${transaction.card.expiryMonth}/${transaction.card.expiryYear}</td>
       	<td>${transaction.statusResult.code} ${transaction.statusResult.description}</td> 
        <jsp:useBean id="dateObject" class="java.util.Date" />
        <jsp:setProperty name="dateObject" property="time" value="${transaction.timestamp}" />
        <td><fmt:formatDate type = "both" dateStyle = "long" timeStyle = "long" value = "${dateObject}" /></td>    	
    </tr>
</c:forEach>
	<tbody>
</table>

</body>
</html>

