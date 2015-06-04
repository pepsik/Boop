<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 6/3/15
  Time: 16:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="margin-top: 10px">
    <div class="col-sm-3">
        <ul class="nav nav-pills nav-stacked">
            <li role="presentation">
                <a href="/messages">New private message</a></li>
            <li role="presentation">
                <a href="/messages/input">Inbox (${inputPMCount})
                    <span class="badge" style="float: right">1</span>
                </a></li>
            <li role="presentation">
                <a href="/messages/output">Sent messages (${outputPMCount})
                </a></li>
        </ul>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('a[href="' + this.location.pathname + '"]').parent().addClass('active');
    });
</script>