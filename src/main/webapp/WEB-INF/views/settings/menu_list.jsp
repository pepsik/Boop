<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 5/17/15
  Time: 20:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="container-fluid col-sm-4">
    <div class="list-group">
            <span class="list-group-item list-group-item-info">
                <b>Personal Settings</b>
            </span>
        <a href="${relativePath}/settings/profile" class="list-group-item">Profile</a>
        <a href="${relativePath}/settings/account" class="list-group-item">Account</a>
        <a href="${relativePath}/settings/emails" class="list-group-item">Emails</a>
        <a href="${relativePath}/settings/security" class="list-group-item">Security</a>
        <a href="${relativePath}/settings/view" class="list-group-item disabled">Accessibility</a>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('a[href="' + this.location.pathname + '"]').addClass('active');
    });
</script>
