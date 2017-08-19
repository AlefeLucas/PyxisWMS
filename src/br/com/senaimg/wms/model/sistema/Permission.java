/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.sistema;

import br.com.senaimg.wms.dao.PermissionDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Alefe Lucas
 */
@Entity
@Table(name = "permission")
public class Permission implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne 
   @JoinColumn (nullable = false)
    private Profile profile;
    @ManyToOne 
   @JoinColumn (nullable = false)
    private Functionality function;

    
    
    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @param profile
     * @param function
     */
    public Permission(Profile profile, Functionality function) {
        this.profile = profile;
        this.function = function;
    }

    /**
     *
     * @param function
     */
    public Permission(Functionality function) {
        this.function = function;
    }

    /**
     *
     * @param id
     */
    public Permission(Integer id) {
        this.id = id;
    }

    /**
     *
     */
    public Permission() {
    }

    /**
     *
     * @param id
     * @param profile
     * @param function
     */
    public Permission(Integer id, Profile profile, Functionality function) {
        this.id = id;
        this.profile = profile;
        this.function = function;
    }

    /**
     *
     * @return
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     *
     * @param profile
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     *
     * @return
     */
    public Functionality getFunction() {
        return function;
    }

    /**
     *
     * @param function
     */
    public void setFunction(Functionality function) {
        this.function = function;
    }

    /**
     *
     */
    public void insert(){
        PermissionDAO.insertPermission(this);
    }
    
    /**
     *
     */
    public void update(){
        PermissionDAO.updatePermission(this);
    }
    
    /**
     *
     */
    public void merge(){
        PermissionDAO.mergePermission(this);
    }
    
    /**
     *
     */
    public void delete(){
        PermissionDAO.deletePermission(this);
    }
    
    /**
     *
     * @return
     */
    public static List<Permission> list(){
        return PermissionDAO.selectPermissions();
    }
    
}
