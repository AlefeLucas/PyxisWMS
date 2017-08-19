/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.layout;

import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 *
 * @author Alefe Lucas
 */
public class Wizard {

    private TabPane pane;
    private Tab[] steps;
    private Button next;
    private Button back;
    private Button done;
    private Button cancel;
    private Integer activeIndex;

    private boolean isLastStep;
    
    /**
     *
     * @param next
     * @param back
     * @param done
     * @param cancel
     * @param pane
     * @param steps
     */
    public Wizard(Button next, Button back, Button done, Button cancel, TabPane pane, Tab... steps) {
        this.pane = pane;
        this.steps = steps;
        this.next = next;
        this.back = back;
        this.done = done;
        this.cancel = cancel;
        activeIndex = 0;
        setTabs();
    }

    private void setTabs() {
        for (int x = 0; x < steps.length; x++) {
            if (x == activeIndex) {
                steps[x].setDisable(false);
            } else {
                steps[x].setDisable(true);
            }
        }
        setButtons();
    }

    private void setButtons() {
        cancel.setDisable(false);
        if (activeIndex == 0) {
            next.setDisable(true);
            back.setDisable(true);
            done.setDisable(true);            
        } else if (activeIndex > 0 && activeIndex < steps.length - 1) {
            next.setDisable(true);
            back.setDisable(false);
            done.setDisable(true);
        } else if (activeIndex == steps.length - 1) {
            next.setDisable(true);
            back.setDisable(false);
            done.setDisable(false);
            isLastStep = true;
        }
    }

    /**
     *
     */
    public void next() {
        incrementIndex();
        setTabs();
        selectTab();
    }

    /**
     *
     */
    public void back() {
        decrementIndex();
        setTabs();
        selectTab();
    }
    
    private void selectTab(){
        pane.getSelectionModel().select(activeIndex);
    }

    /**
     *
     */
    public void stepDone() {
        if (activeIndex < steps.length - 1) {
            next.setDisable(false);
        }
    }
    
    /**
     *
     */
    public void stepUndone() {
        next.setDisable(true);
    }

    private void decrementIndex() {
        if (activeIndex <= 0) {
            activeIndex = 0;
        } else {
            activeIndex--;
        }
    }

    private void incrementIndex() {
        if (activeIndex >= steps.length - 1) {
            activeIndex = steps.length - 1;
        } else {
            activeIndex++;
        }
    }

    /**
     *
     * @return
     */
    public boolean isLastStep(){
        return this.isLastStep;
    }
    
}
