/**
 * Created by shilim on 2017/12/5.
 */
controllers.controller("userList", ['$scope','$http','$state',function($scope,$http,$state) {
	
    $scope.checkAll = false;//全选
    $scope.userList = [];
    $scope.page = new PageVo();
    
    
    $scope.userList.statusDescription = new UserVo();

    $scope.page.pageNum = 1;
    $scope.page.pageSize = 9;
    //获取用户列表
    $scope.getUserList = function() {
        //是否存在缓存页数
        if(sessionStorage.userListPageNum) $scope.page.pageNum = sessionStorage.userListPageNum;
        var url = baseUrl + "getUserListByPage.do"
        var data = {page:$scope.page.voToJson()}
        
        $http.post(url,data)
            .success(function(data) {
                console.log(data);
                if(data.serviceResult == 1) {
                    console.log(data.resultParam)
                    $scope.userList = data.resultParam;
                    $scope.page.pageNum = $scope.userList.pageNum;
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
    $scope.getUserList();
    
    //修改显示条数
    $scope.changePageSize = function () {
        sessionStorage.userListPageSize = $scope.page.pageSize;
        $scope.getUserList();
    }

    //上一页
    $scope.lastPage = function(pageNum) {
        console.log(pageNum);
        if(pageNum <= 0) return;
        sessionStorage.userListPageNum = pageNum;
        $scope.getUserList();
    }

    //下一页
    $scope.nextPage = function(pageNum) {
        console.log($scope.userList.pages)
        if(pageNum > $scope.userList.pages) return;
        sessionStorage.userListPageNum = pageNum;
        $scope.getUserList();
    }

    //跳转指定页
    $scope.toPage = function(e) {
        if(e && e.keyCode != 13) return;
        if($scope.page.pageNum <=0 ||
            $scope.page.pageNum > $scope.userList.pages) {
            $scope.page.pageNum = $scope.userList.pageNum;
            return;
        }
        sessionStorage.userListPageNum = $scope.page.pageNum;
        $scope.getUserList();
    }


    //用户查询
    $scope.searchUser = function(e) {
        if(e && e.keyCode != 13) return;
        sessionStorage.userListPageNum = 1;
        $scope.page.fuzzy = true;
        $scope.getUserList();
    }

    //全选
    $scope.$watch('checkAll',function() {
        if(!$scope.userList.list) return;
        if($scope.checkAll) {
            for(var i=0;i<$scope.userList.list.length;i++) {
                $scope.userList.list[i].checked = true;
            }
        } else {
            for(var i=0;i<$scope.userList.list.length;i++) {
                $scope.userList.list[i].checked = false;
            }
        }
    });

    //删除确认提示
    $scope.deleteType = "";
    $scope.deleteId = ""
    $scope.deleteTips = function(type,id,userName) {
        $scope.deleteType = type;
        $scope.deleteUserName=userName;
        if(id){
            $scope.deleteId = id;
        }
        $("#deleteTips").modal("show");
    }

    //用户删除
    $scope.deleteUser= function() {
        var userList = [];
        if($scope.deleteType == "one") {
            var userVo = new UserVo();
            userVo.id = $scope.deleteId;
            userVo.userName=$scope.deleteUserName;
            //alert(userVo.id);
            userList.push(userVo);
            var url = baseUrl + "adminDeleteUser.do";
            var data = {user:userList[0].voToJson()};
            
            $http.post(url,data)
                .success(function(data) {
                    toastr.success('删除用户1', '成功');
                    $scope.getUserList();
                })
                .error(function(data) {
                    toastr.error('删除失败3', '失败');
                });
            
        } else if ($scope.deleteType == "batch") {
            for(var i=0;i<$scope.userList.list.length;i++) {
                if($scope.userList.list[i].checked) {
                    var userVo = new UserVo();
                    userVo.id = $scope.userList.list[i].id;
                    userList.push(userVo);
                }
                var url = baseUrl + "adminDeleteUser.do";
                var data = {user:userList[i].voToJson()};
                
                $http.post(url,data)
                    .success(function(data) {
                        toastr.success('删除用户1', '成功');
                        $scope.getUserList();
                    })
                    .error(function(data) {
                        toastr.error('删除失败3', '失败');
                    });
            }
        }
        
        
        
        //var data ={userList:JSON.stringify(userList)}
        
        
    }

    //跳转修改页
    $scope.toEditPage = function(id) {
        $state.go("editUser",{id:id});
    }

}]);