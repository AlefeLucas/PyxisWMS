/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

/**
 * FXML Controller class
 *
 * @author ÁlefeLucas
 */
public class LoadingController implements Initializable {

    @FXML
    private ProgressIndicator loadingIndicator;
    @FXML
    private Label loadingLabel;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     *
     * @return
     */
    public Label getLoadingLabel() {
        return loadingLabel;
    }

    /**
     *
     * @param loadingLabel
     */
    public void setLoadingLabel(Label loadingLabel) {
        this.loadingLabel = loadingLabel;
    }
    
    
    
}
