package com.example.demo.service;

import com.example.demo.entity.BillView;
import com.example.demo.entity.DetailedInvoice;
import com.example.demo.entity.ProductDetails;
import com.example.demo.entity.ProductView;
import com.example.demo.repository.BillRepo;
import com.example.demo.repository.DetailedInvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class DetailedInvoiceService {
    @Autowired
    DetailedInvoiceRepo detailedInvoiceRepo;

    @Autowired
    BillRepo billRepo;

    public List<BillView> billViewList = new ArrayList<>();
    public List<ProductView> productDetailsList = new ArrayList<>();
    public BigDecimal totalBill = BigDecimal.ZERO;
    public BigDecimal totalAllBill = BigDecimal.ZERO;

    public void getData(){
        billViewList = billRepo.listAllHd();
        totalAllBill = BigDecimal.ZERO;
        for (BillView hd : billViewList) {
            if (hd.getTongTien() != null) {
                totalAllBill = totalAllBill.add(hd.getTongTien());
            }
        }
    }

    public Map<String,Object> detaiedInvoice(Long id){
        Map<String,Object> data = new HashMap<>();
        totalBill = BigDecimal.ZERO;
        productDetailsList = detailedInvoiceRepo.productListView(id);
        for (ProductView pv : productDetailsList) {
            totalBill = totalBill.add(pv.getTongTien());
        }
        data.put("totalBill",totalBill);
        data.put("productDetailsList",productDetailsList);
        return data;
    }

    public void search(String keyword){
        billViewList = billRepo.listSearch(keyword);
        totalAllBill = BigDecimal.ZERO;
        for (BillView hd : billViewList) {
            if (hd.getTongTien() != null) {
                totalAllBill = totalAllBill.add(hd.getTongTien());
            }
        }
    }

}
