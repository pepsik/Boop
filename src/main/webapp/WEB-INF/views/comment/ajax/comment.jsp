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

<c:set var="comment" value="${requestScope.get('comment')}"/>

<s:url value="/account/{id}" var="account_url">
    <s:param name="id" value="${comment.account.id}"/>
</s:url>

<div class="comment" id="${comment.id}">
    <div id="summernoteComment${comment.id}">
        ${comment.text}
    </div>
    <div class="author text-info">
        <small><joda:format value="${comment.when}" pattern="HH:mm MMM d, yyyy"/>
            <c:out value="by "/>
            <a href="${account_url}">${comment.account.username}</a>
        </small>
        <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
            <sec:authentication property="principal.username" var="authorizedUser"/>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <c:set var="access" value="${true}" scope="page"/>
            </sec:authorize>
            <c:if test="${authorizedUser.equals(comment.account.username) or access}">
                <div style="float: right">
                    <button id="edit${comment.id}" class="btn btn-xs btn-default"
                            onclick="editComment(${comment.id})">
                        <spring:message code="button.edit"/>
                    </button>
                    <button id="delete${comment.id}" class="btn btn-xs btn-danger"
                            onclick="deleteComment(${comment.post.id}, ${comment.id})">
                        <spring:message code="button.delete"/>
                    </button>
                    <button id="save${comment.id}" class="btn btn-xs btn-success" style="display: none;"
                            onclick="saveComment(${comment.post.id}, ${comment.id})">
                        <spring:message code="button.save"/>
                    </button>
                    <button id="cancel${comment.id}" class="btn btn-xs btn-default" style="display: none;"
                            onclick="cancelEditing(${comment.id})">
                        <spring:message code="button.cancel"/>
                    </button>
                </div>
            </c:if>
        </sec:authorize>
    </div>
</div>