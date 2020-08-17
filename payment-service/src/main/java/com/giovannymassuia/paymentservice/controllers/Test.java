package com.giovannymassuia.paymentservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class Test {
    
    @GetMapping
    public ResponseEntity get() {
        
        return ResponseEntity.ok("Payment Service Working...");
    }
    
}
