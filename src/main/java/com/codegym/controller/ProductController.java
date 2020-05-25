package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.model.Supplier;
import com.codegym.service.product.ProductService;
import com.codegym.service.supplier.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private SupplierService supplierService;

    @ModelAttribute("suppliers")
    public Iterable<Supplier> suppliers(){
        return supplierService.findAll();
    }
    @GetMapping
    public ModelAndView listCustomer(@RequestParam("first") Optional<String> first, Pageable pageable) {
        Page<Product> products;
        if (first.isPresent()){
            products = productService.findAllByName(first.get(), pageable);
        }else {
         products = productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreateProduct(){
        ModelAndView modelAndView = new ModelAndView("product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveCustomer(@ModelAttribute("product") Product product){
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("product/create");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("message", "New product created successfully");
        return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showEditLaptop(@PathVariable Long id){
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()){
            ModelAndView modelAndView = new ModelAndView("product/edit");
            modelAndView.addObject("product", product.get());
            return modelAndView;
        } else {
            return new ModelAndView("product/error");
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("product/edit");
        modelAndView.addObject("product", product);
        modelAndView.addObject("message", "Product Update successful");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showFormDelete(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()){
            ModelAndView modelAndView = new ModelAndView("product/delete");
            modelAndView.addObject("product", product.get());
            return modelAndView;
        } else {
            return new ModelAndView("product/error");
        }
    }

    @PostMapping("/delete")
    public String deleteMaterial(@ModelAttribute("product") Product product) {
        productService.remove(product.getId());
        return "redirect:product";
    }


}
