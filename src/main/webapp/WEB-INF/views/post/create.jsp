<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<head>
    <title>New post</title>
</head>

<s:url value="/post/" var="post_url"/>

<div>
    <div class="well">
        <sf:form modelAttribute="post" method="post" action="${post_url}">
            <sf:label path="title"><spring:message code="label.post.title"/></sf:label>
            <div class="form-group">
                <sf:errors path="title" cssClass="label label-danger"/>
                <sf:input path="title" cssClass="form-control" maxlength="40"/>
            </div>
            <div>
                <spring:bind path="post.tags">
                    <label id="${status.expression}">Tags</label>
                    <input type="text" name="${status.expression}" id="tags" value="${status.value}"
                           data-role="tagsinput"/>
                </spring:bind>
            </div>

            <div>
                <sf:errors path="text" cssClass="label label-danger"/>
                <form:textarea path="text" id="summernote"/>
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
            focus: true,
            onImageUpload: function (files, editor, welEditable) {
                sendFile(files[0], editor, welEditable);
            }
        });
        function sendFile(file, editor, welEditable) {
            var formData = new FormData();
            formData.append("image", file);
            $.ajax({
                data: formData,
                type: "POST",
                url: "/user/upload/image",
                cache: false,
                contentType: false,
                processData: false,
                success: function (url) {
                    editor.insertImage(welEditable, url);
                }
            });
        }
    });
</script>