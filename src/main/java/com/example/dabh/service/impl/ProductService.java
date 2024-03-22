package com.example.dabh.service.impl;

import com.example.dabh.model.*;
import com.example.dabh.repository.*;
import com.example.dabh.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IProductDetailRepository productDetailRepository;

    @Override
    public Iterable<Product> showAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findObjectById(int id) {
        return productRepository.findById(id);
    }
    @Value("C:/Users/PC/Documents/BE2/DABH/src/main/resources/static/picture")
    private String fileUpload;


    @Override
    public void save(ProductForm productForm, String name, String password, int idCategory) {
        Optional<Users> usersOptional = userRepository.findUserByNameUser(name);
        if (usersOptional.get().getPassword().equals(password)) {
            Iterable<Role> roles = roleRepository.findRoleByUsers(Collections.singleton(usersOptional.get()));
            for (Role role : roles) {
                if (role.getNameRole().equals("admin")) {
                    MultipartFile multipartFile = productForm.getPicture();
                    String filename = multipartFile.getOriginalFilename();
                    try{
                        FileCopyUtils.copy(productForm.getPicture().getBytes(),new File(fileUpload + filename));
                    }catch (IOException e){
                        throw new RuntimeException();
                    }
                    Product product = new Product(productForm.getId(),productForm.getNameProduct(),productForm.getPrice(),productForm.getType(),productForm.getDescripsion(),productForm.isStatus(),filename);
                    Optional<Category> categoryOptional = categoryRepository.findById(idCategory);
                    product.setCategory(categoryOptional.get());
                    product.setUsers(usersOptional.get());
                    productRepository.save(product);
                }
            }
        } else {
            throw new RuntimeException();
        }

    }

    @Override
    @Transactional
    public void delete(int id, String name, String password) {
        Optional<Users> usersOptional = userRepository.findUserByNameUser(name);
        if (usersOptional.get().getPassword().equals(password)) {
            Iterable<Role> roles = roleRepository.findRoleByUsers(Collections.singleton(usersOptional.get()));
            for (Role role : roles) {
                if (role.getNameRole().equals("admin")) {
                    Optional<Product> product = productRepository.findById(id);
                   productDetailRepository.deleteProductDetailByProduct(product.get());
                   productRepository.deleteProductById(id);

                }
            }
        } else {
            throw new RuntimeException();
        }

    }

    @Override
    public void update(int id, ProductForm productForm, String name, String password) {
        Optional<Users> usersOptional = userRepository.findUserByNameUser(name);
        if (usersOptional.get().getPassword().equals(password)) {
            Iterable<Role> roles = roleRepository.findRoleByUsers(Collections.singleton(usersOptional.get()));
            for (Role role : roles) {
                if (role.getNameRole().equals("admin")) {
                    MultipartFile multipartFile = productForm.getPicture();
                    String filename = multipartFile.getOriginalFilename();
                    try{
                        FileCopyUtils.copy(productForm.getPicture().getBytes(),new File(fileUpload + filename));
                    }catch (IOException e){
                        throw new RuntimeException();
                    }
                    Optional<Product> productOptional = productRepository.findById(id);
                    Product product = new Product(productForm.getId(),productForm.getNameProduct(),productForm.getPrice(),productForm.getType(),productForm.getDescripsion(),productForm.isStatus(),filename);
                    Optional<Category> categoryOptional = categoryRepository.findById(productOptional.get().getCategory().getId());
                    product.setCategory(categoryOptional.get());
                    product.setUsers(usersOptional.get());
                    product.setId(id);
                    productRepository.save(product);
                }
            }
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public Iterable<Product> findProduct(String keyValue) {
         Iterable<Product> products = productRepository.findAllByProductContaining(keyValue);
         Iterable<Product> productIterable = new ArrayList<>();
         if (!products.iterator().hasNext()){
             Iterable<Category> categoryIterable = categoryRepository.findAllByNameCategoryContains(keyValue);
             for (Category category : categoryIterable){
               productIterable  = productRepository.findAllByCategory(category);
             }
             return productIterable ;
         }else{
             return products;
         }

    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
       Page<Product> productPage = productRepository.findAll(pageable);
        return productPage;
    }

}
