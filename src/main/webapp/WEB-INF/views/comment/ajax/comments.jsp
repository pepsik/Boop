<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div>
    <c:forEach var="comment" items="${commentList}">
        <%@ include file="comment.jspf" %>
    </c:forEach>
    <div id="response${post_id}"></div>
    <br>
    <form:form modelAttribute="comment" method="post" id="commentForm${post_id}">
        <form:textarea path="text" id="summernoteEditor${post_id}"/>
        <div class="submit"><br>
            <button type="submit" class="btn btn-success">
                <spring:message code="button.comment.post"/>
            </button>
        </div>
    </form:form>

</div>

<%@ include file="../../../../resources/js/commentsJS.jspf" %>