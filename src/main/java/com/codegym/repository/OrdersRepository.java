package com.codegym.repository;

import com.codegym.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends PagingAndSortingRepository<Orders,Long> {

    Page<Orders> findAllByDate(String date, Pageable pageable);
}
