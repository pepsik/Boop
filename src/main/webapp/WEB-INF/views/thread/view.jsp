<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>


<div>
    <ol class="spittle-list">

        <li><span class="postListText">
                <a class="title-hyperlink" href="${thread_url}">
                    <c:out value="${thread.title}"/>
                </a> <br>
                <c:out value="${thread.text}"/>  </br>
            <small class="date"><joda:format value="${thread.when}" pattern="HH:mm MMM d, yyyy"/></small>
                </span>
        </li>

    </ol>
</div>