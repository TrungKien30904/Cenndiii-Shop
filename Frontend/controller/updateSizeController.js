window.updateSizeController = function ($scope, $http, $location, $routeParams) {

    $http.get("http://localhost:8080/size/show/" + $routeParams.id).then(function (response) {
        $scope.title = "Size"
        $scope.sizeInput = {
            maSize: response.data.maSize,
            tenSize: response.data.tenSize,
            trangThai: response.data.trangThai
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
    
    $scope.updateSize = function () {
        $scope.title = "Size"
        if ($scope.validateForm()) {
            let newSize = {
                maSize: $scope.sizeInput.maSize,
                tenSize: $scope.sizeInput.tenSize,
                trangThai: $scope.sizeInput.trangThai
            }
            $http.post("http://localhost:8080/size/update/" + $routeParams.id, newSize).then(function (response) {
                if (response.status == 200) {
                    alert("Update success!")
                    $location.path("/size/show")
                }else{
                    alert("Update failed!")
                }
            })
        }
    }
}