window.detailedInvoiceController = function ($scope, $http, $location, $routeParams) {
    $scope.title = "Product detail"
    $http.get("http://localhost:8080/detailed-invoice/show").then(function (response) {
        if (response.status == 200) {
            $scope.productDetailsList = response.data.productDetailsList;
            $scope.billViewList = response.data.billViewList;
            $scope.totalBill = response.data.totalBill;
            $scope.totalAllBill = response.data.totalAllBill;
        } else {
            console("Ket noi that bai")
        }
    })


    $scope.detail = function(id){
        $http.get("http://localhost:8080/detailed-invoice/detail/"+id).then(function (response) {
            if (response.status == 200) {
                $scope.productDetailsList = response.data.productDetailsList;
                $scope.totalBill = response.data.totalBill;
            } else {
                console("Ket noi that bai")
            }
        })
    }
    
    $scope.search = function(keyWord){
        console.log("abc");
        
        $http.get("http://localhost:8080/detailed-invoice/search?key="+keyWord).then(function (response) {
            if (response.status == 200) {
                $scope.billViewList = response.data.billViewList;
                $scope.totalAllBill = response.data.totalAllBill;
            } else {
                console("Ket noi that bai")
            }
        })
    }
}