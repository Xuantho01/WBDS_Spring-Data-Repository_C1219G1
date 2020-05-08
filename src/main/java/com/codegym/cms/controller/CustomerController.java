package com.codegym.cms.controller;

import com.codegym.cms.model.Customer;
import com.codegym.cms.model.CustomerType;
import com.codegym.cms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
        // Before Advice
        // logger.log(...)
        ModelAndView modelAndView = new ModelAndView("customer-list");

        List<Customer> customers = customerService.findAll(); // --->throw Exception. After Throwing
        // logger.log(...)

        modelAndView.addObject("customerList", customers);
        // logger.log(...)
        return  modelAndView; // After Returning
    }

    @GetMapping("/customer-add")
    public ModelAndView getCustomerForm() {
        ModelAndView mv = new ModelAndView("customer-add");
        List<CustomerType> customerTypes = customerService.findAllCustomerType();

        mv.addObject("customer", new Customer());
        mv.addObject("customerTypes", customerTypes);

        return mv;
    }

    @PostMapping("/customer-add")
    public String addCustomer(@ModelAttribute Customer customer, BindingResult bindingResult) {
        customerService.save(customer);
        return "redirect:/customer-list";
    }

    /*@PostMapping("/customer-add")
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
    }*/
}
