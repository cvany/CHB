/**
 * Created by shilim on 2017/12/14.
 */
var orderCheck = angular.module("orderCheck", []);
var baseUrl = "/CHB/";
// var baseUrl = "http://localhost:8080/CHB/";
orderCheck
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
        $httpProvider.interceptors.push(["$window", function ($window) {
            return {
                response: function (response) {
                    if (response.config.url.indexOf(".do") > 0 && response.config.url.indexOf("login") < 0) {
                        if (response.data.userToken == false) {
                            $window.location.href = "/CHB/pages/front/user/userLogin.html";
                        }
                    }
                    return response
                },

            }
        }]);
    }]);

orderCheck.controller("orderCheck", ['$scope', '$http', '$window', function ($scope, $http, $window) {
    if (sessionStorage.order) {
        $scope.order = JSON.parse(sessionStorage.order)
    } else {
        $scope.order = {
            shopInfo: {},
            shopCartList: {}
        }
    }

    // 下单成功标志
    $scope.orderSuccess = false;

    $scope.orderGoodsList = []
    $scope.orderVo = new OrderVo();
    $scope.orderVo.sumMoney = 0;
    $scope.orderVo.payMode = 0;
    Object.keys($scope.order.shopCartList).forEach(function (key, index) {
        $scope.orderGoodsList.push($scope.order.shopCartList[key])
        $scope.orderVo.sumMoney += $scope.order.shopCartList[key].sumMoney * 1
    })

    $scope.changePayMode = function (payMode) {
        $scope.orderVo.payMode = payMode;
    }

    $scope.submit = function () {
        $scope.orderVo.shopId = $scope.order.shopInfo.id;
        var url = baseUrl + "user/newOrder.do";
        var data = {
            order: $scope.orderVo.voToJson(),
            orderGoodsList: JSON.stringify($scope.orderGoodsList)
        };
        $http.post(url, data)
            .success(function (data) {
                if (data.serviceResult == 1) {
                    $scope.orderSuccess = true;
                    $scope.getPayUrl(data.resultParam)
                } else {
                    alert('下单失败')
                }
            })
            .error(function (data) {
                alert('下单失败')
            });
    }

    $scope.getPayUrl = function (order) {
        var url = baseUrl + "user/getPayUrl.do";
        var data = {
            order: JSON.stringify(order)
        };
        $http.post(url, data)
            .success(function (data) {
                if (data.serviceResult == 1) {
                    $scope.payUrl = data.resultParam;
                } else {
                }
            })
            .error(function (data) {
            });
    }

    $scope.toOrderPage = function () {
        $window.location.href = "/CHB/pages/front/order/user-order.html";
    }

    $scope.toPayPage = function () {
        $window.location.href = $scope.payUrl;
    }

}]);