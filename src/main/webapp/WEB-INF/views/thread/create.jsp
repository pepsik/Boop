<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div>

    <c:out value="New Thread"/>


    <sf:form modelAttribute="newThread" method="post" action="/thread">
        <sf:label path="title">Title:</sf:label>
        <sf:input path="title" size="15" maxlength="15"/>

        <sf:label path="text"/>
        <sf:textarea path="text" rows="15" cols="125"/>
        <sf:errors path="text"/>

        <div class="spitItSubmitIt">
            <input type="submit" value="Create" class="status-btn round-btn disabled "/>
        </div>
    </sf:form>

</div>