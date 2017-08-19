/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.contact.Contact;
import br.com.senaimg.wms.model.location.Address;
import br.com.senaimg.wms.model.location.City;
import br.com.senaimg.wms.model.location.Country;
import br.com.senaimg.wms.model.location.State;
import br.com.senaimg.wms.model.sistema.ImageFile;
import br.com.senaimg.wms.model.sistema.Settings;
import br.com.senaimg.wms.model.sistema.enums.CssTheme;
import br.com.senaimg.wms.model.sistema.enums.ImageCategory;
import br.com.senaimg.wms.model.warehouse.agent.Supplier;
import br.com.senaimg.wms.util.SystemImageUtil;
import br.com.senaimg.wms.util.ValidateFieldUtil;
import br.com.senaimg.wms.view.layout.AlertCustom;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class EditSupplierController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private StackPane titlePane;
    @FXML
    private Label titleLabel;
    @FXML
    private VBox centerPane;
    @FXML
    private GridPane formGridPaneSupplier;
    @FXML
    private Label nameLabel;
    @FXML
    private Label mnemonicLabel;
    @FXML
    private Label companyCodeLabel;
    @FXML
    private Label taxLabel;
    @FXML
    private Label taxTypeLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private Tooltip nameToolTip;
    @FXML
    private TextField mnemonicTextField;
    @FXML
    private Tooltip emailToolTip;
    @FXML
    private TextField companyCodeTextField;
    @FXML
    private Tooltip phoneToolTip;
    @FXML
    private TextField taxTextField;
    @FXML
    private TextField taxTypeTextField;
    @FXML
    private Label addressTitle;
    @FXML
    private GridPane formGridPaneAddress;
    @FXML
    private Label addressLine1Label;
    @FXML
    private Label addressLine2Label;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label countryLabel;
    @FXML
    private TextField addressLine1TextField;
    @FXML
    private Label cityLabel;
    @FXML
    private ComboBox<Country> countryComboBox;
    @FXML
    private Label stateLabel;
    @FXML
    private ComboBox<State> stateComboBox;
    @FXML
    private ComboBox<City> cityComboBox;
    @FXML
    private TextField addressLine2TextField;
    @FXML
    private TextField postalCodeTextField;
    @FXML
    private Label contactTitle;
    @FXML
    private GridPane formGridPaneContact;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label phone2Label;
    @FXML
    private Label faxLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField phone2TextField;
    @FXML
    private TextField faxTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private VBox rightPane;
    @FXML
    private Label imageLabel;
    @FXML
    private Rectangle squareImage;
    @FXML
    private Button selectImageButton;
    @FXML
    private HBox footPane;
    @FXML
    private Button buttonClean;
    @FXML
    private Button buttonSaveSupplier;
    @FXML
    private Tooltip mnemonicToolTip;
    @FXML
    private Tooltip companyCodeToolTip;
    @FXML
    private Tooltip taxToolTip;
    @FXML
    private Tooltip taxTypeToolTip;
    @FXML
    private Tooltip addressLine1ToolTip;
    @FXML
    private Tooltip addressLine2ToolTip;
    @FXML
    private Tooltip postalCodeToolTip;
    @FXML
    private Tooltip phone2ToolTip;
    @FXML
    private Tooltip faxToolTip;

    private ImageFile image;

    /**
     *
     */
    public BooleanProperty inserted = new SimpleBooleanProperty(false);
    private List<Supplier> suppliers;
    private Supplier supplierEdit;
    @FXML
    private Tooltip taxTipToolTip;
    @FXML
    private Label statusLabel;
    @FXML
    private ComboBox<String> statusComboBox;
    @FXML
    private TextArea annotationTextArea;
    @FXML
    private Label annotationLabel;
    @FXML
    private Tooltip annotationToolTip;
    @FXML
    private Label webPageLabel;
    @FXML
    private Tooltip webPageToolTip;
    @FXML
    private TextField webPageTextField;

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
        setStatusComboBox();
        cleanImage();
        loadSuppliers();

        setLocationsComboBoxes();

        setFieldListeners();
    }

       private void setTheme() {
        String path = "br/com/senaimg/wms/view/";
        String css = "insertsupplier.css";
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
    
    private void setStatusComboBox() {
        ObservableList<String> items = statusComboBox.getItems();
        items.clear();

        items.addAll(Lang.get("On"), Lang.get("Off"));

    }

    private void setFieldListeners() {
        nameTextField.textProperty().addListener((o, oldV, newV) -> verifyName());
        mnemonicTextField.textProperty().addListener((o, oldV, newV) -> verifyMnemonic());
        companyCodeTextField.textProperty().addListener((o, oldV, newV) -> verifyCompanyCode());
        taxTextField.textProperty().addListener((o, oldV, newV) -> verifyTax());
        taxTypeTextField.textProperty().addListener((o, oldV, newV) -> verifyTaxType());
        annotationTextArea.textProperty().addListener((o, oldV, newV) -> verifyAnnotation());

        addressLine1TextField.textProperty().addListener((o, oldV, newV) -> verifyAddress1());
        addressLine2TextField.textProperty().addListener((o, oldV, newV) -> verifyAddress2());
        postalCodeTextField.textProperty().addListener((o, oldV, newV) -> verifyPostalCode());

        phoneTextField.textProperty().addListener((o, oldV, newV) -> verifyPhone());
        phone2TextField.textProperty().addListener((o, oldV, newV) -> verifyPhone2());
        faxTextField.textProperty().addListener((o, oldV, newV) -> verifyFax());
        emailTextField.textProperty().addListener((o, oldV, newV) -> verifyEmail());
        webPageTextField.textProperty().addListener((o, oldV, newV) -> verifyUrl());
    }

    private void setLocationsComboBoxes() {

        setStateListener();

        setCountryListener();

        loadCountries();
    }

    private void setStateListener() {
        stateComboBox.valueProperty().addListener((ObservableValue<? extends State> observable, State oldValue, State state) -> {
            if (state == null || state.getCities() == null || state.getCities().isEmpty()) {

                cityComboBox.setDisable(true);
            } else {
                cityComboBox.setDisable(false);
                List<City> cities = state.getCities();

                ObservableList<City> items = cityComboBox.getItems();
                items.clear();

                items.addAll(cities);

                if (!items.isEmpty()) {
                    cityComboBox.setDisable(false);
                    cityComboBox.setValue(items.get(0));
                } else {
                    cityComboBox.setDisable(true);
                }

            }

        });
    }

    private void setCountryListener() {
        countryComboBox.valueProperty().addListener((ObservableValue<? extends Country> observable, Country oldValue, Country country) -> {
            if (country == null || country.getStates() == null || country.getStates().isEmpty()) {
                stateComboBox.setDisable(true);
                cityComboBox.setDisable(true);
            } else {
                stateComboBox.setDisable(false);
                List<State> states = country.getStates();

                ObservableList<State> items = stateComboBox.getItems();
                items.clear();

                items.addAll(states);

                if (!items.isEmpty()) {
                    stateComboBox.setDisable(false);
                    stateComboBox.setValue(items.get(0));
                } else {
                    stateComboBox.setDisable(true);
                }

            }

        });
    }

    private void loadCountries() {
        List<Country> countries = Country.list();

        ObservableList<Country> items = countryComboBox.getItems();
        items.clear();

        items.addAll(countries);
        if (!items.isEmpty()) {
            countryComboBox.setDisable(false);
            countryComboBox.setValue(items.get(0));
        } else {
            countryComboBox.setDisable(true);
        }
    }

    private void setTexts() {
        titleLabel.setText(Lang.get(titleLabel.getText()));
        buttonClean.setText(Lang.get(buttonClean.getText()));
        buttonSaveSupplier.setText(Lang.get(buttonSaveSupplier.getText()));

        addressTitle.setText(Lang.get(addressTitle.getText()));
        contactTitle.setText(Lang.get(contactTitle.getText()));

        nameLabel.setText(Lang.get(nameLabel.getText()) + "*");
        mnemonicLabel.setText(Lang.get(mnemonicLabel.getText()) + "*");
        companyCodeLabel.setText(Lang.get(companyCodeLabel.getText()) + "*");
        taxLabel.setText(Lang.get(taxLabel.getText()) + "*");
        taxTypeLabel.setText(Lang.get(taxTypeLabel.getText()) + "*");
        annotationLabel.setText(Lang.get(annotationLabel.getText()) + "*");

        addressLine1Label.setText(Lang.get(addressLine1Label.getText()) + "*");
        addressLine2Label.setText(Lang.get(addressLine2Label.getText()));
        postalCodeLabel.setText(Lang.get(postalCodeLabel.getText()) + "*");
        countryLabel.setText(Lang.get(countryLabel.getText()));
        stateLabel.setText(Lang.get(stateLabel.getText()));
        cityLabel.setText(Lang.get(cityLabel.getText()) + "*");
        selectImageButton.setText(Lang.get(selectImageButton.getText()));
        imageLabel.setText(Lang.get(imageLabel.getText()));

        phoneLabel.setText(Lang.get(phoneLabel.getText()) + "*");
        phone2Label.setText(Lang.get(phone2Label.getText()));
        faxLabel.setText(Lang.get(faxLabel.getText()));
        emailLabel.setText(Lang.get(emailLabel.getText()) + "*");
        webPageLabel.setText(Lang.get(webPageLabel.getText()));

        taxTipToolTip.setText(Lang.get(taxTipToolTip.getText()));

    }

    private void cleanImage() {
        image = null;
        setImage(SystemImageUtil.getImage(SystemImageUtil.DEFAULT_USER));
    }

    private void loadSuppliers() {
        suppliers = Supplier.list();
    }

    private void setImage(Image image) {
        ImagePattern pattern = new ImagePattern(image);
        squareImage.setFill(pattern);
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

    @FXML
    private void cleanHandle(ActionEvent event) {

        nameTextField.setText("");
        companyCodeTextField.setText("");
        mnemonicTextField.setText("");
        taxTextField.setText("");
        annotationTextArea.setText("");
        taxTypeTextField.setText("");
        addressLine1TextField.setText("");
        addressLine2TextField.setText("");
        postalCodeTextField.setText("");
        phoneTextField.setText("");
        phone2TextField.setText("");
        faxTextField.setText("");
        emailTextField.setText("");

        nameToolTip.hide();
        mnemonicToolTip.hide();
        companyCodeToolTip.hide();
        taxToolTip.hide();
        taxTypeToolTip.hide();

        addressLine1ToolTip.hide();
        addressLine2ToolTip.hide();
        postalCodeToolTip.hide();

        phone2ToolTip.hide();
        phoneToolTip.hide();
        faxToolTip.hide();
        emailToolTip.hide();

        cleanImage();
    }

    @FXML
    private void saveSupplierHandle(ActionEvent event) {
        try {
            String name = nameTextField.getText();
            String mnemonic = mnemonicTextField.getText();
            String companyCode = companyCodeTextField.getText();
            String tax = taxTextField.getText();
            String taxType = taxTypeTextField.getText();
            String annotation = annotationTextArea.getText();
            String address1 = addressLine1TextField.getText();
            String address2 = addressLine2TextField.getText();
            int postal = Integer.parseInt(postalCodeTextField.getText());
            String phone = phoneTextField.getText();
            String phone2 = phone2TextField.getText();
            String fax = faxTextField.getText();
            String email = emailTextField.getText();
            String webPage = webPageTextField.getText();

            City city = cityComboBox.getValue();

            if (city == null) {
                throw new IllegalStateException();
            }

            boolean areAllFilled = !(name.isEmpty()
                    || mnemonic.isEmpty()
                    || companyCode.isEmpty()
                    || tax.isEmpty()
                    || taxType.isEmpty()
                    || address1.isEmpty()
                    || email.isEmpty()
                    || phone.isEmpty());

            if (!areAllFilled) {
                throw new IllegalStateException();
            }

            boolean isValid
                    = verifyAddress1()
                    && verifyAddress2()
                    && verifyAnnotation()
                    && verifyCompanyCode()
                    && verifyEmail()
                    && verifyFax()
                    && verifyMnemonic()
                    && verifyName()
                    && verifyPhone()
                    && verifyPhone2()
                    && verifyPostalCode()
                    && verifyTax()
                    && verifyTaxType()
                    && verifyUrl()
                    && areAllFilled;

            if (!isValid) {
                throw new IllegalStateException();
            }

            if (isValid) {

                Optional<ButtonType> result = showAlertSure();
                if (result.get() == ButtonType.YES) {

                    Address address = supplierEdit.getAddress();
                    Contact contact = supplierEdit.getContact();

                    address.setFields(address1, address2, postal, city);
                    contact.setFields(phone, phone2, fax, email, webPage);

                    boolean disabled;

                    String status = statusComboBox.getValue();
                    disabled = !(status.equals(Lang.get("On")));

                    supplierEdit.setFields(disabled, companyCode, mnemonic, name, tax, taxType, annotation, image, address, contact);

                    supplierEdit.update();
                    loadSuppliers();
                    inserted.setValue(true);
                    inserted.setValue(false);
                    cleanHandle(event);
                    closeWindow();
                }

            }

        } catch (IllegalStateException | NumberFormatException ex) {
            showAlertError();
        }

    }

    private void closeWindow() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    private Optional<ButtonType> showAlertSure() {
        Alert alertModify = new AlertCustom(Alert.AlertType.CONFIRMATION, Lang.get("Are you sure you want to save changes?"), ButtonType.YES, ButtonType.NO);
        alertModify.setTitle(Lang.get("Insert User"));
        Optional<ButtonType> result = alertModify.showAndWait();
        return result;
    }

    private void showAlertError() {
        Alert alertInvalid = new AlertCustom(Alert.AlertType.ERROR, Lang.get("Fill the data correctly"), ButtonType.OK);
        alertInvalid.setTitle(Lang.get("Error"));
        alertInvalid.show();
    }

    private void showToolTip(Tooltip tooltip, Node node) {
        Window window = rootPane.getScene().getWindow();
        Bounds bounds = node.localToScreen(node.getBoundsInLocal());
        double x = bounds.getMinX();
        double y = bounds.getMaxY();
        tooltip.show(window, x, y);
    }

    private boolean verifyName() {
        boolean isValid = ValidateFieldUtil.isValidName(nameTextField.getText().trim()) || nameTextField.getText().trim().isEmpty();

        if (!isValid) {
            nameToolTip.setText(Lang.get("Name is invalid."));
            showToolTip(nameToolTip, nameTextField);
        } else {
            nameToolTip.setText("");
            nameToolTip.hide();
        }

        return isValid;
    }

    private boolean verifyMnemonic() {
        boolean isValid = ValidateFieldUtil.isValidMnemonic(mnemonicTextField.getText().trim()) || mnemonicTextField.getText().trim().isEmpty();

        if (!isValid) {
            mnemonicToolTip.setText(Lang.get("Mnemonic is invalid. It should not countain numbers neither exceed 20 characters."));
            showToolTip(mnemonicToolTip, mnemonicTextField);
        } else {
            mnemonicToolTip.setText("");
            mnemonicToolTip.hide();
        }

        return isValid;
    }

    private boolean verifyCompanyCode() {
        boolean isValid = ValidateFieldUtil.isValidCompanyCode(companyCodeTextField.getText().trim()) || companyCodeTextField.getText().trim().isEmpty();

        if (!isValid) {
            companyCodeToolTip.setText(Lang.get("Company code is invalid. It should not exceed 10 characters neither contain spaces ( )."));
            showToolTip(companyCodeToolTip, companyCodeTextField);
        } else {
            companyCodeToolTip.setText("");
            companyCodeToolTip.hide();
        }

        return isValid;
    }

    private boolean verifyTax() {
        taxTextField.setText(taxTextField.getText().toUpperCase());

        boolean isValid = ValidateFieldUtil.isValidTax(taxTextField.getText().trim()) || taxTextField.getText().trim().isEmpty();

        if (!isValid) {
            taxToolTip.setText(Lang.get("Tax is invalid. It should not exceed 40 characters and should contain only alphanumeric characters, \".\" and \"-\""));
            showToolTip(taxToolTip, taxTextField);
        } else {
            taxToolTip.setText("");
            taxToolTip.hide();
        }

        return isValid;
    }

    private boolean verifyTaxType() {
        taxTypeTextField.setText(taxTypeTextField.getText().toUpperCase());

        boolean isValid = ValidateFieldUtil.isValidTaxType(taxTypeTextField.getText().trim()) || taxTypeTextField.getText().trim().isEmpty();

        if (!isValid) {
            taxTypeToolTip.setText(Lang.get("Tax type is invalid. It should not exceed 20 characters and should contain only letters, \".\" and \"-\""));
            showToolTip(taxTypeToolTip, taxTypeTextField);
        } else {
            taxTypeToolTip.setText("");
            taxTypeToolTip.hide();
        }

        return isValid;
    }

    private boolean verifyAnnotation() {
        boolean isValid = !ValidateFieldUtil.biggerThan(annotationTextArea.getText().trim(), 1023) || annotationTextArea.getText().trim().isEmpty();

        if (!isValid) {
            annotationToolTip.setText(Lang.get("Annotation is invalid. It should not exceed 1023 characters."));
            showToolTip(annotationToolTip, annotationTextArea);
        } else {
            annotationToolTip.setText("");
            annotationToolTip.hide();
        }

        return isValid;
    }

    private boolean verifyAddress1() {
        boolean isValid = !ValidateFieldUtil.biggerThan(addressLine1TextField.getText().trim(), 511) || addressLine1TextField.getText().trim().isEmpty();

        if (!isValid) {
            addressLine1ToolTip.setText(Lang.get("Address is invalid. It should not exceed 511 characters."));
            showToolTip(addressLine1ToolTip, addressLine1TextField);
        } else {
            addressLine1ToolTip.setText("");
            addressLine1ToolTip.hide();
        }

        return isValid;
    }

    private boolean verifyAddress2() {
        boolean isValid = !ValidateFieldUtil.biggerThan(addressLine2TextField.getText().trim(), 511) || addressLine2TextField.getText().trim().isEmpty();

        if (!isValid) {
            addressLine2ToolTip.setText(Lang.get("Address is invalid. It should not exceed 511 characters."));
            showToolTip(addressLine2ToolTip, addressLine2TextField);
        } else {
            addressLine2ToolTip.setText("");
            addressLine2ToolTip.hide();
        }

        return isValid;
    }

    private boolean verifyPostalCode() {
        boolean isValid = ValidateFieldUtil.isValidPostalCode(postalCodeTextField.getText().trim()) || postalCodeTextField.getText().trim().isEmpty();

        if (!isValid) {
            postalCodeToolTip.setText(Lang.get("Postal code is invalid. It should contain only numbers."));
            showToolTip(postalCodeToolTip, postalCodeTextField);
        } else {
            postalCodeToolTip.setText("");
            postalCodeToolTip.hide();
        }

        return isValid;
    }

    private boolean verifyPhone() {
        boolean isValid = ValidateFieldUtil.isValidPhone(phoneTextField.getText().trim()) || phoneTextField.getText().trim().isEmpty();

        if (!isValid) {
            phoneToolTip.setText(Lang.get("Phone number is invalid. It should contain only numbers."));
            showToolTip(phoneToolTip, phoneTextField);
        } else {
            phoneToolTip.setText("");
            phoneToolTip.hide();
        }

        return isValid;
    }

    private boolean verifyPhone2() {
        boolean isValid = ValidateFieldUtil.isValidPhone(phone2TextField.getText().trim()) || phone2TextField.getText().trim().isEmpty();

        if (!isValid) {
            phone2ToolTip.setText(Lang.get("Phone number is invalid. It should contain only numbers."));
            showToolTip(phone2ToolTip, phone2TextField);
        } else {
            phone2ToolTip.setText("");
            phone2ToolTip.hide();
        }

        return isValid;
    }

    private boolean verifyFax() {
        boolean isValid = ValidateFieldUtil.isValidPhone(faxTextField.getText().trim()) || faxTextField.getText().trim().isEmpty();

        if (!isValid) {
            faxToolTip.setText(Lang.get("Fax number is invalid. It should contain only numbers."));
            showToolTip(faxToolTip, faxTextField);
        } else {
            faxToolTip.setText("");
            faxToolTip.hide();
        }

        return isValid;
    }

    private boolean verifyEmail() {
        boolean isValid = ValidateFieldUtil.isValidEmailAddress(emailTextField.getText().trim()) || emailTextField.getText().trim().isEmpty();

        if (!isValid) {
            emailToolTip.setText(Lang.get("Email is invalid."));
            showToolTip(emailToolTip, emailTextField);
        } else {
            emailToolTip.setText("");
            emailToolTip.hide();
        }

        return isValid;
    }

    private boolean verifyUrl() {
        boolean isValid = !ValidateFieldUtil.biggerThan(webPageTextField.getText().trim(), 255) || webPageTextField.getText().trim().isEmpty();

        if (!isValid) {
            webPageToolTip.setText(Lang.get("Web Address is too big."));
            showToolTip(webPageToolTip, webPageTextField);
        } else {
            webPageToolTip.setText("");
            webPageToolTip.hide();
        }

        return isValid;
    }

    private void setImageFromFile(File file) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        String fileName = file.getName();

        ImageFile imageFile = new ImageFile(fileName, bytes, ImageCategory.USER_PROF);
        image = imageFile;

        Image img = image.getImage();
        if (img == null) {
            cleanImage();
        } else {
            setImage(img);
        }
    }

    private File chooseFile() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(Lang.get(" All image files "), "*.png", "*.jpg", "*.jpeg", "*.jpe", "*.jfif", "*.gif", "*.tif", "*.tiff", "*.ico"),
                new FileChooser.ExtensionFilter(" PNG ", "*.png"),
                new FileChooser.ExtensionFilter(" JPEG ", "*.jpg", "*.jpeg", "*.jpe", "*.jfif"),
                new FileChooser.ExtensionFilter(" GIF ", "*.gif"),
                new FileChooser.ExtensionFilter(" TIFF ", "*.tif", "*.tiff"),
                new FileChooser.ExtensionFilter(" ICO ", "*.ico")
        );
        File file = fc.showOpenDialog(null);
        return file;
    }

    public void setSupplier(Supplier supplier) {
        supplierEdit = supplier;

        Address address = supplierEdit.getAddress();
        Contact contact = supplierEdit.getContact();

        nameTextField.setText(supplierEdit.getName());
        mnemonicTextField.setText(supplierEdit.getMnemonic());
        companyCodeTextField.setText(supplierEdit.getCompanyCode());
        taxTextField.setText(supplierEdit.getTaxNumber());
        taxTypeTextField.setText(supplierEdit.getTaxNumberType());
        annotationTextArea.setText(supplierEdit.getAnnotation());

        addressLine1TextField.setText(address.getAddressLine1());
        addressLine2TextField.setText(address.getAddressLine2());
        postalCodeTextField.setText(address.getPostalCode() + "");

        if (address.getCity() != null) {
            countryComboBox.setValue(address.getCity().getState().getCountry());
            stateComboBox.setValue(address.getCity().getState());
            cityComboBox.setValue(address.getCity());
        }
        statusComboBox.setValue((supplier.isDisabled() ? Lang.get("Off") : Lang.get("On")));

        phoneTextField.setText(contact.getPhone());
        phone2TextField.setText(contact.getPhoneAlt());
        faxTextField.setText(contact.getFax());
        emailTextField.setText(contact.getEmail());
        webPageTextField.setText(contact.getWebPage());

        if (supplierEdit.getImage() == null) {
            cleanImage();
        } else {
            Image img = supplierEdit.getImage().getImage();
            setImage(img);
        }

        image = supplierEdit.getImage();

    }
}
