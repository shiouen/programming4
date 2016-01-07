<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Favorites error</title>
    </head>
<body>
    <form action="/tdd/login" method="get">
        <input type="submit" name="try-again" value="Try again"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
    <p id="error">${error}</p>
</body>
</html>
