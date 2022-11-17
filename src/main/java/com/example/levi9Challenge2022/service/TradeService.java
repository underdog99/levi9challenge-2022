/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.levi9Challenge2022.service;

import com.example.levi9Challenge2022.model.Trade;
import java.util.List;

/**
 *
 * @author Mladen
 */


public interface TradeService {
    
    public List<Trade> getTrades();
    
    public void saveTrade(Trade t);
    
    public Trade getTrade(int id);
    
    public void deleteTrade();
    
}
