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
                getComments: {
                    method: 'GET',
                    url: '/rest/profile/:username/comments',
                    params: {page: '1'},
                    isArray: false
                },
                get: {method: 'GET', isArray: false},
                update: {method: 'PUT'}
            });
        };
        service.getPublicProfile = function (username) {
            return performQuery().getPublic({username: username});
        };
        service.getProfile = function () {
            return performQuery().get();
        };
        service.updateProfile = function (data) {
            performQuery().update({}, data, function () {
                    alert("success updated");
                },
                function () {
                    alert("failure");
                });
        };
        service.getUserPosts = function (username) {
            return performQuery().getPosts({username: username});
        };
        service.getUserComments = function (username) {
            return performQuery().getComments({username: username});
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
        $scope.profile = profileService.getPublicProfile($stateParams.username);
        $scope.username = $stateParams.username;
        $scope.posts = profileService.getUserPosts($stateParams.username);
        $scope.comments = profileService.getUserComments($stateParams.username);
        $scope.makeTrust = function (html) {
            return $sce.trustAsHtml(html);
        };
        $scope.tabs = [
            {title: 'Public Profile', template: 'profile/public.tpl.html'},
            {title: 'Favorites', content: 'Dynamic content 1', disabled: true},
            {title: 'Posts', template: 'page/page.tpl.html'},
            {title: 'Comments', template: 'comment/comment.tpl.html'},
            {title: 'Friends', content: 'Dynamic content 2', disabled: true}
        ];
    });
