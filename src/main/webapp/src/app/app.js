angular.module('ngBoilerplate', [
    'templates-app',
    'templates-common',
    'ngBoilerplate.home',
    'ngBoilerplate.about',
    'ngBoilerplate.account',
    'ngBoilerplate.page',
    'ngBoilerplate.post',
    'ngBoilerplate.comment',
    'ngBoilerplate.profile',
    'ngBoilerplate.tag',
    'ui.router',
    'ui.bootstrap',
    'hateoas',
    'summernote'
])

    .config(function myAppConfig($stateProvider, $urlRouterProvider, HateoasInterceptorProvider) {
        $urlRouterProvider.otherwise('/home');
        HateoasInterceptorProvider.transformAllResponses();
    })

    .run(function run() {
    })

    .controller('AppCtrl', function AppCtrl($rootScope, $scope, $location, sessionService) {
        sessionService.isAuthenticated();
        $rootScope.isLoggedIn = sessionService.isLoggedIn;
        $rootScope.logout = sessionService.logout;

        var sessionInfo = localStorage.getItem("session");
        if ($.type(sessionInfo) === "string") {
            var retVal = JSON.parse(sessionInfo);
            $rootScope.loggedUser = retVal.loggedUser;
        }
        $scope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {
            if (angular.isDefined(toState.data.pageTitle)) {
                $scope.pageTitle = toState.data.pageTitle + ' | Boop';
            }
        });
    });

