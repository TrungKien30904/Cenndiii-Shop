package com.example.demo.controller;

import com.example.demo.service.DetailedInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/adminuser/detailed-invoice")
public class DetailedInvoiceController {
    @Autowired
    DetailedInvoiceService detailedInvoiceService;

    @GetMapping("/show")
    public Map<String,Object> show(){
        Map<String,Object> data = new HashMap<>();
        detailedInvoiceService.getData();
        data.put("billViewList",detailedInvoiceService.billViewList);
        data.put("productDetailsList",detailedInvoiceService.productDetailsList);
        data.put("totalBill",detailedInvoiceService.totalBill);
        data.put("totalAllBill",detailedInvoiceService.totalAllBill);
        return data;
    }

    @GetMapping("/detail/{id}")
    public Map<String,Object> getDetailed(@PathVariable Long id){
        return detailedInvoiceService.detaiedInvoice(id);
    }

    @GetMapping("/search")
    public Map<String,Object> search(@RequestParam("key") String keyword){
        Map<String,Object> search = new HashMap<>();
        detailedInvoiceService.search(keyword);
        search.put("billViewList",detailedInvoiceService.billViewList);
        search.put("totalAllBill",detailedInvoiceService.totalAllBill);
        return search;
    }
}
