/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.stock.place;

import br.com.senaimg.wms.dao.PalletDAO;
import br.com.senaimg.wms.model.warehouse.stock.Stock;
import br.com.senaimg.wms.model.warehouse.stock.item.ItemDimension;
import java.io.Serializable;
import java.util.List;
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
@Table(name = "pallet")
public class Pallet implements Serializable {

    @OneToMany(mappedBy = "pallet")
     @LazyCollection(LazyCollectionOption.FALSE)
    private List<Stock> stocks;

    public Pallet(ItemDimension capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Pallet " + id;
     }

    

    public Pallet() {
    }

    public Pallet(ItemDimension capacity, RackFloor floor) {
        this.capacity = capacity;
        this.floor = floor;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne
    private ItemDimension capacity;
    @ManyToOne
    @JoinColumn(nullable = false)
    private RackFloor floor;

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
    public ItemDimension getCapacity() {
        return capacity;
    }

    /**
     *
     * @param capacity
     */
    public void setCapacity(ItemDimension capacity) {
        this.capacity = capacity;
    }

    /**
     *
     * @return
     */
    public RackFloor getFloor() {
        return floor;
    }

    /**
     *
     * @param floor
     */
    public void setFloor(RackFloor floor) {
        this.floor = floor;
    }

    
       /**
     * Inserts this object into the database
     */
    public void insert() {
        PalletDAO.insertPallet(this);
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        PalletDAO.updatePallet(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        PalletDAO.deletePallet(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        PalletDAO.mergePallet(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt Pallet>
     */
    public static List<Pallet> list() {
        return PalletDAO.selectPallets();
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

 
}
