angular.module('templates-app', ['about/about.tpl.html', 'account/login.tpl.html', 'account/register.tpl.html', 'comment/comment.tpl.html', 'home/home.tpl.html', 'page/page.tpl.html', 'post/create.tpl.html', 'post/post.tpl.html', 'profile/profile.tpl.html', 'profile/public.tpl.html', 'profile/tabs.tpl.html', 'tag/tag.tpl.html']);

angular.module("about/about.tpl.html", []).run(["$templateCache", function($templateCache) {
  $templateCache.put("about/about.tpl.html",
    "<div class=\"row\">\n" +
    "  <h1 class=\"page-header\">\n" +
    "    The Elevator Pitch\n" +
    "    <small>For the lazy and impatient.</small>\n" +
    "  </h1>\n" +
    "  <p>\n" +
    "    <code>ng-boilerplate</code> is an opinionated kickstarter for web\n" +
    "    development projects. It's an attempt to create a simple starter for new\n" +
    "    web sites and apps: just download it and start coding. The goal is to\n" +
    "    have everything you need to get started out of the box; of course it has\n" +
    "    slick styles and icons, but it also has a best practice directory structure\n" +
    "    to ensure maximum code reuse. And it's all tied together with a robust\n" +
    "    build system chock-full of some time-saving goodness.\n" +
    "  </p>\n" +
    "\n" +
    "  <h2>Why?</h2>\n" +
    "\n" +
    "  <p>\n" +
    "    Because my team and I make web apps, and \n" +
    "    last year AngularJS became our client-side framework of choice. We start\n" +
    "    websites the same way every time: create a directory structure, copy and\n" +
    "    ever-so-slightly tweak some config files from an older project, and yada\n" +
    "    yada, etc., and so on and so forth. Why are we repeating ourselves? We wanted a starting point; a set of\n" +
    "    best practices that we could identify our projects as embodying and a set of\n" +
    "    time-saving wonderfulness, because time is money.\n" +
    "  </p>\n" +
    "\n" +
    "  <p>\n" +
    "    There are other similar projects out there, but none of them suited our\n" +
    "    needs. Some are awesome but were just too much, existing more as reference\n" +
    "    implementations, when we really just wanted a kickstarter. Others were just\n" +
    "    too little, with puny build systems and unscalable architectures.  So we\n" +
    "    designed <code>ng-boilerplate</code> to be just right.\n" +
    "  </p>\n" +
    "\n" +
    "  <h2>What ng-boilerplate Is Not</h2>\n" +
    "\n" +
    "  <p>\n" +
    "    This is not an example of an AngularJS app. This is a kickstarter. If\n" +
    "    you're looking for an example of what a complete, non-trivial AngularJS app\n" +
    "    that does something real looks like, complete with a REST backend and\n" +
    "    authentication and authorization, then take a look at <code><a\n" +
    "        href=\"https://github.com/angular-app/angular-app/\">angular-app</a></code>, \n" +
    "    which does just that, and does it well.\n" +
    "  </p>\n" +
    "\n" +
    "  <h1 class=\"page-header\">\n" +
    "    So What's Included?\n" +
    "    <small>I'll try to be more specific than \"awesomeness\".</small>\n" +
    "  </h1>\n" +
    "  <p>\n" +
    "    This section is just a quick introduction to all the junk that comes\n" +
    "    pre-packaged with <code>ng-boilerplate</code>. For information on how to\n" +
    "    use it, see the <a\n" +
    "      href=\"https://github.com/joshdmiller/ng-boilerplate#readme\">project page</a> at\n" +
    "    GitHub.\n" +
    "  </p>\n" +
    "\n" +
    "  <p>\n" +
    "    The high-altitude view is that the base project includes \n" +
    "    <a href=\"http://getbootstrap.com\">Twitter Bootstrap</a>\n" +
    "    styles to quickly produce slick-looking responsive web sites and apps. It also\n" +
    "    includes <a href=\"http://angular-ui.github.com/bootstrap\">UI Bootstrap</a>,\n" +
    "    a collection of native AngularJS directives based on the aforementioned\n" +
    "    templates and styles. It also includes <a href=\"http://fortawesome.github.com/Font-Awesome/\">Font Awesome</a>,\n" +
    "    a wicked-cool collection of font-based icons that work swimmingly with all\n" +
    "    manner of web projects; in fact, all images on the site are actually font-\n" +
    "    based icons from Font Awesome. Neat! Lastly, this also includes\n" +
    "    <a href=\"http://joshdmiller.github.com/angular-placeholders\">Angular Placeholders</a>,\n" +
    "    a set of pure AngularJS directives to do client-side placeholder images and\n" +
    "    text to make mocking user interfaces super easy.\n" +
    "  </p>\n" +
    "\n" +
    "  <p>\n" +
    "    And, of course, <code>ng-boilerplate</code> is built on <a href=\"http://angularjs.org\">AngularJS</a>,\n" +
    "    by the far the best JavaScript framework out there! But if you don't know\n" +
    "    that already, then how did you get here? Well, no matter - just drink the\n" +
    "    Kool Aid. Do it. You know you want to.\n" +
    "  </p>\n" +
    "  \n" +
    "  <h2>Twitter Bootstrap</h2>\n" +
    "\n" +
    "  <p>\n" +
    "    You already know about this, right? If not, <a\n" +
    "      href=\"http://getbootstrap.com\">hop on over</a> and check it out; it's\n" +
    "    pretty sweet. Anyway, all that wonderful stylistic goodness comes built in.\n" +
    "    The LESS files are available for you to import in your main stylesheet as\n" +
    "    needed - no excess, no waste. There is also a dedicated place to override\n" +
    "    variables and mixins to suit your specific needs, so updating to the latest\n" +
    "    version of Bootstrap is as simple as: \n" +
    "  </p>\n" +
    "\n" +
    "  <pre>$ cd vendor/twitter-bootstrap<br />$ git pull origin master</pre>\n" +
    "\n" +
    "  <p>\n" +
    "    Boom! And victory is ours.\n" +
    "  </p>\n" +
    "\n" +
    "  <h2>UI Bootstrap</h2>\n" +
    "\n" +
    "  <p>\n" +
    "    What's better than Bootstrap styles? Bootstrap directives!  The fantastic <a href=\"http://angular-ui.github.com/bootstrap\">UI Bootstrap</a>\n" +
    "    library contains a set of native AngularJS directives that are endlessly\n" +
    "    extensible. You get the tabs, the tooltips, the accordions. You get your\n" +
    "    carousel, your modals, your pagination. And <i>more</i>.\n" +
    "    How about a quick demo? \n" +
    "  </p>\n" +
    "\n" +
    "  <ul>\n" +
    "    <li class=\"dropdown\">\n" +
    "      <a class=\"btn dropdown-toggle\">\n" +
    "        Click me!\n" +
    "      </a>\n" +
    "      <ul class=\"dropdown-menu\">\n" +
    "        <li ng-repeat=\"choice in dropdownDemoItems\">\n" +
    "          <a>{{choice}}</a>\n" +
    "        </li>\n" +
    "      </ul>\n" +
    "    </li>\n" +
    "  </ul>\n" +
    "\n" +
    "  <p>\n" +
    "    Oh, and don't include jQuery;  \n" +
    "    you don't need it.\n" +
    "    This is better.\n" +
    "    Don't be one of those people. <sup>*</sup>\n" +
    "  </p>\n" +
    "\n" +
    "  <p><small><sup>*</sup> Yes, there are exceptions.</small></p>\n" +
    "\n" +
    "  <h2>Font Awesome</h2>\n" +
    "\n" +
    "  <p>\n" +
    "    Font Awesome has earned its label. It's a set of open (!) icons that come\n" +
    "    distributed as a font (!) for super-easy, lightweight use. Font Awesome \n" +
    "    works really well with Twitter Bootstrap, and so fits right in here.\n" +
    "  </p>\n" +
    "\n" +
    "  <p>\n" +
    "    There is not a single image on this site. All of the little images and icons \n" +
    "    are drawn through Font Awesome! All it takes is a little class:\n" +
    "  </p>\n" +
    "\n" +
    "  <pre>&lt;i class=\"fa fa-flag\"&gt;&lt/i&gt</pre>\n" +
    "\n" +
    "  <p>\n" +
    "    And you get one of these: <i class=\"fa fa-flag\"> </i>. Neat!\n" +
    "  </p>\n" +
    "\n" +
    "  <h2>Placeholders</h2>\n" +
    "\n" +
    "  <p>\n" +
    "    Angular Placeholders is a simple library for mocking up text and images. You\n" +
    "    can automatically throw around some \"lorem ipsum\" text:\n" +
    "  </p>\n" +
    "\n" +
    "  <pre>&lt;p ph-txt=\"3s\"&gt;&lt;/p&gt;</pre>\n" +
    "\n" +
    "  <p>\n" +
    "    Which gives you this:\n" +
    "  </p>\n" +
    "\n" +
    "  <div class=\"pre\">\n" +
    "    &lt;p&gt;\n" +
    "    <p ph-txt=\"3s\"></p>\n" +
    "    &lt;/p&gt;\n" +
    "  </div>\n" +
    "\n" +
    "  <p>\n" +
    "    Even more exciting, you can also create placeholder images, entirely \n" +
    "    client-side! For example, this:\n" +
    "  </p>\n" +
    "  \n" +
    "  <pre>\n" +
    "&lt;img ph-img=\"50x50\" /&gt;\n" +
    "&lt;img ph-img=\"50x50\" class=\"img-polaroid\" /&gt;\n" +
    "&lt;img ph-img=\"50x50\" class=\"img-rounded\" /&gt;\n" +
    "&lt;img ph-img=\"50x50\" class=\"img-circle\" /&gt;</pre>\n" +
    "\n" +
    "  <p>\n" +
    "    Gives you this:\n" +
    "  </p>\n" +
    "\n" +
    "  <div>\n" +
    "    <img ph-img=\"50x50\" />\n" +
    "    <img ph-img=\"50x50\" class=\"img-polaroid\" />\n" +
    "    <img ph-img=\"50x50\" class=\"img-rounded\" />\n" +
    "    <img ph-img=\"50x50\" class=\"img-circle\" />\n" +
    "  </div>\n" +
    "\n" +
    "  <p>\n" +
    "    Which is awesome.\n" +
    "  </p>\n" +
    "\n" +
    "  <h1 class=\"page-header\">\n" +
    "    The Roadmap\n" +
    "    <small>Really? What more could you want?</small>\n" +
    "  </h1>\n" +
    "\n" +
    "  <p>\n" +
    "    This is a project that is <i>not</i> broad in scope, so there's not really\n" +
    "    much of a wish list here. But I would like to see a couple of things:\n" +
    "  </p>\n" +
    "\n" +
    "  <p>\n" +
    "    I'd like it to be a little simpler. I want this to be a universal starting\n" +
    "    point. If someone is starting a new AngularJS project, she should be able to\n" +
    "    clone, merge, or download its source and immediately start doing what she\n" +
    "    needs without renaming a bunch of files and methods or deleting spare parts\n" +
    "    ... like this page. This works for a first release, but I just think there\n" +
    "    is a little too much here right now.\n" +
    "  </p>\n" +
    "\n" +
    "  <p>\n" +
    "    I'd also like to see a simple generator. Nothing like <a href=\"yeoman.io\">\n" +
    "    Yeoman</a>, as there already is one of those, but just something that\n" +
    "    says \"I want Bootstrap but not Font Awesome and my app is called 'coolApp'.\n" +
    "    Gimme.\" Perhaps a custom download builder like UI Bootstrap\n" +
    "    has. Like that. Then again, perhaps some Yeoman generators wouldn't be out\n" +
    "    of line. I don't know. What do you think?\n" +
    "  </p>\n" +
    "\n" +
    "  <p>\n" +
    "    Naturally, I am open to all manner of ideas and suggestions. See the \"Can I\n" +
    "    Help?\" section below.\n" +
    "  </p>\n" +
    "\n" +
    "  <h2>The Tactical To Do List</h2>\n" +
    "\n" +
    "  <p>\n" +
    "    There isn't much of a demonstration of UI Bootstrap. I don't want to pollute\n" +
    "    the code with a demo for demo's sake, but I feel we should showcase it in\n" +
    "    <i>some</i> way.\n" +
    "  </p>\n" +
    "\n" +
    "  <p>\n" +
    "    <code>ng-boilerplate</code> should include end-to-end tests. This should\n" +
    "    happen soon. I just haven't had the time.\n" +
    "  </p>\n" +
    "\n" +
    "  <p>\n" +
    "    Lastly, this site should be prettier, but I'm no web designer. Throw me a\n" +
    "    bone here, people!\n" +
    "  </p>\n" +
    "\n" +
    "  <h2>Can I Help?</h2>\n" +
    "\n" +
    "  <p>\n" +
    "    Yes, please!\n" +
    "  </p>\n" +
    "\n" +
    "  <p>\n" +
    "    This is an opinionated kickstarter, but the opinions are fluid and\n" +
    "    evidence-based. Don't like the way I did something? Think you know of a\n" +
    "    better way? Have an idea to make this more useful? Let me know! You can\n" +
    "    contact me through all the usual channels or you can open an issue on the\n" +
    "    GitHub page. If you're feeling ambitious, you can even submit a pull\n" +
    "    request - how thoughtful of you!\n" +
    "  </p>\n" +
    "\n" +
    "  <p>\n" +
    "    So join the team! We're good people.\n" +
    "  </p>\n" +
    "</div>\n" +
    "\n" +
    "");
}]);

angular.module("account/login.tpl.html", []).run(["$templateCache", function($templateCache) {
  $templateCache.put("account/login.tpl.html",
    "<div class=\"row\">\n" +
    "  <h1 class=\"page-header\">\n" +
    "      Login\n" +
    "  </h1>\n" +
    "  <form ng-submit=\"login()\">\n" +
    "      <div class=\"form-group\">\n" +
    "          <label>Login:</label>\n" +
    "          <input type=\"text\" ng-model=\"account.username\" class=\"form-control\"/>\n" +
    "      </div>\n" +
    "      <div class=\"form-group\">\n" +
    "          <label>Password:</label>\n" +
    "          <input type=\"password\" ng-model=\"account.password\" class=\"form-control\"/>\n" +
    "      </div>\n" +
    "      <button class=\"btn btn-success\" type=\"submit\">Login</button>\n" +
    "  </form>\n" +
    "</div>\n" +
    "\n" +
    "");
}]);

angular.module("account/register.tpl.html", []).run(["$templateCache", function($templateCache) {
  $templateCache.put("account/register.tpl.html",
    "<div class=\"row\">\n" +
    "  <h1 class=\"page-header\">\n" +
    "      Register\n" +
    "  </h1>\n" +
    "  <form ng-submit=\"register()\">\n" +
    "      <div class=\"form-group\">\n" +
    "          <label>Username:</label>\n" +
    "          <input type=\"text\" ng-model=\"account.username\" class=\"form-control\" />\n" +
    "      </div>\n" +
    "      <div class=\"form-group\">\n" +
    "          <label>Password:</label>\n" +
    "          <input type=\"password\" ng-model=\"account.password\" class=\"form-control\" />\n" +
    "      </div>\n" +
    "      <button class=\"btn btn-success\" type=\"submit\">Register</button>\n" +
    "  </form>\n" +
    "</div>\n" +
    "\n" +
    "");
}]);

angular.module("comment/comment.tpl.html", []).run(["$templateCache", function($templateCache) {
  $templateCache.put("comment/comment.tpl.html",
    "<ol>\n" +
    "    <li type=\"none\" class=\"comment\" ng-repeat=\"comment in comments.comments\">\n" +
    "        <div id=\"{{comment.rid}}\" ng-bind-html=\"makeTrust(comment.text)\"></div>\n" +
    "\n" +
    "        <div class=\"comment_bottom\">\n" +
    "                <span>\n" +
    "                    <b><a ui-sref=\"public_profile({username:'{{comment.author}}'})\">{{comment.author}}</a></b> &nbsp;&nbsp; {{comment.when | date:'short'}}\n" +
    "                </span>\n" +
    "\n" +
    "            <div style=\"float: right\">\n" +
    "                <div ng-show=\"canManage(comment.author)\">\n" +
    "                    <button class=\"btn btn-xs btn-default\" ng-click=\"editComment(comment.rid)\">\n" +
    "                        <span class=\"glyphicon glyphicon-pencil\"></span>&nbsp;&nbsp;Edit\n" +
    "                    </button>\n" +
    "                    <button class=\"btn btn-xs btn-danger\" ng-click=\"deleteComment($index)\">\n" +
    "                        <span class=\"glyphicon glyphicon-trash\"></span>\n" +
    "                    </button>\n" +
    "                </div>\n" +
    "                <div ng-show=\"canEdit(comment.rid)\">\n" +
    "                    <button class=\"btn btn-xs btn-success\" ng-click=\"updateComment(comment.rid)\">\n" +
    "                        <span class=\"glyphicon glyphicon-ok-sign\"></span>\n" +
    "                        Save\n" +
    "                    </button>\n" +
    "                    <button class=\"btn btn-xs btn-default\" ng-click=\"cancelComment(comment.rid)\">\n" +
    "                        <span class=\"glyphicon glyphicon-remove-sign\"></span>\n" +
    "                        Cancel\n" +
    "                    </button>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "        </div>\n" +
    "    </li>\n" +
    "</ol>\n" +
    "<div class=\"text-center\" style=\"margin-top: 30px\">\n" +
    "    <uib-pagination total-items=\"totalPosts.postCount\" items-per-page=\"postsPerPage\" ng-model=\"currentPage\" max-size=\"maxSize\" class=\"pagination-sm\"\n" +
    "                    boundary-links=\"true\" rotate=\"false\" num-pages=\"numPages\" ng-change=\"setPage(currentPage)\"></uib-pagination>\n" +
    "</div>");
}]);

angular.module("home/home.tpl.html", []).run(["$templateCache", function($templateCache) {
  $templateCache.put("home/home.tpl.html",
    "<!--<div ng-controller=\"PageCtrl\">-->\n" +
    "    <!--<ul type=\"none\">-->\n" +
    "        <!--<li class=\"post_list\" ng-repeat=\"post in posts\">-->\n" +
    "            <!--<h3><a class=\"label label-default\" href=\"#/post/{{post.id}}\">{{post.title}}</a></h3>-->\n" +
    "\n" +
    "            <!--<div class=\"post_body\" ng-bind-html=\"makeTrust(post.text)\"></div>-->\n" +
    "\n" +
    "            <!--<div class=\"post_bottom\">-->\n" +
    "                <!--<span class=\"comments_button\">-->\n" +
    "                    <!--comments - {{post.comments.length}}-->\n" +
    "                <!--</span>-->\n" +
    "                <!--<span class=\"post_date\">-->\n" +
    "                    <!--Posted by {{post.user.username}} on {{post.when | date:'MMM d, y H:mm:ss'}}-->\n" +
    "                <!--</span>-->\n" +
    "            <!--</div>-->\n" +
    "        <!--</li>-->\n" +
    "    <!--</ul>-->\n" +
    "\n" +
    "    <!--<ul class=\"menu text-center\">-->\n" +
    "        <!--<li>pages</li>-->\n" +
    "        <!--<li ng-repeat=\"pageId in pagination\"><a href=\"#/page/{{pageId}}\">{{pageId}}</a></li>-->\n" +
    "    <!--</ul>-->\n" +
    "<!--</div>-->\n" +
    "");
}]);

angular.module("page/page.tpl.html", []).run(["$templateCache", function($templateCache) {
  $templateCache.put("page/page.tpl.html",
    "<ul type=\"none\">\n" +
    "    <li class=\"post_list\" ng-repeat=\"post in posts.posts\">\n" +
    "\n" +
    "        <div class=\"post_title\">\n" +
    "            <h3><a class=\"label label-default\" href=\"#/post/{{post.rid}}\">{{post.title}}</a></h3>\n" +
    "        </div>\n" +
    "\n" +
    "        <div class=\"post_tags\">\n" +
    "                 <span ng-repeat=\"tagName in post.tagNames\">\n" +
    "                &nbsp;&nbsp;\n" +
    "                <a ui-sref=\"tag({name:'{{tagName}}', page:'1'})\" class=\"tag label label-default\">{{tagName}} </a>\n" +
    "                 </span>\n" +
    "        </div>\n" +
    "\n" +
    "        <div class=\"post_body\" ng-bind-html=\"makeTrust(post.text)\"></div>\n" +
    "\n" +
    "        <div class=\"post_bottom\">\n" +
    "                <span class=\"post_date\">\n" +
    "                    Posted by <a ui-sref=\"public_profile({username:'{{post.author}}'})\">{{post.author}}</a> on {{post.when | date:'MMM d, y H:mm:ss'}}\n" +
    "                </span>\n" +
    "        </div>\n" +
    "    </li>\n" +
    "</ul>\n" +
    "<div class=\"text-center\" style=\"margin-top: 30px\">\n" +
    "    <uib-pagination total-items=\"totalPosts.postCount\" items-per-page=\"postsPerPage\" ng-model=\"currentPage\" max-size=\"maxSize\" class=\"pagination-sm\"\n" +
    "                    boundary-links=\"true\" rotate=\"false\" num-pages=\"numPages\" ng-change=\"setPage(currentPage)\"></uib-pagination>\n" +
    "</div>\n" +
    "");
}]);

angular.module("post/create.tpl.html", []).run(["$templateCache", function($templateCache) {
  $templateCache.put("post/create.tpl.html",
    "<div ng-controller=\"NewPostCtrl\">\n" +
    "    <div class=\"post_body\" ng-show=\"isLoggedIn()\">\n" +
    "        <div class=\"row\">\n" +
    "            <div class=\"col-md-5\">\n" +
    "                <input type=\"text\" class=\"form-control\" ng-model=\"post.title\" maxlength=\"60\"\n" +
    "                       placeholder=\"Title here ...\"/>\n" +
    "            </div>\n" +
    "        </div>\n" +
    "        <div>\n" +
    "            <label id=\"tagNames\">Tags</label>\n" +
    "            <tags-input class=\"bootstrap\" ng-model=\"tags\">\n" +
    "                <!--<auto-complete source=\"loadTags($query)\"></auto-complete>-->\n" +
    "            </tags-input>\n" +
    "        </div>\n" +
    "        <br>\n" +
    "        <summernote config=\"options\" ng-model=\"post.text\"></summernote>\n" +
    "        <div style=\"text-align: right\">\n" +
    "            <button class=\"btn btn-default\" ng-click=\"createPost()\">Create Post</button>\n" +
    "        </div>\n" +
    "    </div>\n" +
    "</div>");
}]);

angular.module("post/post.tpl.html", []).run(["$templateCache", function($templateCache) {
  $templateCache.put("post/post.tpl.html",
    "<div ng-controller=\"PostCtrl\">\n" +
    "    <div class=\"post_list\">\n" +
    "        <div class=\"post_title\">\n" +
    "            <h3>\n" +
    "                <a class=\"label label-default\" href=\"#/post/{{post.rid}}\">{{post.title}}</a>\n" +
    "\n" +
    "                <div style=\"float: right\">\n" +
    "                    <div ng-show=\"canManage()\">\n" +
    "                        <button class=\"btn btn-xs btn-default\" ng-click=\"editPost()\">\n" +
    "                            <span class=\"glyphicon glyphicon-pencil\"></span>&nbsp;&nbsp;Edit\n" +
    "                        </button>\n" +
    "                        <button class=\"btn btn-xs btn-danger\" ng-click=\"deletePost()\">\n" +
    "                            <span class=\"glyphicon glyphicon-trash\"></span>\n" +
    "                        </button>\n" +
    "                    </div>\n" +
    "                    <div ng-show=\"canEdit()\">\n" +
    "                        <button class=\"btn btn-xs btn-success\" ng-click=\"updatePost()\">\n" +
    "                            <span class=\"glyphicon glyphicon-ok-sign\"></span>\n" +
    "                            Save\n" +
    "                        </button>\n" +
    "                        <button class=\"btn btn-xs btn-default\" ng-click=\"cancelPost()\">\n" +
    "                            <span class=\"glyphicon glyphicon-remove-sign\"></span>\n" +
    "                            Cancel\n" +
    "                        </button>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "            </h3>\n" +
    "            <div class=\"post_tags\">\n" +
    "                 <span ng-repeat=\"tagName in post.tagNames\">\n" +
    "                &nbsp;&nbsp;\n" +
    "                <a ui-sref=\"tag({name:'{{tagName}}', page:'1'})\" class=\"tag label label-default\">{{tagName.text}} </a>\n" +
    "                 </span>\n" +
    "            </div>\n" +
    "        </div>\n" +
    "        <div class=\"post_body\">\n" +
    "            <div class=\"form-group col-md-5\">\n" +
    "                <input type=\"text\" class=\"form-control\" ng-model=\"post.title\" ng-show=\"canEdit()\" maxlength=\"60\"\n" +
    "                       placeholder=\"Title here ...\"/>\n" +
    "            </div>\n" +
    "            <div class=\"form-group col-md-12\">\n" +
    "                <tags-input class=\"bootstrap\" ng-model=\"post.tagNames\" ng-show=\"canEdit()\"></tags-input>\n" +
    "            </div>\n" +
    "            <br>\n" +
    "        </div>\n" +
    "\n" +
    "        <div id=\"post_text\" ng-bind-html=\"makeTrust(post.text)\"></div>\n" +
    "\n" +
    "\n" +
    "        <div class=\"post_bottom\">\n" +
    "                <span class=\"post_date\">\n" +
    "                    Posted by <a ui-sref=\"public_profile({username:'{{post.author}}'})\">{{post.author}}</a> on {{post.when | date:'MMM d, y H:mm:ss'}}\n" +
    "                </span>\n" +
    "        </div>\n" +
    "    </div>\n" +
    "    <br>\n" +
    "\n" +
    "    <div ng-controller=\"CommentCtrl\">\n" +
    "        <ol>\n" +
    "            <li type=\"none\" class=\"comment\" ng-repeat=\"comment in comments.comments\">\n" +
    "                <div id=\"{{comment.rid}}\" ng-bind-html=\"makeTrust(comment.text)\"></div>\n" +
    "\n" +
    "                <div class=\"comment_bottom\">\n" +
    "                <span>\n" +
    "                    <b><a ui-sref=\"public_profile({username:'{{comment.author}}'})\">{{comment.author}}</a></b> &nbsp;&nbsp; {{comment.when | date:'short'}}\n" +
    "                </span>\n" +
    "\n" +
    "                    <div style=\"float: right\">\n" +
    "                        <div ng-show=\"canManage(comment.author)\">\n" +
    "                            <button class=\"btn btn-xs btn-default\" ng-click=\"editComment(comment.rid)\">\n" +
    "                                <span class=\"glyphicon glyphicon-pencil\"></span>&nbsp;&nbsp;Edit\n" +
    "                            </button>\n" +
    "                            <button class=\"btn btn-xs btn-danger\" ng-click=\"deleteComment($index)\">\n" +
    "                                <span class=\"glyphicon glyphicon-trash\"></span>\n" +
    "                            </button>\n" +
    "                        </div>\n" +
    "                        <div ng-show=\"canEdit(comment.rid)\">\n" +
    "                            <button class=\"btn btn-xs btn-success\" ng-click=\"updateComment(comment.rid)\">\n" +
    "                                <span class=\"glyphicon glyphicon-ok-sign\"></span>\n" +
    "                                Save\n" +
    "                            </button>\n" +
    "                            <button class=\"btn btn-xs btn-default\" ng-click=\"cancelComment(comment.rid)\">\n" +
    "                                <span class=\"glyphicon glyphicon-remove-sign\"></span>\n" +
    "                                Cancel\n" +
    "                            </button>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "            </li>\n" +
    "        </ol>\n" +
    "\n" +
    "        <div ng-controller=\"CommentCreateCtrl\">\n" +
    "            <div class=\"comment\" ng-show=\"isLoggedIn()\">\n" +
    "                <summernote height=\"250\" ng-model=\"comment\"></summernote>\n" +
    "                <div style=\"text-align: right\">\n" +
    "                    <button class=\"btn btn-default\" ng-click=\"createComment()\">Post comment</button>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "        </div>\n" +
    "        <br/>\n" +
    "    </div>\n" +
    "</div>");
}]);

angular.module("profile/profile.tpl.html", []).run(["$templateCache", function($templateCache) {
  $templateCache.put("profile/profile.tpl.html",
    "<div ng-controller=\"ProfileCtrl\">\n" +
    "    <div class=\"container-fluid col-md-6\">\n" +
    "        <div class=\"panel panel-default\">\n" +
    "            <div class=\"panel-heading\"><b>Profile</b></div>\n" +
    "            <div class=\"panel-body\">\n" +
    "                <form id=\"profile_form\" class=\"form-horizontal\" ng-submit=\"updateProfile()\">\n" +
    "                    <div class=\"form-group col-md-12\">\n" +
    "                        <label for=\"firstname\">Firstname</label>\n" +
    "                        <input type=\"text\" id=\"firstname\" class=\"form-control name\" ng-model=\"profile.firstname\"/>\n" +
    "                    </div>\n" +
    "                    <div class=\"form-group col-md-12\">\n" +
    "                        <label for=\"lastname\">Lastname</label>\n" +
    "                        <input type=\"text\" id=\"lastname\" class=\"form-control name\" ng-model=\"profile.lastname\"/>\n" +
    "                    </div>\n" +
    "                    <div class=\"form-group col-md-12\">\n" +
    "                        <label for=\"birthdate\">Birthdate</label>\n" +
    "                        <input type=\"date\" id=\"birthdate\" class=\"form-control date\" ng-model=\"profile.birthdate\"/>\n" +
    "                    </div>\n" +
    "                    <div class=\"form-group col-md-12\">\n" +
    "                        <label for=\"gender\" class=\"col-md-6\">Gender</label>\n" +
    "                        <select id=\"gender\" class=\"form-control\" ng-model=\"profile.gender\">\n" +
    "                            <option id=\"no_gender\" value=\"N\">Choose your gender</option>\n" +
    "                            <option id=\"male\" value=\"M\">Male</option>\n" +
    "                            <option id=\"female\" value=\"F\">Female</option>\n" +
    "                        </select>\n" +
    "                    </div>\n" +
    "                    <div class=\"form-group col-md-12\">\n" +
    "                        <label for=\"country\" class=\"col-md-6\">Country</label>\n" +
    "                        <input type=\"text\" id=\"country\" class=\"form-control\" ng-model=\"profile.country\"/>\n" +
    "                    </div>\n" +
    "                    <div class=\"form-group col-md-12\">\n" +
    "                        <label for=\"city\" class=\"col-md-6\">City</label>\n" +
    "                        <input type=\"text\" id=\"city\" class=\"form-control\" ng-model=\"profile.city\"/>\n" +
    "                    </div>\n" +
    "                    <div class=\"form-group col-md-12\">\n" +
    "                        <label for=\"job\" class=\"col-md-6\">Job</label>\n" +
    "                        <input type=\"text\" id=\"job\" class=\"form-control\" ng-model=\"profile.job\"/>\n" +
    "                    </div>\n" +
    "                    <div class=\"form-group col-md-12\">\n" +
    "                        <label for=\"about\" class=\"col-md-6\">About</label>\n" +
    "                        <textarea id=\"about\" rows=\"5\" maxlength=\"500\" class=\"form-control\"\n" +
    "                                  ng-model=\"profile.about\"></textarea>\n" +
    "                    </div>\n" +
    "                </form>\n" +
    "            </div>\n" +
    "            <div class=\"panel-footer\">\n" +
    "                <button form=\"profile_form\" type=\"submit\" class=\"btn btn-success\">Update</button>\n" +
    "            </div>\n" +
    "        </div>\n" +
    "    </div>\n" +
    "\n" +
    "    <div class=\"container-fluid col-md-5\">\n" +
    "        <div class=\"panel panel-default\">\n" +
    "            <div class=\"panel-heading\"><b>Avatar</b></div>\n" +
    "            <div class=\"panel-body\">\n" +
    "\n" +
    "                <img src=\"/uploads/avatars/{{username}.jpeg\" width=\"350\"\n" +
    "                     class=\"img-rounded\"\n" +
    "                     onError=\"this.src='/uploads/avatars/def-ava.png';\"/>\n" +
    "                <br>\n" +
    "                Resize image\n" +
    "                <input type=\"range\" class=\"cropit-image-zoom-input custom\" min=\"0\" max=\"1\" step=\"0.01\"/>\n" +
    "                <input type=\"hidden\" name=\"image-data\" class=\"hidden-image-data\"/>\n" +
    "                <br>\n" +
    "                <button form=\"imageForm\" class=\"btn btn-success\" type=\"submit\">Upload</button>\n" +
    "            </div>\n" +
    "        </div>\n" +
    "    </div>\n" +
    "</div>\n" +
    "");
}]);

angular.module("profile/public.tpl.html", []).run(["$templateCache", function($templateCache) {
  $templateCache.put("profile/public.tpl.html",
    "<div class=\"container\" style=\"margin-top: 40px\">\n" +
    "    <div class=\"col-md-5\">\n" +
    "        <div>\n" +
    "            <img src=\"#\" width=\"350\"\n" +
    "                 class=\"img-rounded\"\n" +
    "                 onError=\"this.src='/uploads/avatars/def-ava.png';\"/>\n" +
    "        </div>\n" +
    "        <div style=\"margin-top:20px \">\n" +
    "            <span class=\"glyphicon glyphicon-time margin-top\"></span>&nbsp;&nbsp;Joined on Feb 18, 2013\n" +
    "            <br>\n" +
    "            <span class=\"glyphicon glyphicon-user margin-top\"></span>&nbsp;&nbsp;as Member\n" +
    "            <br>\n" +
    "            <span class=\"glyphicon glyphicon-flag margin-top\"></span>&nbsp;&nbsp;Last seen at Feb 18, 2013\n" +
    "            <br>\n" +
    "            <span class=\"glyphicon glyphicon-thumbs-up margin-top\"></span>&nbsp;&nbsp;0 &nbsp;&nbsp;&nbsp;&nbsp;\n" +
    "        </div>\n" +
    "    </div>\n" +
    "\n" +
    "    <div class=\"col-md-6\">\n" +
    "        <div class=\"panel panel-success\">\n" +
    "            <div class=\"panel-heading\" style=\"padding-left: 40px\"><h4><b>{{username}}</b></h4></div>\n" +
    "            <div class=\"panel-body\">\n" +
    "                <table class=\"table\">\n" +
    "                    <tbody>\n" +
    "                    <tr>\n" +
    "                        <td>Firstname</td>\n" +
    "                        <td>{{profile.firstname}}</td>\n" +
    "                    </tr>\n" +
    "                    <tr>\n" +
    "                        <td>Lastname</td>\n" +
    "                        <td>{{profile.lastname}}</td>\n" +
    "                    </tr>\n" +
    "\n" +
    "                    <tr>\n" +
    "                        <td>Birthdate</td>\n" +
    "                        <td>{{profile.birthdate}}</td>\n" +
    "                    </tr>\n" +
    "                    <tr>\n" +
    "                        <td>Gender</td>\n" +
    "                        <td>{{profile.gender}}</td>\n" +
    "                    </tr>\n" +
    "                    <tr>\n" +
    "                        <td>Country</td>\n" +
    "                        <td>{{profile.country}}</td>\n" +
    "                    </tr>\n" +
    "                    <tr>\n" +
    "                        <td>City</td>\n" +
    "                        <td>{{profile.city}}</td>\n" +
    "                    </tr>\n" +
    "                    <tr>\n" +
    "                        <td>Job</td>\n" +
    "                        <td>{{profile.job}}</td>\n" +
    "                    </tr>\n" +
    "                    <tr>\n" +
    "                        <td>About</td>\n" +
    "                        <td>{{profile.about}}</td>\n" +
    "                    </tr>\n" +
    "                    </tbody>\n" +
    "                </table>\n" +
    "            </div>\n" +
    "        </div>\n" +
    "    </div>\n" +
    "</div>\n" +
    "");
}]);

angular.module("profile/tabs.tpl.html", []).run(["$templateCache", function($templateCache) {
  $templateCache.put("profile/tabs.tpl.html",
    "<div ng-controller=\"PublicProfileCtrl\">\n" +
    "    <uib-tabset>\n" +
    "        <uib-tab ng-repeat=\"tab in tabs\" heading=\"{{tab.title}}\" active=\"tab.active\" disable=\"tab.disabled\">\n" +
    "            <div ng-include=\"tab.template\"></div>\n" +
    "        </uib-tab>\n" +
    "    </uib-tabset>\n" +
    "</div>");
}]);

angular.module("tag/tag.tpl.html", []).run(["$templateCache", function($templateCache) {
  $templateCache.put("tag/tag.tpl.html",
    "<div ng-controller=\"TagCtrl\">\n" +
    "    <div class=\"container\">\n" +
    "        <img ng-src=\"{{tag.imageUrl}}\" style=\"max-width: 900px; max-height: 400px\"/>\n" +
    "\n" +
    "        <h1><span class=\"tag label label-default\">{{tag.name}}</span></h1>\n" +
    "\n" +
    "        <p class=\"bg-primary\">\n" +
    "            &nbsp;&nbsp;&nbsp;Date created: {{tag.createDate | date:'MMM d, y H:mm:ss'}}\n" +
    "            &nbsp;&nbsp;&nbsp;Author: {{tag.author}}\n" +
    "            &nbsp;&nbsp;&nbsp;Posts count: {{tag.postsCount}}\n" +
    "            &nbsp;&nbsp;&nbsp;Ratings: 0\n" +
    "            &nbsp;&nbsp;&nbsp;Subscribers count: 0\n" +
    "        </p>\n" +
    "\n" +
    "        <div class=\"row\">\n" +
    "            <div class=\"container col-sm-offset-0\" style=\"background-color:lavender;\">\n" +
    "                {{tag.description}}\n" +
    "            </div>\n" +
    "        </div>\n" +
    "\n" +
    "        <ul type=\"none\">\n" +
    "            <li class=\"post_list\" ng-repeat=\"post in posts.posts\">\n" +
    "                <div class=\"post_title\">\n" +
    "                    <h3><a class=\"label label-default\" href=\"#/post/{{post.rid}}\">{{post.title}}</a></h3>\n" +
    "                </div>\n" +
    "\n" +
    "                <div class=\"post_tags\">\n" +
    "                 <span ng-repeat=\"tagName in post.tagNames\">\n" +
    "                &nbsp;&nbsp;\n" +
    "                <a ui-sref=\"tag({name:'{{tagName}}'})\" class=\"tag label label-default\">{{tagName}} </a>\n" +
    "                 </span>\n" +
    "                </div>\n" +
    "\n" +
    "                <div class=\"post_body\" ng-bind-html=\"makeTrust(post.text)\"></div>\n" +
    "\n" +
    "                <div class=\"post_bottom\">\n" +
    "                <span class=\"post_date\">\n" +
    "                    Posted by <a ui-sref=\"public_profile({username:'{{post.author}}'})\">{{post.author}}</a> on {{post.when | date:'MMM d, y H:mm:ss'}}\n" +
    "                </span>\n" +
    "                </div>\n" +
    "            </li>\n" +
    "        </ul>\n" +
    "        <ul class=\"menu text-center\">\n" +
    "            <li>pages</li>\n" +
    "            <li ng-repeat=\"pageId in pagination\"><a href=\"#/page/{{pageId}}\">{{pageId}}</a></li>\n" +
    "        </ul>\n" +
    "    </div>\n" +
    "</div>");
}]);
