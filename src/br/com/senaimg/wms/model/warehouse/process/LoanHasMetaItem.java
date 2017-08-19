/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.process;

import br.com.senaimg.wms.dao.LoanHasMetaItemDAO;
import br.com.senaimg.wms.model.warehouse.stock.item.MetaItem;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ÁlefeLucas
 */
@Entity
@Table(name = "loan_has_meta_item")
public class LoanHasMetaItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Loan loan;
    @ManyToOne
    private MetaItem metaItem;
    private int quantity;

    public LoanHasMetaItem(Loan loan, MetaItem metaItem, int quantity) {
        this.loan = loan;
        this.metaItem = metaItem;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return metaItem.getMnemonic();
    }
    
    

    public LoanHasMetaItem() {
    }

    public int getId() {
        return id;
    }

    public Loan getLoan() {
        return loan;
    }

    public MetaItem getMetaItem() {
        return metaItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
    
    /**
     * Inserts this object into the database
     */
    public void insert() {
        LoanHasMetaItemDAO.insertLoanHasMetaItem(this);
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        LoanHasMetaItemDAO.updateLoanHasMetaItem(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        LoanHasMetaItemDAO.deleteLoanHasMetaItem(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        LoanHasMetaItemDAO.mergeLoanHasMetaItem(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt LoanHasMetaItem>
     */
    public static List<LoanHasMetaItem> list() {
        return LoanHasMetaItemDAO.selectLoanHasMetaItems();
    }
}
