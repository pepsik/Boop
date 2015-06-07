<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 6/3/15
  Time: 16:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-fluid">
  <div id="menu">
    <t:insertAttribute name="menu"/>
  </div>

  <div id="menu_content">
    <t:insertAttribute name="menu_content"/>
  </div>
</div>

<script type="text/javascript">
  $(document).ready(function () {
    $('#messages_nav').addClass('active');
  });
</script>