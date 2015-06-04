<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<s:url value="${relativePath}/post/${post_id}/comment" var="comment_url"/>

<div>
    <h2>New Comment</h2>

    <div class="well">
        <sf:form modelAttribute="comment" method="post" id="commentForm" action="${comment_url}">
            <form:textarea path="text" id="summernote"/>
            <br>

            <div class="submit">
                <button type="submit" class="btn btn-success"><spring:message code="button.comment.post"/></button>
            </div>
        </sf:form>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $("#summernote").summernote({
            height: 200,
            minHeight: 200,
            maxHeight: null,
            focus: true
        });
    });
</script>


