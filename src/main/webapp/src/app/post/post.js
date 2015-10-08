angular.module('ngBoilerplate.post', [
    'ui.router'
])

    .config(function ($stateProvider) {
        $stateProvider
            .state('view_post', {
                url: '/post/{postId:[0-9]{1,8}}',
                views: {
                    "main": {
                        templateUrl: 'post/post.tpl.html'
                    }
                },
                data: {pageTitle: 'View post'}
            })
            .state('create_post', {
                url: '/post/new',
                views: {
                    "main": {
                        templateUrl: 'post/create.tpl.html'
                    }
                },
                data: {pageTitle: 'New post'}
            });
    })

    .factory('postService', ['$resource', '$state', 'postManager', function ($resource, $state, postManager) {
        var service = {};
        var tempData = {
            id: null,
            title: null,
            text: null,
            editor: null
        };
        var performQuery = function () {
            return $resource('/rest/posts/:postId', {}, {
                create: {method: 'POST'},
                get: {method: 'GET', isArray: false},
                update: {method: 'PUT'},
                remove: {method: 'DELETE'}
            });
        };
        service.createPost = function (data, success, failure) {
            performQuery().create({}, data, success, failure);
        };
        service.getPost = function (id) {
            tempData.id = id;
            return performQuery().get({postId: id});
        };
        service.updatePost = function (data) {
            performQuery().update({postId: tempData.id}, data,
                function () {/*success*/
                    editor.destroy();
                    postManager.disableEditing();
                },
                function () {
                    alert("failure updating post");
                });
        };
        service.deletePost = function () {
            performQuery().remove({postId: tempData.id},
                function () {/*success*/
                    $state.go("page", {pageId: 1});
                },
                function () {
                    alert("error deleting");
                });
        };
        service.editPost = function (editor, title) {
            postManager.enableEditing();
            editor.summernote({
                height: 350,
                minHeight: 150,
                maxHeight: null,
                focus: true
            });
            tempData.title = title;
            tempData.text = editor.code();
            tempData.editor = editor;
        };
        service.cancelPost = function () {
            tempData.editor.code(tempData.text);
            tempData.editor.destroy();
            postManager.disableEditing();
            return tempData.title;
        };
        return service;
    }])

    .factory('postManager', function ($rootScope) {
        var loggedUser = $rootScope.loggedUser;
        var service = {};
        var editMode = false;
        service.canEdit = function () {
            return editMode;
        };
        service.canManage = function (author) {
            if (editMode === true) {
                return false;
            } else {
                return loggedUser === author;
            }
        };
        service.enableEditing = function () {
            editMode = true;
        };
        service.disableEditing = function () {
            editMode = false;
        };
        return service;
    })

    .controller('PostCtrl', function ($scope, $stateParams, $sce, $state, postService, postManager) {
        $scope.post = postService.getPost($stateParams.postId);
        $scope.makeTrust = function (html) {
            return $sce.trustAsHtml(html);
        };
        var editor = $("#post_text");
        $scope.canManage = function () {
            return postManager.canManage($scope.post.author);
        };
        $scope.canEdit = postManager.canEdit;
        $scope.editPost = function () {
            postService.editPost(editor, $scope.post.title);
        };
        $scope.updatePost = function () {
            var data = {
                title: $scope.post.title,
                text: editor.code()
            };
            postService.updatePost(data);
        };
        $scope.deletePost = postService.deletePost;
        $scope.cancelPost = function () {
            $scope.post.title = postService.cancelPost();
        };
        postManager.disableEditing();
    })

    .controller('NewPostCtrl', function ($scope, $resource, $state, postService) {
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
            postService.createPost(data,
                function (returnedData) {
                    $scope.text = "";
                    $state.go("view_post", {postId: returnedData.rid});
                },
                function () {
                    alert("error saving");
                });
        };
    });