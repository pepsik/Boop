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

                <div class="thread-text">
                    <c:out value="${thread.text}"/>
                </div>

                <div class="formHolder author text-info">
                    <span class="padding-top">
                    <small><joda:format value="${thread.when}" pattern="HH:mm MMM d, yyyy"/>
                        <c:out value="by ${thread.account.userName}"/></small>
                    </span>

                    <s:url value="collapseExample{id}" var="count">
                        <s:param name="id" value="${loop.count}"/>
                    </s:url>

                    <a class="btn btn-xs btn-success" data-toggle="collapse" href="#${count}"
                       aria-expanded="false" aria-controls="collapseExample">
                        Comments
                    </a>

                    <sf:form action="${thread_url}" method="delete">
                        <input type="submit" class="btn btn-xs btn-danger" value="Delete"/>
                    </sf:form>

                    <sf:form action="${thread_url}/edit" method="get">
                        <input type="submit" class="btn btn-xs" value="Edit"/>
                    </sf:form>
                </div>
                </span>
            </li>

            <div class="collapse" id="${count}">
                <div class="well">

                    <c:forEach var="post" items="${thread.posts}">
                        <li>
                            <c:out value="${post.text}"/>
                            <div class="formHolder author text-info">
                                <small><joda:format value="${thread.when}" pattern="HH:mm MMM d, yyyy"/>
                                    <c:out value="by ${thread.account.userName}"/></small>

                                <sf:form action="${thread_url}/post/${post.id}" method="delete">
                                    <input type="submit" class="btn btn-xs btn-danger" value="Delete"/>
                                </sf:form>

                                <sf:form action="${thread_url}/post/${post.id}/edit" method="get">
                                    <input type="submit" class="btn btn-xs btn-inverse" value="Edit"/>
                                </sf:form>
                            </div>
                        </li>
                    </c:forEach>

                    <sf:form action="${thread_url}/post/new" method="get">
                        <input type="submit" class="btn btn-success margin-top" value="New Post"/>
                    </sf:form>

                </div>
            </div>

        </c:forEach>

    </ol>
</div>

