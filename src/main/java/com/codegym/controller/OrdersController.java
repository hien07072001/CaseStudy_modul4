package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Orders;
import com.codegym.model.Product;
import com.codegym.repository.CustomerRepository;
import com.codegym.repository.OrdersRepository;
import com.codegym.service.customer.CustomerService;
import com.codegym.service.orders.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private OrdersRepository ordersRepo;

    @ModelAttribute("customers")
    public Page<Customer> customers(Pageable pageable){
        return customerService.findAll(pageable);
    }

    @GetMapping
    public ModelAndView listOrders( @RequestParam("orders") Optional<String> date,
                                    @RequestParam(required = false) Long id,
                                    Pageable pageable){
        Page<Orders> orders;
        if (date .isPresent()){
            orders = ordersService.findAllByDatesOrders(date.get(), pageable);
        }else {
            orders = ordersService.findAll(pageable);
        }

        Optional<Customer> customer = Objects.nonNull(id)
                ? customerService.findById(id)
                : Optional.empty();

        if (customer.isPresent()){
            orders = new PageImpl<>(customer.get().getOrders());
        }

        ModelAndView modelAndView = new ModelAndView("/orders/list");
        modelAndView.addObject("orders", orders);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showFormCreateOrders(){
        ModelAndView modelAndView = new ModelAndView("orders/create");
        modelAndView.addObject("orders", new Orders());
        return modelAndView;
    }

    @PostMapping("/create")
    public RedirectView saveOrders(@ModelAttribute("orders") Orders orders, RedirectAttributes redirect){
        ordersService.save(orders);
        redirect.addFlashAttribute("message", "create orders successfully !");
        return new RedirectView("/orders");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditOrders(@PathVariable Long id){
        Optional<Orders> orders = ordersService.findById(id);
        if (orders.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/orders/edit");
            modelAndView.addObject("orders", orders.get());
            return modelAndView;
        } else {
            return new ModelAndView("/orders/error");
        }
    }

    @PostMapping("/edit")
    public RedirectView editOrders(@ModelAttribute("orders") Orders orders, RedirectAttributes redirect) throws Exception {
        ordersService.save(orders);
        redirect.addFlashAttribute("message", "Edit orders successfully !" );
        return new RedirectView("/orders");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showFormDeleteOrders(@PathVariable Long id){
        Optional<Orders> orders = ordersService.findById(id);
        if (orders.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/orders/delete");
            modelAndView.addObject("orders", orders.get());
            return modelAndView;
        }else {
            return new ModelAndView("/orders/error");
        }
    }

    @PostMapping("/delete")
    public RedirectView deleteOrders(@ModelAttribute("orders") Orders orders, RedirectAttributes redirect){
        ordersService.remote(orders.getId());
        redirect.addFlashAttribute("message", "delete orders successfully !");
        return new RedirectView("/orders");
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewOrders(@PathVariable Long id) {
        Optional<Orders> orders = ordersService.findById(id);
        if (orders.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/orders/view");
            modelAndView.addObject("orders", orders.get());
            return modelAndView;
        } else {
            return new ModelAndView("/orders/error");
        }
    }
}