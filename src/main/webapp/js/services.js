angular.module('postServices', ['ngResource'])

    .factory('PostList', ['$resource',
        function ($resource) {
            return $resource('/api/page/:pageId', {}, {
                query: {method: 'GET', params: {pageId: '1'}, isArray: true}
            });
        }])

    .factory('PostPagination', ['$resource',
        function ($resource) {
            return $resource('/api/pagination', {}, {
                query: {method: 'GET', params: {activePage: '1'}, isArray: true}
            });
        }])

    .factory('Post', ['$resource', function ($resource) {
        return $resource('/api/post/:postId', {}, {
            query: {method: 'GET', params: {postId: '1'}, isArray: false}
        });
    }]);
