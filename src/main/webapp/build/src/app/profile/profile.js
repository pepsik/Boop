angular.module('ngBoilerplate.profile', [
    'ui.router'
])

    .config(function ($stateProvider) {
        $stateProvider
            .state('public_profile', {
                url: '/profile/:username',
                views: {
                    "main": {
                        templateUrl: 'profile/tabs.tpl.html'
                    }
                },
                data: {pageTitle: 'Public profile'}
            })
            .state('profile', {
                url: '/profile',
                views: {
                    "main": {
                        templateUrl: 'profile/profile.tpl.html'
                    }
                },
                data: {pageTitle: 'Profile'}
            });
    })

    .factory('profileService', ['$resource', '$state', function ($resource, $state) {
        var service = {};
        var performQuery = function () {
            return $resource('/rest/profile', {}, {
                getPublic: {url: '/rest/profile/:username', method: 'GET'},
                getPosts: {method: 'GET', url: '/rest/profile/:username/posts', params: {page: '1'}, isArray: false},
                getPostCount: {method: 'GET', url: '/rest/profile/:username/posts', isArray: false},
                getComments: {
                    method: 'GET',
                    url: '/rest/profile/:username/comments',
                    params: {page: '1'},
                    isArray: false
                },
                getCommentCount: {method: 'GET', url: '/rest/profile/:username/comments', isArray: false},
                getProfile: {method: 'GET', isArray: false},
                updateProfile: {method: 'PUT'}
            });
        };
        service.getPublicProfile = function (username) {
            return performQuery().getPublic({username: username});
        };
        service.getProfile = function () {
            return performQuery().getProfile();
        };
        service.updateProfile = function (data) {
            performQuery().updateProfile({}, data, function () {
                    alert("success updated");
                },
                function () {
                    alert("failure");
                });
        };
        service.getUserPosts = function (username, page) {
            return performQuery().getPosts({username: username, page: page});
        };
        service.getUserPostCount = function (username) {
            return performQuery().getPostCount({username: username});
        };
        service.getUserComments = function (username, page) {
            return performQuery().getComments({username: username, page: page});
        };
        service.getUserCommentsCount = function (username) {
            return performQuery().getCommentCount({username: username});
        };
        return service;
    }])

    .controller('ProfileCtrl', function ($scope, profileService) {
        $scope.profile = profileService.getProfile();
        $scope.updateProfile = function () {
            var profileData = $scope.profile;
            data = {
                firstname: profileData.firstname,
                lastname: profileData.lastname,
                birthday: profileData.birthday,
                gender: profileData.gender,
                country: profileData.country,
                city: profileData.city,
                job: profileData.job,
                about: profileData.about
            };
            profileService.updateProfile(data);
        };
    })

    .controller('PublicProfileCtrl', function ($scope, $stateParams, $state, $sce, $resource, profileService) {
        $scope.makeTrust = function (html) {
            return $sce.trustAsHtml(html);
        };

        $scope.profile = profileService.getPublicProfile($stateParams.username);
        $scope.username = $stateParams.username;
        $scope.posts = profileService.getUserPosts($stateParams.username, 1);
        $scope.comments = profileService.getUserComments($stateParams.username, 1);

        $scope.tabs = [
            {title: 'Public Profile', template: 'profile/public.tpl.html'},
            {title: 'Favorites', content: 'Dynamic content 1', disabled: true},
            {title: 'Posts', template: 'page/page.tpl.html'},
            {title: 'Comments', template: 'comment/comment.tpl.html'},
            {title: 'Friends', content: 'Dynamic content 2', disabled: true}
        ];

        $scope.maxSize = 5;
        $scope.totalPosts = profileService.getUserPostCount($stateParams.username);
        $scope.currentPostPage = 1;
        $scope.postsPerPage = 7;
        $scope.setPostPage = function (page) {
            $scope.currentPostPage = page;
            $scope.posts = profileService.getUserPosts($stateParams.username, page);
        };

        $scope.totalComments = profileService.getUserCommentsCount($stateParams.username);
        //$scope.currentCommentPage = 2;
        $scope.commentsPerPage = 10;
        $scope.setCommentPage = function (page) {
            $scope.currentCommentPage = page;
            $scope.comments = profileService.getUserComments($stateParams.username, page);
        };
    });
