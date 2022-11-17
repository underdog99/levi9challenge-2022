/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.levi9Challenge2022.model;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Mladen
 */


@Entity
@Table(name = "trade")
public class Trade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idTrade")
    private int id;
    
    @Column(name = "createdDateTimeTrade")
    private LocalDateTime createdDateTime;
    
    @Column(name = "priceTrade")
    private float price;
    
    @Column(name = "quantityTrade")
    private float quantity;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idOrder")
    private Order order;
    
    //@OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "buyOrder_id", referencedColumnName = "idOrder")
    @OneToOne(mappedBy = "tbo")
    private Order buyOrder;
    
    //@OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "sellOrder_id", referencedColumnName = "idOrder")
    @OneToOne(mappedBy = "tso")
    private Order sellOrder;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getBuyOrder() {
        return buyOrder;
    }

    public void setBuyOrder(Order buyOrder) {
        this.buyOrder = buyOrder;
    }

    public Order getSellOrder() {
        return sellOrder;
    }

    public void setSellOrder(Order sellOrder) {
        this.sellOrder = sellOrder;
    }
    
    
}
