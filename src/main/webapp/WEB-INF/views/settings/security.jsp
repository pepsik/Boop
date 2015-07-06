<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<div class="container col-md-8">
    <div class="panel panel-default">
        <div class="panel-heading"><b>Sessions</b></div>
        <div class="panel-body">
            This is a list of devices that have logged into your user. Revoke any sessions that you do not recognize.
            <ul type="none">
                <li class="spittle-list"></li>
                <c:forEach items="${sessions}" var="sessionInfo" varStatus="loop">
                    <li class="spittle-list postListText">
                        <span>${sessionInfo.getAttribute("User-Agent")}</span><br>
                        CreationTime - ${sessionInfo.creationTime}<br>
                        LastAccessedTime - ${sessionInfo.lastAccessedTime}<br>
                        <button class="btn btn-sm btn-danger" formmethod="post"
                                formaction="/settings/security/session/${loop.count}/revoke">
                            Revoke
                        </button>
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