<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 6/5/15
  Time: 12:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<s:url value="/user/${username}/profile" var="public_profile_url"/>
<s:url value="/user/${username}/favorites/1" var="user_favorites_url"/>
<s:url value="/user/${username}/posts/1" var="user_posts_url"/>
<s:url value="/user/${username}/comments/1" var="user_comments_url"/>

<div class="container-fluid">
    <br>
    <ul class="nav nav-tabs">
        <li id="public_profile"><a href="${public_profile_url}">Public Profile</a></li>
        <li id="posts"><a href="${user_posts_url}">Posts&nbsp;&nbsp;<span class="badge">${postsCount}</span></a></li>
        <li id="comments"><a href="${user_comments_url}">Comments&nbsp;&nbsp;<span class="badge">${commentsCount}</span></a>
        </li>
        <li id="favorites"><a href="${user_favorites_url}">Favorites&nbsp;&nbsp;<span
                class="badge">${favoritesCount}</span></a></li>
        <li id="friends"><a href="#">Friends</a></li>
    </ul>
    <br>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        if (window.location.href.indexOf("profile") > -1) {
            $('#public_profile').addClass('active');
        }
        if (window.location.href.indexOf("favorites") > -1) {
            $('#favorites').addClass('active');
        }
        if (window.location.href.indexOf("posts") > -1) {
            $('#posts').addClass('active');
        }
        if (window.location.href.indexOf("comments") > -1) {
            $('#comments').addClass('active');
        }
        if (window.location.href.indexOf("friends") > -1) {
            $('#friends').addClass('active');
        }
    });
</script>
