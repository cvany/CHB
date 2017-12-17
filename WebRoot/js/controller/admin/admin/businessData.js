controllers.controller("businessData", ['$scope','$http','$state',function($scope,$http,$state) {

	var userDataList= new Array();
	
	$http.post(baseUrl + "userData.do")
    .success(function (data) {
        if (data !=null) {
        	for(var i=0;i<data.length;i++){
        	
        		userDataList[i] = data[i];
        }
           
        	var lineChartData = {
        			
        	        labels : ["七月","八月","九月","十月","十一月","十二月"],
        	        datasets : [
        	            {
        	                fillColor : "rgba(151,187,205,0.5)",
        	                strokeColor : "rgba(151,187,205,1)",
        	                pointColor : "rgba(151,187,205,1)",
        	                pointStrokeColor : "#fff",
        	                data : userDataList
        	            }
        	        ]

        	    };
        	new Chart(document.getElementById("line").getContext("2d")).Line(lineChartData);
        }else {
            toastr.error('获取信息82', '失败');
        }
    });
	    var barChartData = {
	        labels : ["广州","深圳","湛江","江门","茂名","珠海","佛山","其他"],
	        datasets : [
	            {
	                fillColor : "rgba(220,120,220,0.5)",
	                strokeColor : "rgba(220,120,220,1)",
	                data : [65,59,90,81,56,55,40,20]
	            }
	        ]

	    };
	   
	    
	    new Chart(document.getElementById("bar").getContext("2d")).Bar(barChartData);

	
	
}]);