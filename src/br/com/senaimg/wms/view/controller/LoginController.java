package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.util.Encryption;
import br.com.senaimg.wms.util.LoginBackgroundTransition;
import br.com.senaimg.wms.util.SystemImageUtil;
import br.com.senaimg.wms.model.sistema.User;
import br.com.senaimg.wms.model.sistema.ImageFile;
import br.com.senaimg.wms.model.sistema.Settings;
import br.com.senaimg.wms.model.sistema.enums.CssTheme;
import br.com.senaimg.wms.model.sistema.enums.UserStatus;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Alefe Lucas
 */
public class LoginController implements Initializable {

    @FXML
    Circle circleImage;

    @FXML
    TextField tfUser;

    @FXML
    PasswordField pfPassword;

    @FXML
    StackPane backgroundPane1;

    @FXML
    StackPane backgroundPane2;

    @FXML
    StackPane contentPane;

    @FXML
    StackPane rootPane;

    @FXML
    Hyperlink hpAccessReq;

    @FXML
    Hyperlink hpPasswordRecovery;

    @FXML
    Button loginButton;

    @FXML
    Button okButton;

    @FXML
    Text textWarning;

    @FXML
    VBox loginPane;

    @FXML
    HBox linkPane;

    private List<User> users;
    private User loggedUser;
    private LoginBackgroundTransition bgTrans;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setTexts();

        setEnterListener();

        loginPane.getChildren().removeAll(okButton, textWarning);

        bgTrans = new LoginBackgroundTransition(backgroundPane1, backgroundPane2);
        

        setProfileImage(SystemImageUtil.getImage(SystemImageUtil.DEFAULT_USER));

        loadUsers();

        setUserInputListener();
        System.out.println(Lang.get("LOGIN PAGE LOADED"));
    }

    private void setTheme() {
        String path = "br/com/senaimg/wms/view/";
        String css = "login.css";
        String theme;

        Settings s = Settings.getLast();
        if (s != null) {
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

    private void setTexts() {
        tfUser.setPromptText(Lang.get(tfUser.getPromptText()));
        pfPassword.setPromptText(Lang.get(pfPassword.getPromptText()));
        loginButton.setText(Lang.get(loginButton.getText()));
        okButton.setText(Lang.get(okButton.getText()));
        hpPasswordRecovery.setText(Lang.get(hpPasswordRecovery.getText()));
        hpAccessReq.setText(Lang.get(hpAccessReq.getText()));
    }

    /**
     *
     */
    public void startTransition() {
        bgTrans.start();
    }

    /**
     * Stops the running transition.
     *
     */
    public void stopTransition() {
        bgTrans.stop();
    }

    /**
     * Sets the functionality of listening to value changes on tfUser
     */
    private void setUserInputListener() {
        tfUser.textProperty().addListener((observable, oldValue, newValue) -> {
            typedUser(newValue);
        });
    }

    /**
     * Loads Users
     */
    public void loadUsers() {
        setTheme();
        users = User.list();

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
    private void okButtonHandle() {
        loginPane.getChildren().addAll(circleImage, tfUser, pfPassword, loginButton, linkPane);
        loginPane.getChildren().removeAll(textWarning, okButton);
    }

    @FXML
    private void callAccessRequest() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/AccessRequest.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("Access Request"));
            stage.setResizable(false);
            stage.show();

            AccessRequestController controller = fxmlLoader.<AccessRequestController>getController();
            controller.inserted.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (newValue) {
                    loadUsers();
                }
            });

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    @FXML
    private void loginButtonHandle() {
        login();

    }

    private void login() {
        String userStr = tfUser.getText();
        String passwordStr = pfPassword.getText();

        boolean isLogged = false;
        boolean isAllowed = false;

        for (User user : users) {
            textWarning.setText(Lang.get("Username or password is incorrect. Try Again."));
            if (user.getUsername().equalsIgnoreCase(userStr) && Arrays.equals(user.getPassword(), Encryption.encrypt(passwordStr))) {

                isAllowed = !(user.getProfile() == null || user.getStatus() == UserStatus.REQUESTED || user.getStatus() == UserStatus.OFF);
                if (isAllowed) {
                    isLogged = logIn(user);

                } else {
                    textWarning.setText(Lang.get("Your account ain't activated. Contact an administrator."));
                }

                break;
            }

        }

        if (!isLogged) {
            loginPane.getChildren().removeAll(circleImage, tfUser, pfPassword, loginButton, linkPane);
            loginPane.getChildren().addAll(textWarning, okButton);
        }
    }

    /**
     * Logs In
     *
     * @param user
     * @return
     */
    private boolean logIn(User user) {
        System.out.println("\n\n" + Lang.get("LOGGED IN WITH USER ") + user.getUsername() + "\n\n");
        setLoggedUser(user);

        loggedIn.setValue(!loggedIn.getValue());
        tfUser.setText("");
        pfPassword.setText("");

        return true;
    }

    /**
     *
     */
    public BooleanProperty loggedIn = new SimpleBooleanProperty(false);

    /**
     * Runs for each value change in tfUser; Verify if the user typed a name of
     * an existing User, if so, sets the typed user's profile pic, if not, sets
     * the default user image
     *
     * @param typedUsername
     */
    private void typedUser(String typedUsername) {
        setProfileImage(SystemImageUtil.getImage(SystemImageUtil.DEFAULT_USER));

        users.forEach(t -> {
            if (t.getUsername().equalsIgnoreCase(typedUsername)) {
                ImageFile img = t.getProfilePic();
                if (img == null) {
                    setProfileImage(SystemImageUtil.getImage(SystemImageUtil.DEFAULT_USER_ON));
                } else {
                    setProfileImage(img.getImage());
                }
            }
        });
    }

    @FXML
    private void callPasswordRecovery(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/PasswordRecovery.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("Password Recovery"));
            stage.setResizable(false);
            stage.show();

            PasswordRecoveryController controller = fxmlLoader.<PasswordRecoveryController>getController();
            controller.closed.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (newValue) {
                    loadUsers();
                }
            });

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Gets the logged user;
     *
     * @return User
     */
    public User getLoggedUser() {
        return loggedUser;
    }

    private void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    private void setEnterListener() {
        pfPassword.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                login();
            }
        });

        tfUser.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                login();
            }
        });
        okButton.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                okButtonHandle();
            }
        });
    }

}
