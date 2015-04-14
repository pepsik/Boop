<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div>

    <s:url value="/thread/${thread_id}/post/${post.id}" var="post_url"/>

    <sf:form modelAttribute="post" method="PUT" action="${post_url}">

        <sf:label path="text"/>
        <sf:textarea cssClass="text-width" path="text"/>
        <sf:errors path="text"/>

        <div class="spitItSubmitIt">
            <input type="submit" value="Edit" class="btn btn-success"/>
        </div>
    </sf:form>

</div>