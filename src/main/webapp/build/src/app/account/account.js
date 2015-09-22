angular.module('ngBoilerplate.account', ['ui.router'])
    .config(function ($stateProvider) {
        $stateProvider.state('login', {
            url: '/login',
            views: {
                'main': {
                    templateUrl: 'account/login.tpl.html',
                    controller: 'LoginCtrl'
                }
            },
            data: {pageTitle: "Login"}
        })
            .state('register', {
                url: '/register',
                views: {
                    'main': {
                        templateUrl: 'account/register.tpl.html',
                        controller: 'RegisterCtrl'
                    }
                },
                data: {pageTitle: "Registration"}
            }
        )
            .state('accountSearch', {
                url: '/accounts/search',
                views: {
                    'main': {
                        templateUrl: 'account/search.tpl.html',
                        controller: 'AccountSearchCtrl'
                    }
                },
                data: {pageTitle: "Search Accounts"},
                resolve: {
                    accounts: function (accountService) {
                        return accountService.getAllAccounts();
                    }
                }
            });
    })
    .factory('sessionService', function () {
        var session = {};
        session.login = function (data) {
            localStorage.setItem("session", data);
            alert("user logIn with name " + data.name + " and password " + data.password);
        };
        session.logout = function () {
            localStorage.removeItem("session");
        };
        session.isLoggedIn = function () {
            return localStorage.getItem("session") !== null;
        };
        return session;
    })

    .factory('accountService', function ($resource) {
        var service = {};
        service.register = function (account, success, failure) {
            var Account = $resource("/rest/accounts");
            Account.save({}, account, success, failure);
        };
        service.UserExists = function (account, success, failure) {
            var Account = $resource("/rest/accounts");
            var data = Account.get({name: account.name, password: account.password}, function () {
                var accounts = data.accounts;
                if (accounts.length !== 0) {
                    success(account);
                } else {
                    failure();
                }
            });
        };
        return service;
    })

    .controller('LoginCtrl', function ($scope, $state, accountService, sessionService) {
        $scope.login = function () {
            accountService.UserExists($scope.account, function () {
                    alert("logging success!");
                },
                function () {
                    alert("logging failed!");
                });
        };
    })

    .controller('RegisterCtrl', function ($scope, $state, sessionService, accountService) {
        $scope.register = function () {
            accountService.register($scope.account,
                function (returnedData) {
                    sessionService.login(returnedData);
                    $state.go("home");
                },
                function () {
                    alert("Error registering user");
                });
        };
    });