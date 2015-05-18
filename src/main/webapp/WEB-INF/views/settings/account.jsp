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

<s:url var="user_url" value="/settings/account"/>

<div class="container col-md-8">
    <div class="panel panel-default">
        <div class="panel-heading"><b>Change password</b></div>
        <div class="panel-body">
            <form:form id="account_password_change" action="${user_url}" method="put" class="form-horizontal">
                <div class="form-group col-md-10">
                    <label for="old_password" class="col-md-6">Old password</label>
                    <input type="password" name="old_password" id="old_password"
                           class="form-control password required"
                           data-placement="bottom"
                           value=""
                           data-trigger="manual"
                           data-content="Must be at least 3 characters long, must contain 1 number, 1 lowercase, 1 uppercase letters">

                    <div class="container">
                            <%--<form:errors path="${status.expression}" cssClass="label label-danger"/>--%>
                    </div>
                </div>
                <div class="form-group col-md-10">
                    <label for="new_password" class="col-md-6">New password</label>
                    <input type="password" name="new_password" id="new_password"
                           class="form-control password required"
                           data-placement="bottom"
                           value=""
                           data-trigger="manual"
                           data-content="Must be at least 3 characters long, must contain 1 number, 1 lowercase, 1 uppercase letters">
                </div>
                <div class="form-group col-md-10">
                    <label for="repeat_password" class="col-md-6">Confirm new password</label>
                    <input type="password" name="repeat_password" id="repeat_password"
                           class="form-control password required"
                           data-placement="bottom"
                           value=""
                           data-trigger="manual"
                           data-content="Must be at least 3 characters long, must contain 1 number, 1 lowercase, 1 uppercase letters">
                </div>
            </form:form>
        </div>
        <div class="panel-footer">
            <button form="account_password_change" type="submit" class="btn btn-default">Update password</button>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading"><b>Change username</b></div>
        <div class="panel-body">
            <form:form id="account_username_change" modelAttribute="account" class="form-horizontal" method="put"
                       action="${user_url}">
                <div class="form-group col-md-10">
                    <label for="username" class="col-md-6"><spring:message code="label.username"/></label>
                    <spring:bind path="account.username">
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
            <button form="account_username_change" type="submit" class="btn btn-default">Update username</button>
        </div>
    </div>

    <div class="panel panel-danger">
        <div class="panel-heading"><b>Delete account</b></div>
        <div class="panel-body">
            Once you delete your account, there is no going back. Please be certain.
        </div>
        <div class="panel-footer">
            <button type="submit" class="btn btn-danger" disabled>Delete your account</button>
        </div>
    </div>
</div>