<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 5/26/15
  Time: 16:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<title><spring:message code="button.private_message.outbox"/></title>

<div style="margin-top: 10px">
    <div class="col-md-8">
        <c:forEach var="privateMessage" items="${outputPMList}">
            <div class="well">
                <table class="table">
                    <tbody>
                    <tr>
                        <td width="150px"><spring:message code="button.private_message.recipient"/></td>
                        <td>${privateMessage.recipient.username}</td>
                    </tr>
                    <tr>
                        <td><spring:message code="button.private_message.send_date"/></td>
                        <td><joda:format value="${privateMessage.dispatchDate}" pattern="HH:mm MMM d, yyyy"/></td>
                    </tr>
                    <tr>
                        <td><spring:message code="button.private_message.message"/></td>
                        <td>${privateMessage.text}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </c:forEach>
    </div>
</div>