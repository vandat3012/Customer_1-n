package com.example.practice2.model.service.customer;


import com.example.practice2.model.Customer;

import java.util.List;

public interface ICustomerService {
        List<Customer> findAll();
        void save(Customer c);
        List<Customer> findByName(String name);
        void update(Customer customer);
        Customer findById (int id);
    }

