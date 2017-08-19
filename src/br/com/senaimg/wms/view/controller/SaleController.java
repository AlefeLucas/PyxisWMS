/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.process.OutProcess;
import br.com.senaimg.wms.model.warehouse.process.ProcessStatus;
import br.com.senaimg.wms.model.warehouse.process.Sale;
import br.com.senaimg.wms.view.adapter.TableSaleAdapter;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
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

/**
 * FXML Controller class
 *
 * @author ÁlefeLucas
 */
public class SaleController extends TabController implements Initializable {

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
    private TableColumn<TableSaleAdapter, String> situationColumn;
    @FXML
    private TableColumn<TableSaleAdapter, String> processColumn;
    @FXML
    private TableColumn<TableSaleAdapter, String> customerColumn;
    @FXML
    private TableColumn<TableSaleAdapter, String> typeColumn;
    @FXML
    private TableColumn<TableSaleAdapter, String> taxColumn;
    @FXML
    private TableColumn<TableSaleAdapter, String> taxTypeColumn;
    @FXML
    private TableColumn<TableSaleAdapter, String> phoneColumn;
    @FXML
    private TableColumn<TableSaleAdapter, String> emailColumn;
    @FXML
    private TableColumn<TableSaleAdapter, String> issueColumn;
    @FXML
    private TableColumn<TableSaleAdapter, String> shippingColumn;
    @FXML
    private TableColumn<TableSaleAdapter, String> deliveryColumn;
    @FXML
    private TableColumn<TableSaleAdapter, String> paymentColumn;
    @FXML
    private TableColumn<TableSaleAdapter, String> priceColumn;
    @FXML
    private TableColumn<TableSaleAdapter, String> profitMarginColumn;
    @FXML
    private TableColumn<TableSaleAdapter, String> profitColumn;
    @FXML
    private TableColumn<TableSaleAdapter, String> address1Column;
    @FXML
    private TableColumn<TableSaleAdapter, String> address2Column;
    @FXML
    private TableColumn<TableSaleAdapter, String> postalColumn;
    @FXML
    private TableColumn<TableSaleAdapter, String> cityColumn;
    @FXML
    private TableColumn<TableSaleAdapter, String> stateColumn;
    @FXML
    private TableColumn<TableSaleAdapter, String> countryColumn;
    @FXML
    private TableColumn<TableSaleAdapter, String> annotationsColumn;
    @FXML
    private TableView<TableSaleAdapter> saleTableView;

    private ObservableList<TableSaleAdapter> masterData = FXCollections.observableArrayList();
    private TableSaleAdapter selectedSale;
    @FXML
    private Button viewItemsButton;
    @FXML
    private Button pickButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTexts();
        setTableCliked();
        setTableContext();
        setFilterComboBox();
        setTableColumns();
        load();
    }
    
    private void setTexts() {
        insertButton.setText(Lang.get(insertButton.getText()));
        modifyButton.setText(Lang.get(modifyButton.getText()));
        deleteButton.setText(Lang.get(deleteButton.getText()));
        viewItemsButton.setText(Lang.get(viewItemsButton.getText()));
        pickButton.setText(Lang.get(pickButton.getText()));
        filterLabel.setText(Lang.get(filterLabel.getText()));
        searchTextField.setPromptText(Lang.get(searchTextField.getPromptText()));
        ObservableList<TableColumn<TableSaleAdapter, ?>> columns = saleTableView.getColumns();
        for (TableColumn<TableSaleAdapter, ?> column : columns) {
            column.setText(Lang.get(column.getText()));
        }
    }

    private void load() {
        masterData.clear();
        for (Sale s : Sale.list()) {
            masterData.add(new TableSaleAdapter(s));
        }
        SortedList<TableSaleAdapter> sortedData = setSearch();
        saleTableView.setItems(sortedData);

    }

    @FXML
    private void insertButtonHandle(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/InsertSale.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("Insert Sale"));
            stage.setResizable(false);
            stage.show();

            InsertSaleController controller = fxmlLoader.<InsertSaleController>getController();
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/EditSale.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("Edit Sale"));
            stage.setResizable(false);
            stage.show();

            EditSaleController controller = fxmlLoader.<EditSaleController>getController();
            controller.setSale(selectedSale.getSale());
            controller.saved.addListener((observable, oldValue, newValue) -> {
                load();

            });

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    @FXML
    private void deleteButtonHandle(ActionEvent event) {
        selectedSale.getSale().delete();
        load();
        
    }

    private void setTableColumns() {
        situationColumn.setCellValueFactory(new PropertyValueFactory<>("situation"));
        processColumn.setCellValueFactory(new PropertyValueFactory<>("process"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customer"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("customerType"));
        taxColumn.setCellValueFactory(new PropertyValueFactory<>("tax"));
        taxTypeColumn.setCellValueFactory(new PropertyValueFactory<>("taxType"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        issueColumn.setCellValueFactory(new PropertyValueFactory<>("issue"));
        shippingColumn.setCellValueFactory(new PropertyValueFactory<>("shipping"));
        deliveryColumn.setCellValueFactory(new PropertyValueFactory<>("delivery"));
        paymentColumn.setCellValueFactory(new PropertyValueFactory<>("payment"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        profitMarginColumn.setCellValueFactory(new PropertyValueFactory<>("profitMargin"));
        profitColumn.setCellValueFactory(new PropertyValueFactory<>("totalProfit"));
        address1Column.setCellValueFactory(new PropertyValueFactory<>("address1"));
        address2Column.setCellValueFactory(new PropertyValueFactory<>("address2"));
        postalColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        annotationsColumn.setCellValueFactory(new PropertyValueFactory<>("annotation"));
    }

    private void setFilterComboBox() {
        filterComboBox.getItems().addAll(
                Lang.get("All fields"),
                Lang.get("Situation"),
                Lang.get("Process"),
                Lang.get("Customer"),
                Lang.get("Tax Number"),
                Lang.get("Tax Type"),
                Lang.get("Customer's Phone"),
                Lang.get("Customer's Email"),
                Lang.get("Issue Date"),
                Lang.get("Shipping Date"),
                Lang.get("Delivery Date"),
                Lang.get("Payment Conditions"),
                Lang.get("Total Selling Price"),
                Lang.get("Profit Margin"),
                Lang.get("Total Profit"),
                Lang.get("Address Line 1"),
                Lang.get("Address Line 2"),
                Lang.get("Postal Code"),
                Lang.get("City"),
                Lang.get("State"),
                Lang.get("Country"),
                Lang.get("Annotations")
        );

        filterComboBox.getSelectionModel().select(0);
    }

    private SortedList<TableSaleAdapter> setSearch() {
        FilteredList<TableSaleAdapter> filteredData = new FilteredList<>(masterData, p -> true);
        searchTextField.textProperty().addListener((obs, oldV, newV) -> {
            filter(filteredData, newV);
        });
        filterComboBox.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filter(filteredData, searchTextField.getText());
        });

        SortedList<TableSaleAdapter> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(saleTableView.comparatorProperty());
        return sortedData;
    }

    private void filter(FilteredList<TableSaleAdapter> filteredData, String newV) {
        filteredData.setPredicate((TableSaleAdapter t) -> {
            if (newV == null || newV.isEmpty()) {
                return true;
            }

            String filter = newV.toLowerCase();

            String fieldFilter = filterComboBox.getValue();

            if (fieldFilter.equals(Lang.get("Customer"))) {
                if (t.getCustomer().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Situation"))) {
                if (t.getSituation().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Process"))) {
                if (t.getProcess().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Tax Number"))) {
                if (t.getTax().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Tax Type"))) {
                if (t.getTaxType().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Customer's Phone"))) {
                if (t.getPhone().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Customer's Email"))) {
                if (t.getEmail().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Issue Date"))) {
                if (t.getIssue().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Shipping Date"))) {
                if (t.getIssue().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Delivery Date"))) {
                if (t.getDelivery().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Payment Conditions"))) {
                if (t.getPayment().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Total Selling Price"))) {
                if (t.getPrice().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Profit Margin"))) {
                if (t.getProfitMargin().toLowerCase().contains(filter)) {
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
            } else if (fieldFilter.equals(Lang.get("Annotations"))) {
                if (t.getAnnotation().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("All fields"))) {

                if (t.getCustomer().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getSituation().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getProcess().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getTax().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getTaxType().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getPhone().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getEmail().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getIssue().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getShipping().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getDelivery().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getPayment().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getPrice().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getProfitMargin().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getTotalProfit().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getAddress1().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getAddress2().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getPostalCode().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getCity().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getState().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getCountry().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getAnnotation().toLowerCase().contains(filter)) {
                    return true;
                }

            }

            return false;
        });
    }

    
    
    private void setTableCliked() {
        saleTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldV, selectedSale) -> {
            if (selectedSale != null) {
                this.selectedSale = selectedSale;
                modifyButton.setDisable(false);
                deleteButton.setDisable(false);
                viewItemsButton.setDisable(false);
                Sale sale = selectedSale.getSale();
                OutProcess outProcess = sale.getOutProcess();
                ProcessStatus processStatus = sale.getProcessStatus();

                if ((outProcess == OutProcess.SALE && processStatus == ProcessStatus.DONE)
                        || (outProcess == OutProcess.PICK && processStatus == ProcessStatus.UNBEGUN)
                        || (outProcess == OutProcess.PICK && processStatus == ProcessStatus.ONGOING)) {
                    pickButton.setDisable(false);
                }

            } else {
                this.selectedSale = null;
                modifyButton.setDisable(true);
                deleteButton.setDisable(true);
                viewItemsButton.setDisable(true);
                pickButton.setDisable(true);
            }
        });

    }

    private void setTableContext() {
        saleTableView.setRowFactory(tableView -> {
            final TableRow<TableSaleAdapter> row = new TableRow<>();
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

    @FXML
    private void viewItemsHandle(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/ViewItemsSale.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("View Sale"));
            stage.setResizable(false);
            stage.show();

            ViewItemsSaleController controller = fxmlLoader.<ViewItemsSaleController>getController();
            controller.setSale(selectedSale.getSale());

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    @FXML
    private void pickHandle(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/PickSale.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("Insert Sale"));
            stage.setResizable(false);
            stage.show();

            PickSaleController controller = fxmlLoader.<PickSaleController>getController();
            controller.setSale(selectedSale.getSale());
 controller.saved.addListener((observable, oldValue, newValue) -> {
                load();

            });
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
