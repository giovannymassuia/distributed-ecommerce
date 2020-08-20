package com.giovannymassuia.catalogservice.controllers;

import com.giovannymassuia.catalogservice.producers.OrderProducer;
import com.giovannymassuia.kafka.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.*;
import java.util.UUID;

@RestController
@RequestMapping("cart")
@RequiredArgsConstructor
public class CartController {
    
    private final OrderProducer orderProducer;
    
    @GetMapping
    public ResponseEntity get() {
        return ResponseEntity.ok("Cart");
    }
    
    @PostMapping
    public ResponseEntity post() {
        
        orderProducer.produce(Order.newBuilder()
                .setOrderId(UUID.randomUUID().toString())
                .setCreatedAt(LocalDateTime.now().atOffset(ZoneOffset.UTC).toInstant().toEpochMilli())
                .build());
        
        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }
    
}
