package com.example.dabh.repository;

import com.example.dabh.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryRepository extends CrudRepository<Category , Integer> {
    Iterable<Category> findAllByNameCategoryContains(String keyValue);
}
