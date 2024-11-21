window.orderController = function ($scope, $http, $location, $routeParams) {
    // $scope.title = "Product detail"
   
    $http.get("http://localhost:8080/order/get-pdList")
    $http.get("http://localhost:8080/order/show").then(function (response) {
        if (response.status == 200) {
            $scope.cartList = response.data.cartList;
            $scope.billList = response.data.billList;
            $scope.customerList = response.data.customerList;
            $scope.productDetailsList = response.data.productDetailsList;
            $scope.total = response.data.total;
            
        } else {
            console("Ket noi that bai")
        }
    })



    $scope.getQuantity = function (id, quantity) {
        $http.get("http://localhost:8080/order/get-quantity/" + id + "/" + quantity)
        $http.get("http://localhost:8080/order/show").then(function (response) {
            if (response.status == 200) {
                $scope.cartList = response.data.cartList;
                $scope.productDetailsList = response.data.productDetailsList;
                $scope.total = response.data.total;
            } else {
                console("Ket noi that bai")
            }
        })
        
        $('#exampleModal' + id).modal('hide');
    }

    $scope.editQuantity = function (id, quantity) {
        $http.get("http://localhost:8080/order/edit-quantity/" + id + "/" + quantity)
        $http.get("http://localhost:8080/order/show").then(function (response) {
            if (response.status == 200) {
                $scope.cartList = response.data.cartList;
                $scope.productDetailsList = response.data.productDetailsList;
                $scope.total = response.data.total;
            } else {
                console("Ket noi that bai")
            }
        })
        $('#suaSoLuong' + id).modal('hide');
    }

    $scope.removeProduct = function (id) {
        $http.get("http://localhost:8080/order/remove-product/" + id)
        $http.get("http://localhost:8080/order/show").then(function (response) {
            if (response.status == 200) {
                $scope.cartList = response.data.cartList;
                $scope.productDetailsList = response.data.productDetailsList;
                $scope.total = response.data.total;

            } else {
                console("Ket noi that bai")
            }
        })
    }

    $scope.createBill = function () {
        $http.get("http://localhost:8080/order/add").then(function (response) {
            if (response.status == 200) {
                $scope.billList = response.data;
            } else {
                console("Ket noi that bai")
            }
        })
    }

    $scope.selectBill = function (id) {
        $http.get("http://localhost:8080/order/select-bill/" + id).then(function(response){
            if (response.status == 200) {
                $scope.detailBill = response.data;
            } else {
                console("Ket noi that bai")
            }
        })
    }

    $scope.validateForm = function () {
        let check = true
        $scope.checkBillSelect = {
            id: false,
            total:false
        }

        if (!$scope.detailBill || !$scope.detailBill.id) {
            check = false;
            $scope.checkBillSelect.id = true
        }

        if ($scope.total == 0.00 || !$scope.total) {
            check = false;
            $scope.checkBillSelect.total = true
        }
        return check;
    }

    $scope.pay = function () {
        if ($scope.validateForm()) {
            let bill = {
                id:$scope.detailBill.id,
                diaChi: $scope.detailBill.diaChi == "" ? null:$scope.detailBill.diaChi,
                soDienThoai:$scope.detailBill.soDienThoai == "" ? null:$scope.detailBill.soDienThoai,
            }

            $http.post("http://localhost:8080/order/pay/"+$scope.detailBill.id, bill).then(function (response) {
                if (response.status == 200) {
                    alert("Add success!")
                    $http.get("http://localhost:8080/order/show").then(function (response) {
                        if (response.status == 200) {
                            $scope.cartList = response.data.cartList;
                            $scope.billList = response.data.billList;
                            $scope.customerList = response.data.customerList;
                            $scope.productDetailsList = response.data.productDetailsList;
                            $scope.total = null;
                            $scope.detailBill = null;
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