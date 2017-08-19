/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.stock.item;

import br.com.senaimg.wms.dao.ItemDimensionDAO;
import java.io.Serializable;
import java.util.List;
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
@Table(name = "item_dimension")
public class ItemDimension implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double weight; //kg
    private double height; //mm
    private double width; //mm
    private double length; //mm

    /**
     *
     * @param weight
     * @param height
     * @param width
     * @param length
     */
    public ItemDimension(double weight, double height, double width, double length) {
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.length = length;
    }

    public void setFields(double weight, double height, double width, double length) {
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.length = length;
    }

    /**
     *
     */
    public ItemDimension() {
    }

    /**
     *
     * @param id
     */
    public ItemDimension(int id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param weight
     * @param height
     * @param width
     * @param length
     */
    public ItemDimension(int id, double weight, double height, double width, double length) {
        this.id = id;
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.length = length;
    }

    /**
     *
     * @return
     */
    public double getWeight() {
        return weight;
    }

    /**
     *
     * @param weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     *
     * @return
     */
    public double getHeight() {
        return height;
    }

    /**
     *
     * @param height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     *
     * @return
     */
    public double getWidth() {
        return width;
    }

    /**
     *
     * @param width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     *
     * @return
     */
    public double getLength() {
        return length;
    }

    /**
     *
     * @param length
     */
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * Inserts this object into the database
     */
    public void insert() {
        ItemDimensionDAO.insertItemDimension(this);
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        ItemDimensionDAO.updateItemDimension(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        ItemDimensionDAO.deleteItemDimension(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        ItemDimensionDAO.mergeItemDimension(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt ItemDimension>
     */
    public static List<ItemDimension> list() {
        return ItemDimensionDAO.selectItemDimensions();
    }

    public ItemDimension rotateXAxis90() {
        return new ItemDimension(weight, length, width, weight);
    }

    public ItemDimension rotateYAxis90() {
        return new ItemDimension(weight, height, length, width);
    }

    public ItemDimension rotateZAxis90() {
        return new ItemDimension(weight, width, height, length);
    }
}
