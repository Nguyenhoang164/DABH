package com.example.dabh.service.impl;

import com.example.dabh.model.Product;
import com.example.dabh.model.ProductDetail;
import com.example.dabh.model.Role;
import com.example.dabh.model.Users;
import com.example.dabh.repository.IProductDetailRepository;
import com.example.dabh.repository.IProductRepository;
import com.example.dabh.repository.IRoleRepository;
import com.example.dabh.repository.IUserRepository;
import com.example.dabh.service.IProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
@Service
public class ProductDetailService implements IProductDetailService {
    @Autowired
    private IProductDetailRepository productDetailRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Optional<ProductDetail> showProductDetailByIdProduct(int idProduct) {
        Optional<Product> productOptional = productRepository.findById(idProduct);

        return productDetailRepository.findProductDetailByProduct(productOptional.get());
    }

    @Override
    public void save(ProductDetail productDetail, String name, String password, int idProduct) {
        Optional<Users> usersOptional = userRepository.findUserByNameUser(name);
        if (usersOptional.get().getPassword().equals(password)){
            Iterable<Role> roleIterable = roleRepository.findRoleByUsers(Collections.singleton(usersOptional.get()));
            for (Role role : roleIterable){
                if (role.getNameRole().equals("admin")){
                    Optional<Product> productOptional = productRepository.findById(idProduct);
                    productDetail.setProduct(productOptional.get());
                    productDetailRepository.save(productDetail);
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
                productDetailRepository.deleteById(id);
            }
        }
    }
    }

    @Override
    public void update(ProductDetail productDetail, String name, String password) {
        Optional<Users> usersOptional = userRepository.findUserByNameUser(name);
        if (usersOptional.get().getPassword().equals(password)){
            Iterable<Role> roleIterable = roleRepository.findRoleByUsers(Collections.singleton(usersOptional.get()));
            for (Role role : roleIterable){
                if (role.getNameRole().equals("admin")){
                    Optional<Product> productOptional = productRepository.findById(productDetail.getProduct().getId());
                    productDetail.setProduct(productOptional.get());
                    productDetail.setId(productDetail.getId());
                    productDetailRepository.deleteById(productDetail.getId());
                    productDetailRepository.save(productDetail);
                }
            }
        }
    }


}
