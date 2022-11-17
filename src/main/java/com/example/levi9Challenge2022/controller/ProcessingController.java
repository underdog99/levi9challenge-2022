/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.levi9Challenge2022.controller;

import com.example.levi9Challenge2022.model.Order;
import com.example.levi9Challenge2022.model.Orderbook;
import com.example.levi9Challenge2022.model.Status;
import com.example.levi9Challenge2022.model.Type;
import com.example.levi9Challenge2022.service.OrderProcessingService;
import com.example.levi9Challenge2022.service.OrderService;
import com.example.levi9Challenge2022.service.OrderbookService;
import com.example.levi9Challenge2022.service.TradeService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mladen
 */
@RestController
public class ProcessingController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private TradeService tradeService;

    @Autowired
    private OrderbookService obService;

    @Autowired
    private OrderProcessingService opService;

    @GetMapping("/order")
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/order/{id}")
    public ResponseEntity getOrder(@PathVariable("id") int id) {
        Order o = orderService.getOrder(id);

        if (o == null) {
            return new ResponseEntity("Order ne postoji sa id: " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(o, HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity saveOrder(@RequestBody Order o) {
        if (o != null) {
            if (o.getQuantity() >= 0) {
                if (o.getPrice() >= 0) {
                    o.setStatus(Status.OPEN);
                    o.setCreatedDateTime(LocalDateTime.now());
                    o.setFilledQuantity(0);

                    if (o.getType().toString() == "BUY") {
                        o.setType(Type.BUY);
                    } else if (o.getType().toString() == "SELL") {
                        o.setType(Type.SELL);
                    } else {
                        o.setType(null);
                    }

                    //o.setTrades(null);
                    orderService.saveOrder(o);
                    //opService.orderBuyProc(o);

                    if (o.getType().equals(Type.BUY)) {
                        opService.orderBuyProc(o);
                    } else if (o.getType().equals(Type.SELL)) {
                        opService.orderSellProc(o);
                    } else {

                    }

                    return new ResponseEntity(o, HttpStatus.CREATED);
                }
            }
        }

        return new ResponseEntity("Greska!", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/order/all")
    public ResponseEntity deleteAll() {
        orderService.deleteOrder();
        tradeService.deleteTrade();

        return new ResponseEntity("Uspesno brisanje", HttpStatus.OK);
    }

    @GetMapping("/orderbook")
    public ResponseEntity getOrderbook() {
        List<Order> buyOrders = obService.getBuyOrdes();
        List<Order> sellOrders = obService.getSellOrdes();

        Orderbook ob = new Orderbook();
        ob.setBuyOrdes(buyOrders);
        ob.setSellOrdes(sellOrders);

        return new ResponseEntity(ob, HttpStatus.OK);
    }

}
