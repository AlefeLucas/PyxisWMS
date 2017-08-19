/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.process;

import br.com.senaimg.wms.dao.LoanHasItemDAO;
import br.com.senaimg.wms.model.warehouse.stock.item.Item;
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
 * @author Aluno
 */
@Entity
@Table(name = "loan_has_item")
public class LoanHasItem implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Loan loan;
    @ManyToOne
    private Item item;

    public int getId() {
        return id;
    }

    public LoanHasItem(Loan loan, Item item) {
        this.loan = loan;
        this.item = item;
    }

    public LoanHasItem() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
    /**
     * Inserts this object into the database
     */
    public void insert() {
        LoanHasItemDAO.insertLoanHasItem(this);
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        LoanHasItemDAO.updateLoanHasItem(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        LoanHasItemDAO.deleteLoanHasItem(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        LoanHasItemDAO.mergeLoanHasItem(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt LoanHasItem>
     */
    public static List<LoanHasItem> list() {
        return LoanHasItemDAO.selectLoanHasItems();
    }

    
}
