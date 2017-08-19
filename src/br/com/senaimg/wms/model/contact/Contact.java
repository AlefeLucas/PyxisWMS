/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.contact;

import java.io.Serializable;
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
@Table( name = "contact")
public class Contact implements Serializable{
    
    private static final long serialVersionUID = 201606160003L;
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    private String phone;
    private String phoneAlt;
    private String fax;
    private String email;
    private String webPage;

    /**
     *
     */
    public Contact() {
    }

    /**
     *
     * @param id
     */
    public Contact(int id) {
        this.id = id;
    }

    /**
     *
     * @param phone
     * @param phoneAlt
     * @param fax
     * @param email
     * @param webPage
     */
    public Contact(String phone, String phoneAlt, String fax, String email, String webPage) {
        this.phone = phone;
        this.phoneAlt = phoneAlt;
        this.fax = fax;
        this.email = email;
        this.webPage = webPage;
    }
    
    public void setFields(String phone, String phoneAlt, String fax, String email, String webPage) {
        this.phone = phone;
        this.phoneAlt = phoneAlt;
        this.fax = fax;
        this.email = email;
        this.webPage = webPage;
    }

    /**
     *
     * @param id
     * @param phone
     * @param phoneAlt
     * @param fax
     * @param email
     * @param webPage
     */
    public Contact(int id, String phone, String phoneAlt, String fax, String email, String webPage) {
        this.id = id;
        this.phone = phone;
        this.phoneAlt = phoneAlt;
        this.fax = fax;
        this.email = email;
        this.webPage = webPage;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
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
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     */
    public String getPhoneAlt() {
        return phoneAlt;
    }

    /**
     *
     * @param phoneAlt
     */
    public void setPhoneAlt(String phoneAlt) {
        this.phoneAlt = phoneAlt;
    }

    /**
     *
     * @return
     */
    public String getFax() {
        return fax;
    }

    /**
     *
     * @param fax
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }
    
    
    
    
}
