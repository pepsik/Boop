angular.module('postServices', ['ngResource'])

    .factory('PostList', ['$resource', function ($resource) {
        return $resource('/api/page/:pageId', {}, {
            query: {method: 'GET', params: {pageId: '1'}, isArray: true}
        });
    }])

    .factory('Pagination', ['$resource', function ($resource) {
        return $resource('/api/pagination', {}, {
            query: {method: 'GET', params: {activePage: '1'}, isArray: true}
        });
    }])

    .factory('Post', ['$resource', function ($resource) {
        return $resource('/api/post/:postId', {}, {
            query: {method: 'GET', params: {postId: '1'}, isArray: false}
        });
    }])

    .factory('sessionService', function () {
        var session = {};
        session.login = function (data) {
            alert("user login with " + data.name + " and " + data.password);
            localStorage.setItem("session", data);
        };
        session.logout = function () {
            localStorage.removeItem("session");
        };
        session.isLoggedIn = function () {
            return localStorage.getItem("session") !== null;
        };
        return session;
    });
