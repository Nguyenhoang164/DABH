package com.example.dabh.service;

import com.example.dabh.model.CartItem;

public interface ICartService extends IGenerateService<CartItem> {

    Iterable<CartItem> findCartCustomer(int id);

    void addProductsToCart(CartItem cartItem, int id_Customer, int id_Product);

    void deleteProductCart(int idCustomer, int idProduct);
}
