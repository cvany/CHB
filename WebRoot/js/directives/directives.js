controllers.directive('iCheck', ['$timeout', '$parse', function ($timeout, $parse) {
    return {
        require: 'ngModel',
        $scope:true,
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
}]);