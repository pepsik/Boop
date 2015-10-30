angular.module('ngBoilerplate.profile', [
    'ui.router'
])

    .config(function ($stateProvider) {
        $stateProvider
            .state('public_profile', {
                url: '/profile/:username',
                views: {
                    "main": {
                        templateUrl: 'profile/public_profile.tpl.html'
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
            performQuery().update(data);
        };
        return service;
    }])

    .controller('ProfileCtrl', function ($scope, $stateParams, $state, profileService) {
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
    });
