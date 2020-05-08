package com.codegym.cms.service;

import com.codegym.cms.model.Customer;
import com.codegym.cms.model.CustomerType;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    Customer findById(Long id);

    void save(Customer customer);

    void remove(Long id);

    List<CustomerType> findAllCustomerType();
    CustomerType findCustomerTypeById(Long id);
}
