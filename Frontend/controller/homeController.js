window.homeController = function($scope,$http){
    $http.get("http://localhost:8080/home/show").then(function (response) {
        if (response.status == 200) {
            $scope.dayRevenue = response.data.dayRevenue == null ? 0 :response.data.dayRevenue;
            $scope.monthlyRevenue = response.data.monthlyRevenue == null ? 0 :response.data.monthlyRevenue;
            $scope.revenueByYear = response.data.revenueByYear == null ? 0 : response.data.revenueByYear;
        } else {
            console("Ket noi that bai")
        }
    })
}