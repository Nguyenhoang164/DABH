package com.example.dabh.service.impl;

import com.example.dabh.model.Category;
import com.example.dabh.model.Product;
import com.example.dabh.model.Role;
import com.example.dabh.model.Users;
import com.example.dabh.repository.ICategoryRepository;
import com.example.dabh.repository.IProductRepository;
import com.example.dabh.repository.IRoleRepository;
import com.example.dabh.repository.IUserRepository;
import com.example.dabh.service.ICategoryService;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IProductRepository productRepository;
    @Override
    public Iterable<Category> showAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findObjectById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Iterable<Category> findByAllOption(String keyword) {
        return null;
    }

    @Override
    public void save(Category category, String name, String password) {
        Optional<Users> usersOptional = userRepository.findUserByNameUser(name);
        if (usersOptional.get().getPassword().equals(password)){
            Iterable<Role> roleIterable = roleRepository.findRoleByUsers(Collections.singleton(usersOptional.get()));
            for (Role role : roleIterable){
                if (role.getNameRole().equals("admin")){
                    category.setUsers(usersOptional.get());
                    categoryRepository.save(category);
                }
            }
        }
    }

    @Override
    public void delete(int id, String name, String password) {
        Optional<Users> usersOptional = userRepository.findUserByNameUser(name);
        if (usersOptional.get().getPassword().equals(password)){
            Iterable<Role> roleIterable = roleRepository.findRoleByUsers(Collections.singleton(usersOptional.get()));
            for (Role role : roleIterable){
                if (role.getNameRole().equals("admin")){
                   productRepository.deleteAllByCategoryId(id);
                    categoryRepository.deleteById(id);
                }
            }
        }
    }

    @Override
    public void update(int id, Category category, String name, String password) {
        Optional<Users> usersOptional = userRepository.findUserByNameUser(name);
        if (usersOptional.get().getPassword().equals(password)){
            Iterable<Role> roleIterable = roleRepository.findRoleByUsers(Collections.singleton(usersOptional.get()));
            for (Role role : roleIterable){
                if (role.getNameRole().equals("admin")){
                    category.setId(id);
                    category.setUsers(usersOptional.get());
                    categoryRepository.save(category);
                }
            }
        }
    }

    @Override
    public Iterable<Category> findCategory(String keyValue) {
       Iterable<Category> categoryIterable = categoryRepository.findAllByNameCategoryContains(keyValue);
       return categoryIterable;
    }
}
