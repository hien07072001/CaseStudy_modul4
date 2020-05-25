package com.codegym;

import com.codegym.service.product.ProductService;
import com.codegym.service.product.ProductServiceImpl;
import com.codegym.service.supplier.SupplierService;
import com.codegym.service.supplier.SupplierServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class ClockApplication {
//    @Bean
//    public ProductService productService(){
//        return new ProductServiceImpl();
//    }
//
//    @Bean
//    public SupplierService supplierService(){
//        return new SupplierServiceImpl();
//    }

    public static void main(String[] args) {
        SpringApplication.run(ClockApplication.class, args);
    }

}
