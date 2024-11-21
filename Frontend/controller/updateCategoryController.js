window.updateCategoryController = function ($scope, $http, $location, $routeParams) {

    $http.get("http://localhost:8080/category/show/" + $routeParams.id).then(function (response) {
        $scope.title = "Category"
        $scope.categoryInput = {
            maDanhMuc: response.data.maDanhMuc,
            tenDanhMuc: response.data.tenDanhMuc,
            trangThai: response.data.trangThai
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
    
    $scope.updateCategory = function () {
        $scope.title = "Category"
        if ($scope.validateForm()) {
            let newCategory = {
                maDanhMuc: $scope.categoryInput.maDanhMuc,
                tenDanhMuc: $scope.categoryInput.tenDanhMuc,
                trangThai: $scope.categoryInput.trangThai
            }
            $http.post("http://localhost:8080/category/update/" + $routeParams.id, newCategory).then(function (response) {
                if (response.status == 200) {
                    alert("Update success!")
                    $location.path("/category/show")
                }else{
                    alert("Update failed!")
                }
            })
        }
    }
}