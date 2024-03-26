package com.example.dabh.repository;

import com.example.dabh.model.Bill;
import com.example.dabh.model.Category;
import com.example.dabh.model.Customer;
import com.example.dabh.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.transform.sax.SAXResult;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Repository
@Transactional
public interface IProductRepository extends CrudRepository<Product , Integer> {
    @Modifying
    @Query(value = "SELECT * FROM product p where p.name_product like %:keyValue% or p.descripsion like %:keyValue% or p.price =:keyValue or p.type like %:keyValue% or p.id_user =:keyValue or p.id_category like %:keyValue%",nativeQuery = true)
    Iterable<Product> findAllByProductContaining(@Param("keyValue") String keyValue);
    Iterable<Product> findAllByCategory(Category category);
    @Query("SELECT DISTINCT p FROM Product p JOIN p.bills b WHERE b.customer = :customer")
    List<Product> findAllProductsByCustomer(@Param("customer") Customer customer);
    Iterable<Product> findAllByBills(Set<Bill> bill);
    void deleteAllByCategoryId(int id);
    @Modifying
    @Query(value ="DELETE FROM Product WHERE id = :id AND (id NOT IN (SELECT id FROM product_detail) OR id NOT IN (SELECT product_id FROM bill_product))",nativeQuery = true )
    void deleteProductById(@Param("id") int id);
    Page<Product> findAll(Pageable pageable);
}
