<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div>
    <div>
        <s:url value="/post/{id}" var="post_url">
            <s:param name="id" value="${post.id}"/>
        </s:url>

        <h3><a class="label label-primary" href="${post_url}">
            <c:out value="${post.title}"/>
        </a></h3>

        <div class="post">
            <div class="summernote">${post.text}</div>
        </div>

        <div class="formHolder author text-info">
                    <span class="padding-top">
                    <small><joda:format value="${post.when}" pattern="HH:mm MMM d, yyyy"/>
                        <c:out value="by ${post.account.username}"/></small>
                    </span>

            <sf:form action="${post_url}" method="delete">
                <input type="submit" class="btn btn-xs btn-danger" value="Delete"/>
            </sf:form>

            <sf:form action="${post_url}/edit" method="get">
                <input type="submit" class="btn btn-xs" value="Edit"/>
            </sf:form>
        </div>
    </div>


    <c:forEach var="comment" items="${post.comments}">
        <div class="comment">
            <div class="summernote margin-bottom">${comment.text}</div>

            <div class="formHolder author text-info">
                <small><joda:format value="${comment.when}" pattern="HH:mm MMM d, yyyy"/>
                    <c:out value="by ${comment.account.username}"/></small>

                <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                    <sec:authentication property="principal.username" var="authorizedUser"/>

                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <c:set var="access" value="${true}" scope="page"/>
                    </sec:authorize>

                    <c:if test="${authorizedUser.equals(comment.account.username) or access}"> <!-- Shit -->

                        <sf:form action="${post_url}/comment/${comment.id}" method="delete">
                            <input type="submit" class="btn btn-xs btn-danger" value="Delete"/>
                        </sf:form>

                        <sf:form action="${post_url}/comment/${comment.id}/edit" method="get">
                            <input type="submit" class="btn btn-xs btn-default" value="Edit"/>
                        </sf:form>

                    </c:if>
                </sec:authorize>
            </div>
        </div>
    </c:forEach>

    <sec:authorize access="isAuthenticated()">
        <sf:form action="${post_url}/comment/new" method="get">
            <div >
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