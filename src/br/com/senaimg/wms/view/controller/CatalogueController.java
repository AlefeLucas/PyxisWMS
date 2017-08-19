/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.agent.Catalogue;
import br.com.senaimg.wms.model.warehouse.agent.Supplier;
import br.com.senaimg.wms.model.warehouse.stock.item.MetaItem;
import br.com.senaimg.wms.view.adapter.TableCatalogueAdapter;
import br.com.senaimg.wms.view.layout.AlertCustom;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class CatalogueController extends TabController implements Initializable {

    @FXML
    private StackPane rootTabPane;
    @FXML
    private VBox leftPane;
    @FXML
    private Label supplierLabel;
    @FXML
    private ComboBox<Supplier> supplierComboBox;
    @FXML
    private Label addLabel;
    @FXML
    private Label addItemLabel;
    @FXML
    private Label addPriceLabel;
    @FXML
    private TextField addPriceTextField;
    @FXML
    private ComboBox<MetaItem> addItemComboBox;
    @FXML
    private Button addButton;
    @FXML
    private Label editLabel;
    @FXML
    private Label editItemLabel;
    @FXML
    private Label editPriceLabel;
    @FXML
    private TextField editPriceTextField;
    @FXML
    private ComboBox<Catalogue> editItemComboBox;
    @FXML
    private Button editButton;
    @FXML
    private TableView<TableCatalogueAdapter> catalogueTableView;
    @FXML
    private TableColumn<TableCatalogueAdapter, String> mnemonicColumn;
    @FXML
    private TableColumn<TableCatalogueAdapter, String> descriptionColumn;
    @FXML
    private TableColumn<TableCatalogueAdapter, String> priceColumn;
    @FXML
    private Button deleteButton;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTexts();
        setEditListener();

        setTableColumns();
        setSuppliers();
        setSupplierListener();

    }

    private void setTexts() {
        supplierLabel.setText(Lang.get(supplierLabel.getText()));
        addLabel.setText(Lang.get(addLabel.getText()));

        supplierComboBox.setPromptText(Lang.get(supplierComboBox.getPromptText()));
        addItemLabel.setText(Lang.get(addItemLabel.getText()));
        addPriceLabel.setText(Lang.get(addPriceLabel.getText()));
        addButton.setText(Lang.get(addButton.getText()));
        editButton.setText(Lang.get(editButton.getText()));
        editLabel.setText(Lang.get(editLabel.getText()));
        editItemLabel.setText(Lang.get(editItemLabel.getText()));
        editPriceLabel.setText(Lang.get(editPriceLabel.getText()));
        deleteButton.setText(Lang.get(deleteButton.getText()));

        ObservableList<TableColumn<TableCatalogueAdapter, ?>> columns = catalogueTableView.getColumns();
        for (TableColumn<TableCatalogueAdapter, ?> column : columns) {
            column.setText(Lang.get(column.getText()));
        }
    }

    private void setEditListener() {
        editItemComboBox.valueProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                editPriceTextField.setText(newV.getPrice() + "");
            }
        });
    }

    private void setSupplierListener() {
        supplierComboBox.valueProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                newV.refresh();
                changeSupplier(newV);
            }
        });
    }

    private void changeSupplier(Supplier newV) {
        if (newV != null) {
            loadCatalogue(newV);
            setComboAdd(newV);
            setComboEdit(newV);
        } else if (newV != null) {
            setComboAdd(newV);
        }
    }

    private void setSuppliers() {
        ObservableList<Supplier> items = supplierComboBox.getItems();
        items.clear();
        items.addAll(Supplier.list());
    }

    private void loadCatalogue(Supplier newV) {
        ObservableList<TableCatalogueAdapter> items = catalogueTableView.getItems();
        items.clear();
        if (newV.getCatalogues() != null) {
            for (Catalogue catalogue : newV.getCatalogues()) {
                items.add(new TableCatalogueAdapter(catalogue));
            }
        }
    }

    private void setComboAdd(Supplier newV) {
        ObservableList<MetaItem> items = addItemComboBox.getItems();
        items.clear();
        if (newV.getCatalogues() != null) {
            List<Catalogue> catalogues = newV.getCatalogues();
            List<MetaItem> catItem = new ArrayList<>();

            for (Catalogue c : catalogues) {
                catItem.add(c.getMetaItem());
            }

            List<MetaItem> allItem = MetaItem.list();

            List<MetaItem> resultItem = new ArrayList<>();

            for (MetaItem m : allItem) {
                boolean contains = false;
                for (MetaItem i : catItem) {
                    if (m.getId() == i.getId()) {
                        contains = true;
                        break;
                    }
                }

                if (!contains) {
                    resultItem.add(m);
                }
            }

            items.addAll(resultItem);

            if (!items.isEmpty()) {
                addButton.setDisable(false);
                addItemComboBox.setValue(items.get(0));
            } else {
                addButton.setDisable(true);
                addItemComboBox.setValue(null);
                addItemComboBox.getItems().clear();
                addPriceTextField.setText("");

            }
        }
    }

    private void setComboEdit(Supplier newV) {
        ObservableList<Catalogue> items = editItemComboBox.getItems();
        items.clear();
        if (newV.getCatalogues() != null) {
            items.addAll(newV.getCatalogues());

            if (!items.isEmpty()) {
                editItemComboBox.setValue(items.get(0));
                editButton.setDisable(false);
                deleteButton.setDisable(false);
            } else {
                editButton.setDisable(true);
                deleteButton.setDisable(true);
                editItemComboBox.setValue(null);
                editItemComboBox.getItems().clear();
                editPriceTextField.setText("");
            }
        }
    }

    @FXML
    private void addButtonHandle(ActionEvent event) {
        boolean valid;
        try {
            Double.parseDouble(addPriceTextField.getText());
            valid = true;
        } catch (NumberFormatException | NullPointerException ex) {
            valid = false;
        }

        if (valid) {
            Optional<ButtonType> result = showAlertSure("New Item", "Are you sure you want add item to catalogue?");
            if (result.get() == ButtonType.YES) {
                MetaItem metaItem = addItemComboBox.getValue();
                String strPrice = addPriceTextField.getText();
                double price = Double.parseDouble(strPrice);

                Supplier supplier = supplierComboBox.getValue();

                Catalogue catalogue = new Catalogue(metaItem, supplier, price);
                catalogue.insert();
                refresh();
            }
        } else {
            showAlertError("Error", "Invalid price");
        }
    }

    private void refresh() {
        Supplier supplier = supplierComboBox.getValue();
        supplierComboBox.setValue(null);
        supplierComboBox.setValue(supplier);
    }

    @FXML
    private void editButtonHandle(ActionEvent event) {
        boolean valid;
        try {
            Double.parseDouble(editPriceTextField.getText());
            valid = true;
        } catch (NumberFormatException | NullPointerException ex) {
            valid = false;
        }

        if (valid) {
            Optional<ButtonType> result = showAlertSure("Edit Item", "Are you sure you want save changes?");
            if (result.get() == ButtonType.YES) {
                Catalogue value = editItemComboBox.getValue();
                String strPrice = editPriceTextField.getText();
                double price = Double.parseDouble(strPrice);

                value.setPrice(price);
                value.update();
                refresh();
            }
        } else {
            showAlertError("Error", "Invalid price");
        }
    }

    private void setTableColumns() {
        mnemonicColumn.setCellValueFactory(new PropertyValueFactory<>("mnemonic"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    private void deleteButtonHandle(ActionEvent event) {
        Catalogue value = editItemComboBox.getValue();
        Optional<ButtonType> result = showAlertSure("Delete Item", "Are you sure you want delete item from catalogue?");
        if (result.get() == ButtonType.YES) {
            value.delete();

            refresh();
        }

    }

}
