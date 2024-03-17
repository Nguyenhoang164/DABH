package com.example.dabh.controller;

import com.example.dabh.model.Category;
import com.example.dabh.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @GetMapping("")
    public ResponseEntity<Iterable<Category>> showAllCategory(){
        return new ResponseEntity<>(categoryService.showAll(), HttpStatus.OK);
    }
    @PostMapping("/add/{name}/{password}")
    public ResponseEntity<String> createCategory(@RequestBody Category category , @PathVariable("name") String name , @PathVariable("password") String password){
        categoryService.save(category,name,password);
        return new ResponseEntity<>("create new category",HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}/{name}/{password}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") int id , @PathVariable("name") String name , @PathVariable("password") String password){
        categoryService.delete(id,name,password);
        return new ResponseEntity<>("delete category complete",HttpStatus.OK);
    }
    @PutMapping("/update/{id}/{name}/{password}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category,@PathVariable("id") int id , @PathVariable("name") String name ,@PathVariable("password") String password){
        categoryService.update(id,category,name,password);
        return new ResponseEntity<>("update new category complete",HttpStatus.OK);
    }
    @GetMapping("/search/{keyWord}")
    public ResponseEntity<Iterable<Category>> findCategory(@PathVariable("keyWord") String keyWord){
         return new ResponseEntity<>(categoryService.findCategory(keyWord),HttpStatus.OK);
    }
}
