package com.example.dabh.controller;

import com.example.dabh.model.Category;
import com.example.dabh.model.Product;
import com.example.dabh.model.ProductDetail;
import com.example.dabh.service.ICategoryService;
import com.example.dabh.service.IProductDetailService;
import com.example.dabh.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IProductDetailService productDetailService;
    @GetMapping("")
    public String mainPage(Model model){
        Iterable<Product> products = productService.showAll();
        Iterable<Category> categories = categoryService.showAll();
        List<String> imagePaths = new ArrayList<>();
        List<String> imageNames = new ArrayList<>();
        for (Product product : products) {
            // Thêm đường dẫn ảnh vào danh sách
            imagePaths.add("/images/" + product.getPicture());
            // Thêm tên ảnh vào danh sách
            imageNames.add(product.getPicture());
        }
        // Thêm danh sách đường dẫn ảnh và tên ảnh vào model
        model.addAttribute("imagePaths", imagePaths);
        model.addAttribute("imageNames", imageNames);
        model.addAttribute("categorys",categories);
        model.addAttribute("products",products);
        return "customer/mainPage";
    }
    @GetMapping("/showDetailProduct/{id}")
    public String showDetailProduct(@PathVariable("id") int idProduct , Model model){
        Optional<Product> productOptional = productService.findObjectById(idProduct);
        Optional<ProductDetail> productDetail = productDetailService.showProductDetailByIdProduct(idProduct);
        model.addAttribute("product",productOptional.get());
        model.addAttribute("productDetail",productDetail);
        return "customer/productDetail";
    }

}
