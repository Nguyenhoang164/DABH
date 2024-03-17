package com.example.dabh.repository;

import com.example.dabh.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends CrudRepository<Category , Integer> {
    Iterable<Category> findAllByNameCategoryContains(String keyValue);
}
