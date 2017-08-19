/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.sistema.Settings;
import br.com.senaimg.wms.model.sistema.enums.CssTheme;
import br.com.senaimg.wms.model.warehouse.stock.item.ItemDimension;
import br.com.senaimg.wms.model.warehouse.stock.item.ItemType;
import br.com.senaimg.wms.model.warehouse.stock.place.Hall;
import br.com.senaimg.wms.model.warehouse.stock.place.Pallet;
import br.com.senaimg.wms.model.warehouse.stock.place.Rack;
import br.com.senaimg.wms.model.warehouse.stock.place.RackFloor;
import br.com.senaimg.wms.util.ValidateFieldUtil;
import br.com.senaimg.wms.view.adapter.TreeMapAdapter;
import br.com.senaimg.wms.view.layout.AlertCustom;
import br.com.senaimg.wms.view.layout.FloorTreeItem;
import br.com.senaimg.wms.view.layout.HallTreeItem;
import br.com.senaimg.wms.view.layout.PalletTreeItem;
import br.com.senaimg.wms.view.layout.RackTreeItem;
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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import org.hibernate.exception.ConstraintViolationException;

/**
 * FXML Controller class
 *
 * @author ÁlefeLucas
 */
public class StockMapController extends TabController implements Initializable {

    @FXML
    private TreeView<TreeMapAdapter> mapTreeView;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab hallPane;
    @FXML
    private Label hallCodeLabel;
    @FXML
    private Label hallItemTypeLabel;
    @FXML
    private TextField hallCodeTextField;
    @FXML
    private ComboBox<ItemType> hallItemTypeComboBox;
    @FXML
    private Button buttonSaveHall;
    @FXML
    private Tab rackPane;
    @FXML
    private Label hallParentLabel;
    @FXML
    private Label hallParentItemTypeLabel;
    @FXML
    private Label rackCodeLabel;
    @FXML
    private Label rackItemTypeLabel;
    @FXML
    private Label parentHallCodeLabel;
    @FXML
    private Label parentHallItemTypeLabel;
    @FXML
    private TextField rackCodeTextField;
    @FXML
    private ComboBox<ItemType> rackItemTypeComboBox;
    @FXML
    private Label palletsLabel;
    @FXML
    private Label heightLabel;
    @FXML
    private Label widthLabel;
    @FXML
    private Label lengthLabel;
    @FXML
    private Label weightLabel;
    @FXML
    private Label palletsFloorLabel;
    @FXML
    private TextField heightTextField;
    @FXML
    private TextField widthTextField;
    @FXML
    private TextField lengthTextField;
    @FXML
    private TextField weightTextField;
    @FXML
    private Spinner<Integer> palletsFloorSpinner;
    @FXML
    private Button buttonSaveRack;
    @FXML
    private Tab floorPane;
    @FXML
    private Label rackParentLabel;
    @FXML
    private Label rackParentItemType;
    @FXML
    private Label floorItemTypeLabel;
    @FXML
    private Label parentRackCodeLabel;
    @FXML
    private Label parentRackItemTypeLabel;
    @FXML
    private ComboBox<ItemType> parentRackItemTypeComboBox;
    @FXML
    private Button buttonSaveFloor;

    private Hall selectedHall;
    private Rack selectedRack;
    private RackFloor selectedFloor;
    @FXML
    private StackPane rootTabPane;
    @FXML
    private BorderPane backgroundPaneTab;
    @FXML
    private Button hallSetAnyButton;
    @FXML
    private Button rackSetAnyButton;
    @FXML
    private Button floorSetAnyButton;

    private boolean validHall;
    private boolean validRack;
    private boolean validFloor;
    private List<Hall> halls;
    @FXML
    private Tooltip hallCodeToolTip;
    @FXML
    private Tooltip rackCodeToolTip;
    @FXML
    private Label tipLabel;
    @FXML
    private Label unit1Label;
    @FXML
    private Label unit2Label;
    @FXML
    private Label unit3Label;
    @FXML
    private Label unit4Label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTheme();
        setTexts();
        palletsFloorSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50, 3, 1));
        setFieldsListener();
        setTreeContext();

        cleanTab();

        setTreeListener();

        load();

    }

    private void setTheme() {
        String path = "br/com/senaimg/wms/view/";
        String css = "stockmap.css";
        String theme;

        Settings s = Settings.getLast();
        if (s.getTheme() == CssTheme.DARK) {
            theme = "stylesheet";
        } else {
            theme = "stylesheetLight";
        }

        ObservableList<String> stylesheets = rootTabPane.getStylesheets();
        stylesheets.clear();
        stylesheets.add(path + theme + "/" + css);
    }

    private void setTexts() {
        ObservableList<Tab> tabs = tabPane.getTabs();
        for (Tab tab : tabs) {
            tab.setText(Lang.get(tab.getText()));
        }
        hallCodeLabel.setText(Lang.get(hallCodeLabel.getText()));
        hallItemTypeLabel.setText(Lang.get(hallItemTypeLabel.getText()));
        hallSetAnyButton.setText(Lang.get(hallSetAnyButton.getText()));
        hallItemTypeComboBox.setPromptText(Lang.get(hallItemTypeComboBox.getPromptText()));
        hallParentLabel.setText(Lang.get(hallParentLabel.getText()));
        hallParentItemTypeLabel.setText(Lang.get(hallParentItemTypeLabel.getText()));
        rackCodeLabel.setText(Lang.get(rackCodeLabel.getText()));
        rackItemTypeLabel.setText(Lang.get(rackItemTypeLabel.getText()));
        buttonSaveHall.setText(Lang.get(buttonSaveHall.getText()));
        rackItemTypeComboBox.setPromptText(Lang.get(rackItemTypeComboBox.getPromptText()));
        rackSetAnyButton.setText(Lang.get(rackSetAnyButton.getText()));
        heightLabel.setText(Lang.get(heightLabel.getText()));
        widthLabel.setText(Lang.get(widthLabel.getText()));
        lengthLabel.setText(Lang.get(lengthLabel.getText()));
        weightLabel.setText(Lang.get(weightLabel.getText()));
        palletsFloorLabel.setText(Lang.get(palletsFloorLabel.getText()));
        buttonSaveRack.setText(Lang.get(buttonSaveRack.getText()));
        floorPane.setText(Lang.get(floorPane.getText()));
        rackParentLabel.setText(Lang.get(rackParentLabel.getText()));
        rackParentItemType.setText(Lang.get(rackParentItemType.getText()));
        floorItemTypeLabel.setText(Lang.get(floorItemTypeLabel.getText()));
        parentRackItemTypeComboBox.setPromptText(Lang.get(parentRackItemTypeComboBox.getPromptText()));
        floorSetAnyButton.setText(Lang.get(floorSetAnyButton.getText()));
        buttonSaveFloor.setText(Lang.get(buttonSaveFloor.getText()));
        tipLabel.setText(Lang.get(tipLabel.getText()));

    }

    private Optional<ButtonType> showAlertSure(String title) {
        Alert alertModify = new AlertCustom(Alert.AlertType.CONFIRMATION, Lang.get("Are you sure you want to insert customer?"), ButtonType.YES, ButtonType.NO);
        alertModify.setTitle(Lang.get(title));
        Optional<ButtonType> result = alertModify.showAndWait();
        return result;
    }

    private void showAlertError() {
        Alert alertInvalid = new AlertCustom(Alert.AlertType.ERROR, Lang.get("Fill the data correctly"), ButtonType.OK);
        alertInvalid.setTitle(Lang.get("Error"));
        alertInvalid.show();
    }

    private void showAlertError(String title, String message) {
        Alert alertInvalid = new AlertCustom(Alert.AlertType.ERROR, Lang.get(message), ButtonType.OK);
        alertInvalid.setTitle(Lang.get(title));
        alertInvalid.show();
    }

    private void cleanTab() {
        ObservableList<Tab> tabs = tabPane.getTabs();
        tabs.clear();
    }

    private ArrayList<MenuItem> getHallItems() {
        MenuItem newHall = new MenuItem(Lang.get("New Hall"));
        newHall.setOnAction(e -> {
            List<Hall> halls = Hall.list();
            Hall h = new Hall(Lang.get("New Hall") + " " + (halls.size() + 1), null);
            h.insert();
            load();

        });

        MenuItem sep = new SeparatorMenuItem();
        MenuItem deleteHall = new MenuItem(Lang.get("Delete Hall"));
        deleteHall.setOnAction(e -> {
            try {
                selectedHall.delete();
            } catch (ConstraintViolationException ex) {
                showAlertError("Error", "DELETE_ERROR");
            }
            cleanMenu();
            load();
        });

        MenuItem newRack = new MenuItem(Lang.get("New Rack"));
        newRack.setOnAction(e -> {
            List<Rack> rack = Rack.list();

            ItemDimension d = new ItemDimension(1000, 1000, 1000, 1000);
            Rack r;

            ItemType itemType = selectedHall.getItemType();
            r = new Rack(Lang.get("New Rack") + " " + (rack.size() + 1), itemType, selectedHall, d, 4);

            r.insert();
            load();

        });

        ArrayList<MenuItem> items = new ArrayList<>();
        items.add(newHall);
        items.add(sep);
        items.add(deleteHall);
        items.add(newRack);

        return items;

    }

    private ArrayList<MenuItem> getOutItems() {
        MenuItem newHall = new MenuItem(Lang.get("New Hall"));
        newHall.setOnAction(e -> {
            List<Hall> halls = Hall.list();
            Hall h = new Hall(Lang.get("New Hall") + " " + (halls.size() + 1), null);

            h.insert();
            load();

        });

        ArrayList<MenuItem> items = new ArrayList<>();
        items.add(newHall);

        return items;

    }

    private ArrayList<MenuItem> getRackItems() {
        MenuItem newHall = new MenuItem(Lang.get("New Floor"));
        newHall.setOnAction(e -> {
            Rack rack = selectedRack;

            int size = rack.getRackFloors().size();
            if (size < Byte.MAX_VALUE - 1) {

                ItemType itemType = rack.getItemType();
                int palletsPerFloor = rack.getPalletsPerFloor();
                ItemDimension capacity = rack.getCapacity();

                ArrayList<Pallet> pallets = new ArrayList<>(palletsPerFloor);
                RackFloor floor = new RackFloor(null, itemType, (byte) (size + 1), rack);
                floor.insert();

                for (int x = 0; x < palletsPerFloor; x++) {
                    Pallet p = new Pallet(capacity, floor);
                    pallets.add(p);
                }

                floor.setPallets(pallets);
                floor.update();
                load();
            }

        });

        MenuItem deleteHall = new MenuItem(Lang.get("Delete Rack"));
        deleteHall.setOnAction(e -> {
            try {
                selectedRack.delete();
            } catch (ConstraintViolationException ex) {
                showAlertError("Error", "DELETE_ERROR");
            }
            cleanMenu();
            load();
        });

        MenuItem deleteFloor = new MenuItem(Lang.get("Delete Last Floor"));
        deleteFloor.setOnAction(e -> {
            List<RackFloor> rackFloors = selectedRack.getRackFloors();
            if (!rackFloors.isEmpty()) {
                RackFloor last = rackFloors.get(rackFloors.size() - 1);
                for (RackFloor r : rackFloors) {
                    if (r.getFloorNumber() > last.getFloorNumber()) {
                        last = r;
                    }
                }
                try {
                    last.delete();
                } catch (ConstraintViolationException ex) {
                    showAlertError("Error", "DELETE_ERROR");
                }
                cleanMenu();
                load();
            }

        });

        ArrayList<MenuItem> items = new ArrayList<>();
        items.add(newHall);
        items.add(deleteHall);
        items.add(deleteFloor);

        return items;

    }

    private ContextMenu menu;

    private void cleanMenu() {
        menu.getItems().clear();
    }

    private void setTreeContext() {
        List<MenuItem> hallItems = getHallItems();

        List<MenuItem> rackItems = getRackItems();
        List<MenuItem> outItems = getOutItems();

        menu = new ContextMenu();
        menu.getItems().addAll(hallItems);

        mapTreeView.setContextMenu(menu);

        mapTreeView.setOnMouseClicked(e -> {
            ObservableList<MenuItem> items = menu.getItems();
            items.clear();

            TreeItem<TreeMapAdapter> selectedItem = mapTreeView.getSelectionModel().getSelectedItem();
            if (selectedItem instanceof HallTreeItem) {
                items.addAll(hallItems);
            } else if (selectedItem instanceof RackTreeItem) {
                items.addAll(rackItems);
            } else if (selectedItem instanceof FloorTreeItem || selectedItem instanceof PalletTreeItem) {

            } else {
                items.addAll(outItems);
            }
        });

    }

    private void setTreeListener() {
        mapTreeView.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                TreeMapAdapter item = newV.getValue();
                Hall hall = item.getHall();
                Rack rack = item.getRack();
                RackFloor floor = item.getFloor();
                Pallet pallet = item.getPallet();

                ObservableList<Tab> tabs = tabPane.getTabs();
                tabs.clear();
                if (hall != null) {
                    System.out.println("\nHALL\n");
                    if (!tabs.contains(hallPane)) {
                        tabs.add(hallPane);

                    }
                    selectedHall = hall;
                    tabPane.getSelectionModel().select(hallPane);

                    hallCodeTextField.setText(hall.getCode());
                    hallItemTypeComboBox.setValue(hall.getItemType());

                } else if (rack != null) {
                    System.out.println("\nRACK\n");
                    if (!tabs.contains(rackPane)) {
                        tabs.add(rackPane);

                    }

                    selectedRack = rack;

                    parentHallCodeLabel.setText(selectedRack.getHall().getCode());
                    ItemType itemType = selectedRack.getHall().getItemType();
                    if (itemType == null) {
                        parentHallItemTypeLabel.setText(Lang.get("Any"));
                    } else {
                        parentHallItemTypeLabel.setText(itemType.getName());
                    }

                    tabPane.getSelectionModel().select(rackPane);

                    rackCodeTextField.setText(rack.getCode());
                    rackItemTypeComboBox.setValue(rack.getItemType());
                    if (rack.getHall().getItemType() == null) {
                        rackSetAnyButton.setDisable(false);
                        rackItemTypeComboBox.setDisable(false);
                    } else {
                        rackSetAnyButton.setDisable(true);
                        rackItemTypeComboBox.setDisable(true);
                    }

                    List<RackFloor> rackFloors = rack.getRackFloors();
                    if (rackFloors != null && !rackFloors.isEmpty()) {
                        RackFloor rackFloor = rackFloors.get(0);
                        List<Pallet> pallets = rackFloor.getPallets();
                        if (pallets != null && !pallets.isEmpty()) {
                            Pallet p = pallets.get(0);
                            ItemDimension capacity = p.getCapacity();

                            double height = capacity.getHeight();
                            double width = capacity.getWidth();
                            double length = capacity.getLength();
                            double weight = capacity.getWeight();

                            heightTextField.setText(height + "");
                            widthTextField.setText(width + "");
                            lengthTextField.setText(length + "");
                            weightTextField.setText(weight + "");
                            palletsFloorSpinner.getValueFactory().setValue(pallets.size());

                        }
                    }

                } else if (floor != null) {
                    System.out.println("\nFLOOR\n");
                    if (!tabs.contains(floorPane)) {
                        tabs.add(floorPane);

                    }
                    selectedFloor = floor;

                    parentRackCodeLabel.setText(selectedFloor.getRack().getCode());
                    ItemType itemType = selectedFloor.getRack().getItemType();
                    if (itemType == null) {
                        parentRackItemTypeLabel.setText(Lang.get("Any"));
                    } else {
                        parentRackItemTypeLabel.setText(itemType.getName());
                    }

                    parentRackItemTypeComboBox.setValue(floor.getItemType());
                    if (floor.getRack().getItemType() == null) {
                        floorSetAnyButton.setDisable(false);
                        parentRackItemTypeComboBox.setDisable(false);
                    } else {
                        floorSetAnyButton.setDisable(true);
                        parentRackItemTypeComboBox.setDisable(true);
                    }
                    tabPane.getSelectionModel().select(floorPane);

                } else if (pallet != null) {
                    System.out.println("\nPALLET\n");
                }
            }
        });
    }

    private void load() {
        cleanTab();
        setTree();

        setTypes(hallItemTypeComboBox);
        setTypes(parentRackItemTypeComboBox);
        setTypes(rackItemTypeComboBox);
    }

    private void setTypes(ComboBox<ItemType> cb) {
        List<ItemType> types = ItemType.list();

        if (!types.isEmpty()) {
            ObservableList<ItemType> items = cb.getItems();
            items.clear();
            items.addAll(types);
            cb.setValue(items.get(0));
        }
    }

    @FXML
    private void saveHallHandle(ActionEvent event) {
        if (validHall) {
            Optional<ButtonType> result = showAlertSure("Save hall?");
            if (result.get() == ButtonType.YES) {
                String code = hallCodeTextField.getText();
                ItemType type = hallItemTypeComboBox.getValue();

                selectedHall.setCode(code);
                selectedHall.setItemType(type);

                List<Rack> racks = selectedHall.getRacks();
                for (Rack rack : racks) {
                    rack.setItemType(type);
                    List<RackFloor> floors = rack.getRackFloors();
                    for (RackFloor floor : floors) {
                        floor.setItemType(type);
                        floor.update();
                    }

                    rack.update();
                }
                selectedHall.update();
                load();
            }
        } else {
            showAlertError();
        }
    }

    @FXML
    private void saveRackHandle(ActionEvent event) {
        if (validRack) {
            Optional<ButtonType> result = showAlertSure("Save rack?");
            if (result.get() == ButtonType.YES) {
                String code = rackCodeTextField.getText();
                ItemType type = rackItemTypeComboBox.getValue();

                selectedRack.setCode(code);
                selectedRack.setItemType(type);
                int size = selectedRack.getRackFloors().size();

                for (RackFloor floor : selectedRack.getRackFloors()) {
                    try {
                        floor.delete();
                    } catch (ConstraintViolationException ex) {
                        showAlertError("Error", "DELETE_ERROR");
                    }
                }

                Integer perFloor = palletsFloorSpinner.getValue();

                String strHeight = heightTextField.getText();
                String strWidth = widthTextField.getText();
                String strLength = lengthTextField.getText();
                String strWeight = weightTextField.getText();
                try {
                    double height = Double.parseDouble(strHeight);
                    double width = Double.parseDouble(strWidth);
                    double length = Double.parseDouble(strLength);
                    double weight = Double.parseDouble(strWeight);

                    ItemDimension capacity = new ItemDimension(weight, height, width, length);
                    capacity.insert();
                    selectedRack.setCapacity(capacity);
                    selectedRack.setPalletsPerFloor(perFloor);

                    ArrayList<RackFloor> floors = new ArrayList<>();
                    for (int y = 0; y < size; y++) {

                        RackFloor rackFloor = new RackFloor(type, (byte) (y + 1), selectedRack);
                        rackFloor.insert();
                        floors.add(rackFloor);

                        for (int x = 0; x < perFloor; x++) {
                            Pallet p = new Pallet(capacity, rackFloor);
                            p.insert();
                        }
                    }

                    selectedRack.setRackFloors(floors);
                    selectedRack.update();
                    load();
                } catch (NumberFormatException | NullPointerException ex) {
                    showAlertError();
                }
            }
        } else {
            showAlertError();
        }
    }

    @FXML
    private void saveFloorHandle(ActionEvent event) {
        Optional<ButtonType> result = showAlertSure("Save hall?");
        if (result.get() == ButtonType.YES) {
            ItemType value = parentRackItemTypeComboBox.getValue();
            selectedFloor.setItemType(value);

            selectedFloor.update();
            load();
        }
    }

    private void setTree() {

        halls = Hall.list();
        TreeItem<TreeMapAdapter> root = new TreeItem<>(new TreeMapAdapter());

        ObservableList<TreeItem<TreeMapAdapter>> rootChildren = root.getChildren();
        for (Hall hall : halls) {
            TreeItem<TreeMapAdapter> hallItem = new HallTreeItem<>(new TreeMapAdapter(hall));
            ObservableList<TreeItem<TreeMapAdapter>> hallChildren = hallItem.getChildren();
            for (Rack rack : hall.getRacks()) {
                TreeItem<TreeMapAdapter> rackItem = new RackTreeItem<>(new TreeMapAdapter(rack));
                ObservableList<TreeItem<TreeMapAdapter>> rackChildren = rackItem.getChildren();
                for (RackFloor floor : rack.getRackFloors()) {
                    TreeItem<TreeMapAdapter> floorItem = new FloorTreeItem<>(new TreeMapAdapter(floor));
                    ObservableList<TreeItem<TreeMapAdapter>> floorChildren = floorItem.getChildren();
                    for (Pallet pallet : floor.getPallets()) {
                        TreeItem<TreeMapAdapter> palletItem = new PalletTreeItem<>(new TreeMapAdapter(pallet));

                        floorChildren.add(palletItem);
                    }

                    rackChildren.add(floorItem);
                }

                hallChildren.add(rackItem);
            }
            rootChildren.add(hallItem);

        }

        mapTreeView.setRoot(root);
    }

    @FXML
    private void hallSetAnyButtonHandle(ActionEvent event) {
        hallItemTypeComboBox.setValue(null);
    }

    @FXML
    private void rackSetAnyButtonHandle(ActionEvent event) {
        rackItemTypeComboBox.setValue(null);
    }

    @FXML
    private void floorSetAnyButtonHandle(ActionEvent event) {
        parentRackItemTypeComboBox.setValue(null);
    }

    private void setFieldsListener() {
        hallCodeTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean validLocationField = ValidateFieldUtil.isValidLocationField(newValue);

            boolean exists = false;
            for (Hall h : halls) {
                if (h.getCode().equalsIgnoreCase(newValue) && h != selectedHall) {
                    exists = true;
                    break;
                }
            }

            validHall = validLocationField && !exists;

        });

        rackCodeTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean validLocationField = ValidateFieldUtil.isValidLocationField(newValue);
            boolean exists = false;
            for (Hall h : halls) {
                for (Rack r : h.getRacks()) {
                    if (r.getCode().equalsIgnoreCase(newValue) && r != selectedRack) {
                        exists = true;
                        break;
                    }
                }
            }

            validRack = validLocationField && !exists;

        });

    }

}
