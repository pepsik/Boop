<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>
    <div class="well">
        <sf:form modelAttribute="post" method="post" action="/post">
            <div class="form-group">
                <sf:label path="title"><spring:message code="label.post.create.title"/></sf:label>
                <sf:input path="title" cssClass="form-control" maxlength="20"/>
            </div>
            <form:textarea path="text" id="summernote"/>
            <sf:errors path="text"/>
            <br>

            <div class="submit">
                <button type="submit" class="btn btn-success"><spring:message code="button.post.new.create"/></button>
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