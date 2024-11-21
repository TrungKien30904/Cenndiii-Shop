package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepo customerRepo;

    public List<Customer> customer = new ArrayList<>();

    public void getData(){
        customer = customerRepo.findAll();
    }

    public void addCustomer(Customer dm) {
        dm.setNgayTao(LocalDateTime.now());
        customerRepo.save(dm);
    }

    public Customer khahcHangDetail(Long id) {
        return customerRepo.findById(id).get();
    }

    public void updateCustomer(Long id, Customer dm) {
        dm.setNgaySua(LocalDateTime.now());
        dm.setId(id);
        customerRepo.save(dm);
    }
}
