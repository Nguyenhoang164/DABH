package com.example.dabh.service.impl;

import com.example.dabh.model.*;
import com.example.dabh.repository.*;
import com.example.dabh.service.IBillService;
import com.example.dabh.service.ICustomerService;
import com.example.dabh.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BillService implements IBillService {
    @Autowired
    private IBillRepository billRepository;
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Iterable<Bill> showAll() {
        return billRepository.findAll();
    }

    @Override
    public Optional<Bill> findObjectById(int id) {
        return billRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(List<Product> products, Customer customer) {
        Bill bill = new Bill();
        bill.setNumberProduct(products.size());
        LocalDate localDate = LocalDate.now();
        bill.setDate(localDate);
        bill.setCustomer(customer);

// Sử dụng User từ một Product

        Set<Product> productSet = new HashSet<>(products);
        bill.setStatus(false);
        bill.setProducts(productSet);
        billRepository.save(bill);
    }
        @Override
    public void updateBillForCustomer( Bill bill, int idBill) {
        bill.setId(idBill);
        LocalDate localDate = LocalDate.now();
        bill.setDate(localDate);
        bill.setStatus(true);
        billRepository.save(bill);
    }

    @Override
    public void updateBillForUser(Bill bill, String name, String password, int idBill) {
        Optional<Users> usersOptional = userRepository.findUserByNameUser(name);
        if (usersOptional.get().getPassword().equals(password)) {
            Iterable<Role> roles = roleRepository.findRoleByUsers(Collections.singleton(usersOptional.get()));
            for (Role role : roles) {
                if (role.getNameRole().equals("admin")) {
                    bill.setId(idBill);
                    billRepository.save(bill);
                }
            }
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    @Transactional
    public void deleteBill(Customer customer) {
       billRepository.deleteAllByCustomer(customer);
    }

    @Override
    public Iterable<Bill> findBill(String name) {
        Optional<Customer> customerOptional = customerRepository.findCustomersByNameCustomer(name);
       Iterable<Bill> billIterable = billRepository.findAllByCustomerNameCustomer(customerOptional.get().getNameCustomer());
       return billIterable;
    }
}
