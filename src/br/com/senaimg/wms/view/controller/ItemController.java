/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.stock.item.Brand;
import br.com.senaimg.wms.model.warehouse.stock.item.ItemGroup;
import br.com.senaimg.wms.model.warehouse.stock.item.MetaItem;
import br.com.senaimg.wms.view.adapter.TableMetaItemAdapter;
import br.com.senaimg.wms.view.layout.AlertCustom;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
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
public class ItemController extends TabController implements Initializable {

    @FXML
    private StackPane rootTabPane;
    @FXML
    private Button insertButton;
    @FXML
    private Button modifyButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button groupsButton;
    @FXML
    private Label filterLabel;
    @FXML
    private ComboBox<String> filterComboBox;
    @FXML
    private TextField searchTextField;
    @FXML
    private Label groupsLabel;
    @FXML
    private TreeView<ItemGroup> groupsTreeView;
    @FXML
    private Label brandsLabel;
    @FXML
    private ListView<Brand> brandsListView;
    @FXML
    private TableColumn<TableMetaItemAdapter, String> mnemonicColumn;
    @FXML
    private TableColumn<TableMetaItemAdapter, String> groupColumn;
    @FXML
    private TableColumn<TableMetaItemAdapter, String> brandColumn;
    @FXML
    private TableColumn<TableMetaItemAdapter, String> descriptionColumn;
    @FXML
    private TableColumn<TableMetaItemAdapter, String> ean13Column;
    @FXML
    private TableColumn<TableMetaItemAdapter, String> operationTypeColumn;
    @FXML
    private TableColumn<TableMetaItemAdapter, String> minQuantityColumn;
    @FXML
    private TableColumn<TableMetaItemAdapter, String> maxQuantityColumn;
    @FXML
    private TableColumn<TableMetaItemAdapter, String> itemTypeColumn;
    @FXML
    private TableColumn<TableMetaItemAdapter, String> cautionColumn;
    @FXML
    private TableColumn<TableMetaItemAdapter, String> heightColumn;
    @FXML
    private TableColumn<TableMetaItemAdapter, String> widthColumn;
    @FXML
    private TableColumn<TableMetaItemAdapter, String> lengthColumn;
    @FXML
    private TableColumn<TableMetaItemAdapter, String> weightColumn;
    @FXML
    private TableView<TableMetaItemAdapter> itemTableView;
    @FXML
    private Button operationsButton;
    @FXML
    private Button brandsButton;

    private TableMetaItemAdapter selectedItem;
    private ObservableList<TableMetaItemAdapter> masterData = FXCollections.observableArrayList();
    @FXML
    private TableColumn<TableMetaItemAdapter, String> sellingPriceColumn;
    @FXML
    private Button typeButton;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTexts();
        setGroupTree();
        setBrandsList();

        setTableViewClickedHandle();
        setTableViewContextMenu();
        setFilterComboBox();
        setTableColumns();

        loadMetaItems();

    }

    private void setTexts() {
        insertButton.setText(Lang.get(insertButton.getText()));
        modifyButton.setText(Lang.get(modifyButton.getText()));
        deleteButton.setText(Lang.get(deleteButton.getText()));
        filterLabel.setText(Lang.get(filterLabel.getText()));
        groupsButton.setText(Lang.get(groupsButton.getText()));
        operationsButton.setText(Lang.get(operationsButton.getText()));
        brandsButton.setText(Lang.get(brandsButton.getText()));
        typeButton.setText(Lang.get(typeButton.getText()));
        groupsLabel.setText(Lang.get(groupsLabel.getText()));
        brandsLabel.setText(Lang.get(brandsLabel.getText()));
        searchTextField.setPromptText(Lang.get(searchTextField.getPromptText()));
        ObservableList<TableColumn<TableMetaItemAdapter, ?>> columns = itemTableView.getColumns();
        for (TableColumn<TableMetaItemAdapter, ?> column : columns) {
            column.setText(Lang.get(column.getText()));
        }
    }

    private void setTableViewClickedHandle() {
        itemTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedItem) -> {
            if (selectedItem == null) {
                modifyButton.setDisable(true);
                deleteButton.setDisable(true);
                this.selectedItem = null;
            } else {
                this.selectedItem = selectedItem;
                modifyButton.setDisable(false);
                deleteButton.setDisable(false);
            }
        });
    }

    private void setTableViewContextMenu() {
        itemTableView.setRowFactory(tableView -> {
            final TableRow<TableMetaItemAdapter> row = new TableRow<>();
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

    private void setTableColumns() {
        mnemonicColumn.setCellValueFactory(new PropertyValueFactory<>("mnemonic"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<>("group"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        ean13Column.setCellValueFactory(new PropertyValueFactory<>("ean13"));
        sellingPriceColumn.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        minQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("minQuantity"));
        maxQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("maxQuantity"));
        operationTypeColumn.setCellValueFactory(new PropertyValueFactory<>("operation"));
        itemTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        cautionColumn.setCellValueFactory(new PropertyValueFactory<>("caution"));
        heightColumn.setCellValueFactory(new PropertyValueFactory<>("height"));
        widthColumn.setCellValueFactory(new PropertyValueFactory<>("width"));
        lengthColumn.setCellValueFactory(new PropertyValueFactory<>("length"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
    }

    private void setBrandsList() {
        ObservableList<Brand> items = brandsListView.getItems();
        items.clear();
        items.addAll(Brand.list());
    }

    private void setGroupTree() {
        List<ItemGroup> groups = ItemGroup.list();
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
            ItemGroup value = root.getValue();
            value.setName(Lang.get(value.getName()));
            groupsTreeView.setRoot(root);
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

    @FXML
    private void insertButtonHandle(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/InsertItem.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("Insert Item"));
            stage.setResizable(false);
            stage.show();

            InsertItemController controller = fxmlLoader.<InsertItemController>getController();
            controller.inserted.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (newValue) {
                    loadMetaItems();

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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/EditItem.fxml"));

            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("Edit Item"));
            stage.setResizable(false);
            stage.show();

            EditItemController controller = fxmlLoader.<EditItemController>getController();
            controller.setItem(selectedItem.getMetaItem());
            controller.inserted.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (newValue) {
                    loadMetaItems();
                    getHome().relog();
                }
            });

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    private void showAlertError(String title, String message) {
        Alert alertInvalid = new AlertCustom(Alert.AlertType.ERROR, Lang.get(message), ButtonType.OK);
        alertInvalid.setTitle(Lang.get(title));
        alertInvalid.show();
    }
    @FXML
    private void deleteButtonHandle(ActionEvent event) {
        try {
            selectedItem.getMetaItem().delete();
        } catch (ConstraintViolationException ex) {
         showAlertError("Error", "DELETE_ERROR");

        }
        loadMetaItems();
    }

    @FXML
    private void groupButtonHandle(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/ItemGroup.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("Item Group Manager"));
            stage.setResizable(false);
            stage.show();

            ItemGroupController controller = fxmlLoader.<ItemGroupController>getController();
            controller.inserted.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (newValue) {
                    setGroupTree();

                }
            });

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

 

    @FXML
    private void operationsButtonHandle(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/ItemOperations.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("Operation Types"));
            stage.setResizable(false);
            stage.show();

            ItemOperationsController controller = fxmlLoader.<ItemOperationsController>getController();
            controller.inserted.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (newValue) {
                    loadMetaItems();

                }
            });

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    @FXML
    private void brandsButtonHandle(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/Brands.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("Brands"));
            stage.setResizable(false);
            stage.show();

            BrandsController controller = fxmlLoader.<BrandsController>getController();
            controller.inserted.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (newValue) {
                    setBrandsList();
                }
            });

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private void loadMetaItems() {
        masterData.clear();
        System.out.println("\n\n\nCARGOU\n\n\n");
        for (MetaItem customer : MetaItem.list()) {
            masterData.add(new TableMetaItemAdapter(customer));
        }

        SortedList<TableMetaItemAdapter> sortedData = setSearch();

        itemTableView.setItems(sortedData);
    }

    private SortedList<TableMetaItemAdapter> setSearch() {
        FilteredList<TableMetaItemAdapter> filteredData = new FilteredList<>(masterData, p -> true);
        searchTextField.textProperty().addListener((obs, oldV, newV) -> {
            filter(filteredData, newV);
        });
        filterComboBox.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filter(filteredData, searchTextField.getText());
        });

        SortedList<TableMetaItemAdapter> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(itemTableView.comparatorProperty());
        return sortedData;
    }

    private void filter(FilteredList<TableMetaItemAdapter> filteredData, String newV) {
        filteredData.setPredicate((TableMetaItemAdapter t) -> {
            if (newV == null || newV.isEmpty()) {
                return true;
            }

            String filter = newV.toLowerCase();

            String fieldFilter = filterComboBox.getValue();

            if (fieldFilter.equals(Lang.get("Mnemonic"))) {

                if (t.getMnemonic().toLowerCase().contains(filter)) {
                    return true;
                }

            } else if (fieldFilter.equals(Lang.get("Group"))) {
                if (t.getGroup().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Brand"))) {
                if (t.getBrand().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Description"))) {
                if (t.getDescription().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Bar code (Ean13)"))) {
                if (t.getEan13().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Selling Price"))) {
                if (t.getSellingPrice().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Minimum Quantity"))) {
                if (t.getMinQuantity().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Maximum Quantity"))) {
                if (t.getMaxQuantity().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Operation Type"))) {
                if (t.getOperation().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Item Type"))) {
                if (t.getType().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Caution"))) {
                if (t.getCaution().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Height"))) {
                if (t.getHeight().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Width"))) {
                if (t.getWidth().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Length"))) {
                if (t.getLength().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Weight"))) {
                if (t.getWeight().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("All fields"))) {
                if (t.getMnemonic().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getGroup().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getBrand().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getDescription().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getEan13().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getMinQuantity().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getMaxQuantity().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getOperation().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getType().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getCaution().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getHeight().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getWidth().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getLength().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getWeight().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getSellingPrice().toLowerCase().contains(filter)) {
                    return true;
                }

            }

            return false;
        });
    }

    private void setFilterComboBox() {
        filterComboBox.getItems().addAll(
                Lang.get("All fields"),
                Lang.get("Mnemonic"),
                Lang.get("Group"),
                Lang.get("Brand"),
                Lang.get("Description"),
                Lang.get("Bar code (Ean13)"),
                Lang.get("Selling Price"),
                Lang.get("Minimum Quantity"),
                Lang.get("Maximum Quantity"),
                Lang.get("Operation Type"),
                Lang.get("Item Type"),
                Lang.get("Caution"),
                Lang.get("Height"),
                Lang.get("Width"),
                Lang.get("Length"),
                Lang.get("Weight")
        );

        filterComboBox.getSelectionModel().select(0);
    }

    @FXML
    private void typeButtonHandle(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/ItemType.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("Item Type"));
            stage.setResizable(false);
            stage.show();

            ItemTypeController controller = fxmlLoader.<ItemTypeController>getController();
            controller.inserted.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (newValue) {
                    loadMetaItems();
                }
            });

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
