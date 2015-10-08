angular.module('ngBoilerplate.comment', [])

    .factory('commentService', ['$resource', 'commentManager', function ($resource, commentManager) {
        var service = {};
        var tempData = {
            postId: null,
            commentId: null,
            commentText: null
        };
        var performQuery = function () {
            return $resource({}, {}, {
                create: {method: 'POST', url: '/rest/posts/:postId/comments'},
                get: {method: 'GET', url: '/rest/posts/:postId/comments', isArray: false},
                update: {method: 'PUT', url: '/rest/posts/:postId/comments/:commentId'},
                remove: {method: 'DELETE', url: '/rest/posts/:postId/comments/:commentId'}
            });
        };
        service.createComment = function (text, success, failure) {
            var data = {
                "text": text
            };
            performQuery().create({postId: tempData.postId}, data, success, failure);
        };
        service.getComments = function (postId) {
            tempData.postId = postId;
            return performQuery().get({postId: postId});
        };
        service.updateComment = function (id) {
            var editor = $("#" + id);
            var data = {
                "text": editor.code()
            };
            performQuery().update({postId: tempData.postId, commentId: id}, data,
                function () {/*success*/
                    editor.destroy();
                    commentManager.disableEditing();
                },
                function () {
                    alert("failure updating comment");
                });
        };
        service.deleteComment = function (id, success, failure) {
            performQuery().remove({postId: tempData.postId, commentId: id}, success, failure);
        };
        service.editComment = function (id) {
            commentManager.enableEditing(id);
            var editor = $("#" + id);
            editor.summernote({
                height: 75,
                minHeight: 25,
                maxHeight: 1000,
                focus: true
            });
            tempData.commentText = editor.code();
        };
        service.cancelComment = function (id) {
            var editor = $("#" + id);
            editor.code(tempData.commentText);
            editor.destroy();
            commentManager.disableEditing();
        };
        return service;
    }])

    .factory('commentManager', function ($rootScope) {
        var loggedUser = $rootScope.loggedUser;
        var service = {};
        var comment = {
            id: null,
            isEditing: null
        };
        service.canManage = function (author) {
            if (comment.isEditing === true) {
                return false;
            } else {
                return author === loggedUser;
            }
        };
        service.canEdit = function (id) {
            return comment.id === id;
        };
        service.enableEditing = function (id) {
            comment.isEditing = true;
            comment.id = id;
        };
        service.disableEditing = function () {
            comment.isEditing = false;
            comment.id = null;
        };
        return service;
    })

    .controller('CommentCtrl', function ($scope, $stateParams, commentService, commentManager) {
        $scope.comments = commentService.getComments($stateParams.postId);
        $scope.updateComment = commentService.updateComment;
        $scope.deleteComment = function (index) {
            commentService.deleteComment($scope.comments.comments[index].rid,
                function () {/*success*/
                    $scope.comments.comments.splice(index, 1);
                },
                function () {
                    alert("failure deleting comment");
                });
        };
        $scope.editComment = commentService.editComment;
        $scope.cancelComment = commentService.cancelComment;
        $scope.canManage = commentManager.canManage;
        $scope.canEdit = commentManager.canEdit;
        commentManager.disableEditing();
    })

    .controller('CommentCreateCtrl', function ($scope, commentService) {
        $scope.createComment = function () {
            commentService.createComment($scope.comment,
                function (returnedData) {
                    $scope.comments.comments.push(returnedData);
                    $scope.comment = "";
                },
                function () {
                    alert("error creating");
                });
        };
    });