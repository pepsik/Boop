<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 4/15/15
  Time: 18:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>

    <s:url var="account_url" value="/account/{id}">
        <s:param name="id" value="${account.id}"/>
    </s:url>

    <div class="formHolder">
        <h2 class="formHolder"><spring:message code="label.account.view.title"/></h2>

    </div>
    <br>

    <table class="table table-striped" style="width:600px;">
        <tr>
            <th>Id</th>
            <td><c:out value="${account.id}"/></td>
        </tr>
        <tr>
            <th><spring:message code="label.fullname"/></th>
            <td><b><c:out value="${account.fullname}"/></b></td>
        </tr>
        <tr>
            <th><spring:message code="label.email"/></th>
            <td><b><c:out value="${account.email}"/></b></td>
        </tr>
        <tr>
            <th><spring:message code="label.username"/></th>
            <td><c:out value="${account.username}"/></td>
        </tr>
        <tr>
            <th>Password</th>
            <td><c:out value="${account.password}"/></td>
        </tr>
        <tr>
            <th><spring:message code="label.birthdate"/></th>
            <td><joda:format value="${account.birthdate}" pattern="MMM d, yyyy"/></td>
        </tr>
    </table>

    <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
        <sec:authentication property="principal.username" var="authorizedUser"/>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <c:set var="access" value="${true}" scope="page"/>
        </sec:authorize>

        <c:if test="${authorizedUser.equals(account.username) or access}"> <!-- Shit -->

            <sf:form action="${account_url}/edit" method="get">
                <button type="submit" class="btn btn-default"><spring:message code="button.edit"/></button>
            </sf:form>

        </c:if>
    </sec:authorize>

</div>