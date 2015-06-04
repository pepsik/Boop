<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 5/26/15
  Time: 15:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<title>Inbox</title>

<div style="margin-top: 10px">
    <div class="col-md-8">
        <c:forEach var="privateMessage" items="${inputPMList}">
            <div class="well">
                <table class="table">
                    <tbody>
                    <tr>
                        <td width="150px">Sender</td>
                        <td>${privateMessage.sender.username}</td>
                    </tr>
                    <tr>
                        <td>Send date</td>
                        <td><joda:format value="${privateMessage.dispatchDate}" pattern="HH:mm MMM d, yyyy"/></td>
                    </tr>
                    <tr>
                        <td>Message</td>
                        <td>${privateMessage.text}</td>
                    </tr>
                    <tr>
                        <td>Viewed(temp)</td>
                        <td>${privateMessage.read}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </c:forEach>
    </div>
</div>