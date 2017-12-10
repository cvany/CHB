/**
 * 定位功能JS
 * 崔文元
 * 2017年12月6日
 */
controllers.controller("location",['$scope','$timeout',function($scope,$timeout) {
	var loc =new locationUtil();	//实例化地图工具
	
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
		loc.geoByAreaName(val,function(point){
			if (point) {
	          	alert("经度："+point.lng+"纬度："+point.lat);
			}else{
				alert("您选择地址没有解析到结果!");
			}
		});
	}
}]);