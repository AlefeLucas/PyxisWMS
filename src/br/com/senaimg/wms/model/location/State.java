/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.location;

import br.com.senaimg.wms.dao.StateDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Alefe Lucas
 */
@Entity
@Table (name = "state")
public class State implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String iso;
    
    @ManyToOne
    @JoinColumn (nullable = false)
    private Country country;
    
    @OneToMany(mappedBy = "state")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<City> cities;

    /**
     *
     */
    public State() {
    }
    
      @Override
    public String toString() {
        return name;
    }

    /**
     * Creates a new State object
     * @param name
     * @param iso - Example: RJ, WA
     */
    public State(String name, String iso) {
        this.name = name;
        this.iso = iso;
    }

    /**
     * Creates a new State object
     * @param id
     */
    public State(Integer id) {
        this.id = id;
    }

    /**
     * Creates a new State object
     * @param id
     * @param name
     * @param iso - Example: NY, SP
     */
    public State(Integer id, String name, String iso) {
        this.id = id;
        this.name = name;
        this.iso = iso;
    }

    /**
     * Creates a new State object
     * @param name
     */
    public State(String name) {
        this.name = name;
    }

    /**
     * Creates a new State object
     * @param id
     * @param name
     * @param iso - Example: NY, SP
     * @param country
     */
    public State(Integer id, String name, String iso, Country country) {
        this.id = id;
        this.name = name;
        this.iso = iso;
        this.country = country;
    }

    /**
     * Creates a new State objecT
     * @param name
     * @param iso - Example: NY, SP
     * @param country
     */
    public State(String name, String iso, Country country) {
        this.name = name;
        this.iso = iso;
        this.country = country;
    }

    /**
     * Creates a new State objecT
     * @param name
     * @param country
     */
    public State(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    /**
     * Creates a new State objecT
     * @param id
     * @param country
     */
    public State(Integer id, Country country) {
        this.id = id;
        this.country = country;
    }

    /**
     * Example: NY, SP
     * @return ISO code
     */
    public String getIso() {
        return iso;
    }

    /**
     * Example: NY, SP
     * @param iso
     */
    public void setIso(String iso) {
        this.iso = iso;
    }

    /**
     * Gets the Country of this State
     * @return country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Sets the Country of this state
     * @param country
     */
    public void setCountry(Country country) {
        this.country = country;
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
     * Name of the State
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Name of the State
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     *
     */
    public void insert(){
        StateDAO.insertState(this);
    }
    
    /**
     *
     */
    public void update(){
        StateDAO.updateState(this);
    }
    
    /**
     *
     */
    public void delete(){
        StateDAO.deleteState(this);
    }
    
    /**
     *
     */
    public void merge(){
        StateDAO.mergeState(this);
    }
    
    /**
     *
     * @return
     */
    public static List<State> list(){
        return StateDAO.selectStates();
    }
    
    /**
     *
     * @param country
     * @return
     */
    public static List<State> list(Country country){
        return StateDAO.selectStates(country);
    }

    /**
     *
     * @return
     */
    public List<City> getCities() {
        return cities;
    }

    /**
     *
     * @param cities
     */
    public void setCities(List<City> cities) {
        this.cities = cities;
    }

}
