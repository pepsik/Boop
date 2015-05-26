<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 4/30/15
  Time: 17:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="post" value="${requestScope.get('post')}"/>

<s:url value="/user/{id}" var="user_url">
    <s:param name="id" value="${post.user.id}"/>
</s:url>

<div class="comment" id="${post.id}">
    <div id="summernoteComment${post.id}">
        ${post.text}
    </div>
    <div class="author text-info">
        <small><joda:format value="${post.when}" pattern="HH:mm MMM d, yyyy"/>
            <c:out value="by "/>
            <a href="${user_url}">${post.user.username}</a>
        </small>
        <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
            <sec:authentication property="principal.username" var="authorizedUser"/>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <c:set var="access" value="${true}" scope="page"/>
            </sec:authorize>
            <c:if test="${authorizedUser.equals(post.user.username) or access}">
                <div style="float: right">
                    <button id="edit${post.id}" class="btn btn-xs btn-default"
                            onclick="editComment(${post.id})">
                        <span class="glyphicon glyphicon-pencil"></span>
                        <spring:message code="button.edit"/>
                    </button>
                    <button id="delete${post.id}" class="btn btn-xs btn-danger"
                            onclick="deleteComment(${post.post.id}, ${post.id})">
                        <span class="glyphicon glyphicon-trash"></span>
                        <spring:message code="button.delete"/>
                    </button>
                    <button id="save${post.id}" class="btn btn-xs btn-success" style="display: none;"
                            onclick="saveComment(${post.post.id}, ${post.id})">
                        <span class="glyphicon glyphicon-ok-sign"></span>
                        <spring:message code="button.save"/>
                    </button>
                    <button id="cancel${post.id}" class="btn btn-xs btn-default" style="display: none;"
                            onclick="cancelEditing(${post.id})">
                        <span class="glyphicon glyphicon-remove-sign"></span>
                        <spring:message code="button.cancel"/>
                    </button>
                </div>
            </c:if>
        </sec:authorize>
    </div>
</div>