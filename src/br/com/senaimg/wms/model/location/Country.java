/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.location;

import br.com.senaimg.wms.dao.CountryDAO;
import java.io.Serializable;
import java.util.List;
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
@Table(name ="country")
public class Country implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String iso;

    @Override
    public String toString() {
        return name;
    }
    
    @OneToMany(mappedBy = "country")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<State> states;

    /**
     * Creates a new Country
     */
    public Country() {
    }

    /**
     * Creates a new Country
     * @param name
     * @param iso
     */
    public Country(String name, String iso) {
        this.name = name;
        this.iso = iso;
    }

    /**
     * Creates a new Country
     * @param id
     */
    public Country(Integer id) {
        this.id = id;
    }

    /**
     * Creates a new Country
     * @param id
     * @param name
     * @param iso
     */
    public Country(Integer id, String name, String iso) {
        this.id = id;
        this.name = name;
        this.iso = iso;
    }

    /**
     * Creates a new Country
     * @param name
     */
    public Country(String name) {
        this.name = name;
    }

    /**
     * Identifier
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Identifier
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Name of this Country
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Name of this Country
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Abbreviation for this Country eg. UK
     * @return iso
     */
    public String getIso() {
        return iso;
    }

    /**
     * Abbreviation for this Country eg. UK
     * @param iso
     */
    public void setIso(String iso) {
        this.iso = iso;
    }

    /**
     * Inserts this object into the database
     */
    public void insert(){
        CountryDAO.insertCountry(this);
    }
    
    /**
     * Updates this object in the database
     */
    public void update(){
        CountryDAO.updateCountry(this);
    }
    
    /**
     * Deletes this object from the database
     */
    public void delete(){
        CountryDAO.deleteCountry(this);
    }
    
    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge(){
        CountryDAO.mergeCountry(this);
    }
    
    /**
     * Lists this class from the database
     * @return List &lt Country>
     */
    public static List<Country> list(){           
        return CountryDAO.selectCountries();
    }

    /**
     *
     * @return
     */
    public List<State> getStates() {
        return states;
    }

    /**
     *
     * @param states
     */
    public void setStates(List<State> states) {
        this.states = states;
    }
    
    
    
}
