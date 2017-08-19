/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.sistema.Settings;
import br.com.senaimg.wms.model.sistema.User;
import br.com.senaimg.wms.model.sistema.enums.CssTheme;
import br.com.senaimg.wms.util.Encryption;
import br.com.senaimg.wms.util.ValidateFieldUtil;
import br.com.senaimg.wms.view.layout.AlertCustom;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class ChangePasswordController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private Label oldLabel;
    @FXML
    private Label newLabel;
    @FXML
    private Label confirmLabel;
    @FXML
    private Button saveButton;
    @FXML
    private PasswordField oldField;
    @FXML
    private PasswordField newField;
    @FXML
    private PasswordField confirmField;
    private User user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTheme();
        setTexts();
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private void saveAction(ActionEvent event) {
        String newS = newField.getText();
        String oldS = oldField.getText();
        String confirmS = confirmField.getText();

        if (oldS == null) {
            oldS = "";
        }

        if (newS == null) {
            newS = "";
        }

        if (confirmS == null) {
            confirmS = "";
        }
        if (newS.isEmpty() || oldS.isEmpty() || confirmS.isEmpty()) {
            showAlertError("Error", "Fill the data correctly");
        } else if (!newS.equals(confirmS)) {
            showAlertError("Error", "Passwords don't match");
        } else if (!Arrays.equals(user.getPassword(), Encryption.encrypt(oldS))) {
            showAlertError("Error", "Wrong Password");
        } else if (!ValidateFieldUtil.isValidPassword(newS)) {
            showAlertError("Error", "PASSWORD_NOT_VALID");
        } else {

            Optional<ButtonType> result = showAlertSure("Confirmation", "Are you sure you want to change your password?");
            if (result.get() == ButtonType.YES) {
                user.setPassword(newS);
                user.update();
                Stage stage = (Stage) rootPane.getScene().getWindow();
                stage.close();
            }
        }

    }

    private Optional<ButtonType> showAlertSure(String title, String message) {
        Alert alertModify = new AlertCustom(Alert.AlertType.CONFIRMATION, Lang.get(message), ButtonType.YES, ButtonType.NO);
        alertModify.setTitle(Lang.get(title));
        Optional<ButtonType> result = alertModify.showAndWait();
        return result;
    }

    private void showAlertError(String title, String message) {
        Alert alertInvalid = new AlertCustom(Alert.AlertType.ERROR, Lang.get(message), ButtonType.OK);
        alertInvalid.setTitle(Lang.get(title));
        alertInvalid.show();
    }

    private void setTexts() {
        oldLabel.setText(Lang.get(oldLabel.getText()));
        newLabel.setText(Lang.get(newLabel.getText()));
        confirmLabel.setText(Lang.get(confirmLabel.getText()));
        saveButton.setText(Lang.get(saveButton.getText()));
    }

    private void setTheme() {
        String path = "br/com/senaimg/wms/view/";
        String css = "changepassword.css";
        String theme;

        Settings s = Settings.getLast();
        if (s.getTheme() == CssTheme.DARK) {
            theme = "stylesheet";
        } else {
            theme = "stylesheetLight";
        }

        ObservableList<String> stylesheets = rootPane.getStylesheets();
        stylesheets.clear();
        stylesheets.add(path + theme + "/" + css);
    }

}
