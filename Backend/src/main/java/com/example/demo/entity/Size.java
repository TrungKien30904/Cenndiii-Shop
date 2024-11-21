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
@Table(name = "size")
//@EntityListeners(AuditingEntityListener.class)
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Khong duoc de trong ma")
    private String maSize;

    @NotEmpty(message = "Khong duoc de trong ten")
    private String tenSize;

    @NotEmpty
    private String trangThai;

//    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime ngayTao;

    private LocalDateTime ngaySua;
}
