/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author ÁlefeLucas
 */
public class WelcomeController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Button startButton;
    @FXML
    private Label subWelcomeLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTexts();
    }

    public Button getStartButton() {
        return startButton;
    }

    private void setTexts() {
        startButton.setText(Lang.get(startButton.getText()));
        welcomeLabel.setText(Lang.get(welcomeLabel.getText()));
        subWelcomeLabel.setText(Lang.get(subWelcomeLabel.getText()));
    }

   

}
