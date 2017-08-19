/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.process;

import br.com.senaimg.wms.dao.LoanDAO;
import br.com.senaimg.wms.model.warehouse.agent.Customer;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
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
 * @author Aluno
 */
@Entity
@Table(name = "loan")
public class Loan implements Serializable {

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<LoanHasMetaItem> loanHasMetaItems;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Customer customer;
    private String annotations;
    private Date loanDate;
    private Date expReturnDate;
    private Date returnDate;
    private LoanStatus status;
    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<LoanHasItem> loanHasItems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String annotations) {
        this.annotations = annotations;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getExpReturnDate() {
        return expReturnDate;
    }

    public void setExpReturnDate(Date expReturnDate) {
        this.expReturnDate = expReturnDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public List<LoanHasItem> getLoanHasItems() {
        return loanHasItems;
    }

    public void setLoanHasItems(List<LoanHasItem> loanHasItems) {
        this.loanHasItems = loanHasItems;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    /**
     * Inserts this object into the database
     */
    public void insert() {
        LoanDAO.insertLoan(this);
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        LoanDAO.updateLoan(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        LoanDAO.deleteLoan(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        LoanDAO.mergeLoan(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt Loan>
     */
    public static List<Loan> list() {
        return LoanDAO.selectLoans();
    }

    public List<LoanHasMetaItem> getLoanHasMetaItems() {
        return loanHasMetaItems;
    }

    public void setLoanHasMetaItems(List<LoanHasMetaItem> loanHasMetaItems) {
        this.loanHasMetaItems = loanHasMetaItems;
    }

}
