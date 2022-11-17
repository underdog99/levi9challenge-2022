/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.levi9Challenge2022.model;

import java.util.List;



/**
 *
 * @author Mladen
 */

public class Orderbook {
    
    private List <Order> buyOrdes;
    
    private List <Order> sellOrdes;


    
    public List<Order> getBuyOrdes() {
        return buyOrdes;
    }

    public void setBuyOrdes(List<Order> buyOrdes) {
        this.buyOrdes = buyOrdes;
    }

    public List<Order> getSellOrdes() {
        return sellOrdes;
    }

    public void setSellOrdes(List<Order> sellOrdes) {
        this.sellOrdes = sellOrdes;
    }
    
    
}
