/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.process;

import br.com.senaimg.wms.dao.SaleHasItemDAO;
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
@Table(name = "sale_has_item")
public class SaleHasItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Sale sale;
    @OneToOne
    private Item item;

    public SaleHasItem(Sale sale, Item item) {
        this.sale = sale;
        this.item = item;
    }

    public SaleHasItem() {
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
    
    /**
     * Inserts this object into the database
     */
    public void insert() {
        SaleHasItemDAO.insertSaleHasItem(this);
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        SaleHasItemDAO.updateSaleHasItem(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        SaleHasItemDAO.deleteSaleHasItem(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        SaleHasItemDAO.mergeSaleHasItem(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt SaleHasItem>
     */
    public static List<SaleHasItem> list() {
        return SaleHasItemDAO.selectSaleHasItems();
    }
}
