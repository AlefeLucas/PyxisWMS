/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.sistema.Settings;
import br.com.senaimg.wms.model.sistema.User;
import br.com.senaimg.wms.util.InitialState;
import br.com.senaimg.wms.util.SystemImageUtil;
import br.com.senaimg.wms.view.controller.HomeController;
import br.com.senaimg.wms.view.controller.LoadingController;
import br.com.senaimg.wms.view.controller.LoginController;
import br.com.senaimg.wms.view.controller.SetupController;
import br.com.senaimg.wms.view.controller.WelcomeController;
import com.sun.javafx.stage.StageHelper;
import java.io.IOException;
import java.util.List;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 *
 * @author Alefe Lucas
 */
public class StarterFx extends Application {

    private User user;
    private Service<Void> service;
    //  private Optional<ButtonType> result;
    private Stage loadingStage;
    private LoginController login;
    private Scene sceneLogin;

    private boolean isFirstRun;
    private Stage mainStage;
    private static StarterFx starter;

    @Override
    public void start(Stage stage) throws Exception {
        starter = this;
        mainStage = stage;
        startApp(stage);
    }

    public static StarterFx getStarter() {
        return starter;
    }

    public void startApp(Stage stage) throws IOException {
       

        service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {

                    @Override
                    protected Void call() throws Exception {
                        updateMessage("Loading Database...");

                        List<Settings> list = Settings.list();

                        if (list.isEmpty()) {
                            isFirstRun = true;
                            updateMessage(Lang.get("Setting default state..."));
                            InitialState is = new InitialState();
                            is.set();
                        } else {
                            isFirstRun = false;
                        }

                        updateMessage(Lang.get("Loading Pyxis WMS..."));
                        FXMLLoader loginLoader = new FXMLLoader(
                                getClass().getResource("/br/com/senaimg/wms/view/fxml/Login.fxml"));
                        Parent rootLogin = null;
                        try {
                            rootLogin = loginLoader.load();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        updateMessage(Lang.get("Loaded!"));

                        sceneLogin = new Scene(rootLogin);
                        login = loginLoader.<LoginController>getController();

                        return null;
                    }
                };
            }
        };

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/Loading.fxml"));
        Parent rootNew = fxmlLoader.load();
        LoadingController loadController = fxmlLoader.<LoadingController>getController();

        loadingStage = new Stage();
        loadingStage.getIcons().add(SystemImageUtil.getImage(SystemImageUtil.WMSICON_128));
        Scene sceneNew = new Scene(rootNew);
        sceneNew.setFill(null);
        loadingStage.setScene(sceneNew);
        sceneNew.setOnKeyPressed(new EventHandler<KeyEvent>() {
            private int x = 0;
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.CONTROL) {
                    x++;
                    System.out.println(x);
                } 
                if(x == 3){
                    Console console = Console.create();
                }
            }
        });
        loadingStage.initStyle(StageStyle.TRANSPARENT);
        loadingStage.show();

        service.setOnSucceeded(event -> {
            stage.getIcons().add(SystemImageUtil.getImage(SystemImageUtil.WMSICON_128));
            stage.setTitle(Lang.get("Pyxis WMS"));
            stage.setMaximized(true);
            stage.setOnCloseRequest(e -> {
                login.stopTransition();
                SessionHibernateUtil.getSessionFactory().close();
            });
            
            stage.showingProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    login.startTransition();
                }
            });
            stage.setScene(sceneLogin);

            login.loggedIn.addListener(
                    (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {

                        login.stopTransition();
                        user = login.getLoggedUser();

                        FXMLLoader homeLoader = new FXMLLoader(
                                getClass().getResource("/br/com/senaimg/wms/view/fxml/Home.fxml"));
                        Parent rootHome = null;
                        try {
                            rootHome = homeLoader.load();
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, Lang.get("Error loading home screen"),
                                    Lang.get("Fatal Error"), JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                            System.exit(1);
                        }
                        Scene sceneHome = new Scene(rootHome);
                        HomeController home = homeLoader.<HomeController>getController();

                        home.logUser(user);
                        stage.setScene(sceneHome);
                        stage.setMaximized(false);
                        stage.setMaximized(true);

                        home.loggedOut.addListener((ObservableValue<? extends Boolean> observable1, Boolean oldValue1,
                                Boolean newValue1) -> {
                            login.loadUsers();
                            login.startTransition();
                            stage.setScene(sceneLogin);
                            stage.setMaximized(false);
                            stage.setMaximized(true);
                        });
                    });
            sceneNew.setOnKeyPressed(null);
            loadingStage.close();
             setNimbus();
            if (!isFirstRun) {
                login.loadUsers();
                stage.show();
            } else {
                try {
                    FXMLLoader ld = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/Welcome.fxml"));
                    Parent root = ld.load();

                    Stage stage2 = new Stage();
                    Scene scene = new Scene(root);
                    stage2.setScene(scene);
                    stage2.initModality(Modality.APPLICATION_MODAL);
                    stage2.initStyle(StageStyle.DECORATED);
                    stage2.setTitle(Lang.get("Welcome"));
                    stage2.setResizable(false);
                    stage2.show();
                    stage2.setOnCloseRequest(evt -> {
                        SessionHibernateUtil.getSessionFactory().close();
                        Console console = Console.getConsole();
                        if(console != null){
                            console.dispose();
                        }
                    });

                    stage2.getIcons().add(SystemImageUtil.getImage(SystemImageUtil.WMSICON_128));
                    WelcomeController controller = ld.<WelcomeController>getController();
                    controller.getStartButton().setOnAction(e -> {
                        try {
                            FXMLLoader loadSetup = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/Setup.fxml"));

                            Parent rootSetup = loadSetup.load();

                            stage2.getScene().setRoot(rootSetup);

                            SetupController setup = loadSetup.<SetupController>getController();
                            setup.finished.addListener((observable, oldValue, newValue) -> {
                                closeAndRestart();
                            });

                        } catch (IOException ex) {

                        }
                    });

                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        });

        loadController.getLoadingLabel().textProperty().bind(service.messageProperty());

        service.restart();
    }

    private void closeAndRestart() {
        try {

            ObservableList<Stage> stages = StageHelper.getStages();
            mainStage.close();
            while (!stages.isEmpty()) {
                stages.get(0).close();
            }

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

    private void setNimbus() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
        * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StarterFx.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StarterFx.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StarterFx.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StarterFx.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public Stage getMainStage() {
        return mainStage;
    }

}
