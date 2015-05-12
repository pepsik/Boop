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

    <s:url var="user_url" value="/user/{username}">
        <s:param name="username" value="${username}"/>
    </s:url>

    <div class="formHolder">
        <h2 class="formHolder"><spring:message code="label.account.view.title"/></h2>

    </div>
    <br>

    <table class="table table-striped" style="width:600px;">
        <tr>
            <th><spring:message code="label.fullname"/></th>
            <td><b><c:out value="${profile.fullname}"/></b></td>
        </tr>
        <tr>
            <th></th>
            <td></td>
        </tr>
        <tr>
            <th><spring:message code="label.email"/></th>
            <td><b><c:out value="${profile.email}"/></b></td>
        </tr>
        <tr>
            <th></th>
            <td></td>
        </tr>
        <tr>
            <th><spring:message code="label.birthdate"/></th>
            <td><joda:format value="${profile.birthdate}" pattern="MMM d, yyyy"/></td>
        </tr>
        <tr>
            <th></th>
            <td></td>
        </tr>
        <tr>
            <th><spring:message code="label.gender"/></th>
            <td><b><spring:message code="label.gender.${profile.gender}"/></b></td>
        </tr>
        <tr>
            <th></th>
            <td></td>
        </tr>
        <tr>
            <th><spring:message code="label.country"/></th>
            <td><b><c:out value="${profile.country}"/></b></td>
        </tr>
        <tr>
            <th></th>
            <td></td>
        </tr>
        <tr>
            <th><spring:message code="label.city"/></th>
            <td><b><c:out value="${profile.city}"/></b></td>
        </tr>
        <tr>
            <th></th>
            <td></td>
        </tr>
        <tr>
            <th><spring:message code="label.job"/></th>
            <td><b><c:out value="${profile.job}"/></b></td>
        </tr>
        <tr>
            <th></th>
            <td></td>
        </tr>
        <tr>
            <th><spring:message code="label.about"/></th>
            <td><b><c:out value="${profile.about}"/></b></td>
        </tr>
    </table>

    <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
        <sec:authentication property="principal.username" var="authorizedUser"/>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <c:set var="access" value="${true}" scope="page"/>
        </sec:authorize>

        <c:if test="${authorizedUser.equals(profile.account.username) or access}"> <!-- Shit -->

            <sf:form action="${user_url}/edit" method="get">
                <button type="submit" class="btn btn-default"><spring:message code="button.edit"/></button>
            </sf:form>

        </c:if>
    </sec:authorize>

</div>