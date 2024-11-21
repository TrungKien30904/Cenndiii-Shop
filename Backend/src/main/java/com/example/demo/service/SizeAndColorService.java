package com.example.demo.service;

import com.example.demo.entity.Color;
import com.example.demo.entity.Size;
import com.example.demo.repository.ColorRepo;
import com.example.demo.repository.SizeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SizeAndColorService {
    @Autowired
    SizeRepo sizeRepo;

    @Autowired
    ColorRepo colorRepo;

    public List<Color> colorList = new ArrayList<>();
    public List<Size> sizeList = new ArrayList<>();


    public void getData(){
        colorList = colorRepo.findAll();
        sizeList = sizeRepo.findAll();
    }


    public void addSize(Size s) {
        s.setNgayTao(LocalDateTime.now());
        sizeRepo.save(s);
    }

    public Size sizeDetail(Long id) {
        return sizeRepo.findById(id).get();
    }

    public void updateSize(Long id, Size s) {
        s.setNgaySua(LocalDateTime.now());
        s.setId(id);
        sizeRepo.save(s);
    }

    // them sua chi tiet mau !
    public void addColor(Color c) {
        c.setNgayTao(LocalDateTime.now());
        colorRepo.save(c);
    }

    public Color colorDetail(Long id) {
        return colorRepo.findById(id).get();
    }


    public void updateColor(Long id, Color c) {
        c.setNgaySua(LocalDateTime.now());
        c.setId(id);
        colorRepo.save(c);
    }

}
