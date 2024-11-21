package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.BillRepo;
import com.example.demo.repository.CustomerRepo;
import com.example.demo.repository.DetailedInvoiceRepo;
import com.example.demo.repository.ProductDetailsRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    public Bill billD = null;
    public List<ProductDetails> productDetailsList = new ArrayList<>();
    public List<ProductDetails> productDetailsListCopy = new ArrayList<>();
    public List<Bill> billList = new ArrayList<>();
    public List<Customer> customerList = new ArrayList<>();
    public List<Cart> cartList = new ArrayList<>();
    public BigDecimal total = BigDecimal.ZERO;
    @Autowired
    ProductDetailsRepo productDetailsRepo;
    @Autowired
    BillRepo billRepo;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    DetailedInvoiceRepo detailedInvoiceRepo;

    @PostConstruct
    public void getDataProductDetails() {
        productDetailsList = productDetailsRepo.findByTrangThai("Active");
        productDetailsListCopy = productDetailsRepo.findByTrangThai("Active");
    }

    public void getData() {
        billList = billRepo.findAllByTrangThai("Pending payment");
        customerList = customerRepo.findByTrangThai("Active");
    }

    public void getQuantity(int sl, Long id) {

        boolean check = false;

        for (ProductDetails c : productDetailsList) {
            if (c.getId().equals(id)) {
                if (sl <= c.getSoLuongTon()) {
                    Cart cart = new Cart(c.getId(), c.getProduct().getTenSanPham(), c.getColor().getTenMau(), c.getSize().getTenSize(), c.getGiaBan(), sl);
                    for (Cart g : cartList) {
                        if (g.getId().equals(cart.getId())) {
                            g.setSoLuong(g.getSoLuong() + cart.getSoLuong());
                            c.setSoLuongTon(c.getSoLuongTon() - sl);
                            total = total.add(BigDecimal.valueOf(g.getSoLuong()).multiply(g.getGiaBan()));
                            check = true;
                        }
                    }
                    if (!check) {
                        c.setSoLuongTon(c.getSoLuongTon() - sl);
                        cartList.add(cart);
                        total = total.add(BigDecimal.valueOf(cart.getSoLuong()).multiply(cart.getGiaBan()));
                    }
                }
            }
        }
    }

    public void editQuantity(int sl, Long id) {
        total = BigDecimal.ZERO;
        total = cartList.stream()
                .map(gh -> BigDecimal.valueOf(gh.getSoLuong()).multiply(gh.getGiaBan()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        for (ProductDetails pd : productDetailsListCopy) {
            if (pd.getId().equals(id)) {
                if (sl <= pd.getSoLuongTon()) {
                    for (Cart gh : cartList) {
                        if (gh.getId().equals(id)) {
                            for (ProductDetails c : productDetailsList) {
                                if (c.getId().equals(id)) {
                                    c.setSoLuongTon(c.getSoLuongTon() + (gh.getSoLuong()) - sl);
                                }
                            }
                            gh.setSoLuong(sl);
                        }
                    }
                }
            }
        }

        total = cartList.stream()
                .map(gh -> BigDecimal.valueOf(gh.getSoLuong()).multiply(gh.getGiaBan()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void removeProductInCart(Long id) {
        Cart rGh = new Cart();
        for (ProductDetails c : productDetailsList) {
            if (c.getId().equals(id)) {
                for (Cart gh : cartList) {
                    if (gh.getId().equals(id)) {
                        c.setSoLuongTon(c.getSoLuongTon() + gh.getSoLuong());
                        rGh = gh;
                        total = total.subtract(BigDecimal.valueOf(gh.getSoLuong()).multiply(gh.getGiaBan()));
                    }
                }
            }
        }
        cartList.remove(rGh);
    }

    public List<Bill> add() {
        Bill bill = new Bill();
        bill.setTrangThai("Pending payment");
        bill.setNgayTao(LocalDateTime.now());
        billRepo.save(bill);
        return billRepo.findAllByTrangThai("Pending payment");
    }

    public void pay(Long id, Bill bill) {
        if (id != null) {
            bill.setTrangThai("Paid");
            bill.setNgaySua(LocalDateTime.now());
            billRepo.save(bill);
            for (Cart gh : cartList) {
                DetailedInvoice detailedInvoce = new DetailedInvoice();
                detailedInvoce.setBill(bill);
                detailedInvoce.setProductDetails(productDetailsRepo.findById(gh.id).get());
                detailedInvoce.setSoLuongMua(gh.getSoLuong());
                detailedInvoce.setGiaBan(gh.getGiaBan());
                detailedInvoce.setTrangThai("Active");
                detailedInvoce.setTongTien(BigDecimal.valueOf(gh.getSoLuong()).multiply(gh.getGiaBan()));
                detailedInvoce.setNgayTao(LocalDateTime.now());
                detailedInvoiceRepo.save(detailedInvoce);
            }
            productDetailsRepo.saveAll(productDetailsList);
            clearData();
        }
    }

    public void clearData() {
        cartList.clear();
        billD = null;
        total = BigDecimal.ZERO;
    }

    public void clearCart() {
        for (ProductDetails productDetails : productDetailsList) {
            for (Cart cart : cartList) {
                if (productDetails.getId().equals(cart.id)) {
                    productDetails.setSoLuongTon(productDetails.getSoLuongTon() + cart.getSoLuong());
                }
            }
        }
        clearData();
    }

    public Bill selectBill(Long id) {
        cartList.clear();
        return billRepo.findById(id).get();
    }

    public void huyHd(Long id) {
        Bill h = billRepo.findById(id).orElse(null);
        if (h != null) {
            h.setTrangThai("Cancel");
            billRepo.save(h);
            clearData();
        }
    }
}
