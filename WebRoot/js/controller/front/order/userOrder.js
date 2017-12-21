/**
 * Created by shilim on 2017/12/18.
 */
var userOrder = angular.module("userOrder", []);
 var baseUrl = "/CHB/";
//var baseUrl = "http://localhost:8080/CHB/";
userOrder
    .config(['$httpProvider', function ($httpProvider) {
        $httpProvider.defaults.transformRequest = function (obj) {
            var str = [];
            for (var p in obj) {
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            }
            return str.join("&");
        };
        $httpProvider.defaults.headers.post = {
            'Content-Type': 'application/x-www-form-urlencoded',
        };
        $httpProvider.interceptors.push(["$window", function ($window) {
            return {
                response: function (response) {
                    if (response.config.url.indexOf(".do") > 0 && response.config.url.indexOf("login") < 0) {
                        if (response.data.userToken == false) {
                            $window.location.href = "/CHB/pages/front/user/userLogin.html";
                        }
                    }
                    return response
                },

            }
        }]);
    }])
    .filter("goodsListFormat", function () {//商品过滤器
        return function (input) {
            var out = "";
            input.forEach(function (item) {
               out += item.goods.goodsName + ' / '
            })
            out = out.substring(0,out.length-3) + " 共"+input.length+"个菜品"
            return out;
        };
    });

userOrder.controller("userOrder", ['$scope', '$http', '$window', function ($scope, $http, $window) {
    $scope.page = new PageVo();
    $scope.order = new OrderVo();

    $scope.page.pageNum = 1;
    $scope.page.pageSize = 10;
    $scope.order.status = 0;
    sessionStorage.userOrderListPageNum = 1;
    
    $scope.logout = function(){
    	$.ajax({
    		url : baseUrl+"/logout.do",
    		dataType:"json",
    		success : function(data) {
    			//保存当前的URL地址
            	var curUrl = window.document.location.href;
            	sessionStorage.setItem("curUrl",curUrl);
    			sessionStorage.setItem("userToken","false");
    			$window.location.href = "/CHB/pages/front/user/userLogin.html";
    		}
    	});
    }
    
    layui.use('layer', function(){
    	  var layer = layui.layer;
    	});   

    // 切换订单类型
    $scope.changeStatus = function (status) {
        sessionStorage.userOrderType = status;
        $scope.getOrderList();
    }

    // 获取订单列表
    $scope.getOrderList = function () {
        //是否存在缓存页数
        if (sessionStorage.userOrderListPageNum) $scope.page.pageNum = sessionStorage.userOrderListPageNum;
        //是否存在缓存类型
        if (sessionStorage.userOrderType) $scope.order.status = sessionStorage.userOrderType;
        var url = baseUrl + "user/getOrderListByPage.do"
        var data = {page: $scope.page.voToJson(), order: $scope.order.voToJson()}
        $http.post(url, data)
            .success(function (data) {
                if (data.serviceResult == 1) {
                    console.log(data.resultParam)
                    if(data.resultParam.total !=0 && sessionStorage.userOrderListPageNum>Math.ceil(data.resultParam.total/$scope.page.pageSize)) {
                        sessionStorage.userOrderListPageNum = 1;
                        $scope.getOrderList();
                        return;
                    }
                    $scope.orderList = data.resultParam;
                    $scope.page.pageNum = $scope.orderList.pageNum;
                }
            })
            .error(function (data) {
                alert('网络错误');
            });
    }
    $scope.getOrderList();

    //上一页
    $scope.lastPage = function (pageNum) {
        console.log(pageNum);
        if (pageNum <= 0) return;
        sessionStorage.userOrderListPageNum = pageNum;
        $scope.getOrderList();
    }

    //下一页
    $scope.nextPage = function (pageNum) {
        console.log($scope.orderList.pages)
        if (pageNum > $scope.orderList.pages) return;
        sessionStorage.userOrderListPageNum = pageNum;
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
        sessionStorage.userOrderListPageNum = $scope.page.pageNum;
        $scope.getOrderList();
    }
    
    // 催单
    $scope.remind = function(id,shopId) {
    	var orderVo = new OrderVo();
        orderVo.id = id;
        orderVo.shopId = shopId;
        var url = baseUrl + "user/remind.do";
        var data = {
            order: orderVo.voToJson()
        };
        $http.post(url, data)
            .success(function (data) {
                if (data.serviceResult == 1) {
                	 layer.msg("催单成功")
                } else {
                }
            })
            .error(function (data) {
            });
    }

    // 付款
    $scope.pay = function (id) {
        var orderVo = new OrderVo();
        orderVo.id = id;
        var url = baseUrl + "user/getPayUrl.do";
        var data = {
            order: orderVo.voToJson()
        };
        $http.post(url, data)
            .success(function (data) {
                if (data.serviceResult == 1) {
                    $window.location.href = data.resultParam;
                } else {
                }
            })
            .error(function (data) {
            });
    }

    $scope.confirmId = ''
    $scope.confirmReceiptCheck = function (id) {
        $scope.confirmId = id;
        angular.element("#confirm-check").modal()
    }
    // 确认收货
    $scope.confirmReceipt = function () {
        angular.element("#confirm-check").modal('hide')
        var orderVo = new OrderVo();
        orderVo.id = $scope.confirmId;
        var url = baseUrl + "user/confirmReceipt.do";
        var data = {
            order: orderVo.voToJson()
        };
        $http.post(url, data)
            .success(function (data) {
                if (data.serviceResult == 1) {
                    $scope.getOrderList();
                }
            })
            .error(function (data) {
            });
    }
}]);