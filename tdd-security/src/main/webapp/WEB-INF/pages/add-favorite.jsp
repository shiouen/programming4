<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Favorites: ${user.username}</title>
    </head>
    <body>
        <form action="/tdd/add-favorite" method="post">
            <input name="favorite" type="text"/>
            <input name="Add favorite" value="Add favorite" type="submit">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <table name="favorites">
            <c:forEach var="favorite" items="${favorites}">
                <tr>
                    <td>${favorite}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
