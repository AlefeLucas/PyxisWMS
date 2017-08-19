/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.agent;

import br.com.senaimg.wms.dao.SupplierDAO;
import br.com.senaimg.wms.model.contact.Contact;
import br.com.senaimg.wms.model.location.Address;
import br.com.senaimg.wms.model.sistema.ImageFile;
import br.com.senaimg.wms.model.warehouse.stock.item.Item;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "supplier")
public class Supplier implements Serializable {

    private static final long serialVersionUID = 201606160004L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private boolean disabled;
    @Column(length = 10)
    private String companyCode; // Ltd.  Inc.  Co.
    @Column(length = 20)
    private String mnemonic;
    private String name;
    @Column(length = 40, unique = true, nullable = false)
    private String taxNumber;
    @Column(length = 20, nullable = false)
    private String taxNumberType;
    @Column(length = 1024)
    private String annotation;
    @OneToOne(cascade = CascadeType.ALL)
    private ImageFile image;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Address address;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Contact contact;
    @OneToMany(mappedBy = "supplier")
    private List<Item> items;
    @OneToMany(mappedBy = "supplier")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Catalogue> catalogues;

    /**
     *
     */
    public Supplier() {
    }

    public Supplier(boolean disabled, String companyCode, String mnemonic, String name, String taxNumber, String taxNumberType, String annotation, ImageFile image, Address address, Contact contact) {
        this.disabled = disabled;
        this.companyCode = companyCode;
        this.mnemonic = mnemonic;
        this.name = name;
        this.taxNumber = taxNumber;
        this.taxNumberType = taxNumberType;
        this.annotation = annotation;
        this.image = image;
        this.address = address;
        this.contact = contact;
    }

    public void setFields(boolean disabled, String companyCode, String mnemonic, String name, String taxNumber, String taxNumberType, String annotation, ImageFile image, Address address, Contact contact) {
        this.disabled = disabled;
        this.companyCode = companyCode;
        this.mnemonic = mnemonic;
        this.name = name;
        this.taxNumber = taxNumber;
        this.taxNumberType = taxNumberType;
        this.annotation = annotation;
        this.image = image;
        this.address = address;
        this.contact = contact;
    }

    /**
     *
     * @param id
     */
    public Supplier(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Contact getContact() {
        return contact;
    }

    /**
     *
     * @param contact
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

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
    public boolean isDisabled() {
        return disabled;
    }

    /**
     *
     * @param disabled
     */
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    /**
     *
     * @return
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     *
     * @param companyCode
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    /**
     *
     * @return
     */
    public String getMnemonic() {
        return mnemonic;
    }

    /**
     *
     * @param mnemonic
     */
    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public Address getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     *
     * @return
     */
    public ImageFile getImage() {
        return image;
    }

    /**
     *
     * @param image
     */
    public void setImage(ImageFile image) {
        this.image = image;
    }

    /**
     *
     * @return
     */
    public String getTaxNumber() {
        return taxNumber;
    }

    /**
     *
     * @param taxeNumber
     */
    public void setTaxNumber(String taxeNumber) {
        this.taxNumber = taxeNumber;
    }

    /**
     *
     * @return
     */
    public String getTaxNumberType() {
        return taxNumberType;
    }

    /**
     *
     * @param taxeNumberType
     */
    public void setTaxNumberType(String taxeNumberType) {
        this.taxNumberType = taxeNumberType;
    }

    /**
     *
     * @return
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     *
     * @param items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * Inserts this object into the database
     */
    public void insert() {
        SupplierDAO.insertSupplier(this);
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        SupplierDAO.updateSupplier(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        SupplierDAO.deleteSupplier(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        SupplierDAO.mergeSupplier(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt Supplier>
     */
    public static List<Supplier> list() {
        return SupplierDAO.selectSuppliers();
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    @Override
    public String toString() {
        return mnemonic;
    }

    public List<Catalogue> getCatalogues() {
        return catalogues;
    }

    public void setCatalogues(List<Catalogue> catalogues) {
        this.catalogues = catalogues;
    }

    public void refresh() {
        SupplierDAO.refresh(this);
    }

}
