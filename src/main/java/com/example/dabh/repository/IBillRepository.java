package com.example.dabh.repository;

import com.example.dabh.model.Bill;
import com.example.dabh.model.Customer;
import com.example.dabh.model.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface IBillRepository extends CrudRepository<Bill , Integer> {
    Iterable<Bill> findAllByCustomerNameCustomer(String nameCustomer);

    @Modifying
    @Query(value = "delete from bill_product where product_id = :id ",nativeQuery = true)
    void deleteBillByProducts(@Param("id") int id);
    Iterable<Bill> findAllByCustomer(Customer customer);
    void deleteAllByCustomer(Customer customer);
}
