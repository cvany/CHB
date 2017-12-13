/**
 * Created by shilim on 2017/12/11.
 */
controllers.controller("classifyGoodsAddition", ['$scope','$http','$state','$timeout',function($scope,$http,$state,$timeout) {
    $scope.classifyGoods = new ClassifyGoodsVo();

    //返回
    $scope.goBackTips = function() {
        $("#goBackTips").modal("show");
    }
    $scope.goBack = function() {
        $("#goBackTips").modal("hide");
        $timeout(function() {
            $state.go("classifyGoodsList");
        },300);
    }

    $scope.valid = false;
    $scope.submit = function() {
        $scope.valid = true;
        if($scope.classifyGoodsForm.$invalid) return;
        var url = baseUrl + "business/addClassifyGoods.do";
        var data = {classifyGoods:$scope.classifyGoods.voToJson()};
        $http.post(url,data)
            .success(function(data) {
                toastr.success(data.resultInfo, '成功');
                $scope.goBack();
            });
    }
}]);