<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%--<s:url value="/thread/{id}" var="thread_url">--%>
<%--<s:param name="id" value="${thread_id}"/>--%>
<%--</s:url>--%>

<div>
    <h2>New Comment</h2>

    <div class="well">
        <sf:form modelAttribute="post" method="post" id="postForm${count}">
            <form:textarea path="text" id="summernote${thread_id}"/>
            <br>

            <div class="spitItSubmitIt">
                <button type="submit" class="btn btn-success"><spring:message code="button.comment.post"/></button>
            </div>
        </sf:form>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $("#summernote" + "${thread_id}").summernote({
            height: 200,
            minHeight: 200,
            maxHeight: null,
            focus: true
        });
    });
</script>


