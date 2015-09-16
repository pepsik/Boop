<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 5/30/15
  Time: 14:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<head>
    <title>Edit tag</title>
</head>

<s:url value="/tag/${tag.name}" var="tag_url"/>

<div>
    <div class="well">
        <sf:form modelAttribute="tag" method="put" action="${tag_url}">
            <div class="form-group">
                <sf:label path="name">Tag Name</sf:label>
                <sf:errors path="name" cssClass="label label-danger"/>
                <sf:input path="name" cssClass="form-control" maxlength="30"/>
            </div>
            <div class="form-group">
                <sf:label path="imageUrl">Image url</sf:label>
                <sf:errors path="imageUrl" cssClass="label label-danger"/>
                <sf:input path="imageUrl" cssClass="form-control" maxlength="100"/>
            </div>
            <div>
                <sf:errors path="description" cssClass="label label-danger"/>
                <form:textarea path="description" id="summernote"/>
            </div>
            <br>

            <div class="submit">
                <button type="submit" onclick="autolink('summernote')" class="btn btn-success"><spring:message
                        code="button.post.new.create"/></button>
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