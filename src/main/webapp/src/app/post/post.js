angular.module('ngBoilerplate.post', [
    'ui.router'
])

    .config(function ($stateProvider) {
        $stateProvider.state('post', {
            url: '/post/:postId',
            views: {
                "main": {
                    controller: 'PostCtrl',
                    templateUrl: 'post/post.tpl.html'
                }
            },
            data: {pageTitle: ':postId'}
        });
    })

    .factory('Post', ['$resource', function ($resource) {
        return $resource('/api/post/:postId', {}, {
            query: {method: 'GET', params: {postId: '1'}, isArray: false}
        });
    }])

    .controller('PostCtrl', function ($scope, $stateParams, $sce, Post) {
        $scope.post = Post.query({postId: $stateParams.postId});
        $scope.makeTrust = function (html) { //TODO: config
            return $sce.trustAsHtml(html);
        };
    });