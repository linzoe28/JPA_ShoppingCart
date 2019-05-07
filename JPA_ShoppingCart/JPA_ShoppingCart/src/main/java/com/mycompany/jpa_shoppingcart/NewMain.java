/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpa_shoppingcart;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author User
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("com.mycompany_JPA_ShoppingCart_jar_1.0-SNAPSHOTPU");
        EntityManager em=emf.createEntityManager();
        Query query=em.createQuery("SELECT o FROM ShoppingCart o WHERE o.product='note'"); //From接的為 Entity name
        List<ShoppingCart> list=query.getResultList();
        for(ShoppingCart item:list){
            System.out.println(item.getProduct()+":"+item.getAmount());
        }
        em.close();
        System.exit(0);
        
    }
    
}
