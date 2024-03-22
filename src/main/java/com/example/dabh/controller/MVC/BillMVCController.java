package com.example.dabh.controller.MVC;

import com.example.dabh.model.*;
import com.example.dabh.repository.IBillRepository;
import com.example.dabh.repository.ICustomerRepository;
import com.example.dabh.repository.IProductRepository;
import com.example.dabh.service.*;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/bill")
public class BillMVCController {
    @Autowired
    private IBillService billService;
    @Autowired
    private ICartService cartService;
    @Autowired
    private IProductService iProductService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IBillRepository billRepository;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IUserService userService;

    @PostMapping("/{id}")
    public String showFormBill(HttpSession httpSession, @PathVariable("id") int idCustomer, Model model) {
        List<Cart> carts = cartService.getCart(httpSession, idCustomer);
        List<Product> productList = new ArrayList<>();
        for (Cart cart : carts) {
            Optional<Product> productOptional = iProductService.findObjectById(cart.getProduct().getId());
            productList.add(productOptional.get());
        }
        Optional<Customer> customerOptional = customerService.findObjectById(idCustomer);
        billService.save(productList, customerOptional.get());
        Iterable<Bill> billIterable = billRepository.findAllByCustomer(customerOptional.get());

        // Tạo danh sách chứa tất cả các sản phẩm từ tất cả các hóa đơn
        List<Product> allProducts = productRepository.findAllProductsByCustomer(customerOptional.get());

        // Đưa danh sách sản phẩm vào model
        model.addAttribute("products", allProducts);
        // Đưa dữ liệu vào model
        model.addAttribute("billsBuy", billIterable);
        model.addAttribute("customersBuy", customerOptional.get());
        return "customer/bill";
    }

    @GetMapping("/show/{id}")
    public String showBill(@PathVariable("id") int idCustomer, Model model) {
        Optional<Customer> customerOptional = customerService.findObjectById(idCustomer);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();

            // Lấy tất cả các hóa đơn của khách hàng
            Iterable<Bill> billIterable = billRepository.findAllByCustomer(customer);

            // Tạo danh sách chứa tất cả các sản phẩm từ tất cả các hóa đơn
            List<Product> allProducts = productRepository.findAllProductsByCustomer(customer);

            // Đưa danh sách sản phẩm vào model
            model.addAttribute("products", allProducts);
            // Đưa dữ liệu vào model
            model.addAttribute("bills", billIterable);
            model.addAttribute("customers", customer);

            // Trả về view
            return "customer/bill";
        } else {
            // Xử lý trường hợp không tìm thấy khách hàng
            // Ví dụ: thông báo lỗi, chuyển hướng, hoặc xử lý khác tùy theo yêu cầu của ứng dụng của bạn
            return "redirect:/products";
        }

    }
    //*------------------------Admin------------------------
    @GetMapping("/showAllBill/{name}/{password}")
    public String showAllBill(@PathVariable("name") String name , @PathVariable("password") String password,Model model){
        Iterable<Bill> billIterable = billRepository.findAll();
        model.addAttribute("bills",billIterable);
        return "user/bill/showBill";
    }
    @GetMapping("/updateBill/{id}/{name}/{password}")
    public String updateBillUser(@PathVariable("id") int id,@PathVariable("name") String name , @PathVariable("password") String password,Model model){
        Optional<Bill> billOptional = billRepository.findById(id);
        Optional<Users> userIterable = userService.findUserByName(name);
        Iterable<Users> usersIterable = userService.showAll();
        for (Users users : usersIterable){
            if (userIterable.get().getNameUser().equals(users.getNameUser())){
                if (userIterable.get().getPassword().equals(password)){
                    billService.updateBillForCustomer(billOptional.get(),id);
                }
            }
        }
        return "/user/bill/showBill";
    }
}
