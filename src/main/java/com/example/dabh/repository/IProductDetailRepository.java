package com.example.dabh.repository;

import com.example.dabh.model.Product;
import com.example.dabh.model.ProductDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IProductDetailRepository extends CrudRepository<ProductDetail , Integer> {
    Optional<ProductDetail> findProductDetailByProduct(Product product);
    void deleteProductDetailByProduct(Product product);
}
