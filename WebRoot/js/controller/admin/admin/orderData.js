controllers.controller("orderData", ['$scope','$http','$state',function($scope,$http,$state) {

var orderDataList= new Array();
	
	$http.post(baseUrl + "orderData.do")
    .success(function (data) {
        if (data !=null) {
        	for(var i=0;i<data.length;i++){
        	//alert(data);
        		orderDataList[i] = data[i];
        }
           // alert(userDataList);

        	var barChartData = {
        			
        	        labels : ["0~20","20~40","40~60","60~80","80~100",">100"],
        	        datasets : [
        	            {
        	            	fillColor : "rgba(220,120,220,0.5)",
        	                strokeColor : "rgba(220,120,220,1)",
        	                data : orderDataList
        	            }
        	        ]

        	    };
        	new Chart(document.getElementById("bar").getContext("2d")).Bar(barChartData);
        }else {
            toastr.error('获取信息82', '失败');
        }
    });
	
	$scope.setData=function(){
		var dataAnalysis=$("#dataAnalysis");
		
		var url = baseUrl + "setData.do"
		var data={data:dataAnalysis.val()};
		alert(data);
		$http.post(url,data)
        .success(function(data) {
           
            if(data ==1) {
            	toastr.success('保存数据', '成功');
            } else {
                toastr.error('获取数据2', '失败');
            }
            
        })
	}
    var doughnutData = [
        {
            value: 30,
            color:"#F7464A"
        },
        {
            value : 50,
            color : "#46BFBD"
        },
        {
            value : 100,
            color : "#FDB45C"
        },
        {
            value : 40,
            color : "#949FB1"
        },
        {
            value : 120,
            color : "#4D5360"
        }

    ];

	    new Chart(document.getElementById("doughnut").getContext("2d")).Doughnut(doughnutData);

	
	
}]);