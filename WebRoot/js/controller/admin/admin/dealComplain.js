/**
 * 李卓宏 2017/12/09.
 */
controllers.controller("dealComplain", ['$scope','$http','$state',function($scope,$http,$state) {
	
    $scope.dealComplainList = [];
    $scope.page = new PageVo();

    $scope.page.pageNum = 1;
    $scope.page.pageSize = 3;
    //获取商家审核列表
    $scope.getDealComplainList = function() {
        //是否存在缓存页数
        if(sessionStorage.dealComplainListPageNum) $scope.page.pageNum = sessionStorage.dealComplainListPageNum;
        var url = baseUrl + "getDealComplainListByPage.do"
        var data = {page:$scope.page.voToJson()}
        
        $http.post(url,data)
            .success(function(data) {
                console.log(data);
                if(data.serviceResult == 1) {
                    console.log(data.resultParam)
                    $scope.dealComplainList = data.resultParam;
                    $scope.page.pageNum = $scope.dealComplainList.pageNum;
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
    $scope.getDealComplainList();
    
    //修改显示条数
    $scope.changePageSize = function () {
        sessionStorage.dealComplainListPageSize = $scope.page.pageSize;
        $scope.getDealComplainList();
    }

    //上一页
    $scope.lastPage = function(pageNum) {
        console.log(pageNum);
        if(pageNum <= 0) return;
        sessionStorage.dealComplainListPageNum = pageNum;
        $scope.getDealComplainList();
    }

    //下一页
    $scope.nextPage = function(pageNum) {
        console.log($scope.dealComplainList.pages)
        if(pageNum > $scope.dealComplainList.pages) return;
        sessionStorage.dealComplainListPageNum = pageNum;
        $scope.getDealComplainList();
    }

    //跳转指定页
    $scope.toPage = function(e) {
        if(e && e.keyCode != 13) return;
        if($scope.page.pageNum <=0 ||
            $scope.page.pageNum > $scope.dealComplainList.pages) {
            $scope.page.pageNum = $scope.dealComplainList.pageNum;
            return;
        }
        sessionStorage.dealComplainListPageNum = $scope.page.pageNum;
        $scope.getDealComplainList();
    }


    //审核商品查询
    $scope.searchDealComplain = function(e) {
        if(e && e.keyCode != 13) return;
        sessionStorage.dealComplainListPageNum = 1;
        $scope.page.fuzzy = true;
        $scope.getDealComplainList();
    }

    //全选
    $scope.$watch('checkAll',function() {
        if(!$scope.dealComplainList.list) return;
        if($scope.checkAll) {
            for(var i=0;i<$scope.dealComplainList.list.length;i++) {
                $scope.dealComplainList.list[i].checked = true;
            }
        } else {
            for(var i=0;i<$scope.dealComplainList.list.length;i++) {
                $scope.dealComplainList.list[i].checked = false;
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
    //审核投诉通过
    $scope.checkComplaintOk= function(id,shopId,userName,content) {
    	
    	var complaintVo = new ComplaintVo();
    	complaintVo.id=id;
    	complaintVo.userName=userName;
    	complaintVo.shopId=shopId;
    	complaintVo.content=content;
    	
        var url = baseUrl + "checkComplaintOk.do";
        var data={complaint:complaintVo.voToJson()};
        $http.post(url,data)
            .success(function(data) {
                toastr.success('审核通过1', '成功');
                $scope.getDealComplainList();
            })
            .error(function(data) {
                toastr.error('审核失败3', '失败');
            });
            
        
    }
    //审核投诉不通过
    $scope.checkComplaintFail= function(id,userId,userName,shopName) {
    	var complaintVo = new ComplaintVo();
    	complaintVo.id=id;
    	complaintVo.userId=userId;
    	complaintVo.userName=userName;
    	complaintVo.shopName=shopName;
        var url = baseUrl + "deleteComplaint.do";
        var data={complaint:complaintVo.voToJson()};
        //alert(data);
        $http.post(url,data)
            .success(function(data) {
                toastr.success('审核不通过1', '成功');
                $scope.getDealComplainList();
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
