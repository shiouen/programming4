<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Favorites: ${user.username}</title>
    </head>
    <body>
        <form action="/tdd/add-user" method="post">
            <input name="username" type="text"/>
            <input name="password" type="text"/>
            <input value="Add user" name="Add user" type="submit">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <table name="users">
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>
                        <form action="/tdd/remove-user" method="post">
                            <input type="hidden" id="${user}" name="username" value="${user}" />
                            ${user} <input value="Remove" type="submit">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
