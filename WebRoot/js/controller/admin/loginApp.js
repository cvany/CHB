var loginApp = angular.module("loginApp",[]);
var baseUrl = "/CHB/";
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
	
	$scope.admin = new AdminVo();
	
	$scope.login = function() {
	
		var url = baseUrl + "adminLogin.do";
		var data = {admin:$scope.admin.voToJson()};
		$http.post(url,data)
		.success(function(data) {
			console.log(data);
			if(data.serviceResult == 1) {
				$scope.admin.userName = data.resultParam.userName;
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