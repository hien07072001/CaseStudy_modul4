package com.codegym.service.orders;

import com.codegym.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrdersServiceImpl  implements OrdersService{
    @Override
    public Page<Orders> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Orders> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Orders orders) {

    }

    @Override
    public void remote(Long id) {

    }
}
