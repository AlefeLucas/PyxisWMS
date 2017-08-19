/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.sistema.Settings;
import br.com.senaimg.wms.model.sistema.enums.CssTheme;
import br.com.senaimg.wms.model.warehouse.stock.item.ItemType;
import br.com.senaimg.wms.model.warehouse.stock.item.MetaItem;
import br.com.senaimg.wms.model.warehouse.stock.place.Pallet;
import br.com.senaimg.wms.util.ValidateFieldUtil;
import br.com.senaimg.wms.view.layout.AlertCustom;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author ÁlefeLucas
 */
public class ItemTypeController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private BorderPane backgroundPane;
    @FXML
    private TextField searchField;
    @FXML
    private StackPane centerPane;
    @FXML
    private Label nameLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private HBox footPane;
    @FXML
    private Button buttonSave;
    @FXML
    private Label nameLabel1;
    @FXML
    private TextField nameTextField1;
    @FXML
    private ComboBox<ItemType> itemTypeComboBox;
    @FXML
    private HBox footPane1;
    @FXML
    private Button buttonSave1;

    public BooleanProperty inserted = new SimpleBooleanProperty(false);
    @FXML
    private ListView<ItemType> itemTypesListView;
    private List<ItemType> itemTypes;
    @FXML
    private TabPane tabPane;
    @FXML
    private Label typeLabel;

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

    private void setTheme() {
        String path = "br/com/senaimg/wms/view/";
        String css = "itemtype.css";
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTexts();
        setTheme();
        setComboBoxListener();
        setListContextMenu();
        load();
    }

    private void setTexts() {
        searchField.setPromptText(Lang.get(searchField.getPromptText()));
        nameLabel.setText(Lang.get(nameLabel.getText()));
        typeLabel.setText(Lang.get(typeLabel.getText()));
        nameLabel1.setText(Lang.get(nameLabel1.getText()));
        buttonSave1.setText(Lang.get(buttonSave1.getText()));
        buttonSave.setText(Lang.get(buttonSave.getText()));
        ObservableList<Tab> tabs = tabPane.getTabs();
        for (Tab tab : tabs) {
            tab.setText(Lang.get(tab.getText()));
        }
    }

    private void load() {
        itemTypes = ItemType.list();
        setItemTypesList(itemTypes);
        setComboBox(itemTypes);
    }

    @FXML
    private void saveButtonHandle(ActionEvent event) {

        String name = nameTextField.getText();
        boolean exists = false;
        for (ItemType type : itemTypes) {
            if (type.getName().equalsIgnoreCase(name)) {
                exists = true;
                break;
            }
        }

        if (exists) {
            showAlertError("Error", "Item type already exists.");
        } else if (ValidateFieldUtil.biggerThan(name, 255)) {
            showAlertError("Error", "Item type name is too big.");
        } else if (name.isEmpty()) {
            showAlertError("Error", "Item type name can't be empty.");
        } else {

            Optional<ButtonType> result = showAlertSure("New Item Type", "Are you sure you want to insert new Item Type?");
            if (result.get() == ButtonType.YES) {

                ItemType itemType = new ItemType(name);
                itemType.insert();
                inserted.setValue(Boolean.TRUE);
                inserted.setValue(Boolean.FALSE);
                load();
            }
        }
    }

    private void setItemTypesList(List<ItemType> itemTypes) {

        if (!itemTypes.isEmpty()) {
            ObservableList<ItemType> items = itemTypesListView.getItems();
            items.clear();
            items.addAll(itemTypes);
        }
    }

    @FXML
    private void saveChangeItemType(ActionEvent event) {
        ItemType itemType = itemTypeComboBox.getValue();

        String name = nameTextField1.getText();

        boolean exists = false;
        for (ItemType type : itemTypes) {
            if (type.getName().equalsIgnoreCase(name) && type != itemType) {
                exists = true;
                break;
            }
        }

        if (exists) {
            showAlertError("Error", "Item type already exists.");
        } else if (ValidateFieldUtil.biggerThan(name, 255)) {
            showAlertError("Error", "Item type name is too big.");
        } else if (name.isEmpty()) {
            showAlertError("Error", "Item type name can't be empty.");
        } else {

            Optional<ButtonType> result = showAlertSure("New Item Type", "Are you sure you want to insert new Item Type?");
            if (result.get() == ButtonType.YES) {
                itemType.setName(name);
                itemType.update();
                inserted.setValue(Boolean.TRUE);
                inserted.setValue(Boolean.FALSE);
                load();
            }
        }
    }

    private void setComboBox(List<ItemType> itemTypes) {

        if (!itemTypes.isEmpty()) {
            ObservableList<ItemType> items = itemTypeComboBox.getItems();
            items.clear();
            items.addAll(itemTypes);
            itemTypeComboBox.setValue(items.get(0));
        }

    }

    private void setListContextMenu() {

        ContextMenu contextMenu = new ContextMenu();
        MenuItem deleteMenuItem = new MenuItem(Lang.get("Delete"));
        deleteMenuItem.setOnAction(event -> deleteHandle(event));
        contextMenu.getItems().add(deleteMenuItem);
        itemTypesListView.setContextMenu(contextMenu);
    }

    private void deleteHandle(ActionEvent event) {
        Optional<ButtonType> result = showAlertSure("Delete Item Type", "Are you sure you want to delete Item Type?");
        if (result.get() == ButtonType.YES) {
            ItemType selectedItem = itemTypesListView.getSelectionModel().getSelectedItem();
            boolean used = hasDependencies(selectedItem);

            if (!used) {
                selectedItem.delete();
                load();
            } else {
                showAlertError("Error", "Item type is being used.");
            }
        }
    }

    private boolean hasDependencies(ItemType selectedItem) {
        List<MetaItem> metas = MetaItem.list();
        List<Pallet> pallets = Pallet.list();
        boolean used = false;
        for (MetaItem item : metas) {
            if(item.getItemType() == null){
                continue;
            }
            if (item.getItemType().getId() == selectedItem.getId()) {
                used = true;
                break;
            }
        }
        for (Pallet pallet : pallets) {
            if (pallet.getFloor().getItemType().getId() == selectedItem.getId()) {
                used = true;
                break;
            }
        }
        return used;
    }

    private void setComboBoxListener() {
        itemTypeComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                nameTextField1.setText(newValue.getName());
            }
        });
    }

}
