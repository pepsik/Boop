angular.module('ngBoilerplate.page', [
    'ui.router'
])

    .config(function ($stateProvider) {
        $stateProvider.state('page', {
            url: '/page/:pageId',
            views: {
                "main": {
                    controller: 'PageCtrl',
                    templateUrl: 'page/page.tpl.html'
                }
            },
            data: {pageTitle: 'Pages'}
        });
    })

    .factory('PostList', ['$resource', function ($resource) {
        return $resource('/rest/posts/', {}, {
            query: {method: 'GET', params: {pageId: '1'}, isArray: false}
        });
    }])

    .factory('Pagination', ['$resource', function ($resource) {
        return $resource('/api/pagination', {}, {
            query: {method: 'GET', params: {activePage: '1'}, isArray: true}
        });
    }])

    .controller('PageCtrl', function ($scope, $sce, $stateParams, PostList, Pagination) {
        $scope.posts = PostList.query({pageId: $stateParams.pageId});
        $scope.makeTrust = function (html) { //TODO: config
            return $sce.trustAsHtml(html);
        };
        //$scope.pagination = Pagination.query({activePage: $stateParams.pageId});
    });