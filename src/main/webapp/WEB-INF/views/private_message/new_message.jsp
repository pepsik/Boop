<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 5/26/15
  Time: 16:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>New Private message</title>
</head>

<div style="margin-top: 10px">
    <div class="well col-sm-9" style="padding: 5px">
        <sf:form modelAttribute="privateMessage" method="post" action="/messages">
            <sf:label path="recipient">Recipient name</sf:label>
            <div class="form-group">
                <sf:errors path="recipient" cssClass="label label-danger"/>
                <sf:input path="recipient" cssClass="form-control" maxlength="20"/>
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
        var editor = $('#summernote');      //exclude to js file?
        editor.summernote({
            lang: '<spring:message code="summernote.lang"/>',  //wtf
            height: 250,
            minHeight: 100,
            maxHeight: null
        });

        $('a[href="' + this.location.pathname + '"]').addClass('active');
    });
</script>