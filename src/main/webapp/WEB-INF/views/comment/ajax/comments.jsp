<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div>
    <c:forEach var="commentIndex" items="${commentList}">
        <c:set var="comment" value="${commentIndex}" scope="request"/>
        <jsp:include page="comment.jsp"/>
    </c:forEach>
    <sec:authorize access="isAuthenticated()">
        <div id="response${post_id}"></div>
        <br>

        <div id="errors${post_id}"></div>
        <div id="summernoteEditor${post_id}"></div>
        <div class="submit"><br>
            <button type="submit" onclick="postComment(${post_id})" class="btn btn-success">
                <spring:message code="button.comment.post"/>
            </button>
            <span id="postCommentError"></span>
        </div>
    </sec:authorize>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        var post_id = '${post_id}';
        var editor = $('#summernoteEditor' + post_id);      //exclude to js file?
        editor.summernote({
            lang: '<spring:message code="summernote.lang"/>',  //wtf
//            toolbar: [['group', ['video']]],
            height: 150,
            minHeight: 100,
            maxHeight: null
        });
    });
</script>