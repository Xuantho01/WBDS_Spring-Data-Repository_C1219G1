package com.codegym.cms.formatter;

import com.codegym.cms.model.CustomerType;
import com.codegym.cms.service.CustomerService;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CustomerTypeFormatter implements Formatter<CustomerType> {

    private CustomerService customerService;

    public CustomerTypeFormatter(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public CustomerType parse(String text, Locale locale) throws ParseException {
        return customerService.findCustomerTypeById(Long.getLong(text));
    }

    @Override
    public String print(CustomerType object, Locale locale) {
        return "[" + object.getId() + ", " +object.getName() + "]";
    }
}
