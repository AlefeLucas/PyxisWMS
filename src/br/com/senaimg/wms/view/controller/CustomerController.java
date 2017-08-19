/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.view.adapter.TableCustomersAdapter;
import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.agent.Customer;
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
public class CustomerController extends TabController implements Initializable {

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
    private TableView<TableCustomersAdapter> customerTableView;
    @FXML
    private TableColumn<TableCustomersAdapter, String> statusColumn;
    @FXML
    private TableColumn<TableCustomersAdapter, String> nameColumn;
    @FXML
    private TableColumn<TableCustomersAdapter, String> typeColumn;
    @FXML
    private TableColumn<TableCustomersAdapter, String> employeeColumn;
    @FXML
    private TableColumn<TableCustomersAdapter, String> taxNumberColumn;
    @FXML
    private TableColumn<TableCustomersAdapter, String> taxNumberTypeColumn;
    @FXML
    private TableColumn<TableCustomersAdapter, String> addressLine1Column;
    @FXML
    private TableColumn<TableCustomersAdapter, String> addressLine2Column;
    @FXML
    private TableColumn<TableCustomersAdapter, String> postalCodeColumn;
    @FXML
    private TableColumn<TableCustomersAdapter, String> cityColumn;
    @FXML
    private TableColumn<TableCustomersAdapter, String> stateColumn;
    @FXML
    private TableColumn<TableCustomersAdapter, String> countryColumn;
    @FXML
    private TableColumn<TableCustomersAdapter, String> countryCodeColumn;
    @FXML
    private TableColumn<TableCustomersAdapter, String> phoneColumn;
    @FXML
    private TableColumn<TableCustomersAdapter, String> phone2Column;
    @FXML
    private TableColumn<TableCustomersAdapter, String> faxColumn;
    @FXML
    private TableColumn<TableCustomersAdapter, String> emailColumn;
    @FXML
    private TableColumn<TableCustomersAdapter, String> webPageColumn;
    @FXML
    private TableColumn<TableCustomersAdapter, String> annotationColumn;
    private TableCustomersAdapter selectedCustomer;
    private ObservableList<TableCustomersAdapter> masterData = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTexts();

        setTableViewClickedHandle();
        setTableViewContextMenu();
        setFilterComboBox();
        setTableColumns();
        loadCustomers();
    }

    @FXML
    private void insertButtonHandle(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/InsertCustomer.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("Insert Customer"));
            stage.setResizable(false);
            stage.show();

            InsertCustomerController controller = fxmlLoader.<InsertCustomerController>getController();
            controller.inserted.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (newValue) {
                    loadCustomers();

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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/EditCustomer.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("Edit Customer"));
            stage.setResizable(false);
            stage.show();

            EditCustomerController controller = fxmlLoader.<EditCustomerController>getController();
            controller.setCustomer(selectedCustomer.getCustomer());
            controller.inserted.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (newValue) {
                    loadCustomers();

                }
            });

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    @FXML
    private void deleteButtonHandle(ActionEvent event) {
        Alert alert = new AlertCustom(Alert.AlertType.CONFIRMATION, Lang.get("Are you sure you want to delete ") + selectedCustomer.getName() + "?", ButtonType.YES, ButtonType.NO);
        alert.setTitle(Lang.get("Delete Customer"));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            try {
                selectedCustomer.getCustomer().delete();
            } catch (ConstraintViolationException ex) {
                showAlertError("Error", "DELETE_ERROR");
            }
            loadCustomers();
            getHome().relog();
        }
    }

    private void showAlertError(String title, String message) {
        Alert alertInvalid = new AlertCustom(Alert.AlertType.ERROR, Lang.get(message), ButtonType.OK);
        alertInvalid.setTitle(Lang.get(title));
        alertInvalid.show();
    }

    private void setTexts() {
        insertButton.setText(Lang.get(insertButton.getText()));
        modifyButton.setText(Lang.get(modifyButton.getText()));
        deleteButton.setText(Lang.get(deleteButton.getText()));
        filterLabel.setText(Lang.get(filterLabel.getText()));
        searchTextField.setPromptText(Lang.get(searchTextField.getPromptText()));
        ObservableList<TableColumn<TableCustomersAdapter, ?>> columns = customerTableView.getColumns();
        for (TableColumn<TableCustomersAdapter, ?> column : columns) {
            column.setText(Lang.get(column.getText()));
        }
    }

    private void setTableViewClickedHandle() {
        customerTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedItem) -> {
            if (selectedItem == null) {
                modifyButton.setDisable(true);
                deleteButton.setDisable(true);
                selectedCustomer = null;
            } else {
                selectedCustomer = selectedItem;
                modifyButton.setDisable(false);
                deleteButton.setDisable(false);
            }
        });
    }

    private void setTableViewContextMenu() {
        customerTableView.setRowFactory(tableView -> {
            final TableRow<TableCustomersAdapter> row = new TableRow<>();
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

    private void setFilterComboBox() {
        filterComboBox.getItems().addAll(
                Lang.get("All fields"),
                Lang.get("Status"),
                Lang.get("Name"),
                Lang.get("Type"),
                Lang.get("Employee"),
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

    private void setTableColumns() {
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        employeeColumn.setCellValueFactory(new PropertyValueFactory<>("isEmployee"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        taxNumberColumn.setCellValueFactory(new PropertyValueFactory<>("tax"));
        taxNumberTypeColumn.setCellValueFactory(new PropertyValueFactory<>("taxType"));
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
        annotationColumn.setCellValueFactory(new PropertyValueFactory<>("annotation"));
    }

    private void loadCustomers() {
        masterData.clear();

        for (Customer customer : Customer.list()) {
            masterData.add(new TableCustomersAdapter(customer));
        }

        SortedList<TableCustomersAdapter> sortedData = setSearch();

        customerTableView.setItems(sortedData);
    }

    private SortedList<TableCustomersAdapter> setSearch() {
        FilteredList<TableCustomersAdapter> filteredData = new FilteredList<>(masterData, p -> true);
        searchTextField.textProperty().addListener((obs, oldV, newV) -> {
            filter(filteredData, newV);
        });
        filterComboBox.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filter(filteredData, searchTextField.getText());
        });

        SortedList<TableCustomersAdapter> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(customerTableView.comparatorProperty());
        return sortedData;
    }

    private void filter(FilteredList<TableCustomersAdapter> filteredData, String newV) {
        filteredData.setPredicate((TableCustomersAdapter t) -> {
            if (newV == null || newV.isEmpty()) {
                return true;
            }

            String filter = newV.toLowerCase();

            String fieldFilter = filterComboBox.getValue();

            if (fieldFilter.equals(Lang.get("Status"))) {

                if (t.getStatus().toLowerCase().contains(filter)) {
                    return true;
                }

            } else if (fieldFilter.equals(Lang.get("Employee"))) {
                if (t.getIsEmployee().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Type"))) {
                if (t.getType().toLowerCase().contains(filter)) {
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
                } else if (t.getType().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getIsEmployee().toLowerCase().contains(filter)) {
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
}
