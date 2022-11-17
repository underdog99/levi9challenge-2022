/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.levi9Challenge2022.dao;

import com.example.levi9Challenge2022.model.Trade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mladen
 */

@Repository
public class TradeDaoImpl implements TradeDao {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<Trade> getTrades() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery <Trade> cq = cb.createQuery(Trade.class);
        Root <Trade> root = cq.from(Trade.class);
        cq.select(root);
        Query query = entityManager.createQuery(cq);
        return query.getResultList(); 
    }

    @Override
    public void saveTrade(Trade t) {
        entityManager.persist(t);
        System.out.print("Gotovo cuvanje TRADE-a!");
    }

    @Override
    public Trade getTrade(int id) {
        Trade t = entityManager.find(Trade.class, id);
        return t;
    }

    @Override
    public void deleteTrade() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete <Trade> cq = cb.createCriteriaDelete(Trade.class);
        Root <Trade> root = cq.from(Trade.class);
        int res = entityManager.createQuery(cq).executeUpdate();
    }
    
}
