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
    <ol>
        <c:forEach var="comment" items="${postList}" varStatus="loop">
            <s:url value="/post/{id}" var="post_url">
                <s:param name="id" value="${comment.id}"/>
            </s:url>
            <s:url value="/user/{id}" var="user_url">
                <s:param name="id" value="${comment.user.username}"/>
            </s:url>

            <li type="none" class="spittle-list">
                <div class="postListText">

                    <h3><a class="label label-primary" href="${post_url}">
                        <c:out value="${comment.title}"/>
                    </a>
                        <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                            <sec:authentication property="principal.username" var="authorizedUser"/>
                            <c:choose>
                                <c:when test="${comment.favorite == true}">
                                    <button id="favorite${comment.id}"
                                            onclick="removeFavorite(${comment.id}, '${authorizedUser}')"
                                            style="float: right; margin-right: 20px"
                                            class="btn btn-success btn-sm"><span class="glyphicon glyphicon-ok"></span>
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button id="favorite${comment.id}"
                                            onclick="addFavorite(${comment.id}, '${authorizedUser}')"
                                            style="float: right; margin-right: 20px"
                                            class="btn btn-info btn-sm"><span class="glyphicon glyphicon-star"></span>
                                    </button>
                                </c:otherwise>
                            </c:choose>
                        </sec:authorize>
                    </h3>

                    <div class="post summernote">
                            ${comment.text}
                    </div>
                    <div class="formHolder author text-info">
                        <small><joda:format value="${comment.when}" pattern="HH:mm MMM d, yyyy"/>
                            <c:out value="by "/>
                            <a href="${user_url}">${comment.user.username}</a>
                        </small>
                        <button class="btn btn-xs btn-success" type="button" data-toggle="collapse"
                                data-target="#button${loop.count}">
                            <spring:message code="button.comment.hide"/> &nbsp;<span
                                class="badge">${comment.comments.size()}</span>
                        </button>
                        <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                            <%--<sec:authentication property="principal.username" var="authorizedUser"/>--%>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <c:set var="access" value="${true}" scope="page"/>
                            </sec:authorize>
                            <c:if test="${authorizedUser.equals(comment.user.username) or access}">
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
            </li>
            <div class="collapse well" id="button${loop.count}">
                <span id="responseCollapse${loop.count}"></span>
            </div>

            <script type="text/javascript">
                $(document).ready(function () {
                    $("#button" + '${loop.count}').on('show.bs.collapse', function () {               //exclude to js file?
                        getComments(${comment.id}, ${loop.count});
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
            <c:when test="${pagination.get(pagination.size()- 1) != currentPageIndex}">
                <li><a href="/page/${currentPageIndex + 1}">&raquo;</a></li>
            </c:when>
        </c:choose>
    </ul>
</div>

