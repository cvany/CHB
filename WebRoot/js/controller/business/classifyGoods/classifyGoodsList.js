/**
 * Created by shilim on 2017/12/11.
 */
controllers.controller("classifyGoodsList", ['$scope', '$http', '$state', function ($scope, $http, $state) {
    $scope.checkAll = false;//全选
    $scope.classifyGoodsList = [];
    $scope.page = new PageVo();
    $scope.classifyGoods = new ClassifyGoodsVo();
    $scope.tempPageSize = sessionStorage.classifyGoodsListPageSize || '5';

    $scope.page.pageNum = 1;
    $scope.page.pageSize = 5;
    $scope.classifyGoods.status = 1;
    //获取用户列表
    $scope.getClassifyGoodsList = function () {
        //是否存在缓存页数
        if (sessionStorage.classifyGoodsListPageNum) $scope.page.pageNum = sessionStorage.classifyGoodsListPageNum;
        //是否存在缓存显示数
        if (sessionStorage.classifyGoodsListPageSize) $scope.page.pageSize = sessionStorage.classifyGoodsListPageSize;
        //是否存在缓存类型
        if (sessionStorage.classifyGoodsType) $scope.classifyGoods.status = sessionStorage.classifyGoodsType;
        var url = baseUrl + "business/getClassifyGoodsListByPage.do"
        var data = {page: $scope.page.voToJson(), classifyGoods: $scope.classifyGoods.voToJson()}
        $http.post(url, data)
            .success(function (data) {
                if (data.serviceResult == 1) {
                    console.log(data.resultParam)
                    $scope.classifyGoodsList = data.resultParam;
                    $scope.page.pageNum = $scope.classifyGoodsList.pageNum;
                } else {
                    toastr.error('获取数据', '失败');
                }
                if (data.serviceResult == 5) {
                    toastr.error('获取权限', '失败');
                }
            })
            .error(function (data) {
                toastr.error('获取数据', '失败');
            });
    }
    $scope.getClassifyGoodsList();

    //修改显示条数
    $scope.changePageSize = function () {
        sessionStorage.classifyGoodsListPageSize = $scope.page.pageSize;
        $scope.getClassifyGoodsList();
    }

    //修改订单类型
    $scope.changeClassifyGoodsType = function (type) {
        sessionStorage.classifyGoodsType = type;
        $scope.getClassifyGoodsList()
    }

    //上一页
    $scope.lastPage = function (pageNum) {
        console.log(pageNum);
        if (pageNum <= 0) return;
        sessionStorage.classifyGoodsListPageNum = pageNum;
        $scope.getClassifyGoodsList();
    }

    //下一页
    $scope.nextPage = function (pageNum) {
        console.log($scope.classifyGoodsList.pages)
        if (pageNum > $scope.classifyGoodsList.pages) return;
        sessionStorage.classifyGoodsListPageNum = pageNum;
        $scope.getClassifyGoodsList();
    }

    //跳转指定页
    $scope.toPage = function (e) {
        if (e && e.keyCode != 13) return;
        if ($scope.page.pageNum <= 0 ||
            $scope.page.pageNum > $scope.classifyGoodsList.pages) {
            $scope.page.pageNum = $scope.classifyGoodsList.pageNum;
            return;
        }
        sessionStorage.classifyGoodsListPageNum = $scope.page.pageNum;
        $scope.getClassifyGoodsList();
    }


    //分类查询
    $scope.searchClassifyGoods = function (e) {
        if (e && e.keyCode != 13) return;
        sessionStorage.classifyGoodsListPageNum = 1;
        $scope.page.fuzzy = true;
        $scope.getClassifyGoodsList();
    }

    //全选
    $scope.$watch('checkAll', function () {
        if (!$scope.classifyGoodsList.list) return;
        if ($scope.checkAll) {
            for (var i = 0; i < $scope.classifyGoodsList.list.length; i++) {
                $scope.classifyGoodsList.list[i].checked = true;
            }
        } else {
            for (var i = 0; i < $scope.classifyGoodsList.list.length; i++) {
                $scope.classifyGoodsList.list[i].checked = false;
            }
        }
    });

    //删除确认提示
    $scope.deleteType = "";
    $scope.deleteId = ""
    $scope.deleteTips = function (type, id) {
        $scope.deleteType = type;
        if (id) {
            $scope.deleteId = id;
        }
        $("#deleteTips").modal("show");
    }

    //用户删除
    $scope.deleteUser = function () {
        var classifyGoodsList = [];
        if ($scope.deleteType == "one") {
            var userVo = new UserVo();
            userVo.id = $scope.deleteId;
            classifyGoodsList.push(userVo);
        } else if ($scope.deleteType == "batch") {
            for (var i = 0; i < $scope.classifyGoodsList.list.length; i++) {
                if ($scope.classifyGoodsList.list[i].checked) {
                    var userVo = new UserVo();
                    userVo.id = $scope.classifyGoodsList.list[i].id;
                    classifyGoodsList.push(userVo);
                }
            }
        }
        var url = baseUrl + "userManage/deleteUser";
        var data = {classifyGoodsList: JSON.stringify(classifyGoodsList)};
        $http.post(url, data)
            .success(function (data) {
                toastr.success('删除用户', '成功');
                $scope.getClassifyGoodsList();
            });
    }

    //跳转修改页
    $scope.toEditPage = function (id) {
        $state.go("classifyGoodsEdition", {id: id});
    }

}]);