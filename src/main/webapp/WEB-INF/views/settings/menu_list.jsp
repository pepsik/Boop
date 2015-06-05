<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 5/17/15
  Time: 20:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<s:url value="/settings/profile" var="profile_url"/>
<s:url value="/settings/account" var="account_url"/>
<s:url value="/settings/emails" var="email_url"/>
<s:url value="/settings/security" var="security_url"/>
<s:url value="/settings/view" var="acc_url"/>

<div class="container-fluid col-sm-4">
    <div class="list-group">
            <span class="list-group-item list-group-item-info">
                <b>Personal Settings</b>
            </span>
        <a href="${profile_url}" class="list-group-item">Profile</a>
        <a href="${account_url}" class="list-group-item">Account</a>
        <a href="${email_url}" class="list-group-item">Emails</a>
        <a href="${security_url}" class="list-group-item">Security</a>
        <a href="${acc_url}" class="list-group-item disabled">Accessibility</a>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('a[href="' + this.location.pathname + '"]').addClass('active');
    });
</script>
