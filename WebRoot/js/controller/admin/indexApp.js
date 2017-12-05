var indexApp = angular.module("indexApp", ['ui.router', 'controllers']);
var baseUrl = "/CHB/";
indexApp.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.when("", "/welcome");
    $stateProvider
        .state("welcome", {
            url: "/welcome",
            templateUrl: "welcome/welcome.html"
        })
        .state("userList", {
            url: "/userList",
            templateUrl: "user/user-list.html",
            controller: "userList"
        })
}])
    .config(['$httpProvider', function ($httpProvider) {
        $httpProvider.defaults.transformRequest = function (obj) {
            var str = [];
            for (var p in obj) {
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            }
            return str.join("&");
        };
        $httpProvider.defaults.headers.post = {
            'Content-Type': 'application/x-www-form-urlencoded',
        };
        $httpProvider.interceptors.push(["$window", function ($window) {
            return {
                response: function (response) {
                    if (response.config.url.indexOf(".do") > 0 && response.config.url.indexOf("login") < 0) {
                        if (response.data.userToken == false) {
                            $window.location.href = "login.html";
                        }
                    }
                    return response
                },

            }
        }]);
    }]);
