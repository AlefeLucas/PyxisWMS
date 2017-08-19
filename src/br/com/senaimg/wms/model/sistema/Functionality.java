/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.sistema;

import br.com.senaimg.wms.dao.FunctionalityDAO;
import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.util.SystemImageUtil;
import java.io.Serializable;
import java.util.List;
import javafx.scene.image.Image;
import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Alefe Lucas
 */
@Entity
@Table(name = "functionality")
public class Functionality implements Serializable {

    /**
     *
     * @param functionalityGroup
     * @param title
     * @param active
     * @param tabfxml
     * @param imageName
     */
    public Functionality(FunctionalityGroup functionalityGroup, String title, boolean active, String tabfxml, String imageName) {
        this.functionalityGroup = functionalityGroup;
        this.title = title;
        this.active = active;
        this.tabfxml = tabfxml;
        this.imageName = imageName;
    }

    /**
     *
     * @param title
     * @param active
     * @param tabfxml
     * @param imageName
     */
    public Functionality(String title, boolean active, String tabfxml, String imageName) {
        this.title = title;
        this.active = active;
        this.tabfxml = tabfxml;
        this.imageName = imageName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private FunctionalityGroup functionalityGroup;
    @Column(unique = true, nullable = false)
    private String title;
    private boolean active;
    @Column(unique = true, nullable = false)
    private String tabfxml;
    @Column(nullable = false)
    private String imageName;

    @OneToMany(mappedBy = "function", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Permission> permissions;

    /**
     * Creates a new Functionality
     */
    public Functionality() {
    }

    /**
     * Creates a new Functionality
     *
     * @param id
     */
    public Functionality(Integer id) {
        this.id = id;
    }

    /**
     * Creates a new Functionality
     *
     * @param id
     * @param functionalityGroup
     * @param title
     * @param active
     * @param tabfxml
     * @param permissions
     */
    public Functionality(Integer id, FunctionalityGroup functionalityGroup, String title, boolean active, String tabfxml, List<Permission> permissions) {
        this.id = id;
        this.functionalityGroup = functionalityGroup;
        this.title = title;
        this.active = active;
        this.permissions = permissions;
    }

    /**
     * Creates a new Functionality
     *
     * @param functionalityGroup
     * @param title
     * @param active
     * @param tabfxml
     * @param permissions
     */
    public Functionality(FunctionalityGroup functionalityGroup, String title, boolean active, String tabfxml, List<Permission> permissions) {
        this.functionalityGroup = functionalityGroup;
        this.title = title;
        this.active = active;
        this.permissions = permissions;
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
     * Group of which this object is part of
     *
     * @return functionalityGroup
     */
    public FunctionalityGroup getFunctionalityGroup() {
        return functionalityGroup;
    }

    /**
     * Group of which this object is part of
     *
     * @param functionalityGroup
     */
    public void setFunctionalityGroup(FunctionalityGroup functionalityGroup) {
        this.functionalityGroup = functionalityGroup;
    }

    /**
     * Title
     *
     * @return
     */
    public String getTitle() {
        return Lang.get(title);
    }

    /**
     * Title
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Whether this functionality is on or off
     *
     * @return active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Whether this functionality is on or off
     *
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * The permissions to this functionality
     *
     * @return List &lt Permission>
     */
    public List<Permission> getPermissions() {
        return permissions;
    }

    /**
     * The permissions to this functionality
     *
     * @param permissions
     */
    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    /**
     * The fxml associated to this functionality
     *
     * @return tabfxml
     */
    public String getTabfxml() {
        return tabfxml;
    }

    /**
     * The fxml associated to this functionality
     *
     * @param tabfxml
     */
    public void setTabfxml(String tabfxml) {
        this.tabfxml = tabfxml;
    }

    /**
     * Inserts this object into the database
     */
    public void insert() {
        FunctionalityDAO.insertFunctionality(this);
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        FunctionalityDAO.updateFunctionality(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        FunctionalityDAO.mergeFunctionality(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        FunctionalityDAO.deleteFunctionality(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt Functionality &gt
     */
    public static List<Functionality> list() {

        return FunctionalityDAO.selectFunctionalities();
    }

    /**
     * Gets the image name
     *
     * @return imageName
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * Sets the image name
     *
     * @param imageName
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     * Gets the image
     *
     * @return image
     */
    public Image getImage() {
        return SystemImageUtil.getImage(imageName);
    }

}
