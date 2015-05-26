<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <title>${post.title}</title>
</head>

<div>
    <div>
        <s:url value="/post/{id}" var="post_url">
            <s:param name="id" value="${post.id}"/>
        </s:url>

        <h3><a class="label label-primary" href="${post_url}">
            <c:out value="${post.title}"/>
        </a>

            <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                <sec:authentication property="principal.username" var="authorizedUser"/>
                <c:choose>
                    <c:when test="${post.favorite == true}">
                        <button id="favorite${post.id}"
                                onclick="removeFavorite(${post.id}, '${authorizedUser}')"
                                style="float: right; margin-right: 20px"
                                class="btn btn-success btn-sm"><span class="glyphicon glyphicon-ok"></span>
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button id="favorite${post.id}"
                                onclick="addFavorite(${post.id}, '${authorizedUser}')"
                                style="float: right; margin-right: 20px"
                                class="btn btn-info btn-sm"><span class="glyphicon glyphicon-star"></span>
                        </button>
                    </c:otherwise>
                </c:choose>
            </sec:authorize>

        </h3>
        <c:forEach var="tag" items="${post.tags}">
            &nbsp;&nbsp;
                            <span class="tag label label-default">
                                <span>${tag.name}</span>
                            </span>
        </c:forEach>
        <div class="post">
            <div class="summernote">${post.text}</div>
        </div>

        <div class="formHolder author text-info">
                    <span class="padding-top">
                    <small><joda:format value="${post.when}" pattern="HH:mm MMM d, yyyy"/>
                        <c:out value="by ${post.user.username}"/></small>
                    </span>

            <sf:form action="${post_url}" method="delete">
                <input type="submit" class="btn btn-xs btn-danger" value="Delete"/>
            </sf:form>

            <sf:form action="${post_url}/edit" method="get">
                <input type="submit" class="btn btn-xs" value="Edit"/>
            </sf:form>
        </div>
    </div>


    <c:forEach var="post" items="${post.comments}">
        <div class="comment">
            <div class="summernote margin-bottom">${post.text}</div>

            <div class="formHolder author text-info">
                <small><joda:format value="${post.when}" pattern="HH:mm MMM d, yyyy"/>
                    <c:out value="by ${post.user.username}"/></small>

                <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                    <sec:authentication property="principal.username" var="authorizedUser"/>

                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <c:set var="access" value="${true}" scope="page"/>
                    </sec:authorize>

                    <c:if test="${authorizedUser.equals(post.user.username) or access}"> <!-- Shit -->

                        <sf:form action="${post_url}/comment/${post.id}" method="delete">
                            <input type="submit" class="btn btn-xs btn-danger" value="Delete"/>
                        </sf:form>

                        <sf:form action="${post_url}/comment/${post.id}/edit" method="get">
                            <input type="submit" class="btn btn-xs btn-default" value="Edit"/>
                        </sf:form>

                    </c:if>
                </sec:authorize>
            </div>
        </div>
    </c:forEach>

    <sec:authorize access="isAuthenticated()">
        <sf:form action="${post_url}/comment/new" method="get">
            <div class="submit">
                <button type="submit" class="btn btn-success margin-top"><spring:message
                        code="button.comment.new"/></button>
            </div>
        </sf:form>
    </sec:authorize>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('#summernote').summernote({
            height: 450
        });
    });
</script>