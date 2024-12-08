package com.example.demo.controller;

import com.example.demo.DTO.ReqRes;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody ReqRes reqRes){
        return ResponseEntity.ok(authService.signUp(reqRes));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> sigIn(@RequestBody ReqRes reqRes){
        return ResponseEntity.ok(authService.signIn(reqRes));
    }
}
