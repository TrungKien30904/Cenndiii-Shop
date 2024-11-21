window.updateProductDController = function ($scope, $http, $location, $routeParams) {
    $http.get("http://localhost:8080/product-detail/show/" + $routeParams.id).then(function (response) {
        $scope.title = "Product detail";
        
        $scope.productList = response.data.productList;
        $scope.sizeList = response.data.sizeList;
        $scope.colorList = response.data.colorList;

        $scope.productDetailInput = {
            giaBan: response.data.productDetail.giaBan,
            soLuongTon: response.data.productDetail.soLuongTon,
            trangThai: response.data.productDetail.trangThai,
        };
        
        let selectedProduct = $scope.productList.find(product => product.tenSanPham === response.data.productDetail.product.tenSanPham);
        let selectedSize = $scope.sizeList.find(size => size.tenSize === response.data.productDetail.size.tenSize);
        let selectedColor = $scope.colorList.find(color => color.tenMau === response.data.productDetail.color.tenMau);

        if (selectedProduct) {
            $scope.productDetailInput.product = selectedProduct;
        }
        if (selectedSize) {
            $scope.productDetailInput.size = selectedSize;
        }
        if (selectedColor) {
            $scope.productDetailInput.color = selectedColor;
        }
    });

    $scope.validateForm = function () {
        let check = true
        $scope.checkProductDetail = {
            product: false,
            color: false,
            size: false,
            giaBan: false,
            soLuongTon: false,
            trangThai: false,
        }

        if (!$scope.productDetailInput || !$scope.productDetailInput.product) {
            check = false;
            $scope.checkProductDetail.product = true
        }

        if (!$scope.productDetailInput || !$scope.productDetailInput.color) {
            check = false;
            $scope.checkProductDetail.color = true
        }

        if (!$scope.productDetailInput || !$scope.productDetailInput.size) {
            check = false;
            $scope.checkProductDetail.size = true
        }

        if (!$scope.productDetailInput || !$scope.productDetailInput.giaBan) {
            check = false;
            $scope.checkProductDetail.giaBan = true
        }

        if (!$scope.productDetailInput || !$scope.productDetailInput.soLuongTon) {
            check = false;
            $scope.checkProductDetail.soLuongTon = true
        }

        if (!$scope.productDetailInput || !$scope.productDetailInput.trangThai) {
            check = false;
            $scope.checkProductDetail.trangThai = true
        }

        return check;
    }

    $scope.updateProductDetail = function () {
        if ($scope.validateForm()) {
            let newProduct = {
                product: $scope.productDetailInput.product,
                color: $scope.productDetailInput.color,
                size: $scope.productDetailInput.size,
                giaBan: $scope.productDetailInput.giaBan,
                soLuongTon: $scope.productDetailInput.soLuongTon,
                trangThai: $scope.productDetailInput.trangThai,
            }

            $http.post("http://localhost:8080/product-detail/update/" + $routeParams.id, newProduct).then(function (response) {
                if (response.status == 200) {
                    alert("Update success!");
                    $location.path("/product-detail/show");
                } else {
                    alert("Update failed!");
                }
            });
        }
    };
};
