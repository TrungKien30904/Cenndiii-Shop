package com.example.demo.controller;

import com.example.demo.entity.ProductDetails;
import com.example.demo.service.ProductDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/product-detail")
public class ProductDetailsController {


    @Autowired
    ProductDetailsService productDetailsService;

    @GetMapping("/show")
    public Map<String,Object> show() {
        productDetailsService.getData();
        Map<String,Object> productDetailsList = new HashMap<>();
        productDetailsList.put("productList",productDetailsService.productList);
        productDetailsList.put("sizeList",productDetailsService.sizeList);
        productDetailsList.put("colorList",productDetailsService.colorList);
        productDetailsList.put("listProductDetails",productDetailsService.listProductDetails);

        return productDetailsList;
    }

    @PostMapping("/add")
    public void add(@Valid @RequestBody ProductDetails ProductDetails) {
        productDetailsService.addProductDetails(ProductDetails);
    }

    @GetMapping("/show/{id}")
    public Map<String,Object> detail(@PathVariable Long id) {
        productDetailsService.getData();
        Map<String,Object> productDetail = new HashMap<>();
        productDetail.put("productDetail",productDetailsService.productDetails(id));
        productDetail.put("sizeList",productDetailsService.sizeList);
        productDetail.put("colorList",productDetailsService.colorList);
        productDetail.put("productList",productDetailsService.productList);
        return productDetail;
    }

    @PostMapping("/update/{id}")
    public void update(@Valid @RequestBody ProductDetails product, @PathVariable Long id) {
        productDetailsService.updateProductDetails(id, product);
    }
}
