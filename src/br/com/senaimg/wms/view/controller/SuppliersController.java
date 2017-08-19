/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.agent.Supplier;
import br.com.senaimg.wms.view.adapter.TableSuppliersAdapter;
import br.com.senaimg.wms.view.layout.AlertCustom;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.exception.ConstraintViolationException;

/**
 * FXML Controller class
 *
 * @author ÁlefeLucas
 */
public class SuppliersController extends TabController implements Initializable {

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
    private TableView<TableSuppliersAdapter> supplierTableView;
    @FXML
    private TableColumn<TableSuppliersAdapter, String> statusColumn;
    @FXML
    private TableColumn<TableSuppliersAdapter, String> mnemonicColumn;
    @FXML
    private TableColumn<TableSuppliersAdapter, String> nameColumn;
    @FXML
    private TableColumn<TableSuppliersAdapter, String> taxNumberColumn;
    @FXML
    private TableColumn<TableSuppliersAdapter, String> taxNumberTypeColumn;
    @FXML
    private TableColumn<TableSuppliersAdapter, String> addressLine1Column;
    @FXML
    private TableColumn<TableSuppliersAdapter, String> addressLine2Column;
    @FXML
    private TableColumn<TableSuppliersAdapter, String> postalCodeColumn;
    @FXML
    private TableColumn<TableSuppliersAdapter, String> cityColumn;
    @FXML
    private TableColumn<TableSuppliersAdapter, String> stateColumn;
    @FXML
    private TableColumn<TableSuppliersAdapter, String> countryColumn;
    @FXML
    private TableColumn<TableSuppliersAdapter, String> countryCodeColumn;
    @FXML
    private TableColumn<TableSuppliersAdapter, String> phoneColumn;
    @FXML
    private TableColumn<TableSuppliersAdapter, String> phone2Column;
    @FXML
    private TableColumn<TableSuppliersAdapter, String> faxColumn;
    @FXML
    private TableColumn<TableSuppliersAdapter, String> emailColumn;
    @FXML
    private TableColumn<TableSuppliersAdapter, String> annotationColumn;

    private ObservableList<TableSuppliersAdapter> masterData = FXCollections.observableArrayList();

    private TableSuppliersAdapter selectedSupplier;
    @FXML
    private TableColumn<TableSuppliersAdapter, String> companyCodeColumn;
    @FXML
    private TableColumn<TableSuppliersAdapter, String> webPageColumn;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTexts();

        setTableViewClickedHandle();
        setTableViewContextMenu();
        setFilterComboBox();
        setTableColumns();
        loadSuppliers();
    }

    private void setTexts() {
        insertButton.setText(Lang.get(insertButton.getText()));
        modifyButton.setText(Lang.get(modifyButton.getText()));
        deleteButton.setText(Lang.get(deleteButton.getText()));
        filterLabel.setText(Lang.get(filterLabel.getText()));
        searchTextField.setPromptText(Lang.get(searchTextField.getPromptText()));
        ObservableList<TableColumn<TableSuppliersAdapter, ?>> columns = supplierTableView.getColumns();
        for (TableColumn<TableSuppliersAdapter, ?> column : columns) {
            column.setText(Lang.get(column.getText()));
        }
    }

    private void setFilterComboBox() {
        filterComboBox.getItems().addAll(
                Lang.get("All fields"),
                Lang.get("Status"),
                Lang.get("Mnemonic"),
                Lang.get("Company Code"),
                Lang.get("Name"),
                Lang.get("Tax Number"),
                Lang.get("Tax Number Type"),
                Lang.get("Address Line 1"),
                Lang.get("Address Line 2"),
                Lang.get("Postal Code"),
                Lang.get("City"),
                Lang.get("State"),
                Lang.get("Country"),
                Lang.get("Country Code"),
                Lang.get("Phone Number"),
                Lang.get("Phone Number 2"),
                Lang.get("Fax Number"),
                Lang.get("Email"),
                Lang.get("Web Page")
        );

        filterComboBox.getSelectionModel().select(0);
    }

    private void loadSuppliers() {
        masterData.clear();

        for (Supplier supplier : Supplier.list()) {
            masterData.add(new TableSuppliersAdapter(supplier));
        }

        SortedList<TableSuppliersAdapter> sortedData = setSearch();

        supplierTableView.setItems(sortedData);
    }

    private SortedList<TableSuppliersAdapter> setSearch() {
        FilteredList<TableSuppliersAdapter> filteredData = new FilteredList<>(masterData, p -> true);
        searchTextField.textProperty().addListener((obs, oldV, newV) -> {
            filter(filteredData, newV);
        });
        filterComboBox.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filter(filteredData, searchTextField.getText());
        });

        SortedList<TableSuppliersAdapter> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(supplierTableView.comparatorProperty());
        return sortedData;
    }

    private void filter(FilteredList<TableSuppliersAdapter> filteredData, String newV) {
        filteredData.setPredicate((TableSuppliersAdapter t) -> {
            if (newV == null || newV.isEmpty()) {
                return true;
            }

            String filter = newV.toLowerCase();

            String fieldFilter = filterComboBox.getValue();

            if (fieldFilter.equals(Lang.get("Status"))) {

                if (t.getStatus().toLowerCase().contains(filter)) {
                    return true;
                }

            } else if (fieldFilter.equals(Lang.get("Mnemonic"))) {
                if (t.getMnemonic().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Company Code"))) {
                if (t.getCompanyCode().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Name"))) {
                if (t.getName().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Tax Number"))) {
                if (t.getTax().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Tax Number Type"))) {
                if (t.getTaxType().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Address Line 1"))) {
                if (t.getAddress1().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Address Line 2"))) {
                if (t.getAddress2().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Postal Code"))) {
                if (t.getPostalCode().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("City"))) {
                if (t.getCity().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("State"))) {
                if (t.getState().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Country"))) {
                if (t.getCountry().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Country Code"))) {
                if (t.getCountryCode().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Phone Number"))) {
                if (t.getPhone().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Fax Number"))) {
                if (t.getFax().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Phone Number 2"))) {
                if (t.getPhone2().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Email"))) {
                if (t.getEmail().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Web Page"))) {
                if (t.getWebPage().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("All fields"))) {

                if (t.getStatus().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getAddress1().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getCompanyCode().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getAddress2().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getCity().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getCountry().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getCountryCode().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getEmail().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getFax().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getMnemonic().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getName().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getPhone().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getPhone2().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getPostalCode().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getState().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getTax().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getTaxType().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getWebPage().toLowerCase().contains(filter)) {
                    return true;
                }

            }

            return false;
        });
    }

    private void setTableViewContextMenu() {
        supplierTableView.setRowFactory(tableView -> {
            final TableRow<TableSuppliersAdapter> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem deleteMenuItem = new MenuItem(Lang.get("Delete"));
            final MenuItem modifyMenuItem = new MenuItem(Lang.get("Edit"));
            modifyMenuItem.setOnAction(event -> modifyButtonHandle(event));
            deleteMenuItem.setOnAction(event -> deleteButtonHandle(event));
            contextMenu.getItems().addAll(modifyMenuItem, deleteMenuItem);
            row.contextMenuProperty().bind(Bindings.when(row.emptyProperty()).then((ContextMenu) null).otherwise(contextMenu));

            return row;
        });
    }

    /**
     *
     */
    public void setTableViewClickedHandle() {
        supplierTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedItem) -> {
            if (selectedItem == null) {
                modifyButton.setDisable(true);
                deleteButton.setDisable(true);
                selectedSupplier = null;
            } else {
                selectedSupplier = selectedItem;
                modifyButton.setDisable(false);
                deleteButton.setDisable(false);
            }
        });
    }

    private void setTableColumns() {
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        mnemonicColumn.setCellValueFactory(new PropertyValueFactory<>("mnemonic"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        taxNumberColumn.setCellValueFactory(new PropertyValueFactory<>("tax"));
        taxNumberTypeColumn.setCellValueFactory(new PropertyValueFactory<>("taxType"));
        annotationColumn.setCellValueFactory(new PropertyValueFactory<>("annotation"));
        addressLine1Column.setCellValueFactory(new PropertyValueFactory<>("address1"));
        addressLine2Column.setCellValueFactory(new PropertyValueFactory<>("address2"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        countryCodeColumn.setCellValueFactory(new PropertyValueFactory<>("countryCode"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        phone2Column.setCellValueFactory(new PropertyValueFactory<>("phone2"));
        faxColumn.setCellValueFactory(new PropertyValueFactory<>("fax"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        webPageColumn.setCellValueFactory(new PropertyValueFactory<>("webPage"));
        companyCodeColumn.setCellValueFactory(new PropertyValueFactory<>("companyCode"));
    }

    @FXML
    private void insertButtonHandle(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/InsertSupplier.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("Insert Supplier"));
            stage.setResizable(false);
            stage.show();

            InsertSupplierController controller = fxmlLoader.<InsertSupplierController>getController();
            controller.inserted.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (newValue) {
                    loadSuppliers();

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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/EditSupplier.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("Edit Supplier"));
            stage.setResizable(false);
            stage.show();

            EditSupplierController controller = fxmlLoader.<EditSupplierController>getController();
            controller.setSupplier(selectedSupplier.getSupplier());
            controller.inserted.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (newValue) {
                    loadSuppliers();
                    getHome().relog();
                }
            });

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    @FXML
    private void deleteButtonHandle(ActionEvent event) {
        Alert alert = new AlertCustom(Alert.AlertType.CONFIRMATION, Lang.get("Are you sure you want to delete ") + selectedSupplier.getMnemonic() + "?", ButtonType.YES, ButtonType.NO);
        alert.setTitle(Lang.get("Delete Supplier"));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            try{
            selectedSupplier.getSupplier().delete();
             } catch (ConstraintViolationException ex) {
             showAlertError("Error", "DELETE_ERROR");
             }
            loadSuppliers();
            getHome().relog();
        }
    }
  private void showAlertError(String title, String message) {
        Alert alertInvalid = new AlertCustom(Alert.AlertType.ERROR, Lang.get(message), ButtonType.OK);
        alertInvalid.setTitle(Lang.get(title));
        alertInvalid.show();
    }
}
