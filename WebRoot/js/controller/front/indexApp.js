var indexApp = angular.module("indexApp",['ui.router','controllers']);
var baseUrl = "/CHB/";
indexApp.config(['$stateProvider','$urlRouterProvider',function($stateProvider,$urlRouterProvider) {
	$urlRouterProvider 
	.when("/ruzhu","/businessman")
	.when("/sms","/businessman")
	.when("/shenhe","/businessman");
	
	$stateProvider
	.state("location",{
		url:"/location",
		templateUrl:"location/location.html",
		controller:"location"
	})
	.state("ruzhu",{
		url:"/ruzhu/:businessmanId",
		templateUrl:"ruzhu.html",
		controller:"shopInData"
	})
	.state("sms",{
		url:"/sms",
		templateUrl:"sms.html",
		controller:"shopInData"
	})
	.state("shenhe",{
		url:"/shenhe",
		templateUrl:"shenhe.html",
		controller:"shopInData"
	})
	
}])
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
