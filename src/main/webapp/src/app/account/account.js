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
    .factory('sessionService', function ($http, $state) {
        var session = {};
        session.login = function (data) {
            return $http.post("/login", "username=" + data.username +
                "&password=" + data.password, {
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            }).then(function () {
                localStorage.setItem("session", JSON.stringify({
                    'loggedUser': data.username,
                    'avatarUrl': 'url//'
                }));
                $state.go("home");
            }, function (data) {
                alert("login and password doesn't match");
            });
        };
        session.logout = function () {
            localStorage.removeItem("session");
        };
        session.isLoggedIn = function () {
            return localStorage.getItem("session") !== null;
        };
        session.isAuthenticated = function () {
            $http.get("/rest/accounts/1").then(
                function () {/*success*/
                },
                function () {
                    localStorage.removeItem("session");
                });
        };
        return session;
    })

    .factory('accountService', function ($resource) {
        var service = {};
        service.register = function (account, success, failure) {
            var Account = $resource("/rest/accounts");
            Account.save({}, account, success, failure);
        };
        service.getAccountById = function (accountId) {
            var Account = $resource("/rest/accounts/:paramAccountId");
            return Account.get({paramAccountId: accountId}).$promise;
        };
        service.UserExists = function (account, success, failure) {
            var Account = $resource("/rest/accounts");
            var data = Account.get({username: account.username}, function () {
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

    .controller('LoginCtrl', function ($rootScope, $scope, $state, accountService, sessionService) {
        $scope.login = function () {
            accountService.UserExists($scope.account,
                function (account) {
                    sessionService.login($scope.account).then(
                        function () {
                            $rootScope.loggedUser = $scope.account.username;
                        }
                    );
                },
                function () {
                    alert("User doesn't exist!");
                });
        };
    })

    .controller('RegisterCtrl', function ($rootScope, $scope, $state, sessionService, accountService) {
        $scope.register = function () {
            accountService.register($scope.account,
                function (returnedData) {
                    sessionService.login($scope.account).then(function () {
                        $rootScope.loggedUser = $scope.account.username;
                        $state.go("home");
                    });
                },
                function () {
                    alert("Error registering user");
                });
        };
    });