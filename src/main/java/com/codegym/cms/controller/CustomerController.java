package com.codegym.cms.controller;

import com.codegym.cms.model.Customer;
import com.codegym.cms.model.CustomerType;
import com.codegym.cms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public String getHomepage() {
        return "index";
    }

    @GetMapping("/customer-list")
    public ModelAndView getCustomerList(@CookieValue(value = "starredCustomer", defaultValue = "") String starredCustomer) {
        // Before Advice
        // logger.log(...)
        ModelAndView modelAndView = new ModelAndView("customer-list");

        String[] customerIds = starredCustomer.split("\\|");

        List<Customer> customers = customerService.findAll(); // --->throw Exception. After Throwing
        boolean[] isStarred = new boolean[customers.size()];
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            for (String customerId : customerIds) {
                if (!customerId.isEmpty() && customer.getId() == Long.parseLong(customerId)) {
                    isStarred[i] = true;
                }
            }
        }
        // logger.log(...)

        modelAndView.addObject("customerList", customers);
        modelAndView.addObject("isStarred", isStarred);
        // logger.log(...)
        return  modelAndView; // After Returning
    }

    @PostMapping("/customer-list")
    public ModelAndView saveStarredCustomer(@RequestParam String starredCustomer, HttpServletResponse res) {
        System.out.println(starredCustomer);
        String savedCookieValue = starredCustomer.toString().replace(',','|');

        res.addCookie(new Cookie("starredCustomer", savedCookieValue));

        ModelAndView modelAndView = new ModelAndView("redirect:/customer-list");
        return  modelAndView;
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
