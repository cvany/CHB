/**
 * Created by cqs on 2017/12/19.
 */
controllers.controller("notice", ['$scope', '$http', '$state', '$stateParams', function ($scope, $http, $state, $stateParams) {
	
//	var loc =sessionStorage.getItem("loc");
//	alert(loc);
//	var id = loc.getParam("id");
//	id=getShopInfo(id).getParam("shopId");
//	$scope.shop.id=id;
	
//	var shopVo = new ShopVo();
	$scope.saveNotice = function () {	
//		shopVo.shopNotice=$scope.shop.shopNotice;
		var url = baseUrl + "updateShopById.do"
        var data = {shop:$scope.shop.voToJson()}
		console.log(data)
        $http.post(url,data)
            .success(function () {
            	 toastr.success('成功提交', '成功');
               
            })
            .error(function () {
                
            })
	}
	
	
    // 获取公告信息
	$scope.shop=new ShopVo();
    $scope.getNotice = function () {
        
        var url = baseUrl + "findNotice.do"
        data={};
        $http.post(url,data)
            .success(function (data) {
                console.log(data);
                console.log(data.resultParam.shopNotice);
                $scope.shop.shopNotice=data.resultParam.shopNotice;
            })
            .error(function () {
                
            })
    }
    $scope.getNotice()

    // 返回
    $scope.back = function () {
        $state.go("notice")
    }
}]);