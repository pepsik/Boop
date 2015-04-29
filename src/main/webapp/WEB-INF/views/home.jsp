<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 4/27/15
  Time: 23:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
            <s:url value="/account/{id}" var="account_url">
                <s:param name="id" value="${thread.account.id}"/>
            </s:url>

            <li>
                <div class="postListText">
                    <h3><a class="label label-primary" href="${thread_url}">
                        <c:out value="${thread.title}"/></a></h3>
                <span class="thread summernote">
                        ${thread.text}</span>

                    <div class="formHolder author text-info">

                    <span class="padding-top">
                    <small><joda:format value="${thread.when}" pattern="HH:mm MMM d, yyyy"/>
                        <c:out value="by "/>
                        <a href="${account_url}">${thread.account.username}</a></small>
                    </span>
                        <button class="btn btn-xs btn-success" type="button" data-toggle="collapse"
                                data-target="#button${loop.count}">
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
                </div>
            </li>

            <div class="collapse well" id="button${loop.count}">
                <span id="response${loop.count}"></span>

                <h2>New Comment</h2>
                <form:form modelAttribute="post" method="post" id="postForm${loop.count}">
                    <form:textarea path="text" id="summernote${loop.count}"/>
                    <div class="spitItSubmitIt"><br>
                        <button type="submit" class="btn btn-success">
                            <spring:message code="button.comment.post"/>
                        </button>
                    </div>
                </form:form>
            </div>

            <script type="text/javascript">
                $(document).ready(function () {
                    var wysiwygNumber = "summernote" + ${loop.count};
                    var editor = $("#" + wysiwygNumber);
                    editor.summernote({
                        height: 200,
                        minHeight: 200,
                        maxHeight: null
                    });

                    $("#postForm" + ${loop.count}).submit(function (event) {
                        event.preventDefault();

                        var form = $(this),
                                postMessage = editor.code();
                        editor.code("");
                        var json = {text: postMessage};
                        $.ajax({
                            type: 'POST',
                            url: '/thread/' + ${thread.id} +'/addcomment',
                            data: JSON.stringify(json),

                            beforeSend: function (xhr) {
                                xhr.setRequestHeader("Content-Type", "application/json");
                            },
                            success: function (response) {
                                <%--$("#response" + ${loop.count}).html(response);--%>

                                var newComment = document.createElement("DIV");
                                newComment.className += "post";

                                var textWrap = document.createElement("DIV");
                                textWrap.className += "summernote margin-bottom";
                                var textComment = document.createTextNode(postMessage);
                                textWrap.appendChild(textComment);

                                var dateWrap = document.createElement("DIV");
                                dateWrap.className += "text-info author";
                                var textDate = document.createTextNode("date here");
                                dateWrap.appendChild(textDate);

                                newComment.appendChild(textWrap);
                                newComment.appendChild(dateWrap);
                                document.getElementById("response${loop.count}").appendChild(newComment);
                            }
                        });
                    });

                    $("#button" + ${loop.count}).on('show.bs.collapse', function () {
                        $.ajax({
                            type: 'GET',
                            url: '/thread/' + ${thread.id} +'/comments.html',
                            dataType: 'html',
                            success: function (response) {
                                $("#response" + ${loop.count}).html(response);
                            }
                        });
                    });
                });
            </script>
        </c:forEach>
    </ol>

    <ul class="pagination">
        <c:choose>
            <c:when test="${1 != currentPageIndex}">
                <li><a href="/page/${currentPageIndex - 1}">&laquo;</a></li>
            </c:when>
        </c:choose>

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

        <c:choose>
            <c:when test="${pagination[3] != null}">
                <li><a href="/page/${currentPageIndex + 1}">&raquo;</a></li>
            </c:when>
        </c:choose>
    </ul>
</div>