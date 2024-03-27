package com.example.dabh.controller.MVC;


import com.example.dabh.model.Product;
import com.example.dabh.model.ProductForm;
import com.example.dabh.repository.IProductRepository;
import com.example.dabh.service.ICartService;
import com.example.dabh.service.ICategoryService;
import com.example.dabh.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/test")
@SessionAttributes("customer")
public class TestMVCTemplate {

//    @GetMapping("")
//    public String index(){
//        return "tryWeb/index";
//    }
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private ICartService cartService;

    @RequestMapping("{id}")
    public String showFormUpdateProduct(Model model , @PathVariable("id") int id){
//        Optional<Product> productOptional = productService.findObjectById(id);
//        model.addAttribute("product",productOptional.get());
//        model.addAttribute("categoryList",categoryService.showAll());

        return "tryWeb/doneTry/updateProduct";
    }

//    @GetMapping("{id}")
//    public String showProductList(Model model, @PageableDefault(6) Pageable pageable, @PathVariable("id") int idCustomer) {
//        // Lấy trang sản phẩm từ service
//        Page<Product> productsPage = productService.findAll(pageable);
//
//        model.addAttribute("productsPage", productsPage);
//        model.addAttribute("id",idCustomer);
//
//        // Lấy danh sách ảnh
//        List<String> imagePaths = new ArrayList<>();
//        List<String> imageNames = new ArrayList<>();
//        for (Product product : productsPage.getContent()) {
//            // Thêm đường dẫn ảnh vào danh sách
//            imagePaths.add( product.getPicture());
//            // Thêm tên ảnh vào danh sách
//            imageNames.add(product.getPicture());
//        }
//        // Thêm danh sách đường dẫn ảnh và tên ảnh vào model
//        model.addAttribute("imagePaths", imagePaths);
//        model.addAttribute("imageNames", imageNames);
//
//        return "user/listCategoryAndProduct";
//    }
//    CART CRUD
//    @GetMapping("/cart/{id}")
//    public ModelAndView addToCart(HttpSession session, @PathVariable("id") int idProducts){
//        Optional<Customer> customer = (Optional<Customer>) session.getAttribute("customer");
//        if (customer == null){
//            return new ModelAndView("/login");
//        }
//        cartService.addToCart(session,customer.get().getId(),idProducts);
//        ModelAndView modelAndView = new ModelAndView("redirect:/products/"+ customer.get().getId());
//        return modelAndView;
//    }
//    @GetMapping("/plus/{id}/{idCustomer}")
//    public ModelAndView plus(HttpSession session, @PathVariable("id") int idProducts, @PathVariable("idCustomer") int idCustomer){
//        cartService.addToCart(session,idCustomer,idProducts);
//        ModelAndView modelAndView = new ModelAndView("redirect:/products/showCart/"+idCustomer);
//        return modelAndView;
//    }
//
//    @GetMapping("/minus/{id}/{idCustomer}")
//    public ModelAndView minus(HttpSession session, @PathVariable("id") int idProducts, @PathVariable("idCustomer") int idCustomer){
//        cartService.minusOneProduct(session,idCustomer,idProducts);
//        ModelAndView modelAndView = new ModelAndView("redirect:/products/showCart/"+idCustomer);
//        return modelAndView;
//    }
//
//    @GetMapping("/showCart")
//    public ModelAndView showCart(HttpSession session){
//        Optional<Customer> customerOptional = (Optional<Customer>) session.getAttribute("customer");
//        List<Cart> carts = cartService.getCart(session,customerOptional.get().getId());
//        double total=0;
//        for(Cart c: carts){
//            total += c.getPrice();
//        }
//        ModelAndView modelAndView = new ModelAndView("/user/cart");
//        modelAndView.addObject("carts",carts);
//        modelAndView.addObject("id",customerOptional.get().getId());
//        modelAndView.addObject("total",total);
//        return modelAndView;
//    }
//END CART CRUD

//    PRODUCTS CRUD
//, @PageableDefault(2) Pageable pageable
    @GetMapping("")
    public ModelAndView showProductList(@RequestParam("status") String status) {
        // Lấy trang sản phẩm từ service
        ModelAndView modelAndView = new ModelAndView("tryWeb/doneTry/ListProduct");
//        Page<Product> productsPage = productService.findAll(pageable);
//        List<Product> productsPage = (List<Product>) productService.showAll();
//        modelAndView.addObject("productsPage", productsPage);
//
//        // Lấy danh sách ảnh
//        List<String> imagePaths = new ArrayList<>();
//        List<String> imageNames = new ArrayList<>();
//        for (Product product : productsPage.getContent()) {
//            // Thêm đường dẫn ảnh vào danh sách
//            imagePaths.add(product.getPicture());
//            // Thêm tên ảnh vào danh sách
//            imageNames.add(product.getPicture());
//        }
//        // Thêm danh sách đường dẫn ảnh và tên ảnh vào model
//        model.addAttribute("imagePaths", imagePaths);
//        model.addAttribute("imageNames", imageNames);

        return modelAndView;
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
//    @GetMapping("/create")
//    public String showFormCreateProduct(Model model){
//        model.addAttribute("product",new ProductForm());
//        model.addAttribute("categoryList",categoryService.showAll());
//        return "tryWeb/doneTry/createProduct";
//    }
//    @PostMapping("/add/{name}/{password}")
//    public String createProduct(Model model,@ModelAttribute ProductForm productForm , @PathVariable("name") String name , @PathVariable("password") String password ){
//        productService.save(productForm,name,password,productForm.getIdCategory() );
//        return "redirect:/test?status=ok";
//    }
//    @GetMapping("/delete/{id}/{name}/{password}")
//    public String showFormDelete( @PathVariable("id") int id, @PathVariable("name") String name , @PathVariable("password") String password){
//        Optional<Product> productOptional = productService.findObjectById(id);
//        productService.delete(productOptional.get().getId(),name,password);
//        return "redirect:/test";
//
//    }
//    @PostMapping("/delete/{name}/{password}")
//    public String deleteProduct(@ModelAttribute Product product , @PathVariable("name") String name , @PathVariable("password") String password){
//
//    }

//    @PostMapping("/update/{name}/{password}")
//    public String updateProduct(@ModelAttribute ProductForm productForm,@PathVariable("name") String name,@PathVariable("password") String password , RedirectAttributes redirectAttributes){
//        productService.update(productForm.getId(),productForm,name,password);
//        redirectAttributes.addFlashAttribute("message", "cap nhat sản phẩm thành công!");
//        return "redirect:/products";
//    }
//    @PostMapping("/search")
//    public String searchProduct(@RequestParam("keyWord") String keyword , Model model){
//        Iterable<Product> productIterable = productService.findProduct(keyword);
//        List<String> imagePaths = new ArrayList<>();
//        List<String> imageNames = new ArrayList<>();
//        for (Product product : productIterable) {
//            // Thêm đường dẫn ảnh vào danh sách
//            imagePaths.add("/images/" + product.getPicture());
//            // Thêm tên ảnh vào danh sách
//            imageNames.add(product.getPicture());
//        }
//        // Thêm danh sách đường dẫn ảnh và tên ảnh vào model
//        model.addAttribute("imagePaths", imagePaths);
//        model.addAttribute("imageNames", imageNames);
//        model.addAttribute("products",productIterable);
//        return "/user/search";
//    }
    //   END PRODUCTS CRUD
}
