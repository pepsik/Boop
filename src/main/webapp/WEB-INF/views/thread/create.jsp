<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div>

    <sf:form modelAttribute="thread" method="post" action="/thread">

        <sf:label path="title">Title:</sf:label>
        <sf:input path="title" maxlength="20"/>

        <sf:label path="text"/>
        <sf:textarea cssClass="text-width" path="text"/>
        <sf:errors path="text"/>

        <div class="spitItSubmitIt">
            <input type="submit" value="Let's Do It" class="btn btn-success"/>
        </div>
    </sf:form>

</div>