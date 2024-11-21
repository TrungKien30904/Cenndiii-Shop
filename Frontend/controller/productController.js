window.productController = function ($scope, $http, $location, $routeParams) {
    $scope.title = "Product detail"
    $http.get("http://localhost:8080/product/show").then(function (response) {
        if (response.status == 200) {
            $scope.productList = response.data.products;
            $scope.categoryList = response.data.categories;
        } else {
            console("Ket noi that bai")
        }
    })

    $scope.validateForm = function () {
        let check = true
        $scope.checkProduct = {
            maSanPham: false,
            tenSanPham: false,
            trangThai: false,
            category:false
        }

        if (!$scope.productInput || !$scope.productInput.maSanPham) {
            check = false;
            $scope.checkProduct.maSanPham = true
        }

        if (!$scope.productInput || !$scope.productInput.tenSanPham) {
            check = false;
            $scope.checkProduct.tenSanPham = true
        }

        if (!$scope.productInput || !$scope.productInput.trangThai) {
            check = false;
            $scope.checkProduct.trangThai = true
        }

        if (!$scope.productInput || !$scope.productInput.category) {
            check = false;
            $scope.checkProduct.category = true
        }
        return check;
    }

    $scope.addProduct = function () {
        if ($scope.validateForm()) {

            let newProduct = {
                maSanPham: $scope.productInput.maSanPham,
                tenSanPham: $scope.productInput.tenSanPham,
                trangThai: $scope.productInput.trangThai,
                category: $scope.productInput.category
            }

            
            $http.post("http://localhost:8080/product/add", newProduct).then(function (response) {
                if (response.status == 200) {
                    alert("Add success!")
                    $http.get("http://localhost:8080/product/show").then(function (response) {
                        if (response.status == 200) {
                            $scope.productList = response.data.products;
                            $scope.categoryList = response.data.categories;
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