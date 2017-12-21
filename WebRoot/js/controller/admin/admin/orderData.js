/**
 * 李卓宏 2017/12/16.
 */
controllers.controller("orderData", ['$scope','$http','$state',function($scope,$http,$state) {

var orderDataList= new Array();
	//统计订单数量
	$http.post(baseUrl + "orderData.do")
    .success(function (data) {
        if (data !=null) {
        	for(var i=0;i<data.length;i++){
        	
        		orderDataList[i] = data[i];
        }

        	var barChartData = {				//订单价格统计
        			
        	        labels : ["0~20","20~40","40~60","60~80","80~100",">100"],
        	        datasets : [
        	            {
        	            	fillColor : "rgba(220,120,220,0.5)",
        	                strokeColor : "rgba(220,120,220,1)",
        	                data : [orderDataList[0],orderDataList[1],orderDataList[2],
        	                	orderDataList[3],orderDataList[4],orderDataList[5]]
        	            }
        	        ]

        	    };
        	new Chart(document.getElementById("bar").getContext("2d")).Bar(barChartData);
        	//根据订单支付方式统计订单
        	var doughnutData = [
                {
                    value: orderDataList[6],
                    label: "线上支付",
                    color:"#F7464A"
                },
                {
                    value : orderDataList[7],
                    label: "货到付款",
                    color : "#46BFBD"
                }

            ];

        	    new Chart(document.getElementById("doughnut").getContext("2d")).Doughnut(doughnutData);

        }else {
            toastr.error('获取信息', '失败');
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
                toastr.error('获取数据', '失败');
            }
            
        })
	}
    
	
	
}]);