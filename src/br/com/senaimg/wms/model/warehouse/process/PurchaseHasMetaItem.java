/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.process;

import br.com.senaimg.wms.dao.PurchaseHasMetaItemDAO;
import br.com.senaimg.wms.model.warehouse.stock.item.MetaItem;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Aluno
 */
@Entity
@Table(name = "purchase_has_meta_item")
public class PurchaseHasMetaItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;      
    @ManyToOne
    private Purchase purchase;    
    @ManyToOne
    private MetaItem metaItem;
    private int quantity;
    private double inPrice;

    public PurchaseHasMetaItem(Purchase purchase, MetaItem metaItem, int quantity, double inPrice) {
        this.purchase = purchase;
        this.metaItem = metaItem;
        this.quantity = quantity;
        this.inPrice = inPrice;
    }

    @Override
    public String toString() {
        return metaItem.getMnemonic();
    }

    
   

    public PurchaseHasMetaItem() {
    }

   

    public MetaItem getMetaItem() {
        return metaItem;
    }

    public void setMetaItem(MetaItem metaItem) {
        this.metaItem = metaItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getInPrice() {
        return inPrice;
    }

    public void setInPrice(double inPrice) {
        this.inPrice = inPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
     /**
     * Inserts this object into the database
     */
    public void insert() {
        PurchaseHasMetaItemDAO.insertPurchaseHasItem(this);
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        PurchaseHasMetaItemDAO.updatePurchaseHasItem(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        PurchaseHasMetaItemDAO.deletePurchaseHasItem(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        PurchaseHasMetaItemDAO.mergePurchaseHasItem(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt PurchaseHasItem>
     */
    public static List<PurchaseHasMetaItem> list() {
        return PurchaseHasMetaItemDAO.selectPurchaseHasItems();
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

}
