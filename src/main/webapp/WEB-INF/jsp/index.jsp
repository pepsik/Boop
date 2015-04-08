<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 4/7/15
  Time: 19:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>DNO</title>
</head>
<body>

<div>
    <ol>
        <c:forEach var="thread" items="${threadList}">

            <li>
                <c:out value="${thread.text}"/>
                <c:out value="${thread.when}"/>
            </li>
        </c:forEach>

    </ol>
</div>

</body>
</html>
