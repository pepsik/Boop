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

    .controller('PostCtrl', function ($scope, $stateParams, $sce, $resource, $state, Post) {
        $scope.post = Post.query({postId: $stateParams.postId});
        $scope.makeTrust = function (html) { //TODO: config
            return $sce.trustAsHtml(html);
        };

        $scope.deletePost = function () {
            var post = $resource("/rest/posts/" + $scope.post.rid);
            post.remove({}, {},
                function () {
                    $state.go("page", {pageId: 1});
                },
                function () {
                    alert("failed");
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

        $scope.click = function () {
            var test = {
                "title": "testTitle",
                "text": $scope.text
            };

            var post = $resource("/rest/posts");
            post.save({}, test,
                function (returnedData) {
                    $state.go("post", {postId: returnedData.rid});
                },
                function () {
                    alert("failed");
                });
        };
    });