/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.levi9Challenge2022.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



/**
 *
 * @author Mladen
 */


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "orderCrypto")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idOrder")
    private int id;
    
    @Column(name = "currencyPairOrder")
    private String currencyPair;
    
    @Column(name = "createdDateTimeOrder")
    private LocalDateTime createdDateTime;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "typeOrder")
    private Type type;
    
    @Column(name = "priceOrder")
    private float price;
    
    @Column(name = "quantityOrder")
    private float quantity;
    
    @Column(name = "filledQuantityOrder")
    private float filledQuantity;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "statusOrder")
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
    private List <Trade> trades;
    
    //@OneToOne(mappedBy = "buyOrder")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "buyOrder_id", referencedColumnName = "idTrade")
    private Trade tbo;
    
    //@OneToOne(mappedBy = "sellOrder")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sellOrder_id", referencedColumnName = "idTrade")
    private Trade tso;
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    public float getFilledQuantity() {
        return filledQuantity;
    }

    public void setFilledQuantity(float filledQuantity) {
        this.filledQuantity = filledQuantity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Trade> getTrades() {
        return trades;
    }

    public void setTrades(List<Trade> trades) {
        this.trades = trades;
    }

    public Trade getTbo() {
        return tbo;
    }

    public void setTbo(Trade tbo) {
        this.tbo = tbo;
    }

    public Trade getTso() {
        return tso;
    }

    public void setTso(Trade tso) {
        this.tso = tso;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", currencyPair=" + currencyPair + ", createdDateTime=" + createdDateTime + ", type=" + type + ", price=" + price + ", quantity=" + quantity + ", filledQuantity=" + filledQuantity + ", status=" + status + ", trades=" + trades + ", tbo=" + tbo + ", tso=" + tso + '}';
    }
    
    
    
}
