/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.process.InProcess;
import br.com.senaimg.wms.model.warehouse.process.ProcessStatus;
import br.com.senaimg.wms.model.warehouse.process.Purchase;
import br.com.senaimg.wms.view.adapter.TablePurchaseAdapter;
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
public class PurchaseController extends TabController implements Initializable {

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
    private TableView<TablePurchaseAdapter> purchaseTableView;
    @FXML
    private TableColumn<TablePurchaseAdapter, String> situationColumn;
    @FXML
    private TableColumn<TablePurchaseAdapter, String> processColumn;
    @FXML
    private TableColumn<TablePurchaseAdapter, String> supplierColumn;
    @FXML
    private TableColumn<TablePurchaseAdapter, String> taxColumn;
    @FXML
    private TableColumn<TablePurchaseAdapter, String> taxTypeColumn;
    @FXML
    private TableColumn<TablePurchaseAdapter, String> phoneColumn;
    @FXML
    private TableColumn<TablePurchaseAdapter, String> emailColumn;
    @FXML
    private TableColumn<TablePurchaseAdapter, String> orderColumn;
    @FXML
    private TableColumn<TablePurchaseAdapter, String> expDeliveryColumn;
    @FXML
    private TableColumn<TablePurchaseAdapter, String> deliveryColumn;
    @FXML
    private TableColumn<TablePurchaseAdapter, String> paymentColumn;
    @FXML
    private TableColumn<TablePurchaseAdapter, String> priceColumn;
    @FXML
    private TableColumn<TablePurchaseAdapter, String> annotationsColumn;

    private ObservableList<TablePurchaseAdapter> masterData = FXCollections.observableArrayList();
    @FXML
    private Button viewItemsButton;
    private TablePurchaseAdapter selectedPurchase;
    @FXML
    private Button receiptButton;
    @FXML
    private Button storeButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTexts();
        setFilterComboBox();
        setTableClicked();
        setTableContextMenu();
        setTableColumns();
        load();
    }

    private void setTexts() {
        insertButton.setText(Lang.get(insertButton.getText()));
        modifyButton.setText(Lang.get(modifyButton.getText()));
        deleteButton.setText(Lang.get(deleteButton.getText()));
        viewItemsButton.setText(Lang.get(viewItemsButton.getText()));
        receiptButton.setText(Lang.get(receiptButton.getText()));
        storeButton.setText(Lang.get(storeButton.getText()));
        filterLabel.setText(Lang.get(filterLabel.getText()));
        searchTextField.setPromptText(Lang.get(searchTextField.getPromptText()));
        ObservableList<TableColumn<TablePurchaseAdapter, ?>> columns = purchaseTableView.getColumns();
        for (TableColumn<TablePurchaseAdapter, ?> column : columns) {
            column.setText(Lang.get(column.getText()));
        }

    }

    @FXML
    private void insertButtonHandle(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/InsertPurchase.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("Insert Purchase"));
            stage.setResizable(false);
            stage.show();

            InsertPurchaseController controller = fxmlLoader.<InsertPurchaseController>getController();
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

    private void load() {
        masterData.clear();
        for (Purchase p : Purchase.list()) {
            masterData.add(new TablePurchaseAdapter(p));
        }

        SortedList<TablePurchaseAdapter> sortedData = setSearch();
        purchaseTableView.setItems(sortedData);

    }

    private void setTableColumns() {
        situationColumn.setCellValueFactory(new PropertyValueFactory<>("situation"));
        processColumn.setCellValueFactory(new PropertyValueFactory<>("process"));
        supplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        taxColumn.setCellValueFactory(new PropertyValueFactory<>("tax"));
        taxTypeColumn.setCellValueFactory(new PropertyValueFactory<>("taxType"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        orderColumn.setCellValueFactory(new PropertyValueFactory<>("order"));
        expDeliveryColumn.setCellValueFactory(new PropertyValueFactory<>("expDelivery"));
        deliveryColumn.setCellValueFactory(new PropertyValueFactory<>("delivery"));
        paymentColumn.setCellValueFactory(new PropertyValueFactory<>("payment"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        annotationsColumn.setCellValueFactory(new PropertyValueFactory<>("annotations"));

    }

    private void setFilterComboBox() {
        filterComboBox.getItems().addAll(
                Lang.get("All fields"),
                Lang.get("Situation"),
                Lang.get("Process"),
                Lang.get("Supplier"),
                Lang.get("Tax Number"),
                Lang.get("Tax Type"),
                Lang.get("Supplier's Phone"),
                Lang.get("Supplier's Email"),
                Lang.get("Order Date"),
                Lang.get("Expected Delivery"),
                Lang.get("Delivery Date"),
                Lang.get("Payment Conditions"),
                Lang.get("Purchase Price"),
                Lang.get("Annotations")
        );

        filterComboBox.getSelectionModel().select(0);
    }

    private SortedList<TablePurchaseAdapter> setSearch() {
        FilteredList<TablePurchaseAdapter> filteredData = new FilteredList<>(masterData, p -> true);
        searchTextField.textProperty().addListener((obs, oldV, newV) -> {
            filter(filteredData, newV);
        });
        filterComboBox.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filter(filteredData, searchTextField.getText());
        });

        SortedList<TablePurchaseAdapter> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(purchaseTableView.comparatorProperty());
        return sortedData;
    }

    private void filter(FilteredList<TablePurchaseAdapter> filteredData, String newV) {
        filteredData.setPredicate((TablePurchaseAdapter t) -> {
            if (newV == null || newV.isEmpty()) {
                return true;
            }

            String filter = newV.toLowerCase();

            String fieldFilter = filterComboBox.getValue();

            if (fieldFilter.equals(Lang.get("Supplier"))) {

                if (t.getSupplier().toLowerCase().contains(filter)) {
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
            } else if (fieldFilter.equals(Lang.get("Supplier's Phone"))) {
                if (t.getPhone().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Supplier's Email"))) {
                if (t.getEmail().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Order Date"))) {
                if (t.getOrder().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Expected Delivery"))) {
                if (t.getExpDelivery().toLowerCase().contains(filter)) {
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
            } else if (fieldFilter.equals(Lang.get("Purchase Price"))) {
                if (t.getPrice().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Annotations"))) {
                if (t.getAnnotations().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("All fields"))) {

                if (t.getSupplier().toLowerCase().contains(filter)) {
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
                } else if (t.getOrder().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getExpDelivery().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getDelivery().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getPayment().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getPrice().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getAnnotations().toLowerCase().contains(filter)) {
                    return true;
                }

            }

            return false;
        });
    }

    @FXML
    private void viewItemsButtonHandle(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/ViewItemsPurchase.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("View Purchase"));
            stage.setResizable(false);
            stage.show();

            ViewItemsPurchaseController controller = fxmlLoader.<ViewItemsPurchaseController>getController();
            controller.setPurchase(selectedPurchase.getPurchase());

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    @FXML
    private void modifyButtonHandle(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/EditPurchase.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("Edit Purchase"));
            stage.setResizable(false);
            stage.show();

            EditPurchaseController controller = fxmlLoader.<EditPurchaseController>getController();
            controller.setPurchase(selectedPurchase.getPurchase());
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
        selectedPurchase.getPurchase().delete();
        load();
    }

    private void setTableClicked() {
        purchaseTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldV, selectedPurchase) -> {
            if (selectedPurchase == null) {
                this.selectedPurchase = null;
                modifyButton.setDisable(true);
                deleteButton.setDisable(true);
                viewItemsButton.setDisable(true);
                receiptButton.setDisable(true);
                storeButton.setDisable(true);

            } else {
                this.selectedPurchase = selectedPurchase;
                modifyButton.setDisable(false);
                deleteButton.setDisable(false);
                viewItemsButton.setDisable(false);
                Purchase purchase = selectedPurchase.getPurchase();
                ProcessStatus processStatus = purchase.getProcessStatus();
                InProcess inProcess = purchase.getInProcess();

                if ((inProcess == InProcess.PURCHASE && processStatus == ProcessStatus.DONE)
                        || (inProcess == InProcess.RECEIPT && processStatus == ProcessStatus.UNBEGUN)
                        || (inProcess == InProcess.RECEIPT && processStatus == ProcessStatus.ONGOING)) {
                    receiptButton.setDisable(false);

                } else {
                    receiptButton.setDisable(true);

                }

                if ((inProcess == InProcess.RECEIPT && processStatus == ProcessStatus.DONE)
                        || (inProcess == InProcess.STORE && processStatus == ProcessStatus.UNBEGUN)
                        || (inProcess == InProcess.STORE && processStatus == ProcessStatus.ONGOING)) {
                    storeButton.setDisable(false);

                } else {
                    storeButton.setDisable(true);

                }
            }

        });
    }

    private void setTableContextMenu() {
        purchaseTableView.setRowFactory(tableView -> {
            final TableRow<TablePurchaseAdapter> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem deleteMenuItem = new MenuItem(Lang.get("Delete"));
            final MenuItem modifyMenuItem = new MenuItem(Lang.get("Edit"));
            final MenuItem viewItemsMenuItem = new MenuItem(Lang.get("View Items"));

            modifyMenuItem.setOnAction(event -> modifyButtonHandle(event));
            deleteMenuItem.setOnAction(event -> deleteButtonHandle(event));
            viewItemsMenuItem.setOnAction(event -> viewItemsButtonHandle(event));

            contextMenu.getItems().addAll(modifyMenuItem, deleteMenuItem, viewItemsMenuItem);
            row.contextMenuProperty().bind(Bindings.when(row.emptyProperty()).then((ContextMenu) null).otherwise(contextMenu));

            return row;
        });
    }

    @FXML
    private void receiptButtonHandle(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/Receipt.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("Receipt"));
            stage.setResizable(false);
            stage.show();

            ReceiptController controller = fxmlLoader.<ReceiptController>getController();
            controller.setPurchase(selectedPurchase.getPurchase());
            controller.saved.addListener((observable, oldValue, newValue) -> {
                load();

            });

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    @FXML
    private void storeButtonHandle(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/Store.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("Store"));
            stage.setResizable(false);
            stage.show();

            StoreController controller = fxmlLoader.<StoreController>getController();
            controller.setPurchase(selectedPurchase.getPurchase());
            controller.saved.addListener((observable, oldValue, newValue) -> {
                load();

            });

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

}
