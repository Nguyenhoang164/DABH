package com.example.dabh.controller.MVC;

import com.example.dabh.model.Cart;
import com.example.dabh.model.Product;
import com.example.dabh.model.ProductForm;
import com.example.dabh.service.ICartService;
import com.example.dabh.service.IProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/products")
public class ProductMVCController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICartService cartService;
    @GetMapping("{id}")
    public String showProductList(Model model, @PageableDefault(6) Pageable pageable,@PathVariable("id") int idCustomer) {
        // Lấy trang sản phẩm từ service
        Page<Product> productsPage = productService.findAll(pageable);

        model.addAttribute("productsPage", productsPage);
        model.addAttribute("id",idCustomer);

        // Lấy danh sách ảnh
        List<String> imagePaths = new ArrayList<>();
        List<String> imageNames = new ArrayList<>();
        for (Product product : productsPage.getContent()) {
            // Thêm đường dẫn ảnh vào danh sách
            imagePaths.add("/images/" + product.getPicture());
            // Thêm tên ảnh vào danh sách
            imageNames.add(product.getPicture());
        }
        // Thêm danh sách đường dẫn ảnh và tên ảnh vào model
        model.addAttribute("imagePaths", imagePaths);
        model.addAttribute("imageNames", imageNames);

        return "user/listCategoryAndProduct";
    }

    @GetMapping("/create")
    public String showFormCreateProduct(Model model){
        model.addAttribute("product",new ProductForm());
        return "/user/createProduct";
    }
    @PostMapping("/add/{name}/{password}")
    public String createProduct(@ModelAttribute ProductForm productForm ,@PathVariable("name") String name ,@PathVariable("password") String password){
        productService.save(productForm,name,password, productForm.getIdCategory());
        return "redirect:/products";
    }
    @GetMapping("/delete/{id}")
    public String showFormDelete(Model model , @PathVariable("id") int id){
       Optional<Product> productOptional = productService.findObjectById(id);
       model.addAttribute("product",productOptional.get());
       return "/user/deleteProduct";
    }
    @PostMapping("/delete/{name}/{password}")
    public String deleteProduct(@ModelAttribute Product product , @PathVariable("name") String name , @PathVariable("password") String password){
        productService.delete(product.getId(),name,password);
        return "redirect:/products";
    }
    @GetMapping("/update/{id}")
    public String showFormUpdateProduct(Model model , @PathVariable("id") int id){
        Optional<Product> productOptional = productService.findObjectById(id);
        model.addAttribute("product",productOptional.get());
        return "user/updateProduct";
    }
    @PostMapping("/update/{name}/{password}")
    public String updateProduct(@ModelAttribute ProductForm productForm,@PathVariable("name") String name,@PathVariable("password") String password){
        productService.update(productForm.getId(),productForm,name,password);
        return "redirect:/products";
    }
    @PostMapping("/search")
    public String searchProduct(@RequestParam("keyWord") String keyword , Model model){
        Iterable<Product> productIterable = productService.findProduct(keyword);
        model.addAttribute("products",productIterable);
        return "/user/search";
    }

    @GetMapping("/cart/{id}/{idCustomer}")
    public ModelAndView addToCart(HttpSession session, @PathVariable("id") int idProducts, @PathVariable("idCustomer") int idCustomer){
        cartService.addToCart(session,idCustomer,idProducts);
        ModelAndView modelAndView = new ModelAndView("redirect:/products/"+idCustomer);
        return modelAndView;
    }
    @GetMapping("/plus/{id}/{idCustomer}")
    public ModelAndView plus(HttpSession session, @PathVariable("id") int idProducts, @PathVariable("idCustomer") int idCustomer){
        cartService.addToCart(session,idCustomer,idProducts);
        ModelAndView modelAndView = new ModelAndView("redirect:/products/showCart/"+idCustomer);
        return modelAndView;
    }

    @GetMapping("/minus/{id}/{idCustomer}")
    public ModelAndView minus(HttpSession session, @PathVariable("id") int idProducts, @PathVariable("idCustomer") int idCustomer){
        cartService.minusOneProduct(session,idCustomer,idProducts);
        ModelAndView modelAndView = new ModelAndView("redirect:/products/showCart/"+idCustomer);
        return modelAndView;
    }


    @GetMapping("/showCart/{id}")
    public ModelAndView showCart(HttpSession session, @PathVariable("id") int id){
        List<Cart> carts = cartService.getCart(session,id);
        double total=0;
        for(Cart c: carts){
              total += c.getPrice();
        }
        ModelAndView modelAndView = new ModelAndView("/user/cart");
        modelAndView.addObject("carts",carts);
        modelAndView.addObject("id",id);
        modelAndView.addObject("total",total);
        return modelAndView;
    }



}
