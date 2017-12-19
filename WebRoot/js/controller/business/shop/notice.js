/**
 * Created by shilim on 2017/12/8.
 */
controllers.controller("orderDetail", ['$scope', '$http', '$state', '$stateParams', function ($scope, $http, $state, $stateParams) {
    $scope.orderGoodsList = null;
    $scope.sumMoney = 0;

    // 获取订单商品列表
    $scope.getOrderGoodsList = function () {
        var orderVo = new OrderVo();
        orderVo.id = $stateParams.id;
        var url = baseUrl + "business/getOrderGoodsListByOrderId.do"
        var data = {order:orderVo.voToJson()}
        $http.post(url,data)
            .success(function (data) {
                if (data.serviceResult == 1) {
                    $scope.orderGoodsList = data.resultParam;
                    $scope.orderGoodsList.forEach(function (item) {
                        $scope.sumMoney += item.sumMoney;
                    })
                }
            })
            .error(function () {
                
            })
    }
    $scope.getOrderGoodsList()

    // 返回
    $scope.back = function () {
        $state.go("orderList")
    }
}]);