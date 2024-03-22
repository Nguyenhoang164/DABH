package com.example.dabh.service;

import com.example.dabh.model.Bill;
import com.example.dabh.model.Customer;
import com.example.dabh.model.Product;

import java.util.List;

public interface IBillService extends IGenerateService<Bill> {
    void save(List<Product> products, Customer customer);
    void updateBillForCustomer( Bill bill , int idBill);
    void updateBillForUser(Bill bill , String name , String password , int Bill);
    void deleteBill(Customer customer);
    Iterable<Bill> findBill(String name);
}
