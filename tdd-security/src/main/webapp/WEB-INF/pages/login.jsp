<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Favorites login</title>
    </head>
    <body>
        <form action="/tdd/login" method="post">
            <input type="text" name="username"/>
            <input type="password" name="password"/>

            <input type="submit" name="login" value="Login"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </body>
</html>
