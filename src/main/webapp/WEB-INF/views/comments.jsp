<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<c:forEach var="post" items="${postList}">
    <div class="post" id="${post.id}">
        <div class="summernote margin-bottom">${post.text}</div>
        <div class="author text-info">
            <s:url value="/account/{id}" var="account_url">
                <s:param name="id" value="${post.account.id}"/>
            </s:url>
            <small><joda:format value="${post.when}" pattern="HH:mm MMM d, yyyy"/>
                <c:out value="by "/>
                <a href="${account_url}">${post.account.username}</a></small>

            <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                <sec:authentication property="principal.username" var="authorizedUser"/>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <c:set var="access" value="${true}" scope="page"/>
                </sec:authorize>

                <c:if test="${authorizedUser.equals(post.account.username) or access}">
                    <div style="float: right">
                        <button class="btn btn-xs btn-default" onclick="editCommentAjax(${post.thread.id}, ${post.id})">Edit</button>
                        <button class="btn btn-xs btn-danger"
                                onclick="deleteCommentAjax(${post.thread.id}, ${post.id})">
                            Delete
                        </button>
                    </div>
                </c:if>
            </sec:authorize>
        </div>
    </div>
</c:forEach>

<script type="text/javascript">
    function deleteCommentAjax(thread_id, post_id) {
        $("#" + post_id).remove();

        $.ajax({
            type: 'DELETE',
            url: "/thread/" + thread_id + "/post/" + post_id + "/delete"
        });
    }

    function editCommentAjax(thread_id, post_id) {
//        $.ajax({
//            type: 'DELETE',
//            url: "/thread/" + thread_id + "/post/" + post_id + "/delete"
//        });
    }
</script>

