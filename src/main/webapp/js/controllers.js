angular.module('postsControllers', [])

    .controller('PostListController', ['$scope', '$routeParams', '$sce', 'PostList', 'PostPagination',
        function ($scope, $routeParams, $sce, PostList, PostPagination) {
            $scope.posts = PostList.query({pageId: $routeParams.pageId});
            $scope.makeTrust = function (html) { //TODO: config
                return $sce.trustAsHtml(html);
            };
            $scope.pagination = PostPagination.query({activePage: $routeParams.pageId});
        }])

    .controller('PostController', ['$scope', '$routeParams', '$sce', 'Post', function ($scope, $routeParams, $sce, Post) {
        $scope.post = Post.query({postId: $routeParams.postId});
        $scope.makeTrust = function (html) { //TODO: config
            return $sce.trustAsHtml(html);
        };
    }])

    .controller('View2Ctrl', [function () {
    }]);

