package com.example.demo.repository;

import com.example.demo.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColorRepo extends JpaRepository<Color,Long> {
    List<Color> findByTrangThai(String status);
}
