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

    <s:url var="user_url" value="/settings/profile"/>

    <div class="container col-md-7">
        <div class="panel panel-success">
            <div class="panel-heading"><h4><spring:message code="label.user.view.title"/></h4></div>
            <div class="panel-body">
                <table class="table table-striped">
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
                        <td><b>${profile.gender}</b></td>
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
            </div>
            <div class="panel-footer">
                <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                    <sec:authentication property="principal.username" var="authorizedUser"/>

                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <c:set var="access" value="${true}" scope="page"/>
                    </sec:authorize>

                    <c:if test="${authorizedUser.equals(profile.user.username) or access}"> <!-- Shit -->

                        <a href="${user_url}" class="btn btn-default"><span
                                class="glyphicon glyphicon-pencil"></span>&nbsp;<spring:message code="button.edit"/></a>

                    </c:if>
                </sec:authorize>
            </div>
        </div>
    </div>
    <div class="container col-md-5">
        <img src="<s:url value="/resources" />/images/def-ava.png"/>
    </div>

    <div class="container col-md-5" style="margin-top: 30px">
        <div class="panel panel-success">
            <div class="panel-body">
                <a href="/user/${profile.user.username}/favorite" class="btn btn-default btn-block">Favorites</a>
                <a href="#" class="btn btn-default btn-block">Friends</a>
            </div>
        </div>
    </div>

</div>