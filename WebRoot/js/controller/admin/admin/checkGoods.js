/**
 * 李卓宏 2017/12/09.
 */
controllers.controller("checkGoods", ['$scope','$http','$state',function($scope,$http,$state) {
	
	 
    //$scope.checkAll = false;//全选
    $scope.checkGoodsList = [];
    $scope.page = new PageVo();

    $scope.page.pageNum = 1;
    $scope.page.pageSize = 3;
    //获取商品审核列表
    $scope.getCheckGoodsList = function() {
        //是否存在缓存页数
        if(sessionStorage.checkGoodsListPageNum) $scope.page.pageNum = sessionStorage.checkGoodsListPageNum;
        var url = baseUrl + "getCheckGoodsListByPage.do"
        var data = {page:$scope.page.voToJson()}
        
        $http.post(url,data)
            .success(function(data) {
                console.log(data);
                if(data.serviceResult == 1) {
                    console.log(data.resultParam)
                    $scope.checkGoodsList = data.resultParam;
                    $scope.page.pageNum = $scope.checkGoodsList.pageNum;
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
    $scope.getCheckGoodsList();
    
    //修改显示条数
    $scope.changePageSize = function () {
        sessionStorage.checkGoodsListPageSize = $scope.page.pageSize;
        $scope.getCheckGoodsList();
    }

    //上一页
    $scope.lastPage = function(pageNum) {
        console.log(pageNum);
        if(pageNum <= 0) return;
        sessionStorage.checkGoodsListPageNum = pageNum;
        $scope.getCheckGoodsList();
    }

    //下一页
    $scope.nextPage = function(pageNum) {
        console.log($scope.checkGoodsList.pages)
        if(pageNum > $scope.checkGoodsList.pages) return;
        sessionStorage.checkGoodsListPageNum = pageNum;
        $scope.getCheckGoodsList();
    }

    //跳转指定页
    $scope.toPage = function(e) {
        if(e && e.keyCode != 13) return;
        if($scope.page.pageNum <=0 ||
            $scope.page.pageNum > $scope.checkGoodsList.pages) {
            $scope.page.pageNum = $scope.checkGoodsList.pageNum;
            return;
        }
        sessionStorage.checkGoodsListPageNum = $scope.page.pageNum;
        $scope.getCheckGoodsList();
    }


    //审核商家查询
    $scope.searchCheckGoods = function(e) {
        if(e && e.keyCode != 13) return;
        sessionStorage.checkGoodsListPageNum = 1;
        $scope.page.fuzzy = true;
        $scope.getCheckGoodsList();
    }

    //全选
    $scope.$watch('checkAll',function() {
        if(!$scope.checkGoodsList.list) return;
        if($scope.checkAll) {
            for(var i=0;i<$scope.checkGoodsList.list.length;i++) {
                $scope.checkGoodsList.list[i].checked = true;
            }
        } else {
            for(var i=0;i<$scope.checkGoodsList.list.length;i++) {
                $scope.checkGoodsList.list[i].checked = false;
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
    $scope.checkGoodsOk= function(id) {
    	var goodsVo = new GoodsVo();
        goodsVo.id=id;
        var url = baseUrl + "checkGoodsOk.do";
        var data={goods:goodsVo.voToJson()};
        $http.post(url,data)
            .success(function(data) {
                toastr.success('审核通过1', '成功');
                $scope.getCheckGoodsList();
            })
            .error(function(data) {
                toastr.error('审核失败3', '失败');
            });
            
        
    }
    //审核不通过
    $scope.checkGoodsFail= function(id) {
    	var goodsVo = new GoodsVo();
        goodsVo.id=id;
        var url = baseUrl + "deleteGoods.do";
        var data={goods:goodsVo.voToJson()};
        $http.post(url,data)
            .success(function(data) {
                toastr.success('审核不通过1', '成功');
                $scope.getCheckGoodsList();
            })
            .error(function(data) {
                toastr.error('审核失败3', '失败');
            });
            
        
    }
  //查看审核商家详情
    $scope.checkDetails=function(id){
    	alert(ghkdg);
    	$scope.checkDetailsList = null;
        var shopInDataVo = new ShopInDataVo();
        shopVo.id=id;
        var url = baseUrl + "checkDetails.do";
        var data={shopInData:ShopInDataVo.voToJson()};
        $http.post(url,data)
        .success(function(data) {
            toastr.success('查询详情1', '成功');
            $scope.checkDetailsList=data.result;
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
