angular.module('postControllers', [])

    .controller('PostListController', function ($scope, $sce, PostList, Pagination, sessionService) {
        $scope.posts = PostList.query();
        $scope.makeTrust = function (html) { //TODO: config
            return $sce.trustAsHtml(html);
        };
        $scope.pagination = Pagination.query();
        //$scope.isLoggedIn = sessionService.isLoggedIn;
    })

    .controller('PostController', function ($scope, $routeParams, $sce, Post) {
        $scope.post = Post.query({postId: $routeParams.postId});
        $scope.makeTrust = function (html) { //TODO: config
            return $sce.trustAsHtml(html);
        };
    })

    .controller('LoginController', function ($scope, sessionService, $state) {
        $scope.login = function () {
            sessionService.login($scope.account);
            $state.go("home");
        }
    });

