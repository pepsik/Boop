angular.module('ngBoilerplate.page', [
    'ui.router'
])

    .config(function ($stateProvider) {
        $stateProvider
            .state('page', {
                url: '/page',
                templateUrl: 'page/page.tpl.html',
                views: {
                    "main": {
                        controller: 'PageCtrl',
                        templateUrl: 'page/page.tpl.html'
                    }
                },
                data: {pageTitle: 'Pages'}
            })
            .state('page.paginated', {
                url: '/:page',
                templateUrl: 'page/page.tpl.html',
                controller: function ($stateParams) {
                    alert($stateParams.page);
                    expect($stateParams).toBe({page: 2});
                },
                data: {pageTitle: 'Pages'}
            });
        //.state('page', {
        //    url: '/page/:page',
        //    views: {
        //        "main": {
        //            controller: 'PageCtrl',
        //            templateUrl: 'page/page.tpl.html'
        //        }
        //    },
        //    data: {pageTitle: 'Pages'}
        //});
    })

    .factory('pageService', ['$resource', function ($resource) {
        var service = {};

        var performQuery = function () {
            return $resource('/rest/posts/', {}, {
                getPosts: {method: 'GET', params: {page: '1'}, isArray: false},
                getPostCount: {method: 'GET', isArray: false}
            });
        };
        service.getPosts = function (page) {
            return performQuery().getPosts({page: page});
        };
        service.getMaxPosts = function () {
            return performQuery().getPostCount();
        };
        return service;
    }])

    .controller('PageCtrl', function ($scope, $sce, $stateParams, $state, pageService) {
        $scope.posts = pageService.getPosts(1);
        $scope.makeTrust = function (html) {
            return $sce.trustAsHtml(html);
        };
        $scope.currentPostPage = 1;
        $scope.maxSize = 5;
        $scope.totalPosts = pageService.getMaxPosts();
        $scope.postsPerPage = 7;
        $scope.setPostPage = function (page) {
            $scope.posts = pageService.getPosts(page);
            $state.go('page.paginated', {page: page});
        };
    });