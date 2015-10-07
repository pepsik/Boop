angular.module('ngBoilerplate.post', [
    'ui.router'
])

    .config(function ($stateProvider) {
        $stateProvider
            .state('view_post', {
                url: '/post/{postId:[0-9]{1,8}}',
                views: {
                    "main": {
                        controller: 'PostCtrl',
                        templateUrl: 'post/post.tpl.html'
                    }
                },
                data: {pageTitle: 'View post'}
            })
            .state('create_post', {
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
        service.updatePost = function (data, success, failure) {
            performQuery().update({postId: tempData.id}, data, success, failure);
            postManager.off();
        };
        service.deletePost = function (success, failure) {
            performQuery().remove({postId: tempData.id}, success, failure);
        };
        service.editPost = function (editor, title) {
            postManager.on();
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
            postManager.off();
            return tempData.title;
        };
        return service;
    }])

    /*defines an edit state {save, cancel} when click on 'edit' button of post which can be edited by the user*/
    .factory('postManager', function () {
        var editMode = false;
        var editEnable = function () {
            editMode = true;
        };
        var editDisable = function () {
            editMode = false;
        };
        var getMode = function () {
            return editMode;
        };
        return {
            on: editEnable,
            off: editDisable,
            getMode: getMode
        };
    })

    .controller('PostCtrl', function ($scope, $stateParams, $sce, $state, postService, postManager) {
        $scope.post = postService.getPost($stateParams.postId);
        $scope.makeTrust = function (html) {
            return $sce.trustAsHtml(html);
        };
        var editor = $("#post_text");
        postManager.off();
        $scope.isEditing = postManager.getMode;
        $scope.editPost = function () {
            postService.editPost(editor, $scope.post.title);
        };
        $scope.updatePost = function () {
            var data = {
                title: $scope.post.title,
                text: editor.code()
            };
            postService.updatePost(data,
                function () {/*success*/
                    editor.destroy();
                },
                function () {
                    alert("failure updating post");
                });
        };
        $scope.deletePost = function () {
            postService.deletePost(
                function () {/*success*/
                    $state.go("page", {pageId: 1});
                },
                function () {
                    alert("error deleting");
                });
        };
        $scope.cancelPost = function () {
            $scope.post.title = postService.cancelPost();
        };
    })

    .
    controller('NewPostCtrl', function ($scope, $resource, $state, postService) {
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