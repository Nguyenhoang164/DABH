package com.example.dabh.repository;

import com.example.dabh.model.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryRepository extends CrudRepository<Category , Integer> {
    Iterable<Category> findAllByNameCategoryContains(String keyValue);
    @Modifying
    @Query(value = "delete from Category where id not in(select id from Product )")
    void deleteProduct();
}
