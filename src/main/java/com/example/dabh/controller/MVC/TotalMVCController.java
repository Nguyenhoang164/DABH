package com.example.dabh.controller.MVC;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/total")
public class TotalMVCController {
    @GetMapping("")
    public String showTotalProduct(){
        return "user/total/totalProduct";
    }
}
