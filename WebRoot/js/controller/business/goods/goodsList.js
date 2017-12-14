/**
 * Created by shilim on 2017/12/11.
 */
controllers.controller("goodsList", ['$scope', '$http', '$state', function ($scope, $http, $state) {
    $scope.checkAll = false;//全选
    $scope.goodsList = [];
    $scope.page = new PageVo();
    $scope.goods = new GoodsVo();
    $scope.tempPageSize = sessionStorage.goodsListPageSize || 5;

    $scope.page.pageNum = 1;
    $scope.page.pageSize = 5;
    $scope.goods.status = 1;
    //获取用户列表
    $scope.getGoodsList = function () {
        //是否存在缓存页数
        if (sessionStorage.goodsListPageNum) $scope.page.pageNum = sessionStorage.goodsListPageNum;
        //是否存在缓存显示数
        if (sessionStorage.goodsListPageSize) $scope.page.pageSize = sessionStorage.goodsListPageSize;
        //是否存在缓存类型
        if (sessionStorage.goodsType) $scope.goods.status = sessionStorage.goodsType;
        var url = baseUrl + "business/getGoodsListByPage.do"
        var data = {page: $scope.page.voToJson(), goods: $scope.goods.voToJson()}
        $http.post(url, data)
            .success(function (data) {
                if (data.serviceResult == 1) {
                    console.log(data.resultParam)
                    $scope.goodsList = data.resultParam;
                    $scope.page.pageNum = $scope.goodsList.pageNum;
                } else {
                    toastr.error('获取数据', '失败');
                }
                if(data.serviceResult == 5) {
                    toastr.error('获取权限', '失败');
                }
            })
            .error(function(data) {
                if (data.serviceResult == 5) {
                    toastr.error('获取权限', '失败');
                }
            });
    }
    $scope.getGoodsList();

    //修改显示条数
    $scope.changePageSize = function () {
        sessionStorage.orderListPageSize = $scope.page.pageSize;
        $scope.getGoodsList();
    }

    //上一页
    $scope.lastPage = function(pageNum) {
        console.log(pageNum);
        if(pageNum <= 0) return;
        sessionStorage.goodsListPageSize = $scope.page.pageSize;
        $scope.getGoodsList();
    }

    //修改订单类型
    $scope.changeGoodsType = function (type) {
        sessionStorage.goodsType = type;
        $scope.getGoodsList()
    }

    //上一页
    $scope.lastPage = function (pageNum) {
        console.log(pageNum);
        if (pageNum <= 0) return;
        sessionStorage.goodsListPageNum = pageNum;
        $scope.getGoodsList();
    }

    //下一页
    $scope.nextPage = function (pageNum) {
        console.log($scope.goodsList.pages)
        if (pageNum > $scope.goodsList.pages) return;
        sessionStorage.goodsListPageNum = pageNum;
        $scope.getGoodsList();
    }

    //跳转指定页
    $scope.toPage = function (e) {
        if (e && e.keyCode != 13) return;
        if ($scope.page.pageNum <= 0 ||
            $scope.page.pageNum > $scope.goodsList.pages) {
            $scope.page.pageNum = $scope.goodsList.pageNum;
            return;
        }
        sessionStorage.goodsListPageNum = $scope.page.pageNum;
        $scope.getGoodsList();
    }

    //分类查询
    $scope.searchGoods = function (e) {
        if (e && e.keyCode != 13) return;
        sessionStorage.goodsListPageNum = 1;
        $scope.page.fuzzy = true;
        $scope.getGoodsList();
    }

    //全选

    $scope.$watch('checkAll', function () {
        if (!$scope.goodsList.list) return;
        if ($scope.checkAll) {
            for (var i = 0; i < $scope.goodsList.list.length; i++) {
                $scope.goodsList.list[i].checked = true;
            }
        } else {
            for (var i = 0; i < $scope.goodsList.list.length; i++) {
                $scope.goodsList.list[i].checked = false;
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

    //菜品删除
    $scope.deleteGoods= function() {
        var goodsList = [];
        if($scope.deleteType == "one") {
            var goodsVo = new GoodsVo();
            goodsVo.id = $scope.deleteId;
            goodsList.push(goodsVo);
        } else if ($scope.deleteType == "batch") {
            for(var i=0;i<$scope.goodsList.list.length;i++) {
                if($scope.goodsList.list[i].checked) {
                    var goodsVo = new GoodsVo();
                    goodsVo.id = $scope.goodsList.list[i].id;
                    goodsList.push(goodsVo);
                }
            }
        }
        var url = baseUrl + "goodsManage/deleteGoods";
        var data = {goodsList:JSON.stringify(goodsList)};
        $http.post(url,data)
            .success(function(data) {
                toastr.success('删除菜品', '成功');
            });
    }
    //用户删除
    $scope.deleteUser = function () {
        var goodsList = [];
        if ($scope.deleteType == "one") {
            var userVo = new UserVo();
            userVo.id = $scope.deleteId;
            goodsList.push(userVo);
        } else if ($scope.deleteType == "batch") {
            for (var i = 0; i < $scope.goodsList.list.length; i++) {
                if ($scope.goodsList.list[i].checked) {
                    var userVo = new UserVo();
                    userVo.id = $scope.goodsList.list[i].id;
                    goodsList.push(userVo);
                }
            }
        }
        var url = baseUrl + "userManage/deleteUser";
        var data = {goodsList: JSON.stringify(goodsList)};
        $http.post(url, data)
            .success(function (data) {
                toastr.success('删除用户', '成功');
                $scope.getGoodsList();
            });
    }

    //跳转修改页
    $scope.toEditPage = function (id) {
        $state.go("goodsEdition", {id: id});
    }

}]);