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

    .factory('PostService', ['$resource', function ($resource) {
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

    .controller('PageCtrl', function ($scope, $sce, $stateParams, PostService) {
        $scope.posts = PostService.getPosts($stateParams.page);
        $scope.makeTrust = function (html) {
            return $sce.trustAsHtml(html);
        };
        $scope.maxSize = 5;
        $scope.totalPosts = PostService.getMaxPosts();
        $scope.currentPage = 1;
        $scope.postsPerPage = 3;
        $scope.setPage = function (page) {
            $scope.currentPage = page;
            $scope.posts = PostService.getPosts(page);
        };
    });