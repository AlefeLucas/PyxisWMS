/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.sistema.Settings;
import br.com.senaimg.wms.model.sistema.enums.CssTheme;
import br.com.senaimg.wms.model.warehouse.stock.item.ItemGroup;
import br.com.senaimg.wms.model.warehouse.stock.item.MetaItem;
import br.com.senaimg.wms.util.ValidateFieldUtil;
import br.com.senaimg.wms.view.layout.AlertCustom;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author ÁlefeLucas
 */
public class ItemGroupController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private BorderPane backgroundPane;
    @FXML
    private TreeView<ItemGroup> groupTreeView;
    @FXML
    private Label nameLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label parentLabel;
    @FXML
    private ComboBox<ItemGroup> parentComboBox;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private HBox footPane;
    @FXML
    private Button buttonSave;
    @FXML
    private Label nameLabel1;
    @FXML
    private Label descriptionLabel1;
    @FXML
    private Label parentLabel1;
    @FXML
    private ComboBox<ItemGroup> parentComboBox1;

    @FXML
    private TextField nameTextField1;
    @FXML
    private TextArea descriptionTextArea1;
    @FXML
    private Label groupLabel;
    @FXML
    private ComboBox<ItemGroup> groupComboBox;
    @FXML
    private HBox footPane1;
    @FXML
    private Button buttonSave1;

    public BooleanProperty inserted = new SimpleBooleanProperty(false);
    private List<ItemGroup> groups;
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
        groupLabel.setText(Lang.get(groupLabel.getText()));        
        parentLabel.setText(Lang.get(parentLabel.getText()));        
        descriptionLabel1.setText(Lang.get(descriptionLabel1.getText()));        
        parentLabel1.setText(Lang.get(parentLabel1.getText()));               
        descriptionLabel.setText(Lang.get(descriptionLabel.getText()));        
        nameLabel.setText(Lang.get(nameLabel.getText()));        
        nameLabel1.setText(Lang.get(nameLabel1.getText()));
        buttonSave1.setText(Lang.get(buttonSave1.getText()));
        buttonSave.setText(Lang.get(buttonSave.getText()));
        ObservableList<Tab> tabs = tabPane.getTabs();
        for (Tab tab : tabs) {
            tab.setText(Lang.get(tab.getText()));
        }
       
    }
    
       private void setTheme() {
        String path = "br/com/senaimg/wms/view/";
        String css = "itemgroup.css";
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

    @FXML
    private void saveButtonHandle(ActionEvent event) {

        String name = nameTextField.getText();

        boolean exists = false;
        for (ItemGroup group : groups) {
            if (group.getName().equalsIgnoreCase(name)) {
                exists = true;
                break;
            }
        }

        if (exists) {
            showAlertError("Error", "Group already exists.");
        } else if (ValidateFieldUtil.biggerThan(name, 255)) {
            showAlertError("Error", "Group name is too big.");
        } else if (name.isEmpty()) {
            showAlertError("Error", "Group name can't be empty.");
        } else {

            Optional<ButtonType> result = showAlertSure("New Group", "Are you sure you want to insert new Group?");
            if (result.get() == ButtonType.YES) {

                String description = descriptionTextArea.getText();

                ItemGroup parent = parentComboBox.getValue();

                ItemGroup group = new ItemGroup(name, description, parent);
                group.insert();
                
                load();
            }
        }
    }

    @FXML
    private void saveChangesHandle(ActionEvent event) {

        String name = nameTextField1.getText();

        ItemGroup group = groupComboBox.getValue();

        boolean exists = false;
        for (ItemGroup g : groups) {
            if (g.getName().equalsIgnoreCase(name) && group != g) {
                exists = true;
                break;
            }
        }

        if (exists) {
            showAlertError("Error", "Group already exists.");
        } else if (ValidateFieldUtil.biggerThan(name, 255)) {
            showAlertError("Error", "Group name is too big.");
        } else if (name.isEmpty()) {
            showAlertError("Error", "Group name can't be empty.");
        } else {

            Optional<ButtonType> result = showAlertSure("New Group", "Are you sure you want to insert new Group?");
            if (result.get() == ButtonType.YES) {

                String description = descriptionTextArea1.getText();

                ItemGroup parent = parentComboBox1.getValue();

                group.setName(name);
                group.setDescription(description);
                group.setParent(parent);
                group.update();
                inserted.setValue(Boolean.TRUE);
                inserted.setValue(Boolean.FALSE);
                load();
            }
        }
    }

    private void setGroupTree(List<ItemGroup> groups) {

        ItemGroup rootItem = null;
        for (ItemGroup i : groups) {
            if (i.getParent() == null) {
                rootItem = i;
                break;
            }
        }
        if (!groups.isEmpty()) {
            TreeItem<ItemGroup> root = new TreeItem<>(rootItem);

            setTreeItems(root);

            groupTreeView.setRoot(root);
        }
    }

    private void setTreeItems(TreeItem<ItemGroup> root) {
        ItemGroup rootValue = root.getValue();
        for (ItemGroup childValue : rootValue.getItemGroups()) {
            TreeItem<ItemGroup> child = new TreeItem<>(childValue);

            setTreeItems(child);
            root.getChildren().add(child);
        }
    }

    private void load() {
        groups = ItemGroup.list();
        setGroupTree(groups);

        inserted.setValue(Boolean.TRUE);
                inserted.setValue(Boolean.FALSE);
        setComboBoxEdit(groupComboBox, groups);
        setComboBox(parentComboBox, groups);

    }

    private void setComboBox(ComboBox<ItemGroup> cb, List<ItemGroup> groups) {
        List<ItemGroup> ops = groups;

        if (!ops.isEmpty()) {
            ObservableList<ItemGroup> items = cb.getItems();
            items.clear();
            items.addAll(ops);
            cb.setValue(items.get(0));
        }
    }

    private void setComboBoxEdit(ComboBox<ItemGroup> groupComboBox, List<ItemGroup> groups) {
        List<ItemGroup> ops = groups;

        if (ops.size() > 1) {
            ObservableList<ItemGroup> items = groupComboBox.getItems();
            items.clear();
            for (ItemGroup itemG : ops) {
                if (itemG.getParent() != null) {
                    items.add(itemG);
                }
            }

            groupComboBox.setValue(items.get(0));
        }
    }

    private void setListContextMenu() {

        ContextMenu contextMenu = new ContextMenu();
        MenuItem deleteMenuItem = new MenuItem(Lang.get("Delete"));
        deleteMenuItem.setOnAction(event -> deleteHandle(event));
        contextMenu.getItems().add(deleteMenuItem);
        groupTreeView.setContextMenu(contextMenu);
    }

    private void deleteHandle(ActionEvent event) {
        Optional<ButtonType> result = showAlertSure("Delete Group", "Are you sure you want to delete Group?");
        if (result.get() == ButtonType.YES) {
            ItemGroup selectedItem = groupTreeView.getSelectionModel().getSelectedItem().getValue();
            boolean used = hasDependencies(selectedItem);

            if (!used) {
                selectedItem.delete();
                load();
            } else {
                showAlertError("Error", "Group is being used.");
            }
        }
    }

    private boolean hasDependencies(ItemGroup selectedItem) {
        List<MetaItem> metaItems = selectedItem.getMetaItems();
        if (metaItems.isEmpty()) {
            List<ItemGroup> itemGroups = selectedItem.getItemGroups();
            if (itemGroups.isEmpty()) {
                return false;
            } else {
                boolean has = false;
                for (ItemGroup it : itemGroups) {
                    if (hasDependencies(it)) {
                        has = true;
                    }
                }

                return has;
            }
        } else {
            return true;
        }
    }

    private void setComboBoxListener() {
        groupComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                nameTextField1.setText(newValue.getName());
                descriptionTextArea1.setText(newValue.getDescription());

                ObservableList<ItemGroup> items = parentComboBox1.getItems();
                items.clear();

                for (ItemGroup g : groups) {
                    if (!isSubgroup(g, newValue) && g != newValue) {
                        items.add(g);
                    }
                }
                if (!items.isEmpty()) {
                    parentComboBox1.setValue(newValue.getParent());
                }

            }
        });
    }

    private boolean isSubgroup(ItemGroup item, ItemGroup relative) {

        ArrayList<ItemGroup> childs = new ArrayList<>();
        childs.add(relative);
        verifyTree(relative, childs);

        return childs.contains(item);

    }

    private void verifyTree(ItemGroup relative, ArrayList<ItemGroup> childs) {
        List<ItemGroup> itemGroups = relative.getItemGroups();
        for (ItemGroup i : itemGroups) {
            childs.add(i);
            verifyTree(i, childs);
        }
    }

}
