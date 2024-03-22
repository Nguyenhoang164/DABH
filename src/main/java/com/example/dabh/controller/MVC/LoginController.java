package com.example.dabh.controller.MVC;

import com.example.dabh.model.Customer;
import com.example.dabh.model.Users;
import com.example.dabh.service.ICustomerService;
import com.example.dabh.service.IUserService;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ICustomerService customerService;
    @PostMapping("/login")
    public String loginToPage(@RequestParam("name") String name , @RequestParam("password") String password , HttpSession session){
        Optional<Users> users = userService.findUserByName(name);
        if (users.isEmpty()){
            Optional<Customer> customer = customerService.findCustomer(name);
            if (customer.isEmpty()){
                return "/login";
            }else{
                if (customer.get().getPassword().equals(password)){
                   session.setAttribute("customer",customer);
                    return "redirect:/products";
                }
            }
        }else {
            if (users.get().getPassword().equals(password)){
                session.setAttribute("users",users);
                return "redirect:/product";
            }else {
                return "/login";
            }
        }
        return "/login";
    }
}
