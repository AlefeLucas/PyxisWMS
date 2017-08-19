/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.sistema;

import br.com.senaimg.wms.dao.ProfileDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
@Table(name = "profile")
public class Profile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String name;
    @OneToMany(mappedBy = "profile")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> users;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.MERGE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Permission> permissions;

    /**
     *
     */
    public Profile() {
        users = new ArrayList<>();
        permissions = new HashSet<>();
    }

    /**
     *
     * @param name
     */
    public Profile(String name) {
        this();
        this.name = name;
    }

    /**
     *
     * @param user
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     *
     * @param permission
     */
    public void addPermission(Permission permission) {
        permissions.add(permission);
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
     * Name of category
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Name of category
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     */
    public void insert() {
        ProfileDAO.insertProfile(this);
    }

    /**
     *
     */
    public void update() {
        ProfileDAO.updateProfile(this);
    }

    /**
     *
     */
    public void delete() {
        ProfileDAO.deleteProfile(this);
    }

    /**
     *
     */
    public void merge() {
        ProfileDAO.mergeProfile(this);
    }

    /**
     *
     * @return
     */
    public static List<Profile> list() {
        return ProfileDAO.selectProfiles();
    }

    /**
     *
     * @return
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     *
     * @param users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     *
     * @return
     */
    public Set<Permission> getPermissions() {
        return permissions;
    }

    /**
     *
     * @param permissions
     */
    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return name;
    }

}
