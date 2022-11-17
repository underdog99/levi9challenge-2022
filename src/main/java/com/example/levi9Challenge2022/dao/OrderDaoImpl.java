/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.levi9Challenge2022.dao;

import com.example.levi9Challenge2022.model.Order;
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
public class OrderDaoImpl implements OrderDao {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Order> getOrders() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery <Order> cq = cb.createQuery(Order.class);
        Root <Order> root = cq.from(Order.class);
        cq.select(root);
        Query query = entityManager.createQuery(cq);
        return query.getResultList(); 
    }

    @Override
    public void saveOrder(Order o) {
        entityManager.persist(o);
        System.out.print("Gotovo cuvanje ORDERA!");
    }

    @Override
    public Order getOrder(int id) {
        Order o = entityManager.find(Order.class, id);
        return o;
    }

    @Override
    public void deleteOrder() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete <Order> cq = cb.createCriteriaDelete(Order.class);
        Root <Order> root = cq.from(Order.class);
        int res = entityManager.createQuery(cq).executeUpdate();
    }

    @Override
    public void updateOrder(Order o) {
        entityManager.merge(o);
    }
    
    
}
