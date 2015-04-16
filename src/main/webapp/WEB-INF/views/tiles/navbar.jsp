<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
                    <li class="active"><a href="#" class="btn" data-toggle="modal" data-target="#signUpModal">Sign
                        Up</a></li>
                    <li><a href="#" class="btn" data-toggle="modal" data-target="#loginModal">Login</a></li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <li class="padding-top">
                        <img src="http://mmsmake.com/wp-content/themes/jupiter/images/cloud/default-avatar_ie.png" alt=""/>
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
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3>SmartSite Login</h3>
            </div>
            <div class="modal-body">
                <form action="<s:url value="/static/j_spring_security_check"/>" method="post">
                    <div class="form-group">
                        <label for="username" class="control-label">Username</label>
                        <input type="text" class="form-control" id="username" name="j_username"/>
                    </div>
                    <div class="form-group">
                        <label for="password" class="control-label">Password</label>
                        <input class="form-control" id="password" name="j_password"/>
                    </div>
                    <button type="submit" class="btn btn-success">Login</button>
                    <button type="reset" class="btn">Clear</button>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <input id="remember_me" type="checkbox" value="1" name="_spring_security_remember_me"/>
                    <label class="rememberMe" for="remember_me">Remember me</label>
                    <small>
                        <a href="/account/resend_password">Forgot?</a>
                    </small>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="signUpModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3>Create a New Account</h3>
            </div>
            <div class="modal-body">
                <form accept-charset="UTF-8" action="/account" method="post">
                    <div class="form-group">
                        <label for="fullname" class="control-label">Fullname</label>
                        <input type="text" class="form-control" id="fullname" name="fullname"
                               placeholder="Your first and last names..."/>
                    </div>
                    <div class="form-group">
                        <label for="newUsername" class="control-label">Username</label>
                        <input type="text" class="form-control" id="newUsername" name="username"
                               placeholder="Your username..."/>
                    </div>
                    <div class="form-group">
                        <label for="newPassword" class="control-label">Password</label>
                        <input type="password" class="form-control" id="newPassword" name="password"/>
                    </div>
                    <div class="form-group">
                        <label for="birthdate" class="control-label">Birthdate</label>
                        <input type="date" class="form-control" id="birthdate" name="birthdate"/>
                    </div>

                    <button type="submit" class="btn btn-success" formaction="/account">Create</button>
                    <button type="reset" class="btn">Clear</button>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
            </div>
        </div>
    </div>
</div>



