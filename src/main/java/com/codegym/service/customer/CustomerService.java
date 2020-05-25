package com.codegym.service.customer;

import com.codegym.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomerService {
    Page<Customer> findAll(Pageable pageable);
    Page<Customer> findAllByCustomerName(String name, Pageable pageable);

    Optional<Customer> findById(Long id);

    void save(Customer customer);

    void remote(Long id);
}


























