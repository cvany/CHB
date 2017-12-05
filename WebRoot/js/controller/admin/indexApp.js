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
    // .config(['$httpProvider', function ($httpProvider) {
    //     $httpProvider.defaults.transformRequest = function (obj) {
    //         var str = [];
    //         for (var p in obj) {
    //             str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
    //         }
    //         return str.join("&");
    //     };
    //     $httpProvider.defaults.headers.post = {
    //         'Content-Type': 'application/x-www-form-urlencoded',
    //     }
    // }]);
