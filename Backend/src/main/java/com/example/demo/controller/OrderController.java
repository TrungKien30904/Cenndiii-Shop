package com.example.demo.controller;

import com.example.demo.entity.Bill;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    Boolean isReset = false;
    @GetMapping("/show")
    public Map<String, Object> show() {
        orderService.getData();
        if (isReset){
            orderService.getDataProductDetails();
            isReset = false;
        }
        Map<String, Object> data = new HashMap<>();
        data.put("cartList",orderService.cartList);
        data.put("billList",orderService.billList);
        data.put("customerList",orderService.customerList);
        data.put("total",orderService.total);
        data.put("productDetailsList",orderService.productDetailsList);
        return data;
    }


    @GetMapping("/get-pdList")
    public void getPDList(){
        orderService.clearCart();
        isReset = true;
    }
    @GetMapping("/get-quantity/{id}/{quantity}")
    public void getQuantity(@PathVariable Long id,@PathVariable int quantity){
        orderService.getQuantity(quantity,id);
    }

    @GetMapping("/remove-product/{id}")
    public void removeProduct(@PathVariable Long id){
        orderService.removeProductInCart(id);
    }

    @GetMapping("/edit-quantity/{id}/{quantity}")
    public void editQuantity(@PathVariable Long id,@PathVariable int quantity){
        orderService.editQuantity(quantity,id);
    }

    @GetMapping("/select-bill/{id}")
    public Bill selectBill(@PathVariable Long id){
        return orderService.selectBill(id);
    }

    @GetMapping("/add")
    public List<Bill> addBill(){
        return orderService.add();
    }

    @PostMapping("/pay/{id}")
    public void pay(@PathVariable Long id, @RequestBody Bill bill){
        orderService.pay(id,bill);
    }


}
