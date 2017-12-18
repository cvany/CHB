var indexApp = angular.module("indexApp", ['ui.router', 'controllers']);
var baseUrl = "/CHB/";
indexApp.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.when("", "/welcome");
    $stateProvider
        .state("welcome", {
            url: "/welcome",
            templateUrl: "welcome/welcome.html",
            controller:"index"
        })
        .state("userList", {
            url: "/userList",
            templateUrl: "user/user-list.html",
            controller: "userList"
        })
         .state("checkBusinessInData", {
            url: "/checkBusinessInData",
            templateUrl: "admin/checkBusinessInData.html",
            controller: "checkBusinessInData"
        })
        .state("updateBusiness", {
            url: "/updateBusiness",
            templateUrl: "admin/updateBusiness.html",
            controller: "updateBusiness"
        
        })
        .state("checkGoods", {
            url: "/checkGoods",
            templateUrl: "admin/checkGoods.html",
            controller: "checkGoods"
        })
        .state("dealComplain", {
            url: "/dealComplain",
            templateUrl: "admin/dealComplain.html",
            controller: "dealComplain"
        })
        .state("userData", {
            url: "/userData",
            templateUrl: "admin/userData.html",
            controller:"userData"
        })
        .state("businessData", {
            url: "/businessData",
            templateUrl: "admin/businessData.html",
            controller:"businessData"
        })
        .state("orderData", {
            url: "/orderData",
            templateUrl: "admin/orderData.html",
            controller:"orderData"
        })
        .state("managerLog", {
            url: "/managerLog",
            templateUrl: "admin/managerLog.html",
            controller:"managerLog"
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
        }
    }]);
