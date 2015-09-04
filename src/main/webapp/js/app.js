'use strict';

angular.module('myApp', [
    'ngRoute',
    'ngResource',
    'myApp.version',
    'postServices',
    'postsControllers',
    'summernote'
])

    .run(function ($rootScope) {
        $rootScope.$on('$viewContentLoaded', function (event) {
            $('footer').fadeIn(500);
        });
    })
    //.config(function ($sceDelegateProvider) {
    //    $sceDelegateProvider.resourceUrlWhitelist([
    //        'self',   // trust all resources from the same origin
    //        '*://www.youtube.com/**'   // trust all resources from `www.youtube.com`
    //    ]);
    //})

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/home', {
                redirectTo: '/page/1'
            })
            .when('/page/:pageId', {
                templateUrl: 'views/home.html',
                controller: 'PostListController'
            })
            .when('/post/:postId', {
                templateUrl: 'views/post.html',
                controller: 'PostController'
            })
            .when('/view2', {
                templateUrl: 'views/view2.html',
                controller: 'View2Ctrl'
            })
            .otherwise({redirectTo: '/page/1'});
    }]);
