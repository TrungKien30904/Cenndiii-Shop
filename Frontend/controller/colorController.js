window.colorController = function ($scope, $http, $location, $routeParams) {
    $scope.title = "Color detail"
    $http.get("http://localhost:8080/color/show").then(function (response) {
        if (response.status == 200) {
            $scope.colorList = response.data;
        } else {
            console("Ket noi that bai")
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
    
    $scope.addColor = function () {
        if ($scope.validateForm()) {
            let newSize = {
                maMau: $scope.colorInput.maMau,
                tenMau: $scope.colorInput.tenMau,
                trangThai: $scope.colorInput.trangThai
            }
            $http.post("http://localhost:8080/color/add", newSize).then(function (response) {
                if (response.status == 200) {

                    alert("Add success!")

                    $http.get("http://localhost:8080/color/show").then(function (response) {
                        if (response.status == 200) {
                            $scope.colorInput = null;
                            $scope.colorList = response.data;
                        } else {
                            console("Ket noi that bai")
                        }
                    })
                    
                } else {
                    alert("Add failed!")
                }
            })
        }
    }
}