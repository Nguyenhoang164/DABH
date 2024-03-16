package com.example.dabh.controller;

import com.example.dabh.model.Category;
import com.example.dabh.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @GetMapping("")
    public ResponseEntity<Iterable<Category>> showAllCategory(){
        return new ResponseEntity<>(categoryService.showAll(), HttpStatus.OK);
    }
}
