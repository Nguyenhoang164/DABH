package com.example.dabh.service;

import com.example.dabh.model.Customer;

import java.util.Optional;

public interface ICustomerService extends IGenerateService<Customer> {
    Optional<Customer> findCustomer(String name);
}
