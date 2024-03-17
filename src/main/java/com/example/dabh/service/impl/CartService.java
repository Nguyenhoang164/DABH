package com.example.dabh.service.impl;

import com.example.dabh.model.CartItem;
import com.example.dabh.model.Customer;
import com.example.dabh.model.Product;
import com.example.dabh.repository.ICartRepository;
import com.example.dabh.repository.ICustomerRepository;
import com.example.dabh.repository.IProductRepository;
import com.example.dabh.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CartService implements ICartService {

    @Autowired
    private ICartRepository cartRepository;
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Iterable<CartItem> findCartCustomer(int id){
        List<CartItem> cartItem = cartRepository.findAll();
        List<CartItem> cartItems = new ArrayList<>();
        for(CartItem c: cartItem ){
            if(id == c.getCustomer().getId()){
                assert false;
                cartItems.add(c);
            }
        }
        return cartItems;
    }

    @Override
    public void addProductsToCart(CartItem cartItem, int id_Customer, int id_Product){
        Customer customer = customerRepository.findCustomerById(id_Customer);
        Product product = productRepository.findProductById(id_Product);

        if (id_Customer == cartItem.getCustomer().getId() && id_Product == cartItem.getProduct().getId()){
            double price = Double.parseDouble(cartItem.getPrice() + product.getPrice());
            int quantity = cartItem.getQuantity() + 1;
            cartItem.setPrice(price);
            cartItem.setQuantity(quantity);
        } else {
            cartItem.setCustomer(customer);
            cartItem.setProduct(product);
            cartItem.setPrice(Double.parseDouble(product.getPrice()));
            cartItem.setQuantity(1);
        }
        cartRepository.save(cartItem);
    }


    @Override
    public void deleteProductCart(int idCustomer,int idProduct){
        Iterable<CartItem> cartItems = cartRepository.findAll();
        for(CartItem cart : cartItems){
            if (cart.getCustomer().getId() == idCustomer && cart.getProduct().getId() == idProduct){
                cartRepository.delete(cart);
            }
        }

    }
    @Override
    public Iterable<CartItem> showAll() {
        return null;
    }

    @Override
    public Optional<CartItem> findObjectById(int id) {
        return Optional.empty();
    }

    @Override
    public Iterable<CartItem> findByAllOption(String keyword) {
        return null;
    }
}
