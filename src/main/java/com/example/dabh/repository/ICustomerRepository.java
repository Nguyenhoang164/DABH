package com.example.dabh.repository;

import com.example.dabh.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends CrudRepository<Customer , Integer> {

     Customer findCustomerById(int id);
}
