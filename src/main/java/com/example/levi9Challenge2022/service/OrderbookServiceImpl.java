/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.levi9Challenge2022.service;

import com.example.levi9Challenge2022.dao.OrderDao;
import com.example.levi9Challenge2022.model.Order;
import com.example.levi9Challenge2022.model.Status;
import com.example.levi9Challenge2022.model.Type;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mladen
 */
@Service
public class OrderbookServiceImpl implements OrderbookService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> getBuyOrdes() {
        List<Order> list = orderDao.getOrders()
                .stream()
                .filter(bo -> bo.getStatus().equals(Status.OPEN) && bo.getType().equals(Type.BUY))
                .collect(Collectors.toList());

        list.stream()
                .sorted(Comparator.comparingDouble(Order::getPrice).reversed())
                .collect(Collectors.toList());

        return list;
    }

    @Override
    public List<Order> getSellOrdes() {
        List<Order> list = orderDao.getOrders()
                .stream()
                .filter(so -> so.getStatus().equals(Status.OPEN) && so.getType().equals(Type.SELL))
                .collect(Collectors.toList());

        list.stream()
                .sorted(Comparator.comparingDouble(Order::getPrice))
                .collect(Collectors.toList());

        return list;
    }

}
