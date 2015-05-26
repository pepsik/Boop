<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <title>Post edit</title>
</head>

<div>
    <s:url value="/post/${post.id}" var="post_url"/>

    <div class="well">
        <sf:form modelAttribute="post" method="PUT" action="${post_url}">
            <sf:label path="title"><spring:message code="label.post.edit.title"/></sf:label>
            <div class="form-group">
                <sf:errors path="title" cssClass="label label-danger"/>
                <sf:input path="title" cssClass="form-control" maxlength="40"/>
            </div>
            <div>
                <spring:bind path="post.tags">
                    <input type="text" name="${status.expression}" id="tags" value="${status.value}"
                           data-role="tagsinput"/>
                </spring:bind>
            </div>
            <sf:errors path="text" cssClass="label label-danger"/> <br>
            <form:textarea path="text" id="summernote"/>
            <br>

            <div class="submit">
                <button type="submit" onclick="autolink()" class="btn btn-success"><spring:message
                        code="button.edit"/></button>
            </div>
        </sf:form>
    </div>
</div>

<script type="text/javascript">

    $(document).ready(function () {
        $('#summernote').summernote({
            height: 450,
            minHeight: 200,
            maxHeight: null,
            focus: true
        });
    });

    function autolink() {
        var input = $('#summernote').code();  // string with URLs, Email Addresses, Phone #s, Twitter Handles, and Hashtags

        var linkedText = Autolinker.link(input, {
            replaceFn: function (autolinker, match) {
                console.log("href = ", match.getAnchorHref());
                console.log("text = ", match.getAnchorText());

                switch (match.getType()) {
                    case 'url' :
                        console.log("url: ", match.getUrl());

                        if (match.getUrl().indexOf('mysite.com') === -1) {
                            var tag = autolinker.getTagBuilder().build(match);  // returns an `Autolinker.HtmlTag` instance, which provides mutator methods for easy changes
                            tag.setAttr('rel', 'nofollow');
                            tag.addClass('external-link');

                            return tag;

                        } else {
                            return true;  // let Autolinker perform its normal anchor tag replacement
                        }

                    case 'email' :
                        var email = match.getEmail();
                        console.log("email: ", email);

                        if (email === "my@own.address") {
                            return false;  // don't auto-link this particular email address; leave as-is
                        } else {
                            return;  // no return value will have Autolinker perform its normal anchor tag replacement (same as returning `true`)
                        }

                    case 'phone' :
                        var phoneNumber = match.getPhoneNumber();
                        console.log(phoneNumber);

                        return '<a href="http://newplace.to.link.phone.numbers.to/">' + phoneNumber + '</a>';

                    case 'twitter' :
                        var twitterHandle = match.getTwitterHandle();
                        console.log(twitterHandle);

                        return '<a href="http://newplace.to.link.twitter.handles.to/">' + twitterHandle + '</a>';

                    case 'hashtag' :
                        var hashtag = match.getHashtag();
                        console.log(hashtag);

                        return '<a href="http://newplace.to.link.hashtag.handles.to/">' + hashtag + '</a>';
                }
            }
        });
        alert(linkedText);
    }

</script>
