/**
 * 李卓宏 2017/12/09.
 */
controllers.controller("updateBusiness", ['$scope','$http','$state',function($scope,$http,$state) {
	$scope.checkAll = false;//全选
    $scope.businessList = [];
    $scope.page = new PageVo();

    $scope.page.pageNum = 1;
    $scope.page.pageSize = 6;
    //获取商家列表
    $scope.getBusinessList = function() {
        //是否存在缓存页数
        if(sessionStorage.businessListPageNum) $scope.page.pageNum = sessionStorage.businessListPageNum;
        var url = baseUrl + "getBusinessListByPage.do"
        var data = {page:$scope.page.voToJson()};
        $http.post(url,data)
            .success(function(data) {
                console.log(data);
                if(data.serviceResult == 1) {
                    console.log(data.resultParam)
                    $scope.businessList = data.resultParam;
                    $scope.page.pageNum = $scope.businessList.pageNum;
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
    $scope.getBusinessList();
    
    //修改显示条数
    $scope.changePageSize = function () {
        sessionStorage.businessListPageSize = $scope.page.pageSize;
        $scope.getBusinessList();
    }

    //上一页
    $scope.lastPage = function(pageNum) {
        console.log(pageNum);
        if(pageNum <= 0) return;
        sessionStorage.businessListPageNum = pageNum;
        $scope.getBusinessList();
    }

    //下一页
    $scope.nextPage = function(pageNum) {
        console.log($scope.businessList.pages)
        if(pageNum > $scope.businessList.pages) return;
        sessionStorage.businessListPageNum = pageNum;
        $scope.getBusinessList();
    }

    //跳转指定页
    $scope.toPage = function(e) {
        if(e && e.keyCode != 13) return;
        if($scope.page.pageNum <=0 ||
            $scope.page.pageNum > $scope.businessList.pages) {
            $scope.page.pageNum = $scope.businessList.pageNum;
            return;
        }
        sessionStorage.businessListPageNum = $scope.page.pageNum;
        $scope.getBusinessList();
    }


    //商家查询
    $scope.searchBusiness = function(e) {
        if(e && e.keyCode != 13) return;
        sessionStorage.businessListPageNum = 1;
        $scope.page.fuzzy = true;
        $scope.getBusinessList();
    }

    //全选
    $scope.$watch('checkAll',function() {
        if(!$scope.businessList.list) return;
        if($scope.checkAll) {
            for(var i=0;i<$scope.businessList.list.length;i++) {
                $scope.businessList.list[i].checked = true;
            }
        } else {
            for(var i=0;i<$scope.businessList.list.length;i++) {
                $scope.businessList.list[i].checked = false;
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
    $scope.checkOk= function(id) {
    	var shopVo = new ShopVo();
        shopVo.id=id;
        var url = baseUrl + "checkBusinessOk.do";
        var data={shop:shopVo.voToJson()};
        $http.post(url,data)
            .success(function(data) {
                toastr.success('审核通过1', '成功');
                $scope.getBusinessList();
            })
            .error(function(data) {
                toastr.error('审核失败3', '失败');
            });
            
        
    }
  //商家删除
    $scope.deleteShop= function() {
        var shopList = [];
        if($scope.deleteType == "one") {
            var shopVo = new ShopVo();
            shopVo.id = $scope.deleteId;
            //alert(userVo.id);
            shopList.push(shopVo);
            var url = baseUrl + "deleteBusiness.do";
            var data = {shop:shopList[0].voToJson()};
            
            $http.post(url,data)
                .success(function(data) {
                    toastr.success('删除商家1', '成功');
                    $scope.getBusinessList();
                })
                .error(function(data) {
                    toastr.error('删除失败3', '失败');
                });
            
        } else if ($scope.deleteType == "batch") {
            for(var i=0;i<$scope.businessList.list.length;i++) {
                if($scope.businessList.list[i].checked) {
                    var shopVo = new ShopVo();
                    shopVo.id = $scope.businessList.list[i].id;
                    shopList.push(shopVo);
                    var url = baseUrl + "deleteBusiness.do";
                    var data = {shop:shopList[i].voToJson()};
                    
                    $http.post(url,data)
                        .success(function(data) {
                            toastr.success('删除商家1', '成功');
                            $scope.getBusinessList();
                        })
                        .error(function(data) {
                            toastr.error('删除失败3', '失败');
                        });
                }
                
            }
        }
    }
   
    //查看商家详情
    $scope.updateShow=function(item){
    	
    	$scope.shopDetails=item;
    	$("#editBusiness").modal("show");
    	
    	
    }
  //商家信息修改
    $scope.updateShop=function(credibility,isPass,isOnline){
    	if(credibility==null){
    		credibility=$scope.shopDetails.credibility;
    	}
    	if(isPass==null){
    		isPass=$scope.shopDetails.isPass;
    	}
    	if(isOnline==null){
    		isOnline=$scope.shopDetails.isOnline;
    	}
    	var shopVo=new ShopVo();
    	shopVo.id=$scope.shopDetails.id;
    	shopVo.credibility=credibility;
    	shopVo.isPass=isPass;
    	shopVo.isOnline=isOnline;
    	shopVo.shopName=$scope.shopDetails.shopName;
        var url = baseUrl + "updateShop.do";
        var data={shop:shopVo.voToJson()};
        $http.post(url,data)
        .success(function(data) {
            toastr.success('修改信息1', '成功');
           // $("#editBusiness").modal("hide");
            window.location.reload();
        })
        .error(function(data) {
            toastr.error('修改失败3', '失败');
        });
       
    }
    //跳转修改页
    $scope.toEditPage = function(id) {
        $state.go("editUser",{id:id});
    }

}]);

