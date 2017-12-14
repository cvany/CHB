/**
 * Created by cqs on 2017/12/7.
 */
var controllers = angular.module("controllers", []);
controllers.controller("shopInData", ['$scope', '$http', '$state', function ($scope, $http, $state) {
   $scope.shopInData = new ShopInDataVo();
   $scope.shop = new ShopVo();
   $scope.businessman = new BusinessmanVo();
   var businessmanId="";
    //发送验证码
   $scope.industrySMS=function(){
	   var industrySMSUrl=baseUrl + "industrySMS.do";
	   var data={businessman:$scope.businessman.voToJson()};
	   $http.post(industrySMSUrl,data)
	   .then(function() {
           toastr.success('发送成功', '成功');
       });
   }
    //存入入驻信息
    $scope.insertShopInData = function() {
    	console.log("调用insertShopInData");
        var shopUrl = baseUrl + "insertShop.do";
        var shopInDataUrl = baseUrl + "insertShopInData.do";
        var shopData = {shop:$scope.shop.voToJson()}; 
        var shopInData = {shopInData:$scope.shopInData.voToJson()}; 
        $http.post(shopUrl,shopData);
        $http.post(shopInDataUrl,shopInData)
            .then(function() {
                toastr.success('成功提交', '成功');
                window.location.href="shenhe.html"; 
            });
    }  
    //存入成功短信验证商家
    $scope.insertBusinessman=function(){
    	var validateCodeData={validateCode:$scope.validateCode};
    	var ValidateCodeUrl=baseUrl+"checkValidateCode.do"
    	$http.post(ValidateCodeUrl,validateCodeData)
    	.success(function(status){
    		console.log(status);
    		if(status){
    			console.log("insertBusinessman!");
    			
    	var businessmanUrl = baseUrl + "insertBusinessman.do";
    	var businessmanData = {businessman:$scope.businessman.voToJson()};
    	console.log(businessmanData);
    	$http.post(businessmanUrl,businessmanData)
    	.success(function(id){
    		businessmanId=id;
    		console.log(businessmanId);
    	toastr.success('success','insert');
    	 window.location.href="ruzhu.html";
    	});
    		}else{
    			toastr.error('验证码错误','error');
    		}
    	
    	});
    }

}]);