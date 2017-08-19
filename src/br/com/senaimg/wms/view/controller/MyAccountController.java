/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.sistema.ImageFile;
import br.com.senaimg.wms.model.sistema.SecQuestion;
import br.com.senaimg.wms.model.sistema.Settings;
import br.com.senaimg.wms.model.sistema.User;
import br.com.senaimg.wms.model.sistema.enums.CssTheme;
import br.com.senaimg.wms.model.sistema.enums.Gender;
import br.com.senaimg.wms.model.sistema.enums.ImageCategory;
import br.com.senaimg.wms.model.sistema.enums.UserStatus;
import br.com.senaimg.wms.util.DateUtil;
import br.com.senaimg.wms.util.SystemImageUtil;
import br.com.senaimg.wms.util.ValidateFieldUtil;
import br.com.senaimg.wms.view.layout.AlertCustom;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author ÁlefeLucas
 */
public class MyAccountController extends TabController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private BorderPane borderPane;
    @FXML
    private StackPane titlePane;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label answerLabel;
    @FXML
    private TextField nameTf;
    @FXML
    private Tooltip nameToolTip;
    @FXML
    private TextField emailTf;
    @FXML
    private Tooltip emailToolTip;
    @FXML
    private TextField phoneTf;
    @FXML
    private Tooltip phoneToolTip;
    @FXML
    private TextField usernameTf;
    @FXML
    private Tooltip usernameToolTip;
    @FXML
    private TextField genderTf;
    @FXML
    private TextField birthdateTf;
    @FXML
    private Button changePasswordButton;
    @FXML
    private TextField answerTf;
    @FXML
    private DatePicker birthdateDp;
    @FXML
    private Tooltip birthdateToolTip;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private VBox rightPane;
    @FXML
    private Label usernameLabel;
    @FXML
    private Circle circleImage;
    @FXML
    private Button selectImageButton;
    private ImageFile imageUser;
    boolean edit = false;
    @FXML
    private TextField secQuestionTf;
    @FXML
    private TextField profileTf;
    @FXML
    private ComboBox<Gender> genderComboBox;
    @FXML
    private Tooltip answerToolTip;

    private List<User> users;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    @FXML
    private Label label6;
    @FXML
    private Label label7;
    @FXML
    private Label label8;
/*  File e = new File("C:\\Users\\Aluno\\Desktop\\err.txt");
        PrintStream old = System.err;
        try {
            System.setErr(new PrintStream(e));
        } catch (FileNotFoundException ex) {

        }*/
    private void setTexts() {
        label1.setText(Lang.get(label1.getText()));
        label2.setText(Lang.get(label2.getText()));
        label3.setText(Lang.get(label3.getText()));
        label4.setText(Lang.get(label4.getText()));
        label5.setText(Lang.get(label5.getText()));
        label6.setText(Lang.get(label6.getText()));
        label7.setText(Lang.get(label7.getText()));
        label8.setText(Lang.get(label8.getText()));
        passwordLabel.setText(Lang.get(passwordLabel.getText()));
        answerLabel.setText(Lang.get(answerLabel.getText()));
        changePasswordButton.setText(Lang.get(changePasswordButton.getText()));
        editButton.setText(Lang.get(editButton.getText()));
        deleteButton.setText(Lang.get(deleteButton.getText()));
        usernameLabel.setText(Lang.get(usernameLabel.getText()));
        selectImageButton.setText(Lang.get(selectImageButton.getText()));
    }

    private void loadUsers() {
        users = User.list();
    }

    private void setTheme() {
        String path = "br/com/senaimg/wms/view/";
        String css = "myaccount.css";
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

    //private StringProperty date = new SimpleStringProperty("dd/MM/yyyy");
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUsers();
        setTexts();
        setTheme();
        setGenders();
        setFieldsListeners();

        this.getUserSet().addListener((observable, oldValue, newValue) -> {
            getTab().setOnCloseRequest(e -> hideAllToolTips());
            User user = getLoggedUser();
            usernameLabel.setText(user.getUsername());
            usernameTf.setText(user.getUsername());
            nameTf.setText(user.getName());
            emailTf.setText(user.getEmail());
            phoneTf.setText(user.getPhoneNumber());

            genderComboBox.valueProperty().addListener((obs, oldGender, newGender) -> {

                genderTf.setText(newGender.toString());
            });

            genderComboBox.setValue(Gender.OTHER);
            genderComboBox.setValue(user.getGender());

            profileTf.setText((user.getProfile() == null ? Lang.get("No profile") : user.getProfile().getName()));

            birthdateDp.valueProperty().addListener((observableDate, oldDate, newDate) -> {
                if (newDate == null) {
                    birthdateTf.setText("");
                } else {
                    birthdateTf.setText(new SimpleDateFormat(Lang.get("dd/MM/yyyy")).format(DateUtil.toDate(newDate)));
                }
            });

            birthdateDp.setValue(DateUtil.dateToLocalDate(user.getBirthDate()));

            secQuestionTf.setText(user.getSecQuestion().getQuestion());
            answerTf.setText(user.getSecQuestion().getAnswer());

            imageUser = user.getProfilePic();

            if (user.getProfilePic() == null) {
                cleanProfileImage();
            } else {
                Image img = user.getProfilePic().getImage();
                setProfileImage(img);
            }
        });
    }

    private void setProfileImage(Image image) {
        ImagePattern pattern = new ImagePattern(image);
        circleImage.setFill(pattern);
    }

    private void cleanProfileImage() {
        imageUser = null;
        setProfileImage(SystemImageUtil.getImage(SystemImageUtil.DEFAULT_USER));
    }

    private void setGenders() {
        genderComboBox.getItems().addAll(Gender.MALE, Gender.FEMALE, Gender.OTHER);
        genderComboBox.setValue(Gender.MALE);
    }

    @FXML
    private void deleteAccount(ActionEvent event) {
        Optional<ButtonType> result = showAlertSure("Are you sure you want to delete your account?");
        if (result.get() == ButtonType.YES) {
            getLoggedUser().setStatus(UserStatus.OFF);
            getLoggedUser().update();
            getHome().relog();
        }
    }

    @FXML
    private void selectImageHandle(ActionEvent event) {
        File file = chooseFile();

        if (file != null) {
            try {
                setImageFromFile(file);

            } catch (IOException ex) {
                ex.printStackTrace();

            }

        }

    }

    private void setImageFromFile(File file) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        String fileName = file.getName();

        ImageFile imageFile = new ImageFile(fileName, bytes, ImageCategory.USER_PROF);
        imageUser = imageFile;

        Image img = imageUser.getImage();
        if (img == null) {
            cleanProfileImage();
        } else {
            setProfileImage(img);
        }
    }

    private File chooseFile() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(" All image files ", "*.png", "*.jpg", "*.jpeg", "*.jpe", "*.jfif", "*.gif", "*.tif", "*.tiff", "*.ico"),
                new FileChooser.ExtensionFilter(" PNG ", "*.png"),
                new FileChooser.ExtensionFilter(" JPEG ", "*.jpg", "*.jpeg", "*.jpe", "*.jfif"),
                new FileChooser.ExtensionFilter(" GIF ", "*.gif"),
                new FileChooser.ExtensionFilter(" TIFF ", "*.tif", "*.tiff"),
                new FileChooser.ExtensionFilter(" ICO ", "*.ico")
        );
        File file = fc.showOpenDialog(null);
        return file;
    }

    @FXML
    private void editClicked(ActionEvent event) {

        if (!edit) {
            editButton.setText(Lang.get("Save"));
            edit = !edit;
        } else {

            Date birthDate = null;
            if (birthdateDp.getValue() != null) {
                birthDate = DateUtil.toDate(birthdateDp.getValue());
            }
            Gender gender = genderComboBox.getValue();

            String name = nameTf.getText().trim();
            String email = emailTf.getText().trim().toLowerCase();
            String phone = phoneTf.getText().trim();
            String username = usernameTf.getText().trim().toLowerCase();
            String question = secQuestionTf.getText().trim();
            String answer = answerTf.getText().trim();

            nameTf.setText(name);
            emailTf.setText(email);
            phoneTf.setText(phone);
            usernameTf.setText(username);
            secQuestionTf.setText(question);
            answerTf.setText(answer);

            Boolean areAllFilled;

            areAllFilled = !(name.isEmpty()
                    || email.isEmpty()
                    || phone.isEmpty()
                    || username.isEmpty()
                    || question.isEmpty()
                    || answer.isEmpty()
                    || birthDate == null);

            boolean isValid = areAllFilled && verifyPhone() && verifyUsername() && verifyEmail() && verifyName() && verifyBirthDate() && verifyAnswer();

            if (isValid) {
                Optional<ButtonType> result = showAlertSure("Are you sure you want to save changes?");
                if (result.get() == ButtonType.YES) {

                    SecQuestion secQuestion = new SecQuestion(question, answer);

                    getLoggedUser().setName(name);
                    getLoggedUser().setUsername(username);
                    getLoggedUser().setEmail(email);
                    getLoggedUser().setProfilePic(imageUser);
                    getLoggedUser().setBirthDate(birthDate);
                    getLoggedUser().setGender(gender);
                    getLoggedUser().setPhoneNumber(phone);
                    getLoggedUser().setSecQuestion(secQuestion);

                    getLoggedUser().update();

                    editButton.setText(Lang.get("Edit..."));
                    edit = !edit;
                    usernameLabel.setText(getLoggedUser().getUsername());
                    getHome().relog();
                }
            } else {
                showAlertError();
            }
        }

        nameTf.setDisable(!edit);
        emailTf.setDisable(!edit);
        usernameTf.setDisable(!edit);
        phoneTf.setDisable(!edit);
        secQuestionTf.setDisable(!edit);

        birthdateTf.setVisible(!edit);
        genderTf.setVisible(!edit);

        answerLabel.setVisible(edit);
        answerTf.setVisible(edit);

        birthdateDp.setVisible(edit);
        genderComboBox.setVisible(edit);
        passwordLabel.setVisible(edit);
        changePasswordButton.setVisible(edit);
        deleteButton.setVisible(edit);
        selectImageButton.setVisible(edit);

    }

    private Optional<ButtonType> showAlertSure(String sureMessage) {
        Alert alertModify = new AlertCustom(Alert.AlertType.CONFIRMATION, Lang.get(sureMessage), ButtonType.YES, ButtonType.NO);
        alertModify.setTitle(Lang.get("Edit Account"));
        Optional<ButtonType> result = alertModify.showAndWait();
        return result;
    }

    private void showAlertError() {
        Alert alertInvalid = new AlertCustom(Alert.AlertType.ERROR, Lang.get("Fill the data correctly"), ButtonType.OK);
        alertInvalid.setTitle("Error");
        alertInvalid.show();
    }

    private void setFieldsListeners() {
        birthdateDp.valueProperty().addListener((o, oldV, newV) -> verifyBirthDate());
        usernameTf.textProperty().addListener((o, oldV, newV) -> verifyUsername());
        emailTf.textProperty().addListener((o, oldV, newV) -> verifyEmail());
        phoneTf.textProperty().addListener((o, oldV, newV) -> verifyPhone());
        nameTf.textProperty().addListener((o, oldV, newV) -> verifyName());
//        passwordPf.textProperty().addListener((observable, oldValue, newValue) -> verifyPassword());
        answerTf.textProperty().addListener((observable, oldValue, newValue) -> verifyAnswer());
//        passwordConfirmPf.textProperty().addListener((observable, oldValue, newValue) -> verifyPassword());
    }

    private boolean verifyBirthDate() {
        LocalDate localDate = birthdateDp.getValue();
        boolean isValid = true;
        if (localDate == null) {
            isValid = false;
        } else if (!DateUtil.isPast(localDate)) {
            isValid = false;
        }

        if (!isValid && localDate != null) {
            birthdateToolTip.setText(Lang.get("Date is invalid"));
            showToolTip(birthdateToolTip, birthdateDp);
        } else {
            birthdateToolTip.setText("");
            birthdateToolTip.hide();
        }

        return isValid;
    }

    private boolean verifyAnswer() {
        String value = "";
        String newValue = answerTf.getText();
        if (newValue.length() < 4) {
            value = (4 - newValue.length()) + Lang.get(" characters left");
            answerToolTip.setText(value);
            showToolTip(answerToolTip, answerTf);
            return false;
        } else {
            answerToolTip.setText(value);
            answerToolTip.hide();
            return true;
        }

    }

    private boolean verifyName() {

        boolean isValid = ValidateFieldUtil.isValidName(nameTf.getText().trim()) || nameTf.getText().trim().isEmpty();

        if (!isValid) {
            nameToolTip.setText(Lang.get("Name is invalid."));
            showToolTip(nameToolTip, nameTf);
        } else {
            nameToolTip.setText("");
            nameToolTip.hide();
        }

        return isValid;
    }

    private boolean verifyEmail() {

        boolean isValid = ValidateFieldUtil.isValidEmailAddress(emailTf.getText().trim()) || emailTf.getText().trim().isEmpty();

        if (!isValid) {
            emailToolTip.setText(Lang.get("Email is invalid."));
            showToolTip(emailToolTip, emailTf);
        } else {
            emailToolTip.setText("");
            emailToolTip.hide();
        }

        return isValid;
    }

    private boolean verifyPhone() {

        boolean isValid = ValidateFieldUtil.isValidPhone(phoneTf.getText().trim()) || phoneTf.getText().trim().isEmpty();

        if (!isValid) {
            phoneToolTip.setText(Lang.get("Phone number is invalid."));
            showToolTip(phoneToolTip, phoneTf);
        } else {
            phoneToolTip.setText("");
            phoneToolTip.hide();
        }

        return isValid;
    }

    private Boolean verifyUsername() {
        String newV = usernameTf.getText();
        Boolean usernameAvailable = true;
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(newV.trim()) && !user.getId().equals(getLoggedUser().getId())) {
                usernameAvailable = false;
                usernameToolTip.setText(Lang.get("Username already registered"));

                showToolTip(usernameToolTip, usernameTf);
                break;
            }

            usernameToolTip.setText("");
            usernameToolTip.hide();
        }

        boolean usernameIsValid = usernameAvailable;

        if (usernameAvailable) {
            if (!ValidateFieldUtil.isValidUsername(newV.trim()) && !newV.trim().isEmpty()) {
                usernameIsValid = false;
                usernameToolTip.setText(Lang.get("USERNAME RESTRICTION"));
                showToolTip(usernameToolTip, usernameTf);

            } else {
                usernameIsValid = true;
                usernameToolTip.setText("");
                usernameToolTip.hide();
            }
        }
        return usernameIsValid;
    }

    private void showToolTip(Tooltip tooltip, Node node) {
        Window window = rootPane.getScene().getWindow();
        Bounds bounds = node.localToScreen(node.getBoundsInLocal());
        double x = bounds.getMinX();
        double y = bounds.getMaxY();
        tooltip.show(window, x, y);
    }

    private void hideAllToolTips() {
        List<Tooltip> toolitips = new ArrayList<Tooltip>();
        toolitips.add(nameToolTip);
        toolitips.add(answerToolTip);
        toolitips.add(birthdateToolTip);
        toolitips.add(emailToolTip);
        toolitips.add(usernameToolTip);
        toolitips.add(phoneToolTip);

        for (Tooltip tip : toolitips) {
            tip.setText("");
            tip.hide();
        }
    }

    @FXML
    private void changePasswordAction(ActionEvent event) {
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/ChangePassword.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("Change Password"));
            stage.setResizable(false);
            stage.show();
            ChangePasswordController controler = fxmlLoader.<ChangePasswordController>getController();
            controler.setUser(this.getLoggedUser());

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

}
