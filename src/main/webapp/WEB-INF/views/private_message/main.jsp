<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 5/26/15
  Time: 16:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>PMs</title>
</head>

<div>
    <div class="col-sm-3">
        <ul class="nav nav-pills nav-stacked">
            <li role="presentation" class="active"><a href="/messages">New private message</a></li>
            <li role="presentation"><a href="/messages/input">Input</a></li>
            <li role="presentation"><a href="/messages/output">Output</a></li>
        </ul>
    </div>

    <div class="col-sm-9">
        <div id="summernoteEditor">Not Working yet</div>
        <div class="submit"><br>
            <button type="submit" class="btn btn-success">
                <spring:message code="button.comment.post"/>
            </button>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        var editor = $('#summernoteEditor');      //exclude to js file?
        editor.summernote({
            lang: '<spring:message code="summernote.lang"/>',  //wtf
            height: 250,
            minHeight: 100,
            maxHeight: null
        });

        $('a[href="' + this.location.pathname + '"]').addClass('active');
    });
</script>