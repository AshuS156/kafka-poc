package com.pathfinder.publisher.controller;

import com.pathfinder.publisher.Service.PublisherOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController{

    @Autowired
    PublisherOrderService publisherService;
    @GetMapping("/publishOrder")
    public String test(){
        try{
            for(int i= 0 ; i < 50; i++ ) {
                publisherService.publishOrder("Test message " + "Test message"+i);
            }
            return "Test messages published successfully";
        }catch (Exception e) {
            System.err.println("Error publishing test messages: " + e.getMessage());
            return "Failed to publish test messages: " + e.getMessage();
        }
    }
}

