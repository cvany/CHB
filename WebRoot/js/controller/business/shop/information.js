/**
 * Created by shilim on 2017/12/8.
 */
controllers.controller("information",['$scope','$http','$timeout',function($scope,$http,$timeout) {
	var loc =new locationUtil();	//实例化地图工具
	var shopVo=new ShopVo();
	loc.localCity(function(result){
		var cityName = result.name;
		$timeout(function(){	//回调函数中需要加timeout函数才可以触发angular属性
			$scope.city =cityName;
		})
	});
	//关键字自动提示
	loc.autoCompelete("suggestId");
	//搜索按钮事件
	$scope.search =function(){
		var val =$("#suggestId").val();
		var encodeVal =encodeURIComponent(val);
		loc.geoByAreaName(val,function(point){
			if (point) {
//				window.location.href ="shopBrowse.html?lng="+point.lng+"&lat="+point.lat+"&loc="+val;
	          	alert("经度："+point.lng+"纬度："+point.lat);
	          	shopVo.lng=point.lng;
	          	shopVo.lat=point.lat;
	          	
	          	var url = baseUrl + "updateAddress.do"
	            var data = {shop:shopVo.voToJson()}
	    		console.log(data)
	            $http.post(url,data)
	                .success(function () {
	                	 toastr.success('成功提交', '成功');
	                   
	                })
	                .error(function () {
	                    
	                })
	          	
			}else{
				alert("您选择地址没有解析到结果!");
			}
		});
	}
}]);