<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Simple Calculator</title>
</head>
<body>
<h2>Simple Calculator</h2>

<form action="/calculate" method="post">
  <input type="number" name="num1" />
  <input type="number" name="num2" /><br>

  <button type="submit" name="operation" value="add">Addition(+)</button>
  <button type="submit" name="operation" value="sub">Subtraction(-)</button>
  <button type="submit" name="operation" value="mul">Multiplication(X)</button>
  <button type="submit" name="operation" value="div">Division(/)</button>

</form>
<h2>Result ${operation} : ${result}</h2>
<c:if test="${not empty error}">
  <p style="color:red">${error}</p>
</c:if>
</body>
</html>
