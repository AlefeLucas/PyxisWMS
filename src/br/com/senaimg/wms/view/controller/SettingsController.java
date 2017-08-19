/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.connection.ConnectionFactory;
import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.language.Language;
import br.com.senaimg.wms.model.sistema.Settings;
import br.com.senaimg.wms.model.sistema.enums.CssTheme;
import br.com.senaimg.wms.util.SystemImageUtil;
import br.com.senaimg.wms.view.Console;
import br.com.senaimg.wms.view.StarterFx;
import br.com.senaimg.wms.view.layout.AlertCustom;
import com.sun.javafx.stage.StageHelper;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ÁlefeLucas
 */
public class SettingsController implements Initializable {
    
    @FXML
    private StackPane rootPane;
    @FXML
    private StackPane titlePane;
    @FXML
    private Label titleLabel;
    @FXML
    private StackPane backgroundPane;
    @FXML
    private TabPane tabPane;
    @FXML
    private VBox centerPane;
    @FXML
    private Label languageLabel;
    @FXML
    private RadioButton ptButton;
    @FXML
    private ToggleGroup language;
    @FXML
    private RadioButton enButton;
    
    @FXML
    private Label themeLabel;
    @FXML
    private RadioButton darkButton;
    @FXML
    private ToggleGroup theme;
    @FXML
    private RadioButton whiteButton;
    @FXML
    private VBox centerPane1;
    @FXML
    private HBox footPane;
    @FXML
    private Button buttonSave;
    @FXML
    private Label resetLabel;
    @FXML
    private Button resetButton;
    
    private Service<Void> service;
    private Stage loadingStage;
    private Connection con;
    private Settings last;

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
        loadSettings();
    }
    
    private void setTexts() {
        titleLabel.setText(Lang.get(titleLabel.getText()));
        languageLabel.setText(Lang.get(languageLabel.getText()));
        themeLabel.setText(Lang.get(themeLabel.getText()));
        ptButton.setText(Lang.get(ptButton.getText()));
        enButton.setText(Lang.get(enButton.getText()));
        darkButton.setText(Lang.get(darkButton.getText()));
        whiteButton.setText(Lang.get(whiteButton.getText()));
        resetLabel.setText(Lang.get(resetLabel.getText()));
        resetButton.setText(Lang.get(resetButton.getText()));
        buttonSave.setText(Lang.get(buttonSave.getText()));
        ObservableList<Tab> tabs = tabPane.getTabs();
        for (Tab tab : tabs) {
            tab.setText(Lang.get(tab.getText()));            
        }
        
    }
    
    private void setTheme() {
        String path = "br/com/senaimg/wms/view/";
        String css = "settings.css";
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
    
    int exit = 0;
    
    @FXML
    private void resetHandle(ActionEvent event) throws IOException {
        //updateMessage(Lang.get("Deleting database..."));

        try {
            service = new Service<Void>() {
                @Override
                protected Task<Void> createTask() {
                    return new Task<Void>() {
                        
                        @Override
                        protected Void call() throws Exception {
                            SessionHibernateUtil.getSessionFactory().close();
                            SessionHibernateUtil.setSessionFactory(null);
                            updateMessage(Lang.get("Deleting database..."));
                            try {
                                Statement st = con.createStatement();
                                st.execute("drop schema pyxis_wms");
                                st.close();
                                con.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                exit = 1;
                                showAlertError("Error", "Could not delete database.");
                            }
                            return null;
                        }
                    };
                }
            };
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/Loading.fxml"));
            Parent rootNew;
            
            rootNew = fxmlLoader.load();
            
            LoadingController loadController = fxmlLoader.<LoadingController>getController();
            loadingStage = new Stage();
            loadingStage.getIcons().add(SystemImageUtil.getImage(SystemImageUtil.WMSICON_128));
            Scene sceneNew = new Scene(rootNew);
            sceneNew.setFill(null);
            loadingStage.setScene(sceneNew);
            loadingStage.initModality(Modality.APPLICATION_MODAL);
            loadingStage.initStyle(StageStyle.TRANSPARENT);
            
            service.setOnSucceeded(e -> {
                closeAndRestart();
            });
            
            loadController.getLoadingLabel().textProperty().bind(service.messageProperty());
            
            con = ConnectionFactory.getConnection();
            if (con == null) {
                exit = 1;
                showAlertError("Error", "Could not connect do database.");
                
            } else {
                Optional<ButtonType> result = showAlertSure("Reset", "RESTART_MESSAGE");
                if (result.get() == ButtonType.YES) {
                    loadingStage.show();
                    service.restart();
                }
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
    
    @FXML
    private void saveHandle(ActionEvent event) {
        Language language;
        
        CssTheme theme;
        Toggle selectedLang = this.language.getSelectedToggle();
        Toggle selectedTheme = this.theme.getSelectedToggle();
        
        if (selectedLang == ptButton) {
            language = Language.PTBR;
        } else {
            language = Language.ENUS;
        }
        
        if (selectedTheme == darkButton) {
            theme = CssTheme.DARK;
        } else {
            theme = CssTheme.LIGHT;
        }
        Optional<ButtonType> result = showAlertSure("Save Settings", "CHANGE_SETTINGS");
        if (result.get() == ButtonType.YES) {
            last.setTheme(theme);
            last.setLanguageSystem(language);
            last.setDateSaved(new Date());
            last.merge();
            Lang.loadLanguage();
            closeAndRestart();
        }
        
    }
    
    private void closeAndRestart() {
        try {            
            StarterFx.getStarter().getMainStage().setOnCloseRequest(null);
            
            ObservableList<Stage> stages = StageHelper.getStages();
            while(!stages.isEmpty()){
                stages.get(0).close();
            }
            Stage s = (Stage) rootPane.getScene().getWindow();
            s.close();
            Console console = Console.getConsole();            
            if(console != null){
                console.dispose();
            }
            StarterFx.getStarter().startApp(StarterFx.getStarter().getMainStage());
        } catch (IOException ex) {
            
            ex.printStackTrace();
            System.exit(1);
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
    
    private void loadSettings() {
        last = Settings.getLast();
        
        Language languageSystem = last.getLanguageSystem();
        CssTheme theme1 = last.getTheme();
        
        if (theme1 == CssTheme.DARK) {
            darkButton.setSelected(true);
        } else {
            whiteButton.setSelected(true);
        }
        
        if (languageSystem == Language.ENUS) {
            enButton.setSelected(true);
        } else {
            ptButton.setSelected(true);
        }
    }
    
}
