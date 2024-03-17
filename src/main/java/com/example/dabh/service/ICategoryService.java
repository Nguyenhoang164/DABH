package com.example.dabh.service;

import com.example.dabh.model.Category;
import com.example.dabh.model.Product;

public interface ICategoryService extends IGenerateService<Category> {
    void save(Category category , String name , String password);
    void delete(int id ,String name , String password);
    void update(int id ,Category category , String name , String password);
    Iterable<Category> findCategory(String keyValue);
}
