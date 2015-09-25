/**
 * Each section of the site has its own module. It probably also has
 * submodules, though this boilerplate is too simple to demonstrate it. Within
 * `src/app/home`, however, could exist several additional folders representing
 * additional modules that would then be listed as dependencies of this one.
 * For example, a `note` section could have the submodules `note.create`,
 * `note.delete`, `note.edit`, etc.
 *
 * Regardless, so long as dependencies are managed correctly, the build process
 * will automatically take take of the rest.
 *
 * The dependencies block here is also where component dependencies should be
 * specified, as shown below.
 */
angular.module('ngBoilerplate.home', [
    'ui.router',
    'plusOne',
    'ngBoilerplate.account'
])

/**
 * Each section or module of the site can also have its own routes. AngularJS
 * will handle ensuring they are all available at run-time, but splitting it
 * this way makes each module more "self-contained".
 */
    .config(function config($stateProvider) {
        $stateProvider.state('home', {
            url: '/home',
            views: {
                "main": {
                    controller: 'HomeCtrl',
                    templateUrl: 'home/home.tpl.html'
                }
            },
            data: {pageTitle: 'Home'}
        });
    })

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

/**
 * And of course we define a controller for our route.
 */
    .controller('HomeCtrl', function HomeController($scope, sessionService) {
        $scope.isLoggedIn = sessionService.isLoggedIn;
        $scope.logout = sessionService.logout;
    })

    .controller('PostListController', function ($scope, $sce, PostList, Pagination) {
        $scope.posts = PostList.query();
        $scope.makeTrust = function (html) { //TODO: config
            return $sce.trustAsHtml(html);
        };
        $scope.pagination = Pagination.query();
    })

    .controller('PostController', function ($scope, $routeParams, $sce, Post) {
        $scope.post = Post.query({postId: $routeParams.postId});
        $scope.makeTrust = function (html) { //TODO: config
            return $sce.trustAsHtml(html);
        };
    });


