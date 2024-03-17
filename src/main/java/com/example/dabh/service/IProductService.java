package com.example.dabh.service;

import com.example.dabh.model.Product;
import com.example.dabh.model.Users;

public interface IProductService extends IGenerateService<Product> {
    void save(Product product , String name , String password , int idCategory);
    void delete(int id ,String name , String password);
    void update(int id ,Product product , String name , String password , int idCategory);
    Iterable<Product> findProduct(String keyValue);
}
