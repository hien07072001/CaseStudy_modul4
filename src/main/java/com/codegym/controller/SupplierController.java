package com.codegym.controller;

import com.codegym.model.Supplier;
import com.codegym.service.product.ProductService;
import com.codegym.service.supplier.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ProductService productService;

    @GetMapping("/suppliers")
    public ModelAndView listSupplier(){
        Iterable<Supplier> suppliers=supplierService.findAll();
        ModelAndView modelAndView=new ModelAndView("supplier/list");
        modelAndView.addObject("suppliers",suppliers);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView=new ModelAndView("supplier/create","supplier",new Supplier());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView saveSupplier(@ModelAttribute("supplier") Supplier supplier ){
        supplierService.save(supplier);
        ModelAndView modelAndView = new ModelAndView("supplier/create");
        modelAndView.addObject("supplier", new Supplier());
        modelAndView.addObject("message", "New Supplier created successfully");
        return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showEditSupplier(@PathVariable Long id){
        Optional<Supplier> supplier = supplierService.findById(id);
        if (supplier.isPresent()){
            ModelAndView modelAndView = new ModelAndView("supplier/edit");
            modelAndView.addObject("supplier", supplier.get());
            return modelAndView;
        } else {
            return new ModelAndView("supplier/error");
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateSupplier(@ModelAttribute("supplier") Supplier supplier) {
        supplierService.save(supplier);
        ModelAndView modelAndView = new ModelAndView("supplier/edit");
        modelAndView.addObject("supplier", supplier);
        modelAndView.addObject("message", "Supplier Update successful");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showFormDelete(@PathVariable Long id) {
        Optional<Supplier> supplier = supplierService.findById(id);
        if (supplier.isPresent()){
            ModelAndView modelAndView = new ModelAndView("supplier/delete");
            modelAndView.addObject("supplier", supplier.get());
            return modelAndView;
        } else {
            return new ModelAndView("supplier/error");
        }
    }

    @PostMapping("/delete")
    public String deleteSupplier(@ModelAttribute("supplier") Supplier supplier) {
        productService.remove(supplier.getId());
        return "redirect:suppliers";
    }


}
