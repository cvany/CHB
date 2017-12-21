/**
 * 定位功能JS
 * 崔文元
 * 2017年12月6日
 */
controllers.controller("location",['$scope','$timeout',function($scope,$timeout) {
	//保存当前的URL地址
	var curUrl = window.document.location.href;
	sessionStorage.setItem("curUrl",curUrl);
	var userToken =sessionStorage.getItem("userToken");
	if(userToken!="null"&&userToken=="true"){
		$(".menu").empty();
		$(".menu").append("<span><a class='link' href='user/register.html'>注册</a></span>");
		$(".menu").append("<span><a class='link' href='shopBrowse.html'>商家入驻</a></span>");
	}else{
		$(".menu").empty();
		$(".menu").append("<span><a class='link' href='user/register.html'>注册</a></span>");
		$(".menu").append("<span><a class='link' href='user/userLogin.html'>登录</a></span>");
		$(".menu").append("<span><a class='link' href='shopBrowse.html'>商家入驻</a></span>");
	}
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
		var encodeVal =encodeURIComponent(val);
		loc.geoByAreaName(val,function(point){
			if (point) {
				$.ajax({
					url : rootPath+"/isUserLogin.do",
					dataType:"json",
					success : function(data) {
						window.location.href ="shopBrowse.html?lng="+point.lng+"&lat="+point.lat+"&loc="+val;
//						if(data=="0"){	//未登录情况
//							window.location.href ="shopBrowse.html?lng="+point.lng+"&lat="+point.lat+"&loc="+val;
//						}else{
//							window.location.href ="user/userShopBrowse.html?lng="+point.lng+"&lat="+point.lat+"&loc="+val;
//						}
					}
				});
			}else{
				alert("您选择地址没有解析到结果!");
			}
		});
	}
}]);