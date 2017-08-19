/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.stock.item;

import br.com.senaimg.wms.dao.BrandDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ÁlefeLucas
 */
@Entity
@Table(name = "brand")
public class Brand implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column (unique = true, nullable = false)
    private String name;
    @OneToMany(mappedBy = "brand")
    private List<MetaItem> metaItems;

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
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     */
    public Brand() {
    }

    /**
     *
     * @param id
     */
    public Brand(int id) {
        this.id = id;
    }

    /**
     *
     * @param name
     */
    public Brand(String name) {
        this.name = name;
    }

    /**
     *
     * @param id
     * @param name
     */
    public Brand(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     *
     * @return
     */
    public List<MetaItem> getMetaItems() {
        return metaItems;
    }

    /**
     *
     * @param metaItems
     */
    public void setMetaItems(List<MetaItem> metaItems) {
        this.metaItems = metaItems;
    }

    @Override
    public String toString() {
        return name;
    }

    
      /**
     * Inserts this object into the database
     */
    public void insert() {
        BrandDAO.insertBrand(this);
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        BrandDAO.updateBrand(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        BrandDAO.deleteBrand(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        BrandDAO.mergeBrand(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt Brand>
     */
    public static List<Brand> list() {
        return BrandDAO.selectBrands();
    }
    
    
}
