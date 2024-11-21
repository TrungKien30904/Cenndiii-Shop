package com.example.demo.service;

import com.example.demo.entity.Color;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductDetails;
import com.example.demo.entity.Size;
import com.example.demo.repository.ColorRepo;
import com.example.demo.repository.ProductDetailsRepo;
import com.example.demo.repository.ProductRepo;
import com.example.demo.repository.SizeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDetailsService {

    public List<ProductDetails> listProductDetails = new ArrayList<>();
    public List<Product> productList = new ArrayList<>();
    public List<Color> colorList = new ArrayList<>();
    public List<Size> sizeList = new ArrayList<>();

    @Autowired
    ProductDetailsRepo productDetailsRepo;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    ColorRepo colorRepo;
    @Autowired
    SizeRepo sizeRepo;

    public void getData(){
        listProductDetails = productDetailsRepo.findAll();
        productList =productRepo.findByTrangThai("Active");
        colorList = colorRepo.findByTrangThai("Active");
        sizeList = sizeRepo.findByTrangThai("Active");
    }

    public void addProductDetails(ProductDetails sp) {
        sp.setNgayTao(LocalDateTime.now());
        productDetailsRepo.save(sp);
    }

    public ProductDetails productDetails(Long id) {
        return productDetailsRepo.findById(id).get();
    }

    public void updateProductDetails(Long id, ProductDetails sp) {
        sp.setNgaySua(LocalDateTime.now());
        sp.setId(id);
        productDetailsRepo.save(sp);
    }
}
