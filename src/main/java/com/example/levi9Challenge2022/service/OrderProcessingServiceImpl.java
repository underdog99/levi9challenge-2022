/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.levi9Challenge2022.service;

import com.example.levi9Challenge2022.model.Order;
import com.example.levi9Challenge2022.model.Status;
import com.example.levi9Challenge2022.model.Trade;
import com.example.levi9Challenge2022.model.Type;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mladen
 */
@Service
public class OrderProcessingServiceImpl implements OrderProcessingService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private TradeService tradeService;

    @Autowired
    private OrderbookService obService;

    @Override
    public void orderBuyProc(Order buy) {
        if (buy.getStatus().equals(Status.OPEN) && buy.getType().equals(Type.BUY)) {

            List<Order> list = obService.getSellOrdes()
                    .stream()
                    .filter(s -> s.getPrice() <= buy.getPrice() && s.getFilledQuantity() < buy.getQuantity())
                    .collect(Collectors.toList());

            System.out.println("Sell orderi4: " + list.toString());
            System.out.println("Size4: " + list.size());
            System.out.println("Buy Cena: " + buy.getPrice());

            for (Order os : list) {
                if (buy.getStatus().equals(Status.OPEN) && os.getFilledQuantity() < buy.getQuantity()) {

                    if (buy.getQuantity() > os.getQuantity()) {
                        float tmp = os.getQuantity();
                        os.setQuantity(0);
                        os.setFilledQuantity(tmp);
                        buy.setFilledQuantity(tmp);
                        buy.setQuantity(buy.getQuantity() - tmp);
                    } else if (buy.getQuantity() < os.getQuantity()) {
                        os.setQuantity(os.getQuantity() - buy.getQuantity());
                        buy.setFilledQuantity(os.getQuantity());
                        os.setFilledQuantity(buy.getQuantity());
                        buy.setQuantity(0);
                    } else {
                        buy.setFilledQuantity(os.getQuantity());
                        buy.setQuantity(0);
                        os.setFilledQuantity(os.getQuantity());
                        os.setQuantity(0);
                    }

                    if (os.getFilledQuantity() >= os.getQuantity()) {
                        System.out.println("Zatvori SELL order: " + os.getId());
                        os.setFilledQuantity(os.getQuantity());
                        os.setStatus(Status.CLOSED);

                        orderService.updateOrder(os);
                    }

                    if (buy.getFilledQuantity() == buy.getQuantity()) {
                        System.out.println("Zatvori BUY order: " + buy.getId());
                        buy.setStatus(Status.CLOSED);

                        orderService.updateOrder(buy);
                    }

                    System.out.println("Trade izmedju: " + buy.getId() + " i " + os.getId());
                    System.out.println("BO: " + buy.getFilledQuantity());
                    System.out.println("BO q: " + buy.getQuantity());

                    System.out.println("SO: " + os.getFilledQuantity());
                    System.out.println("SO q: " + os.getQuantity());

                    Trade t = new Trade();
                    t.setBuyOrder(buy);
                    t.setSellOrder(os);
                    t.setCreatedDateTime(LocalDateTime.now());
                    t.setPrice(os.getPrice());
                    t.setQuantity(os.getQuantity());
                    t.setOrder(buy);

                    tradeService.saveTrade(t);
                }

            }
        }
    }

    @Override
    public void orderSellProc(Order sell) {
        if (sell.getStatus().equals(Status.OPEN) && sell.getType().equals(Type.SELL)) {

            List<Order> list = obService.getBuyOrdes()
                    .stream()
                    .filter(b -> b.getPrice() <= sell.getPrice() && b.getFilledQuantity() >= sell.getQuantity())
                    .collect(Collectors.toList());

            for (Order ob : list) {
                if (sell.getStatus().equals(Status.OPEN) && ob.getFilledQuantity() < sell.getQuantity()) {

                    if (sell.getQuantity() > ob.getQuantity()) {
                        float tmp = ob.getQuantity();
                        ob.setQuantity(0);
                        ob.setFilledQuantity(tmp);
                        sell.setFilledQuantity(tmp);
                        sell.setQuantity(sell.getQuantity() - tmp);
                    } else if (sell.getQuantity() < ob.getQuantity()) {
                        ob.setQuantity(ob.getQuantity() - sell.getQuantity());
                        sell.setFilledQuantity(ob.getQuantity());
                        ob.setFilledQuantity(sell.getQuantity());
                        sell.setQuantity(0);
                    } else {
                        sell.setFilledQuantity(ob.getQuantity());
                        sell.setQuantity(0);
                        ob.setFilledQuantity(ob.getQuantity());
                        ob.setQuantity(0);
                    }

                    if (ob.getFilledQuantity() >= ob.getQuantity()) {
                        System.out.println("Zatvori BUY order: " + ob.getId());
                        ob.setFilledQuantity(ob.getQuantity());
                        ob.setStatus(Status.CLOSED);

                        orderService.updateOrder(ob);
                    }

                    if (sell.getFilledQuantity() == sell.getQuantity()) {
                        System.out.println("Zatvori SELL order: " + sell.getId());
                        sell.setStatus(Status.CLOSED);

                        orderService.updateOrder(sell);
                    }

                    Trade t = new Trade();
                    t.setBuyOrder(sell);
                    t.setSellOrder(ob);
                    t.setCreatedDateTime(LocalDateTime.now());
                    t.setPrice(ob.getPrice());
                    t.setQuantity(ob.getQuantity());
                    t.setOrder(sell);

                    tradeService.saveTrade(t);
                }

            }
        }
    }

    @Override
    public void orderClosed(Order o) {
        if (o.getFilledQuantity() == o.getQuantity()) {
            o.setStatus(Status.CLOSED);
            orderService.updateOrder(o);
        }
    }

}
