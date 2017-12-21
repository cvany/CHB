/**
 * 李卓宏 2017/12/09.
 */
controllers.controller("checkBusinessInData", ['$scope','$http','$state',function($scope,$http,$state) {
	
	 
    //$scope.checkAll = false;//全选
    $scope.businessInDataList = [];
    $scope.page = new PageVo();

    $scope.page.pageNum = 1;
    $scope.page.pageSize = 8;
    //获取商家审核列表
    $scope.getBusinessInDataList = function() {
        //是否存在缓存页数
        if(sessionStorage.businessInDataListPageNum) $scope.page.pageNum = sessionStorage.businessInDataListPageNum;
        var url = baseUrl + "getBusinessInDataListByPage.do"
        var data = {page:$scope.page.voToJson()}
        
        $http.post(url,data)
            .success(function(data) {
                console.log(data);
                if(data.serviceResult == 1) {
                    console.log(data.resultParam)
                    $scope.businessInDataList = data.resultParam;
                    $scope.page.pageNum = $scope.businessInDataList.pageNum;
                } else {
                    toastr.error('获取数据2', '失败');
                }
                if(data.serviceResult == 5) {
                    toastr.error('获取权限', '失败');
                }
            })
            .error(function(data) {
                toastr.error('获取数据3', '失败');
            });
    }
    $scope.getBusinessInDataList();
    
    //修改显示条数
    $scope.changePageSize = function () {
        sessionStorage.businessInDataListPageSize = $scope.page.pageSize;
        $scope.getBusinessInDataList();
    }

    //上一页
    $scope.lastPage = function(pageNum) {
        console.log(pageNum);
        if(pageNum <= 0) return;
        sessionStorage.businessInDataListPageNum = pageNum;
        $scope.getBusinessInDataList();
    }

    //下一页
    $scope.nextPage = function(pageNum) {
        console.log($scope.businessInDataList.pages)
        if(pageNum > $scope.businessInDataList.pages) return;
        sessionStorage.businessInDataListPageNum = pageNum;
        $scope.getBusinessInDataList();
    }

    //跳转指定页
    $scope.toPage = function(e) {
        if(e && e.keyCode != 13) return;
        if($scope.page.pageNum <=0 ||
            $scope.page.pageNum > $scope.businessInDataList.pages) {
            $scope.page.pageNum = $scope.businessInDataList.pageNum;
            return;
        }
        sessionStorage.businessInDataListPageNum = $scope.page.pageNum;
        $scope.getBusinessInDataList();
    }


    //审核商家查询
    $scope.searchBusinessInData = function(e) {
        if(e && e.keyCode != 13) return;
        sessionStorage.businessInDataListPageNum = 1;
        $scope.page.fuzzy = true;
        $scope.getBusinessInDataList();
    }

    //全选
    $scope.$watch('checkAll',function() {
        if(!$scope.businessInDataList.list) return;
        if($scope.checkAll) {
            for(var i=0;i<$scope.businessInDataList.list.length;i++) {
                $scope.businessInDataList.list[i].checked = true;
            }
        } else {
            for(var i=0;i<$scope.businessInDataList.list.length;i++) {
                $scope.businessInDataList.list[i].checked = false;
            }
        }
    });

   
    
    //删除确认提示
    $scope.deleteType = "";
    $scope.deleteId = ""
    $scope.deleteTips = function(type,id) {
        $scope.deleteType = type;
        if(id){
            $scope.deleteId = id;
        }
        $("#deleteTips").modal("show");
    }
    //审核通过
    $scope.checkOk= function(id,shopName) {
    	
    	var shopVo = new ShopVo();
        shopVo.id=id; 
        shopVo.shopName=shopName;
        
        var url = baseUrl + "checkBusinessOk.do";
        var data={shop:shopVo.voToJson()};
        $http.post(url,data)
            .success(function(data) {
                toastr.success('审核通过1', '成功');
                $scope.getBusinessInDataList();
            })
            .error(function(data) {
                toastr.error('审核失败3', '失败');
            });
            
        
    }
    //审核不通过
    $scope.checkFail= function(id,shopName) {
    	
    	var shopVo = new ShopVo();
        shopVo.id=id;
        shopVo.shopName=shopName;
        var url = baseUrl + "deleteBusiness.do";
        var data={shop:shopVo.voToJson()};
       
        $http.post(url,data)
            .success(function(data) {
                toastr.success('审核不通过1', '成功');
                $scope.getBusinessInDataList();
            })
            .error(function(data) {
                toastr.error('审核失败3', '失败');
            });
            
        
    }
  //查看审核商家详情
    $scope.checkDetails=function(id){
    	
    	$scope.checkDetailsList = null;
        var shopInDataVo = new ShopInDataVo();
        shopInDataVo.id=id;
        //alert(shopInDataVo.id);
        var url = baseUrl + "checkDetails.do";
        var data={shopInData:shopInDataVo.voToJson()};
        
        $http.post(url,data)
        .success(function(data) {
            toastr.success('查询详情1', '成功');
            $scope.checkDetailsList=data.resultParam;
            $("#checkDetails").modal("show");
            
        })
        .error(function(data) {
            toastr.error('查询失败3', '失败');
        });
        
    }
    //跳转修改页
    $scope.toEditPage = function(id) {
        $state.go("editUser",{id:id});
    }

}]);
