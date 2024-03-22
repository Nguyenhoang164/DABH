package com.example.dabh.repository;

import com.example.dabh.model.Bill;
import com.example.dabh.model.Customer;
import com.example.dabh.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface IBillRepository extends CrudRepository<Bill , Integer> {
    Iterable<Bill> findAllByCustomerNameCustomer(String nameCustomer);

    Iterable<Bill> findAllByCustomer(Customer customer);
    void deleteAllByCustomer(Customer customer);
}
