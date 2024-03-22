package com.example.dabh.service.impl;

import com.example.dabh.model.Customer;
import com.example.dabh.repository.ICustomerRepository;
import com.example.dabh.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;
    @Override
    public Optional<Customer> findCustomer(String name) {
        return customerRepository.findCustomersByNameCustomer(name);
    }

    @Override
    public Iterable<Customer> showAll() {
        return null;
    }

    @Override
    public Optional<Customer> findObjectById(int id) {
        return customerRepository.findById(id);
    }
}
