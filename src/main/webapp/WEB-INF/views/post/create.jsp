<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<s:url value="/thread/{id}" var="thread_url">
    <s:param name="id"  value="${thread_id}"/>
</s:url>

<div>
    <sf:form modelAttribute="post" method="post" action="${thread_url}/post">

        <sf:label path="text"/>
        <sf:textarea cssClass="text-width" path="text"/>
        <sf:errors path="text"/>

        <div class="spitItSubmitIt">
            <input type="submit" value="Post" class="btn btn-success"/>
        </div>

    </sf:form>
</div>