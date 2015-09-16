<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 7/3/15
  Time: 13:04 PM
  To change this template use File | Settings | File Templates.
--%>
<s:url value="/static/j_spring_security_check?targetUrl=${targetUrl}" var="login_url"/>
<div class="container-fluid col-sm-8">
    <div class="panel panel-default">
        <div class="panel-heading"><b>Please enter your username and password</b></div>
        <div class="panel-body">
            <form action="${login_url}" method="post">
                <div class="form-group">
                    <label for="username" class="control-label"><spring:message code="label.username"/></label>
                    <input type="text" class="form-control" id="username" name="j_username"/>
                </div>
                <div class="form-group">
                    <label for="password" class="control-label"><spring:message code="label.password"/></label>
                    <input type="password" class="form-control" id="password" name="j_password"/>
                </div>
                <input type="hidden"
                       name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button class="btn btn-success"><spring:message
                        code="button.login"/></button>
            </form>
        </div>
    </div>
</div>
