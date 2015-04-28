<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 4/27/15
  Time: 23:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <ol class="spittle-list">
        <c:forEach var="thread" items="${threadList}" varStatus="loop">

            <s:url value="/thread/{id}" var="thread_url">
                <s:param name="id" value="${threadList[0].id}"/>
            </s:url>
            <s:url value="/account/{id}" var="account_url">
                <s:param name="id" value="${threadList[0].account.id}"/>
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
                    $("#" + wysiwygNumber).summernote({
                        height: 200,
                        minHeight: 200,
                        maxHeight: null
                    });

                    $("#postForm" + ${loop.count}).submit(function (event) {
                        event.preventDefault();

                        var form = $(this),
                                url = form.attr('action'),
                                postMessage = $("#" + wysiwygNumber).code(),
                                json = {text: postMessage};
                        $.ajax({
                            type: 'POST',
                            url: '/thread/' + ${thread.id} +'/addcomment',
                            data: JSON.stringify(json),

                            beforeSend: function (xhr) {
                                xhr.setRequestHeader("Content-Type", "application/json");
                            },
                            success: function (response) {
                                $("#response" + ${loop.count}).html(response);
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
</div>