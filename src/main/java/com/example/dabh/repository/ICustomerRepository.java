package com.example.dabh.repository;

import com.example.dabh.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerRepository extends CrudRepository<Customer , Integer> {
}
