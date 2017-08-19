/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.sistema.Settings;
import br.com.senaimg.wms.model.sistema.enums.CssTheme;
import br.com.senaimg.wms.model.warehouse.process.InProcess;
import br.com.senaimg.wms.model.warehouse.process.ProcessStatus;
import br.com.senaimg.wms.model.warehouse.process.Purchase;
import br.com.senaimg.wms.util.DateUtil;
import br.com.senaimg.wms.util.ValidateFieldUtil;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class EditPurchaseController implements Initializable {

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
    private GridPane formGridPaneSupplier11;
    @FXML
    private Label expLabel;
    @FXML
    private Label orderLabel;
    @FXML
    private Label paymentLabel;
    @FXML
    private Label annotationLabel;
    @FXML
    private TextArea annotationTextArea;
    @FXML
    private DatePicker orderDatePicker;
    @FXML
    private DatePicker expDatePicker;
    @FXML
    private TextArea paymentTextArea;
    @FXML
    private Label situationLabel;
    @FXML
    private Label processLabel;
    @FXML
    private ComboBox<InProcess> processComboBox;
    @FXML
    private ComboBox<ProcessStatus> situationComboBox;
    private Purchase purchase;
    @FXML
    private DatePicker deliveryDatePicker;
    @FXML
    private Label deliveryLabel;

    public BooleanProperty saved = new SimpleBooleanProperty(false);
    @FXML
    private Tooltip annotationToolTip;
    @FXML
    private Tooltip paymentToolTip;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTexts();
        setTheme();
        setProcessComboBox();
        setSituationComboBox();
        setPreferencesListeners();
    }

    private void setTheme() {
        String path = "br/com/senaimg/wms/view/";
        String css = "editpurchase.css";
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

    @FXML
    private void doneHandle(ActionEvent event) {
        Date order = DateUtil.toDate(orderDatePicker.getValue());
        Date exp = DateUtil.toDate(expDatePicker.getValue());
        Date del = null;
        if (deliveryDatePicker.getValue() != null) {
            del = DateUtil.toDate(deliveryDatePicker.getValue());
        }

        String annotation = annotationTextArea.getText();
        String payment = paymentTextArea.getText();

        ProcessStatus process = situationComboBox.getValue();
        InProcess in = processComboBox.getValue();

        this.purchase.setFields(order, exp, payment, annotation, del, process, in);
        this.purchase.update();
        this.saved.setValue(!this.saved.getValue());
        closeWindow();
    }

    @FXML
    private void cancelHandle(ActionEvent event) {
    }

    private void setProcessComboBox() {
        ObservableList<InProcess> items = processComboBox.getItems();
        items.clear();
        items.addAll(InProcess.PURCHASE, InProcess.RECEIPT, InProcess.CHECK, InProcess.STORE);
        processComboBox.setValue(items.get(0));
    }

    private void setSituationComboBox() {
        ObservableList<ProcessStatus> items = situationComboBox.getItems();
        items.clear();
        items.addAll(ProcessStatus.UNBEGUN, ProcessStatus.ONGOING, ProcessStatus.DONE);
        situationComboBox.setValue(items.get(0));
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
        Date orderDate = purchase.getOrderDate();
        Date expDelivery = purchase.getExpDelivery();
        Date delivery = purchase.getDelivery();

        String paymentConditions = purchase.getPaymentConditions();
        String annotations = purchase.getAnnotations();

        orderDatePicker.setValue(DateUtil.dateToLocalDate(orderDate));
        expDatePicker.setValue(DateUtil.dateToLocalDate(expDelivery));
        if (delivery != null) {
            deliveryDatePicker.setValue(DateUtil.dateToLocalDate(delivery));
        }
        paymentTextArea.setText(paymentConditions);
        annotationTextArea.setText(annotations);
        situationComboBox.setValue(purchase.getProcessStatus());
        processComboBox.setValue(purchase.getInProcess());

    }

    private void setPreferencesListeners() {
        orderDatePicker.valueProperty().addListener((obs, oldV, value) -> {
            orderValid.setValue(!orderValid.getValue());
            if (value == null) {
                orderValid.setValue(false);
            } else {
                orderValid.setValue(true);
            }

        });

        expDatePicker.valueProperty().addListener((obs, oldV, value) -> {
            expValid.setValue(!expValid.getValue());
            if (value == null) {
                expValid.setValue(false);
            } else {
                expValid.setValue(true);
            }

        });

        ChangeListener<Boolean> change = (obs, oldV, newV) -> {
            if (orderValid.getValue() && expValid.getValue()) {
                LocalDate exp = expDatePicker.getValue();
                LocalDate order = orderDatePicker.getValue();

                Date dateOrder = DateUtil.toDate(order);
                Date dateExp = DateUtil.toDate(exp);
                datesValid.setValue(!datesValid.getValue());
                if (!dateExp.before(dateOrder)) {
                    datesValid.setValue(true);
                } else {
                    datesValid.setValue(false);
                }
            } else {
                datesValid.setValue(false);
            }
        };
        orderValid.addListener(change);
        expValid.addListener(change);

        datesValid.addListener((obs, oldV, newV) -> {
            verifyValid();
        });

        payValid.addListener((obs, oldV, newV) -> {
            verifyValid();
        });

        annoValid.addListener((obs, oldV, newV) -> {
            verifyValid();
        });
        annotationTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean valid = !ValidateFieldUtil.biggerThan(newValue, 1023);

            annoValid.setValue(valid);
            if (!valid) {
                annotationToolTip.setText(Lang.get("Annotation cannot be bigger than 1023 characters"));
                showToolTip(annotationToolTip, annotationTextArea);
            } else {
                annotationToolTip.setText("");
                annotationToolTip.hide();
            }
        });

        paymentTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean valid = !ValidateFieldUtil.biggerThan(newValue, 255);

            payValid.setValue(valid);
            if (!valid) {
                paymentToolTip.setText(Lang.get("Payment conditions cannot be bigger than 255 characters"));
                showToolTip(paymentToolTip, paymentTextArea);
            } else {
                paymentToolTip.setText("");
                paymentToolTip.hide();
            }
        });

    }

    private void showToolTip(Tooltip tooltip, Node node) {
        Window window = rootPane.getScene().getWindow();
        Bounds bounds = node.localToScreen(node.getBoundsInLocal());
        double x = bounds.getMinX();
        double y = bounds.getMaxY();
        tooltip.show(window, x, y);
    }

    private BooleanProperty orderValid = new SimpleBooleanProperty(false);
    private BooleanProperty expValid = new SimpleBooleanProperty(false);
    private BooleanProperty datesValid = new SimpleBooleanProperty(false);

    private BooleanProperty annoValid = new SimpleBooleanProperty(true);
    private BooleanProperty payValid = new SimpleBooleanProperty(true);

    private void verifyValid() {
        if (annoValid.getValue() && payValid.getValue() && datesValid.getValue()) {
            buttonSave.setDisable(false);
        } else {
            buttonSave.setDisable(true);
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    private void setTexts() {
        titleLabel.setText(Lang.get(titleLabel.getText()));
        orderLabel.setText(Lang.get(orderLabel.getText()));
        deliveryLabel.setText(Lang.get(deliveryLabel.getText()));
        expLabel.setText(Lang.get(expLabel.getText()));
        paymentLabel.setText(Lang.get(paymentLabel.getText()));
        annotationLabel.setText(Lang.get(annotationLabel.getText()));
        processLabel.setText(Lang.get(processLabel.getText()));
        situationLabel.setText(Lang.get(situationLabel.getText()));
        buttonSave.setText(Lang.get(buttonSave.getText()));
        buttonCancel.setText(Lang.get(buttonCancel.getText()));
    }
}
