<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>


<div>
    <div class="thread">
        <s:url value="/thread/{id}" var="thread_url">
            <s:param name="id" value="${thread.id}"/>
        </s:url>

        <h3><a class="label label-primary" href="${thread_url}">
            <c:out value="${thread.title}"/>
        </a></h3>

        <div class="thread">
            <c:out value="${thread.text}"/>
        </div>

        <div class="formHolder author text-info">
                    <span class="padding-top">
                    <small><joda:format value="${thread.when}" pattern="HH:mm MMM d, yyyy"/>
                        <c:out value="by ${thread.account.username}"/></small>
                    </span>

            <sf:form action="${thread_url}" method="delete">
                <input type="submit" class="btn btn-xs btn-danger" value="Delete"/>
            </sf:form>

            <sf:form action="${thread_url}/edit" method="get">
                <input type="submit" class="btn btn-xs" value="Edit"/>
            </sf:form>
        </div>
    </div>


    <c:forEach var="post" items="${thread.posts}">
        <div class="post">
            <c:out value="${post.text}"/> <br>

            <div class="formHolder author text-info">
                <small><joda:format value="${thread.when}" pattern="HH:mm MMM d, yyyy"/>
                    <c:out value="by ${thread.account.username}"/></small>


                <sf:form action="${thread_url}/post/${post.id}" method="delete">
                    <input type="submit" class="btn btn-xs btn-danger" value="Delete"/>
                </sf:form>

                <sf:form action="${thread_url}/post/${post.id}/edit" method="get">
                    <input type="submit" class="btn btn-xs btn-default" value="Edit"/>
                </sf:form>
            </div>
        </div>
    </c:forEach>

    <sf:form action="${thread_url}/post/new" method="get">
        <input type="submit" class="btn btn-success margin-top" value="New Post"/>
    </sf:form>

</div>