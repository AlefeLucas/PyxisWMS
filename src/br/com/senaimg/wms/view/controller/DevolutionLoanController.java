/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.sistema.Settings;
import br.com.senaimg.wms.model.sistema.enums.CssTheme;
import br.com.senaimg.wms.model.warehouse.process.Loan;
import br.com.senaimg.wms.model.warehouse.process.LoanHasItem;
import br.com.senaimg.wms.model.warehouse.process.LoanHasMetaItem;
import br.com.senaimg.wms.model.warehouse.process.LoanStatus;
import br.com.senaimg.wms.model.warehouse.stock.Stock;
import br.com.senaimg.wms.model.warehouse.stock.item.Item;
import br.com.senaimg.wms.model.warehouse.stock.item.ItemDimension;
import br.com.senaimg.wms.model.warehouse.stock.item.ItemType;
import br.com.senaimg.wms.model.warehouse.stock.item.MetaItem;
import br.com.senaimg.wms.model.warehouse.stock.place.Hall;
import br.com.senaimg.wms.model.warehouse.stock.place.Pallet;
import br.com.senaimg.wms.model.warehouse.stock.place.Rack;
import br.com.senaimg.wms.model.warehouse.stock.place.RackFloor;
import br.com.senaimg.wms.util.SystemImageUtil;
import br.com.senaimg.wms.view.adapter.TableToStockAdapter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ÁlefeLucas
 */
public class DevolutionLoanController implements Initializable {

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
    private ComboBox<LoanHasMetaItem> itemComboBox;
    @FXML
    private Label remainingLabel;
    @FXML
    private Label remainingValueLabel;
    @FXML
    private Label availablePlacesLabel;
    @FXML
    private Label quantityLabel;
    @FXML
    private Button addButton;
    @FXML
    private Slider quantitySlider;
    @FXML
    private TextField quantityTextField;
    @FXML
    private ComboBox<Hall> hallComboBox;
    @FXML
    private ComboBox<Rack> rackComboBox;
    @FXML
    private ComboBox<RackFloor> floorComboBox;
    @FXML
    private ComboBox<Pallet> palletComboBox;
    @FXML
    private Label stockLabel;
    @FXML
    private TableView<TableToStockAdapter> stockTableView;
    @FXML
    private TableColumn<TableToStockAdapter, String> itemColumn;
    @FXML
    private TableColumn<TableToStockAdapter, String> quantityColumn;
    @FXML
    private TableColumn<TableToStockAdapter, String> palletColumn;
    @FXML
    private HBox footPane;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    private Loan loan;
    public BooleanProperty saved = new SimpleBooleanProperty(false);
    private ObservableList<TableToStockAdapter> masterData = FXCollections.observableArrayList();
    private TableToStockAdapter selectedToStock;
    @FXML
    private Label hallLabel;
    @FXML
    private Label rackLabel;
    @FXML
    private Label floorLabel;
    @FXML
    private Label palletLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTexts();
        setTheme();
        setSlider();
        setTableListener();
        setLocationListeners();
        setItemListener();
        setTableColumns();
        setTable();
        setTableContextMenu();
    }

    private void setTexts() {
        titleLabel.setText(Lang.get(titleLabel.getText()));
        remainingItemsLabel.setText(Lang.get(remainingItemsLabel.getText()));
        itemLabel.setText(Lang.get(itemLabel.getText()));
        remainingLabel.setText(Lang.get(remainingLabel.getText()));
        availablePlacesLabel.setText(Lang.get(availablePlacesLabel.getText()));
        hallLabel.setText(Lang.get(hallLabel.getText()));
        rackLabel.setText(Lang.get(rackLabel.getText()));
        floorLabel.setText(Lang.get(floorLabel.getText()));
        palletLabel.setText(Lang.get(palletLabel.getText()));
        quantityLabel.setText(Lang.get(quantityLabel.getText()));
        addButton.setText(Lang.get(addButton.getText()));
        stockLabel.setText(Lang.get(stockLabel.getText()));
        ObservableList<TableColumn<TableToStockAdapter, ?>> columns = stockTableView.getColumns();
        for (TableColumn<TableToStockAdapter, ?> column : columns) {
            column.setText(Lang.get(column.getText()));
        }

        saveButton.setText(Lang.get(saveButton.getText()));
        cancelButton.setText(Lang.get(cancelButton.getText()));
    }

    private void setTheme() {
        String path = "br/com/senaimg/wms/view/";
        String css = "devolutionloan.css";
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

    private void setTableListener() {
        stockTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldV, selected) -> {
            this.selectedToStock = selected;

        });
    }

    private void setSlider() {
        quantitySlider.setBlockIncrement(1);

        quantitySlider.valueProperty().addListener((obse, oldVa, newV) -> {
            if (newV != null) {
                quantityTextField.setText("" + newV.intValue());
                if (newV.intValue() == 0) {
                    addButton.setDisable(true);
                } else {
                    addButton.setDisable(false);
                }
            }
        });
    }

    @FXML
    private void addHandle(ActionEvent event) {
        Double dou = quantitySlider.getValue();
        int quantity = dou.intValue();

        Pallet pallet = palletComboBox.getValue();
        List<Item> items = selectedItens;

        ArrayList<Stock> stocks = new ArrayList<>(quantity);

        for (int x = 0; x < quantity; x++) {
            Stock stock = new Stock(pallet, items.get(x));
            stocks.add(stock);
        }

        masterData.add(new TableToStockAdapter(stocks));
        loadItems();

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
                        updateMessage(Lang.get("Inserting") + "...");
                        int size = 0;

                        for (TableToStockAdapter tableStock : masterData) {
                            List<Stock> stocks = tableStock.getStocks();
                            size += stocks.size();
                        }

                        int c = 1;
                        for (TableToStockAdapter tableStock : masterData) {
                            List<Stock> stocks = tableStock.getStocks();
                            for (Stock stock : stocks) {
                                updateMessage(Lang.get("Inserting") + " " + c + " " + Lang.get("of") + " " + size + "...");
                                stock.insert();
                                c++;
                            }

                        }
                        updateMessage(Lang.get("Done!"));
                        loan.setStatus(LoanStatus.RETURNED);
                        loan.update();
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
        loan.setStatus(LoanStatus.STORE);
        loan.update();
        closeWindow();
    }

    private void closeWindow() {
        saved.setValue(!saved.getValue());
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    private void setTableColumns() {
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("item"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        palletColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
    }

    private void setTable() {
        masterData.clear();
        stockTableView.setItems(masterData);

    }

    public void setLoan(Loan loan) {
        this.loan = loan;
        loan.setStatus(LoanStatus.STORE);
        loan.update();
        loadOut();
        loadItems();
    }

    private void loadOut() {
        saved.setValue(!saved.getValue());
    }

    private void loadItems() {
        List<LoanHasMetaItem> loanHasMetaItems = loan.getLoanHasMetaItems();
        ObservableList<LoanHasMetaItem> items = itemComboBox.getItems();
        items.clear();
        for (LoanHasMetaItem lhmi : loanHasMetaItems) {

            List<Item> selectedItens = new ArrayList<>();
            selectedItens.clear();
            if (lhmi != null) {
                List<LoanHasItem> lhis = loan.getLoanHasItems();
                for (LoanHasItem lhi : lhis) {
                    Item item = lhi.getItem();
                    if (lhmi.getMetaItem() == item.getBatch().getMetaItem()) {

                        boolean contains = false;
                        for (TableToStockAdapter toS : masterData) {
                            List<Stock> stocks = toS.getStocks();
                            for (Stock stock : stocks) {
                                if (stock.getItem().getId() == item.getId()) {
                                    contains = true;
                                    break;
                                }

                            }

                            if (contains) {
                                break;
                            }
                        }

                        if (!contains) {
                            selectedItens.add(item);
                        }
                    }
                }
            }

            if (!selectedItens.isEmpty()) {

                items.add(lhmi);
            }
        }

        if (!items.isEmpty()) {
            itemComboBox.setValue(items.get(0));
        }
    }

    private void setTableContextMenu() {
        stockTableView.setRowFactory(tableView -> {
            final TableRow<TableToStockAdapter> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem deleteMenuItem = new MenuItem("Delete");

            deleteMenuItem.setOnAction(event -> deleteToStock());

            contextMenu.getItems().addAll(deleteMenuItem);
            row.contextMenuProperty().bind(Bindings.when(row.emptyProperty()).then((ContextMenu) null).otherwise(contextMenu));

            return row;
        });
    }

    List<Item> selectedItens = new ArrayList<>();

    private void setItemListener() {
        itemComboBox.valueProperty().addListener((obs, oldV, selected) -> {

            remainingValueLabel.setText("");
            addButton.setDisable(true);
            hallComboBox.setDisable(true);
            floorComboBox.setDisable(true);
            rackComboBox.setDisable(true);
            palletComboBox.setDisable(true);
            quantitySlider.setMax(0);
            hallComboBox.getItems().clear();

            selectedItens.clear();
            if (selected != null) {
                List<LoanHasItem> lhis = loan.getLoanHasItems();
                for (LoanHasItem lhi : lhis) {
                    Item item = lhi.getItem();
                    if (selected.getMetaItem() == item.getBatch().getMetaItem()) {

                        boolean contains = false;
                        for (TableToStockAdapter toS : masterData) {
                            List<Stock> stocks = toS.getStocks();
                            for (Stock stock : stocks) {
                                if (stock.getItem().getId() == item.getId()) {
                                    contains = true;
                                    break;
                                }

                            }

                            if (contains) {
                                break;
                            }
                        }

                        if (!contains) {
                            selectedItens.add(item);
                        }
                    }
                }

                remainingValueLabel.setText(selectedItens.size() + "");

                quantitySlider.setMin(0);
                quantitySlider.setValue(0);
                quantityTextField.setText("0");

                ItemType itemType = selected.getMetaItem().getItemType();

                setLocationComboBoxes(itemType, selected.getMetaItem());
                saveButton.setDisable(true);
            } else {
                saveButton.setDisable(false);
            }

        });

    }

    ObservableList<Hall> utilHalls = FXCollections.observableArrayList();
    ObservableList<Rack> utilRacks = FXCollections.observableArrayList();
    ObservableList<RackFloor> utilFloors = FXCollections.observableArrayList();
    ObservableList<Pallet> utilPallets = FXCollections.observableArrayList();

    private void setLocationComboBoxes(ItemType type, MetaItem metaItem) {
        List<Hall> halls = Hall.list();

        boolean isValidItem = false;
        boolean isValidType = false;

        for (Hall hall : halls) {
            List<Rack> racks = hall.getRacks();
            for (Rack rack : racks) {
                List<RackFloor> rackFloors = rack.getRackFloors();
                for (RackFloor floor : rackFloors) {
                    List<Pallet> pallets = floor.getPallets();
                    for (Pallet pallet : pallets) {
                        List<Stock> stocks = pallet.getStocks();
                        if (stocks == null || stocks.isEmpty()) {
                            isValidItem = true;
                        } else if (stocks.get(0).getItem().getBatch().getMetaItem() == metaItem) {
                            isValidItem = true;
                        } else {
                            isValidItem = false;
                        }

                        if (pallet.getFloor().getItemType() == null) {
                            if (type == null) {
                                isValidType = true;
                            } else {
                                isValidType = false;
                            }
                        } else if (type == null) {
                            isValidType = false;
                        } else if (pallet.getFloor().getItemType().getId() == type.getId()) {
                            isValidType = true;
                        } else {
                            isValidType = false;
                        }

                        int space = howManyFit(metaItem, pallet);

                        boolean fit = (space > 0 ? true : false);

                        if (isValidType && isValidItem && fit) {

                            utilPallets.add(pallet);
                            if (!utilFloors.contains(pallet.getFloor())) {
                                utilFloors.add(pallet.getFloor());
                            }
                            if (!utilRacks.contains(pallet.getFloor().getRack())) {
                                utilRacks.add(pallet.getFloor().getRack());
                            }
                            if (!utilHalls.contains(pallet.getFloor().getRack().getHall())) {
                                utilHalls.add(pallet.getFloor().getRack().getHall());
                            }
                        }
                    }
                }
            }
        }

        hallComboBox.setItems(utilHalls);
        if (!utilHalls.isEmpty()) {
            hallComboBox.setValue(utilHalls.get(0));
        }
        comboBoxAbility();
    }

    private void comboBoxAbility() {
        ObservableList<Hall> halls = hallComboBox.getItems();
        hallComboBox.setDisable(halls.isEmpty());

        ObservableList<Rack> racks = rackComboBox.getItems();
        rackComboBox.setDisable(racks.isEmpty());

        ObservableList<RackFloor> floors = floorComboBox.getItems();
        floorComboBox.setDisable(floors.isEmpty());

        ObservableList<Pallet> pallets = palletComboBox.getItems();
        palletComboBox.setDisable(pallets.isEmpty());
        //addButton.setDisable(pallets.isEmpty();
    }

    private void setLocationListeners() {
        hallComboBox.valueProperty().addListener((observable, oldValue, selectedHall) -> {
            rackComboBox.getItems().clear();
            if (selectedHall != null) {
                for (Rack rack : selectedHall.getRacks()) {
                    if (utilRacks.contains(rack)) {
                        rackComboBox.getItems().add(rack);
                    }
                }

                if (!rackComboBox.getItems().isEmpty()) {
                    rackComboBox.setValue(rackComboBox.getItems().get(0));
                }
            }

            comboBoxAbility();
        });

        rackComboBox.valueProperty().addListener((observable, oldValue, selectedRack) -> {
            floorComboBox.getItems().clear();
            if (selectedRack != null) {
                for (RackFloor floor : selectedRack.getRackFloors()) {
                    if (utilFloors.contains(floor)) {
                        floorComboBox.getItems().add(floor);
                    }
                }

                if (!floorComboBox.getItems().isEmpty()) {
                    floorComboBox.setValue(floorComboBox.getItems().get(0));
                }
            }

            comboBoxAbility();
        });

        floorComboBox.valueProperty().addListener((observable, oldValue, selectedFloor) -> {
            palletComboBox.getItems().clear();
            if (selectedFloor != null) {
                for (Pallet pallet : selectedFloor.getPallets()) {
                    if (utilPallets.contains(pallet)) {
                        palletComboBox.getItems().add(pallet);
                    }
                }

                if (!palletComboBox.getItems().isEmpty()) {
                    palletComboBox.setValue(palletComboBox.getItems().get(0));
                }
            }

            comboBoxAbility();
        });

        palletComboBox.valueProperty().addListener((observable, oldValue, selectedPallet) -> {
            quantitySlider.setValue(0);
            if (selectedPallet != null && itemComboBox.getValue() != null) {
                //addButton.setDisable(false);
                int suported = howManyFit(itemComboBox.getValue().getMetaItem(), selectedPallet);
                int toAdd = selectedItens.size();
                int maxSlider = (toAdd > suported ? suported : toAdd);

                quantitySlider.setMax(maxSlider);

                if (suported <= 0) {
                    addButton.setDisable(true);
                } else {
                    // addButton.setDisable(false);
                }
            } else {
                addButton.setDisable(true);

            }
        });
    }

    private int howManyFit(MetaItem metaItem, Pallet pallet) {
        ItemDimension capacity = pallet.getCapacity();
        ItemDimension dimension = metaItem.getDimension();

        int[] cases = new int[6];

        cases[0] = howManyFit(capacity, dimension);
        int bigger = cases[0];
        cases[1] = howManyFit(capacity, dimension.rotateXAxis90());
        cases[2] = howManyFit(capacity, dimension.rotateYAxis90());
        cases[3] = howManyFit(capacity, dimension.rotateZAxis90());
        cases[4] = howManyFit(capacity, dimension.rotateXAxis90().rotateYAxis90());
        cases[5] = howManyFit(capacity, dimension.rotateZAxis90().rotateYAxis90());

        for (int x = 0; x < cases.length; x++) {
            if (cases[x] > bigger) {
                bigger = cases[x];
            }
        }

        int size = pallet.getStocks().size();

        for (TableToStockAdapter toS : masterData) {
            List<Stock> stocks = toS.getStocks();
            if (stocks.get(0).getPallet().getId() == pallet.getId()) {
                size += stocks.size();
            }
        }

        return bigger - size;

    }

    private int howManyFit(ItemDimension capacity, ItemDimension dimension) {
        Double dx = capacity.getWidth() / dimension.getWidth();
        Double dy = capacity.getHeight() / dimension.getHeight();
        Double dz = capacity.getLength() / dimension.getLength();
        int x = dx.intValue();
        int y = dy.intValue();
        int z = dz.intValue();
        int qtt = x * y * z;

        double suported = capacity.getWeight();
        double weight = dimension.getWeight();

        Double dw = suported / weight;
        int w = dw.intValue();

        if (qtt > w) {
            return w;
        } else {
            return qtt;
        }
    }

    private void deleteToStock() {
        masterData.remove(selectedToStock);
        loadItems();
    }

}
