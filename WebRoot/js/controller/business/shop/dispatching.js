/**
 * Created by cqs on 2017/12/8.
 */
controllers.controller("dispatching", ['$scope', '$http', '$state', '$stateParams', function ($scope, $http, $state, $stateParams) {

    // 获取配送信息
    $scope.getDispatching = function () {
    	$scope.shop = new ShopVo();
        var url = baseUrl + "getDispatchByBusinessmanId.do"
        var data = {shop:$scope.shop.voToJson()}
        $http.post(url,data)
            .success(function (data) {
                console.log(data);
                $scope.shop.takeoutAreaId=data.resultParam.takeoutAreaId;
                $scope.shop.startPrice=data.resultParam.startPrice;
                $scope.shop.carriage=data.resultParam.carriage;
                $scope.shop.dispatchDescription=data.resultParam.dispatchDescription;
               
                
            })
            .error(function () {
                
            })
    }
    $scope.getDispatching()

    $scope.saveDispatching=function(){
    	var url = baseUrl + "updateDispatching.do"
        var data = {shop:$scope.shop.voToJson()}
		console.log(data)
        $http.post(url,data)
            .success(function () {
            	 toastr.success('成功提交', '成功');
               
            })
            .error(function () {
                
            })
    }
    
    // 返回
    $scope.back = function () {
        $state.go("dispatching")
    }
}]);