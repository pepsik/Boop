<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 5/17/15
  Time: 21:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>Emails</title>
</head>

<div class="container col-md-8">
    <div class="panel panel-default">
        <div class="panel-heading"><b>Email</b></div>
        <div class="panel-body">
            <form:form id="email_form" modelAttribute="emailForm" class="form-horizontal" method="put"
                       action="/settings/emails">
                <div class="form-group  col-md-10">
                    <label for="email" class="col-md-6"><spring:message code="label.email"/></label>
                    <input type="email" name="email" id="email"
                           class="form-control email required"
                           data-placement="bottom"
                           value="${emailForm.email}"
                           data-trigger="manual"
                           data-content="Must be a valid e-mail address (user@gmail.com)"/>
                </div>
                <form:errors path="email" cssClass="label label-danger"/>
            </form:form>
        </div>
        <div class="panel-footer">
            <button form="email_form" type="submit" class="btn btn-default">Update email</button>
        </div>
    </div>
</div>