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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ÁlefeLucas
 */
public class UserManualController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private HBox headerPane;
    @FXML
    private ImageView tipIcon;
    @FXML
    private HBox footerPane;
    @FXML
    private CheckBox showStartupCheckBox;
    @FXML
    private Button previousButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button closeButton;
    @FXML
    private WebView webView;
    private WebEngine engine;

    private static final String[] HTML = {
        "tip1.html",
        "tip2.html",
        "tip3.html",
        "tip4.html",
        "tip5.html",
        "tip6.html"
    };

    private int currentIndex = 0;
    @FXML
    private Label titleLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTexts();
        setTheme();
        Settings last = Settings.getLast();
        showStartupCheckBox.setSelected(last.isShowTipOnStartup());
        engine = webView.getEngine();

        engine.load(getHtmlURL(HTML[currentIndex]));

    }

    private void setTexts() {
        titleLabel.setText(Lang.get(titleLabel.getText()));
        previousButton.setText(Lang.get(previousButton.getText()));
        nextButton.setText(Lang.get(nextButton.getText()));
        closeButton.setText(Lang.get(closeButton.getText()));

        showStartupCheckBox.setText(Lang.get(showStartupCheckBox.getText()));
    }

    public boolean showTipOnStartup() {
        return showStartupCheckBox.isSelected();
    }

    private String getHtmlURL(String fileName) {
        try {
            String url = UserManualController.class.getResource("/br/com/senaimg/wms/view/html/" + fileName).toExternalForm();
            return url;
        } catch (NullPointerException ex) {
            return null;
        }

    }

    @FXML
    private void previousHandle(ActionEvent event) {
        currentIndex--;
        if (currentIndex < 0) {
            currentIndex = HTML.length - 1;
        }
        engine.load(getHtmlURL(HTML[currentIndex]));
    }

    @FXML
    private void nextHandle(ActionEvent event) {
        currentIndex++;
        if (currentIndex == HTML.length) {
            currentIndex = 0;
        }
        engine.load(getHtmlURL(HTML[currentIndex]));
    }

    @FXML
    private void closeHandle(ActionEvent event) {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        Settings last = Settings.getLast();
        last.setShowTipOnStartup(showStartupCheckBox.isSelected());
        last.merge();
        stage.close();
    }

    private void setTheme() {
        String path = "br/com/senaimg/wms/view/";
        String css = "usermanual.css";
        String theme;

        Settings s = Settings.getLast();
        if (s.getTheme() == CssTheme.DARK) {
            tipIcon.setImage(SystemImageUtil.getImage(SystemImageUtil.TIPS));
            theme = "stylesheet";
        } else {
            tipIcon.setImage(SystemImageUtil.getImage(SystemImageUtil.TIPS_BLACK));
            theme = "stylesheetLight";
        }

        ObservableList<String> stylesheets = rootPane.getStylesheets();
        stylesheets.clear();
        stylesheets.add(path + theme + "/" + css);
    }
}
