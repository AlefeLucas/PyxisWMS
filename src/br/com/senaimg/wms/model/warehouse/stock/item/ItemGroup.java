/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.stock.item;

import br.com.senaimg.wms.dao.ItemGroupDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author ÁlefeLucas
 */
@Entity
@Table(name = "item_group")
public class ItemGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column (unique = true, nullable = false)
    private String name;
    @Column (length = 511)    
    private String description;
    @ManyToOne
    private ItemGroup parent;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
     @LazyCollection(LazyCollectionOption.FALSE)
    private List<ItemGroup> itemGroups;
    @OneToMany(mappedBy = "group")
     @LazyCollection(LazyCollectionOption.FALSE)
    private List<MetaItem> metaItems;

    public ItemGroup(String name) {
        this.name = name;
    }

    /**
     *
     */
    public ItemGroup() {
    }

    public ItemGroup(String name, String description, ItemGroup parent) {
        this.name = name;
        this.description = description;
        this.parent = parent;
    }

    /**
     *
     * @param id
     */
    public ItemGroup(int id) {
        this.id = id;
    }

    /**
     *
     * @param name
     * @param description
     * @param parent
     * @param itemGroups
     */
    public ItemGroup(String name, String description, ItemGroup parent, List<ItemGroup> itemGroups) {
        this.name = name;
        this.description = description;
        this.parent = parent;
        this.itemGroups = itemGroups;
    }

    /**
     *
     * @param id
     * @param name
     * @param description
     * @param parent
     * @param itemGroups
     */
    public ItemGroup(int id, String name, String description, ItemGroup parent, List<ItemGroup> itemGroups) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parent = parent;
        this.itemGroups = itemGroups;
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
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public ItemGroup getParent() {
        return parent;
    }

    /**
     *
     * @param parent
     */
    public void setParent(ItemGroup parent) {
        this.parent = parent;
    }

    /**
     *
     * @return
     */
    public List<ItemGroup> getItemGroups() {
        return itemGroups;
    }

    /**
     *
     * @param itemGroups
     */
    public void setItemGroups(List<ItemGroup> itemGroups) {
        this.itemGroups = itemGroups;
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
    
        /**
     * Inserts this object into the database
     */
    public void insert() {
        ItemGroupDAO.insertItemGroup(this);
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        ItemGroupDAO.updateItemGroup(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        ItemGroupDAO.deleteItemGroup(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        ItemGroupDAO.mergeItemGroup(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt ItemGroup>
     */
    public static List<ItemGroup> list() {
        return ItemGroupDAO.selectItemGroups();
    }

    @Override
    public String toString() {
        return name;
    }
    
    
}
