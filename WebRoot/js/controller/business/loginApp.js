var loginApp = angular.module("loginApp",[]);
 var baseUrl = "/CHB/";
//var baseUrl = "http://localhost:8080/CHB/";
loginApp
.config(['$httpProvider',function($httpProvider) {
	$httpProvider.defaults.transformRequest=function(obj){
		var str=[];
		for(var p in obj){
			str.push(encodeURIComponent(p)+"="+encodeURIComponent(obj[p]));
		}
		return str.join("&");
	};
	$httpProvider.defaults.headers.post = {
			'Content-Type': 'application/x-www-form-urlencoded',
	}
}]);

loginApp.controller("login", ['$scope','$http','$window',function($scope,$http,$window) {
	$scope.businessman = new BusinessmanVo();
	
	$scope.login = function() {
		var url = baseUrl + "business/login.do";
		var data = {businessman:$scope.businessman.voToJson()};
		$http.post(url,data)
		.success(function(data) {
			console.log(data);
			if(data.serviceResult == 1) {
				$window.location.href = "index.html";
			} else {
				toastr.error('失败', data.resultInfo);
			}
		})
		.error(function(data) {
			toastr.error('登陆', "网络繁忙");
		});
	}
	
}]);