/**
 * Created by cqs on 2017/12/8.
 */
controllers.controller("dispatching", ['$scope', '$http', '$state', '$stateParams', function ($scope, $http, $state, $stateParams) {
    $scope.dispatching = null;
    $scope.sumMoney = 0;

    // 获取配送信息
    $scope.getDispatching = function () {
    	$scope.businessman = new businessmanVo();
        businessmanVo.id = $stateParams.id;
        var url = baseUrl + "getDispatchingByBusinessmanId.do"
        var data = {businessman:businessmanVo.voToJson()}
        $http.post(url,data)
            .success(function (businessman) {
                $scope.businessman=businessman;
                
            })
            .error(function () {
                
            })
    }
    $scope.getDispatching()

    // 返回
    $scope.back = function () {
        $state.go("dispatching")
    }
}]);