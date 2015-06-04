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

<head>
    <title>Profile / ${profile.user.username}</title>
</head>

<div class="container-fluid">
    <br>
    <ul class="nav nav-tabs">
        <li class="active"><a href="#">Public Profile</a></li>
        <li><a href="/user/${username}/posts/1">Posts&nbsp;&nbsp;<span class="badge">${postsCount}</span></a></li>
        <li><a href="/user/${username}/comments/1">Comments&nbsp;&nbsp;<span class="badge">${commentsCount}</span></a>
        </li>
        <li><a href="/user/${username}/favorites/1">Favorites&nbsp;&nbsp;<span
                class="badge">${favoritesCount}</span></a></li>
        <li><a href="#">Friends</a></li>
    </ul>
    <br>
</div>

<div class="container-fluid col-md-5" style="margin-left: 40px">
    <img src="${pageContext.request.contextPath}/resources/images/avatars/${username}.jpeg" width="350"
         class="img-rounded"
         onError="this.src='<s:url value="${pageContext.request.contextPath}/resources/images/avatars"/>/def-ava.png';"/>

    <div class="container-fluid" style="margin-left:10px ">
        <span class="glyphicon glyphicon-time margin-top"></span>&nbsp;&nbsp;Joined on Feb 18, 2013
        <br>
        <span class="glyphicon glyphicon-user margin-top"></span>&nbsp;&nbsp;as Member
        <br>
        <span class="glyphicon glyphicon-flag margin-top"></span>&nbsp;&nbsp;Last seen at Feb 18, 2013
        <br>
        <span class="glyphicon glyphicon-thumbs-up margin-top"></span>&nbsp;&nbsp;0 &nbsp;&nbsp;&nbsp;&nbsp; <span
            class="glyphicon glyphicon-thumbs-down"></span>&nbsp;&nbsp;0
    </div>
</div>

<div class="container-fluid">
    <s:url var="user_url" value="/settings/profile"/>
    <div class="col-md-6">
        <div class="panel panel-success">
            <div class="panel-heading" style="padding-left: 40px"><h3><b>${username}</b></h3></div>
            <div class="panel-body">
                <table class="table">
                    <tbody>
                    <tr>
                        <td><spring:message code="label.fullname"/></td>
                        <td>${profile.fullname}</td>
                    </tr>
                    <tr>
                        <td><spring:message code="label.email"/></td>
                        <td>${profile.email}</td>
                    </tr>
                    <tr>
                        <td><spring:message code="label.birthdate"/></td>
                        <td><joda:format value="${profile.birthdate}" pattern="MMM d, yyyy"/></td>
                    </tr>
                    <tr>
                        <td><spring:message code="label.gender"/></td>
                        <td>${profile.gender}</td>
                    </tr>
                    <tr>
                        <td><spring:message code="label.country"/></td>
                        <td>${profile.country}</td>
                    </tr>
                    <tr>
                        <td><spring:message code="label.city"/></td>
                        <td>${profile.city}</td>
                    </tr>
                    <tr>
                        <td><spring:message code="label.job"/></td>
                        <td>${profile.job}</td>
                    </tr>
                    <tr>
                        <td><spring:message code="label.about"/></td>
                        <td>${profile.about}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="panel-footer">
                <sec:authorize access="hasRole('ROLE_USER')">
                    <sec:authentication property="principal.username" var="authorizedUser"/>

                    <c:if test="${authorizedUser.equals(profile.user.username)}">

                        <a href="${user_url}" class="btn btn-default"><span
                                class="glyphicon glyphicon-pencil"></span>&nbsp;<spring:message code="button.edit"/></a>

                    </c:if>
                </sec:authorize>
            </div>
        </div>
    </div>
</div>