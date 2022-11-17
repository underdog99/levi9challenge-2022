/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.levi9Challenge2022.service;

import com.example.levi9Challenge2022.model.Order;
import java.util.List;

/**
 *
 * @author Mladen
 */


public interface OrderProcessingService {
    
    public void orderBuyProc(Order buy);
    
    public void orderSellProc(Order sell);
    
    public void orderClosed(Order o);
    
    
}
