/**
 * 菜单控制器
 */
var controllers = angular.module("controllers", []);

/**
 * 主页控制器
 */
controllers.controller("index", ['$scope', '$http', '$window', '$timeout', '$rootScope',
    function ($scope, $http, $window, $timeout, $rootScope) {
        $scope.businessman = new BusinessmanVo();
        $rootScope.orderCount = 0;
        $rootScope.remindCount = 0;

        // 登录判断和管理员信息获取
        $http.get(baseUrl + "business/isLogin.do")
            .success(function (data) {
                if (data.serviceResult === 1) {
                    $scope.businessman.businessmanName = data.resultParam.businessmanName
                    $scope.connectWebsocket(data.resultParam.id)
                }
            })

        // 注销
        $scope.logout = function () {
            $http.get(baseUrl + "business/logout.do")
                .success(function () {

                })
                .error(function () {
                    toastr.error('注销', '失败');
                })
        }

        // if (!!window.EventSource) {
        //     var source = new EventSource(baseUrl+'push.do'); //为http://localhost:8080/testSpringMVC/push
        //     s = '';
        //     source.addEventListener('message', function (e) {
        //         console.log(e)
        //     });
        //
        //     source.addEventListener('open', function (e) {
        //         console.log("连接打开.");
        //     }, false);
        //
        //     source.addEventListener('error', function (e) {
        //         if (e.readyState == EventSource.CLOSED) {
        //             console.log("连接关闭");
        //         } else {
        //             console.log(e.readyState);
        //         }
        //     }, false);
        // } else {
        //     console.log("没有sse");
        // }

        $scope.connectWebsocket = function (id) {
            if ('WebSocket' in window) {
                websocket = new WebSocket("ws://localhost:8080/CHB/ws.do?businessId=" + id);
            } else if ('MozWebSocket' in window) {
                websocket = new MozWebSocket("ws://172.16.15.193:8080/CHB/ws.do?businessId=" + id);
            } else {
                websocket = new SockJS("http://CHB/ws/sockjs?businessId=" + id);
            }
            websocket.onopen = function (event) {
                console.log("WebSocket:已连接");
            };
            websocket.onmessage = function (event) {
                var data = JSON.parse(event.data);
                console.log("WebSocket:收到一条消息：", data);
                if(data.resultInfo == '新订单') {
                    $timeout(function () {
                        $rootScope.orderCount ++;
                    })
                } else if (data.resultInfo == '新催单') {
                    $timeout(function () {
                        $rootScope.remindCount ++;
                    })
                }
            };
            websocket.onerror = function (event) {
                console.log("WebSocket:发生错误 ");
            };
            websocket.onclose = function (event) {
                console.log("WebSocket:已关闭");
            }
        }
    }]);