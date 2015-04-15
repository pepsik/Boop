<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 4/15/15
  Time: 18:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">

    <h2>Account Information</h2>

    <table class="table table-striped" style="width:600px;">
        <tr>
            <th>Fullname</th>
            <td><b><c:out value="${account.fullname}"/></b></td>
        </tr>
        <tr>
            <th>Username</th>
            <td><c:out value="${account.username}"/></td>
        </tr>
        <tr>
            <th>Password</th>
            <td><c:out value="${account.password}"/></td>
        </tr>
        <tr>
            <th>Birthdate</th>
            <td><joda:format value="${account.birthdate}" pattern="MMM d, yyyy"/></td>
        </tr>
        <tr>
            <%--<td>--%>
            <%--<spring:url value="{ownerId}/edit.html" var="editUrl">--%>
            <%--<spring:param name="ownerId" value="${owner.id}"/>--%>
            <%--</spring:url>--%>
            <%--<a href="${fn:escapeXml(editUrl)}" class="btn btn-info">Edit Owner</a></td>--%>
            <%--<td>--%>
            <%--<spring:url value="{ownerId}/pets/new.html" var="addUrl">--%>
            <%--<spring:param name="ownerId" value="${owner.id}"/>--%>
            <%--</spring:url>--%>
            <%--<a href="${fn:escapeXml(addUrl)}"  class="btn btn-success">Add New Pet</a></td>--%>
        </tr>
    </table>
</div>