/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.sistema;

import br.com.senaimg.wms.dao.FunctionalityGroupDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Alefe Lucas
 */
@Entity
@Table(name = "functionality_group")
public class FunctionalityGroup implements Serializable {

    @OneToMany(mappedBy = "functionalityGroup", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Functionality> functionalities;
    @Column(unique = true, nullable = false)
    private String title;

    /**
     *
     * @param title
     */
    public FunctionalityGroup(String title) {
        this.title = title;
    }

    /**
     *
     * @param functionalities
     * @param title
     */
    public FunctionalityGroup(ArrayList<Functionality> functionalities, String title) {
        this.functionalities = functionalities;
        this.title = title;
    }

    /**
     *
     * @param id
     */
    public FunctionalityGroup(Integer id) {
        this.id = id;
    }

    /**
     *
     */
    public FunctionalityGroup() {
    }

    /**
     *
     * @param functionalities
     * @param title
     * @param id
     */
    public FunctionalityGroup(ArrayList<Functionality> functionalities, String title, Integer id) {
        this.functionalities = functionalities;
        this.title = title;
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

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
     * @return
     */
    public List<Functionality> getFunctionalities() {
        return functionalities;
    }

    /**
     *
     * @param functionalities
     */
    public void setFunctionalities(ArrayList<Functionality> functionalities) {
        this.functionalities = functionalities;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     */
    public void insert() {
        FunctionalityGroupDAO.insertFunctionalityGroup(this);
        this.refresh();
    }

    /**
     *
     */
    public void update() {
        FunctionalityGroupDAO.updateFunctionalityGroup(this);
        this.refresh();
    }

    /**
     *
     */
    public void merge() {
        FunctionalityGroupDAO.mergeFunctionalityGroup(this);
        this.refresh();
    }

    /**
     *
     */
    public void delete() {
        FunctionalityGroupDAO.deleteFunctionalityGroup(this);
    }

    /**
     *
     * @return
     */
    public static List<FunctionalityGroup> list() {
        return FunctionalityGroupDAO.selectFunctionalityGroups();
    }

    @Override
    public String toString() {
        return title;
    }
    
  private void refresh(){
        FunctionalityGroupDAO.refresh(this);
    }

}
