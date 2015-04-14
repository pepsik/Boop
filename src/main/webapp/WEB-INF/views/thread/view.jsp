<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>


<div>

    <s:url value="/thread/{id}" var="thread_url">
        <s:param name="id" value="${thread.id}"/>
    </s:url>

    <ol class="spittle-list">
        <li><span class="postListText">
                <a class="title-hyperlink" href="${thread_url}">
                    <c:out value="${thread.title}"/>
                </a> <br>
                <c:out value="${thread.text}"/>  </br>
            <small class="date"><joda:format value="${thread.when}" pattern="HH:mm MMM d, yyyy"/></small>
                </span>
        </li>

        <c:forEach var="post" items="${thread.posts}">
            <li class="formHolder">
                <c:out value="${post.text}"/>

                <sf:form action="${thread_url}/post/${post.id}" method="delete">
                    <input type="submit" class="btn btn-mini btn-danger" value="Delete"/>
                </sf:form>

                <sf:form action="${thread_url}/post/${post.id}/edit" method="get">
                    <input type="submit" class="btn btn-mini btn-inverse" value="Edit"/>
                </sf:form>
            </li>
        </c:forEach>

        <sf:form action="${thread_url}/post/new" method="get">
            <input type="submit" class="btn btn-success margin-top" value="New Post"/>
        </sf:form>

    </ol>
</div>