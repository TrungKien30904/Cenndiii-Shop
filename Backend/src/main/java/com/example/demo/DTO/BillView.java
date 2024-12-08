package com.example.demo.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface BillView {
    public Long getId();
    public String getSoDienThoai();
    public String getDiaChi();
    public String getTrangThai();
    public LocalDateTime getNgayTao();
    public LocalDateTime getNgaySua();
    public BigDecimal getTongTien();
}
