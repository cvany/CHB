controllers.controller("managerLog", ['$scope','$http','$state',function($scope,$http,$state) {
	
	$scope.managerLogList=[];
	
	$http.post(baseUrl + "managerLog.do")
    .success(function (data) {
        if (data !=null) {
        	for(var i=0;i<data.length;i++){
            	//alert(data);
        		$scope.managerLogList[i] = data[i];
            }
        }
        else{
        	toastr.error('获取数据3', '失败');
        }
    })
    $scope.get=function(){
		if($scope.managerLogList !=null){
			var s="";
			for(var i=0;i<$scope.managerLogList.length;i++){
				s+=$scope.managerLogList[i]
				s+="\r\n";
			}
			return s;
		}
	}
}]);