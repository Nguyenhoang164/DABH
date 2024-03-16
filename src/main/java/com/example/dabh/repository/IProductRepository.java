package com.example.dabh.repository;

import com.example.dabh.model.Category;
import com.example.dabh.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepository extends CrudRepository<Product , Integer> {
    Iterable<Product> findAllByNameProductContaining(String keyValue);
    Iterable<Product> findAllByCategory(Category category);
}
