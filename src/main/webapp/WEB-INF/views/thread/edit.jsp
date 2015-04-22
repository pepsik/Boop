<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div>

    <s:url value="/thread/${thread.id}" var="thread_url"/>

    <div class="well">
    <sf:form modelAttribute="thread" method="PUT" action="${thread_url}">
            <div class="form-group">
                <sf:label path="title"><spring:message code="label.thread.edit.title"/></sf:label>
                <sf:input path="title" cssClass="form-control" maxlength="20"/>
            </div>
            <form:textarea path="text" id="summernote"/>
            <sf:errors path="text"/> <br>
            <div class="spitItSubmitIt">
                <button type="submit" class="btn btn-success"><spring:message code="button.edit"/></button>
            </div>
    </sf:form>
    </div>
</div>