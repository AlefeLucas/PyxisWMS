/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.stock.item;

import br.com.senaimg.wms.dao.OperationTypeDAO;
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
@Table(name = "operation_type")
public class OperationType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public OperationType() {
    }

    @Column (nullable = false)
    private String name;
    private boolean turnover;
    @OneToMany(mappedBy = "operation")
    private List<MetaItem> metaItems;

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

    public boolean isTurnover() {
        return turnover;
    }

    public void setTurnover(boolean turnover) {
        this.turnover = turnover;
    }

    public OperationType(String name, boolean turnover) {
        this.name = name;
        this.turnover = turnover;
    }
    
    
    /**
     * Inserts this object into the database
     */
    public void insert() {
        OperationTypeDAO.insertOperationType(this);
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        OperationTypeDAO.updateOperationType(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        OperationTypeDAO.deleteOperationType(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        OperationTypeDAO.mergeOperationType(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt OperationType>
     */
    public static List<OperationType> list() {
        return OperationTypeDAO.selectOperationTypes();
    }
}
