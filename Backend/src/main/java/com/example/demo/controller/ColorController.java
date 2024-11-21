package com.example.demo.controller;

import com.example.demo.entity.Color;
import com.example.demo.service.SizeAndColorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/color")
public class ColorController {
    @Autowired
    SizeAndColorService sizeAndColorService;


    @GetMapping("/show")
    public List<Color> show(Model m) {
        sizeAndColorService.getData();
        return sizeAndColorService.colorList;
    }

    @PostMapping("/add")
    public void add(@Valid @RequestBody Color color) {
        sizeAndColorService.addColor(color);
    }

    @GetMapping("/show/{id}")
    public Color detail(@PathVariable Long id) {
        return sizeAndColorService.colorDetail(id);
    }

    @PostMapping("/update/{id}")
    public void update(@Valid @RequestBody Color color, @PathVariable Long id) {
        sizeAndColorService.updateColor(id, color);
    }
}
