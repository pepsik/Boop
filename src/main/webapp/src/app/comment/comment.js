angular.module('ngBoilerplate.comment', [])

    .factory('Comment', ['$resource', function ($resource) {
        return $resource('/rest/posts/:postId/comments', {}, {
            query: {method: 'GET', params: {postId: '1'}, isArray: false}
        });
    }])

    .controller('CommentCtrl', function ($scope, $stateParams, $resource, Comment) {
        $scope.comments = Comment.query({postId: $stateParams.postId});
    })

    .controller('CommentCreateCtrl', function ($scope) {
        $scope.text = "";
        $scope.click = function () {
            alert($scope.text);
            $scope.text = "";
        };
    });