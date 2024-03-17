package com.example.dabh.controller;

import com.example.dabh.model.CartItem;
import com.example.dabh.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<Iterable<CartItem>> showCart(@PathVariable("id") int id){
        return new ResponseEntity<>(cartService.findCartCustomer(id), HttpStatus.OK);
    }

    @PostMapping("/add/{id_Customer}/{id_Product}")
    public ResponseEntity<String> addToCart(@RequestBody CartItem cartItem,@PathVariable("id_Customer") int id_Customer,@PathVariable("id_Product") int id_Product){
        cartService.addProductsToCart(cartItem,id_Customer,id_Product);
        return new ResponseEntity<>("save complete",HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id_Customer}/{id_Product}")
    public ResponseEntity<String> deleteProductFromCart(@PathVariable("id_Customer") int idCustomer,@PathVariable("id_Product") int idProduct){
        cartService.deleteProductCart(idCustomer,idProduct);
        return new ResponseEntity<>("Delete complete",HttpStatus.OK);
    }
}
