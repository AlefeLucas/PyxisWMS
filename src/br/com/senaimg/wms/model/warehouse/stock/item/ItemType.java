/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.stock.item;

import br.com.senaimg.wms.dao.ItemTypeDAO;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ÁlefeLucas
 */
@Entity
@Table(name = "item_type")
public class ItemType {
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column (unique = true, nullable = false)
    private String name;

    public ItemType(String name) {
        this.name = name;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemType() {
    }
    
    
     /**
     * Inserts this object into the database
     */
    public void insert() {
        ItemTypeDAO.insertItemType(this);
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        ItemTypeDAO.updateItemType(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        ItemTypeDAO.deleteItemType(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        ItemTypeDAO.mergeItemType(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt ItemType>
     */
    public static List<ItemType> list() {
        return ItemTypeDAO.selectItemTypes();
    }

    @Override
    public String toString() {
        return name;
    }

    
}
