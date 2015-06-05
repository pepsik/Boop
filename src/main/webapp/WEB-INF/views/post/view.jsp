<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <title>${post.title}</title>
</head>

<s:url value="/user/${post.user.username}/profile" var="profile_url"/>
<s:url value="/post/${post.id}/edit" var="post_edit_url"/>
<s:url value="/post/${post.id}" var="post_url"/>
<s:url value="${post_url}/comment/${post.id}" var="comment_url"/>
<s:url value="${post_url}/comment/${post.id}/edit" var="comment_edit_url"/>
<s:url value="${post_url}/comment/new" var="new_comment_url"/>

<div>
    <div>
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
            <s:url value="/tag/${tag.name}" var="tag_url"/>
            &nbsp;&nbsp;
            <a href="${tag_url}" class="tag label label-default"> ${tag.name} </a>
        </c:forEach>

        <div class="post">
            <div class="summernote">${post.text}</div>
        </div>

        <div class="formHolder author text-info">
            <div class="row">
                <div class="col-md-6">
                    <small><joda:format value="${post.when}" pattern="HH:mm MMM d, yyyy"/></small>
                    by&nbsp;
                    <img src="/uploads/avatars/${post.user.username}.jpeg"
                         width="40px" class="img-rounded"
                         onError="this.src='/uploads/avatars/def-ava.png';"/>
                    <a href="${profile_url}">${post.user.username}</a>
                </div>
                <div class="col-md-3">
                </div>
                <div class="col-md-3">
                    <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <c:set var="access" value="${true}" scope="page"/>
                        </sec:authorize>
                        <c:if test="${authorizedUser.equals(post.user.username) or access}">
                            <sf:form action="${post_url}" method="delete">
                                <button type="submit" class="btn btn-xs btn-danger">
                                    <span class="glyphicon glyphicon-trash"></span>
                                    <spring:message code="button.delete"/>
                                </button>
                            </sf:form>
                            <sf:form action="${post_edit_url}" method="get">
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

                        <sf:form action="${comment_url}" method="delete">
                            <input type="submit" class="btn btn-xs btn-danger" value="Delete"/>
                        </sf:form>

                        <sf:form action="${comment_edit_url}" method="get">
                            <input type="submit" class="btn btn-xs btn-default" value="Edit"/>
                        </sf:form>

                    </c:if>
                </sec:authorize>
            </div>
        </div>
    </c:forEach>

    <sec:authorize access="isAuthenticated()">
        <sf:form action="${new_comment_url}" method="get">
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