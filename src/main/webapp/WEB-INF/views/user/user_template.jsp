<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%--
  Created by IntelliJ IDEA.
  User: pepsik
  Date: 6/5/15
  Time: 12:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <div id="menu">
        <t:insertAttribute name="menu"/>
    </div>

    <div id="menu_content">
        <t:insertAttribute name="menu_content"/>
    </div>
</div>