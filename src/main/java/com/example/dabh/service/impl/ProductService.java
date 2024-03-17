package com.example.dabh.service.impl;

import com.example.dabh.model.Category;
import com.example.dabh.model.Product;
import com.example.dabh.model.Role;
import com.example.dabh.model.Users;
import com.example.dabh.repository.ICategoryRepository;
import com.example.dabh.repository.IProductRepository;
import com.example.dabh.repository.IRoleRepository;
import com.example.dabh.repository.IUserRepository;
import com.example.dabh.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Iterable<Product> showAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findObjectById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public Iterable<Product> findByAllOption(String keyword) {
        return null;
    }

    @Override
    public void save(Product product, String name, String password, int idCategory) {
        Optional<Users> usersOptional = userRepository.findUserByNameUser(name);
        if (usersOptional.get().getPassword().equals(password)) {
            Iterable<Role> roles = roleRepository.findRoleByUsers(Collections.singleton(usersOptional.get()));
            for (Role role : roles) {
                if (role.getNameRole().equals("admin")) {
                    product.setUsers(usersOptional.get());
                    Optional<Category> categoryOptional = categoryRepository.findById(idCategory);
                    product.setCategory(categoryOptional.get());
                    productRepository.save(product);
                }
            }
        } else {
            throw new RuntimeException();
        }

    }

    @Override
    public void delete(int id, String name, String password) {
        Optional<Users> usersOptional = userRepository.findUserByNameUser(name);
        if (usersOptional.get().getPassword().equals(password)) {
            Iterable<Role> roles = roleRepository.findRoleByUsers(Collections.singleton(usersOptional.get()));
            for (Role role : roles) {
                if (role.getNameRole().equals("admin")) {
                    productRepository.deleteById(id);
                }
            }
        } else {
            throw new RuntimeException();
        }

    }

    @Override
    public void update(int id, Product product, String name, String password, int idCategory) {
        Optional<Users> usersOptional = userRepository.findUserByNameUser(name);
        if (usersOptional.get().getPassword().equals(password)) {
            Iterable<Role> roles = roleRepository.findRoleByUsers(Collections.singleton(usersOptional.get()));
            for (Role role : roles) {
                if (role.getNameRole().equals("admin")) {
                    product.setUsers(usersOptional.get());
                    Optional<Category> categoryOptional = categoryRepository.findById(idCategory);
                    product.setCategory(categoryOptional.get());
                    productRepository.deleteById(id);
                    productRepository.save(product);
                }
            }
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public Iterable<Product> findProduct(String keyValue) {
         Iterable<Product> products = productRepository.findAllByNameProductContaining(keyValue);
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

}
