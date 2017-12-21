var indexApp = angular.module("indexApp", ['ui.router', 'controllers']);
 var baseUrl = "/CHB/";
//var baseUrl = "http://localhost:8080/CHB/";
indexApp.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.when("", "/welcome");
    $stateProvider
        .state("welcome", {
            url: "/welcome",
            templateUrl: "welcome/welcome.html"
        })
        .state("orderList", {
            url: "/orderList",
            templateUrl: "order/order-list.html",
            controller: "orderList"
        })
        .state("orderDetail", {
            url: "/orderDetail/:type/:id",
            templateUrl: "order/order-detail.html",
            controller: "orderDetail"
        })
        .state("classifyGoodsList", {
            url: "/classifyGoodsList",
            templateUrl: "classify-goods/classify-goods-list.html",
            controller: "classifyGoodsList"
        })
        .state("classifyGoodsAddition", {
            url: "/classifyGoodsAddition",
            templateUrl: "classify-goods/classify-goods-addition.html",
            controller: "classifyGoodsAddition"
        })
        .state("classifyGoodsEdition", {
            url: "/classifyGoodsEdition/:id",
            templateUrl: "classify-goods/classify-goods-edition.html",
            controller: "classifyGoodsEdition"
        })
        .state("goodsList", {
            url: "/goodsList",
            templateUrl: "goods/goods-list.html",
            controller: "goodsList"
        })

        .state("goodsAddition", {
            url: "/goodsAddition",
            templateUrl: "goods/goods-addition.html",
            controller: "goodsAddition"
        })
        .state("goodsEdition", {
            url: "/goodsEdition/:id",
            templateUrl: "goods/goods-edition.html",
            controller: "goodsEdition"
        })
         .state("information", {
            url: "/information/:id",
            templateUrl: "shop/information.html",
            controller: "information"
        })
         .state("dispatching", {
            url: "/dispatching/:id",
            templateUrl: "shop/dispatching.html",
            controller: "dispatching"
        })
         .state("notice", {
            url: "/notice/:id",
            templateUrl: "shop/notice.html",
            controller: "notice"
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
