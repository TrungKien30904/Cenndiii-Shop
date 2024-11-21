package com.example.demo.repository;


import com.example.demo.entity.Bill;
import com.example.demo.entity.BillView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface BillRepo extends JpaRepository<Bill, Long> {
    List<Bill> findAllByTrangThai(String trangThai);

    @Query(value = "SELECT hd.id, hd.so_dien_thoai, hd.dia_chi, hd.ngay_tao, hd.ngay_sua,  hd.trang_thai, sum(hdct.tong_tien) as 'tong_tien'\n" +
            "FROM hoa_don hd\n" +
            "FULL JOIN hdct ON hd.id = hdct.id_hoa_don\n" +
            "group by hd.id, hd.so_dien_thoai, hd.dia_chi, hd.ngay_tao, hd.ngay_sua, hd.trang_thai\n" +
            "order by hd.id desc",nativeQuery = true)
    List<BillView> listAllHd();

    @Query(value = "SELECT hd.id, hd.so_dien_thoai, hd.dia_chi, hd.ngay_tao, hd.ngay_sua,  hd.trang_thai, sum(hdct.tong_tien) as 'tong_tien'\n" +
            "FROM hoa_don hd\n" +
            "FULL JOIN hdct ON hd.id = hdct.id_hoa_don\n" +
            "WHERE \n" +
            "   hd.trang_thai LIKE %:keyword%\n" +
            "   OR hd.dia_chi LIKE %:keyword%\n" +
            "   OR hd.so_dien_thoai LIKE %:keyword%\n" +
            "group by hd.id, hd.so_dien_thoai, hd.dia_chi, hd.ngay_tao, hd.ngay_sua, hd.trang_thai\n" +
            "order by hd.id desc",nativeQuery = true)
    List<BillView> listSearch(@Param("keyword") String keyword);

    @Query(value = "SELECT sum(hdct.tong_tien) as 'tong_tien'\n" +
            "            FROM hoa_don hd\n" +
            "            FULL JOIN hdct ON hd.id = hdct.id_hoa_don\n" +
            "            left JOIN khach_hang kh ON hd.id_khach_hang = kh.id\n" +
            "            where hd.ngay_tao between DATEADD(day, DATEDIFF(day, 0, GETDATE()), 0) and EOMONTH(GETDATE())",nativeQuery = true)
    BigDecimal findTotalDay();

    @Query(value = "SELECT sum(hdct.tong_tien) as 'tong_tien'\n" +
            "            FROM hoa_don hd\n" +
            "            FULL JOIN hdct ON hd.id = hdct.id_hoa_don\n" +
            "            left JOIN khach_hang kh ON hd.id_khach_hang = kh.id\n" +
            "            where hd.ngay_tao between DATEADD(month, DATEDIFF(month, 0, GETDATE()), 0) and EOMONTH(GETDATE())",nativeQuery = true)
    BigDecimal findTotalMonth();

    @Query(value = "SELECT sum(hdct.tong_tien) as 'tong_tien'\n" +
            "            FROM hoa_don hd\n" +
            "            FULL JOIN hdct ON hd.id = hdct.id_hoa_don\n" +
            "            left JOIN khach_hang kh ON hd.id_khach_hang = kh.id\n" +
            "            where hd.ngay_tao between DATEADD(year, DATEDIFF(year, 0, GETDATE()), 0) and EOMONTH(GETDATE())",nativeQuery = true)
    BigDecimal findTotalYear();
}
