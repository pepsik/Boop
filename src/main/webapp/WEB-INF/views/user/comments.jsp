<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 5/30/15
  Time: 14:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>Comments / ${username}</title>
</head>

<div class="container-fluid">
    <br>
    <ul class="nav nav-tabs">
        <li><a href="/user/${username}">Public Profile</a></li>
        <li><a href="/user/${username}/posts/1">Posts</a></li>
        <li class="active"><a href="#">Comments&nbsp;&nbsp;<span class="badge">${commentsCount}</span></a></li>
        <li><a href="/user/${username}/favorites/1">Favorites</a></li>
        <li><a href="#">Friends</a></li>
    </ul>
    <br>
</div>

<div class="container-fluid">
    <h2><span class="label label-default">Comments &nbsp;${username}</span></h2>
    <ol>
        <c:forEach var="comment" items="${commentList}" varStatus="loop">
            <s:url value="/post/{id}" var="post_url">
                <s:param name="id" value="${comment.post.id}"/>
            </s:url>
            <s:url value="/user/{id}" var="user_url">
                <s:param name="id" value="${comment.user.username}"/>
            </s:url>

            <li type="none" class="spittle-list">
                <h5><a class="label label-primary" href="${post_url}">
                    <c:out value="${comment.post.title}"/></a></h5>
                <div class="comment" id="${comment.id}">
                    <div id="summernoteComment${comment.id}">
                            ${comment.text}
                    </div>
                    <div class="author text-info">
                        <small><joda:format value="${comment.when}" pattern="HH:mm MMM d, yyyy"/>
                            <c:out value="by "/>
                            <a href="${user_url}">${comment.user.username}</a>
                        </small>
                        <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                            <sec:authentication property="principal.username" var="authorizedUser"/>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <c:set var="access" value="${true}" scope="page"/>
                            </sec:authorize>
                            <c:if test="${authorizedUser.equals(comment.user.username) or access}">
                                <div style="float: right">
                                    <button id="edit${comment.id}" class="btn btn-xs btn-default"
                                            onclick="editComment(${comment.id})">
                                        <span class="glyphicon glyphicon-pencil"></span>
                                        <spring:message code="button.edit"/>
                                    </button>
                                    <button id="delete${comment.id}" class="btn btn-xs btn-danger"
                                            onclick="deleteComment(${comment.post.id}, ${comment.id})">
                                        <span class="glyphicon glyphicon-trash"></span>
                                        <spring:message code="button.delete"/>
                                    </button>
                                    <button id="save${comment.id}" class="btn btn-xs btn-success" style="display: none;"
                                            onclick="saveComment(${comment.post.id}, ${comment.id})">
                                        <span class="glyphicon glyphicon-ok-sign"></span>
                                        <spring:message code="button.save"/>
                                    </button>
                                    <button id="cancel${comment.id}" class="btn btn-xs btn-default"
                                            style="display: none;"
                                            onclick="cancelEditing(${comment.id})">
                                        <span class="glyphicon glyphicon-remove-sign"></span>
                                        <spring:message code="button.cancel"/>
                                    </button>
                                </div>
                            </c:if>
                        </sec:authorize>
                    </div>
                </div>
            </li>
        </c:forEach>
    </ol>

    <ul class="pagination">
        <c:choose>
            <c:when test="${1 != currentPageIndex}">
                <li><a href="/user/${username}/comments/${currentPageIndex - 1}">&laquo;</a></li>
            </c:when>
        </c:choose>
        <c:forEach items="${pagination}" var="pageIndex">
            <c:choose>
                <c:when test="${pageIndex == currentPageIndex}">
                    <li class="active"><a>${pageIndex}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="/user/${username}/comments/${pageIndex}">${pageIndex}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${pagination.get(pagination.size()- 1) != currentPageIndex}">
                <li><a href="/user/${username}/comments/${currentPageIndex + 1}">&raquo;</a></li>
            </c:when>
        </c:choose>
    </ul>
</div>
