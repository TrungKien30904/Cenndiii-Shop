package com.example.demo.controller;

import com.example.demo.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/public/home")
public class HomeController {
    @Autowired
    HomeService homeService;

    @GetMapping("/show")
    public Map<String,Object> show(){
        Map<String,Object> data = new HashMap<>();
        homeService.getData();
        data.put("dayRevenue",homeService.dayRevenue);
        data.put("monthlyRevenue",homeService.monthlyRevenue);
        data.put("revenueByYear",homeService.revenueByYear);
        return data;
    }
}
