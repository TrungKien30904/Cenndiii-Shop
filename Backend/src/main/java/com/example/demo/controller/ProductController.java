package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/show")
    public Map<String,Object> show() {
        productService.getData();
        Map<String,Object> productList = new HashMap<>();
        productList.put("products",productService.products);
        productList.put("categories",productService.categories);
        return productList;
    }

    @PostMapping("/add")
    public void add(@Valid @RequestBody Product product) {
        productService.addProduct(product);
    }

    @GetMapping("/show/{id}")
    public Map<String,Object> detail(@PathVariable Long id) {
        productService.getData();
        Map<String,Object> productDetail = new HashMap<>();
        productDetail.put("productDetail",productService.productDetail(id));
        productDetail.put("categories",productService.categories);
        return productDetail;
    }

    @PostMapping("/update/{id}")
    public void update(@Valid @RequestBody Product product, @PathVariable Long id) {
        productService.updateProduct(id, product);
    }


}
