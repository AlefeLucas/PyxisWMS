/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.agent;

import br.com.senaimg.wms.dao.CatalogueDAO;
import br.com.senaimg.wms.model.warehouse.stock.item.MetaItem;
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
 * @author Aluno
 */
@Entity
@Table(name = "catalogue_supplier")
public class Catalogue {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn (nullable = false)    
    private MetaItem metaItem;
    
    @ManyToOne    
    @JoinColumn (nullable = false)
    private Supplier supplier;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

 

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Catalogue() {
    }

    public Catalogue(MetaItem metaItem, Supplier supplier, double price) {
        this.metaItem = metaItem;
        this.supplier = supplier;
        this.price = price;
    }
    
    
    /**
     * Inserts this object into the database
     */
    public void insert() {
        CatalogueDAO.insertCatalogue(this);
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        CatalogueDAO.updateCatalogue(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        CatalogueDAO.deleteCatalogue(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        CatalogueDAO.mergeCatalogue(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt Supplier>
     */
    public static List<Catalogue> list() {
        return CatalogueDAO.selectCatalogues();
    }
    
    public static List<Catalogue> list(Supplier supplier) {
        return CatalogueDAO.selectCatalogues(supplier);
    }

    @Override
    public String toString() {
        return this.getMetaItem().getMnemonic();
    }

    public MetaItem getMetaItem() {
        return metaItem;
    }

    public void setMetaItem(MetaItem metaItem) {
        this.metaItem = metaItem;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    
}
