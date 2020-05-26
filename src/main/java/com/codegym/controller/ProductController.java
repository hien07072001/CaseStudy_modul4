package com.codegym.controller;

import com.codegym.model.ClockForm;
import com.codegym.model.Customer;
import com.codegym.model.Orders;
import com.codegym.model.Product;
import com.codegym.service.customer.CustomerService;
import com.codegym.service.orders.OrdersService;
import com.codegym.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
     private Environment ev;
    @Autowired
    private ProductService productService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private CustomerService customerService;

    @ModelAttribute("orders")
    public Page<Orders> findAll(Pageable pageable){
        return ordersService.findAll(pageable);
    }

    @ModelAttribute("customers")
    public Page<Customer> findAllC(Pageable pageable){
        return customerService.findAll(pageable);
    }

    @GetMapping
    public ModelAndView listProduct(@RequestParam("s") Optional<String> s, Pageable pageable) {
        Page<Product> products;
        if (s.isPresent()){
            products = productService.findAllByName(s.get(), pageable);
        }else {
         products = productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showFormCreateProducer(){
        ModelAndView modelAndView = new ModelAndView("product/create");
        modelAndView.addObject("clockForm", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public RedirectView saveProducer(@ModelAttribute("clockForm") ClockForm clockForm, BindingResult bindingResult, RedirectAttributes redirect){
        if (bindingResult.hasErrors()){
            System.out.println("Result Error Occured" + bindingResult.getAllErrors());
        }
        //Lay ten file
        MultipartFile multipartFile = clockForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = ev.getProperty("file_upload").toString();
//        /Luu file len server
        try {
            FileCopyUtils.copy(clockForm.getAvatar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Tao doi tuong de luu vao database
        Product product = new Product(fileName,clockForm.getName(),clockForm.getPrice(), clockForm.getDescription(), clockForm.getOrders(), clockForm.getCustomer());
        productService.save(product);
        redirect.addFlashAttribute("message", "Create producer successfully !");
        return new RedirectView("/product");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditProducer(@PathVariable Long id){
        Optional<Product> producer = productService.findById(id);
        ClockForm clockForm= new ClockForm();

        if (producer.isPresent()){
            clockForm.setId(producer.get().getId());
            clockForm.setName(producer.get().getName());
            clockForm.setPrice(producer.get().getPrice());

            clockForm.setDescription(producer.get().getDescription());

            clockForm.setOrders(producer.get().getOrders());
            clockForm.setCustomer(producer.get().getCustomer());

            ModelAndView modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("clockForm", clockForm);
            return modelAndView;
        }else {
            return new ModelAndView("/product/error");
        }
    }

    @PostMapping("/edit")
    public RedirectView editProducer(@ModelAttribute("clockForm") ClockForm clockForm,BindingResult bindingResult, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            System.out.println("Result Error Occured" + bindingResult.getAllErrors());
        }
        MultipartFile multipartFile = clockForm.getAvatar();

        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = ev.getProperty("file_upload").toString();

        if (fileName.equals("")) {
            Product product = new Product(clockForm.getId(), fileName,clockForm.getName(), clockForm.getPrice(), clockForm.getDescription(), clockForm.getOrders(), clockForm.getCustomer());
            productService.save(product);
            redirect.addFlashAttribute("message", "edit laptop error !");
            return new RedirectView("/product");
        } else {
            try {
                FileCopyUtils.copy(clockForm.getAvatar().getBytes(), new File(fileUpload + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Product product = new Product(clockForm.getId(),fileName, clockForm.getName(), clockForm.getPrice(), clockForm.getDescription(), clockForm.getOrders(), clockForm.getCustomer());
            productService.save(product);
            redirect.addFlashAttribute("message", "Edit producer successfully !");
            return new RedirectView("/product");
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showFormDeleteProducer(@PathVariable Long id){
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("product", product.get());
            return modelAndView;
        }else {
            return new ModelAndView("/product/error");
        }
    }

    @PostMapping("/delete")
    public RedirectView deleteProducer(@ModelAttribute("product") Product product, RedirectAttributes redirect){
        productService.remove(product.getId());
        redirect.addFlashAttribute("message", "Delete producer successfully !" );
        return new RedirectView("/product");
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewProducer(@PathVariable Long id){
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/product/view");
            modelAndView.addObject("product", product.get());
            return modelAndView;
        }
        return new ModelAndView("/product/error");
    }

}
