/**
 * 菜单控制器
 */
var controllers = angular.module("controllers", []);

/**
 * 主页控制器
 */
controllers.controller("index", ['$scope', '$http', '$window', function ($scope, $http, $window) {
    $scope.businessman = new BusinessmanVo();

    // 登录判断和管理员信息获取
    $http.get(baseUrl + "business/isLogin.do")
        .success(function (data) {
            if (data.serviceResult === 1) {
                $scope.businessman.businessmanName = data.resultParam.businessmanName
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

}]);