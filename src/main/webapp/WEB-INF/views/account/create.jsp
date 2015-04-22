<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h3><spring:message code="label.account.create.title"/></h3>

<div>
    <form:form modelAttribute="account" class="form-horizontal col-sm-5" method="post" action="/account">
        <div class="form-group">
            <label for="email" class="col-xs-6"><spring:message code="label.email"/></label>
            <spring:bind path="account.email">
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
        <div class="form-group">
            <label for="username" class="col-xs-6"><spring:message code="label.username"/></label>
            <spring:bind path="account.username">
                <input type="text" name="${status.expression}" id="username"
                       class="form-control username required"
                       data-placement="top"
                       value="${status.value}"
                       data-trigger="manual"
                       data-content="Must be at least 3 characters long, and must only contain letters">

                <div class="container">
                    <form:errors path="${status.expression}" cssClass="label label-danger"/>
                </div>
            </spring:bind>
        </div>
        <div class="form-group">
            <label for="password" class="col-xs-6"><spring:message code="label.password"/></label>
            <spring:bind path="account.password">
                <input type="password" name="${status.expression}" id="password"
                       class="form-control password required"
                       data-placement="top"
                       value="${status.value}"
                       data-trigger="manual"
                       data-content="Must be at least 3 characters long, must contain 1 number, 1 lowercase, 1 uppercase letters">

                <div class="container">
                    <form:errors path="${status.expression}" cssClass="label label-danger"/>
                </div>
            </spring:bind>
        </div>
        <div class="form-group">
            <label for="fullname" class="col-xs-6"><spring:message code="label.fullname"/></label>
            <spring:bind path="account.fullname">
                <input type="text" name="${status.expression}" id="fullname"
                       class="form-control name required"
                       data-placement="top"
                       value="${status.value}"
                       data-trigger="manual"
                       data-content="Must be at least 3 characters long, and must only contain letters">

                <div class="container">
                    <form:errors path="${status.expression}" cssClass="label label-danger"/>
                </div>
            </spring:bind>
        </div>
        <div class="form-group">
            <label for="birthdate" class="col-xs-6"><spring:message code="label.birthdate"/></label>
            <spring:bind path="account.birthdate">
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

        <div class="form-group">
            <button type="submit" class="btn btn-success"><spring:message code="button.submit"/></button>
            <button type="reset" class="btn"><spring:message code="button.clear"/></button>
        </div>

        <p class="help-block pull-left text-danger hide" id="form-error">&nbsp; The form is not
            valid.</p>
    </form:form>
</div>

<%--<script type="text/javascript">--%>
<%--//    $('[data-toggle=tooltip]').tooltip();--%>

    <%--$.fn.goValidate = function () {--%>
        <%--var $form = this,--%>
                <%--$inputs = $form.find('input:text, input:password'),--%>
                <%--$selects = $form.find('select'),--%>
                <%--$textAreas = $form.find('textarea');--%>

        <%--var validators = {--%>
            <%--name: {--%>
                <%--regex: /^[A-Za-z]{3,40}$/--%>
            <%--},--%>
            <%--username: {--%>
                <%--regex: /^[A-Za-z0-9]{3,20}$/--%>
            <%--},--%>
            <%--firstName: {--%>
                <%--regex: /^[A-Za-z]{3,}$/--%>
            <%--},--%>
            <%--lastName: {--%>
                <%--regex: /^[A-Za-z]{3,}$/--%>
            <%--},--%>
            <%--town: {--%>
                <%--regex: /^[A-Za-z]{3,}$/--%>
            <%--},--%>
            <%--postcode: {--%>
                <%--regex: /^.{3,}$/--%>
            <%--},--%>
            <%--password: {--%>
                <%--regex: /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{3,}/--%>
            <%--},--%>
            <%--password_repeat: {--%>
                <%--regex: /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,}/--%>
            <%--},--%>
            <%--email: {--%>
                <%--regex: /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/--%>
            <%--},--%>
            <%--phone: {--%>
                <%--regex: /^[2-9]\d{2}-\d{3}-\d{4}$/--%>
            <%--},--%>
            <%--date: {--%>
                <%--regex: /^[0-9]\d{2}-\d{2}-\d{4}$/--%>
            <%--},--%>
            <%--body: {--%>
                <%--regex: /^.{3,}$/--%>
            <%--},--%>
            <%--country: {--%>
                <%--regex: /^(?=\s*\S).*$/--%>
            <%--}--%>
        <%--};--%>
        <%--var validate = function (klass, value) {--%>
            <%--var isValid = true,--%>
                    <%--error = '';--%>

            <%--if (!value && /required/.test(klass)) {--%>
                <%--error = 'This field is required';--%>
                <%--isValid = false;--%>
            <%--} else {--%>
                <%--klass = klass.split(/\s/);--%>
                <%--$.each(klass, function (i, k) {--%>
                    <%--if (validators[k]) {--%>
                        <%--if (value && !validators[k].regex.test(value)) {--%>
                            <%--isValid = false;--%>
                            <%--error = validators[k].error;--%>
                        <%--}--%>
                    <%--}--%>
                <%--});--%>
            <%--}--%>
            <%--return {--%>
                <%--isValid: isValid,--%>
                <%--error: error--%>
            <%--}--%>
        <%--};--%>
        <%--var showError = function ($e) {--%>
            <%--var klass = $e.attr('class'),--%>
                    <%--value = $e.val(),--%>
                    <%--test = validate(klass, value);--%>

            <%--$e.removeClass('invalid');--%>
            <%--$('#form-error').addClass('hide');--%>

            <%--if (!test.isValid) {--%>
                <%--$e.addClass('invalid');--%>

                <%--if (typeof $e.data("shown") == "undefined" || $e.data("shown") == false) {--%>
                    <%--$e.popover('show');--%>
                <%--}--%>

            <%--}--%>
            <%--else {--%>
                <%--$e.popover('hide');--%>
            <%--}--%>
        <%--};--%>

        <%--$inputs.keyup(function () {--%>
            <%--showError($(this));--%>
        <%--});--%>
        <%--$selects.change(function () {--%>
            <%--showError($(this));--%>
        <%--});--%>
        <%--$textAreas.keyup(function () {--%>
            <%--showError($(this));--%>
        <%--});--%>

        <%--$inputs.on('shown.bs.popover', function () {--%>
            <%--$(this).data("shown", true);--%>
        <%--});--%>

        <%--$inputs.on('hidden.bs.popover', function () {--%>
            <%--$(this).data("shown", false);--%>
        <%--});--%>

        <%--$form.submit(function (e) {--%>

            <%--$inputs.each(function () { /* test each input */--%>
                <%--if ($(this).is('.required') || $(this).hasClass('invalid')) {--%>
                    <%--showError($(this));--%>
                <%--}--%>
            <%--});--%>
            <%--$selects.each(function () { /* test each input */--%>
                <%--if ($(this).is('.required') || $(this).hasClass('invalid')) {--%>
                    <%--showError($(this));--%>
                <%--}--%>
            <%--});--%>
            <%--$textAreas.each(function () { /* test each input */--%>
                <%--if ($(this).is('.required') || $(this).hasClass('invalid')) {--%>
                    <%--showError($(this));--%>
                <%--}--%>
            <%--});--%>
            <%--if ($form.find('input.invalid').length) { /* form is not valid */--%>
                <%--e.preventDefault();--%>
                <%--$('#form-error').toggleClass('hide');--%>
            <%--}--%>
        <%--});--%>
        <%--return this;--%>
    <%--};--%>

    <%--$('form').goValidate();--%>
<%--</script>--%>