/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.sistema.Settings;
import br.com.senaimg.wms.model.sistema.enums.CssTheme;
import br.com.senaimg.wms.model.warehouse.agent.Customer;
import br.com.senaimg.wms.model.warehouse.process.Loan;
import br.com.senaimg.wms.model.warehouse.process.LoanHasItem;
import br.com.senaimg.wms.model.warehouse.process.LoanHasMetaItem;
import br.com.senaimg.wms.model.warehouse.process.LoanStatus;
import br.com.senaimg.wms.model.warehouse.stock.Stock;
import br.com.senaimg.wms.model.warehouse.stock.item.MetaItem;
import br.com.senaimg.wms.util.DateUtil;
import br.com.senaimg.wms.util.SystemImageUtil;
import br.com.senaimg.wms.util.ValidateFieldUtil;
import br.com.senaimg.wms.view.adapter.TableLoanHasMetaItemAdapter;
import br.com.senaimg.wms.view.adapter.TableStockAdapter;
import br.com.senaimg.wms.view.layout.Wizard;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author ÁlefeLucas
 */
public class InsertLoanController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private StackPane titlePane;
    @FXML
    private Label titleLabel;
    @FXML
    private HBox footPane;
    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonNext;
    @FXML
    private Button buttonDone;
    @FXML
    private Button buttonCancel;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tab1;
    @FXML
    private Label choosenDataLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label taxLabel;
    @FXML
    private Label taxTypeLabel;
    @FXML
    private Label annotationLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField typeTextField;
    @FXML
    private TextField taxTextField;
    @FXML
    private TextField taxTypeTextField;
    @FXML
    private TextArea annotationTextArea;
    @FXML
    private VBox rightPane;
    @FXML
    private Label imageLabel;
    @FXML
    private Rectangle imageSquare;
    @FXML
    private Tab tab2;
    @FXML
    private Label stockLabel;
    @FXML
    private Label filterLabel;
    @FXML
    private TextField searchProductStock;
    @FXML
    private TableView<TableStockAdapter> stockTableView;
    @FXML
    private TableColumn<TableStockAdapter, String> mnemonicColumn1;
    @FXML
    private TableColumn<TableStockAdapter, String> descriptionColumn;
    @FXML
    private TableColumn<TableStockAdapter, String> quantityColumn;
    @FXML
    private Label addLabel;
    @FXML
    private Button addButton;
    @FXML
    private Label addSelectedItemLabel;
    @FXML
    private Label addSelectedItemValueLabel;
    @FXML
    private Label addQuantityLabel;
    @FXML
    private Spinner<Integer> addSpinner;
    @FXML
    private Label loanLabel1;
    @FXML
    private Label filter2Label;
    @FXML
    private TextField searchProductLoan1;
    @FXML
    private TableColumn<TableLoanHasMetaItemAdapter, String> mnemonicColumn2;
    @FXML
    private TableColumn<TableLoanHasMetaItemAdapter, Integer> quantityColumn2;
    @FXML
    private Button removeButton;
    @FXML
    private Label removeSelectedItemLabel;
    @FXML
    private Label removeSelectedItemValueLabel;
    @FXML
    private Label removeQuantityLabel;
    @FXML
    private Spinner<Integer> removeSpinner;
    @FXML
    private Tab tab3;
    @FXML
    private GridPane formGridPaneSupplier11;
    @FXML
    private Label expLabel;
    @FXML
    private Label loanDateLabel;
    @FXML
    private Label annotationLabel2;
    @FXML
    private TextArea annotationTextArea2;
    @FXML
    private DatePicker loanDatePicker;
    @FXML
    private DatePicker expDatePicker;
    @FXML
    private Label loanLabel2;
    @FXML
    private Label filter3Label;
    @FXML
    private TextField searchProductLoan2;
    @FXML
    private TableColumn<TableLoanHasMetaItemAdapter, String> mnemonicColumn3;
    @FXML
    private TableColumn<TableLoanHasMetaItemAdapter, Integer> quantityColumn3;
    @FXML
    private Tab tab4;
    @FXML
    private Label succesfulLabel;
    @FXML
    private Label employeeLabel;
    @FXML
    private ComboBox<Customer> employeeComboBox;
    @FXML
    private TableView<TableLoanHasMetaItemAdapter> loanTableView1;
    @FXML
    private TableView<TableLoanHasMetaItemAdapter> loanTableView2;

    private Loan loan;
    private ArrayList<LoanHasMetaItem> lhmis;
    private Wizard wizard;
    private ObservableList<TableStockAdapter> masterDataStock = FXCollections.observableArrayList();
    private ObservableList<TableLoanHasMetaItemAdapter> masterDataLoan = FXCollections.observableArrayList();
    private TableStockAdapter selectedStock;
    private Customer selectedEmployee;
    private List<Stock> allStocksTurnover;
    private List<MetaItem> metaItems;
    private TableLoanHasMetaItemAdapter selectedLoanHasMetaItem;
    public BooleanProperty inserted = new SimpleBooleanProperty(false);
    @FXML
    private Label strategyLabel;
    @FXML
    private RadioButton filoButton;
    @FXML
    private ToggleGroup strategyGroup;
    @FXML
    private RadioButton fifoButton;
    private boolean validAnnotation;
    @FXML
    private Tooltip annotationToolTip;
    @FXML
    private Label removeLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTexts();
        setTheme();

        loan = new Loan();
        lhmis = new ArrayList<>();
        addSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1, 1));
        removeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1, 1));
        wizard = new Wizard(buttonNext, buttonBack, buttonDone, buttonCancel, tabPane, tab1, tab2, tab3, tab4);

        preferencesListener();
        setTabListener();
        loanListener();
        setStockClicked();
        setLoan1Clicked();
        setStockColumns();
        setLoan1Columns();
        setLoan2Columns();
        setTableLoan1();
        setTableLoan2();
        load();

        annotationTextArea2.setText(Math.random() + " ");

        annotationTextArea2.setText("");
    }

    private void setTexts() {
        titleLabel.setText(Lang.get(titleLabel.getText()));
        ObservableList<Tab> tabs = tabPane.getTabs();
        for (Tab tab : tabs) {
            tab.setText(Lang.get(tab.getText()));
        }
        employeeLabel.setText(Lang.get(employeeLabel.getText()));
        employeeComboBox.setPromptText(Lang.get(employeeComboBox.getPromptText()));
        imageLabel.setText(Lang.get(imageLabel.getText()));
        choosenDataLabel.setText(Lang.get(choosenDataLabel.getText()));
        nameLabel.setText(Lang.get(nameLabel.getText()));
        typeLabel.setText(Lang.get(typeLabel.getText()));
        taxLabel.setText(Lang.get(taxLabel.getText()));
        taxTypeLabel.setText(Lang.get(taxTypeLabel.getText()));
        annotationLabel.setText(Lang.get(annotationLabel.getText()));
        buttonBack.setText(Lang.get(buttonBack.getText()));
        buttonNext.setText(Lang.get(buttonNext.getText()));
        buttonDone.setText(Lang.get(buttonDone.getText()));
        buttonCancel.setText(Lang.get(buttonCancel.getText()));
        stockLabel.setText(Lang.get(stockLabel.getText()));
        loanLabel1.setText(Lang.get(loanLabel1.getText()));
        filterLabel.setText(Lang.get(filterLabel.getText()));
        filter2Label.setText(Lang.get(filter2Label.getText()));
        searchProductStock.setPromptText(Lang.get(searchProductStock.getPromptText()));
        searchProductLoan1.setPromptText(Lang.get(searchProductLoan1.getPromptText()));

        ObservableList<TableColumn<TableStockAdapter, ?>> columns = stockTableView.getColumns();
        for (TableColumn<TableStockAdapter, ?> column : columns) {
            column.setText(Lang.get(column.getText()));
        }

        ObservableList<TableColumn<TableLoanHasMetaItemAdapter, ?>> columns2 = loanTableView1.getColumns();
        for (TableColumn<TableLoanHasMetaItemAdapter, ?> column : columns2) {
            column.setText(Lang.get(column.getText()));
        }

        addLabel.setText(Lang.get(addLabel.getText()));
        removeLabel.setText(Lang.get(removeLabel.getText()));
        addSelectedItemLabel.setText(Lang.get(addSelectedItemLabel.getText()));
        addSelectedItemValueLabel.setText(Lang.get(addSelectedItemValueLabel.getText()));
        addQuantityLabel.setText(Lang.get(addQuantityLabel.getText()));
        removeSelectedItemLabel.setText(Lang.get(removeSelectedItemLabel.getText()));
        removeSelectedItemValueLabel.setText(Lang.get(removeSelectedItemValueLabel.getText()));
        removeQuantityLabel.setText(Lang.get(removeQuantityLabel.getText()));
        addButton.setText(Lang.get(addButton.getText()));
        removeButton.setText(Lang.get(removeButton.getText()));
        loanDateLabel.setText(Lang.get(loanDateLabel.getText()));
        expLabel.setText(Lang.get(expLabel.getText()));
        annotationLabel2.setText(Lang.get(annotationLabel2.getText()));
        strategyLabel.setText(Lang.get(strategyLabel.getText()));
        filoButton.setText(Lang.get(filoButton.getText()));
        fifoButton.setText(Lang.get(fifoButton.getText()));
        loanLabel2.setText(Lang.get(loanLabel2.getText()));
        filter3Label.setText(Lang.get(filter3Label.getText()));
        searchProductLoan2.setPromptText(Lang.get(searchProductLoan2.getPromptText()));
        ObservableList<TableColumn<TableLoanHasMetaItemAdapter, ?>> columns3 = loanTableView2.getColumns();
        for (TableColumn<TableLoanHasMetaItemAdapter, ?> column : columns3) {
            column.setText(Lang.get(column.getText()));
        }
        succesfulLabel.setText(Lang.get(succesfulLabel.getText()));
    }

    private void setTheme() {
        String path = "br/com/senaimg/wms/view/";
        String css = "insertloan.css";
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

    private void setLoan1Clicked() {
        loanTableView1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedLoanHasMetaItem) -> {
            if (selectedLoanHasMetaItem == null) {
                this.selectedLoanHasMetaItem = null;
                removeButton.setDisable(true);
                removeSelectedItemValueLabel.setText(Lang.get("No Item Selected"));
            } else {
                this.selectedLoanHasMetaItem = selectedLoanHasMetaItem;
                removeButton.setDisable(false);
                removeSelectedItemValueLabel.setText(selectedLoanHasMetaItem.getMnemonic());

                int quantity = selectedLoanHasMetaItem.getQuantity();
                removeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, quantity, 1, 1));

            }
        });
    }

    private void setTableLoan1() {
        masterDataLoan.clear();
        loanTableView1.setItems(setSearchLoan1());
    }

    private void setTableLoan2() {
        masterDataLoan.clear();
        loanTableView2.setItems(setSearchLoan2());
    }

    private SortedList<TableLoanHasMetaItemAdapter> setSearchLoan1() {
        FilteredList<TableLoanHasMetaItemAdapter> filteredData = new FilteredList<>(masterDataLoan, p -> true);
        searchProductLoan1.textProperty().addListener((obs, oldV, newV) -> {
            filterLoan(filteredData, newV);
        });

        SortedList<TableLoanHasMetaItemAdapter> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(loanTableView1.comparatorProperty());
        return sortedData;
    }

    private SortedList<TableLoanHasMetaItemAdapter> setSearchLoan2() {
        FilteredList<TableLoanHasMetaItemAdapter> filteredData = new FilteredList<>(masterDataLoan, p -> true);
        searchProductLoan2.textProperty().addListener((obs, oldV, newV) -> {
            filterLoan(filteredData, newV);
        });

        SortedList<TableLoanHasMetaItemAdapter> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(loanTableView2.comparatorProperty());
        return sortedData;
    }

    private void filterLoan(FilteredList<TableLoanHasMetaItemAdapter> filteredData, String newV) {
        filteredData.setPredicate((TableLoanHasMetaItemAdapter t) -> {
            if (newV == null || newV.isEmpty()) {
                return true;
            }

            String filter = newV.toLowerCase();

            if (t.getMnemonic().toLowerCase().contains(filter)) {
                return true;
            }

            return false;
        });
    }

    private void setStockClicked() {
        stockTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldV, selectedStock) -> {
            if (selectedStock == null) {
                this.selectedStock = null;
                addButton.setDisable(true);
                addSelectedItemValueLabel.setText(Lang.get("No Item Selected"));
            } else {
                this.selectedStock = selectedStock;
                addSelectedItemValueLabel.setText(selectedStock.getMnemonic());
                if (selectedStock.getQuantity() > 0) {
                    addSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, selectedStock.getQuantity(), 1, 1));
                    addButton.setDisable(false);
                } else {
                    addSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0, 0, 0));
                    addButton.setDisable(true);
                }
            }
        });
    }

    private void loanListener() {
        masterDataLoan.addListener((ListChangeListener.Change<? extends TableLoanHasMetaItemAdapter> c) -> {
            if (!c.getList().isEmpty()) {
                wizard.stepDone();
            } else {
                wizard.stepUndone();

            }
        });
    }

    private void preferencesListener() {

        loanDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            verifyPreferences();
        });
        expDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            verifyPreferences();
        });

        annotationTextArea2.textProperty().addListener((observable, oldValue, newValue) -> {
            validAnnotation = !ValidateFieldUtil.biggerThan(newValue, 255);

            if (!validAnnotation && !newValue.isEmpty()) {
                annotationToolTip.setText(Lang.get("Annotation must not contain more than 1023 characters."));
                showToolTip(annotationToolTip, annotationTextArea2);
            } else {
                annotationToolTip.setText("");
                annotationToolTip.hide();
            }

            verifyPreferences();
        });

    }

    private void showToolTip(Tooltip tooltip, Node node) {
        Window window = rootPane.getScene().getWindow();
        Bounds bounds = node.localToScreen(node.getBoundsInLocal());
        double x = bounds.getMinX();
        double y = bounds.getMaxY();
        tooltip.show(window, x, y);
    }

    private void setTabListener() {
        tab1.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                Customer value = employeeComboBox.getValue();
                if (value == null) {
                    wizard.stepUndone();
                } else {
                    wizard.stepDone();
                }
            }
        });

        tab2.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (masterDataLoan.isEmpty()) {
                    wizard.stepUndone();
                } else {
                    wizard.stepDone();
                }
            }
        });

        tab3.selectedProperty().addListener((observable, oldValue, newValue) -> {
            verifyPreferences();
        });
        tab4.selectedProperty().addListener((observable, oldValue, newValue) -> {
            buttonBack.setDisable(true);
            buttonCancel.setDisable(true);
        });
    }

    private void verifyPreferences() {
        Date loan = DateUtil.toDate(loanDatePicker.getValue());
        Date exp = DateUtil.toDate(expDatePicker.getValue());

        if (loan != null && exp != null && validAnnotation) {
            wizard.stepDone();

        } else {
            wizard.stepUndone();
        }
    }

    private void setStockColumns() {
        mnemonicColumn1.setCellValueFactory(new PropertyValueFactory<>("mnemonic"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    private void setLoan1Columns() {
        mnemonicColumn2.setCellValueFactory(new PropertyValueFactory<>("mnemonic"));
        quantityColumn2.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    private void setLoan2Columns() {
        mnemonicColumn3.setCellValueFactory(new PropertyValueFactory<>("mnemonic"));
        quantityColumn3.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    @FXML
    private void backHandle(ActionEvent event) {
        wizard.back();
    }

    @FXML
    private void nextHandle(ActionEvent event) throws IOException {
        if (tab3.isSelected()) {

            loan.setLoanDate(DateUtil.toDate(loanDatePicker.getValue()));
            loan.setExpReturnDate(DateUtil.toDate(expDatePicker.getValue()));

            loan.setAnnotations(annotationTextArea2.getText());

            loan.setStatus(LoanStatus.LOANED);
            service = new Service<Void>() {
                @Override
                protected Task<Void> createTask() {
                    return new Task<Void>() {

                        @Override
                        protected Void call() throws Exception {
                            updateMessage(Lang.get("Loading") + "...");

                            loan.insert();
                            List<LoanHasMetaItem> shmis = new ArrayList<>();
                            for (TableLoanHasMetaItemAdapter tshmi : masterDataLoan) {
                                LoanHasMetaItem shmi = tshmi.getLoanHasMetaItem();
                                shmi.insert();
                                shmis.add(shmi);
                            }

                            List<Stock> allStocks = (fifoButton.isSelected() ? Stock.listFifo() : Stock.listFilo());

                            int total = 0;
                            int c = 0;
                            for (LoanHasMetaItem shmi : shmis) {
                                total += shmi.getQuantity();
                            }

                            List<MetaItem> metaItems = getMetaItemsFromLoan(shmis);

                            ArrayList<ArrayList<Stock>> stockses = new ArrayList<>();

                            divideByMetaItem(allStocks, metaItems, stockses);

                            for (ArrayList<Stock> stocks : stockses) {
                                for (LoanHasMetaItem shmi : shmis) {
                                    if (shmi.getMetaItem().getId() == stocks.get(0).getItem().getBatch().getMetaItem().getId()) {
                                        int quantity = shmi.getQuantity();
                                        for (int x = 0; x < quantity; x++) {
                                            Stock stock = stocks.get(x);
                                            LoanHasItem shi = new LoanHasItem(loan, stock.getItem());
                                            shi.insert();
                                            stock.delete();
                                            c++;
                                            updateMessage(Lang.get("Picking") + " " + c + " " + Lang.get("of") + " " + total + "...");
                                        }
                                        break;
                                    }
                                }
                            }

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
                wizard.next();
            });

            loadController.getLoadingLabel().textProperty().bind(service.messageProperty());
            service.restart();

        } else {
            wizard.next();
        }

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

    @FXML
    private void doneHandle(ActionEvent event) {
        closeWindow();
    }

    private void closeWindow() {
        inserted.setValue(Boolean.FALSE);
        inserted.setValue(Boolean.TRUE);
        closed.setValue(!closed.getValue());
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancelHandle(ActionEvent event) {
        closeWindow();
    }
    public BooleanProperty closed = new SimpleBooleanProperty(false);

    @FXML
    private void addHandle(ActionEvent event) {
        TableStockAdapter s = selectedStock;
        MetaItem metaItem = s.getStocks().get(0).getItem().getBatch().getMetaItem();
        double totalPrice = metaItem.getSellingPrice() * addSpinner.getValue();

        LoanHasMetaItem shmi = null;
        TableLoanHasMetaItemAdapter t = null;
        for (TableLoanHasMetaItemAdapter tshmi : masterDataLoan) {
            LoanHasMetaItem saleHasMetaItem = tshmi.getLoanHasMetaItem();
            if (saleHasMetaItem.getMetaItem() == metaItem) {
                shmi = saleHasMetaItem;
                t = tshmi;
                break;
            }
        }

        if (shmi == null) {
            shmi = new LoanHasMetaItem(loan, metaItem, addSpinner.getValue());
            masterDataLoan.add(new TableLoanHasMetaItemAdapter(shmi));
        } else {
            shmi.setQuantity(shmi.getQuantity() + addSpinner.getValue());

            t.setLoanHasMetaItem(shmi);
            refreshLoan();

        }

        s.setQuantity(s.getQuantity() - addSpinner.getValue());
        refreshStock();
        if (s.getQuantity() != 0) {
            addSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, s.getQuantity(), 1, 1));
        }
    }

    @FXML
    private void removeHandle(ActionEvent event) {
        TableLoanHasMetaItemAdapter t = selectedLoanHasMetaItem;
        LoanHasMetaItem saleHasMetaItem = t.getLoanHasMetaItem();

        Integer quantity = removeSpinner.getValue();
        saleHasMetaItem.setQuantity(saleHasMetaItem.getQuantity() - quantity);
        t.setLoanHasMetaItem(saleHasMetaItem);

        for (TableStockAdapter tsa : masterDataStock) {
            if (tsa.getStocks().get(0).getItem().getBatch().getMetaItem() == saleHasMetaItem.getMetaItem()) {
                tsa.setQuantity(tsa.getQuantity() + quantity);
                refreshStock();
                break;

            }
        }

        if (saleHasMetaItem.getQuantity() == 0) {
            masterDataLoan.remove(t);
        }
        refreshLoan();

    }

    private void loadStocks() {
        masterDataStock.clear();

        loadDatabase();

        ArrayList<ArrayList<Stock>> listOfListOfStock = new ArrayList<>(metaItems.size());
        for (MetaItem metaItem : metaItems) {
            listOfListOfStock.add(new ArrayList<>());
        }

        for (int x = 0; x < metaItems.size(); x++) {
            for (Stock stock : allStocksTurnover) {
                if (stock.getItem().getBatch().getMetaItem().getId() == metaItems.get(x).getId()) {
                    listOfListOfStock.get(x).add(stock);
                }
            }
        }

        for (ArrayList<Stock> stocks : listOfListOfStock) {
            if (!stocks.isEmpty()) {
                masterDataStock.add(new TableStockAdapter(stocks));
            }
        }

        stockTableView.setItems(setSearchStock());

    }

    private void loadDatabase() {

        allStocksTurnover = Stock.listTurnover();

        metaItems = MetaItem.listTurnover();

    }

    private SortedList<TableStockAdapter> setSearchStock() {
        FilteredList<TableStockAdapter> filteredData = new FilteredList<>(masterDataStock, p -> true);
        searchProductStock.textProperty().addListener((obs, oldV, newV) -> {
            filterStock(filteredData, newV);
        });

        SortedList<TableStockAdapter> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(stockTableView.comparatorProperty());
        return sortedData;
    }

    private void filterStock(FilteredList<TableStockAdapter> filteredData, String newV) {
        filteredData.setPredicate((TableStockAdapter t) -> {
            if (newV == null || newV.isEmpty()) {
                return true;
            }

            String filter = newV.toLowerCase();

            if (t.getMnemonic().toLowerCase().contains(filter)) {
                return true;
            }

            return false;
        });
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

                            setCustomer();

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

    private void setCustomer() {
        List<Customer> customers = Customer.listEmployees();
        ObservableList<Customer> items = employeeComboBox.getItems();
        items.clear();
        items.addAll(customers);

        employeeComboBox.valueProperty().addListener((obs, oldV, selectedEmployee) -> {

            if (selectedEmployee != null) {

                if (selectedEmployee.getImage() == null) {
                    cleanImage();
                } else {
                    Image img = selectedEmployee.getImage().getImage();
                    setImage(img);

                }
                setFields(selectedEmployee);
                this.selectedEmployee = selectedEmployee;
                this.loan.setCustomer(this.selectedEmployee);
                wizard.stepDone();

            } else {
                this.loan.setCustomer(null);

                cleanImage();
                this.selectedEmployee = null;
            }

        });

        if (!items.isEmpty()) {
            wizard.stepDone();
            employeeComboBox.setValue(items.get(0));
        } else {
            cleanFields();
            selectedEmployee = null;
            wizard.stepUndone();
        }

    }

    private void cleanImage() {
        setImage(SystemImageUtil.getImage(SystemImageUtil.DEFAULT_USER));
    }

    private void setImage(Image image) {
        ImagePattern pattern = new ImagePattern(image);
        imageSquare.setFill(pattern);
    }

    private void setFields(Customer selectedCustomer) {
        nameTextField.setText(selectedCustomer.getName());
        typeTextField.setText(selectedCustomer.getType().toString());
        taxTextField.setText(selectedCustomer.getTaxNumber());
        taxTypeTextField.setText(selectedCustomer.getTaxNumberType());
        annotationTextArea.setText(selectedCustomer.getAnnotation());

    }

    private void cleanFields() {
        nameTextField.setText("");
        typeTextField.setText("");
        taxTextField.setText("");
        taxTypeTextField.setText("");
        annotationTextArea.setText("");
    }

    private void refreshStock() {
        String text = searchProductStock.getText();
        searchProductStock.setText("" + Math.random());
        searchProductStock.setText(text);
    }

    private void refreshLoan() {
        String text = searchProductLoan1.getText();
        searchProductLoan1.setText("" + Math.random());
        searchProductLoan1.setText(text);

        String text2 = searchProductLoan2.getText();
        searchProductLoan2.setText("" + Math.random());
        searchProductLoan2.setText(text2);

    }

    private List<MetaItem> getMetaItemsFromLoan(List<LoanHasMetaItem> shmis) {
        ArrayList<MetaItem> metaItems = new ArrayList<>();
        for (LoanHasMetaItem lhmi : shmis) {
            metaItems.add(lhmi.getMetaItem());
        }
        return metaItems;
    }

}
