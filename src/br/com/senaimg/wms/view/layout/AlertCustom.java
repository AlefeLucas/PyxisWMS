/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.layout;

import br.com.senaimg.wms.model.sistema.Settings;
import br.com.senaimg.wms.model.sistema.enums.CssTheme;
import br.com.senaimg.wms.util.SystemImageUtil;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Alefe Lucas
 */
public class AlertCustom extends Alert {

    private void setTheme(Pane pane) {
        String path = "br/com/senaimg/wms/view/";
        String css = "dialog.css";
        String theme;

        Settings s = Settings.getLast();

        if (s != null) {
            if (s.getTheme() == CssTheme.DARK) {
                theme = "stylesheet";
            } else {
                theme = "stylesheetLight";
            }
            ObservableList<String> stylesheets = pane.getStylesheets();
            stylesheets.clear();
            stylesheets.add(path + theme + "/" + css);
        }

    }

    private void setTheme(Pane pane, String theme) {
        String path = "br/com/senaimg/wms/view/";
        String css = "dialog.css";

        ObservableList<String> stylesheets = pane.getStylesheets();
        stylesheets.clear();
        stylesheets.add(path + theme + "/" + css);
    }

    /**
     *
     * @param alertType
     * @param contentText
     * @param buttons
     */
    public AlertCustom(AlertType alertType, String contentText, ButtonType... buttons) {
        super(alertType, contentText, buttons);
        //setStyle("dialog.css");
        setTheme(this.getDialogPane());
        Stage stage = (Stage) this.getDialogPane().getScene().getWindow();
        stage.getIcons().add(SystemImageUtil.getImage(SystemImageUtil.WMSICON_128));
    }

    public AlertCustom(AlertType alertType, String contentText, String theme, ButtonType... buttons) {
        super(alertType, contentText, buttons);
        //setStyle("dialog.css");
        setTheme(this.getDialogPane(), theme);
        Stage stage = (Stage) this.getDialogPane().getScene().getWindow();
        stage.getIcons().add(SystemImageUtil.getImage(SystemImageUtil.WMSICON_128));
    }

    /**
     *
     * @param alertType
     */
    public AlertCustom(AlertType alertType) {
        super(alertType);
        //setStyle("dialog.css");
        setTheme(this.getDialogPane());
        Stage stage = (Stage) this.getDialogPane().getScene().getWindow();
        stage.getIcons().add(SystemImageUtil.getImage(SystemImageUtil.WMSICON_128));
    }

    private void setStyle(String stylesheet) {
        DialogPane dialogPane = this.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/br/com/senaimg/wms/view/stylesheet/" + stylesheet).toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
    }

}
