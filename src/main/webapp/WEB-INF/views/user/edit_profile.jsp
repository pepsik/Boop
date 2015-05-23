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

<div>
    <s:url var="user_url" value="/user/${profile.user.username}"/>

    <div class="col-sm-4">
        <div class="list-group">
            <span class="list-group-item list-group-item-info">
                <b>Personal Settings</b>
            </span>
            <a href="#" class="list-group-item active">Profile</a>
            <a href="#" class="list-group-item">Account</a>
            <a href="#" class="list-group-item">Emails</a>
            <a href="#" class="list-group-item">Security</a>
        </div>
    </div>
    <div class="container col-md-8">
        <div class="panel panel-default">
            <div class="panel-heading"><b>Public Profile</b></div>
            <div class="panel-body">
                <form:form modelAttribute="profile" class="form-horizontal" method="put" action="${user_url}">
                    <div class="form-group col-md-10">
                        <label for="fullname"><spring:message code="label.fullname"/></label>
                        <spring:bind path="profile.fullname">
                            <input type="text" name="${status.expression}" id="fullname"
                                   class="form-control name required"
                                   data-placement="bottom"
                                   value="${status.value}"
                                   data-trigger="manual"
                                   data-content="Must be at least 3 characters long, and must only contain letters"/>
                        </spring:bind>
                    </div>
                    <div class="form-group  col-md-10">
                        <label for="email"><spring:message code="label.email"/></label>
                        <spring:bind path="profile.email">
                            <input type="email" name="${status.expression}" id="email"
                                   class="form-control email required"
                                   data-placement="bottom"
                                   value="${status.value}"
                                   data-trigger="manual"
                                   data-content="Must be a valid e-mail address (user@gmail.com)"/>
                        </spring:bind>
                    </div>
                    <div class="form-group col-md-10">
                        <label for="birthdate"><spring:message code="label.birthdate"/></label>
                        <spring:bind path="profile.birthdate">
                            <input type="date" name="${status.expression}" id="birthdate"
                                   class="form-control date required"
                                   value="${status.value}"/>
                        </spring:bind>
                    </div>
                    <div class="form-group col-md-10">
                        <label for="gender"><spring:message code="label.gender"/></label>
                        <spring:bind path="profile.gender">
                            <select name="${status.expression}" id="gender" class="form-control">
                                <option value="male"><spring:message code="label.gender.male"/></option>
                                <option value="female"><spring:message code="label.gender.female"/></option>
                            </select>
                        </spring:bind>
                    </div>
                    <div class="form-group col-md-10">
                        <label for="country"><spring:message code="label.country"/></label>
                        <spring:bind path="profile.country">
                            <input type="text" name="${status.expression}" id="country"
                                   class="form-control" value="${status.value}"/>
                        </spring:bind>
                    </div>
                    <div class="form-group col-md-10">
                        <label for="city"><spring:message code="label.city"/></label>
                        <spring:bind path="profile.city">
                            <input type="text" name="${status.expression}" id="city"
                                   class="form-control" value="${status.value}"/>
                        </spring:bind>
                    </div>
                    <div class="form-group col-md-10">
                        <label for="job"><spring:message code="label.job"/></label>
                        <spring:bind path="profile.job">
                            <input type="text" name="${status.expression}" id="job"
                                   class="form-control" value="${status.value}"/>
                        </spring:bind>
                    </div>
                    <div class="form-group col-md-10">
                        <label for="about"><spring:message code="label.about"/></label>
                        <spring:bind path="profile.about">
                <textarea name="${status.expression}" id="about" rows="5" maxlength="500"
                          class="form-control">${status.value}</textarea>
                        </spring:bind>
                    </div>

                    <div class="form-group col-md-10">
                        <button type="submit" class="btn btn-success"><spring:message code="button.finish"/></button>
                        <button type="reset" class="btn"><spring:message code="button.clear"/></button>
                    </div>

                    <p class="help-block pull-left text-danger hide" id="form-error">&nbsp; The form is not
                        valid.</p>
                </form:form>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/resources/js/accountValidation.js"></script>