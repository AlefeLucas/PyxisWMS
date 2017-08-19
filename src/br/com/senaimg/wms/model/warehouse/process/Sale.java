/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.process;

import br.com.senaimg.wms.dao.SaleDAO;
import br.com.senaimg.wms.model.location.Address;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author ÁlefeLucas
 */
@Entity
@Table(name = "sale")
public class Sale implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private ProcessStatus processStatus;
    private OutProcess outProcess;
    @ManyToOne
    private Customer customer;
    private Date issueDate;
    private Date shippingDate;
    private Date deliveryDate;
    @OneToOne
    private Address deliver;
    private String payment;
    private String annotations;
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<SaleHasMetaItem> saleHasMetaItems;
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<SaleHasItem> saleHasItems;

    public Sale() {
    }

    public Sale(int id, ProcessStatus processStatus, OutProcess outProcess, Customer customer, Date issueDate, Date shippingDate, Date deliveryDate, Address deliver, String payment, String annotations) {
        this.id = id;
        this.processStatus = processStatus;
        this.outProcess = outProcess;
        this.customer = customer;
        this.issueDate = issueDate;
        this.shippingDate = shippingDate;
        this.deliveryDate = deliveryDate;
        this.deliver = deliver;
        this.payment = payment;
        this.annotations = annotations;
    }

    public Sale(ProcessStatus processStatus, OutProcess outProcess, Customer customer, Date issueDate, Date shippingDate, Date deliveryDate, Address deliver, String payment, String annotations) {
        this.processStatus = processStatus;
        this.outProcess = outProcess;
        this.customer = customer;
        this.issueDate = issueDate;
        this.shippingDate = shippingDate;
        this.deliveryDate = deliveryDate;
        this.deliver = deliver;
        this.payment = payment;
        this.annotations = annotations;
    }

    public void setFields(ProcessStatus processStatus, OutProcess outProcess, Customer customer, Date issueDate, Date shippingDate, Date deliveryDate, Address deliver, String payment, String annotations) {
        this.processStatus = processStatus;
        this.outProcess = outProcess;
        this.customer = customer;
        this.issueDate = issueDate;
        this.shippingDate = shippingDate;
        this.deliveryDate = deliveryDate;
        this.deliver = deliver;
        this.payment = payment;
        this.annotations = annotations;
    }

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

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Address getDeliver() {
        return deliver;
    }

    public void setDeliver(Address deliver) {
        this.deliver = deliver;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String annotations) {
        this.annotations = annotations;
    }

    public ProcessStatus getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(ProcessStatus processStatus) {
        this.processStatus = processStatus;
    }

    public OutProcess getOutProcess() {
        return outProcess;
    }

    public void setOutProcess(OutProcess outProcess) {
        this.outProcess = outProcess;
    }

    public List<SaleHasMetaItem> getSaleHasMetaItems() {
        return saleHasMetaItems;
    }

    public void setSaleHasMetaItems(List<SaleHasMetaItem> saleHasMetaItems) {
        this.saleHasMetaItems = saleHasMetaItems;
    }

    public List<SaleHasItem> getSaleHasItems() {
        return saleHasItems;
    }

    public void setSaleHasItems(List<SaleHasItem> saleHasItems) {
        this.saleHasItems = saleHasItems;
    }

    /**
     * Inserts this object into the database
     */
    public void insert() {
        SaleDAO.insertSale(this);
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        SaleDAO.updateSale(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        SaleDAO.deleteSale(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        SaleDAO.mergeSale(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt Sale>
     */
    public static List<Sale> list() {
        return SaleDAO.selectSales();
    }

}
