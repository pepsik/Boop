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

<div>

    <s:url value="/thread/new" var="new_thread_url"/>
    <a href="${new_thread_url}">
        <c:out value="New Thread"/>
    </a>

    <ol class="spittle-list">
        <c:forEach var="thread" items="${threadList}">

            <s:url value="/thread/{id}" var="thread_url">
                <s:param name="id" value="${thread.id}"/>
            </s:url>


            <li><span class="postListText">
                <a class="title-hyperlink" href="${thread_url}">
                    <c:out value="${thread.title}"/>
                </a> <br>
                <c:out value="${thread.text}"/>  </br>
                <small class="date"><joda:format value="${thread.when}" pattern="HH:mm MMM d, yyyy"/></small>
                </span>
            </li>
        </c:forEach>

    </ol>
</div>

