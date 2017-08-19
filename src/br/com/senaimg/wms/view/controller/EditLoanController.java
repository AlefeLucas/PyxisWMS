/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.sistema.Settings;
import br.com.senaimg.wms.model.sistema.enums.CssTheme;
import br.com.senaimg.wms.model.warehouse.process.Loan;
import br.com.senaimg.wms.model.warehouse.process.LoanHasMetaItem;
import br.com.senaimg.wms.model.warehouse.process.LoanStatus;
import br.com.senaimg.wms.util.DateUtil;
import br.com.senaimg.wms.view.adapter.TableLoanHasMetaItemAdapter;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ÁlefeLucas
 */
public class EditLoanController implements Initializable {

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
    private Label loanDateLabel;
    @FXML
    private Label annotationLabel2;
    @FXML
    private TextArea annotationTextArea2;
    @FXML
    private Tooltip annotationToolTip11;
    @FXML
    private DatePicker loanDatePicker;
    @FXML
    private DatePicker expDatePicker;
    @FXML
    private DatePicker returnDatePicker;
    @FXML
    private Label returnLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private ComboBox<LoanStatus> statusComboBox;
    @FXML
    private Label loanLabel2;
    @FXML
    private Label filter3Label;
    @FXML
    private TextField searchProductLoan2;
    @FXML
    private TableView<TableLoanHasMetaItemAdapter> loanTableView2;
    @FXML
    private TableColumn<TableLoanHasMetaItemAdapter, String> mnemonicColumn3;
    @FXML
    private TableColumn<TableLoanHasMetaItemAdapter, String> quantityColumn3;
    private ObservableList<TableLoanHasMetaItemAdapter> masterDataLoan = FXCollections.observableArrayList();
    private Loan loan;

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
        setStatusComboBox();
        setLoan2Columns();
        setTableLoan2();
    }

    private void setTexts() {
        titleLabel.setText(Lang.get(titleLabel.getText()));
        loanDateLabel.setText(Lang.get(loanDateLabel.getText()));
        expLabel.setText(Lang.get(expLabel.getText()));
        returnLabel.setText(Lang.get(returnLabel.getText()));
        annotationLabel2.setText(Lang.get(annotationLabel2.getText()));
        statusLabel.setText(Lang.get(statusLabel.getText()));
        loanLabel2.setText(Lang.get(loanLabel2.getText()));
        filter3Label.setText(Lang.get(filter3Label.getText()));
        searchProductLoan2.setPromptText(Lang.get(searchProductLoan2.getPromptText()));
        buttonSave.setText(Lang.get(buttonSave.getText()));
        buttonCancel.setText(Lang.get(buttonCancel.getText()));
        
        ObservableList<TableColumn<TableLoanHasMetaItemAdapter, ?>> columns = loanTableView2.getColumns();
        for(TableColumn<TableLoanHasMetaItemAdapter, ?> column : columns){
             column.setText(Lang.get(column.getText()));
        }
    }

    private void setTheme() {
        String path = "br/com/senaimg/wms/view/";
        String css = "editloan.css";
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
        Date loanDate = DateUtil.toDate(loanDatePicker.getValue());
        Date expDate = DateUtil.toDate(expDatePicker.getValue());
        Date returnDate = DateUtil.toDate(returnDatePicker.getValue());

        loan.setLoanDate(loanDate);
        loan.setExpReturnDate(expDate);
        loan.setReturnDate(returnDate);

        loan.setStatus(statusComboBox.getValue());
        loan.setAnnotations(annotationTextArea2.getText());
        loan.update();
        closeWindow();
    }

    private void closeWindow() {
        closed.setValue(!closed.getValue());
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancelHandle(ActionEvent event) {
        closeWindow();
    }

    public BooleanProperty closed = new SimpleBooleanProperty(false);

    public void setLoan(Loan loan) {
        this.loan = loan;

        LocalDate loanDate = DateUtil.dateToLocalDate(loan.getLoanDate());
        LocalDate expReturnDate = DateUtil.dateToLocalDate(loan.getExpReturnDate());
        LocalDate returnDate = DateUtil.dateToLocalDate(loan.getReturnDate());

        loanDatePicker.setValue(loanDate);
        expDatePicker.setValue(expReturnDate);
        returnDatePicker.setValue(returnDate);

        annotationTextArea2.setText(loan.getAnnotations());

        statusComboBox.setValue(loan.getStatus());

        for (LoanHasMetaItem lhmi : loan.getLoanHasMetaItems()) {
            masterDataLoan.add(new TableLoanHasMetaItemAdapter(lhmi));
        }

    }

    private void setLoan2Columns() {
        mnemonicColumn3.setCellValueFactory(new PropertyValueFactory<>("mnemonic"));
        quantityColumn3.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    private void setTableLoan2() {
        masterDataLoan.clear();
        loanTableView2.setItems(setSearchLoan2());
    }

    private SortedList<TableLoanHasMetaItemAdapter> setSearchLoan2() {
        FilteredList<TableLoanHasMetaItemAdapter> filteredData = new FilteredList<>(masterDataLoan, p -> true);
        searchProductLoan2.textProperty().addListener((obs, oldV, newV) -> {
            filterLoan(filteredData, newV);
        });

        SortedList<TableLoanHasMetaItemAdapter> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(loanTableView2.comparatorProperty());
        return sortedData;
    }

    private void filterLoan(FilteredList<TableLoanHasMetaItemAdapter> filteredData, String newV) {
        filteredData.setPredicate((TableLoanHasMetaItemAdapter t) -> {
            if (newV == null || newV.isEmpty()) {
                return true;
            }

            String filter = newV.toLowerCase();

            if (t.getMnemonic().toLowerCase().contains(filter)) {
                return true;
            }

            return false;
        });
    }

    private void setStatusComboBox() {
        ObservableList<LoanStatus> items = statusComboBox.getItems();
        items.clear();
        items.add(LoanStatus.LOANED);
        items.add(LoanStatus.PICK);
        items.add(LoanStatus.RETURNED);
        items.add(LoanStatus.STORE);
    }
}
