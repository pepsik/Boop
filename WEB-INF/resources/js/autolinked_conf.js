function autolink(Element) {
    var editor = $("#" + Element);
    var input = editor.code();  // string with URLs, Email Addresses, Twitter Handles, and Hashtags

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

    editor.code(linkedText);
}