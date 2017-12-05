/**
 * 菜单控制器
 */
var controllers = angular.module("controllers", []);

/**
 * 主页控制器
 */
controllers.controller("index", ['$scope', '$http', '$window', function ($scope, $http, $window) {
    $scope.admin = new AdminVo();

    // 登录判断和管理员信息获取
    $http.get(baseUrl + "admin/isLogin.do")
        .success(function (data) {
            if (data.serviceResult === 1) {
                $scope.admin.userName = data.resultParam.userName
            }
        })

    // 注销
    $scope.logout = function () {
        $http.get(baseUrl + "admin/logout.do")
            .success(function () {

            })
            .error(function () {
                toastr.error('注销', '失败');
            })
    }

}]);