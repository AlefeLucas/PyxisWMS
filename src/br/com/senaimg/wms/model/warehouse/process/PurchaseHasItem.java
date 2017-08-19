/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.process;

import br.com.senaimg.wms.dao.PurchaseHasItemDAO;
import br.com.senaimg.wms.model.warehouse.stock.item.Item;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author ÁlefeLucas
 */
@Entity
@Table(name = "purchase_has_item")
public class PurchaseHasItem implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    @OneToOne
    private Item item;
    @ManyToOne
    private Purchase purchase;

    public PurchaseHasItem(Item item, Purchase purchase) {
        this.item = item;
        this.purchase = purchase;
    }

    public PurchaseHasItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }
    
       /**
     * Inserts this object into the database
     */
    public void insert() {
        PurchaseHasItemDAO.insertPurchaseHasItem(this);
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        PurchaseHasItemDAO.updatePurchaseHasItem(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        PurchaseHasItemDAO.deletePurchaseHasItem(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        PurchaseHasItemDAO.mergePurchaseHasItem(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt PurchaseHasItem>
     */
    public static List<PurchaseHasItem> list() {
        return PurchaseHasItemDAO.selectPurchaseHasItems();
    }
    
}
