/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.process;

import br.com.senaimg.wms.dao.PurchaseDAO;
import br.com.senaimg.wms.model.warehouse.agent.Supplier;
import java.io.Serializable;
import java.util.Date;
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

/**
 *
 * @author ÁlefeLucas
 */
@Entity
@Table(name = "purchase")
public class Purchase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Supplier supplier;
    private Date orderDate;
    private Date expDelivery;
    private String paymentConditions;
    @Column(length = 511)
    private String annotations;
    private Date delivery;
    private ProcessStatus processStatus;
    private InProcess inProcess;
    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PurchaseHasMetaItem> purchaseHasMetaItems;
    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PurchaseHasItem> purchaseHasItems;

    public Purchase() {
    }

    public void setFields(Date orderDate, Date expDelivery, String paymentConditions, String annotations, Date delivery, ProcessStatus processStatus, InProcess inProcess) {
        this.orderDate = orderDate;
        this.expDelivery = expDelivery;
        this.paymentConditions = paymentConditions;
        this.annotations = annotations;
        this.delivery = delivery;
        this.processStatus = processStatus;
        this.inProcess = inProcess;
    }

    public Purchase(Supplier supplier, Date orderDate, Date expDelivery, String paymentConditions, String annotations, Date delivery, ProcessStatus processStatus, InProcess inProcess) {
        this.supplier = supplier;
        this.orderDate = orderDate;
        this.expDelivery = expDelivery;
        this.paymentConditions = paymentConditions;
        this.annotations = annotations;
        this.delivery = delivery;
        this.processStatus = processStatus;
        this.inProcess = inProcess;
    }

    public Purchase(Supplier supplier, Date orderDate, Date expDelivery, String paymentConditions, String annotations, Date delivery) {
        this.supplier = supplier;
        this.orderDate = orderDate;
        this.expDelivery = expDelivery;
        this.paymentConditions = paymentConditions;
        this.annotations = annotations;
        this.delivery = delivery;
    }

    public void setFields(Supplier supplier, Date orderDate, Date expDelivery, String paymentConditions, String annotations, Date delivery, ProcessStatus processStatus, InProcess inProcess) {
        this.supplier = supplier;
        this.orderDate = orderDate;
        this.expDelivery = expDelivery;
        this.paymentConditions = paymentConditions;
        this.annotations = annotations;
        this.delivery = delivery;
        this.processStatus = processStatus;
        this.inProcess = inProcess;
    }

    public void setFields(Supplier supplier, Date orderDate, Date expDelivery, String paymentConditions, String annotations, Date delivery) {
        this.supplier = supplier;
        this.orderDate = orderDate;
        this.expDelivery = expDelivery;
        this.paymentConditions = paymentConditions;
        this.annotations = annotations;
        this.delivery = delivery;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getExpDelivery() {
        return expDelivery;
    }

    public void setExpDelivery(Date expDelivery) {
        this.expDelivery = expDelivery;
    }

    public String getPaymentConditions() {
        return paymentConditions;
    }

    public void setPaymentConditions(String paymentConditions) {
        this.paymentConditions = paymentConditions;
    }

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String annotations) {
        this.annotations = annotations;
    }

    public Date getDelivery() {
        return delivery;
    }

    public void setDelivery(Date delivery) {
        this.delivery = delivery;
    }

    public ProcessStatus getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(ProcessStatus processStatus) {
        this.processStatus = processStatus;
    }

    public InProcess getInProcess() {
        return inProcess;
    }

    public void setInProcess(InProcess inProcess) {
        this.inProcess = inProcess;
    }

    /**
     * Inserts this object into the database
     */
    public void insert() {
        PurchaseDAO.insertPurchase(this);
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        PurchaseDAO.updatePurchase(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        PurchaseDAO.deletePurchase(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        PurchaseDAO.mergePurchase(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt Purchase>
     */
    public static List<Purchase> list() {
        return PurchaseDAO.selectPurchases();
    }

    public List<PurchaseHasMetaItem> getPurchaseHasMetaItems() {
        return purchaseHasMetaItems;
    }

    public void setPurchaseHasMetaItems(List<PurchaseHasMetaItem> purchaseHasMetaItems) {
        this.purchaseHasMetaItems = purchaseHasMetaItems;
    }

    public List<PurchaseHasItem> getPurchaseHasItems() {
        return purchaseHasItems;
    }

    public void setPurchaseHasItems(List<PurchaseHasItem> purchaseHasItems) {
        this.purchaseHasItems = purchaseHasItems;
    }

}
