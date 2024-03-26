package com.example.dabh.controller.MVC;

import com.example.dabh.controller.UserController;
import com.example.dabh.model.*;
import com.example.dabh.repository.IProductRepository;
import com.example.dabh.service.ICartService;
import com.example.dabh.service.ICategoryService;
import com.example.dabh.service.IProductService;
import jakarta.servlet.http.HttpSession;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/products")
@SessionAttributes("customer")
public class ProductMVCController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ICategoryService categoryService;
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
    @GetMapping("/cart/{id}")
    public ModelAndView addToCart(HttpSession session, @PathVariable("id") int idProducts){
        Optional<Customer> customer = (Optional<Customer>) session.getAttribute("customer");
        if (customer == null){
            return new ModelAndView("/login");
        }
        cartService.addToCart(session,customer.get().getId(),idProducts);
        ModelAndView modelAndView = new ModelAndView("redirect:/products/"+ customer.get().getId());
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


    @GetMapping("/showCart")
    public ModelAndView showCart(HttpSession session){
        Optional<Customer> customerOptional = (Optional<Customer>) session.getAttribute("customer");
        List<Cart> carts = cartService.getCart(session,customerOptional.get().getId());
        double total=0;
        for(Cart c: carts){
            total += c.getPrice();
        }
        ModelAndView modelAndView = new ModelAndView("/user/cart");
        modelAndView.addObject("carts",carts);
        modelAndView.addObject("id",customerOptional.get().getId());
        modelAndView.addObject("total",total);
        return modelAndView;
    }

    @GetMapping("")
    public String showProductList(Model model, @PageableDefault(6) Pageable pageable) {
        // Lấy trang sản phẩm từ service
        Page<Product> productsPage = productService.findAll(pageable);
        model.addAttribute("productsPage", productsPage);

        // Lấy danh sách ảnh
        List<String> imagePaths = new ArrayList<>();
        List<String> imageNames = new ArrayList<>();
        for (Product product : productsPage.getContent()) {
            // Thêm đường dẫn ảnh vào danh sách
            imagePaths.add(product.getPicture());
            // Thêm tên ảnh vào danh sách
            imageNames.add(product.getPicture());
        }
        // Thêm danh sách đường dẫn ảnh và tên ảnh vào model
        model.addAttribute("imagePaths", imagePaths);
        model.addAttribute("imageNames", imageNames);

        return "user/listCategoryAndProduct";
    }
//    @GetMapping("/showASC")
//    public String showProductListASC(Model model, @PageableDefault(6) Pageable pageable) {
//        // Lấy trang sản phẩm từ service
//        Page<Product> productsPage = productRepository.showProductsByASC(pageable);
//        model.addAttribute("productsPage", productsPage);
//
//        // Lấy danh sách ảnh
//        List<String> imagePaths = new ArrayList<>();
//        List<String> imageNames = new ArrayList<>();
//        for (Product product : productsPage.getContent()) {
//            // Thêm đường dẫn ảnh vào danh sách
//            imagePaths.add("/images/" + product.getPicture());
//            // Thêm tên ảnh vào danh sách
//            imageNames.add(product.getPicture());
//        }
//        // Thêm danh sách đường dẫn ảnh và tên ảnh vào model
//        model.addAttribute("imagePaths", imagePaths);
//        model.addAttribute("imageNames", imageNames);
//
//        return "user/listCategoryAndProduct";
//    }
//    @GetMapping("/showDESC")
//    public String showProductListDESC(Model model, @PageableDefault(6) Pageable pageable) {
//        // Lấy trang sản phẩm từ service
//        Page<Product> productsPage = productRepository.showProductsByDESC(pageable);
//        model.addAttribute("productsPage", productsPage);
//
//        // Lấy danh sách ảnh
//        List<String> imagePaths = new ArrayList<>();
//        List<String> imageNames = new ArrayList<>();
//        for (Product product : productsPage.getContent()) {
//            // Thêm đường dẫn ảnh vào danh sách
//            imagePaths.add("/images/" + product.getPicture());
//            // Thêm tên ảnh vào danh sách
//            imageNames.add(product.getPicture());
//        }
//        // Thêm danh sách đường dẫn ảnh và tên ảnh vào model
//        model.addAttribute("imagePaths", imagePaths);
//        model.addAttribute("imageNames", imageNames);
//
//        return "user/listCategoryAndProduct";
//    }
    @GetMapping("/create")
    public String showFormCreateProduct(Model model){
        model.addAttribute("product",new ProductForm());
        model.addAttribute("categoryList",categoryService.showAll());
        return "/user/createProduct";
    }
    @PostMapping("/add/{name}/{password}")
    public String createProduct(@ModelAttribute ProductForm productForm , @PathVariable("name") String name , @PathVariable("password") String password , RedirectAttributes redirectAttributes){
        productService.save(productForm,name,password,productForm.getIdCategory() );
        redirectAttributes.addFlashAttribute("message", "Thêm sản phẩm thành công!");
        return "redirect:/products";
    }
    @GetMapping("/delete/{id}")
    public String showFormDelete(Model model , @PathVariable("id") int id){
       Optional<Product> productOptional = productService.findObjectById(id);
       model.addAttribute("product",productOptional.get());
       return "/user/deleteProduct";
    }
    @PostMapping("/delete/{name}/{password}")
    public String deleteProduct(@ModelAttribute Product product , @PathVariable("name") String name , @PathVariable("password") String password , RedirectAttributes redirectAttributes){
        productService.delete(product.getId(),name,password);
        redirectAttributes.addFlashAttribute("message", "xoa sản phẩm thành công!");
        return "redirect:/products";
    }
    @GetMapping("/update/{id}")
    public String showFormUpdateProduct(Model model , @PathVariable("id") int id){
        Optional<Product> productOptional = productService.findObjectById(id);
        model.addAttribute("product",productOptional.get());
        return "user/updateProduct";
    }
    @PostMapping("/update/{name}/{password}")
    public String updateProduct(@ModelAttribute ProductForm productForm,@PathVariable("name") String name,@PathVariable("password") String password , RedirectAttributes redirectAttributes){
        productService.update(productForm.getId(),productForm,name,password);
        redirectAttributes.addFlashAttribute("message", "cap nhat sản phẩm thành công!");
        return "redirect:/products";
    }
    @PostMapping("/search")
    public String searchProduct(@RequestParam("keyWord") String keyword , Model model){
        Iterable<Product> productIterable = productService.findProduct(keyword);
        List<String> imagePaths = new ArrayList<>();
        List<String> imageNames = new ArrayList<>();
        for (Product product : productIterable) {
            // Thêm đường dẫn ảnh vào danh sách
            imagePaths.add("/images/" + product.getPicture());
            // Thêm tên ảnh vào danh sách
            imageNames.add(product.getPicture());
        }
        // Thêm danh sách đường dẫn ảnh và tên ảnh vào model
        model.addAttribute("imagePaths", imagePaths);
        model.addAttribute("imageNames", imageNames);
        model.addAttribute("products",productIterable);
        return "/user/search";
    }

}
