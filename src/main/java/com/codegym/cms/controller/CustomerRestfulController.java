package com.codegym.cms.controller;

import com.codegym.cms.model.Customer;
import com.codegym.cms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerRestfulController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/api/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.findAll();

        ResponseEntity<List<Customer>> res = new ResponseEntity(customers, HttpStatus.OK);
        return res;
    }

    @PostMapping("/api/customers")
    public ResponseEntity<Void> createCustomer(@RequestBody Customer customer) {
        customerService.save(customer);
        ResponseEntity<Void> res = new ResponseEntity(HttpStatus.OK);
        return res;
    }
}
