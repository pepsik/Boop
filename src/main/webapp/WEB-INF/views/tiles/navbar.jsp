<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container" style="width: 930px">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/home"><span class="glyphicon glyphicon-home"></span> </a>
        </div>

        <form class="navbar-form navbar-left" role="search">
            <button type="button" class="btn btn-info">
                <span class="glyphicon glyphicon-search"></span> <spring:message code="navbar.search.placeholder"/>
            </button>
        </form>

        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">

                <sec:authorize access="!isAuthenticated()">
                    <li class="active"><a class="btn" href="/user"><spring:message code="button.submit"/></a></li>
                    <li><a href="#" class="btn" data-toggle="modal" data-target="#loginModal"><span
                            class="glyphicon glyphicon-log-in"></span>&nbsp;&nbsp;<spring:message
                            code="button.login"/></a></li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <li class="padding-top">
                        <img src="http://mmsmake.com/wp-content/themes/jupiter/images/cloud/default-avatar_ie.png"
                             alt="" width="40px"/>
                    </li>
                    <li>
                        <sec:authentication var="username" property="principal.username"/>
                        <a href="/user/${username}" role="button" aria-expanded="false" data-toggle="tooltip"
                           data-placement="bottom"
                           title="Public profile">
                            <b>${username}</b>
                        </a>

                            <%--<ul class="dropdown-menu" role="menu">--%>
                            <%--<s:url value="/user/${username}" var="user_profile_url"/>--%>
                            <%--<li><a href="${user_profile_url}"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<spring:message--%>
                            <%--code="navbar.dropdown.button.profile"/></a></li>--%>
                            <%--<li><a href="#"><span class="glyphicon glyphicon-envelope"></span>&nbsp;&nbsp<spring:message--%>
                            <%--code="navbar.dropdown.button.messages"/></a></li>--%>
                            <%--<li><a href="/settings/${username}/profile"><span class="glyphicon glyphicon-cog"></span>&nbsp;&nbsp;<spring:message--%>
                            <%--code="navbar.dropdown.button.settings"/></a></li>--%>
                            <%--<li class="divider"></li>--%>
                            <%--<s:url value="/static/j_spring_security_logout" var="logout_url"/>--%>
                            <%--<li><a href="${logout_url}"><span--%>
                            <%--class="glyphicon glyphicon-off"></span>&nbsp;&nbsp;<spring:message--%>
                            <%--code="navbar.dropdown.button.logout"/></a></li>--%>
                            <%--</ul>--%>
                    </li>
                    <li>
                        <a href="/post/new" role="button" aria-expanded="false" data-toggle="tooltip"
                           data-placement="bottom"
                           title="<spring:message code="button.post.new"/>">
                            <span class="glyphicon glyphicon-plus"></span>
                        </a>
                    </li>
                    <%--<li><a href="${user_profile_url}"><span class="glyphicon glyphicon-user"></span></a></li>--%>
                    <li><a href="/user/${username}/favorite" data-toggle="tooltip" data-placement="bottom"
                           title="Favorites"><span class="glyphicon glyphicon-star"></span></a></li>
                    <li><a href="#" data-toggle="tooltip" data-placement="bottom"
                           title="<spring:message code="navbar.dropdown.button.messages"/>"><span
                            class="glyphicon glyphicon-envelope"></span></a></li>
                    <li><a href="/settings/profile" data-toggle="tooltip" data-placement="bottom"
                           title="<spring:message code="navbar.dropdown.button.settings"/>"><span
                            class="glyphicon glyphicon-cog"></span></a></li>
                    <s:url value="/static/j_spring_security_logout" var="logout_url"/>
                    <li><a href="${logout_url}" data-toggle="tooltip" data-placement="bottom"
                           title="<spring:message code="navbar.dropdown.button.logout"/>"><span
                            class="glyphicon glyphicon-log-out"></span></a></li>
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
                <form id="login" action="<s:url value="/static/j_spring_security_check"/>"
                      method="post">
                    <div class="form-group">
                        <label for="username" class="control-label"><spring:message code="label.username"/></label>
                        <input type="text" class="form-control" id="username" name="j_username"/>
                    </div>
                    <div class="form-group">
                        <label for="password" class="control-label"><spring:message code="label.password"/></label>
                        <input type="password" class="form-control" id="password" name="j_password"/>
                    </div>
                    <div class="checkbox">
                        <label><input type="checkbox" name="_spring_security_remember_me"><spring:message
                                code="navbar.modal.label.remember"/></label>
                    </div>
                    <button form="login" type="submit" class="btn btn-success"><spring:message code="button.login"/></button>
                    <button form="login" type="reset" class="btn"><spring:message code="button.clear"/></button>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true"><spring:message
                        code="navbar.modal.button.close"/></button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });
</script>