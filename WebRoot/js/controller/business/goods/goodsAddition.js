/**
 * Created by shilim on 2017/12/12.
 */
controllers.controller("goodsAddition", ['$scope', '$http', '$state', '$timeout', function ($scope, $http, $state, $timeout) {
    $scope.goods = new GoodsVo();
    $scope.goods.goodsPhoto = '../../img/icon_upload.png'

    //获取菜品分类
    $scope.getClassifyGoodsList = function() {
        var pageVo = new PageVo();
        pageVo.pageNum = 1;
        pageVo.pageSize = 500;
        var url = baseUrl + "business/getClassifyGoodsListByPage.do";
        var data = {page: pageVo.voToJson()}
        $http.post(url,data)
            .success(function(data) {
                if(data.serviceResult == 1) {
                    $scope.classifyGoodsList = data.resultParam.list;
                    $scope.goods.classifyGoodsId = $scope.classifyGoodsList?$scope.classifyGoodsList[0].id:'';
                } else {
                    toastr.error('获取数据', '失败');
                }
            })
            .error(function(data) {
                toastr.error('获取数据', '失败');
            });
    }
    $scope.getClassifyGoodsList();

    // 图片选择监听
    angular.element("#imgSelect").change(function (event) {
        if (event.target.files.length > 0) {
            $timeout(function () {
                console.log(event.target.files)
                if ((event.target.files[0].size / 1024 / 1024) > 3) {
                    toastr.info("请选择小于3M的文件")
                    return;
                }
                $scope.imgSelectedUrl = window.URL.createObjectURL(event.target.files[0]);
                $("#cropper").modal("show")
                $timeout(function () {
                    $("#cropper-img").cropper({
                        aspectRatio: 1,
                        preview: ".img-preview",
                        minWidth: 80,
                        minHeight: 80,
                        done: function (data) {
                        }
                    });
                })
            })
        }
    })

    //返回
    $scope.goBackTips = function () {
        $("#goBackTips").modal("show");
    }
    $scope.goBack = function () {
        $("#goBackTips").modal("hide");
        $timeout(function () {
            $state.go("goodsList");
        }, 300);
    }

    // 图片裁剪完成
    $scope.cropperImg = function () {
        $timeout(function () {
            $scope.goods.goodsPhoto = $("#cropper-img").cropper("getDataURL", "image/jpeg")
            console.log($scope.goods.goodsPhoto)
            $("#cropper-img").cropper("destroy")
            $("#cropper").modal("hide");
        })
    }
    // 取消裁剪
    $scope.cancelCropper = function () {
        $("#cropper-img").cropper("destroy")
        $("#cropper").modal("hide");
    }

    $scope.valid = false;
    $scope.submit = function () {
        $scope.valid = true;
        if ($scope.goodsForm.$invalid) return;
        var url = baseUrl + "business/addGoods.do";
        var data = {goods: $scope.goods.voToJson()};
        $http.post(url, data)
            .success(function (data) {
                if(data.serviceResult == 1) {
                    toastr.success(data.resultInfo, '成功');
                    $scope.goBack();
                } else {
                    toastr.error(data.resultInfo, '失败');
                }
            });
    }
}]);