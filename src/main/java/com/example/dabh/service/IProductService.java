package com.example.dabh.service;

import com.example.dabh.model.Product;
import com.example.dabh.model.ProductForm;
import com.example.dabh.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IProductService extends IGenerateService<Product> {
    void save(ProductForm productForm , String name , String password , int idCategory);
    void delete(int id ,String name , String password);
    void update(int id ,ProductForm productForm , String name , String password);
    Iterable<Product> findProduct(String keyValue);
    Page<Product> findAll(Pageable pageable);


}
