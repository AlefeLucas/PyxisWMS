/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.location;

import br.com.senaimg.wms.dao.AddressDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 *
 * @author ÁlefeLucas
 */
@Entity
@Table (name = "address")
public class Address implements Serializable {

    private static final long serialVersionUID = 201606160002L;
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 511, nullable = false)
    private String addressLine1;
    @Column(length = 511)
    private String addressLine2;
    private int postalCode;
    @ManyToOne
    @JoinColumn (nullable = true)    
    private City city;

    /**
     *
     */
    public Address() {
        
        
    }

    /**
     *
     * @param id
     */
    public Address(int id) {
        this.id = id;
    }

    /**
     *
     * @param addressLine1
     * @param addressLine2
     * @param postalCode
     * @param city
     */
    public Address(String addressLine1, String addressLine2, int postalCode, City city) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.postalCode = postalCode;
        this.city = city;
    }
    
   
    
     public void setFields(String addressLine1, String addressLine2, int postalCode, City city) {
      
         
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.postalCode = postalCode;
        this.city = city;
    }


    /**
     *
     * @param id
     * @param addressLine1
     * @param addressLine2
     * @param postalCode
     * @param city
     */
    public Address(int id, String addressLine1, String addressLine2, int postalCode, City city) {
        this.id = id;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.postalCode = postalCode;
        this.city = city;
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
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     *
     * @param addressLine1
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     *
     * @return
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     *
     * @param addressLine2
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     *
     * @return
     */
    public int getPostalCode() {
        return postalCode;
    }

    /**
     *
     * @param postalCode
     */
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    /**
     *
     * @return
     */
    public City getCity() {
        return city;
    }

    /**
     *
     * @param city
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     *
     */
    public void insert(){
        AddressDAO.insertAddress(this);
    }
    
    /**
     *
     */
    public void update(){
        AddressDAO.updateAddress(this);
    }
    
    /**
     *
     */
    public void delete(){
        AddressDAO.deleteAddress(this);
    }
    
    /**
     *
     */
    public void merge(){
        AddressDAO.mergeAddress(this);
    }
    
    /**
     *
     */
    public static List<Address> list(){
        return AddressDAO.selectAddresses();
    }
}
