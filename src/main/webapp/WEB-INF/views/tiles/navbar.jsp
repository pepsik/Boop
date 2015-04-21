<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/home">SmartSite</a>
        </div>

        <form class="navbar-form navbar-left" role="search">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Search">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>

        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">

                <sec:authorize access="!isAuthenticated()">
                    <li class="active"><a class="btn" href="/account">Sign Up</a></li>
                    <li><a href="#" class="btn" data-toggle="modal" data-target="#loginModal">Login</a></li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <li class="padding-top">
                        <img src="http://mmsmake.com/wp-content/themes/jupiter/images/cloud/default-avatar_ie.png"
                             alt=""/>
                    </li>
                    <li class="dropdown">
                        <sec:authentication var="username" property="principal.username"/>
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                            <c:out value="${username}"/>
                            <span class="caret"></span></a>

                        <ul class="dropdown-menu" role="menu">
                            <s:url value="/account/${username}" var="user_profile_url"/>
                            <li><a href="${user_profile_url}">Profile</a></li>
                            <li><a href="#">Messages</a></li>
                            <li><a href="#">Settings</a></li>
                            <li class="divider"></li>
                            <s:url value="/static/j_spring_security_logout" var="logout_url"/>
                            <li><a href="${logout_url}">Log Out</a></li>
                        </ul>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>

<!-- Modal -->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3>SmartSite Login</h3>
            </div>
            <div class="modal-body">
                <form action="<s:url value="/static/j_spring_security_check"/>"
                      method="post">
                    <div class="form-group">
                        <label for="username" class="control-label">Username</label>
                        <input type="text" class="form-control" id="username" name="j_username"/>
                    </div>
                    <div class="form-group">
                        <label for="password" class="control-label">Password</label>
                        <input type="password" class="form-control" id="password" name="j_password"/>
                    </div>
                    <div class="checkbox">
                        <label><input type="checkbox" name="_spring_security_remember_me"> Remember me</label>
                    </div>
                    <button type="submit" class="btn btn-success">Login</button>
                    <button type="reset" class="btn">Clear</button>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
            </div>
        </div>
    </div>
</div>

<%--<div class="modal fade" id="signUpModal" tabindex="-1" role="dialog" aria-hidden="true">--%>
    <%--<div class="modal-dialog">--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<h3>Create a New Account</h3>--%>
            <%--</div>--%>
            <%--<div class="modal-body">--%>
                <%--<form:form modelAttribute="account" method="post" action="/account">--%>
                    <%--<div class="form-group">--%>
                        <%--<label for="email" class="col-xs-3">Email</label>--%>
                        <%--<spring:bind path="account.email">--%>
                            <%--<input type="text" name="${status.expression}" id="email"--%>
                                   <%--class="form-control email required"--%>
                                   <%--data-placement="bottom"--%>
                                   <%--value="${status.value}"--%>
                                   <%--data-trigger="manual"--%>
                                   <%--data-content="Must be a valid e-mail address (user@gmail.com)">--%>
                        <%--</spring:bind>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label for="username" class="col-xs-3">Username</label>--%>
                        <%--<spring:bind path="account.username">--%>
                            <%--<input type="text" name="${status.expression}" id="username"--%>
                                   <%--class="form-control username required"--%>
                                   <%--data-placement="bottom"--%>
                                   <%--value="${status.value}"--%>
                                   <%--data-trigger="manual"--%>
                                   <%--data-content="Must be at least 3 characters long, and must only contain letters">--%>
                        <%--</spring:bind>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label for="password" class="col-xs-3">Password</label>--%>
                        <%--<spring:bind path="account.password">--%>
                            <%--<input type="password" name="${status.expression}" id="password"--%>
                                   <%--class="form-control password required"--%>
                                   <%--data-placement="bottom"--%>
                                   <%--value="${status.value}"--%>
                                   <%--data-trigger="manual"--%>
                                   <%--data-content="Must be at least 3 characters long, and must have 1 upper/lowercase letter and 1 number">--%>
                        <%--</spring:bind>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label for="fullname" class="col-xs-3">Fullname</label>--%>
                        <%--<spring:bind path="account.fullname">--%>
                            <%--<input type="text" name="${status.expression}" id="fullname"--%>
                                   <%--class="form-control name required"--%>
                                   <%--data-placement="bottom"--%>
                                   <%--value="${status.value}"--%>
                                   <%--data-trigger="manual"--%>
                                   <%--data-content="Must be at least 3 characters long, and must only contain letters">--%>
                        <%--</spring:bind>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label for="birthdate" class="col-xs-3">Birthdate</label>--%>
                        <%--<spring:bind path="account.birthdate">--%>
                            <%--<input type="date" name="${status.expression}" id="birthdate"--%>
                                   <%--class="form-control date required"--%>
                                   <%--value="${status.value}"/>--%>
                        <%--</spring:bind>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<button type="submit" class="btn btn-success">Sign Up</button>--%>
                        <%--<button type="reset" class="btn">Clear</button>--%>
                    <%--</div>--%>

                    <%--<p class="help-block pull-left text-danger hide" id="form-error">&nbsp; The form is not--%>
                        <%--valid.</p>--%>
                <%--</form:form>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>