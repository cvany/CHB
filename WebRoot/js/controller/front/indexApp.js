var indexApp = angular.module("indexApp",['ui.router','controllers']);
var baseUrl = "/CHB/";
indexApp.config(['$stateProvider','$urlRouterProvider',function($stateProvider,$urlRouterProvider) {
	$urlRouterProvider.when("","/location");
	$stateProvider
	.state("location",{
		url:"/location",
		templateUrl:"location/location.html",
		controller:"location"
	})
	.state("ruzhu",{
		url:"/ruzhu",
		templateUrl:"ruzhu.html",
		controller:"shopInData"
	})
	.state("insertBusinessman",{
		url:"/insertBusinessman",
		templateUrl:"sms.html",
		controller:"shopInData"
	})
	.state("industrySMS",{
		url:"/industrySMS",
		templateUrl:"sms.html",
		controller:"shopInData"
	})
	.state("checkValidateCode",{
		url:"/checkValidateCode",
		templateUrl:"sms.html",
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
