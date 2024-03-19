package com.example.dabh.controller;

import com.example.dabh.model.Product;
import com.example.dabh.model.ProductDetail;
import com.example.dabh.model.ProductForm;
import com.example.dabh.service.IProductDetailService;
import com.example.dabh.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductDetailService productDetailService;
    @GetMapping("")
    public ResponseEntity<Iterable<Product>> showAllProduct(){
        return new ResponseEntity<>(productService.showAll(), HttpStatus.OK);
    }
    @PostMapping("/add/{id_category}/{name}/{password}")
    public ResponseEntity<String> createProduct(@RequestBody ProductForm productForm , @PathVariable("name") String name , @PathVariable("password") String password, @PathVariable("id_category") int idCategory){
        productService.save(productForm,name,password,idCategory);
        return new ResponseEntity<>("create product complete",HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}/{name}/{password}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") int id,@PathVariable("name")String name , @PathVariable("password") String password){
        productService.delete(id,name,password);
        return new ResponseEntity<>("delete product complete",HttpStatus.OK);
    }
    @PutMapping("/update/{id}/{idCategory}/{name}/{password}")
    public ResponseEntity<String> updateProduct(@RequestBody ProductForm productForm , @PathVariable("id") int id , @PathVariable("name") String name , @PathVariable("password") String password,@PathVariable("idCategory") int idCategory){
        productService.update(id,productForm,name,password);
        return new ResponseEntity<>("update product complete",HttpStatus.OK);
    }
    @GetMapping("/search/{keyWord}")
    public ResponseEntity<Iterable<Product>> findProduct(@PathVariable("keyWord") String keyWord){
        return new ResponseEntity<>(productService.findProduct(keyWord),HttpStatus.OK);
    }
    @PostMapping("/addProductDetail/{idProduct}/{name}/{password}")
    public ResponseEntity<String> createProductDetailByProductId(@RequestBody ProductDetail productDetail , @PathVariable("idProduct") int idPProduct , @PathVariable("name") String name , @PathVariable("password") String password){
        productDetailService.save(productDetail,name,password,idPProduct);
        return new ResponseEntity<>("Create new product detail complete",HttpStatus.OK);
    }
    @GetMapping("/showProductDetail/{idProduct}")
    public ResponseEntity<Optional<ProductDetail>> showProductDetailByIdProduct(@PathVariable("idProduct") int idProduct){
        return new ResponseEntity<>(productDetailService.showProductDetailByIdProduct(idProduct),HttpStatus.OK);
    }
    @DeleteMapping("/deleteProductDetail/{id}/{name}/{password}")
    public ResponseEntity<String> deleteProductDetail(@PathVariable("id") int idProductDetail,@PathVariable("name") String name , @PathVariable("password") String password){
        productDetailService.delete(idProductDetail,name,password);
        return new ResponseEntity<>("product detail was delete complete",HttpStatus.OK);
    }
    @PutMapping("/updateProductDetail/{id}/{name}/{password}")
    public ResponseEntity<String> updateProductDetail(@RequestBody ProductDetail productDetail , @PathVariable("id") int id , @PathVariable("name") String name , @PathVariable("password") String password){
        productDetailService.update(productDetail,name,password);
        return new ResponseEntity<>("update product detail complete",HttpStatus.OK);
    }
}
