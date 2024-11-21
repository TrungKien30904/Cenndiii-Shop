package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/show")
    public List<Category> show(Model m) {
        categoryService.getData();
        return categoryService.categories;
    }

    @PostMapping("/add")
    public void add(@Valid @RequestBody Category category) {
        categoryService.addCategory(category);
    }

    @GetMapping("/show/{id}")
    public Category detail(@PathVariable Long id) {
        return categoryService.categoryDetail(id);
    }

    @PostMapping("/update/{id}")
    public void update(@Valid @RequestBody Category category, @PathVariable Long id) {
        categoryService.updateCategory(id, category);
    }
}
