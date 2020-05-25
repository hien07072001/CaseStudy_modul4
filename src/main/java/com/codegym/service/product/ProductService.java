package com.codegym.service.product;

import com.codegym.model.Product;
import com.codegym.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {

    Page<Product> findAll(Pageable pageable);

    Page<Product> findAllByName(String name, Pageable pageable);

    Iterable<Product> findAllBySupplier(Supplier supplier);

    Optional<Product> findById(Long id);

    void save(Product product);

    void remove(Long id);
}
