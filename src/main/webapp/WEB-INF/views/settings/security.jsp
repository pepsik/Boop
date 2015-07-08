<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 5/17/15
  Time: 20:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title><spring:message code="label.security"/></title>
</head>

<s:url var="session_url" value="/settings/security/session"/>

<div class="container col-md-8">
    <div class="panel panel-default">
        <div class="panel-heading"><b>Sessions</b></div>
        <div class="panel-body">
            This is a list of devices that have logged into your user. Revoke any sessions that you do not recognize.
            <ul type="none">
                <li class="spittle-list"></li>
                <c:forEach items="${sessionsInfo}" var="sessionInfo" varStatus="loop">
                    <li class="spittle-list postListText">
                        Session Id - ${sessionInfo.sessionId} <br>
                        <span>${sessionInfo.userAgent}</span><br>
                        CreationTime - <joda:format value="${sessionInfo.creationDate}" pattern="HH:mm:ss MMM d, yyyy"/><br>
                        LastAccessedTime - <joda:format value="${sessionInfo.lastAccessedTime}" pattern="HH:mm:ss MMM d, yyyy"/><br>
                        Remote Ip - ${sessionInfo.userRemoteIp}  <br>
                        <form:form method="post" action="${session_url}/${loop.count}/revoke">
                            <button class="btn btn-sm btn-danger">Revoke</button>
                        </form:form>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading"><b>Security history</b></div>
        <div class="panel-body">
            This is a security log of important events involving your user.
            <ul type="none">
                <li>
                    history here
                </li>
                <li>
                    history here
                </li>
                <li>
                    history here
                </li>
            </ul>
        </div>
    </div>
</div>

<%--<script src="${pageContext.request.contextPath}/bower/ua-parser-js/dist/ua-parser.min.js"></script>--%>
<%--<script type="text/javascript">--%>
<%--var parser = new UAParser();--%>
<%--var uastring = $("#ua").text();--%>
<%--parser.setUA(uastring);--%>
<%--var result = parser.getResult();--%>


<%--//    $("#session1").html(--%>
<%--//            "<strong>Session Title</strong>"--%>
<%--//            + "<br>"--%>
<%--//            + "<small>Last Accessed</small>"--%>
<%--//            + "<div>"--%>
<%--//            + "Browser Version"--%>
<%--//            + "OS Version"--%>
<%--//            + "</div>"--%>
<%--//            + "<p>"--%>
<%--//            + "<strong>Signed in:</strong>"--%>
<%--//            + " <br>"--%>
<%--//            + "<small>Time</small>"--%>
<%--//            + "</p>"--%>
<%--//    );--%>
<%--</script>--%>