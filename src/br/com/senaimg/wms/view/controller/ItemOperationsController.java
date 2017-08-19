/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.sistema.Settings;
import br.com.senaimg.wms.model.sistema.enums.CssTheme;
import br.com.senaimg.wms.model.warehouse.stock.item.MetaItem;
import br.com.senaimg.wms.model.warehouse.stock.item.OperationType;
import br.com.senaimg.wms.util.ValidateFieldUtil;
import br.com.senaimg.wms.view.adapter.TableOperationsAdapter;
import br.com.senaimg.wms.view.layout.AlertCustom;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author ÁlefeLucas
 */
public class ItemOperationsController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private BorderPane backgroundPane;
    @FXML
    private TableView<TableOperationsAdapter> OperationTableView;
    @FXML
    private TableColumn<TableOperationsAdapter, String> nameColumn;
    @FXML
    private TableColumn<TableOperationsAdapter, String> turnoverColumn;
    @FXML
    private StackPane centerPane;
    @FXML
    private Label nameLabel;
    @FXML
    private Label turnoverLabel;
    @FXML
    private CheckBox turnoverCheckBox;
    @FXML
    private TextField nameTextField;
    @FXML
    private HBox footPane;
    @FXML
    private Button buttonSave;
    @FXML
    private Label nameLabel1;
    @FXML
    private Label turnoverLabel1;
    @FXML
    private CheckBox turnoverCheckBox1;
    @FXML
    private TextField nameTextField1;
    @FXML
    private Label operationLabel;
    @FXML
    private ComboBox<OperationType> operationComboBox;
    @FXML
    private HBox footPane1;
    @FXML
    private Button buttonSave1;

    public BooleanProperty inserted = new SimpleBooleanProperty(false);
    private List<OperationType> operations;
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
        setTableViewContextMenu();
        setCheckBox(turnoverCheckBox);
        setCheckBox(turnoverCheckBox1);

        setTableColumns();
        load();
    }
    
       private void setTheme() {
        String path = "br/com/senaimg/wms/view/";
        String css = "itemoperations.css";
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
        operationComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                nameTextField1.setText(newValue.getName());
            }
        });
    }

    private void setCheckBox(CheckBox cb) {
        cb.selectedProperty().addListener((obs, oldV, newV) -> {
            if (newV) {
                cb.setText(Lang.get("Yes"));
            } else {
                cb.setText(Lang.get("No"));
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

    @FXML
    private void saveButtonHandle(ActionEvent event) {
        String name = nameTextField.getText();

        boolean exists = false;
        for (OperationType type : operations) {
            if (type.getName().equalsIgnoreCase(name)) {
                exists = true;
                break;
            }
        }

        if (exists) {
            showAlertError("Error", "Operation type already exists.");
        } else if (ValidateFieldUtil.biggerThan(name, 255)) {
            showAlertError("Error", "Operation type name is too big.");
        } else if (name.isEmpty()) {
            showAlertError("Error", "Operation type name can't be empty.");
        } else {

            Optional<ButtonType> result = showAlertSure("New Operation Type", "Are you sure you want to insert new Operation Type?");
            if (result.get() == ButtonType.YES) {
                boolean turnover = turnoverCheckBox.isSelected();

                OperationType op = new OperationType(name, turnover);
                op.insert();
                inserted.setValue(Boolean.TRUE);
                inserted.setValue(Boolean.FALSE);
                load();
            }
        }

    }

    private void setTableViewContextMenu() {
        OperationTableView.setRowFactory(tableView -> {
            final TableRow<TableOperationsAdapter> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem deleteMenuItem = new MenuItem("Delete");
            deleteMenuItem.setOnAction(event -> deleteHandle(event));
            contextMenu.getItems().addAll(deleteMenuItem);
            row.contextMenuProperty().bind(Bindings.when(row.emptyProperty()).then((ContextMenu) null).otherwise(contextMenu));

            return row;
        });
    }

    @FXML
    private void saveChangeOperations(ActionEvent event) {
        OperationType op = operationComboBox.getValue();

        String name = nameTextField1.getText();

        boolean exists = false;
        for (OperationType type : operations) {
            if (type.getName().equalsIgnoreCase(name) && type != op) {
                exists = true;
                break;
            }
        }

        if (exists) {
            showAlertError("Error", "Operation type already exists.");
        } else if (ValidateFieldUtil.biggerThan(name, 255)) {
            showAlertError("Error", "Operation type name is too big.");
        } else if (name.isEmpty()) {
            showAlertError("Error", "Operation type name can't be empty.");
        } else {

            Optional<ButtonType> result = showAlertSure("New Operation Type", "Are you sure you want to insert new Operation Type?");
            if (result.get() == ButtonType.YES) {

                boolean turnover = turnoverCheckBox1.isSelected();

                op.setName(name);
                op.setTurnover(turnover);
                op.update();
                inserted.setValue(Boolean.TRUE);
                inserted.setValue(Boolean.FALSE);
                load();
            }
        }

    }

    private void load() {
        operations = OperationType.list();
        setTable(operations);
        setComboBox(operations);
    }

    private void setTable(List<OperationType> list) {
        ObservableList<TableOperationsAdapter> items = OperationTableView.getItems();
        items.clear();
        for (OperationType op : list) {
            items.add(new TableOperationsAdapter(op));
        }
    }

    private void setTableColumns() {

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        turnoverColumn.setCellValueFactory(new PropertyValueFactory<>("turnover"));
    }

    private void setComboBox(List<OperationType> list) {
        List<OperationType> ops = list;

        if (!ops.isEmpty()) {
            ObservableList<OperationType> items = operationComboBox.getItems();
            items.clear();
            items.addAll(ops);
            operationComboBox.setValue(items.get(0));
        }
    }

    private void deleteHandle(ActionEvent event) {
        Optional<ButtonType> result = showAlertSure("Delete Operation Type", "Are you sure you want to delete Operation Type?");
        if (result.get() == ButtonType.YES) {
            OperationType selectedItem = OperationTableView.getSelectionModel().getSelectedItem().getOperationType();
            boolean used = hasDependencies(selectedItem);

            if (!used) {
                selectedItem.delete();
                load();
            } else {
                showAlertError("Error", "Operation type is being used.");
            }
        }
    }

    private boolean hasDependencies(OperationType selectedItem) {
        List<MetaItem> metas = MetaItem.list();

        boolean used = false;
        for (MetaItem item : metas) {
            if (item.getOperation().getId() == selectedItem.getId()) {
                used = true;
                break;
            }
        }

        return used;
    }

    private void setTexts() {
        turnoverCheckBox.setText(Lang.get(turnoverCheckBox.getText()));        
        turnoverLabel.setText(Lang.get(turnoverLabel.getText()));        
        turnoverCheckBox1.setText(Lang.get(turnoverCheckBox1.getText()));        
        turnoverLabel1.setText(Lang.get(turnoverLabel1.getText()));        
        operationLabel.setText(Lang.get(operationLabel.getText()));        
        nameLabel.setText(Lang.get(nameLabel.getText()));        
        nameLabel1.setText(Lang.get(nameLabel1.getText()));
        buttonSave1.setText(Lang.get(buttonSave1.getText()));
        buttonSave.setText(Lang.get(buttonSave.getText()));
        ObservableList<Tab> tabs = tabPane.getTabs();
        for (Tab tab : tabs) {
            tab.setText(Lang.get(tab.getText()));
        }
        ObservableList<TableColumn<TableOperationsAdapter, ?>> columns = OperationTableView.getColumns();
        for(TableColumn<TableOperationsAdapter, ?> column : columns){
            column.setText(Lang.get(column.getText()));
        }
    }

}
