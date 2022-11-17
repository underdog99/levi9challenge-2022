/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.levi9Challenge2022.service;

import com.example.levi9Challenge2022.dao.TradeDao;
import com.example.levi9Challenge2022.model.Trade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mladen
 */


@Service
public class TradeServiceImpl implements TradeService {
    
    @Autowired
    private TradeDao tradeDao;
    

    @Override
    @Transactional
    public List<Trade> getTrades() {
        return tradeDao.getTrades();
    }

    @Override
    @Transactional
    public void saveTrade(Trade t) {
        tradeDao.saveTrade(t);
    }

    @Override
    @Transactional
    public Trade getTrade(int id) {
        return tradeDao.getTrade(id);
    }

    @Override
    @Transactional
    public void deleteTrade() {
        tradeDao.deleteTrade();
    }
    
}
