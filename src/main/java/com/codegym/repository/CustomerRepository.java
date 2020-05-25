package com.codegym.repository;

import com.codegym.model.Customer;
import com.codegym.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long> {
    Page<Customer> findAllByName(String name, Pageable pageable);

    Customer findCustomerByOrders(Orders order);
}
