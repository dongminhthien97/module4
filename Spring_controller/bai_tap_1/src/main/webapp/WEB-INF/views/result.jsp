<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Ket Qua</title>
</head>
<body>
<h2>Ket qua lua chon</h2>

<c:if test="${empty condiments}">
    <p>Ket qua lua chon</p>
</c:if>

<c:if test="${not empty condiments}">
    <ul>
        <c:forEach var="item" items="${condiments}">
            <li>${item}</li>
        </c:forEach>
    </ul>
</c:if>

<a href="/">Quay lai</a>
</body>
</html>