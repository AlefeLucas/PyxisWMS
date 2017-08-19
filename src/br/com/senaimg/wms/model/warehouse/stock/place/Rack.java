/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.stock.place;

import br.com.senaimg.wms.dao.RackDAO;
import br.com.senaimg.wms.model.warehouse.stock.item.ItemDimension;
import br.com.senaimg.wms.model.warehouse.stock.item.ItemType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author ÁlefeLucas
 */
@Entity
@Table(name = "rack")
public class Rack implements Serializable {

    @Override
    public String toString() {
        return code;
    }

    @OneToMany(mappedBy = "rack", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<RackFloor> rackFloors;

    public Rack(String code, ItemType itemType, Hall hall, ItemDimension capacity, int palletsPerFloor) {
        this.code = code;
        this.itemType = itemType;
        this.hall = hall;
        this.capacity = capacity;
        this.palletsPerFloor = palletsPerFloor;
    }

    public Rack() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 32)
    private String code;
    @ManyToOne
    private ItemType itemType;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Hall hall;
    @OneToOne (cascade = CascadeType.ALL)
    private ItemDimension capacity;
    private int palletsPerFloor;
    

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
    public List<RackFloor> getRackFloors() {
        return rackFloors;
    }

    /**
     *
     * @param rackFloors
     */
    public void setRackFloors(List<RackFloor> rackFloors) {
        this.rackFloors = rackFloors;
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

    /**
     * Inserts this object into the database
     */
    public void insert() {
        RackDAO.insertRack(this);
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        RackDAO.updateRack(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        RackDAO.deleteRack(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        RackDAO.mergeRack(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt Rack>
     */
    public static List<Rack> list() {
        return RackDAO.selectRacks();
    }

    public ItemDimension getCapacity() {
        return capacity;
    }

    public void setCapacity(ItemDimension capacity) {
        this.capacity = capacity;
    }

 
    public int getPalletsPerFloor() {
        return palletsPerFloor;
    }

    public void setPalletsPerFloor(int palletsPerFloor) {
        this.palletsPerFloor = palletsPerFloor;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

}
