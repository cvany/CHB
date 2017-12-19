/**
 * Created by shilim on 2017/12/6.
 */
controllers.controller("orderList", ['$scope', '$http', '$state', '$rootScope', function ($scope, $http, $state, $rootScope) {
    $scope.checkAll = false;//全选
    $scope.orderList = [];
    $scope.page = new PageVo();
    $scope.order = new OrderVo();
    $scope.tempPageSize = sessionStorage.orderListPageSize || '5';

    $scope.page.pageNum = 1;
    $scope.page.pageSize = 5;
    $scope.order.status = 1;
    sessionStorage.orderListPageNum = 1;
    //获取订单列表
    $scope.getOrderList = function () {
        //是否存在缓存页数
        if (sessionStorage.orderListPageNum) $scope.page.pageNum = sessionStorage.orderListPageNum;
        //是否存在缓存显示数
        if (sessionStorage.orderListPageSize) $scope.page.pageSize = sessionStorage.orderListPageSize;
        //是否存在缓存类型
        if (sessionStorage.orderType) $scope.order.status = sessionStorage.orderType;
        var url = baseUrl + "business/getOrderListByPage.do"
        var data = {page: $scope.page.voToJson(), order: $scope.order.voToJson()}
        $http.post(url, data)
            .success(function (data) {
                if (data.serviceResult == 1) {
                    console.log(data.resultParam)
                    if (data.resultParam.total != 0 && sessionStorage.orderListPageNum > Math.ceil(data.resultParam.total / $scope.page.pageSize)) {
                        sessionStorage.orderListPageNum = 1;
                        $scope.getOrderList();
                        return;
                    }
                    $scope.orderList = data.resultParam;
                    $scope.page.pageNum = $scope.orderList.pageNum;
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
    $scope.getOrderList();

    $scope.refreshList = function () {
        $rootScope.orderCount = 0;
        $scope.getOrderList();
    }

    //修改显示条数
    $scope.changePageSize = function () {
        sessionStorage.orderListPageSize = $scope.page.pageSize;
        $scope.getOrderList();
    }

    //修改订单类型
    $scope.changeOrderType = function (type) {
        sessionStorage.orderType = type;
        $scope.getOrderList()
    }

    //上一页
    $scope.lastPage = function (pageNum) {
        console.log(pageNum);
        if (pageNum <= 0) return;
        sessionStorage.orderListPageNum = pageNum;
        $scope.getOrderList();
    }

    //下一页
    $scope.nextPage = function (pageNum) {
        console.log($scope.orderList.pages)
        if (pageNum > $scope.orderList.pages) return;
        sessionStorage.orderListPageNum = pageNum;
        $scope.getOrderList();
    }

    //跳转指定页
    $scope.toPage = function (e) {
        if (e && e.keyCode != 13) return;
        if ($scope.page.pageNum <= 0 ||
            $scope.page.pageNum > $scope.orderList.pages) {
            $scope.page.pageNum = $scope.orderList.pageNum;
            return;
        }
        sessionStorage.orderListPageNum = $scope.page.pageNum;
        $scope.getOrderList();
    }


    //订单查询
    $scope.searchOrder = function (e) {
        if (e && e.keyCode != 13) return;
        sessionStorage.orderListPageNum = 1;
        $scope.page.fuzzy = true;
        $scope.getOrderList();
    }

    //全选
    $scope.$watch('checkAll', function () {
        if (!$scope.orderList.list) return;
        if ($scope.checkAll) {
            for (var i = 0; i < $scope.orderList.list.length; i++) {
                $scope.orderList.list[i].checked = true;
            }
        } else {
            for (var i = 0; i < $scope.orderList.list.length; i++) {
                $scope.orderList.list[i].checked = false;
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
        var orderList = [];
        if ($scope.deleteType == "one") {
            var userVo = new UserVo();
            userVo.id = $scope.deleteId;
            orderList.push(userVo);
        } else if ($scope.deleteType == "batch") {
            for (var i = 0; i < $scope.orderList.list.length; i++) {
                if ($scope.orderList.list[i].checked) {
                    var userVo = new UserVo();
                    userVo.id = $scope.orderList.list[i].id;
                    orderList.push(userVo);
                }
            }
        }
        var url = baseUrl + "userManage/deleteUser";
        var data = {orderList: JSON.stringify(orderList)};
        $http.post(url, data)
            .success(function (data) {
                toastr.success('删除用户', '成功');
                $scope.getOrderList();
            });
    }

    //跳转修改页
    $scope.toDetailPage = function (item) {
        sessionStorage.tempOrder = JSON.stringify(item)
        $state.go("orderDetail", {type: $scope.order.status, id: item.id});
    }

}]);