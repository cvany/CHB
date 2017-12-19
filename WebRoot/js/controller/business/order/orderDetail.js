/**
 * Created by shilim on 2017/12/8.
 */
controllers.controller("orderDetail", ['$scope', '$http', '$state', '$stateParams', function ($scope, $http, $state, $stateParams) {
    $scope.orderGoodsList = null;
    $scope.sumMoney = 0;
    $scope.order = sessionStorage.tempOrder?JSON.parse(sessionStorage.tempOrder):{};
    $scope.btnText = ''

    // 判断按钮显示
    console.log($stateParams.type)
    switch ($stateParams.type) {
        case '1':
            $scope.btnText = '接单'
            if($scope.order.status == 0 && $scope.order.payMode == 0) {
                $scope.btnText = ''
            }
            break;
        case '2':
            $scope.btnText = '派送'
            break;
    }

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
                toastr.error('系统繁忙', '失败');
            })
    }
    $scope.getOrderGoodsList()

    $scope.submit = function () {
        var orderVo = new OrderVo();
        orderVo.id = $stateParams.id;
        if($stateParams.type === '1') {
            var url = baseUrl + "business/takeOrder.do"
        } else if ($stateParams.type === '2') {
            var url = baseUrl + "business/sendGoods.do"
        }
        var data = {order:orderVo.voToJson()}
        $http.post(url,data)
            .success(function (data) {
                if (data.serviceResult == 1) {
                    toastr.success(data.resultInfo, '成功');
                    $scope.back();
                } else {
                    toastr.error(data.resultInfo, '失败');
                }
            })
            .error(function () {
                toastr.error('系统繁忙', '失败');
            })
    }

    // 返回
    $scope.back = function () {
        $state.go("orderList")
    }
}]);