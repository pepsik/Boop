<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 5/17/15
  Time: 20:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>Public profile</title>
    <script src="${pageContext.request.contextPath}/resources/js/image-upload.js"></script>
    <script src="${pageContext.request.contextPath}/bower/cropit/dist/jquery.cropit.min.js"></script>
</head>

<s:url var="user_url" value="/settings/profile"/>

<div class="container-fluid col-sm-8">
    <div class="panel panel-default">
        <div class="panel-heading"><b>Public Profile</b></div>
        <div class="panel-body">

            <form action="#">
                <div class="image-editor">
                    <div class="btns">
                        <div class="btn select-image-btn"><span class="icon icon-image"></span>Select new image</div>
                        <input name="image" type="file" class="cropit-image-input">
                    </div>
                    <div class="cropit-image-preview"></div>
                    <div class="image-size-label">
                        Resize image
                    </div>
                    <div class="slider-wrapper">
                        <span class="icon icon-image small-image"></span>
                        <input type="range" class="cropit-image-zoom-input custom" min="0" max="1" step="0.01">
                        <span class="icon icon-image large-image"></span>
                    </div>
                    <input type="hidden" name="image-data" class="hidden-image-data"/>
                    <br>
                    <button class="btn" type="submit">Upload Image</button>
                </div>
            </form>
            <br>
            <form:form id="profile" modelAttribute="profile" class="form-horizontal" method="put" action="${user_url}">
                <div class="form-group col-md-10">
                    <label for="fullname" class="col-md-6"><spring:message code="label.fullname"/></label>
                    <spring:bind path="profile.fullname">
                        <input type="text" name="${status.expression}" id="fullname"
                               class="form-control name required"
                               data-placement="bottom"
                               value="${status.value}"
                               data-trigger="manual"
                               data-content="Must be at least 3 characters long, and must only contain letters"/>
                        <form:errors path="${status.expression}" cssClass="label label-danger"/>
                    </spring:bind>
                </div>
                <div class="form-group col-md-10">
                    <label for="birthdate" class="col-md-6"><spring:message code="label.birthdate"/></label>
                    <spring:bind path="profile.birthdate">
                        <input type="date" name="${status.expression}" id="birthdate"
                               class="form-control date required"
                               value="${status.value}"/>
                        <form:errors path="${status.expression}" cssClass="label label-danger"/>
                    </spring:bind>
                </div>

                <div class="form-group col-md-10">
                    <label for="gender" class="col-md-6"><spring:message code="label.gender"/></label>
                    <spring:bind path="profile.gender">
                        <select name="${status.expression}" id="gender" class="form-control">
                            <option id="male" value="male"><spring:message code="label.gender.male"/></option>
                            <option id="female" value="female"><spring:message code="label.gender.female"/></option>
                            <option id="no_gender" value="">Choose your gender</option>
                        </select>
                    </spring:bind>
                </div>
                <div class="form-group col-md-10">
                    <label for="country" class="col-md-6"><spring:message code="label.country"/></label>
                    <spring:bind path="profile.country">
                        <input type="text" name="${status.expression}" id="country"
                               class="form-control" value="${status.value}"/>
                        <form:errors path="${status.expression}" cssClass="label label-danger"/>
                    </spring:bind>
                </div>
                <div class="form-group col-md-10">
                    <label for="city" class="col-md-6"><spring:message code="label.city"/></label>
                    <spring:bind path="profile.city">
                        <input type="text" name="${status.expression}" id="city"
                               class="form-control" value="${status.value}"/>
                        <form:errors path="${status.expression}" cssClass="label label-danger"/>
                    </spring:bind>
                </div>
                <div class="form-group col-md-10">
                    <label for="job" class="col-md-6"><spring:message code="label.job"/></label>
                    <spring:bind path="profile.job">
                        <input type="text" name="${status.expression}" id="job"
                               class="form-control" value="${status.value}"/>
                        <form:errors path="${status.expression}" cssClass="label label-danger"/>
                    </spring:bind>
                </div>
                <div class="form-group col-md-10">
                    <label for="about" class="col-md-6"><spring:message code="label.about"/></label>
                    <spring:bind path="profile.about">
                <textarea name="${status.expression}" id="about" rows="5" maxlength="500"
                          class="form-control">${status.value}</textarea>
                        <form:errors path="${status.expression}" cssClass="label label-danger"/>
                    </spring:bind>
                </div>
            </form:form>
        </div>
        <div class="panel-footer">
            <button form="profile" type="submit" class="btn btn-success">Update profile</button>
            <button form="profile" type="reset" class="btn"><spring:message code="button.clear"/></button>
        </div>
        <p class="help-block pull-left text-danger hide" id="form-error">&nbsp; The form is not
            valid.</p>
    </div>
</div>


<script>
    $(document).ready(function myFunction() {
        var active_gender = "${profile.gender}";

        if (active_gender != "male" && active_gender != "female") {
            active_gender = "no_gender";
        }
        document.getElementById(active_gender).selected = "true";
    });

    $('.image-editor').cropit({
        imageState: {
            src: '${pageContext.request.contextPath}/resources/images/avatars/${profile.user.username}.jpeg'
        }
    });
</script>

