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

<div>
    <s:url value="/thread/new" var="new_thread_url"/>


    <h2><a href="${new_thread_url}" class="btn btn-primary">
        <c:out value="New Thread"/>
    </a></h2> <br>

    <ol class="spittle-list">
        <c:forEach var="thread" items="${threadList}" varStatus="loop">

            <s:url value="/thread/{id}" var="thread_url">
                <s:param name="id" value="${thread.id}"/>
            </s:url>

            <li><span class="postListText">
                <h3><a class="label label-primary" href="${thread_url}">
                    <c:out value="${thread.title}"/>
                </a></h3>

                <div class="thread">
                    <c:out value="${thread.text}"/>
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

                    <s:url value="collapseExample{id}" var="count">
                        <s:param name="id" value="${loop.count}"/>
                    </s:url>

                    <a class="btn btn-xs btn-success" data-toggle="collapse" href="#${count}"
                       aria-expanded="false" aria-controls="collapse">
                        Comments (${thread.posts.size()})
                    </a>

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

            <div class="collapse" id="${count}">
                <div class="well">

                    <c:forEach var="post" items="${thread.posts}">
                        <div class="post">
                            <c:out value="${post.text}"/>
                            <div class="formHolder author text-info">

                                <s:url value="/account/{id}" var="account_url">
                                    <s:param name="id" value="${post.account.id}"/>
                                </s:url>

                                <small><joda:format value="${thread.when}" pattern="HH:mm MMM d, yyyy"/>
                                    <c:out value="by "/>
                                    <a href="${account_url}">${post.account.username}</a></small>

                                <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                                    <sec:authentication property="principal.username" var="authorizedUser"/>

                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <c:set var="access" value="${true}" scope="page"/>
                                    </sec:authorize>

                                    <c:if test="${authorizedUser.equals(post.account.username) or access}"> <!-- Shit -->

                                        <sf:form action="${thread_url}/post/${post.id}" method="delete">
                                            <input type="submit" class="btn btn-xs btn-danger" value="Delete"/>
                                        </sf:form>

                                        <sf:form action="${thread_url}/post/${post.id}/edit" method="get">
                                            <input type="submit" class="btn btn-xs btn-default" value="Edit"/>
                                        </sf:form>

                                    </c:if>
                                </sec:authorize>
                            </div>
                        </div>
                    </c:forEach>

                    <sec:authorize access="isAuthenticated()">
                        <sf:form action="${thread_url}/post/new" method="get">
                            <input type="submit" class="btn btn-success margin-top" value="New Comment"/>
                        </sf:form>
                    </sec:authorize>
                </div>
            </div>

        </c:forEach>

    </ol>
</div>

