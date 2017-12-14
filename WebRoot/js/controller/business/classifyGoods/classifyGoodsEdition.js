/**
 * Created by shilim on 2017/12/11.
 */
controllers.controller("classifyGoodsEdition", ['$scope', '$http', '$state', '$timeout', '$stateParams',
    function ($scope, $http, $state, $timeout, $stateParams) {
        $scope.classifyGoods = new ClassifyGoodsVo();

        //获取分类信息
        $scope.getClassifyGoods = function () {
            var classifyGoodsVo = new ClassifyGoodsVo();
            classifyGoodsVo.id = $stateParams.id;
            var url = baseUrl + "business/getClassifyGoodsById.do";
            var data = {classifyGoods: classifyGoodsVo.voToJson()};
            $http.post(url, data)
                .success(function (data) {
                    var rClassifyGoods = data.resultParam;
                    $scope.classifyGoods.id = rClassifyGoods.id;
                    $scope.classifyGoods.classifyName = rClassifyGoods.classifyName;
                    $scope.classifyGoods.classifyDescription = rClassifyGoods.classifyDescription;
                });
        }
        $scope.getClassifyGoods();

        //返回
        $scope.goBackTips = function () {
            $("#goBackTips").modal("show");
        }
        $scope.goBack = function () {
            $("#goBackTips").modal("hide");
            $timeout(function () {
                $state.go("classifyGoodsList");
            }, 300);
        }

        $scope.valid = false;
        $scope.submit = function () {
            $scope.valid = true;
            if ($scope.classifyGoodsForm.$invalid) return;
            var url = baseUrl + "business/updateClassifyGoods.do";
            var data = {classifyGoods: $scope.classifyGoods.voToJson()};
            $http.post(url, data)
                .success(function (data) {
                    toastr.success(data.resultInfo, '成功');
                    $scope.goBack();
                });
        }
    }]);