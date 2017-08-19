/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.sistema.Settings;
import br.com.senaimg.wms.model.sistema.enums.CssTheme;
import br.com.senaimg.wms.model.warehouse.stock.item.Brand;
import br.com.senaimg.wms.model.warehouse.stock.item.MetaItem;
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
public class BrandsController implements Initializable {

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
    private Label brandLabel;
    @FXML
    private ComboBox<Brand> brandComboBox;
    @FXML
    private HBox footPane1;
    @FXML
    private Button buttonSave1;

    public BooleanProperty inserted = new SimpleBooleanProperty(false);
    @FXML
    private ListView<Brand> brandsListView;
    private List<Brand> brands;
    @FXML
    private TabPane tabPane;

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
        nameLabel.setText(Lang.get(nameLabel.getText()));
        nameLabel1.setText(Lang.get(nameLabel1.getText()));
        brandLabel.setText(Lang.get(brandLabel.getText()));
        ObservableList<Tab> tabs = tabPane.getTabs();
        for (Tab tab : tabs) {
            tab.setText(Lang.get(tab.getText()));
        }
        buttonSave1.setText(Lang.get(buttonSave1.getText()));
        buttonSave.setText(Lang.get(buttonSave.getText()));
        searchField.setPromptText(Lang.get(searchField.getPromptText()));
    }

    private void setTheme() {
        String path = "br/com/senaimg/wms/view/";
        String css = "brands.css";
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

    private void setComboBoxListener() {
        brandComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                nameTextField1.setText(newValue.getName());
            }
        });
    }

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

    private void load() {
        brands = Brand.list();
        setBrandsList(brands);
        setComboBox(brands);
    }

    @FXML
    private void saveButtonHandle(ActionEvent event) {

        String name = nameTextField.getText();
        boolean exists = false;
        for (Brand brand : brands) {
            if (brand.getName().equalsIgnoreCase(name)) {
                exists = true;
                break;
            }
        }

        if (exists) {
            showAlertError("Error", "Brand already exists.");
        } else if (ValidateFieldUtil.biggerThan(name, 255)) {
            showAlertError("Error", "Brand name is too big.");
        } else if (name.isEmpty()) {
            showAlertError("Error", "Brand name can't be empty.");
        } else {

            Optional<ButtonType> result = showAlertSure("New Brand", "Are you sure you want to insert new Brand?");
            if (result.get() == ButtonType.YES) {

                Brand brand = new Brand(name);
                brand.insert();
                inserted.setValue(Boolean.TRUE);
                inserted.setValue(Boolean.FALSE);
                load();
            }
        }
    }

    private void setBrandsList(List<Brand> brands) {

        if (!brands.isEmpty()) {
            ObservableList<Brand> items = brandsListView.getItems();
            items.clear();
            items.addAll(brands);
        }
    }

    @FXML
    private void saveChangeBrand(ActionEvent event) {
        Brand brand = brandComboBox.getValue();

        String name = nameTextField1.getText();

        boolean exists = false;
        for (Brand b : brands) {

            if (b.getName().equalsIgnoreCase(name) && b != brand) {

                exists = true;
                break;
            }
        }

        if (exists) {
            showAlertError("Error", "Brand already exists.");
        } else if (ValidateFieldUtil.biggerThan(name, 255)) {
            showAlertError("Error", "Brand name is too big.");
        } else if (name.isEmpty()) {
            showAlertError("Error", "Brand name can't be empty.");
        } else {

            Optional<ButtonType> result = showAlertSure("New Brand", "Are you sure you want to insert new Brand?");
            if (result.get() == ButtonType.YES) {

                brand.setName(name);

                brand.update();
                inserted.setValue(Boolean.TRUE);
                inserted.setValue(Boolean.FALSE);
                load();
            }
        }
    }

    private void setComboBox(List<Brand> brands) {

        if (!brands.isEmpty()) {
            ObservableList<Brand> items = brandComboBox.getItems();
            items.clear();
            items.addAll(brands);
            brandComboBox.setValue(items.get(0));
        }

    }

    private void setListContextMenu() {

        ContextMenu contextMenu = new ContextMenu();
        MenuItem deleteMenuItem = new MenuItem(Lang.get("Delete"));
        deleteMenuItem.setOnAction(event -> deleteHandle(event));
        contextMenu.getItems().add(deleteMenuItem);
        brandsListView.setContextMenu(contextMenu);
    }

    private void deleteHandle(ActionEvent event) {
        Optional<ButtonType> result = showAlertSure("Delete Brand", "Are you sure you want to delete Brand?");
        if (result.get() == ButtonType.YES) {
            Brand selectedItem = brandsListView.getSelectionModel().getSelectedItem();
            boolean used = hasDependencies(selectedItem);

            if (!used) {
                selectedItem.delete();
                load();
            } else {
                showAlertError("Error", "Brand is being used.");
            }
        }
    }

    private boolean hasDependencies(Brand selectedItem) {
        List<MetaItem> metas = MetaItem.list();

        boolean used = false;
        for (MetaItem item : metas) {
            if (item.getBrand().getId() == selectedItem.getId()) {
                used = true;
                break;
            }
        }

        return used;
    }

}
