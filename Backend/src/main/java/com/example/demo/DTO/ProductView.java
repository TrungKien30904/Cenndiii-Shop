package com.example.demo.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ProductView {
    public Long getIdHoaDon();
    public String getTenSanPham();
    public Integer getSoLuongMua();
    public BigDecimal getGiaBan();
    public BigDecimal getTongTien();
    public String getTrangThai();
    public LocalDateTime getNgayTao();
    public LocalDateTime getNgaySua();
}
