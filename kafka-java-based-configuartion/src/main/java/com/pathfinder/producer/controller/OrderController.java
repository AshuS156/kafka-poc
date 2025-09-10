package com.pathfinder.producer.controller;

import com.pathfinder.producer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController{

    @Autowired
    OrderService orderService;

    @GetMapping("/publishOrderData/{message}")
    public ResponseEntity<?> publishOrderData(@PathVariable("message") String orderData){
        System.out.println("Publishing order data: " + orderData);
        try {
            orderService.publishOrderData(orderData);
            return ResponseEntity.status(200).body("Order data published successfully: " + orderData);
        } catch (Exception e) {
            System.err.println("Error publishing order data: " + e.getMessage());
            return ResponseEntity.status(500).body("Failed to publish order data: " + e.getMessage());
        }
    }
}
