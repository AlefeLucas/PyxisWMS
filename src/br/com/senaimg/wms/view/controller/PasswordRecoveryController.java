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
import br.com.senaimg.wms.util.SystemImageUtil;
import br.com.senaimg.wms.util.ValidateFieldUtil;
import br.com.senaimg.wms.view.layout.Wizard;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Alefe Lucas
 */
public class PasswordRecoveryController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private BorderPane borderPane;
    @FXML
    private StackPane titlePane;
    @FXML
    private Label titleLabel;
    @FXML
    private HBox footPane;
    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonNext;
    @FXML
    private Button buttonDone;
    @FXML
    private Button buttonCancel;
    @FXML
    private Tab tab1;
    @FXML
    private Circle circleImage;
    @FXML
    private TextField tfUser;
    @FXML
    private Tab tab2;
    @FXML
    private TextArea questionTextArea;
    @FXML
    private TextField answerTextField;
    @FXML
    private Tab tab3;
    @FXML
    private PasswordField passwordPf;
    @FXML
    private PasswordField passwordConfirmPf;
    @FXML
    private Tooltip passwordToolTip;
    @FXML
    private Tab tab4;
    @FXML
    private TabPane tabPane;
    @FXML
    private StackPane passPane;
    @FXML
    private GridPane passGrid;

    private List<User> users;

    private Wizard wizard;

    private User user;
    private String newPassword;
    @FXML
    private Label questionLabel;
    @FXML
    private Label answerLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label passwordConfirmLabel;
    @FXML
    private Label succesfulLabel;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
setTheme();
        setTexts();

        loadUsers();
        setProfileImage(SystemImageUtil.getImage(SystemImageUtil.DEFAULT_USER));
        wizard = new Wizard(buttonNext, buttonBack, buttonDone, buttonCancel, tabPane, tab1, tab2, tab3, tab4);

        tfUser.textProperty().addListener((o, oldV, newV) -> typedUser(newV));

        answerTextField.textProperty().addListener((o, oldV, newV) -> typedAnswer(newV));

        passwordConfirmPf.textProperty().addListener((o, oldV, newV) -> typedPassword());
        passwordPf.textProperty().addListener((o, oldV, newV) -> typedPassword());

    }
    
     private void setTheme() {
        String path = "br/com/senaimg/wms/view/";
        String css = "passwordrecovery.css";
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

    private void setTexts() {
        tfUser.setPromptText(Lang.get(tfUser.getPromptText()));
        buttonBack.setText(Lang.get(buttonBack.getText()));
        buttonCancel.setText(Lang.get(buttonCancel.getText()));
        buttonNext.setText(Lang.get(buttonNext.getText()));
        buttonDone.setText(Lang.get(buttonDone.getText()));
        titleLabel.setText(Lang.get(titleLabel.getText()));        
        questionLabel.setText(Lang.get(questionLabel.getText()));
        answerLabel.setText(Lang.get(answerLabel.getText()));
        passwordLabel.setText(Lang.get(passwordLabel.getText()));
        passwordConfirmLabel.setText(Lang.get(passwordConfirmLabel.getText()));
        succesfulLabel.setText(Lang.get(succesfulLabel.getText()));
        tab1.setText(Lang.get(tab1.getText()));
        tab2.setText(Lang.get(tab2.getText()));
        tab3.setText(Lang.get(tab3.getText()));
        tab4.setText(Lang.get(tab4.getText()));
        
        
    }

    /**
     * Loads Users
     */
    private void loadUsers() {
        users = User.list();
//        users = UserGenerator.generateUsers();
//        UserDAO.insertUsers(users);
    }

    /**
     * Runs for each value change in tfUser; Verify if the user typed a name of
     * an existing User, if so, sets the typed user's profile pic, if not, sets
     * the default user image
     *
     * @param typedUsername
     */
    private void typedUser(String typedUsername) {
        setProfileImage(SystemImageUtil.getImage(SystemImageUtil.DEFAULT_USER));
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(typedUsername)) {

                if (user.getProfilePic() == null) {
                    setProfileImage(SystemImageUtil.getImage(SystemImageUtil.DEFAULT_USER_ON));
                } else {
                    Image img = user.getProfilePic().getImage();
                    setProfileImage(img);
                }
                this.user = user;
                wizard.stepDone();
                questionTextArea.setText(user.getSecQuestion().getQuestion());
                break;
            } else {
                this.user = null;
                questionTextArea.setText("");
                wizard.stepUndone();
            }
        }
    }

    private void typedAnswer(String newV) {
        if (newV.equalsIgnoreCase(user.getSecQuestion().getAnswer())) {
            wizard.stepDone();
        } else {
            wizard.stepUndone();
        }
    }


    private void typedPassword() {

        if (!passwordConfirmPf.getText().equals(passwordPf.getText()) && !passwordConfirmPf.getText().isEmpty()) {
            passwordToolTip.setText(Lang.get("Passwords don't match."));
            showToolTip(passwordToolTip, passwordConfirmPf);

            wizard.stepUndone();
            newPassword = null;

        } else if (!ValidateFieldUtil.isValidPassword(passwordPf.getText())) {
            passwordToolTip.setText(Lang.get("PASSWORD_NOT_VALID"));
            showToolTip(passwordToolTip, passwordConfirmPf);

            wizard.stepUndone();
            newPassword = null;

        } else {
            passwordToolTip.setText("");
            passwordToolTip.hide();

            wizard.stepDone();
            passwordToolTip.setText("");
            passwordToolTip.hide();
            newPassword = passwordConfirmPf.getText();
        }

    }

    private void showToolTip(Tooltip tooltip, TextField tf) {
        Window window = rootPane.getScene().getWindow();
        Bounds bounds = tf.localToScreen(tf.getBoundsInLocal());
        double x = bounds.getMinX();
        double y = bounds.getMaxY();
        tooltip.show(window, x, y);
    }

    /**
     * Sets the image displayed inside the circleImage
     *
     * @param image
     */
    private void setProfileImage(Image image) {
        ImagePattern pattern = new ImagePattern(image);
        circleImage.setFill(pattern);
    }

    @FXML
    private void backHandle(ActionEvent event) {
        wizard.back();
    }

    @FXML
    private void nextHandle(ActionEvent event) {
        wizard.next();
        if (wizard.isLastStep()) {
            user.setPassword(newPassword);
            user.update();
        }
    }

    @FXML
    private void doneHandle(ActionEvent event) {

        closeWindow();
    }

    private void closeWindow() {
        closed.setValue(true);
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancelHandle(ActionEvent event) {
        closeWindow();
    }

    /**
     *
     */
    public BooleanProperty closed = new SimpleBooleanProperty(false);

}
