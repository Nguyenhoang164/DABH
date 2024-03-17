package com.example.dabh.repository;

import com.example.dabh.model.Category;
import com.example.dabh.model.Customer;
import com.example.dabh.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends CrudRepository<Product , Integer> {
    Iterable<Product> findAllByNameProductContaining(String keyValue);
    Iterable<Product> findAllByCategory(Category category);

    Product findProductById(int id);

}
