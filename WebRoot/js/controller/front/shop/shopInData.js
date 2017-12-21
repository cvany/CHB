/**
 * Created by cqs on 2017/12/7.
 */
var controllers = angular.module("controllers", ['ngFileUpload']);
controllers.controller("shopInData", ['$scope', '$http', '$state','$stateParams','$timeout','Upload',
 function ($scope, $http, $state,$stateParams,$timeout,Upload) {
   $scope.shopInData = new ShopInDataVo();
   $scope.shop = new ShopVo();
   $scope.businessman = new BusinessmanVo();
   var map=new Map();
  
    // 发送验证码
   $scope.industrySMS=function(){
	   var industrySMSUrl=baseUrl + "industrySMS.do";
	   var data={businessman:$scope.businessman.voToJson()};
	   $http.post(industrySMSUrl,data)
	   .then(function() {
           toastr.success('发送成功', '成功');
       });
   }
    // 存入入驻信息
    $scope.insertShopInData = function() {
// $scope.shop.businessmanId=$stateParams.businessmanId;
    	var businessmanId=location.search;
    	var paraString = businessmanId.substring(businessmanId.indexOf("?")+1,businessmanId.length).split("&");  
        for(var i=0;i<paraString.length;i++){  
            businessmanId=paraString[i].split("=")[1];  
        }  
        $scope.shop.businessmanId=parseInt(businessmanId); 
    	
    	console.log($scope.shop.businessmanId);
        var shopUrl = baseUrl + "insertShop.do";
        var shopInDataUrl = baseUrl + "insertShopInData.do";
        var shopData = {shop:$scope.shop.voToJson()}; 
        $http.post(shopUrl,shopData)
        .success(function(id){
        	var shopId=id;
        	$scope.shopInData.shopId=shopId;
           var shopInData = {shopInData:$scope.shopInData.voToJson()}; 
        $http.post(shopInDataUrl,shopInData)
            .then(function() {
                toastr.success('成功提交', '成功');
                window.location.href="shenhe.html"; 
            });
        });
    }  
    
    $scope.fileToBase64 = function (file,type) {
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.addEventListener("load", function () {
          $timeout(function () {
        	  console.log(reader.result);
        	  $scope.shopInData[type]=reader.result;
          })
        }, false);
      }

               
     // 图片裁剪完成
    $scope.cropperImg = function () {
        $timeout(function () {
        	$scope.model= $("#cropper-img").cropper("getDataURL", "image/jpeg");
// console.log($scope.model);
            $("#cropper-img").cropper("destroy")
            $("#cropper").modal("hide");
        })
    }
    // 取消裁剪
    $scope.cancelCropper = function () {
        $("#cropper-img").cropper("destroy")
        $("#cropper").modal("hide");
    }
    
    //图片转base64
    function getBase64Image(img){  
    	
    		var canvas = document.createElement("canvas");  
            canvas.width = img.width;  
            canvas.height = img.height;  
            var ctx = canvas.getContext("2d");  
            ctx.drawImage(img, 0, 0, img.width, img.height);  
            var ext = img.src.substring(img.src.lastIndexOf(".")+1).toLowerCase();  
            var dataURL = canvas.toDataURL("image/"+ext);  
            return dataURL; 
    
         
   }  
    //上传图片
    $scope.uploadScrollPic=function(files,param){
    	if(files.length>0){
    		$scope.fileToBase64(files[0],param);
    	}
    	
    }
    
    $scope.getObjectURL=function(file) {
        var url = null;
        if (window.createObjcectURL != undefined) {
            url = window.createOjcectURL(file);
        } else if (window.URL != undefined) {
            url = window.URL.createObjectURL(file);
        } else if (window.webkitURL != undefined) {
            url = window.webkitURL.createObjectURL(file);
        }
        return url;
    }

    
    $scope.getBase64Image=function(imgSelectedUrl){
    	var canvas = document.createElement("canvas");  
        canvas.width = img.width;  
        canvas.height = img.height;  
        var ctx = canvas.getContext("2d");  
        ctx.drawImage(img, 0, 0, img.width, img.height);  
        var ext = img.src.substring(img.src.lastIndexOf(".")+1).toLowerCase();  
        var dataURL = canvas.toDataURL("image/"+ext);  
        return dataURL;  
    }
    
    // 存入成功短信验证商家
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
    		 var businessmanId=id;
    		console.log(businessmanId);
    	toastr.success('success','insert');
// $state.go("ruzhu",{businessmanId:businessmanId});
    	 window.location.href="ruzhu.html?businessmanId="+businessmanId;
    	});
    		}else{
    			toastr.error('验证码错误','error');
    		}
    	
    	});
    	
    }

    

}]);