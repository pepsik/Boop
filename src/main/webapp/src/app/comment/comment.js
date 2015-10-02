angular.module('ngBoilerplate.comment', [])

    .controller('CommentCreateCtrl', function ($scope) {
        $scope.text = "";
        $scope.click = function () {
            alert($scope.text);
            $scope.text = "";
        };
    });