<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="response${thread_id}">
    <c:forEach var="post" items="${postList}">
        <s:url value="/account/{id}" var="account_url">
            <s:param name="id" value="${post.account.id}"/>
        </s:url>

        <div class="post" id="${post.id}">
            <div id="summernotePost${post.id}">
                    ${post.text}
            </div>
            <div class="author text-info">
                <small><joda:format value="${post.when}" pattern="HH:mm MMM d, yyyy"/>
                    <c:out value="by "/>
                    <a href="${account_url}">${post.account.username}</a>
                </small>
                <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                    <sec:authentication property="principal.username" var="authorizedUser"/>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <c:set var="access" value="${true}" scope="page"/>
                    </sec:authorize>
                    <c:if test="${authorizedUser.equals(post.account.username) or access}">
                        <div style="float: right">
                            <button id="edit${post.id}" class="btn btn-xs btn-default"
                                    onclick="editComment(${post.id})">Edit
                            </button>
                            <button id="delete${post.id}" class="btn btn-xs btn-danger"
                                    onclick="deleteComment(${post.thread.id}, ${post.id})">Delete
                            </button>
                            <button id="save${post.id}" class="btn btn-xs btn-success" style="display: none;"
                                    onclick="saveComment(${post.thread.id}, ${post.id})">Save
                            </button>
                            <button id="cancel${post.id}" class="btn btn-xs btn-default" style="display: none;"
                                    onclick="cancelEditing(${post.id})">Cancel
                            </button>
                        </div>
                    </c:if>
                </sec:authorize>
            </div>
        </div>
    </c:forEach>
    <br>
    <form:form modelAttribute="post" method="post" id="postForm${thread_id}">
        <form:textarea path="text" id="summernoteEditor${thread_id}"/>
        <div class="submit"><br>
            <button type="submit" class="btn btn-success">
                <spring:message code="button.comment.post"/>
            </button>
        </div>
    </form:form>
</div>

<%@ include file="../../resources/js/commentsJS.jspf" %>