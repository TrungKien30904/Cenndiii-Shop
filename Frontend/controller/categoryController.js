window.categoryController = function ($scope, $http, $location, $routeParams) {
    $scope.title = "Category detail"
    $http.get("http://localhost:8080/category/show").then(function (response) {
        if (response.status == 200) {
            $scope.categoryList = response.data;
        } else {
            console("Ket noi that bai")
        }
    })

    $scope.validateForm = function () {
        let check = true
        $scope.checkCategory = {
            maDanhMuc: false,
            tenDanhMuc: false,
            trangThai: false
        }

        if (!$scope.categoryInput || !$scope.categoryInput.maDanhMuc) {
            check = false;
            $scope.checkCategory.maDanhMuc = true
        }

        if (!$scope.categoryInput || !$scope.categoryInput.tenDanhMuc) {
            check = false;
            $scope.checkCategory.tenDanhMuc = true
        }

        if (!$scope.categoryInput || !$scope.categoryInput.trangThai) {
            check = false;
            $scope.checkCategory.trangThai = true
        }
        return check;
    }
    
    $scope.addCategory = function () {
        if ($scope.validateForm()) {
            let newCategory = {
                maDanhMuc: $scope.categoryInput.maDanhMuc,
                tenDanhMuc: $scope.categoryInput.tenDanhMuc,
                trangThai: $scope.categoryInput.trangThai
            }
            $http.post("http://localhost:8080/category/add", newCategory).then(function (response) {
                if (response.status == 200) {

                    alert("Add success!")

                    $http.get("http://localhost:8080/category/show").then(function (response) {
                        if (response.status == 200) {
                            $scope.categoryInput = null;
                            $scope.categoryList = response.data;
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