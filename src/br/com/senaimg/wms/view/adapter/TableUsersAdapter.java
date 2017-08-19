/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.adapter;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.sistema.Profile;
import br.com.senaimg.wms.model.sistema.User;
import java.text.SimpleDateFormat;

/**
 *
 * @author Alefe Lucas
 */
public class TableUsersAdapter {

    private String status;
    private String username;
    private String name;
    private String profile;
    private String email;
    private String birthdate;
    private String gender;
    private User user;

    /**
     *
     */
    public TableUsersAdapter() {
    }

    /**
     *
     * @param user
     */
    public TableUsersAdapter(User user) {
        this.user = user;
        status = user.getStatus().toString();
        username = user.getUsername();
        name = user.getName();
        Profile prof = user.getProfile();
        if (prof != null) {
            profile = prof.getName();
        } else {
            profile = Lang.get("Not set");
        }

        email = user.getEmail();
        birthdate = new SimpleDateFormat("dd/MM/yyyy").format(user.getBirthDate());
        gender = user.getGender().toString();
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
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
    public String getProfile() {
        return profile;
    }

    /**
     *
     * @param profile
     */
    public void setProfile(String profile) {
        this.profile = profile;
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
    public String getBirthdate() {
        return birthdate;
    }

    /**
     *
     * @param birthdate
     */
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    /**
     *
     * @return
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
        status = user.getStatus().toString();
        username = user.getUsername();
        name = user.getName();
        profile = user.getProfile().getName();
        email = user.getEmail();
        birthdate = new SimpleDateFormat("dd/MM/yyyy").format(user.getBirthDate());
        gender = user.getGender().toString();
    }

}
