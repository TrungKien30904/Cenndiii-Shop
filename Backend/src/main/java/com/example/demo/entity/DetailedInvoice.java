package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hdct")
public class DetailedInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_hoa_don")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "id_ctsp")
    private ProductDetails productDetails;

    private Integer soLuongMua;

    @Digits(integer = 18,fraction = 2)
    private BigDecimal giaBan;

    @Digits(integer = 18,fraction = 2)
    private BigDecimal tongTien;

    private String trangThai;

    private LocalDateTime ngayTao;
    private LocalDateTime ngaySua;
}
