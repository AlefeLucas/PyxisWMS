/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.agent;

import br.com.senaimg.wms.dao.CustomerDAO;
import br.com.senaimg.wms.model.contact.Contact;
import br.com.senaimg.wms.model.location.Address;
import br.com.senaimg.wms.model.sistema.ImageFile;
import br.com.senaimg.wms.model.warehouse.agent.enums.CustomerType;
import br.com.senaimg.wms.model.warehouse.process.Sale;
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

/**
 *
 * @author ÁlefeLucas
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 201606160001L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private boolean disabled;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private CustomerType type;
    @Column(nullable = false)
    private boolean employee;
    @Column(length = 40, unique = true, nullable = false)
    private String taxNumber;
    @Column(length = 20, nullable = false)
    private String taxNumberType;
    @Column(length = 1023, nullable = false)
    private String annotation;
    @OneToOne(cascade = CascadeType.ALL)
    private ImageFile image;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Address address;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Contact contact;
    @OneToMany(mappedBy = "customer")
    private List<Sale> sales;

    /**
     *
     */
    public Customer() {
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     *
     * @param id
     */
    public Customer(int id) {
        this.id = id;
    }

    public Customer(boolean disabled, String name, CustomerType type, boolean employee, String taxNumber, String taxNumberType, String annotation, ImageFile image, Address address, Contact contact) {
        this.disabled = disabled;
        this.name = name;
        this.type = type;
        this.employee = employee;
        this.taxNumber = taxNumber;
        this.taxNumberType = taxNumberType;
        this.annotation = annotation;
        this.image = image;
        this.address = address;
        this.contact = contact;
    }

    
       public void setFields(boolean disabled, String name, CustomerType type, boolean employee, String taxNumber, String taxNumberType, String annotation, ImageFile image, Address address, Contact contact) {
        this.disabled = disabled;
        this.name = name;
        this.type = type;
        this.employee = employee;
        this.taxNumber = taxNumber;
        this.taxNumberType = taxNumberType;
        this.annotation = annotation;
        this.image = image;
        this.address = address;
        this.contact = contact;
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
    public String getTaxNumberType() {
        return taxNumberType;
    }

    /**
     *
     * @param taxNumberType
     */
    public void setTaxNumberType(String taxNumberType) {
        this.taxNumberType = taxNumberType;
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
     * @param taxNumber
     */
    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        if (type == CustomerType.COMPANY) {
            employee = false;
        }
        this.type = type;
    }

    public boolean isEmployee() {
        return employee;
    }

    public void setEmployee(boolean isEmployee) {
        if (isEmployee) {
            this.type = CustomerType.INDIVIDUAL;
        }
        this.employee = isEmployee;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    /**
     * Inserts this object into the database
     */
    public void insert() {
        CustomerDAO.insertCustomer(this);
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        CustomerDAO.updateCustomer(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        CustomerDAO.deleteCustomer(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        CustomerDAO.mergeCustomer(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt Customer>
     */
    public static List<Customer> list() {
        return CustomerDAO.selectCustomers();
    }
    
     
    public static List<Customer> listEmployees() {
        return CustomerDAO.selectEmployees();
    }
}
