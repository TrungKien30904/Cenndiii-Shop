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
@Table(name = "mau_sac")
//@EntityListeners(AuditingEntityListener.class)
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String maMau;

    @NotEmpty
    private String tenMau;

    @NotEmpty
    private String trangThai;

//    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime ngayTao;

    private LocalDateTime ngaySua;
}
