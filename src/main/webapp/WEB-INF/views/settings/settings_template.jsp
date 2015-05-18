<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <div id="menu">
        <t:insertAttribute name="menu"/>
    </div>

    <div id="menu_content">
        <t:insertAttribute name="menu_content"/>
    </div>
</div>

<%--<script src="${pageContext.request.contextPath}/resources/js/accountValidation.js"></script>--%>