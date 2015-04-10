<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<html>
<head>
    <title>DjoyReaxtor</title>
    <link href="<s:url value="/resources" />/css/smart.css"
          rel="stylesheet"
          type="text/css"/>
</head>

<body>
<div id="container">
    <a class="logo" href="<s:url value="/home" />">
        <img src="<s:url value="/resources" />/images/logo2.png" border="0"/></a>

    <%--<div class="content-holder">--%>
    <%--<div id="top">--%>
    <%--<t:insertAttribute name="top"/>--%>
    <%--</div>--%>
    <%--<div id="side">--%>
    <%--<t:insertAttribute name="side"/>--%>
    <%--</div>--%>
    <div id="content">
        <t:insertAttribute name="content"/>
    </div>
</div>

</div>
</body>
</html>
