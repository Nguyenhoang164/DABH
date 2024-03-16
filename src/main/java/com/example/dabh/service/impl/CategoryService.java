package com.example.dabh.service.impl;

import com.example.dabh.model.Category;
import com.example.dabh.repository.ICategoryRepository;
import com.example.dabh.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;
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
}
