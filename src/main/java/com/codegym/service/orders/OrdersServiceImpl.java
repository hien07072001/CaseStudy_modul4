package com.codegym.service.orders;

import com.codegym.model.Orders;
import com.codegym.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrdersServiceImpl  implements OrdersService{
    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public Page<Orders> findAll(Pageable pageable) {
        return ordersRepository.findAll(pageable);
    }

    @Override
    public Page<Orders> findAllByDatesOrders(String date, Pageable pageable) {
        return ordersRepository.findAllByDate(date, pageable);
    }

    @Override
    public Optional<Orders> findById(Long id) {
        return ordersRepository.findById(id);
    }

    @Override
    public void save(Orders orders) {
        ordersRepository.save(orders);


    }

    @Override
    public void remote(Long id) {
        ordersRepository.deleteById(id);

    }
}
