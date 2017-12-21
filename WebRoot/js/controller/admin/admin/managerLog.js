controllers.controller("managerLog", ['$scope','$http','$state',function($scope,$http,$state) {
	
	$scope.managerLogList=[];
	$scope.dataAnalysisList=[];
	//获取管理日志
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
    //获取数据分析数据
    $http.post(baseUrl + "getDataAnalysis.do")
    .success(function (data) {
        if (data !=null) {
        	for(var i=0;i<data.length;i++){
            	//alert(data);
        		$scope.dataAnalysisList[i] = data[i];
            }
        }
        else{
        	toastr.error('获取数据3', '失败');
        }
    })
    //输出管理日志
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
	//输出数据分析结果
	$scope.getAnalysis=function(){
		if($scope.dataAnalysisList !=null){
			var s="";
			for(var i=0;i<$scope.dataAnalysisList.length;i++){
				s+=$scope.dataAnalysisList[i]
				s+="\r\n";
			}
			return s;
		}
	}
}]);