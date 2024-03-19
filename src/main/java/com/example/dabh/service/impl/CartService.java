package com.example.dabh.service.impl;

import com.example.dabh.model.Cart;
import com.example.dabh.model.Customer;
import com.example.dabh.model.Product;
import com.example.dabh.repository.ICartRepository;
import com.example.dabh.repository.ICustomerRepository;
import com.example.dabh.repository.IProductRepository;
import com.example.dabh.service.ICartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService implements ICartService {
    @Autowired
    ICartRepository cartRepository;
    @Autowired
    IProductRepository productRepository;
    @Autowired
    ICustomerRepository customerRepository;

    @Override
    public void addToCart(HttpSession session, int idCustomer, int idProduct) {
        String sessionId = "customer_" + idCustomer;

        List<Cart> carts = (List<Cart>) session.getAttribute(sessionId);

        if (carts == null) {
            carts = new ArrayList<>();
            session.setAttribute(sessionId, carts);
        }
        Cart exist = findProductsById(carts, idProduct);
        if (exist != null) {
            String priceStrExist = exist.getProduct().getPrice();
            String changePriceExist = priceStrExist.replace(".","");
            exist.setCount(exist.getCount() + 1);
            double  total = Double.parseDouble(changePriceExist)* exist.getCount();
            exist.setPrice(total);
//            cartRepository.save(exist);
        } else {
            Product product = productRepository.findOneById(idProduct);
            Customer customer = customerRepository.findOneById(idCustomer);
            if (product != null) {
                String priceStr = product.getPrice();
                String changePrice = priceStr.replace(".","");
                double price = Double.parseDouble(changePrice);
                Cart newItem = new Cart();
                newItem.setProduct(product);
                newItem.setCount(1);
                newItem.setPrice(price);
                newItem.setProduct(product);
                newItem.setCustomer(customer);
                carts.add(newItem);
//                cartRepository.save(newItem);
            }
        }
    }
@Override
    public void minusOneProduct(HttpSession session, int idCustomer, int idProduct){
        String sessionId = "customer_" + idCustomer;
        List<Cart> carts = (List<Cart>) session.getAttribute(sessionId);
        if(carts != null){
            Cart exist = findProductsById(carts,idProduct);
            String priceStrExist = exist.getProduct().getPrice();
            String changePriceExist = priceStrExist.replace(".","");
            exist.setCount(exist.getCount()-1);
            exist.setPrice(exist.getPrice() - Double.parseDouble(changePriceExist));
            if (exist.getPrice() < Double.parseDouble(changePriceExist)){
                carts.remove(exist);
            }
        }
    }
    @Override
    public List<Cart> getCart(HttpSession session, int idCustomer) {
        String sessionId = "customer_" + idCustomer;
        List<Cart> carts = (List<Cart>) session.getAttribute(sessionId);
        if (carts == null) {
            carts = new ArrayList<>();
            session.setAttribute(sessionId, carts);
        }
        return carts;
    }

    @Override
    public Cart findProductsById(List<Cart> carts, int idProduct) {
        for (Cart item : carts) {
            if (item.getProduct().getId() == idProduct) {
                return item;
            }
        }
        return null;
    }
}
