/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.levi9Challenge2022.service;

import com.example.levi9Challenge2022.dao.OrderDao;
import com.example.levi9Challenge2022.model.Order;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mladen
 */

@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderDao orderDao;
    
    
    @Override
    @Transactional
    public List<Order> getOrders() {
        return orderDao.getOrders();
    }

    @Override
    @Transactional
    public void saveOrder(Order o) {
        orderDao.saveOrder(o);
    }

    @Override
    @Transactional
    public Order getOrder(int id) {
        return orderDao.getOrder(id);
    }

    @Override
    @Transactional
    public void deleteOrder() {
        orderDao.deleteOrder();
    }

    @Override
    @Transactional
    public void updateOrder(Order o) {
        orderDao.updateOrder(o);
    }

    
}
