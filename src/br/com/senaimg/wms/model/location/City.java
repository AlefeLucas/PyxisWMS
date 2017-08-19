/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.location;

import br.com.senaimg.wms.dao.CityDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Alefe Lucas
 */
@Entity
@Table (name = "city")
public class City implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;
    private String name;    
    
    @ManyToOne
    @JoinColumn (nullable = false)
    private State state;
    @OneToMany(mappedBy = "city")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Address> addresses;

    /**
     *
     */
    public City() {
    }

      @Override
    public String toString() {
        return name;
    }
    
    /**
     * Gets the State of this City
     *
     * @return
     */
    public State getState() {
        return state;
    }

    /**
     * Sets the State of this City
     *
     * @param state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Creates a new City
     *
     * @param id
     */
    public City(Integer id) {
        this.id = id;
    }

    /**
     * Creates a new City
     *
     * @param id
     * @param name
     */
    public City(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Creates a new City
     *
     * @param name
     */
    public City(String name) {
        this.name = name;
    }

    /**
     * Creates a new City
     *
     * @param id
     * @param name
     * @param state
     */
    public City(Integer id, String name, State state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    /**
     * Creates a new City
     *
     * @param name
     * @param state
     */
    public City(String name, State state) {
        this.name = name;
        this.state = state;
    }

    /**
     * Creates a new City
     *
     * @param id
     * @param state
     */
    public City(Integer id, State state) {
        this.id = id;
        this.state = state;
    }

    /**
     * Identifier
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Identifier
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Name of this City
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Name of this City
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Inserts this object into the database
     */
    public void insert(){
        CityDAO.insertCity(this);
    }
    
    /**
     * Updates this object in the database
     */
    public void update(){
        CityDAO.updateCity(this);
    }
    
    /**
     * Deletes this object from the database
     */
    public void delete(){
        CityDAO.deleteCity(this);
    }
    
    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge(){
        CityDAO.mergeCity(this);
    }
    
    /**
     * Lists this class from the database
     * @return List &lt City>
     */
    public static List<City> list(){
        return CityDAO.selectCities();
    }
    
    /**
     * Lists this class from the database with a restriction of state
     * @param state
     * @return
     */
    public static List<City> list(State state){
        return CityDAO.selectCities(state);
    }

    /**
     *
     * @return
     */
    public List<Address> getAddresses() {
        return addresses;
    }

    /**
     *
     * @param addresses
     */
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
    
}
