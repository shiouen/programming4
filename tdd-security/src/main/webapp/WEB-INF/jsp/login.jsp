<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Favorites login</title>
    </head>
    <body>
        <form action="/tdd/login" method="post">
            <input type="text" name="username"/>
            <input type="password" name="password"/>

            <input type="submit" name="login" value="Login"/>
        </form>
        <p id="error">${error}</p>
    </body>
</html>
