package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;

    public List<Category> categories = new ArrayList<>();

    public void getData(){
        categories = categoryRepo.findAll();
    }

    public void addCategory(Category dm) {
        dm.setNgayTao(LocalDateTime.now());
        categoryRepo.save(dm);
    }

    public Category categoryDetail(Long id) {
        return categoryRepo.findById(id).get();
    }

    public void updateCategory(Long id, Category dm) {
        dm.setNgaySua(LocalDateTime.now());
        dm.setId(id);
        categoryRepo.save(dm);
    }
}
