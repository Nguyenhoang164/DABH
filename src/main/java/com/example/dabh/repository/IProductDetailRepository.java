package com.example.dabh.repository;

import com.example.dabh.model.ProductDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductDetailRepository extends CrudRepository<ProductDetail , Integer> {
}
