package com.codegym.repository;

import com.codegym.model.Product;
import com.codegym.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {
    Iterable<Product> findAllBySupplier(Supplier supplier);

    Page<Product> findAllByName(String name , Pageable pageable);

}
