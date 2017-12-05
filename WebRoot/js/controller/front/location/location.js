controllers.controller("location",['$scope','$timeout',function($scope,$timeout) {
	//定位功能呢代码
	$scope.myFun =function(result){
		var cityName = result.name;
		$timeout(function(){	//回调函数中需要加timeout函数才可以触发angular属性
			$scope.city =cityName;
		})
	}
	var myCity = new BMap.LocalCity();
	myCity.get($scope.myFun);
	
	//搜索功能代码
	var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
		{"input" : "suggestId"
	});
	$scope.search =function(){
		var val =$("#suggestId").val();
		// 创建地址解析器实例
		var myGeo = new BMap.Geocoder();
		// 将地址解析结果显示在地图上,并调整地图视野
		myGeo.getPoint(val, function(point){
			if (point) {
	          	alert("经度："+point.lng+"纬度："+point.lat);
			}else{
				alert("您选择地址没有解析到结果!");
			}
		}, "湛江市");
	}

}]);