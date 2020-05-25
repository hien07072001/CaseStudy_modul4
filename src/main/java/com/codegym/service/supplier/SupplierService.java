package com.codegym.service.supplier;

import com.codegym.model.Supplier;

import java.util.Optional;

public interface SupplierService {
    Iterable<Supplier> findAll();

    Optional<Supplier> findById(Long id);

    void save(Supplier supplier);

    void delete(Long id);
}
