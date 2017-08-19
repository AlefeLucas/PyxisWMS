/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.sistema.Settings;
import br.com.senaimg.wms.model.sistema.enums.CssTheme;
import br.com.senaimg.wms.model.warehouse.process.OutProcess;
import br.com.senaimg.wms.model.warehouse.process.ProcessStatus;
import br.com.senaimg.wms.model.warehouse.process.Sale;
import br.com.senaimg.wms.model.warehouse.process.SaleHasItem;
import br.com.senaimg.wms.model.warehouse.process.SaleHasMetaItem;
import br.com.senaimg.wms.model.warehouse.stock.Stock;
import br.com.senaimg.wms.model.warehouse.stock.item.MetaItem;
import br.com.senaimg.wms.util.SystemImageUtil;
import br.com.senaimg.wms.view.adapter.TablePickSaleAdapter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ÁlefeLucas
 */
public class PickSaleController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private StackPane titlePane;
    @FXML
    private Label titleLabel;
    @FXML
    private HBox footPane;
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonCancel;
    @FXML
    private Label saleLabel;
    @FXML
    private TableView<TablePickSaleAdapter> pickTable;
    @FXML
    private TableColumn<TablePickSaleAdapter, String> mnemonicColumn;
    @FXML
    private TableColumn<TablePickSaleAdapter, String> descriptionColumn;
    @FXML
    private TableColumn<TablePickSaleAdapter, String> sellColumn;
    @FXML
    private TableColumn<TablePickSaleAdapter, String> stockColumn;
    @FXML
    private TableColumn<TablePickSaleAdapter, String> enoughColumn;
    @FXML
    private Label preferencesLabel;
    @FXML
    private Label enoughLabel;
    @FXML
    private Label enoughValueLabel;
    @FXML
    private Label strategyLabel;
    @FXML
    private RadioButton filoButton;
    @FXML
    private ToggleGroup strategyGroup;
    @FXML
    private RadioButton fifoButton;
    ArrayList<ArrayList<Stock>> listOfListOfStock;
    private ObservableList<TablePickSaleAdapter> masterDataPick = FXCollections.observableArrayList();
    private Sale sale;
 public BooleanProperty saved = new SimpleBooleanProperty(false);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTexts();
        setTheme();
        loadStocks();
        setColumns();
        setTable();
        setEnoughListener();

    }
    private void setTexts() {
        titleLabel.setText(Lang.get(titleLabel.getText()));
        saleLabel.setText(Lang.get(saleLabel.getText()));
        ObservableList<TableColumn<TablePickSaleAdapter, ?>> columns = pickTable.getColumns();
        for (TableColumn<TablePickSaleAdapter, ?> column : columns) {
            column.setText(Lang.get(column.getText()));
        }
        preferencesLabel.setText(Lang.get(preferencesLabel.getText()));
        enoughLabel.setText(Lang.get(enoughLabel.getText()));
        enoughValueLabel.setText(Lang.get(enoughValueLabel.getText()));
        strategyLabel.setText(Lang.get(strategyLabel.getText()));
        filoButton.setText(Lang.get(filoButton.getText()));
        fifoButton.setText(Lang.get(fifoButton.getText()));
        buttonSave.setText(Lang.get(buttonSave.getText()));
        buttonCancel.setText(Lang.get(buttonCancel.getText()));
    }
       private void setTheme() {
        String path = "br/com/senaimg/wms/view/";
        String css = "picksale.css";
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

    private void loadOut() {
        saved.setValue(!saved.getValue());
        
    }
    
    
    private void setEnoughListener() {
        isEnough.addListener((obs, oldV, newV) -> {
            if (newV) {
                enoughValueLabel.setText(Lang.get("Yes"));
                buttonSave.setDisable(false);
            } else {
                enoughValueLabel.setText(Lang.get("No"));
                buttonSave.setDisable(true);
            }
        });
        isEnough.setValue(Boolean.TRUE);
        isEnough.setValue(Boolean.FALSE);
    }

    private void setColumns() {
        mnemonicColumn.setCellValueFactory(new PropertyValueFactory<>("mnemonic"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        sellColumn.setCellValueFactory(new PropertyValueFactory<>("sell"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        enoughColumn.setCellValueFactory(new PropertyValueFactory<>("enough"));
    }

    private synchronized void loadStocks() {
        List<Stock> allStocks = Stock.list();
        List<MetaItem> metaItems = MetaItem.list();
        listOfListOfStock = new ArrayList<>(metaItems.size());
        divideByMetaItem(allStocks, metaItems, listOfListOfStock);
        System.out.println("\n\n\n\n\n" + listOfListOfStock.size() + "\n\n\n\n\n\n");
    }

    private void divideByMetaItem(List<Stock> allStocks, List<MetaItem> metaItems, ArrayList<ArrayList<Stock>> stockses) {

        //stockses = new ArrayList<>(metaItems.size());
        for (MetaItem metaItem : metaItems) {
            stockses.add(new ArrayList<>());
        }

        for (int x = 0; x < metaItems.size(); x++) {
            for (Stock stock : allStocks) {
                if (stock.getItem().getBatch().getMetaItem().getId() == metaItems.get(x).getId()) {
                    stockses.get(x).add(stock);
                }
            }
        }
    }

    private Service<Void> service;
    private Stage loadingStage;

    @FXML
    private void doneHandle(ActionEvent event) throws IOException {
        if (isEnough.getValue()) {
            service = new Service<Void>() {
                @Override
                protected Task<Void> createTask() {
                    return new Task<Void>() {

                        @Override
                        protected Void call() throws Exception {
                            updateMessage(Lang.get("Ordering stock by expiration date..."));
                            List<Stock> allStocks = (fifoButton.isSelected() ? Stock.listFifo() : Stock.listFilo());

                            List<SaleHasMetaItem> shmis = sale.getSaleHasMetaItems();
                            int total = 0;
                            int c = 0;
                            for (SaleHasMetaItem shmi : shmis) {
                                total += shmi.getQuantity();
                            }
                            
                            
                            List<MetaItem> metaItems = getMetaItemsFromSale(sale);

                            ArrayList<ArrayList<Stock>> stockses = new ArrayList<>();

                            divideByMetaItem(allStocks, metaItems, stockses);

                            for (ArrayList<Stock> stocks : stockses) {
                                for (SaleHasMetaItem shmi : shmis) {
                                    if (shmi.getMetaItem().getId() == stocks.get(0).getItem().getBatch().getMetaItem().getId()) {
                                        int quantity = shmi.getQuantity();
                                        for (int x = 0; x < quantity; x++) {
                                            Stock stock = stocks.get(x);
                                            SaleHasItem shi = new SaleHasItem(sale, stock.getItem());
                                            shi.insert();
                                            stock.delete();
                                            c++;
                                            updateMessage(Lang.get("Picking") +" " + c +" "+  Lang.get("of") + " " + total + "...");
                                        }
                                        break;
                                    }
                                }
                            }

                            sale.setOutProcess(OutProcess.DISPATCH);
                            sale.setProcessStatus(ProcessStatus.UNBEGUN);
                            sale.update();
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
                loadOut();
                closeWindow();
                loadingStage.close();
            });

            loadController.getLoadingLabel().textProperty().bind(service.messageProperty());

            service.restart();
        }

    }

    private List<MetaItem> getMetaItemsFromSale(Sale sale) {
        List<SaleHasMetaItem> shmis = sale.getSaleHasMetaItems();
        List<MetaItem> metaItems = new ArrayList<>();
        for (SaleHasMetaItem shmi : shmis) {
            if (!metaItems.contains(shmi.getMetaItem())) {
                metaItems.add(shmi.getMetaItem());
            }
        }
        return metaItems;
    }

    @FXML
    private void cancelHandle(ActionEvent event) {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    public synchronized void setSale(Sale sale) {
        this.sale = sale;

        for (SaleHasMetaItem shmi : sale.getSaleHasMetaItems()) {
            masterDataPick.add(new TablePickSaleAdapter(shmi, listOfListOfStock));
        }

        for (TablePickSaleAdapter tps : masterDataPick) {
            if (!tps.isEnough()) {
                isEnough.setValue(false);
                break;
            } else {
                isEnough.setValue(true);
            }
        }

        sale.setOutProcess(OutProcess.PICK);
        sale.setProcessStatus(ProcessStatus.ONGOING);
        sale.update();
    }

    private BooleanProperty isEnough = new SimpleBooleanProperty(false);

    private void setTable() {
        masterDataPick.clear();
        pickTable.setItems(masterDataPick);
    }

}
