package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    public ModelAndView user(Principal principal, Pageable pageable) {
        // Get authenticated user name from Principal
        System.out.println(principal.getName());
        Page<Product> products = productService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/admin")
    public String admin() {
        // Get authenticated user name from SecurityContext
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println(context);
        return "/product/admin";
    }
//    @GetMapping("/articles")
//    public String getArticlesPage () {
//        return "articler";
//
//    }
//    @GetMapping("/accessDenied")
//    public String getAccessDeniedPage() {
//        return "accessDenied";
//    }
//    @GetMapping("/admin/monitor")
//    public String getAdminMonitorPage() {
//        return "admin";
//    }


}
