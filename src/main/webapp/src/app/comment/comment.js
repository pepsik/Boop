angular.module('ngBoilerplate.comment', [])

    .factory('Comment', ['$resource', function ($resource) {
        return $resource('/rest/posts/:postId/comments', {}, {
            query: {method: 'GET', params: {postId: '1'}, isArray: false}
        });
    }])

    .factory('EditedComment', ['$resource', function ($resource) {
        return $resource('/rest/posts/:postId/comments/:commentId', null,
            {
                'update': {method: 'PUT'}
            });
    }])

    .controller('CommentCtrl', function ($scope, $stateParams, $resource, Comment, EditedComment) {
        $scope.comments = Comment.query({postId: $stateParams.postId});

        var edited = {
            item: null,
            add: function (commentId) {
                if (this.item !== null) {
                    var editor = $("#" + this.item);
                    editor.code(tempData.text);
                    editor.destroy();
                }
                this.item = commentId;
            },
            get: function () {
                return this.item;
            },
            remove: function () {
                this.item = null;
            }
        };
        $scope.editMode = function (commentId) {
            if (commentId === 'undefined') {
                return false;
            }
            return edited.get() === commentId;
        };

        var tempData = {};
        $scope.editComment = function (commentId) {
            var editor = $("#" + commentId);
            edited.add(commentId);
            editor.summernote({
                height: 75,
                minHeight: 25,
                maxHeight: null,
                focus: true
            });
            tempData.text = editor.code();
        };
        $scope.deleteComment = function (index) {
            var comment = $resource("/rest/posts/" + $stateParams.postId + "/comments/" + $scope.comments.comments[index].rid);
            comment.remove({}, {},
                function () {
                    $scope.comments.comments.splice(index, 1);
                },
                function () {
                    alert("failure deleting comment");
                });
        };
        $scope.saveComment = function (commentId) {
            var editor = $("#" + commentId);
            var data = {
                "text": editor.code()
            };
            EditedComment.update({postId: $stateParams.postId, commentId: commentId}, data);
            editor.destroy();
            edited.remove();
        };
        $scope.cancelComment = function (commentId) {
            var editor = $("#" + commentId);
            editor.code(tempData.text);
            editor.destroy();
            edited.remove();
        };
    })

    .controller('CommentCreateCtrl', function ($scope, $stateParams, $resource) {
        $scope.comment = "";
        $scope.createComment = function () {
            var data = {
                "text": $scope.comment
            };
            var comment = $resource("/rest/posts/" + $stateParams.postId + "/comments");
            comment.save({}, data,
                function (returnedData) {
                    $scope.comments.comments.push(returnedData);
                    $scope.comment = "";
                },
                function () {
                    alert("error saving");
                });
        };
    });