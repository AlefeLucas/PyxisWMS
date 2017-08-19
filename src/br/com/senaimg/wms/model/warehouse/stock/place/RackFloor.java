/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.stock.place;

import br.com.senaimg.wms.dao.RackFloorDAO;
import br.com.senaimg.wms.model.warehouse.stock.item.ItemType;
import java.io.Serializable;
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
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author ÁlefeLucas
 */
@Entity
@Table(name = "rack_floor")
public class RackFloor implements Serializable {

    public RackFloor(ItemType itemType, byte floorNumber, Rack rack) {
        this.itemType = itemType;
        this.floorNumber = floorNumber;
        this.rack = rack;
    }

    public RackFloor() {
    }

    @Override
    public String toString() {
        return floorNumber + "";
    }

    public RackFloor(List<Pallet> pallets, ItemType itemType, byte floorNumber, Rack rack) {
        this.pallets = pallets;
        this.itemType = itemType;
        this.floorNumber = floorNumber;
        this.rack = rack;
    }

    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL)
     @LazyCollection(LazyCollectionOption.FALSE)
    private List<Pallet> pallets;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
     @ManyToOne
    private ItemType itemType;
    private byte floorNumber;
    @ManyToOne
@JoinColumn (nullable = false)
    private Rack rack;

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
    public byte getFloorNumber() {
        return floorNumber;
    }

    /**
     *
     * @param floorNumber
     */
    public void setFloorNumber(byte floorNumber) {
        this.floorNumber = floorNumber;
    }

    /**
     *
     * @return
     */
    public List<Pallet> getPallets() {
        return pallets;
    }

    /**
     *
     * @param pallets
     */
    public void setPallets(List<Pallet> pallets) {
        this.pallets = pallets;
    }

    /**
     *
     * @return
     */
    public Rack getRack() {
        return rack;
    }

    /**
     *
     * @param rack
     */
    public void setRack(Rack rack) {
        this.rack = rack;
    }
    
        
      /**
     * Inserts this object into the database
     */
    public void insert() {
        RackFloorDAO.insertRackFloor(this);
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        RackFloorDAO.updateRackFloor(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        RackFloorDAO.deleteRackFloor(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        RackFloorDAO.mergeRackFloor(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt RackFloor>
     */
    public static List<RackFloor> list() {
        return RackFloorDAO.selectRackFloors();
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
    
    
}
