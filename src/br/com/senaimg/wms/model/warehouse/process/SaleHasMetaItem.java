/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.process;

import br.com.senaimg.wms.dao.SaleHasMetaItemDAO;
import br.com.senaimg.wms.model.warehouse.stock.item.MetaItem;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ÁlefeLucas
 */
@Entity
@Table(name = "sale_has_meta_item")
public class SaleHasMetaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;    
    @ManyToOne
    private Sale sale;
    @ManyToOne
    private MetaItem metaItem;
    private int quantity;
    private double soldPrice;

    public SaleHasMetaItem() {
    }

    public SaleHasMetaItem(Sale sale, MetaItem metaItem, int quantity, double soldPrice) {
        this.sale = sale;
        this.metaItem = metaItem;
        this.quantity = quantity;
        this.soldPrice = soldPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
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
        
        this.soldPrice = this.metaItem.getSellingPrice() * this.quantity;
    }

    public double getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(double soldPrice) {
        this.soldPrice = soldPrice;
    }

    
    /**
     * Inserts this object into the database
     */
    public void insert() {
        SaleHasMetaItemDAO.insertSaleHasMetaItem(this);
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        SaleHasMetaItemDAO.updateSaleHasMetaItem(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        SaleHasMetaItemDAO.deleteSaleHasMetaItem(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        SaleHasMetaItemDAO.mergeSaleHasMetaItem(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt SaleHasMetaItem>
     */
    public static List<SaleHasMetaItem> list() {
        return SaleHasMetaItemDAO.selectSaleHasMetaItems();
    }
}
