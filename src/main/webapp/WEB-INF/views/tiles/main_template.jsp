<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<html>
<head>
    <title>^_^</title>
    <link href="<s:url value="/resources" />/css/smart.css" rel="stylesheet" type="text/css"/>
    <link href="<s:url value="/resources" />/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="<s:url value="/resources" /> /js/bootstrap.js"></script>

</head>

<body>
<div id="container">

    <div class="content-holder">
        <div id="top">
            <t:insertAttribute name="top"/>
        </div>

        <a class="logo" href="<s:url value="/home" />">
            <img src="<s:url value="/resources" />/images/logo2.png" border="0"/></a>

        <%--<div id="side">--%>
        <%--<t:insertAttribute name="side"/>--%>
        <%--</div>--%>
        <div id="content">
            <t:insertAttribute name="content"/>
        </div>
    </div>
</div>

</div>


</body>
</html>
