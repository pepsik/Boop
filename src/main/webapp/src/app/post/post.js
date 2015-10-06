angular.module('ngBoilerplate.post', [
    'ui.router',
    'ngBoilerplate.comment'
])

    .config(function ($stateProvider) {
        $stateProvider.state('view_post', {
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

    .factory('postService', ['$resource', function ($resource) {
        return $resource('/rest/posts/:postId', {}, {
            create: {method: 'POST'},
            query: {method: 'GET', isArray: false},
            update: {method: 'PUT'},
            remove: {method: 'DELETE'}
        });
    }])

    /*defines an edit state {save, cancel} when click on 'edit' button of post which can be edited by the user*/
    .factory('PostEditMode', function () {
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

    .controller('PostCtrl', function ($scope, $stateParams, $sce, $state, postService, PostEditMode) {
        $scope.post = postService.query({postId: $stateParams.postId});
        $scope.makeTrust = function (html) {
            return $sce.trustAsHtml(html);
        };
        var tempData = {};
        var editor = $("#post_text");
        PostEditMode.off();
        $scope.isEditing = PostEditMode.getMode;
        $scope.editPost = function () {
            PostEditMode.on();
            editor.summernote({
                height: 350,
                minHeight: 150,
                maxHeight: null,
                focus: true
            });
            tempData.title = $scope.post.title;
            tempData.text = editor.code();
        };
        $scope.savePost = function () {
            var data = {
                "title": $scope.post.title,
                "text": editor.code()
            };
            postService.update({postId: $stateParams.postId}, data,
                function () {/*success*/
                },
                function () {
                    alert("failure updating post");
                });
            editor.destroy();
            PostEditMode.off();
        };
        $scope.deletePost = function () {
            postService.remove({postId: $stateParams.postId},
                function () {
                    $state.go("page", {pageId: 1});
                },
                function () {
                    alert("error deleting");
                });
        };
        $scope.cancelEdit = function () {
            editor.code(tempData.text);
            $scope.post.title = tempData.title;
            editor.destroy();
            PostEditMode.off();
        };
    })

    .controller('NewPostCtrl', function ($scope, $resource, $state, postService) {
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
            postService.create({}, data,
                function (returnedData) {
                    $state.go("view_post", {postId: returnedData.rid});
                },
                function () {
                    alert("error saving");
                });
        };
    });