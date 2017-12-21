/**
 * 李卓宏 2017/12/15.
 */
controllers.controller("businessData", ['$scope','$http','$state',function($scope,$http,$state) {

	var businessDataList= new Array();
	//统计商家数量
	$http.post(baseUrl + "getBusinessData.do")
    .success(function (data) {
        if (data !=null) {
        	for(var i=0;i<data.length;i++){
        	
        		businessDataList[i] = data[i];
        }
           
        	var barChartData = {  			//根据商家信誉度统计数量
        			
        	        labels : ["0~20","20~40","40~60","60~80","80~100"],
        	        datasets : [
        	            {
        	                fillColor : "rgba(151,187,205,0.5)",
        	                strokeColor : "rgba(151,187,205,1)",
        	                pointColor : "rgba(151,187,205,1)",
        	                pointStrokeColor : "#fff",
        	                data : [businessDataList[0],businessDataList[1],
        	                	businessDataList[2],businessDataList[3],businessDataList[4]]
        	            }
        	        ]

        	    };
        	new Chart(document.getElementById("bar").getContext("2d")).Bar(barChartData);
        	//根据商家类型统计数量
        	var doughnutData = [
                {
                	
                    value: businessDataList[5],
                    label:"快餐便当",
                    color:"#F7464A"
                },
                {
                    value : businessDataList[6],
                    label :"特色菜系",
                    color : "#46BFBD"
                },
                {
                    value : businessDataList[7],
                    label :"小吃宵夜",
                    color : "#FDB45C"
                },
                {
                    value : businessDataList[8],
                    label :"异国料理",
                    color : "#949FB1"
                },
                {
                    value : businessDataList[9],
                    label :"美食",
                    color : "#4D5360"
                },
                {
                    value : businessDataList[10],
                    label :"甜品饮品",
                    color : "#779FB1"
                },
                {
                    value : businessDataList[11],
                    label :"商店超市",
                    color : "#5D5360"
                },
                {
                    value : businessDataList[12],
                    label :"果蔬生鲜",
                    color : "#930FB1"
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