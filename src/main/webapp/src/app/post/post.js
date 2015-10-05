angular.module('ngBoilerplate.post', [
    'ui.router',
    'ngBoilerplate.comment'
])

    .config(function ($stateProvider) {
        $stateProvider.state('post', {
            url: '/post/{postId:[0-9]{1,8}}',
            views: {
                "main": {
                    controller: 'PostCtrl',
                    templateUrl: 'post/post.tpl.html'
                }
            },
            data: {pageTitle: 'View post'}
        });
    })

    .config(function ($stateProvider) {
        $stateProvider.state('create_post', {
            url: '/post/new',
            views: {
                "main": {
                    controller: 'NewPostCtrl',
                    templateUrl: 'post/create.tpl.html'
                }
            },
            data: {pageTitle: 'New post'}
        });
    })

    .factory('Post', ['$resource', function ($resource) {
        return $resource('/rest/posts/:postId', {}, {
            query: {method: 'GET', params: {postId: '1'}, isArray: false}
        });
    }])

    .factory('EditedPost', ['$resource', function ($resource) {
        return $resource('/rest/posts/:postId', null,
            {
                'update': {method: 'PUT'}
            });
    }])

    .controller('PostCtrl', function ($scope, $stateParams, $sce, $resource, $state, Post, EditedPost) {
        $scope.post = Post.query({postId: $stateParams.postId});
        $scope.makeTrust = function (html) {
            return $sce.trustAsHtml(html);
        };

        var tempData = {};
        var summernote = $("#post_text");
        var editMode = false;
        $scope.isEditing = function () {
            return editMode;
        };

        $scope.editPost = function () {
            editMode = true;
            summernote.summernote({
                height: 350,
                minHeight: 150,
                maxHeight: null,
                focus: true
            });
            tempData.title = $scope.post.title;
            tempData.text = summernote.code();
        };

        $scope.cancelEdit = function () {
            summernote.code(tempData.text);
            $scope.post.title = tempData.title;
            summernote.destroy();
            editMode = false;
        };

        $scope.savePost = function () {
            var data = {
                "title": $scope.post.title,
                "text": summernote.code()
            };
            EditedPost.update({postId: $stateParams.postId}, data);
            summernote.destroy();
            editMode = false;
        };

        $scope.deletePost = function () {
            var post = $resource("/rest/posts/" + $stateParams.postId);
            post.remove({}, {},
                function () {
                    $state.go("page", {pageId: 1});
                },
                function () {
                    alert("error deleting");
                });
        };
    })

    .controller('NewPostCtrl', function ($scope, $resource, $state) {
        $scope.text = "";
        $scope.options = {
            height: 550,
            minHeight: 300,
            focus: true
        };

        $scope.createPost = function () {
            var data = {
                "title": $scope.post.title,
                "text": $scope.post.text
            };

            var post = $resource("/rest/posts");
            post.save({}, data,
                function (returnedData) {
                    $state.go("post", {postId: returnedData.rid});
                },
                function () {
                    alert("error saving");
                });
        };
    });