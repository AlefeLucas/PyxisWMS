/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.stock;

import br.com.senaimg.wms.dao.StockDAO;
import br.com.senaimg.wms.model.warehouse.stock.item.Item;
import br.com.senaimg.wms.model.warehouse.stock.place.Pallet;
import java.io.Serializable;
import java.util.List;
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
@Table(name = "stock")
public class Stock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Pallet pallet;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Item item;

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
    public Pallet getPallet() {
        return pallet;
    }

    /**
     *
     * @param pallet
     */
    public void setPallet(Pallet pallet) {
        this.pallet = pallet;
    }

    /**
     *
     * @return
     */
    public Item getItem() {
        return item;
    }

    /**
     *
     * @param item
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     *
     */
    public Stock() {
    }

    /**
     *
     * @param id
     */
    public Stock(int id) {
        this.id = id;
    }

    /**
     *
     * @param pallet
     * @param item
     */
    public Stock(Pallet pallet, Item item) {

        this.pallet = pallet;
        this.item = item;
    }

    /**
     *
     * @param id
     *
     * @param pallet
     * @param item
     */
    public Stock(int id, Pallet pallet, Item item) {
        this.id = id;

        this.pallet = pallet;
        this.item = item;
    }

    
        /**
     * Inserts this object into the database
     */
    public void insert() {
        StockDAO.insertStock(this);
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        StockDAO.updateStock(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        StockDAO.deleteStock(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        StockDAO.mergeStock(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt Stock>
     */
    public static List<Stock> list() {
        return StockDAO.selectStocks();
    }
    
        
    public static List<Stock> listTurnover() {
        System.out.println("STOCK");
        return StockDAO.selectStocksTurnover();
    }
    
    public static List<Stock> listFifo(){
        return StockDAO.selectStocksFifo();
    }
    
    public static List<Stock> listFilo(){
        return StockDAO.selectStocksFilo();
    }
    
}
