package com.codegym.cms.controller;

import com.codegym.cms.model.Customer;
import com.codegym.cms.model.CustomerType;
import com.codegym.cms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer-list")
    public ModelAndView getCustomerList() {
        ModelAndView modelAndView = new ModelAndView("customer-list");

        List<Customer> customers = customerService.findAll();

        modelAndView.addObject("customerList", customers);
        return  modelAndView;
    }

    @GetMapping("/customer-add")
    public ModelAndView getCustomerForm() {
        ModelAndView mv = new ModelAndView("customer-add");
        List<CustomerType> customerTypes = customerService.findAllCustomerType();

        mv.addObject("customerTypes", customerTypes);

        return mv;
    }

    @PostMapping("/customer-add")
    public String addCustomer(@RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestParam("customerType") Long customerTypeId) {

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);

        CustomerType customerType = new CustomerType();
        customerType.setId(customerTypeId);

        customer.setCustomerType(customerType);

        customerService.save(customer);

        return "redirect:/customer-list";
    }
}
