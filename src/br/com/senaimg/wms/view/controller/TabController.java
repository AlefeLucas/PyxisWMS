/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.model.sistema.User;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Tab;

/**
 *
 * @author ÁlefeLucas
 */
public class TabController {

    private User loggedUser;
    private BooleanProperty userSet = new SimpleBooleanProperty(false);
    private Tab tab;
    private HomeController home;

    /**
     *
     * @return
     */
    public User getLoggedUser() {
        return loggedUser;
    }

    /**
     *
     * @param home
     */
    public void setHome(HomeController home) {

        this.home = home;
        this.loggedUser = home.getUser();
        userSet.setValue(!userSet.getValue());
    }

    /**
     *
     * @return
     */
    public BooleanProperty getUserSet() {
        return userSet;
    }

    /**
     *
     * @return
     */
    public Tab getTab() {
        return tab;
    }

    /**
     *
     * @param tab
     */
    public void setTab(Tab tab) {
        this.tab = tab;
    }

    /**
     *
     * @return
     */
    public HomeController getHome() {
        return home;
    }

 
}
