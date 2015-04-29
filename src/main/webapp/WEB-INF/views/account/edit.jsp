<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 4/16/15
  Time: 21:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${pageContext.request.contextPath}/resources/js/accountValidation.js"></script>

<div>
    <s:url var="account_url" value="/account/${account.id}"/>

    <form:form modelAttribute="account" class="form-horizontal col-sm-5" method="put" action="${account_url}">
        <div class="form-group">
            <label for="email" class="col-xs-6"><spring:message code="label.email"/></label>
            <spring:bind path="account.email">
                <input type="text" name="${status.expression}" id="email"
                       class="form-control email required"
                       data-placement="bottom"
                       value="${status.value}"
                       data-trigger="manual"
                       data-content="Must be a valid e-mail address (user@gmail.com)">
            </spring:bind>
        </div>
        <div class="form-group">
            <label for="username" class="col-xs-6"><spring:message code="label.username"/></label>
            <spring:bind path="account.username">
                <input type="text" name="${status.expression}" id="username"
                       class="form-control username required"
                       data-placement="bottom"
                       value="${status.value}"
                       data-trigger="manual"
                       data-content="Must be at least 3 characters long, and must only contain letters">
            </spring:bind>
        </div>
        <div class="form-group">
            <label for="password" class="col-xs-6"><spring:message code="label.password"/></label>
            <spring:bind path="account.password">
                <input type="password" name="${status.expression}" id="password"
                       class="form-control password required"
                       data-placement="bottom"
                       value="${status.value}"
                       data-trigger="manual"
                       data-content="Must be at least 3 characters long, and must have 1 upper/lowercase letter and 1 number">
            </spring:bind>
        </div>
        <div class="form-group">
            <label for="fullname" class="col-xs-6"><spring:message code="label.fullname"/></label>
            <spring:bind path="account.fullname">
                <input type="text" name="${status.expression}" id="fullname"
                       class="form-control name required"
                       data-placement="bottom"
                       value="${status.value}"
                       data-trigger="manual"
                       data-content="Must be at least 3 characters long, and must only contain letters">
            </spring:bind>
        </div>
        <div class="form-group">
            <label for="birthdate" class="col-xs-6"><spring:message code="label.birthdate"/></label>
            <spring:bind path="account.birthdate">
                <input type="date" name="${status.expression}" id="birthdate"
                       class="form-control date required"
                       value="${status.value}"/>
            </spring:bind>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-success"><spring:message code="button.finish"/></button>
            <button type="reset" class="btn"><spring:message code="button.clear"/></button>
        </div>

        <p class="help-block pull-left text-danger hide" id="form-error">&nbsp; The form is not
            valid.</p>
    </form:form>

</div>