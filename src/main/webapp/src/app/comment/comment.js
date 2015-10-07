angular.module('ngBoilerplate.comment', [])

    .factory('commentService', ['$resource', 'commentManager', function ($resource, commentManager) {
        var service = {};
        var tempData = {
            postId: null,
            text: null
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
        service.updateComment = function (id, success, failure) {
            var editor = $("#" + id);
            var data = {
                "text": editor.code()
            };
            performQuery().update({postId: tempData.postId, commentId: id}, data, success, failure);
            editor.destroy();
            commentManager.disableEditing();
        };
        service.deleteComment = function (id, success, failure) {
            performQuery().remove({postId: tempData.postId, commentId: id}, success, failure);
        };
        service.editComment = function (id) {
            commentManager.enableEditing(id, tempData);
            var editor = $("#" + id);
            editor.summernote({
                height: 75,
                minHeight: 25,
                maxHeight: 1000,
                focus: true
            });
            tempData.text = editor.code();
        };
        service.cancelComment = function (id) {
            var editor = $("#" + id);
            editor.code(tempData.text);
            editor.destroy();
            commentManager.disableEditing();
        };
        return service;
    }])

    .factory('commentManager', function () {
        var service = {};
        var commentId = null;
        var commentText = null;
        var hasPermission = true;
        service.canManage = function () {
            return hasPermission;
        };
        service.canEdit = function (id) {
            if (commentId === undefined) {
                return false;
            }
            else {
                return commentId === id;
            }
        };
        service.enableEditing = function (id, tempData) {
            this.disableManaging();
            commentId = id;
            commentText = tempData;
        };
        service.disableEditing = function () {
            this.enableManaging();
            if (commentId !== null) {
                var editor = $("#" + commentId);
                editor.code(commentText);
                editor.destroy();
                commentId = null;
                commentText = null;
            }
        };
        service.enableManaging = function () {
            hasPermission = true;
        };
        service.disableManaging = function () {
            hasPermission = false;
        };
        return service;
    })

    .controller('CommentCtrl', function ($scope, $stateParams, commentService, commentManager) {
        $scope.comments = commentService.getComments($stateParams.postId);
        $scope.updateComment = function (id) {
            commentService.updateComment(id,
                function () {/*success*/
                },
                function () {
                    alert("failure updating comment");
                });
        };
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