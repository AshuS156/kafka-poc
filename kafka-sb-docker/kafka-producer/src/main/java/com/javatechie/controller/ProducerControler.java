package com.javatechie.controller;

import com.javatechie.dto.Customer;
import com.javatechie.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProducerControler{

    @Autowired
    private ProducerService producerService;

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message){
        try {
            for (int i = 0; i < 10000; i++) {
                producerService.sendMessage(message + " " + i);
            }
            return ResponseEntity.ok("Message published successfully:...... ");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error publishing message: " + e.getMessage());
        }
    }


    @PostMapping("/publish/customer")
    public ResponseEntity<?> publishCustomerMessage(@RequestBody Customer customer){
        try {
            producerService.publishCustomer(customer);
            return ResponseEntity.ok("Customer message published successfully: " + customer.toString());
        }catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error publishing customer message: " + e.getMessage());
        }

    }


}
