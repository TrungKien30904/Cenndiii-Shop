package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "san_pham")
//@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String maSanPham;

    @NotEmpty
    private String tenSanPham;

    @NotEmpty
    private String trangThai;

//    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime ngayTao;
    private LocalDateTime ngaySua;

    @ManyToOne
    @JoinColumn(name = "id_danh_muc")
    private Category category;

}
