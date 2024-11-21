package com.example.demo.repository;


import com.example.demo.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDetailsRepo extends JpaRepository<ProductDetails,Long> {
    List<ProductDetails> findByTrangThai(String status);
}
