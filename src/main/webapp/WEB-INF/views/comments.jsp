<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<c:forEach var="post" items="${postList}">
    <div class="post">
        <div class="summernote margin-bottom">${post.text}</div>
        <div class="formHolder author text-info">
            <s:url value="/account/{id}" var="account_url">
                <s:param name="id" value="${post.account.id}"/>
            </s:url>
            <small><joda:format value="${post.when}" pattern="HH:mm MMM d, yyyy"/>
                <c:out value="by "/>
                <a href="${account_url}">${post.account.username}</a></small>

            <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                <sec:authentication property="principal.username" var="authorizedUser"/>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <c:set var="access" value="${true}" scope="page"/>
                </sec:authorize>

                <c:if test="${authorizedUser.equals(post.account.username) or access}"> <!-- Shit -->
                    <sf:form action="${thread_url}/post/${post.id}" method="delete">
                        <input type="submit" class="btn btn-xs btn-danger" value="Delete"/>
                    </sf:form>
                    <sf:form action="${thread_url}/post/${post.id}/edit" method="get">
                        <input type="submit" class="btn btn-xs btn-default" value="Edit"/>
                    </sf:form>
                </c:if>
            </sec:authorize>
        </div>
    </div>
</c:forEach>

