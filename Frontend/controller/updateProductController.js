window.updateProductController = function ($scope, $http, $location, $routeParams) {
    $http.get("http://localhost:8080/product/show/" + $routeParams.id).then(function (response) {
        $scope.title = "Product";
        
        $scope.categoryList = response.data.categories;
        
        $scope.productInput = {
            maSanPham: response.data.productDetail.maSanPham,
            tenSanPham: response.data.productDetail.tenSanPham,
            trangThai: response.data.productDetail.trangThai,
        };

        let selectedCategory = $scope.categoryList.find(category => category.maDanhMuc === response.data.productDetail.category.maDanhMuc);
        
        if (selectedCategory) {
            $scope.productInput.category = selectedCategory;
        }
    });

    $scope.validateForm = function () {
        let check = true;
        $scope.checkProduct = {
            maSanPham: false,
            tenSanPham: false,
            trangThai: false,
            category: false
        };

        if (!$scope.productInput || !$scope.productInput.maSanPham) {
            check = false;
            $scope.checkProduct.maSanPham = true;
        }

        if (!$scope.productInput || !$scope.productInput.tenSanPham) {
            check = false;
            $scope.checkProduct.tenSanPham = true;
        }

        if (!$scope.productInput || !$scope.productInput.trangThai) {
            check = false;
            $scope.checkProduct.trangThai = true;
        }

        if (!$scope.productInput || !$scope.productInput.category) {
            check = false;
            $scope.checkProduct.category = true;
        }

        return check;
    };

    $scope.updateProduct = function () {
        $scope.title = "Product";
        if ($scope.validateForm()) {
            let newProduct = {
                maSanPham: $scope.productInput.maSanPham,
                tenSanPham: $scope.productInput.tenSanPham,
                trangThai: $scope.productInput.trangThai,
                category: $scope.productInput.category 
            };

            $http.post("http://localhost:8080/product/update/" + $routeParams.id, newProduct).then(function (response) {
                if (response.status == 200) {
                    alert("Update success!");
                    $location.path("/product/show");
                } else {
                    alert("Update failed!");
                }
            });
        }
    };
};
