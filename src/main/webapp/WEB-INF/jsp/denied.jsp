<!DOCTYPE html>
<html>
  <head>
  	<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
    <title>Payment Denied</title>
  </head>
<body>
<h1>codenpay</h1> 
<br>
<div class="pure-menu pure-menu-horizontal">
    <ul class="pure-menu-list">
        <li class="pure-menu-item"><a href="/checkout" class="pure-button pure-button-primary">Checkout (1st Phase)</a></li>
        <li class="pure-menu-item"><a href="/oneclick" class="pure-menu-link">One Click (2nd Phase)</a></li>
        <li class="pure-menu-item"><a href="/list" class="pure-menu-link">Transactions</a></li>
    </ul>
</div>
<h3>Your payment has been denied.</h3><br>
<h3>Code: ${code} </h3><br>
<h3>Description: ${description}</h3><br>
</body>
</html>