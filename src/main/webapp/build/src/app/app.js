angular.module('ngBoilerplate', [
  'templates-app',
  'templates-common',
  'ngBoilerplate.home',
  'ngBoilerplate.about',
  'ngBoilerplate.account',
  'ngBoilerplate.page',
  'ngBoilerplate.post',
  'ui.router',
  'hateoas',
  'summernote'
]).config([
  '$stateProvider',
  '$urlRouterProvider',
  'HateoasInterceptorProvider',
  function myAppConfig($stateProvider, $urlRouterProvider, HateoasInterceptorProvider) {
    $urlRouterProvider.otherwise('/home');
    HateoasInterceptorProvider.transformAllResponses();
  }
]).run(function run() {
}).controller('AppCtrl', [
  '$scope',
  '$location',
  'sessionService',
  function AppCtrl($scope, $location, sessionService) {
    $scope.isLoggedIn = sessionService.isLoggedIn;
    $scope.logout = sessionService.logout;
    $scope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {
      if (angular.isDefined(toState.data.pageTitle)) {
        $scope.pageTitle = toState.data.pageTitle + ' | Boop';
      }
    });
  }
]);