package com.example.demo.controller;

import com.example.demo.entity.Size;
import com.example.demo.service.SizeAndColorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/size")
public class SizeController {
    @Autowired
    SizeAndColorService sizeAndColorService;


    @GetMapping("/show")
    public List<Size> show(Model m) {
        sizeAndColorService.getData();
        return sizeAndColorService.sizeList;
    }

    @PostMapping("/add")
    public void add(@Valid @RequestBody Size size) {
        sizeAndColorService.addSize(size);
    }

    @GetMapping("/show/{id}")
    public Size detail(@PathVariable Long id) {
        return sizeAndColorService.sizeDetail(id);
    }

    @PostMapping("/update/{id}")
    public void update(@Valid @RequestBody Size size, @PathVariable Long id) {
        sizeAndColorService.updateSize(id, size);
    }

}
