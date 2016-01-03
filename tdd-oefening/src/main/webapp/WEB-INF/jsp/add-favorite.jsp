<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Favorites: ${user.username}</title>
    </head>
    <body>
        <form action="/tdd/add-favorite" method="post">
            <input name="favorite" type="text"/>
            <input name="Add favorite" value="Add favorite" type="submit">
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
