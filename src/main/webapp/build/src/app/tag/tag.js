angular.module('ngBoilerplate.tag', [
    'ui.router',
    'ui.bootstrap'
])

    .config(function ($stateProvider) {
        $stateProvider
            .state('tag', {
                url: '/tag/:name',
                views: {
                    "main": {
                        templateUrl: 'tag/tag.tpl.html'
                    }
                },
                data: {pageTitle: 'Tag'}
            });
    })

    .factory('tagService', ['$resource', '$state', function ($resource, $state) {
        var service = {};
        var performQuery = function () {
            return $resource('/rest/tag/:name', {}, {
                getPosts: {url: '/rest/tag/:name/posts', method: 'GET'},
                get: {method: 'GET', isArray: false},
                update: {method: 'PUT'}
            });
        };
        service.getTag = function (tag) {
            return performQuery().get({name: tag});
        };
        service.updateTag = function (data) {
            performQuery().update({}, data, function () {
                    alert("success updated");
                },
                function () {
                    alert("failure");
                });
        };
        service.getTagPosts = function (name, number) {
            return performQuery().getPosts({name: name});
        };
        return service;
    }])

    .controller('TagCtrl', function ($scope, $stateParams, $sce, tagService) {
        $scope.tag = tagService.getTag($stateParams.name);
        $scope.posts = tagService.getTagPosts($stateParams.name, 1);

        $scope.makeTrust = function (html) {
            return $sce.trustAsHtml(html);
        };

        $scope.updateTag = function () {
            var tagData = $scope.tag;
            data = {
                description: tagData.description,
                imageUrl: tagData.imageUrl
            };
            tagService.updateTag(data);
        };
    });