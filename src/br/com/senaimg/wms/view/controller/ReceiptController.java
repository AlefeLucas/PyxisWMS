/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.sistema.Settings;
import br.com.senaimg.wms.model.sistema.enums.CssTheme;
import br.com.senaimg.wms.model.warehouse.agent.Supplier;
import br.com.senaimg.wms.model.warehouse.process.InProcess;
import br.com.senaimg.wms.model.warehouse.process.ProcessStatus;
import br.com.senaimg.wms.model.warehouse.process.Purchase;
import br.com.senaimg.wms.model.warehouse.process.PurchaseHasItem;
import br.com.senaimg.wms.model.warehouse.process.PurchaseHasMetaItem;
import br.com.senaimg.wms.model.warehouse.stock.item.Batch;
import br.com.senaimg.wms.model.warehouse.stock.item.Item;
import br.com.senaimg.wms.model.warehouse.stock.item.MetaItem;
import br.com.senaimg.wms.util.DateUtil;
import br.com.senaimg.wms.util.SystemImageUtil;
import br.com.senaimg.wms.view.adapter.TableBatchAdapter;
import br.com.senaimg.wms.view.layout.AlertCustom;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class ReceiptController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private StackPane titlePane;
    @FXML
    private Label titleLabel;
    @FXML
    private VBox centerPane;
    @FXML
    private Label remainingItemsLabel;
    @FXML
    private Label itemLabel;
    @FXML
    private ComboBox<PurchaseHasMetaItem> itemComboBox;
    @FXML
    private Label priceLabel;
    @FXML
    private Label priceValueLabel;
    @FXML
    private Label remainingLabel;
    @FXML
    private Label remainingValueLabel;
    @FXML
    private Label registerBatchLabel;
    @FXML
    private TextField batchNumberTextField;
    @FXML
    private DatePicker manufacturingDatePicker;
    @FXML
    private DatePicker expirationDatePicker;
    @FXML
    private Label quantityLabel;
    @FXML
    private Spinner<Integer> quantitySpinner;
    @FXML
    private Button addButton;
    @FXML
    private Label BatchesLabel;
    @FXML
    private TableView<TableBatchAdapter> batchTableView;
    @FXML
    private TableColumn<TableBatchAdapter, String> itemColumn;
    @FXML
    private TableColumn<TableBatchAdapter, String> numberColumn;
    @FXML
    private TableColumn<TableBatchAdapter, String> manuColumn;
    @FXML
    private TableColumn<TableBatchAdapter, String> expirationColumn;
    @FXML
    private TableColumn<TableBatchAdapter, String> quantityColumn;
    @FXML
    private HBox footPane;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    private Purchase purchase;
    public BooleanProperty saved = new SimpleBooleanProperty(false);
    private ObservableList<TableBatchAdapter> masterData = FXCollections.observableArrayList();
    private TableBatchAdapter selectedBatch;
    @FXML
    private Tooltip batchToolTip;
    @FXML
    private Label batchRegisterLabel;
    @FXML
    private Label manuDateRegisterLabel;
    @FXML
    private Label ExpDateRegisterLabel;

    private Optional<ButtonType> showAlertSure(String title, String message) {
        Alert alertModify = new AlertCustom(Alert.AlertType.CONFIRMATION, Lang.get(message), ButtonType.YES, ButtonType.NO);
        alertModify.setTitle(Lang.get(title));
        Optional<ButtonType> result = alertModify.showAndWait();
        return result;
    }

    private void showAlertError(String title, String message) {
        Alert alertInvalid = new AlertCustom(Alert.AlertType.ERROR, Lang.get(message), ButtonType.OK);
        alertInvalid.setTitle(Lang.get(title));
        alertInvalid.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTexts();
        setTheme();
        setItemListener();
        setTableColumns();
        setTable();
        setTableListener();
        setTableContextMenu();
        batchNumberTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean l = false;

            try {
                Long.parseLong(newValue);
                l = true;
            } catch (NullPointerException | NumberFormatException ex) {
                l = false;
            }

            addButton.setDisable(!(itemComboBox.getValue() != null && l));
            if (!l && newValue != null && !newValue.isEmpty()) {

                batchToolTip.setText(Lang.get("Batch number is invalid"));
                showToolTip(batchToolTip, batchNumberTextField);
            } else {
                batchToolTip.setText("");
                batchToolTip.hide();

            }

        });
    }

    private void setTexts() {
        remainingItemsLabel.setText(Lang.get(remainingItemsLabel.getText()));
        titleLabel.setText(Lang.get(titleLabel.getText()));
        priceLabel.setText(Lang.get(priceLabel.getText()));
        remainingLabel.setText(Lang.get(remainingLabel.getText()));
        registerBatchLabel.setText(Lang.get(registerBatchLabel.getText()));
        batchRegisterLabel.setText(Lang.get(batchRegisterLabel.getText()));
        manuDateRegisterLabel.setText(Lang.get(manuDateRegisterLabel.getText()));
        ExpDateRegisterLabel.setText(Lang.get(ExpDateRegisterLabel.getText()));
        quantityLabel.setText(Lang.get(quantityLabel.getText()));
        addButton.setText(Lang.get(addButton.getText()));
        BatchesLabel.setText(Lang.get(titleLabel.getText()));
        ObservableList<TableColumn<TableBatchAdapter, ?>> columns = batchTableView.getColumns();
        for (TableColumn<TableBatchAdapter, ?> column : columns) {
            column.setText(Lang.get(column.getText()));
        }
        cancelButton.setText(Lang.get(cancelButton.getText()));
        saveButton.setText(Lang.get(saveButton.getText()));

    }

    private void setTheme() {
        String path = "br/com/senaimg/wms/view/";
        String css = "receipt.css";
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

    private void closeWindow() {
        saved.setValue(!saved.getValue());
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addHandle(ActionEvent event) {

        PurchaseHasMetaItem selected = itemComboBox.getValue();
        Supplier supplier = selected.getPurchase().getSupplier();
        MetaItem metaItem = selected.getMetaItem();
        double inPrice = selected.getInPrice();
        String strBNumber = batchNumberTextField.getText();
        long number = 0;
        try {
            number = Long.parseLong(strBNumber);
        } catch (Exception e) {

        }

        LocalDate localManu = manufacturingDatePicker.getValue();
        LocalDate localExp = expirationDatePicker.getValue();
        Integer quantity = quantitySpinner.getValue();

        Date manu;

        Date exp;

        manu = DateUtil.toDate(localManu);
        exp = DateUtil.toDate(localExp);

        Batch batch = new Batch(number, exp, manu, metaItem);
        boolean validManu = false;
        boolean dateCoerencia = false;
        if (manu != null) {
            validManu = !DateUtil.isFuture(manu);
            if (exp != null) {
                dateCoerencia = (manu.compareTo(exp) < 0 ? true : false);
            } else {
                dateCoerencia = true;
            }
        } else {
            validManu = true;
            dateCoerencia = true;
        }

        boolean valid = validManu && dateCoerencia;
        if (valid) {

            for (int x = 0; x < quantity; x++) {
                Item item = new Item(batch, supplier, inPrice, "");
                batch.add(item);
            }

            masterData.add(new TableBatchAdapter(batch));

            loadItems();

            boolean noItemsLeft = itemComboBox.getItems().isEmpty();
            boolean itemsAdded = !masterData.isEmpty();

            if (noItemsLeft && itemsAdded) {
                saveButton.setDisable(false);
            } else {
                saveButton.setDisable(true);
            }
        } else if (!validManu) {
            showAlertError("Error", "Manufacturing date must be at the past.");
        } else {
            showAlertError("Error", "Manufacturing date must be before Expiration date.");
        }
    }

    private void loadOut() {
        saved.setValue(!saved.getValue());
    }
    private Service<Void> service;
    private Stage loadingStage;

    @FXML
    private void saveHandle(ActionEvent event) throws IOException {
        service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {

                    @Override
                    protected Void call() throws Exception {
                        updateMessage(Lang.get("Inserting")+"...");
                        int size = 0;

                        for (TableBatchAdapter tableBatch : masterData) {
                            Batch batch = tableBatch.getBatch();
                            List<Item> items = batch.getItems();
                            size += items.size();
                        }
                        int c = 1;
                        for (TableBatchAdapter tableBatch : masterData) {

                            Batch batch = tableBatch.getBatch();
                            batch.insert();
                            List<Item> items = batch.getItems();
                            for (Item item : items) {
                                PurchaseHasItem cti = new PurchaseHasItem(item, purchase);
                                updateMessage(Lang.get("Inserting") + " " + c + " " + Lang.get("of") + " " + size + "...");
                                cti.insert();
                                c++;
                            }

                        }
                        updateMessage(Lang.get("Done!"));
                        purchase.setInProcess(InProcess.RECEIPT);
                        purchase.setProcessStatus(ProcessStatus.DONE);
                        purchase.update();
                        loadOut();

                        return null;
                    }
                };
            }
        };

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/Loading.fxml"));
        Parent rootNew = fxmlLoader.load();
        LoadingController loadController = fxmlLoader.<LoadingController>getController();
        loadingStage = new Stage();
        loadingStage.getIcons().add(SystemImageUtil.getImage(SystemImageUtil.WMSICON_128));
        Scene sceneNew = new Scene(rootNew);
        sceneNew.setFill(null);
        loadingStage.setScene(sceneNew);
        loadingStage.initModality(Modality.APPLICATION_MODAL);
        loadingStage.initStyle(StageStyle.TRANSPARENT);
        loadingStage.show();

        service.setOnSucceeded(e -> {
            closeWindow();
            loadingStage.close();
        });

        loadController.getLoadingLabel().textProperty().bind(service.messageProperty());

        service.restart();
    }

    @FXML
    private void cancelHandle(ActionEvent event) {
        purchase.setInProcess(InProcess.RECEIPT);
        purchase.setProcessStatus(ProcessStatus.UNBEGUN);
        purchase.update();
        closeWindow();
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
        purchase.setInProcess(InProcess.RECEIPT);
        purchase.setProcessStatus(ProcessStatus.ONGOING);
        purchase.update();
        loadOut();
        loadItems();
    }

    private void setTableContextMenu() {
        batchTableView.setRowFactory(tableView -> {
            final TableRow<TableBatchAdapter> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem deleteMenuItem = new MenuItem("Delete");

            deleteMenuItem.setOnAction(event -> deleteBatch());

            contextMenu.getItems().addAll(deleteMenuItem);
            row.contextMenuProperty().bind(Bindings.when(row.emptyProperty()).then((ContextMenu) null).otherwise(contextMenu));

            return row;
        });
    }

    private void showToolTip(Tooltip tooltip, Node node) {
        Window window = rootPane.getScene().getWindow();
        Bounds bounds = node.localToScreen(node.getBoundsInLocal());
        double x = bounds.getMinX();
        double y = bounds.getMaxY();
        tooltip.show(window, x, y);
    }

    private void setItemListener() {
        itemComboBox.valueProperty().addListener((obs, oldV, selected) -> {
            batchNumberTextField.setText("");
            manufacturingDatePicker.setValue(null);
            expirationDatePicker.setValue(null);
            quantitySpinner.setValueFactory(null);
            //quantitySpinner.getEditor().setText("");
            priceValueLabel.setText("");
            remainingValueLabel.setText("");

            addButton.setDisable(true);

            if (selected != null) {

                boolean l = false;

                try {
                    Long.parseLong(batchNumberTextField.getText());
                    l = true;
                } catch (NullPointerException | NumberFormatException ex) {
                    l = false;
                }

                addButton.setDisable(!l);
                double inPrice = selected.getInPrice();
                priceValueLabel.setText(String.format("R$" + " %.2f", inPrice));
                int quantityOri = selected.getQuantity();
                MetaItem metaItem = selected.getMetaItem();
                int sub = 0;
                for (TableBatchAdapter tb : masterData) {
                    MetaItem mi = tb.getBatch().getMetaItem();
                    if (mi == metaItem) {
                        sub += tb.getBatch().getItems().size();
                    }
                }
                if (quantityOri - sub <= 0) {
                    itemComboBox.getItems().remove(selected);
                    System.out.println("OVE");
                } else {
                    quantitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, (quantityOri - sub), 1, 1));
                }

                remainingValueLabel.setText((quantityOri - sub) + "");
            }

        });

    }

    private void loadItems() {
        ObservableList<PurchaseHasMetaItem> items = itemComboBox.getItems();
        items.clear();
        List<PurchaseHasMetaItem> purchaseHasItems = purchase.getPurchaseHasMetaItems();
        for (PurchaseHasMetaItem phi : purchaseHasItems) {
            MetaItem metaItem = phi.getMetaItem();
            int quantityOri = phi.getQuantity();
            int sub = 0;
            for (TableBatchAdapter tb : masterData) {
                MetaItem mi = tb.getBatch().getMetaItem();
                if (mi == metaItem) {
                    sub += tb.getBatch().getItems().size();
                }
            }

            if (quantityOri - sub > 0) {
                items.add(phi);
            }
        }

        if (!items.isEmpty()) {
            itemComboBox.setValue(items.get(0));
        }
    }

    private void setTableColumns() {
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("item"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        manuColumn.setCellValueFactory(new PropertyValueFactory<>("manu"));
        expirationColumn.setCellValueFactory(new PropertyValueFactory<>("exp"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    private void setTable() {
        masterData.clear();
        batchTableView.setItems(masterData);
    }

    private void deleteBatch() {
        masterData.remove(selectedBatch);
        loadItems();
    }

    private void setTableListener() {
        batchTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldV, selectedBatch) -> {
            this.selectedBatch = selectedBatch;

        });
    }

}
