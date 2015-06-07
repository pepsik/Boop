<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 6/3/15
  Time: 16:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<s:url value="/messages" var="messages_url"/>
<s:url value="/messages/input" var="input_messages_url"/>
<s:url value="/messages/output" var="output_messages_url"/>

<div style="margin-top: 10px">
    <div class="col-sm-3">
        <ul class="nav nav-pills nav-stacked">
            <li role="presentation">
                <a href="${messages_url}"><spring:message code="button.private_message.new"/></a></li>
            <li role="presentation">
                <a href="${input_messages_url}"><spring:message code="button.private_message.inbox"/> (${inputPMCount})
                    <%--<span class="badge" style="float: right">1</span>--%>
                </a></li>
            <li role="presentation">
                <a href="${output_messages_url}"><spring:message code="button.private_message.outbox"/> (${outputPMCount})
                </a></li>
        </ul>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('a[href="' + this.location.pathname + '"]').parent().addClass('active');
    });
</script>