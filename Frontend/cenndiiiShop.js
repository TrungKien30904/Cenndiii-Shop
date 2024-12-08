let myApp =  angular.module("myApp",["ngRoute"])

myApp.config(function($routeProvider){
    $routeProvider
    
    .when(
        "/home",
        {
            templateUrl:"views/home.html",
            controller:homeController
        }
    )
    .when(
        "/size/show",
        {
            templateUrl:"views/size.html",
            controller:sizeController
        }
    )
    .when(
        "/size/detail/:id",
        {
            templateUrl:"views/sizeDetail.html",
            controller:updateSizeController
        }
    )
    .when(
        "/color/show",
        {
            templateUrl:"views/color.html",
            controller:colorController
        }
    )
    .when(
        "/color/detail/:id",
        {
            templateUrl:"views/colorDetail.html",
            controller:updateColorController
        }
    )
    .when(
        "/category/show",
        {
            templateUrl:"views/category.html",
            controller:categoryController
        }
    )
    .when(
        "/category/detail/:id",
        {
            templateUrl:"views/categoryDetail.html",
            controller:updateCategoryController
        }
    )
    .when(
        "/product/show",
        {
            templateUrl:"views/product.html",
            controller:productController
        }
    )
    .when(
        "/product/detail/:id",
        {
            templateUrl:"views/productDetail.html",
            controller:updateProductController
        }
    )
    .when(
        "/product-detail/show",
        {
            templateUrl:"views/product-detail.html",
            controller:productDController
        }
    )
    .when(
        "/product-detail/detail/:id",
        {
            templateUrl:"views/pdDetail.html",
            controller:updateProductDController
        }
    )
    .when(
        "/order/show",
        {
            templateUrl:"views/order.html",
            controller:orderController
        }
    )
    .when(
        "/detailed-invoice/show",
        {
            templateUrl:"views/detailedInvoice.html",
            controller:detailedInvoiceController
        }
    )
    .otherwise({
        redirectTo:"/home"
    })
})

