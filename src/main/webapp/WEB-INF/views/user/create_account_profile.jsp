<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container col-md-8">
    <div class="panel panel-default">
        <div class="panel-heading"><h4><b><spring:message code="label.user.create.title"/></b></h4></div>
        <div class="panel-body">
            <form:form id="new_user" modelAttribute="profile" class="form-horizontal" method="post" action="/user">
                <div class="form-group col-xs-10">
                    <label for="email" class="col-xs-6"><spring:message code="label.email"/></label>
                    <spring:bind path="profile.email">
                        <input type="text" name="${status.expression}" id="email"
                               class="form-control email required"
                               data-placement="top"
                               value="${status.value}"
                               data-trigger="manual"
                               data-content="Must be a valid e-mail address (user@gmail.com)">

                        <div class="container">
                            <form:errors path="${status.expression}" cssClass="label label-danger"/>
                        </div>
                    </spring:bind>
                </div>
                <div class="form-group col-xs-10">
                    <label for="username" class="col-xs-6"><spring:message code="label.username"/></label>
                    <spring:bind path="profile.user.username">
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
                <div class="form-group col-xs-10">
                    <label for="password" class="col-xs-6"><spring:message code="label.password"/></label>
                    <spring:bind path="profile.user.userPassword.password">
                        <input type="password" name="${status.expression}" id="password"
                               class="form-control password required"
                               data-placement="bottom"
                               value="${status.value}"
                               data-trigger="manual"
                               data-content="Must be at least 3 characters long, must contain 1 number, 1 lowercase, 1 uppercase letters">

                        <div class="container">
                            <form:errors path="${status.expression}" cssClass="label label-danger"/>
                        </div>
                    </spring:bind>
                </div>
                <div class="form-group col-xs-10">
                    <label for="fullname" class="col-xs-6"><spring:message code="label.fullname"/></label>
                    <spring:bind path="profile.fullname">
                        <input type="text" name="${status.expression}" id="fullname"
                               class="form-control name required"
                               data-placement="bottom"
                               value="${status.value}"
                               data-trigger="manual"
                               data-content="Must be at least 3 characters long, and must only contain letters">

                        <div class="container">
                            <form:errors path="${status.expression}" cssClass="label label-danger"/>
                        </div>
                    </spring:bind>
                </div>
                <div class="form-group col-xs-10">
                    <label for="birthdate" class="col-xs-6"><spring:message code="label.birthdate"/></label>
                    <spring:bind path="profile.birthdate">
                        <input type="date" name="${status.expression}" id="birthdate"
                               class="form-control date required"
                               value="${status.value}"
                               data-toggle="tooltip"
                               data-content="Invalid Date">

                        <div class="container">
                            <form:errors path="${status.expression}" cssClass="label label-danger"/>
                        </div>
                    </spring:bind>
                </div>
            </form:form>
        </div>
        <div class="panel-footer">
            <div class="form-group">
                <button form="new_user" type="submit" class="btn btn-success"><spring:message
                        code="button.submit"/></button>
                <button form="new_user" type="reset" class="btn"><spring:message code="button.clear"/></button>
            </div>
            <div id="form-error" class="alert alert-danger fade in hide">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <strong>Warning!</strong> The form is not valid.
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/resources/js/accountValidation.js"></script>
