package com.example.dabh.service.impl;

import com.example.dabh.model.Bill;
import com.example.dabh.repository.IBillRepository;
import com.example.dabh.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillService implements IBillService {
    @Autowired
    private IBillRepository billRepository;
    @Override
    public Iterable<Bill> showAll() {
        return billRepository.findAll();
    }

    @Override
    public Optional<Bill> findObjectById(int id) {
        return billRepository.findById(id);
    }
}
