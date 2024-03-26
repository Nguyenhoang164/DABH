package com.example.dabh.controller.MVC;

import com.example.dabh.model.Product;
import com.example.dabh.model.ProductDetail;
import com.example.dabh.repository.IProductDetailRepository;
import com.example.dabh.repository.IProductRepository;
import com.example.dabh.service.IProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/productDetail")
public class ProductDetailMVCController {
    @Autowired
    private IProductDetailService productDetailService;
    @Autowired
    private IProductDetailRepository productDetailRepository;
    @Autowired
    private IProductRepository productRepository;
    @GetMapping("show/{id}")
    public String showDetail(@PathVariable("id") int id, Model model){
      Optional<ProductDetail> productDetail =  productDetailService.showProductDetailByIdProduct(id);
      if (productDetail.isEmpty()){
          model.addAttribute("productDetail",new ProductDetail());
          model.addAttribute("idProduct",id);
      }else{
          model.addAttribute("productDetail",productDetail.get());
          model.addAttribute("idProduct",id);
      }
        return "user/productDetail/showProductDetail";
    }
    @GetMapping("/create/{id}")
    public String showProductDetail(@PathVariable("id") int id , Model model){
        ProductDetail productDetail = new ProductDetail();
        Optional<Product> productOptional = productRepository.findById(id);
        productDetail.setProduct(productOptional.get());
        model.addAttribute("productDetail",productDetail);
        return "user/productDetail/createProductDetail";
    }
    @PostMapping("/save/{name}/{password}")
    public String createProductDetail(@ModelAttribute ProductDetail productDetail , @PathVariable("name") String name , @PathVariable("password") String password){
        productDetailService.save(productDetail,name,password,productDetail.getProduct().getId());
        return "redirect:/products";
    }
    @GetMapping("/update/{id}")
    public String showFormUpdateProductDetail(@PathVariable("id") int id , Model model){
        model.addAttribute("productDetail",productDetailRepository.findById(id).get());
        return "user/productDetail/updateProductDetail";
    }
    @PostMapping("/update/{name}/{password}")
    public String updateProduct(@ModelAttribute ProductDetail productDetail , @PathVariable("name") String name , @PathVariable("password") String password){
        productDetailService.update(productDetail,name,password);
        return "redirect:/products";
    }
}
