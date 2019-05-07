/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpa_shoppingcart;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name = "shopping_cart")
@NamedQueries({
    @NamedQuery(name = "ShoppingCart.findAll", query = "SELECT s FROM ShoppingCart s")})
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "product")
    private String product;
    @Basic(optional = false)
    @Column(name = "unitPrice")
    private double unitPrice;
    @Basic(optional = false)
    @Column(name = "amount")
    private int amount;

    public ShoppingCart() {
    }

    public ShoppingCart(String product) {
        this.product = product;
    }

    public ShoppingCart(String product, double unitPrice, int amount) {
        this.product = product;
        this.unitPrice = unitPrice;
        this.amount = amount;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (product != null ? product.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShoppingCart)) {
            return false;
        }
        ShoppingCart other = (ShoppingCart) object;
        if ((this.product == null && other.product != null) || (this.product != null && !this.product.equals(other.product))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.jpa_shoppingcart.ShoppingCart[ product=" + product + " ]";
    }
    
}
