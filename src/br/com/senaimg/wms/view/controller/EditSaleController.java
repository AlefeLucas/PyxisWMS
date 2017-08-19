/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.location.Address;
import br.com.senaimg.wms.model.location.City;
import br.com.senaimg.wms.model.location.Country;
import br.com.senaimg.wms.model.location.State;
import br.com.senaimg.wms.model.sistema.Settings;
import br.com.senaimg.wms.model.sistema.enums.CssTheme;
import br.com.senaimg.wms.model.warehouse.process.OutProcess;
import br.com.senaimg.wms.model.warehouse.process.ProcessStatus;
import br.com.senaimg.wms.model.warehouse.process.Sale;
import br.com.senaimg.wms.util.DateUtil;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class EditSaleController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private StackPane titlePane;
    @FXML
    private Label titleLabel;
    @FXML
    private HBox footPane;
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonCancel;
    @FXML
    private Label shippingLabel;
    @FXML
    private Label issueLabel;
    @FXML
    private Label paymentLabel;
    @FXML
    private Label annotationLabel;
    @FXML
    private TextArea annotationTextArea;
    @FXML
    private Tooltip annotationToolTip11;
    @FXML
    private DatePicker issueDatePicker;
    @FXML
    private DatePicker shippingDatePicker;
    @FXML
    private TextArea paymentTextArea;
    @FXML
    private Tooltip annotationToolTip111;
    @FXML
    private Label situationLabel;
    @FXML
    private Label processLabel;
    @FXML
    private ComboBox<OutProcess> processComboBox;
    @FXML
    private ComboBox<ProcessStatus> situationComboBox;
    @FXML
    private DatePicker deliveryDatePicker;
    @FXML
    private Label deliveryLabel;

    public BooleanProperty saved = new SimpleBooleanProperty(false);

    private Sale sale;
    @FXML
    private Label addressTitle;
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
    private Tooltip addressLine1ToolTip;
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
    private Tooltip addressLine2ToolTip;
    @FXML
    private TextField postalCodeTextField;
    @FXML
    private Tooltip postalCodeToolTip;
    @FXML
    private Label addressLabel;
    @FXML
    private CheckBox useCustomerAddressCheckBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTexts();
        setTheme();
        setProcessComboBox();
        setSituationComboBox();
        setLocationsComboBoxes();

        setAddressCheckBox();
    }

    private void setTheme() {
        String path = "br/com/senaimg/wms/view/";
        String css = "editsale.css";
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

    private void setAddressCheckBox() {
        useCustomerAddressCheckBox.selectedProperty().addListener((obs, oldV, newV) -> {
            if (newV) {
                cityComboBox.setDisable(true);
                stateComboBox.setDisable(true);
                countryComboBox.setDisable(true);
                addressLine1TextField.setDisable(true);
                addressLine2TextField.setDisable(true);
                postalCodeTextField.setDisable(true);
                useCustomerAddressCheckBox.setText(Lang.get("Use customer address"));

            } else {
                cityComboBox.setDisable(false);
                stateComboBox.setDisable(false);
                countryComboBox.setDisable(false);
                addressLine1TextField.setDisable(false);
                addressLine2TextField.setDisable(false);
                postalCodeTextField.setDisable(false);
                useCustomerAddressCheckBox.setText(Lang.get("Use another address"));
            }
        });
        useCustomerAddressCheckBox.setSelected(false);
        useCustomerAddressCheckBox.setSelected(true);
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

    public void setSale(Sale sale) {
        this.sale = sale;

        Date issueDate = sale.getIssueDate();
        Date shippingDate = sale.getShippingDate();
        Date deliveryDate = sale.getDeliveryDate();

        issueDatePicker.setValue(DateUtil.dateToLocalDate(issueDate));
        shippingDatePicker.setValue(DateUtil.dateToLocalDate(shippingDate));
        deliveryDatePicker.setValue(DateUtil.dateToLocalDate(deliveryDate));

        String annotations = sale.getAnnotations();
        String payment = sale.getPayment();

        annotationTextArea.setText(annotations);
        paymentTextArea.setText(payment);

        ProcessStatus processStatus = sale.getProcessStatus();
        OutProcess outProcess = sale.getOutProcess();
        Address customerAddress = sale.getCustomer().getAddress();
        Address deliver = sale.getDeliver();
        if (isCustomerAddress(sale)) {
            useCustomerAddressCheckBox.setSelected(true);
        } else {
            useCustomerAddressCheckBox.setSelected(false);
        }
        setAddress(deliver);

        processComboBox.setValue(outProcess);
        situationComboBox.setValue(processStatus);

    }

    private static boolean isCustomerAddress(Sale sale1) {
        return sale1.getCustomer().getAddress() == sale1.getDeliver();
    }

    private void setProcessComboBox() {
        ObservableList<OutProcess> items = processComboBox.getItems();
        items.clear();
        items.addAll(OutProcess.SALE, OutProcess.PICK, OutProcess.CHECK, OutProcess.DISPATCH);
        processComboBox.setValue(items.get(0));
    }

    private void setSituationComboBox() {
        ObservableList<ProcessStatus> items = situationComboBox.getItems();
        items.clear();
        items.addAll(ProcessStatus.UNBEGUN, ProcessStatus.ONGOING, ProcessStatus.DONE);
        situationComboBox.setValue(items.get(0));
    }

    @FXML
    private void doneHandle(ActionEvent event) {

        sale.setIssueDate(DateUtil.toDate(issueDatePicker.getValue()));
        sale.setShippingDate(DateUtil.toDate(shippingDatePicker.getValue()));
        sale.setDeliveryDate(DateUtil.toDate(deliveryDatePicker.getValue()));
        sale.setAnnotations(annotationTextArea.getText());
        sale.setPayment(paymentTextArea.getText());

        if (!isCustomerAddress(sale)) {
            String addressLine1 = addressLine1TextField.getText();
            String addressLine2 = addressLine2TextField.getText();
            City city = cityComboBox.getValue();

            String strPostal = postalCodeTextField.getText();
            int postal = Integer.parseInt(strPostal);

            sale.getDeliver().setFields(addressLine1, addressLine2, postal, city);
            sale.getDeliver().update();
        } else if (!useCustomerAddressCheckBox.isSelected()) {
            String addressLine1 = addressLine1TextField.getText();
            String addressLine2 = addressLine2TextField.getText();
            City city = cityComboBox.getValue();

            String strPostal = postalCodeTextField.getText();
            int postal = Integer.parseInt(strPostal);

            Address ad = new Address(addressLine1, addressLine2, postal, city);
            ad.insert();
            sale.setDeliver(ad);
        }

        if (useCustomerAddressCheckBox.isSelected()) {
            sale.setDeliver(sale.getCustomer().getAddress());
        }

        sale.setOutProcess(processComboBox.getValue());
        sale.setProcessStatus(situationComboBox.getValue());
        sale.update();

        saved.setValue(!saved.getValue());
        closeWindow();

    }

    @FXML
    private void cancelHandle(ActionEvent event) {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    private void setAddress(Address deliver) {
        if (deliver != null) {
            addressLine1TextField.setText(deliver.getAddressLine1());
            addressLine2TextField.setText(deliver.getAddressLine2());
            postalCodeTextField.setText(deliver.getPostalCode() + "");
            if (deliver.getCity() != null) {
                cityComboBox.setValue(deliver.getCity());
            }
        }
    }

    private void setTexts() {
        titleLabel.setText(Lang.get(titleLabel.getText()));
        issueLabel.setText(Lang.get(issueLabel.getText()));
        shippingLabel.setText(Lang.get(shippingLabel.getText()));
        deliveryLabel.setText(Lang.get(deliveryLabel.getText()));
        paymentLabel.setText(Lang.get(paymentLabel.getText()));
        annotationLabel.setText(Lang.get(annotationLabel.getText()));
        addressTitle.setText(Lang.get(addressTitle.getText()));
        addressLabel.setText(Lang.get(addressLabel.getText()));
        useCustomerAddressCheckBox.setText(Lang.get(useCustomerAddressCheckBox.getText()));
        addressLine1Label.setText(Lang.get(addressLine1Label.getText()));
        addressLine2Label.setText(Lang.get(addressLine2Label.getText()));
        postalCodeLabel.setText(Lang.get(postalCodeLabel.getText()));
        countryLabel.setText(Lang.get(countryLabel.getText()));
        stateLabel.setText(Lang.get(stateLabel.getText()));
        cityLabel.setText(Lang.get(cityLabel.getText()));
        processLabel.setText(Lang.get(processLabel.getText()));
        situationLabel.setText(Lang.get(situationLabel.getText()));
        buttonSave.setText(Lang.get(buttonSave.getText()));
        buttonCancel.setText(Lang.get(buttonCancel.getText()));

    }

}
