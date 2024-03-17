package com.example.dabh.controller;

import com.example.dabh.model.Product;
import com.example.dabh.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductService productService;
    @GetMapping("")
    public ResponseEntity<Iterable<Product>> showAllProduct(){
        return new ResponseEntity<>(productService.showAll(), HttpStatus.OK);
    }
    @PostMapping("/add/{id_category}/{name}/{password}")
    public ResponseEntity<String> createProduct(@RequestBody Product product , @PathVariable("name") String name , @PathVariable("password") String password,@PathVariable("id_category") int idCategory){
        productService.save(product,name,password,idCategory);
        return new ResponseEntity<>("create product complete",HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}/{name}/{password}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") int id,@PathVariable("name")String name , @PathVariable("password") String password){
        productService.delete(id,name,password);
        return new ResponseEntity<>("delete product complete",HttpStatus.OK);
    }
    @PutMapping("/update/{id}/{idCategory}/{name}/{password}")
    public ResponseEntity<String> updateProduct(@RequestBody Product product , @PathVariable("id") int id , @PathVariable("name") String name , @PathVariable("password") String password,@PathVariable("idCategory") int idCategory){
        productService.update(id,product,name,password,idCategory);
        return new ResponseEntity<>("update product complete",HttpStatus.OK);
    }
    @GetMapping("/search/{keyWord}")
    public ResponseEntity<Iterable<Product>> findProduct(@PathVariable("keyWord") String keyWord){
        return new ResponseEntity<>(productService.findProduct(keyWord),HttpStatus.OK);
    }
}
