<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div>
    <c:forEach var="post" items="${postList}">
        <%@ include file="comment.jspf" %>
    </c:forEach>
    <div id="response${thread_id}"></div>
    <br>
    <form:form modelAttribute="post" method="post" id="postForm${thread_id}">
        <form:textarea path="text" id="summernoteEditor${thread_id}"/>
        <div class="submit"><br>
            <button type="submit" class="btn btn-success">
                <spring:message code="button.comment.post"/>
            </button>
        </div>
    </form:form>

</div>

<%@ include file="../../../../resources/js/commentsJS.jspf" %>