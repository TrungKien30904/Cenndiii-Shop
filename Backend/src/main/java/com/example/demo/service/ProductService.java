package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    public List<Category> categories = new ArrayList<>();
    public List<Product> products = new ArrayList<>();
    @Autowired
    ProductRepo productRepo;
    @Autowired
    CategoryRepo categoryRepo;

    public void getData() {
        categories = categoryRepo.findByTrangThai("Active");
        products = productRepo.findAll();
    }

    public void addProduct(Product sp) {
        sp.setNgayTao(LocalDateTime.now());
        productRepo.save(sp);
    }

    public Product productDetail(Long id) {
        return productRepo.findById(id).get();
    }

    public void updateProduct(Long id, Product sp) {
        sp.setNgaySua(LocalDateTime.now());
        sp.setId(id);
        productRepo.save(sp);
    }
}
