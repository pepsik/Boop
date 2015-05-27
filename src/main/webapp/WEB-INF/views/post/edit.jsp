<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <title>Post edit</title>
</head>

<div>
    <s:url value="/post/${post.id}" var="post_url"/>

    <div class="well">
        <sf:form modelAttribute="post" method="PUT" action="${post_url}">
            <sf:label path="title"><spring:message code="label.post.edit.title"/></sf:label>
            <div class="form-group">
                <sf:errors path="title" cssClass="label label-danger"/>
                <sf:input path="title" cssClass="form-control" maxlength="40"/>
            </div>
            <div>
                <spring:bind path="post.tags">
                    <input type="text" name="${status.expression}" id="tags" value="${status.value}"
                           data-role="tagsinput"/>
                </spring:bind>
            </div>
            <sf:errors path="text" cssClass="label label-danger"/> <br>
            <form:textarea path="text" id="summernote"/>
            <br>

            <div class="submit">
                <button type="submit" onclick="autolink('summernote')" class="btn btn-success"><spring:message
                        code="button.edit"/></button>
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
