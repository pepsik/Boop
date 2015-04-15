<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div>

    <s:url value="/thread/${thread.id}" var="thread_url"/>
    <sf:form modelAttribute="thread" method="PUT" action="${thread_url}">
        <fieldset>
            <div class="form-group">
                <sf:label path="title">Title</sf:label>
                <sf:input path="title" cssClass="form-control" maxlength="20"/>
            </div>

            <sf:label path="text"/>
            <sf:textarea cssClass="text-width" path="text"/>
            <sf:errors path="text"/>

            <div class="spitItSubmitIt">
                <input type="submit" value="Edit" class="btn btn-success"/>
            </div>
        </fieldset>
    </sf:form>

</div>