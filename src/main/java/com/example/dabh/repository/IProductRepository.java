package com.example.dabh.repository;

import com.example.dabh.model.Category;
import com.example.dabh.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IProductRepository extends CrudRepository<Product , Integer> {
    Iterable<Product> findAllByNameProductContaining(String keyValue);
    Iterable<Product> findAllByCategory(Category category);
    void deleteAllByCategoryId(int id);
//@Modifying
//@Query(value = "select * from Product p order by p.nameProduct asc ",nativeQuery = true)
//Page<Product> showProductsByASC(Pageable pageable);
//    @Modifying
//    @Query(value = "select * from Product p order by p.nameProduct desc ",nativeQuery = true)
//    Page<Product> showProductsByDESC(Pageable pageable);
    void deleteProductById(int id);
    Page<Product> findAll(Pageable pageable);
}
