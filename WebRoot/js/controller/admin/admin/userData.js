/**
 * 李卓宏 2017/12/14.
 */
controllers.controller("userData", ['$scope','$http','$state',function($scope,$http,$state) {

	var userDataList= new Array();
	//统计用户数量
	$http.post(baseUrl + "userData.do")
    .success(function (data) {
        if (data !=null) {
        	for(var i=0;i<data.length;i++){
        	//alert(data);
        		userDataList[i] = data[i];
        }
           // alert(userDataList);

        	var lineChartData = {				//根据时间段统计用户数量
        			
        	        labels : ["七月","八月","九月","十月","十一月","十二月"],
        	        datasets : [
        	            {
        	                fillColor : "rgba(151,187,205,0.5)",
        	                strokeColor : "rgba(151,187,205,1)",
        	                pointColor : "rgba(151,187,205,1)",
        	                pointStrokeColor : "#fff",
        	                data : [userDataList[0],userDataList[1],userDataList[2],
        	                	userDataList[3],userDataList[4],userDataList[5]]
        	            }
        	        ]

        	    };
        	new Chart(document.getElementById("line").getContext("2d")).Line(lineChartData);
        	//根据登录次数统计数量
        	var barChartData = {
        	        labels : ["0~50","50~100","100~150","150~200","200~250","250~300"],
        	        datasets : [
        	            {
        	                fillColor : "rgba(220,120,220,0.5)",
        	                strokeColor : "rgba(220,120,220,1)",
        	                data : [userDataList[6],userDataList[7],userDataList[8],
        	                	userDataList[9],userDataList[10],userDataList[11]]
        	            }
        	        ]

        	    };
        	   
        	    
        	    new Chart(document.getElementById("bar").getContext("2d")).Bar(barChartData);
         
        }else {
            toastr.error('获取信息82', '失败');
        }
    });
	//保存数据分析结果
		$scope.setData=function(){
			var dataAnalysis=$("#dataAnalysis");
			
			var url = baseUrl + "setData.do"
			var data={data:dataAnalysis.val()};
			//alert(data);
			$http.post(url,data)
            .success(function(data) {
               
                if(data ==1) {
                	toastr.success('保存数据', '成功');
                } else {
                    toastr.error('获取数据2', '失败');
                }
                
            })
		}
	
        	

	
}]);