<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="shortcut icon"
          href="http://emojipedia.org/wp-content/uploads/2014/04/128x128x1f60e-google-android.png.pagespeed.ic.f3O6TlEr2A.png"/>
    <!-- Main css =(-->
    <link href="${pageContext.request.contextPath}/resources/css/smart.css" rel="stylesheet" type="text/css"/>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet"
          type="text/css"/>
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>--%>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.4/js/bootstrap.min.js"></script>

    <!-- Summernote -->
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.request.contextPath}/resources/summernote/summernote.css" rel="stylesheet"
          type="text/css"/>
    <script src="${pageContext.request.contextPath}/resources/summernote/summernote.js"></script>
    <script src="${pageContext.request.contextPath}/resources/summernote/lang/summernote_uk_UA.js"></script>
    <script src="${pageContext.request.contextPath}/resources/summernote/plugin/summernote-ext-video.js"></script>

    <!-- Bootstrap Tags input-->
    <script src="${pageContext.request.contextPath}/resources/tags/bootstrap-tagsinput.js"></script>
    <link rel="stylesheet" href="http://timschlechter.github.io/bootstrap-tagsinput/dist/bootstrap-tagsinput.css">
    <link rel="stylesheet" href="http://timschlechter.github.io/bootstrap-tagsinput/examples/assets/app.css">
    <%--<script src="http://timschlechter.github.io/bootstrap-tagsinput/dist/bootstrap-tagsinput.js"></script>--%>
    <%--<script src="${pageContext.request.contextPath}/bower_components/readmore/jquery.mockjax.js"></script>--%>

    <!-- SmartSite js,css-->
    <script src="${pageContext.request.contextPath}/resources/js/comments.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/favorites.js"></script>
    <script src="${pageContext.request.contextPath}/bower/Autolinker.js/dist/Autolinker.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/autolinked_conf.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/smooth_scroll.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/after.css" rel="stylesheet" type="text/css"/>

    <%--<script src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.min.js"></script>--%>
    <%--<script src="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.min.css"></script>--%>
</head>

<body>
<div id="container" class="container-fluid">

    <div class="content-holder">
        <div id="top">
            <t:insertAttribute name="top"/>
        </div>

        <div id="carousel-generic" class="carousel slide" data-ride="carousel" data-interval="15000">
            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <img src="/resources/images/logo2.png">
                </div>
                <div class="item">
                    <img src="/resources/images/logo1.png">
                </div>
            </div>

            <a class="left carousel-control" href="#carousel-generic" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#carousel-generic" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>

        <div id="content">
            <t:insertAttribute name="content"/>
        </div>
        <div id="footer">
            <t:insertAttribute name="footer"/>
        </div>
    </div>
</div>
</body>

</html>