window.sizeController = function ($scope, $http, $location, $routeParams) {
    $scope.title = "Size detail"
    $http.get("http://localhost:8080/size/show").then(function (response) {
        if (response.status == 200) {
            $scope.sizeList = response.data;
        } else {
            console("Ket noi that bai")
        }
    })

    $scope.validateForm = function () {
        let check = true
        $scope.checkSize = {
            maSize: false,
            tenSize: false,
            trangThai: false
        }

        if (!$scope.sizeInput || !$scope.sizeInput.maSize) {
            check = false;
            $scope.checkSize.maSize = true
        }

        if (!$scope.sizeInput || !$scope.sizeInput.tenSize) {
            check = false;
            $scope.checkSize.tenSize = true
        }

        if (!$scope.sizeInput || !$scope.sizeInput.trangThai) {
            check = false;
            $scope.checkSize.trangThai = true
        }
        return check;
    }
    $scope.addSize = function () {
        if ($scope.validateForm()) {
            let newColor = {
                maSize: $scope.sizeInput.maSize,
                tenSize: $scope.sizeInput.tenSize,
                trangThai: $scope.sizeInput.trangThai
            }
            $http.post("http://localhost:8080/size/add", newColor).then(function (response) {
                if (response.status == 200) {

                    alert("Add success!")

                    $http.get("http://localhost:8080/size/show").then(function (response) {
                        if (response.status == 200) {
                            $scope.sizeInput = null;
                            $scope.sizeList = response.data;
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