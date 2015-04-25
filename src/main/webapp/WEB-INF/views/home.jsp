<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 4/7/15
  Time: 19:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div>
    <s:url value="/thread/new" var="new_thread_url"/>

    <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
        <sec:authentication property="principal.username" var="authorizedUser"/>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <c:set var="access" value="${true}" scope="page"/>
        </sec:authorize>

        <c:if test="${authorizedUser.equals(post.account.username) or access}"> <!-- Shit -->
            <h2><a href="${new_thread_url}" class="btn btn-primary">
                <spring:message code="button.thread.new"/>
            </a></h2>
        </c:if>
    </sec:authorize>

    <br>
    <ol class="spittle-list">
        <c:forEach var="thread" items="${threadList}" varStatus="loop">
            <s:url value="/thread/{id}" var="thread_url">
                <s:param name="id" value="${thread.id}"/>
            </s:url>

            <li><span class="postListText">
                <h3><a class="label label-primary" href="${thread_url}">
                    <c:out value="${thread.title}"/></a></h3>
                <div class="thread summernote">
                        ${thread.text}
                </div>

                <div class="formHolder author text-info">
                    <s:url value="/account/{id}" var="account_url">
                        <s:param name="id" value="${thread.account.id}"/>
                    </s:url>
                    <span class="padding-top">
                    <small><joda:format value="${thread.when}" pattern="HH:mm MMM d, yyyy"/>
                        <c:out value="by "/>
                        <a href="${account_url}">${thread.account.username}</a></small>
                    </span>
                    <s:url value="collapse{id}" var="count">
                        <s:param name="id" value="${loop.count}"/>
                    </s:url>
                    <button class="btn btn-xs btn-success" data-toggle="collapse" href="#button${count}"
                            aria-expanded="false" aria-controls="collapse" onclick="getJson(${thread.id}, ${count})">
                        <spring:message code="button.comment.hide"/> (${thread.posts.size()})
                    </button>

                    <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                        <sec:authentication property="principal.username" var="authorizedUser"/>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <c:set var="access" value="${true}" scope="page"/>
                        </sec:authorize>

                        <c:if test="${authorizedUser.equals(thread.account.username) or access}"> <!-- Shit -->
                            <sf:form action="${thread_url}" method="delete">
                                <input type="submit" class="btn btn-xs btn-danger" value="Delete"/>
                            </sf:form>
                            <sf:form action="${thread_url}/edit" method="get">
                                <input type="submit" class="btn btn-xs" value="Edit"/>
                            </sf:form>
                        </c:if>
                    </sec:authorize>
                </div>
                </span>
            </li>

            <div class="collapse" id="button${count}">
                <div class="well">

                    <span id="${count}"></span>

                    <sec:authorize access="isAuthenticated()">
                        <sf:form action="${thread_url}/post/new" method="get">
                            <button type="submit" class="btn btn-success margin-top"><spring:message
                                    code="button.comment.new"/></button>
                        </sf:form>
                    </sec:authorize>
                </div>
            </div>
        </c:forEach>
    </ol>

    <ul class="pagination">
        <li class="disabled"><a>&laquo;</a></li>
        <c:forEach items="${pagination}" var="pageIndex">
            <c:choose>
                <c:when test="${pageIndex == currentPageIndex}">
                    <li class="active"><a>${pageIndex}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="/page/${pageIndex}">${pageIndex}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <li class="disabled"><a>&raquo;</a></li>
    </ul>
</div>

<script type="text/javascript">
    function getJson(thread_id, collapse_id) {
        $.ajax({
            type: 'GET',
            url: '/thread/' + thread_id + '.html',
            dataType: 'html',
            success: function (data) {
                $(collapse_id).html(data);
            }
        });
    }
</script>