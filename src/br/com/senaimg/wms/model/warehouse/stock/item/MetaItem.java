/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.stock.item;

import br.com.senaimg.wms.dao.MetaItemDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
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
@Table(name = "meta_item")
public class MetaItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 1023)
    private String caution;
    @Column(length = 1023)
    private String description;
    @Column(length = 14)
    private String ean13;
    private int minQuantity;
    private int maxQuantity;
    @Column(length = 20)
    private String mnemonic;
    private double sellingPrice;

    @ManyToOne
    private Brand brand;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private ItemDimension dimension;
    @ManyToOne
    private ItemGroup group;
    @ManyToOne
    private OperationType operation;
    @ManyToOne
    private ItemType itemType;

    public MetaItem(String caution, String description, String ean13, int minQuantity, int maxQuantity, String mnemonic, double sellingPrice, Brand brand, ItemDimension dimension, ItemGroup group, OperationType operation, ItemType itemType) {
        this.caution = caution;
        this.description = description;
        this.ean13 = ean13;
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
        this.mnemonic = mnemonic;
        this.sellingPrice = sellingPrice;
        this.brand = brand;
        this.dimension = dimension;
        this.group = group;
        this.operation = operation;
        this.itemType = itemType;
    }

    public void setFields(String caution, String description, String ean13, int minQuantity, int maxQuantity, String mnemonic, double sellingPrice, Brand brand, ItemDimension dimension, ItemGroup group, OperationType operation, ItemType itemType) {
        this.caution = caution;
        this.description = description;
        this.ean13 = ean13;
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
        this.mnemonic = mnemonic;
        this.sellingPrice = sellingPrice;
        this.brand = brand;
        this.dimension = dimension;
        this.group = group;
        this.operation = operation;
        this.itemType = itemType;
    }

    public MetaItem() {
    }

    public String getCaution() {
        return caution;
    }

    public void setCaution(String caution) {
        this.caution = caution;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEan13() {
        return ean13;
    }

    public void setEan13(String ean13) {
        this.ean13 = ean13;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return mnemonic;
    }

    public ItemDimension getDimension() {
        return dimension;
    }

    public void setDimension(ItemDimension dimension) {
        this.dimension = dimension;
    }

    public ItemGroup getGroup() {
        return group;
    }

    public void setGroup(ItemGroup group) {
        this.group = group;
    }

    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    /**
     * Inserts this object into the database
     */
    public void insert() {
        MetaItemDAO.insertMetaItem(this);
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        MetaItemDAO.updateMetaItem(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        MetaItemDAO.deleteMetaItem(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        MetaItemDAO.mergeMetaItem(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt MetaItem>
     */
    public static List<MetaItem> list() {
        return MetaItemDAO.selectMetaItems();
    }
    
     public static List<MetaItem> listTurnover() {
        return MetaItemDAO.selectMetaItemsTurnover();
    }

     
     
     

}
