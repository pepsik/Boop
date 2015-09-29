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
])

    .config(function myAppConfig($stateProvider, $urlRouterProvider, HateoasInterceptorProvider) {
        $urlRouterProvider.otherwise('/home');
        HateoasInterceptorProvider.transformAllResponses();
    })

    .run(function run() {
    })

    .controller('AppCtrl', function AppCtrl($scope, $location, sessionService) {
        $scope.isLoggedIn = sessionService.isLoggedIn;
        $scope.logout = sessionService.logout;

        var sessionInfo = localStorage.getItem("session");
        if ($.type(sessionInfo) === "string") {
            var retVal = JSON.parse(sessionInfo);
            $scope.loggedUser = retVal.loggedUser;
        }
        $scope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {
            if (angular.isDefined(toState.data.pageTitle)) {
                $scope.pageTitle = toState.data.pageTitle + ' | Boop';
            }
        });
    });

