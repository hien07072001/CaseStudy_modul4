package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

public class ClockForm {
    private long id ;
    private MultipartFile avatar;
    private String name;
    private double price;
    private String description;
    private Orders orders;
    private Customer customer;


    public ClockForm() {
    }

    public ClockForm(long id, MultipartFile avatar, String name, double price, String description, Orders orders, Customer customer) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.price = price;
        this.description = description;
        this.orders = orders;
        this.customer = customer;
    }

    public ClockForm(MultipartFile avatar, String name, double price, String description, Orders orders, Customer customer) {
        this.avatar = avatar;
        this.name = name;
        this.price = price;
        this.description = description;
        this.orders = orders;
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
