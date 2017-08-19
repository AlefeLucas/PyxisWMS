/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.sistema.Settings;
import br.com.senaimg.wms.model.sistema.enums.CssTheme;
import br.com.senaimg.wms.util.SystemImageUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author ÁlefeLucas
 */
public class AboutController implements Initializable {

    @FXML
    private TextArea aboutTextArea;
    @FXML
    private StackPane rootPane;
    @FXML
    private ImageView pyxisLogo;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTexts();
        setTheme();
    }

    private void setTexts() {
        aboutTextArea.setText(Lang.get("ABOUT_TEXT"));
    }

    private void setTheme() {
        String path = "br/com/senaimg/wms/view/";
        String css = "about.css";
        String theme;

        Settings s = Settings.getLast();
        if (s.getTheme() == CssTheme.DARK) {
             pyxisLogo.setImage(SystemImageUtil.getImage(SystemImageUtil.PYXIS_256));
            theme = "stylesheet";
        } else {
            pyxisLogo.setImage(SystemImageUtil.getImage(SystemImageUtil.PYXIS_256_BLACK));
            theme = "stylesheetLight";
        }

        ObservableList<String> stylesheets = rootPane.getStylesheets();
        stylesheets.clear();
        stylesheets.add(path + theme + "/" + css);
    }

}
