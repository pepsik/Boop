'use strict';

var routerApp = angular.module('routerApp', [
    'ui.router',
    'ngResource',
    'postControllers',
    'postServices'
])

    .config(function ($stateProvider) {
        $stateProvider.state('login', {
            url: '/login',
            views: {
                'main': {
                    templateUrl: 'account/login.tpl.html',
                    controller: 'LoginCtrl'
                }
            },
            data: {pageTitle: "Login"}
        })
            .state('register', {
                url: '/register',
                views: {
                    'main': {
                        templateUrl: 'account/register.tpl.html',
                        controller: 'RegisterCtrl'
                    }
                },
                data: {pageTitle: "Registration"}
            }
        )
            .state('accountSearch', {
                url: '/accounts/search',
                views: {
                    'main': {
                        templateUrl: 'account/search.tpl.html',
                        controller: 'AccountSearchCtrl'
                    }
                },
                data: {pageTitle: "Search Accounts"},
                resolve: {
                    accounts: function (accountService) {
                        return accountService.getAllAccounts();
                    }
                }
            });
    });


//angular.module('boopApp', [
//    //'ngRoute',
//    //'ngResource',
//    'ui.router',
//    'postServices',
//    'postControllers',
//    //'summernote',
//
//])
//
//    .run(function ($rootScope) {
//        $rootScope.$on('$viewContentLoaded', function (event) {
//            $('footer').fadeIn(500);
//        });
//    })
//    //.config(function ($sceDelegateProvider) {
//    //    $sceDelegateProvider.resourceUrlWhitelist([
//    //        'self',   // trust all resources from the same origin
//    //        '*://www.youtube.com/**'   // trust all resources from `www.youtube.com`
//    //    ]);
//    //})
//
//    .config(function ($stateProvider, $urlRouterProvider) {
//        $urlRouterProvider.otherwise('/home');
//
//        $stateProvider
//            .state('home', {
//                url: '/home',
//                views: {
//                    'main': {
//                        templateUrl: 'views/home.html',
//                        controller: 'PostListController'
//                    }
//                },
//                data: {pageTitle: "Home"}
//            })
//            .state('login', {
//                url: '/login',
//                views: {
//                    'main': {
//                        templateUrl: 'views/login.tpl.html',
//                        controller: 'LoginController'
//                    }
//                },
//                data: {pageTitle: "Login"}
//            })
//            .state('register', {
//                url: '/register',
//                views: {
//                    'main': {
//                        templateUrl: 'account/register.tpl.html',
//                        controller: 'RegisterCtrl'
//                    }
//                },
//                data: {pageTitle: "Registration"}
//            })
//
//    });

//.config(['$routeProvider', function ($routeProvider) {
//    $routeProvider
//        .when('/home', {
//            redirectTo: '/page/1'
//        })
//        .when('/page/:pageId', {
//            templateUrl: 'views/home.html',
//            controller: 'PostListController'
//        })
//        .when('/post/:postId', {
//            templateUrl: 'views/post.html',
//            controller: 'PostController'
//        })
//        .when('/login', {
//            templateUrl: 'views/login.tpl.html',
//            controller: 'LoginController'
//        })
//        .otherwise({redirectTo: '/page/1'});
//}]);
