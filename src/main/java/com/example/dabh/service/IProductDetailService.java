package com.example.dabh.service;

import com.example.dabh.model.Product;
import com.example.dabh.model.ProductDetail;

import java.util.Optional;

public interface IProductDetailService{
    Optional<ProductDetail> showProductDetailByIdProduct(int idProduct);
    void save(ProductDetail productDetail, String name , String password , int idProduct);
    void delete(int id ,String name , String password);
    void update(ProductDetail productDetail , String name , String password);
}
