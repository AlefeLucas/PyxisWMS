/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.stock.Stock;
import br.com.senaimg.wms.model.warehouse.stock.item.Brand;
import br.com.senaimg.wms.model.warehouse.stock.item.ItemGroup;
import br.com.senaimg.wms.model.warehouse.stock.item.MetaItem;
import br.com.senaimg.wms.util.SystemImageUtil;
import br.com.senaimg.wms.view.adapter.TableStockAdapter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class StockController extends TabController implements Initializable {

    @FXML
    private StackPane rootTabPane;
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
    private TableView<TableStockAdapter> itemTableView;
    @FXML
    private TableColumn<TableStockAdapter, String> mnemonicColumn;
    @FXML
    private TableColumn<TableStockAdapter, String> groupColumn;
    @FXML
    private TableColumn<TableStockAdapter, String> brandColumn;
    @FXML
    private TableColumn<TableStockAdapter, String> descriptionColumn;
    @FXML
    private TableColumn<TableStockAdapter, String> quantityInStockColumn;
    @FXML
    private TableColumn<TableStockAdapter, String> minQuantityColumn;
    @FXML
    private TableColumn<TableStockAdapter, String> maxQuantityColumn;
    @FXML
    private TableColumn<TableStockAdapter, String> totalCostsColumn;
    @FXML
    private TableColumn<TableStockAdapter, String> avgCostColumn;
    @FXML
    private TableColumn<TableStockAdapter, String> profitMarginColumn;
    @FXML
    private TableColumn<TableStockAdapter, String> sellingPriceColumn;
    @FXML
    private TableColumn<TableStockAdapter, String> totalCostOutColumn;
    @FXML
    private TableColumn<TableStockAdapter, String> totalProfitExpectancyColumn;
    @FXML
    private TableColumn<TableStockAdapter, String> avgIndividualProfitExpectancyColumn;
    @FXML
    private TableColumn<TableStockAdapter, String> ean13Column;
    @FXML
    private TableColumn<TableStockAdapter, String> operationTypeColumn;
    @FXML
    private TableColumn<TableStockAdapter, String> itemTypeColumn;
    @FXML
    private TableColumn<TableStockAdapter, String> cautionColumn;
    @FXML
    private TableColumn<TableStockAdapter, String> heightColumn;
    @FXML
    private TableColumn<TableStockAdapter, String> widthColumn;
    @FXML
    private TableColumn<TableStockAdapter, String> weightColumn;
    @FXML
    private TableColumn<TableStockAdapter, String> lengthColumn;

    private ObservableList<TableStockAdapter> masterData = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTexts();
        setFilterComboBox();
        setTableColumn();
        load();
    }

    private void setTexts() {
        filterLabel.setText(Lang.get(filterLabel.getText()));
        searchTextField.setPromptText(Lang.get(searchTextField.getPromptText()));
        groupsLabel.setText(Lang.get(groupsLabel.getText()));
        brandsLabel.setText(Lang.get(brandsLabel.getText()));
        ObservableList<TableColumn<TableStockAdapter, ?>> columns = itemTableView.getColumns();
        for (TableColumn<TableStockAdapter, ?> column : columns) {
            column.setText(Lang.get(column.getText()));
        }
    }

    private Service<Void> service;
    private Stage loadingStage;

    private void load() {
        try {
            service = new Service<Void>() {
                @Override
                protected Task<Void> createTask() {
                    return new Task<Void>() {

                        @Override
                        protected Void call() throws Exception {
                            updateMessage(Lang.get("Loading") + "...");
                            setBrandsList();
                            setGroupTree();
                            loadStocks();

                            return null;
                        }
                    };
                }
            };

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/Loading.fxml"));
            Parent rootNew;

            rootNew = fxmlLoader.load();

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
                loadingStage.close();
            });

            loadController.getLoadingLabel().textProperty().bind(service.messageProperty());
            service.restart();

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private void setFilterComboBox() {
        filterComboBox.getItems().addAll(
                Lang.get("All fields"),
                Lang.get("Mnemonic"),
                Lang.get("Group"),
                Lang.get("Brand"),
                Lang.get("Description"),
                Lang.get("Quantity in Stock"),
                Lang.get("Minimum Quantity"),
                Lang.get("Maximum Quantity"),
                Lang.get("Total Costs"),
                Lang.get("Selling Price"),
                Lang.get("Profit Margin"),
                Lang.get("Average Cost"),
                Lang.get("Total Selling Price"),
                Lang.get("Total Profit Expectancy"),
                Lang.get("Average Individual Profit Expectancy"),
                Lang.get("Bar code (Ean13)"),
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

    private void loadStocks() {
        masterData.clear();
        List<Stock> allStocks = Stock.list();

        List<MetaItem> metaItems = MetaItem.list();
        ArrayList<ArrayList<Stock>> listOfListOfStock = new ArrayList<>(metaItems.size());
        for (MetaItem metaItem : metaItems) {
            listOfListOfStock.add(new ArrayList<>());
        }

        for (int x = 0; x < metaItems.size(); x++) {
            for (Stock stock : allStocks) {
                if (stock.getItem().getBatch().getMetaItem().getId() == metaItems.get(x).getId()) {
                    listOfListOfStock.get(x).add(stock);
                }
            }
        }

        for (ArrayList<Stock> stocks : listOfListOfStock) {
            if (!stocks.isEmpty()) {
                masterData.add(new TableStockAdapter(stocks));
            }
        }

        SortedList<TableStockAdapter> sortedData = setSearch();

        itemTableView.setItems(sortedData);
    }

    private SortedList<TableStockAdapter> setSearch() {
        FilteredList<TableStockAdapter> filteredData = new FilteredList<>(masterData, p -> true);
        searchTextField.textProperty().addListener((obs, oldV, newV) -> {
            filter(filteredData, newV);
        });
        filterComboBox.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filter(filteredData, searchTextField.getText());
        });

        SortedList<TableStockAdapter> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(itemTableView.comparatorProperty());
        return sortedData;
    }

    private void filter(FilteredList<TableStockAdapter> filteredData, String newV) {
        filteredData.setPredicate((TableStockAdapter t) -> {
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
            } else if (fieldFilter.equals(Lang.get("Quantity in Stock"))) {
                if (Integer.toString(t.getQuantity()).toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Minimum Quantity"))) {
                if (t.getMinimum().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Maximum Quantity"))) {
                if (t.getMaximum().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Total Costs"))) {
                if (t.getTotalCosts().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Selling Price"))) {
                if (t.getSellingPrice().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Profit Margin"))) {
                if (t.getProfitMargin().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Total Selling Price"))) {
                if (t.getTotalSellingPrice().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Total Profit Expectancy"))) {
                if (t.getTotalProfit().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Average Individual Profit Expectancy"))) {
                if (t.getAvgIndividualProfit().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals(Lang.get("Bar code (Ean13)"))) {
                if (t.getEan13().toLowerCase().contains(filter)) {
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
                } else if (t.getMinimum().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getMaximum().toLowerCase().contains(filter)) {
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
                } else if (Integer.toString(t.getQuantity()).toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getTotalCosts().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getProfitMargin().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getAvgCost().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getTotalSellingPrice().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getTotalProfit().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getAvgIndividualProfit().toLowerCase().contains(filter)) {
                    return true;
                }

            }

            return false;
        });
    }

    private void setBrandsList() {
        ObservableList<Brand> items = brandsListView.getItems();
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

    private void setTableColumn() {
        mnemonicColumn.setCellValueFactory(new PropertyValueFactory<>("mnemonic"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<>("group"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        quantityInStockColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        minQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("minimum"));
        maxQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("maximum"));
        totalCostsColumn.setCellValueFactory(new PropertyValueFactory<>("totalCosts"));
        sellingPriceColumn.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        profitMarginColumn.setCellValueFactory(new PropertyValueFactory<>("profitMargin"));
        avgCostColumn.setCellValueFactory(new PropertyValueFactory<>("avgCost"));
        totalCostOutColumn.setCellValueFactory(new PropertyValueFactory<>("totalSellingPrice"));
        totalProfitExpectancyColumn.setCellValueFactory(new PropertyValueFactory<>("totalProfit"));
        avgIndividualProfitExpectancyColumn.setCellValueFactory(new PropertyValueFactory<>("avgIndividualProfit"));
        ean13Column.setCellValueFactory(new PropertyValueFactory<>("ean13"));
        operationTypeColumn.setCellValueFactory(new PropertyValueFactory<>("operation"));
        itemTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        cautionColumn.setCellValueFactory(new PropertyValueFactory<>("caution"));
        heightColumn.setCellValueFactory(new PropertyValueFactory<>("height"));
        widthColumn.setCellValueFactory(new PropertyValueFactory<>("width"));
        lengthColumn.setCellValueFactory(new PropertyValueFactory<>("length"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
    }

}
