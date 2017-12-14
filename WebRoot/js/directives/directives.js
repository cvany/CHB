controllers
    .directive('iCheck', ['$timeout', '$parse', function ($timeout, $parse) {
        return {
            require: 'ngModel',
            $scope: true,
            link: function ($scope, element, $attrs, ngModel) {
                return $timeout(function () {
                    var $element = $(element);

                    // Instantiate the iCheck control.
                    $element.iCheck({
                        checkboxClass: 'icheckbox_square-green',
                        radioClass: 'iradio_square-green',
                        increaseArea: '20%'
                    });

                    // If the model changes, update the iCheck control.
                    $scope.$watch($attrs.ngModel, function (newValue) {
                        $element.iCheck('update');
                    });

                    // If the iCheck control changes, update the model.
                    $element.on('ifChecked', function (event) {
                        if ($attrs.ngModel) {
                            $scope.$apply(function () {
                                ngModel.$setViewValue(true);
                            });
                        }
                    });
                    $element.on('ifUnchecked', function (event) {
                        if ($attrs.ngModel) {
                            $scope.$apply(function () {
                                ngModel.$setViewValue(false);
                            });
                        }
                    });

                });
            }
        };
    }])
    .directive('imgTooltip', ['$timeout', function ($timeout) {
        return {
            restrict: 'A',
            scope: true,
            link: function (scope, element, attrs) {
                var windowHalfWidth = $(window).width() / 2;
                var windowHalfHeight = $(window).height() / 2;
                var xOffset = 10;
                var yOffset = 20;
                var imgX = 0;//x轴坐标
                var imgY = 0;//y轴坐标
                var mousing = true;
                var imgWidth = 0;
                var imgHeight = 0;
                var maxWidth = attrs.imgTooltip.split("x")[0] || 100;
                var maxHeight = attrs.imgTooltip.split("x")[1] || 100;
                $timeout(function () {
                    console.log($("div[ui-view]"))
                    $("div[ui-view]").on("mouseover", "img#" + $(element).attr("id"), function (e) {
                        e.stopPropagation();
                        mousing = true;
                        var tooltip =
                            "<div id='tooltip'>" +
                            "<img src='" + e.target.src + "' " +
                            "style='max-width:" + maxWidth + "px; max-height:" + maxHeight + "px;'/>" +
                            "<\/div>";
                        $("body").append(tooltip);
                        imgWidth = $("#tooltip").width();
                        imgHeight = $("#tooltip").height();
                        imgX = e.pageX <= windowHalfWidth ? e.pageX + xOffset : (e.pageX - imgWidth - xOffset);
                        imgY = e.clientY <= windowHalfHeight ? e.pageY + yOffset : (e.pageY - imgHeight - yOffset);
                        $("#tooltip").css({
                            "top": (imgY) + "px",
                            "left": (imgX) + "px"
                        }).show("fast", function () {
                            imgWidth = $("#tooltip").width();
                            imgHeight = $("#tooltip").height();
                            imgX = e.pageX <= windowHalfWidth ? e.pageX + xOffset : (e.pageX - imgWidth - xOffset);
                            imgY = e.clientY <= windowHalfHeight ? e.pageY + yOffset : (e.pageY - imgHeight - yOffset);
                            $("#tooltip")
                                .css({
                                    "top": (imgY) + "px",
                                    "left": (imgX) + "px"
                                }); //设置x坐标和y坐标
                            mousing = false;
                        });
                    }).on("mouseout", "img#" + $(element).attr("id"), function () {
                        $("#tooltip").remove();  //移除
                    }).on("mousemove", "img#" + $(element).attr("id"), function (e) {
                        e.stopPropagation();
                        if (mousing == true) return;
                        imgX = e.pageX <= windowHalfWidth ? e.pageX + xOffset : (e.pageX - imgWidth - xOffset);
                        imgY = e.clientY <= windowHalfHeight ? e.pageY + yOffset : (e.pageY - imgHeight - yOffset);
                        $("#tooltip")
                            .css({
                                "top": (imgY) + "px",
                                "left": (imgX) + "px"
                            });
                    });
                })
            }
        }
    }])