angular.module('ngBoilerplate.page', [
    'ui.router'
])

    .config(function ($stateProvider) {
        $stateProvider.state('page', {
            url: '/page/:page',
            views: {
                "main": {
                    controller: 'PageCtrl',
                    templateUrl: 'page/page.tpl.html'
                }
            },
            data: {pageTitle: 'Pages'}
        });
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

    .controller('PageCtrl', function ($scope, $sce, $stateParams, pageService) {
        $scope.posts = pageService.getPosts($stateParams.page);
        $scope.makeTrust = function (html) {
            return $sce.trustAsHtml(html);
        };
        $scope.maxSize = 5;
        $scope.totalPosts = pageService.getMaxPosts();
        $scope.currentPostPage = 1;
        $scope.postsPerPage = 3;
        $scope.setPostPage = function (page) {
            $scope.currentPostPage = page;
            $scope.posts = pageService.getPosts(page);
        };
    });