/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.process.Loan;
import br.com.senaimg.wms.model.warehouse.process.LoanStatus;
import br.com.senaimg.wms.view.adapter.TableLoanAdapter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class LoanController extends TabController implements Initializable {

    @FXML
    private StackPane rootTabPane;
    @FXML
    private Button insertButton;
    @FXML
    private Button modifyButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Label filterLabel;
    @FXML
    private ComboBox<String> filterComboBox;
    @FXML
    private TextField searchTextField;
    @FXML
    private TableView<TableLoanAdapter> loanTableView;
    @FXML
    private TableColumn<TableLoanAdapter, String> situationColumn;
    @FXML
    private TableColumn<TableLoanAdapter, String> employeeColumn;
    @FXML
    private TableColumn<TableLoanAdapter, String> loanColumn;
    @FXML
    private TableColumn<TableLoanAdapter, String> expReturnColumn;
    @FXML
    private TableColumn<TableLoanAdapter, String> returnColumn;
    @FXML
    private TableColumn<TableLoanAdapter, String> annotationsColumn;
    private ObservableList<TableLoanAdapter> masterData = FXCollections.observableArrayList();
    private TableLoanAdapter selectedLoan;
    @FXML
    private Button devolutionButton;
    @FXML
    private Button viewItemsButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTexts();
        setColumns();
        setFilterComboBox();
        setTableCliked();
        load();
    }

    private void setTexts() {
        insertButton.setText(Lang.get(insertButton.getText()));
        modifyButton.setText(Lang.get(modifyButton.getText()));
        deleteButton.setText(Lang.get(deleteButton.getText()));
        viewItemsButton.setText(Lang.get(viewItemsButton.getText()));
        devolutionButton.setText(Lang.get(devolutionButton.getText()));
        filterLabel.setText(Lang.get(filterLabel.getText()));
        searchTextField.setPromptText(Lang.get(searchTextField.getPromptText()));
        ObservableList<TableColumn<TableLoanAdapter, ?>> columns = loanTableView.getColumns();
        for(TableColumn<TableLoanAdapter, ?> column : columns){
            column.setText(Lang.get(column.getText()));
        }
    }

    private void setTableCliked() {
        loanTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldV, selectedLoan) -> {
            if (selectedLoan != null) {
                this.selectedLoan = selectedLoan;
                modifyButton.setDisable(false);
                deleteButton.setDisable(false);
                viewItemsButton.setDisable(false);
                if (selectedLoan.getLoan().getStatus() == LoanStatus.LOANED) {
                    devolutionButton.setDisable(false);
                }
            } else {
                this.selectedLoan = null;
                modifyButton.setDisable(true);
                deleteButton.setDisable(true);
                devolutionButton.setDisable(true);
                viewItemsButton.setDisable(true);
            }
        });

    }

    private void setColumns() {
        employeeColumn.setCellValueFactory(new PropertyValueFactory<>("customer"));
        situationColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        loanColumn.setCellValueFactory(new PropertyValueFactory<>("loanDate"));
        expReturnColumn.setCellValueFactory(new PropertyValueFactory<>("expDate"));
        returnColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        annotationsColumn.setCellValueFactory(new PropertyValueFactory<>("annotations"));
    }

    private void load() {
        masterData.clear();
        for (Loan l : Loan.list()) {
            masterData.add(new TableLoanAdapter(l));
        }
        SortedList<TableLoanAdapter> sortedData = setSearch();
        loanTableView.setItems(sortedData);

    }

    @FXML
    private void insertButtonHandle(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/InsertLoan.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("Insert Loan"));
            stage.setResizable(false);
            stage.show();

            InsertLoanController controller = fxmlLoader.<InsertLoanController>getController();
            controller.inserted.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (newValue) {
                    load();

                }
            });

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    @FXML
    private void modifyButtonHandle(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/EditLoan.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("Edit Loan"));
            stage.setResizable(false);
            stage.show();

            EditLoanController controller = fxmlLoader.<EditLoanController>getController();
            controller.setLoan(selectedLoan.getLoan());
            controller.closed.addListener((observable, oldValue, newValue) -> {
                load();

            });

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    @FXML
    private void deleteButtonHandle(ActionEvent event) {
        selectedLoan.getLoan().delete();
        load();
    }

    private void setFilterComboBox() {
        filterComboBox.getItems().addAll(
                Lang.get("All fields"),
                Lang.get("Situation"),
                Lang.get("Customer"),
                Lang.get("Loan Date"),
                Lang.get("Expected Return Date"),
                Lang.get("Return Date"),
                Lang.get("Annotations")
        );

        filterComboBox.getSelectionModel().select(0);
    }

    private SortedList<TableLoanAdapter> setSearch() {
        FilteredList<TableLoanAdapter> filteredData = new FilteredList<>(masterData, p -> true);
        searchTextField.textProperty().addListener((obs, oldV, newV) -> {
            filter(filteredData, newV);
        });
        filterComboBox.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filter(filteredData, searchTextField.getText());
        });

        SortedList<TableLoanAdapter> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(loanTableView.comparatorProperty());
        return sortedData;
    }

    private void filter(FilteredList<TableLoanAdapter> filteredData, String text) {
        filteredData.setPredicate((TableLoanAdapter t) -> {
            if (text == null || text.isEmpty()) {
                return true;
            }

            String filter = text.toLowerCase();

            String fieldFilter = filterComboBox.getValue();

            if (fieldFilter.equals(Lang.get("Situation"))) {
                if (t.getStatus().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Customer"))) {
                if (t.getCustomer().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Loan Date"))) {
                if (t.getLoanDate().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Expected Return Date"))) {
                if (t.getExpDate().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Return Date"))) {
                if (t.getReturnDate().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Annotations"))) {
                if (t.getAnnotations().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("All fields"))) {

                if (t.getCustomer().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getCustomer().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getStatus().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getLoanDate().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getExpDate().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getReturnDate().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getAnnotations().toLowerCase().contains(filter)) {
                    return true;
                }

            }

            return false;
        });
    }

    @FXML
    private void devolutionHandle(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/DevolutionLoan.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("Devolution Loan"));
            stage.setResizable(false);
            stage.show();

            DevolutionLoanController controller = fxmlLoader.<DevolutionLoanController>getController();
            controller.setLoan(selectedLoan.getLoan());
            controller.saved.addListener((observable, oldValue, newValue) -> {
                load();

            });

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    @FXML
    private void viewItemsHandle(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/ViewItemsLoan.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("View Loan"));
            stage.setResizable(false);
            stage.show();

            ViewItemsLoanController controller = fxmlLoader.<ViewItemsLoanController>getController();
            controller.setLoan(selectedLoan.getLoan());

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

}
