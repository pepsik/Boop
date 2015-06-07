<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 5/17/15
  Time: 20:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title><spring:message code="label.account"/></title>
</head>

<s:url value="/settings/account/password" var="change_password_url"/>
<s:url value="/settings/account/username" var="change_username_url"/>

<div class="container col-md-8">
    <div class="panel panel-default">
        <div class="panel-heading"><b><spring:message code="label.password.change"/></b></div>
        <div class="panel-body">
            <form:form id="account_password_change" modelAttribute="passwordForm" method="put" class="form-horizontal"
                       action="${change_password_url}">
                <div class="form-group col-md-10">
                    <label for="old_password" class="col-md-6"><spring:message code="label.password.old"/></label>
                    <input type="password" name="old_password" id="old_password"
                           class="form-control password required"
                           data-placement="bottom"
                           value=""
                           data-trigger="manual"
                           data-content="Must be at least 3 characters long, must contain 1 number, 1 lowercase, 1 uppercase letters">
                    <form:errors path="old_password" cssClass="label label-danger"/>
                </div>
                <div class="form-group col-md-10">
                    <label for="new_password" class="col-md-6"><spring:message code="label.password.new"/></label>
                    <input type="password" name="new_password" id="new_password"
                           class="form-control password required"
                           data-placement="bottom"
                           value=""
                           data-trigger="manual"
                           data-content="Must be at least 3 characters long, must contain 1 number, 1 lowercase, 1 uppercase letters">
                    <form:errors path="new_password" cssClass="label label-danger"/>
                </div>
                <div class="form-group col-md-10">
                    <label for="repeat_new_password" class="col-md-6"><spring:message code="label.password.confirm"/></label>
                    <input type="password" name="repeat_new_password" id="repeat_new_password"
                           class="form-control password required"
                           data-placement="bottom"
                           value=""
                           data-trigger="manual"
                           data-content="Must be at least 3 characters long, must contain 1 number, 1 lowercase, 1 uppercase letters">
                    <form:errors path="repeat_new_password" cssClass="label label-danger"/>
                </div>
            </form:form>
        </div>
        <div class="panel-footer">
            <button form="account_password_change" type="submit" class="btn btn-success"><spring:message code="button.password.update"/></button>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading"><b><spring:message code="label.username.change"/></b></div>
        <div class="panel-body">
            <form:form id="account_username_change" modelAttribute="user" class="form-horizontal" method="put"
                       action="${change_username_url}">
                <div class="form-group col-md-10">
                    <label for="username" class="col-md-6"><spring:message code="label.username"/></label>
                    <spring:bind path="user.username">
                        <input type="text" name="${status.expression}" id="username"
                               class="form-control username required"
                               data-placement="bottom"
                               value="${status.value}"
                               data-trigger="manual"
                               data-content="Must be at least 3 characters long, and must only contain letters">

                        <div class="container">
                            <form:errors path="${status.expression}" cssClass="label label-danger"/>
                        </div>
                    </spring:bind>
                </div>
            </form:form>
        </div>
        <div class="panel-footer">
            <button form="account_username_change" type="submit" class="btn btn-success"><spring:message code="button.username.update"/></button>
        </div>
    </div>

    <div class="panel panel-danger">
        <div class="panel-heading"><b><spring:message code="label.user.delete"/></b></div>
        <div class="panel-body">
            Once you delete your user, there is no going back. Please be certain.
        </div>
        <div class="panel-footer">
            <button type="submit" class="btn btn-danger" disabled><spring:message code="button.delete.user"/></button>
        </div>
    </div>
</div>