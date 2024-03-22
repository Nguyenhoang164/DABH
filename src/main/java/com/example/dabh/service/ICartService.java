package com.example.dabh.service;

import com.example.dabh.model.Cart;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface ICartService {
    void addToCart(HttpSession session, int idCustomer, int idProduct);

    void minusOneProduct(HttpSession session, int idCustomer, int idProduct);

    List<Cart> getCart(HttpSession session, int idCustomer);

    Cart findProductsById(List<Cart> carts, int idProduct);
}
