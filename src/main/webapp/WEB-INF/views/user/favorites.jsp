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
  Date: 5/22/15
  Time: 15:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal.username" var="authorizedUser"/>
</sec:authorize>
<head>
    <title>Favorites / ${username}</title>
</head>

<div class="container-fluid">
    <br>
    <ul class="nav nav-tabs">
        <li><a href="/user/${username}">Public Profile</a></li>
        <li><a href="/user/${username}/posts/1">Posts&nbsp;&nbsp;<span class="badge">${postsCount}</span></a></li>
        <li><a href="/user/${username}/comments/1">Comments&nbsp;&nbsp;<span class="badge">${commentsCount}</span></a></li>
        <li class="active"><a href="#">Favorites&nbsp;&nbsp;<span class="badge">${favoritesCount}</span></a></li>
        <li><a href="#">Friends</a></li>
    </ul>
    <br>
</div>

<div class="container-fluid">
    <h2><span class="label label-default">Favorites &nbsp;${username}</span></h2>
    <ol>
        <c:forEach var="favorite" items="${favoriteList}" varStatus="loop">
            <s:url value="/post/{id}" var="post_url">
                <s:param name="id" value="${favorite.post.id}"/>
            </s:url>
            <s:url value="/user/{id}" var="user_url">
                <s:param name="id" value="${favorite.post.user.username}"/>
            </s:url>

            <li type="none" class="spittle-list">
                <div class="postListText">
                    <h3><a class="label label-primary" href="${post_url}">
                        <c:out value="${favorite.post.title}"/>
                    </a>
                        <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                            <c:choose>
                                <c:when test="${favorite.post.favorite == true}">
                                    <button id="favorite${favorite.post.id}"
                                            onclick="removeFavorite(${favorite.post.id}, '${authorizedUser}')"
                                            style="float: right; margin-right: 20px"
                                            class="btn btn-success btn-sm"><span class="glyphicon glyphicon-ok"></span>
                                        &nbsp;<b>added</b>&nbsp;<joda:format value="${favorite.addedDate}"
                                                                             pattern="d/M/yy"/>
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button id="favorite${favorite.post.id}"
                                            onclick="addFavorite(${favorite.post.id}, '${authorizedUser}')"
                                            style="float: right; margin-right: 20px"
                                            class="btn btn-info btn-sm"><span class="glyphicon glyphicon-star"></span>
                                    </button>
                                </c:otherwise>
                            </c:choose>
                        </sec:authorize>

                    </h3>
                    <c:forEach var="tag" items="${favorite.post.tags}">
                        &nbsp;&nbsp;
                        <a href="/tag/${tag.name}" class="tag label label-default"> ${tag.name} </a>
                    </c:forEach>

                    <article>
                        <div class="post summernote">
                                ${favorite.post.text}
                        </div>
                    </article>

                    <div class="formHolder author text-info">
                        <%--<small><joda:format value="${favorite.post.when}" pattern="HH:mm MMM d, yyyy"/>--%>
                            <%--<c:out value="by "/>--%>
                            <%--<a href="${user_url}">${favorite.post.user.username}</a>--%>
                        <%--</small>--%>
                        <%--<button class="btn btn-xs btn-success" type="button" data-toggle="collapse"--%>
                                <%--data-target="#button${loop.count}">--%>
                            <%--<spring:message code="button.comment.hide"/> &nbsp;<span--%>
                                <%--class="badge">${favorite.post.comments.size()}</span>--%>
                        <%--</button>--%>
                        <%--<sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">--%>
                            <%--<sec:authorize access="hasRole('ROLE_ADMIN')">--%>
                                <%--<c:set var="access" value="${true}" scope="page"/>--%>
                            <%--</sec:authorize>--%>
                            <%--<c:if test="${authorizedUser.equals(favorite.post.user.username) or access}">--%>
                                <%--<sf:form action="${post_url}" method="delete">--%>
                                    <%--<button type="submit" class="btn btn-xs btn-danger">--%>
                                        <%--<spring:message code="button.delete"/>--%>
                                    <%--</button>--%>
                                <%--</sf:form>--%>
                                <%--<sf:form action="${post_url}/edit" method="get">--%>
                                    <%--<button type="submit" class="btn btn-xs">--%>
                                        <%--<spring:message code="button.edit"/>--%>
                                    <%--</button>--%>
                                <%--</sf:form>--%>
                            <%--</c:if>--%>
                        <%--</sec:authorize>--%>
                            <div class="row">
                                <div class="col-md-6">
                                    <small><joda:format value="${favorite.post.when}" pattern="HH:mm MMM d, yyyy"/></small>
                                    by&nbsp;
                                    <img src="${pageContext.request.contextPath}/resources/images/avatars/${favorite.post.user.username}.jpeg"
                                         alt=""
                                         width="40px" class="img-rounded"
                                         onError="this.src='<s:url value="${pageContext.request.contextPath}/resources/images/avatars"/>/def-ava.png';"/>
                                    <a href="${user_url}">${favorite.post.user.username}</a>
                                    &nbsp;&nbsp;
                                    <button class="btn btn-xs btn-default" type="button" data-toggle="collapse"
                                            data-target="#button${loop.count}">
                                        <spring:message code="button.comment.hide"/> &nbsp;<span
                                            class="badge">${favorite.post.comments.size()}</span>
                                    </button>
                                </div>
                                <div class="col-md-3">
                                </div>
                                <div class="col-md-3">
                                    <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                                            <c:set var="access" value="${true}" scope="page"/>
                                        </sec:authorize>
                                        <c:if test="${authorizedUser.equals(favorite.post.user.username) or access}">
                                            <sf:form action="${post_url}" method="delete">
                                                <button type="submit" class="btn btn-xs btn-danger">
                                                    <span class="glyphicon glyphicon-trash"></span>
                                                    <spring:message code="button.delete"/>
                                                </button>
                                            </sf:form>
                                            <sf:form action="${post_url}/edit" method="get">
                                                <button type="submit" class="btn btn-xs">
                                                    <span class="glyphicon glyphicon-pencil"></span>
                                                    <spring:message code="button.edit"/>
                                                </button>
                                            </sf:form>
                                        </c:if>
                                    </sec:authorize>
                                </div>
                            </div>
                    </div>
                </div>
            </li>
            <div class="collapse well" id="button${loop.count}">
                <span id="responseCollapse${loop.count}"></span>
            </div>

            <script type="text/javascript">
                var collapseButton = $("#button" + '${loop.count}');
                collapseButton.on('show.bs.collapse', function () {               //exclude to js file?
                    getComments(${favorite.post.id}, ${loop.count});
                });

                collapseButton.on('shown.bs.collapse', function (e) {
                    var id = $(e.target).prev().find("[id]")[0].id;
                    navigateToElement(id);
                })

                function navigateToElement(id) {
                    $('html, body').animate({
                        scrollTop: $("#" + id).offset().top
                    }, 1000);
                }
            </script>

        </c:forEach>
    </ol>

    <ul class="pagination">
        <c:choose>
            <c:when test="${1 != currentPageIndex}">
                <li><a href="/user/${username}/favorites/${currentPageIndex - 1}">&laquo;</a></li>
            </c:when>
        </c:choose>
        <c:forEach items="${pagination}" var="pageIndex">
            <c:choose>
                <c:when test="${pageIndex == currentPageIndex}">
                    <li class="active"><a>${pageIndex}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="/user/${username}/favorites/${pageIndex}">${pageIndex}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${pagination.get(pagination.size()- 1) != currentPageIndex}">
                <li><a href="/user/${username}/favorites/${currentPageIndex + 1}">&raquo;</a></li>
            </c:when>
        </c:choose>
    </ul>
</div>

<script src="${pageContext.request.contextPath}/bower/readmore/readmore.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/readmore_conf.js"></script>

