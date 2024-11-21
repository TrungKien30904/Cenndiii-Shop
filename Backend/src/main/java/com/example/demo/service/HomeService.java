package com.example.demo.service;

import com.example.demo.repository.BillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class HomeService {
    @Autowired
    BillRepo billRepo;

    public BigDecimal dayRevenue = BigDecimal.ZERO;
    public BigDecimal monthlyRevenue= BigDecimal.ZERO;
    public BigDecimal revenueByYear= BigDecimal.ZERO;

    public void getData(){
        dayRevenue = billRepo.findTotalDay() ;
//                == null ? BigDecimal.ZERO : billRepo.findTotalDay();
        monthlyRevenue= billRepo.findTotalMonth();
//                == null ? BigDecimal.ZERO : billRepo.findTotalDay();
        revenueByYear= billRepo.findTotalYear();
//                == null ? BigDecimal.ZERO : billRepo.findTotalDay();
    }
}
