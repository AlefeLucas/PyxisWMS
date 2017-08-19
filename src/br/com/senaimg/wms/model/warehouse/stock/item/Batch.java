/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.stock.item;

import br.com.senaimg.wms.dao.BatchDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ÁlefeLucas
 */
@Entity
@Table(name = "batch")
public class Batch implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;   
    long number;
    Date expiration;
    Date manufacturing;
    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
    private List<Item> items;

    @ManyToOne
    @JoinColumn(nullable = false)
    private MetaItem metaItem;

    /**
     *
     */
    public Batch() {
    }

    public void add(Item item){
        if(items == null){
            items = new ArrayList<>();
        }
        
        items.add(item);
    }
    
    public void addAll(Item... itens) {
        for(Item item : itens){
            add(item);
        }
    }
    
    public void addAll(List<Item> itens){
        for(Item item : itens){
            add(item);
        }
    }

    
    /**
     *
     * @param id
     */
    public Batch(int id) {
        this.id = id;
    }

    /**
     *
     * @param number
     * @param expiration
     * @param manufacturing
     */
    public Batch(long number, Date expiration, Date manufacturing, MetaItem metaItem) {
        this.number = number;
        this.expiration = expiration;
        this.manufacturing = manufacturing;
        this.metaItem = metaItem;
    }

    /**
     *
     * @param id
     * @param number
     * @param expiration
     * @param manufacturing
     */
    public Batch(int id, long number, Date expiration, Date manufacturing) {
        this.id = id;
        this.number = number;
        this.expiration = expiration;
        this.manufacturing = manufacturing;
    }

    
    /**
     * Inserts this object into the database
     */
    public void insert() {
        BatchDAO.insertBatch(this);
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        BatchDAO.updateBatch(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        BatchDAO.deleteBatch(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        BatchDAO.mergeBatch(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt Batch>
     */
    public static List<Batch> list() {
        return BatchDAO.selectBatchs();
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
    public long getNumber() {
        return number;
    }

    /**
     *
     * @param number
     */
    public void setNumber(long number) {
        this.number = number;
    }

    /**
     *
     * @return
     */
    public Date getExpiration() {
        return expiration;
    }

    /**
     *
     * @param expiration
     */
    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    /**
     *
     * @return
     */
    public Date getManufacturing() {
        return manufacturing;
    }

    /**
     *
     * @param manufacturing
     */
    public void setManufacturing(Date manufacturing) {
        this.manufacturing = manufacturing;
    }

    /**
     *
     * @return
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     *
     * @param items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     *
     * @return
     */
    public MetaItem getMetaItem() {
        return metaItem;
    }

    /**
     *
     * @param metaItem
     */
    public void setMetaItem(MetaItem metaItem) {
        this.metaItem = metaItem;
    }

}
