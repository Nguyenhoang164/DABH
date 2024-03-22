package com.example.dabh.repository;

import com.example.dabh.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ICustomerRepository extends CrudRepository<Customer , Integer> {
        Optional<Customer> findCustomersByNameCustomer(String name);
}
