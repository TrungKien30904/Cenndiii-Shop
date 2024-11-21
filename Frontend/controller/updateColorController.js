window.updateColorController = function ($scope, $http, $location, $routeParams) {

    $http.get("http://localhost:8080/color/show/" + $routeParams.id).then(function (response) {
        $scope.title = "Color"
        $scope.colorInput = {
            maMau: response.data.maMau,
            tenMau: response.data.tenMau,
            trangThai: response.data.trangThai
        }
    })

    $scope.validateForm = function () {
        let check = true
        $scope.checkColor = {
            maMau: false,
            tenMau: false,
            trangThai: false
        }

        if (!$scope.colorInput || !$scope.colorInput.maMau) {
            check = false;
            $scope.checkColor.maMau = true
        }

        if (!$scope.colorInput || !$scope.colorInput.tenMau) {
            check = false;
            $scope.checkColor.tenMau = true
        }

        if (!$scope.colorInput || !$scope.colorInput.trangThai) {
            check = false;
            $scope.checkColor.trangThai = true
        }
        return check;
    }
    
    $scope.updateColor = function () {
        $scope.title = "Color"
        if ($scope.validateForm()) {
            let newColor = {
                maMau: $scope.colorInput.maMau,
                tenMau: $scope.colorInput.tenMau,
                trangThai: $scope.colorInput.trangThai
            }
            $http.post("http://localhost:8080/color/update/" + $routeParams.id, newColor).then(function (response) {
                if (response.status == 200) {
                    alert("Update success!")
                    $location.path("/color/show")
                }else{
                    alert("Update failed!")
                }
            })
        }
    }
}