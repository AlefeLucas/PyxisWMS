/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.stock.item;

import br.com.senaimg.wms.dao.ItemDAO;
import br.com.senaimg.wms.model.warehouse.agent.Supplier;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ÁlefeLucas
 */
@Entity
@Table(name = "item")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Batch batch;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Supplier supplier;
    private double buyingPrice;
    @Column (length = 511)
    private String description;

    /**
     *
     */
    public Item() {
    }

    public Item(Batch batch, Supplier supplier, double buyingPrice, String description) {
        this.batch = batch;
        this.supplier = supplier;
        this.buyingPrice = buyingPrice;
        this.description = description;
    }

    /**
     *
     * @param id
     */
    public Item(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Batch getBatch() {
        return batch;
    }

    /**
     *
     * @param batch
     */
    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    /**
     *
     * @return
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     *
     * @param supplier
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Inserts this object into the database
     */
    public void insert() {
        ItemDAO.insertItem(this);
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        ItemDAO.updateItem(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        ItemDAO.deleteItem(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        ItemDAO.mergeItem(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt Supplier>
     */
    public static List<Item> list() {
        return ItemDAO.selectItems();
    }
    

}
