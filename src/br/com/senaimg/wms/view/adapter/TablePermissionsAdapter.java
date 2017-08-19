/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.adapter;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.sistema.Functionality;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Alefe Lucas
 */
public class TablePermissionsAdapter {

    private String group;
    private String function;
    private CheckBox on;
    private StackPane onPane;
    
    private Functionality functionality;

    /**
     *
     * @return
     */
    public String getGroup() {
        return group;
    }

    /**
     *
     * @param group
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     *
     * @return
     */
    public String getFunction() {
        return function;
    }

    /**
     *
     * @param function
     */
    public void setFunction(String function) {
        this.function = function;
    }

    /**
     *
     * @return
     */
    public CheckBox getOn() {
        return on;
    }
    
    /**
     *
     * @return
     */
    public boolean isOn() {
        return on.isSelected();
    }

    /**
     *
     * @param on
     */
    public void setOn(boolean on) {
        this.on.setSelected(on);
    }

    /**
     *
     * @param group
     * @param function
     * @param functionality
     */
    public TablePermissionsAdapter(String group, String function, Functionality functionality) {
        this.group = Lang.get(group);
        this.function = function;
        this.functionality = functionality; 
        on = new CheckBox();
        on.setAlignment(Pos.CENTER);
        onPane = new StackPane(on);
    }

    /**
     *
     * @param disable
     */
    public void setDisable(boolean disable) {
        
        on.setDisable(disable);
        
    }

    /**
     *
     * @param on
     */
    public void setOn(CheckBox on) {
        this.on = on;
    }

    /**
     *
     * @return
     */
    public StackPane getOnPane() {
        return onPane;
    }

    /**
     *
     * @param onPane
     */
    public void setOnPane(StackPane onPane) {
        this.onPane = onPane;
    }

    /**
     *
     * @return
     */
    public Functionality getFunctionality() {
        return functionality;
    }

    /**
     *
     * @param functionality
     */
    public void setFunctionality(Functionality functionality) {
        this.functionality = functionality;
    }
    
    
    
    
}
