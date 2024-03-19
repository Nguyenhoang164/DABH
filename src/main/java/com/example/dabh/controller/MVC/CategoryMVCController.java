package com.example.dabh.controller.MVC;

import com.example.dabh.model.Category;
import com.example.dabh.repository.ICategoryRepository;
import com.example.dabh.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryMVCController {
    @Autowired
    private ICategoryService categoryService;
    @GetMapping("")
    public String showCategoryPage(Model model){
        model.addAttribute("categorys",categoryService.showAll());
        return "user/category/listCategory";
    }
    @GetMapping("/create")
    public String showFormCreateCategory(Model model){
        model.addAttribute("category",new Category());
        return "user/category/createCategory";
    }
    @PostMapping("/create/{name}/{password}")
    public String createCategory(@ModelAttribute("category") Category category , @PathVariable("name") String name , @PathVariable("password") String password){
        categoryService.save(category,name,password);
        return "redirect:/category";
    }
    @GetMapping("/delete/{id}")
    public String showFormDelete(@PathVariable("id") int id , Model model){
        model.addAttribute("category",categoryService.findObjectById(id).get());
        return "user/category/deleteCategory";
    }
    @PostMapping("/delete/{name}/{password}")
    public String deleteCategory(@ModelAttribute Category category , @PathVariable("name") String name , @PathVariable("password") String password){
        categoryService.delete(category.getId(),name,password);
        return "redirect:/category";
    }
    @GetMapping("/update/{id}")
    public String showUpdateCategory(@PathVariable("id") int id , Model model){
        model.addAttribute("category",categoryService.findObjectById(id).get());
        return "user/category/updateCategory";
    }
    @PostMapping("/update/{name}/{password}")
    public String updateCategory(@ModelAttribute Category category , @PathVariable("name")String name , @PathVariable("password") String password){
        categoryService.update(category.getId(),category,name,password);
        return "redirect:/category";
    }
}
