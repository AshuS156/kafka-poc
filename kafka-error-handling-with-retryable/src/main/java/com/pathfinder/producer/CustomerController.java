package com.pathfinder.producer;


import com.pathfinder.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/publish")
public class CustomerController{

    @Autowired
    private CustomerService customerService;

    @PostMapping("/v1/customer")
    public ResponseEntity<?> publishCustomer(@RequestBody Customer customer){
        System.out.println("Received customer data: " + customer);
        try {
            customerService.processCustomerData(customer);
            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body("Customer data published successfully");
        } catch (Exception e) {
            System.out.println("Error publishing customer data: " + e.getMessage());
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body("Failed to publish customer data");
        }

    }


}
