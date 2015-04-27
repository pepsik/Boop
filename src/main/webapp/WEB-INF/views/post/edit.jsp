<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div>

    <s:url value="/thread/${thread_id}/post/${post.id}" var="post_url"/>

    <h2>Edit Comment</h2>

    <div class="well">
        <sf:form modelAttribute="post" method="PUT" action="${post_url}">
            <form:textarea path="text" id="summernote"/>
            <sf:errors path="text"/>
            <br>

            <div class="spitItSubmitIt">
                <button type="submit" class="btn btn-success"><spring:message code="button.edit"/></button>
            </div>
        </sf:form>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('#summernote').summernote({
            height: 450,
            minHeight: 200,
            maxHeight: null,
            focus: true
        });
    });
</script>