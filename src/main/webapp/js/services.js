var postServices = angular.module('postServices', ['ngResource']);

postServices.factory('PostList', ['$resource',
    function ($resource) {
        return $resource('/api/page/:pageId', {}, {
            query: {method: 'GET', params: {pageId: '1'}, isArray: true}
        });
    }]);

postServices.factory('PostPagination', ['$resource',
    function ($resource) {
        return $resource('/api/pagination', {}, {
            query: {method: 'GET', params: {activePage: '1'}, isArray: true}
        });
    }]);
