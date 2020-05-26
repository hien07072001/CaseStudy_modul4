package com.codegym.model;

import javax.persistence.*;

@Entity

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String avatar;
    private double price;
    private String description;

    public Product() {
    }

    public Product(String name, String avatar, double price, String description, Orders orders, Customer customer) {
        this.name = name;
        this.avatar = avatar;
        this.price = price;
        this.description = description;
        this.orders = orders;
        this.customer = customer;
    }

    public Product(Long id, String name, String avatar, double price, String description, Orders orders, Customer customer) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.price = price;
        this.description = description;
        this.orders = orders;
        this.customer = customer;
    }

    @ManyToOne
    private Orders orders;

    @ManyToOne
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
