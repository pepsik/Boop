var PostsControllers = angular.module('postsControllers', [])

    .controller('PostListController', ['$scope', '$routeParams', '$sce', 'PostList', 'PostPagination',
        function ($scope, $routeParams, $sce, PostList, PostPagination) {
            $scope.posts = PostList.query({pageId: $routeParams.pageId});
            $scope.makeTrust = function (html) {
                return $sce.trustAsHtml(html);
            };
            $scope.pagination = PostPagination.query({activePage: $routeParams.pageId});
        }])
    .controller('View2Ctrl', [function () {

    }]);