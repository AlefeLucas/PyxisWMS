/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.stock.place;

import br.com.senaimg.wms.dao.HallDAO;
import br.com.senaimg.wms.model.warehouse.stock.item.ItemType;
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
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author ÁlefeLucas
 */
@Entity
@Table(name = "hall")
public class Hall implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 32)
    private String code;
    @ManyToOne
    private ItemType itemType;
    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Rack> racks;

    public Hall(String code, ItemType itemType) {
        this.code = code;
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        return code;
    }

    public Hall() {
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
    public List<Rack> getRacks() {
        return racks;
    }

    /**
     *
     * @param racks
     */
    public void setRacks(List<Rack> racks) {
        this.racks = racks;
    }

    /**
     * Inserts this object into the database
     */
    public void insert() {
        HallDAO.insertHall(this);
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        HallDAO.updateHall(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() throws ConstraintViolationException {
        HallDAO.deleteHall(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        HallDAO.mergeHall(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt Hall>
     */
    public static List<Hall> list() {
        return HallDAO.selectHalls();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

}
