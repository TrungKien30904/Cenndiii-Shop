window.productDController = function ($scope, $http, $location, $routeParams) {
    $scope.title = "Product detail"
    $http.get("http://localhost:8080/product-detail/show").then(function (response) {
        if (response.status == 200) {
            $scope.productList = response.data.productList;
            $scope.sizeList = response.data.sizeList;
            $scope.colorList = response.data.colorList;
            $scope.listProductDetails = response.data.listProductDetails;

        } else {
            console("Ket noi that bai")
        }
    })

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
        // console.log($scope.productDetailInput.product);
        
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

    $scope.addProductDetail = function () {
        if ($scope.validateForm()) {
            let newProduct = {
                product: $scope.productDetailInput.product,
                color: $scope.productDetailInput.color,
                size: $scope.productDetailInput.size,
                giaBan: $scope.productDetailInput.giaBan,
                soLuongTon: $scope.productDetailInput.soLuongTon,
                trangThai: $scope.productDetailInput.trangThai,
            }

            $http.post("http://localhost:8080/product-detail/add", newProduct).then(function (response) {
                if (response.status == 200) {
                    alert("Add success!")
                    $http.get("http://localhost:8080/product-detail/show").then(function (response) {
                        if (response.status == 200) {
                            $scope.productList = response.data.productList;
                            $scope.sizeList = response.data.sizeList;
                            $scope.colorList = response.data.colorList;
                            $scope.listProductDetails = response.data.listProductDetails;

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