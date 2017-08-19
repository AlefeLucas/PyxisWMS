/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.location.Address;
import br.com.senaimg.wms.model.location.City;
import br.com.senaimg.wms.model.location.Country;
import br.com.senaimg.wms.model.location.State;
import br.com.senaimg.wms.model.sistema.Settings;
import br.com.senaimg.wms.model.sistema.enums.CssTheme;
import br.com.senaimg.wms.model.warehouse.agent.Customer;
import br.com.senaimg.wms.model.warehouse.process.OutProcess;
import br.com.senaimg.wms.model.warehouse.process.ProcessStatus;
import br.com.senaimg.wms.model.warehouse.process.Sale;
import br.com.senaimg.wms.model.warehouse.process.SaleHasMetaItem;
import br.com.senaimg.wms.model.warehouse.stock.Stock;
import br.com.senaimg.wms.model.warehouse.stock.item.MetaItem;
import br.com.senaimg.wms.util.DateUtil;
import br.com.senaimg.wms.util.SystemImageUtil;
import br.com.senaimg.wms.util.ValidateFieldUtil;
import br.com.senaimg.wms.view.adapter.TableSaleHasMetaItemAdapter;
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
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
public class InsertSaleController implements Initializable {

    @FXML
    private StackPane rootPane;

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
    private Label customerLabel;
    @FXML
    private ComboBox<Customer> customerComboBox;

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
    private Rectangle imageSquare;
    @FXML
    private Tab tab2;
    @FXML
    private TableView<TableStockAdapter> stockTableView;
    @FXML
    private ComboBox<Country> countryComboBox;
    @FXML
    private ComboBox<State> stateComboBox;
    @FXML
    private ComboBox<City> cityComboBox;
    @FXML
    private Tab tab3;
    @FXML
    private Tab tab4;
    @FXML
    private TableColumn<TableStockAdapter, String> descriptionColumn;
    @FXML
    private TableColumn<TableStockAdapter, String> priceColumn;
    @FXML
    private TableColumn<TableStockAdapter, String> mnemonicColumn1;
    @FXML
    private StackPane titlePane;
    @FXML
    private Label titleLabel;
    @FXML
    private HBox footPane;
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
    private VBox rightPane;
    @FXML
    private Label imageLabel;
    @FXML
    private Label stockLabel;
    @FXML
    private Label filterLabel;
    @FXML
    private TextField searchProductStock;
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
    private Label saleLabel1;
    @FXML
    private Label filter2Label;
    @FXML
    private TextField searchProductSale1;
    @FXML
    private TableView<TableSaleHasMetaItemAdapter> saleTableView1;
    @FXML
    private TableColumn<TableSaleHasMetaItemAdapter, String> mnemonicColumn2;
    @FXML
    private TableColumn<TableSaleHasMetaItemAdapter, String> quantityColumn2;
    @FXML
    private TableColumn<TableSaleHasMetaItemAdapter, String> totalPriceColumn;
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
    private Label totalPriceLabel1;
    @FXML
    private Label totalPriceValueLabel;
    @FXML
    private GridPane formGridPaneSupplier11;
    @FXML
    private Label shippingLabel;
    @FXML
    private Label issueLabel;
    @FXML
    private Label paymentLabel;
    @FXML
    private DatePicker issueDatePicker;
    @FXML
    private DatePicker shippingDatePicker;
    @FXML
    private TextArea paymentTextArea;
    @FXML
    private DatePicker deliveryDatePicker;
    @FXML
    private Label deliveryLabel;
    @FXML
    private Label addressTitle;
    @FXML
    private Label addressLine1Label;
    @FXML
    private Label addressLine2Label;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label countryLabel;
    @FXML
    private TextField addressLine1TextField;
    @FXML
    private Tooltip addressLine1ToolTip;
    @FXML
    private Label cityLabel;
    @FXML
    private Label stateLabel;
    @FXML
    private TextField addressLine2TextField;
    @FXML
    private Tooltip addressLine2ToolTip;
    @FXML
    private TextField postalCodeTextField;
    @FXML
    private Tooltip postalCodeToolTip;
    @FXML
    private Label addressLabel;
    @FXML
    private CheckBox useCustomerAddressCheckBox;
    @FXML
    private Label saleLabel2;
    @FXML
    private Label filter3Label;
    @FXML
    private TextField searchProductSale2;
    @FXML
    private TableView<TableSaleHasMetaItemAdapter> saleTableView2;
    @FXML
    private TableColumn<TableSaleHasMetaItemAdapter, String> totalPriceColumn2;
    @FXML
    private Label totalPriceLabel2;
    @FXML
    private Label totalPriceValueLabel2;
    @FXML
    private Label succesfulLabel;
    @FXML
    private Label infoPriceLabel;
    @FXML
    private Label annotationLabel2;
    @FXML
    private TextArea annotationTextArea2;
    @FXML
    private TableColumn<TableSaleHasMetaItemAdapter, String> mnemonicColumn3;
    @FXML
    private TableColumn<TableSaleHasMetaItemAdapter, String> quantityColumn3;
    private TableStockAdapter selectedStock;

    private Sale sale;
    private ArrayList<SaleHasMetaItem> shmis;
    private List<Stock> allStocks;
    private List<MetaItem> metaItems;
    public BooleanProperty inserted = new SimpleBooleanProperty(false);
    private ObservableList<TableStockAdapter> masterDataStock = FXCollections.observableArrayList();
    private ObservableList<TableSaleHasMetaItemAdapter> masterDataSale1 = FXCollections.observableArrayList();
    private Wizard wizard;
    private Customer selectedCustomer;
    private TableSaleHasMetaItemAdapter selectedSaleHasMetaItem;
    private boolean validPayment;
    @FXML
    private Tooltip annotationToolTip;
    @FXML
    private Tooltip paymentToolTip;
    private boolean validAnnotation;
    @FXML
    private Label removeLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTexts();
        setTheme();
        setAddressListener();
        sale = new Sale();
        shmis = new ArrayList<>();
        addSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1, 1));
        removeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1, 1));
        wizard = new Wizard(buttonNext, buttonBack, buttonDone, buttonCancel, tabPane, tab1, tab2, tab3, tab4);
        altAddress = new Address();

        preferencesListener();
        setTabListener();

        setLocationsComboBoxes();
        saleListener();
        setSale2Columns();
        setSale1Columns();
        setTableSale2();
        setTableSale1();
        setStockClicked();
        setStockColumns();
        setSale1Clicked();

        load();

        paymentTextArea.setText(Math.random() + " ");
        annotationTextArea2.setText(Math.random() + " ");
        paymentTextArea.setText("");
        annotationTextArea2.setText("");
    }

    private void setTheme() {
        String path = "br/com/senaimg/wms/view/";
        String css = "insertsale.css";
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

    private void refreshAddress() {
        useCustomerAddressCheckBox.setSelected(false);
        useCustomerAddressCheckBox.setSelected(true);
    }

    private void preferencesListener() {
        addressLine1TextField.textProperty().addListener((observable, oldValue, newValue) -> {
            verifyPreferences();
        });

        postalCodeTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            verifyPreferences();
        });

        issueDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            verifyPreferences();
        });
        shippingDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            verifyPreferences();
        });
        deliveryDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            verifyPreferences();
        });

        paymentTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            validPayment = !ValidateFieldUtil.biggerThan(newValue, 255);

            if (!validPayment && !newValue.isEmpty()) {
                paymentToolTip.setText(Lang.get("Payment must not contain more than 255 characters."));
                showToolTip(paymentToolTip, paymentTextArea);
            } else {
                paymentToolTip.setText("");
                paymentToolTip.hide();
            }

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

    private void saleListener() {
        masterDataSale1.addListener((ListChangeListener.Change<? extends TableSaleHasMetaItemAdapter> c) -> {
            if (!c.getList().isEmpty()) {
                wizard.stepDone();
                refreshTotalPrice();
            } else {
                wizard.stepUndone();
                totalPriceValueLabel.setText(Lang.get("No Item Added"));
                totalPriceValueLabel2.setText(Lang.get("No Item Added"));
            }
        });
    }

    private void refreshTotalPrice() {
        double totalPrice = 0;
        for (TableSaleHasMetaItemAdapter tshmi : masterDataSale1) {
            int quantity = tshmi.getSaleHasMetaItem().getQuantity();
            double price = tshmi.getSaleHasMetaItem().getMetaItem().getSellingPrice();
            totalPrice += quantity * price;
        }
        totalPriceValueLabel2.setText(String.format(Lang.get("R$") + " %.2f", totalPrice));
        totalPriceValueLabel.setText(String.format(Lang.get("R$") + " %.2f", totalPrice));
    }

    @FXML
    private void backHandle(ActionEvent event) {
        wizard.back();
    }

    @FXML
    private void nextHandle(ActionEvent event) {
        if (tab3.isSelected()) {

            sale.setIssueDate(DateUtil.toDate(issueDatePicker.getValue()));
            sale.setShippingDate(DateUtil.toDate(shippingDatePicker.getValue()));
            sale.setDeliveryDate(DateUtil.toDate(deliveryDatePicker.getValue()));
            sale.setAnnotations(annotationTextArea2.getText());
            sale.setPayment(paymentTextArea.getText());
            boolean selected = useCustomerAddressCheckBox.isSelected();
            if (selected) {
                sale.setDeliver(selectedCustomer.getAddress());
            } else {
                String addressLine1 = addressLine1TextField.getText();
                String addressLine2 = addressLine2TextField.getText();
                City city = cityComboBox.getValue();

                String strPostal = postalCodeTextField.getText();
                int postal = Integer.parseInt(strPostal);

                Address address = new Address(addressLine1, addressLine2, postal, city);
                address.insert();
                sale.setDeliver(address);
            }
            sale.setProcessStatus(ProcessStatus.DONE);
            sale.setOutProcess(OutProcess.SALE);

            sale.insert();
            double selling = 0;
            for (TableSaleHasMetaItemAdapter tshmi : masterDataSale1) {
                SaleHasMetaItem shmi = tshmi.getSaleHasMetaItem();
                shmi.insert();
                selling += shmi.getQuantity() * shmi.getMetaItem().getSellingPrice();
            }

            inserted.setValue(Boolean.FALSE);
            inserted.setValue(Boolean.TRUE);
            infoPriceLabel.setText(Lang.get("Selling Price") + ": " + Lang.get("R$") + " " + String.format("%.2f", selling));

        }

        wizard.next();
    }

    @FXML
    private void doneHandle(ActionEvent event) {

        closeWindow();
    }

    private void closeWindow() {
        closed.setValue(true);
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

        SaleHasMetaItem shmi = null;
        TableSaleHasMetaItemAdapter t = null;
        for (TableSaleHasMetaItemAdapter tshmi : masterDataSale1) {
            SaleHasMetaItem saleHasMetaItem = tshmi.getSaleHasMetaItem();
            if (saleHasMetaItem.getMetaItem() == metaItem) {
                shmi = saleHasMetaItem;
                t = tshmi;
                break;
            }
        }

        if (shmi == null) {
            shmi = new SaleHasMetaItem(sale, metaItem, addSpinner.getValue(), totalPrice);
            masterDataSale1.add(new TableSaleHasMetaItemAdapter(shmi));
        } else {
            shmi.setQuantity(shmi.getQuantity() + addSpinner.getValue());

            t.setSaleHasMetaItem(shmi);
            refreshSale();

        }

        s.setQuantity(s.getQuantity() - addSpinner.getValue());
        refreshStock();
        if (s.getQuantity() == 0) {
            addSpinner.setValueFactory(null);
        } else {
            addSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, s.getQuantity(), 1, 1));
        }
    }

    private void refreshStock() {
        String text = searchProductStock.getText();
        searchProductStock.setText("" + Math.random());
        searchProductStock.setText(text);
    }

    @FXML
    private void removeHandle(ActionEvent event) {
        TableSaleHasMetaItemAdapter t = selectedSaleHasMetaItem;
        SaleHasMetaItem saleHasMetaItem = t.getSaleHasMetaItem();

        Integer quantity = removeSpinner.getValue();
        saleHasMetaItem.setQuantity(saleHasMetaItem.getQuantity() - quantity);
        t.setSaleHasMetaItem(saleHasMetaItem);

        for (TableStockAdapter tsa : masterDataStock) {
            if (tsa.getStocks().get(0).getItem().getBatch().getMetaItem() == saleHasMetaItem.getMetaItem()) {
                tsa.setQuantity(tsa.getQuantity() + quantity);
                refreshStock();
                break;

            }
        }

        if (saleHasMetaItem.getQuantity() == 0) {
            masterDataSale1.remove(t);
        }
        refreshSale();

    }

    private void refreshSale() {
        String text = searchProductSale1.getText();
        searchProductSale1.setText("" + Math.random());
        searchProductSale1.setText(text);

        String text2 = searchProductSale2.getText();
        searchProductSale2.setText("" + Math.random());
        searchProductSale2.setText(text2);
        refreshTotalPrice();
    }

    private void setLocationsComboBoxes() {

        setStateListener();

        setCountryListener();

        loadCountries();
    }

    private void setStateListener() {
        stateComboBox.valueProperty().addListener((ObservableValue<? extends State> observable, State oldValue, State state) -> {
            if (state == null || state.getCities() == null || state.getCities().isEmpty()) {

                cityComboBox.setDisable(true);
            } else {
                cityComboBox.setDisable(false);
                List<City> cities = state.getCities();

                ObservableList<City> items = cityComboBox.getItems();
                items.clear();

                items.addAll(cities);

                if (!items.isEmpty()) {
                    cityComboBox.setDisable(false);
                    cityComboBox.setValue(items.get(0));
                    altAddress.setCity(items.get(0));
                } else {
                    cityComboBox.setDisable(true);
                }

            }

        });
    }

    private void setCountryListener() {
        countryComboBox.valueProperty().addListener((ObservableValue<? extends Country> observable, Country oldValue, Country country) -> {
            if (country == null || country.getStates() == null || country.getStates().isEmpty()) {
                stateComboBox.setDisable(true);
                cityComboBox.setDisable(true);
            } else {
                stateComboBox.setDisable(false);
                List<State> states = country.getStates();

                ObservableList<State> items = stateComboBox.getItems();
                items.clear();

                items.addAll(states);

                if (!items.isEmpty()) {
                    stateComboBox.setDisable(false);
                    stateComboBox.setValue(items.get(0));
                } else {
                    stateComboBox.setDisable(true);
                }

            }

        });
    }

    private void loadCountries() {
        List<Country> countries = Country.list();

        ObservableList<Country> items = countryComboBox.getItems();
        items.clear();

        items.addAll(countries);
        if (!items.isEmpty()) {
            countryComboBox.setDisable(false);
            countryComboBox.setValue(items.get(0));
        } else {
            countryComboBox.setDisable(true);
        }
    }

    private void setCustomer() {
        List<Customer> customers = Customer.list();
        ObservableList<Customer> items = customerComboBox.getItems();
        items.clear();
        items.addAll(customers);

        customerComboBox.valueProperty().addListener((obs, oldV, selectedCustomer) -> {

            if (selectedCustomer != null) {
                refreshAddress();
                if (selectedCustomer.getImage() == null) {
                    cleanImage();
                } else {
                    Image img = selectedCustomer.getImage().getImage();
                    setImage(img);

                }
                setFields(selectedCustomer);
                this.selectedCustomer = selectedCustomer;
                this.sale.setCustomer(this.selectedCustomer);
                useCustomerAddressCheckBox.setSelected(false);
                useCustomerAddressCheckBox.setSelected(true);
                wizard.stepDone();
            } else {
                this.sale.setCustomer(null);
                useCustomerAddressCheckBox.setSelected(false);
                cleanImage();
                this.selectedCustomer = null;
            }

        });

        if (!items.isEmpty()) {
            wizard.stepDone();
            customerComboBox.setValue(items.get(0));
        } else {
            cleanFields();
            selectedCustomer = null;
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

    private void verifyPreferences() {
        Date issue = DateUtil.toDate(issueDatePicker.getValue());
        Date shipping = DateUtil.toDate(shippingDatePicker.getValue());
        //Date delivery = DateUtil.toDate(deliveryDatePicker.getValue());

//        String payment = paymentTextArea.getText();
//        String annotation = annotationTextArea2.getText();
        if (issue != null && shipping != null && validAnnotation && validPayment) {
            if (useCustomerAddressCheckBox.isSelected()) {
                wizard.stepDone();
            } else {
                try {
                    String address1 = addressLine1TextField.getText();
                    String strPostal = postalCodeTextField.getText();
                    int postal = Integer.parseInt(strPostal);

                    if (address1 == null || address1.isEmpty()) {
                        wizard.stepUndone();
                    } else {
                        wizard.stepDone();
                    }
                } catch (NumberFormatException ex) {
                    wizard.stepUndone();
                }

            }

        } else {
            wizard.stepUndone();
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
                            updateMessage(Lang.get("Loading")+"...");
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

    private void loadStocks() {
        masterDataStock.clear();
        loadDatabase();
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
                masterDataStock.add(new TableStockAdapter(stocks));
            }
        }

        stockTableView.setItems(setSearchStock());
    }

    private void loadDatabase() {
        allStocks = Stock.list();

        metaItems = MetaItem.list();
    }

    private void setStockColumns() {
        mnemonicColumn1.setCellValueFactory(new PropertyValueFactory<>("mnemonic"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));

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

    private void setSale1Columns() {
        mnemonicColumn2.setCellValueFactory(new PropertyValueFactory<>("mnemonic"));
        quantityColumn2.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    }

    private void setSale2Columns() {
        mnemonicColumn3.setCellValueFactory(new PropertyValueFactory<>("mnemonic"));
        quantityColumn3.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalPriceColumn2.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    }

    private void setTableSale1() {
        masterDataSale1.clear();
        saleTableView1.setItems(setSearchSale1());
    }

    private void setTableSale2() {
        masterDataSale1.clear();
        saleTableView2.setItems(setSearchSale2());
    }

    private SortedList<TableSaleHasMetaItemAdapter> setSearchSale1() {
        FilteredList<TableSaleHasMetaItemAdapter> filteredData = new FilteredList<>(masterDataSale1, p -> true);
        searchProductSale1.textProperty().addListener((obs, oldV, newV) -> {
            filterSale(filteredData, newV);
        });

        SortedList<TableSaleHasMetaItemAdapter> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(saleTableView1.comparatorProperty());
        return sortedData;
    }

    private SortedList<TableSaleHasMetaItemAdapter> setSearchSale2() {
        FilteredList<TableSaleHasMetaItemAdapter> filteredData = new FilteredList<>(masterDataSale1, p -> true);
        searchProductSale2.textProperty().addListener((obs, oldV, newV) -> {
            filterSale(filteredData, newV);
        });

        SortedList<TableSaleHasMetaItemAdapter> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(saleTableView2.comparatorProperty());
        return sortedData;
    }

    private void filterSale(FilteredList<TableSaleHasMetaItemAdapter> filteredData, String newV) {
        filteredData.setPredicate((TableSaleHasMetaItemAdapter t) -> {
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

    private void setSale1Clicked() {
        saleTableView1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedSaleHasMetaItem) -> {
            if (selectedSaleHasMetaItem == null) {
                this.selectedSaleHasMetaItem = null;
                removeButton.setDisable(true);
                removeSelectedItemValueLabel.setText(Lang.get("No Item Selected"));
            } else {
                this.selectedSaleHasMetaItem = selectedSaleHasMetaItem;
                removeButton.setDisable(false);
                removeSelectedItemValueLabel.setText(selectedSaleHasMetaItem.getMnemonic());

                int quantity = selectedSaleHasMetaItem.getQuantity();
                removeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, quantity, 1, 1));

            }
        });
    }

    Address altAddress;

    private void setAddressListener() {
        useCustomerAddressCheckBox.selectedProperty().addListener((obs, oldValue, newValue) -> {

            if (newValue) {
                altAddress.setAddressLine1(addressLine1TextField.getText());
                altAddress.setAddressLine2(addressLine2TextField.getText());
                altAddress.setCity(cityComboBox.getValue());
                String strPostal = postalCodeTextField.getText();
                try {
                    int postal = Integer.parseInt(strPostal);
                    altAddress.setPostalCode(postal);
                } catch (Exception ex) {

                }

                if (selectedCustomer != null) {
                    Address address = selectedCustomer.getAddress();
                    addressLine1TextField.setText(address.getAddressLine1());
                    addressLine2TextField.setText(address.getAddressLine2());
                    postalCodeTextField.setText(address.getPostalCode() + "");
                    countryComboBox.setValue(address.getCity().getState().getCountry());
                    stateComboBox.setValue(address.getCity().getState());
                    cityComboBox.setValue(address.getCity());
                }
                useCustomerAddressCheckBox.setText(Lang.get("Use customer address"));
            } else {
                addressLine1TextField.setText(altAddress.getAddressLine1());
                addressLine2TextField.setText(altAddress.getAddressLine2());
                postalCodeTextField.setText(altAddress.getPostalCode() + "");
                if (altAddress.getCity() != null) {
                    cityComboBox.setValue(altAddress.getCity());
                }
                useCustomerAddressCheckBox.setText(Lang.get("Use another address"));
            }
            addressLine1TextField.setDisable(newValue);
            addressLine2TextField.setDisable(newValue);
            postalCodeTextField.setDisable(newValue);
            countryComboBox.setDisable(newValue);
            stateComboBox.setDisable(newValue);
            cityComboBox.setDisable(newValue);
            verifyPreferences();
        });
    }

    private void setTabListener() {
        tab1.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                Customer value = customerComboBox.getValue();
                if (value == null) {
                    wizard.stepUndone();
                } else {
                    wizard.stepDone();
                }
            }
        });

        tab2.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (masterDataSale1.isEmpty()) {
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

    private void setTexts() {

        ObservableList<TableColumn<TableStockAdapter, ?>> columns1 = stockTableView.getColumns();
        for (TableColumn<TableStockAdapter, ?> column : columns1) {
            column.setText(Lang.get(column.getText()));
        }
        ObservableList<TableColumn<TableSaleHasMetaItemAdapter, ?>> columns2 = saleTableView1.getColumns();
        for (TableColumn<TableSaleHasMetaItemAdapter, ?> column : columns2) {
            column.setText(Lang.get(column.getText()));
        }
        ObservableList<TableColumn<TableSaleHasMetaItemAdapter, ?>> columns3 = saleTableView2.getColumns();
        for (TableColumn<TableSaleHasMetaItemAdapter, ?> column : columns3) {
            column.setText(Lang.get(column.getText()));
        }

        titleLabel.setText(Lang.get(titleLabel.getText()));
        ObservableList<Tab> tabs = tabPane.getTabs();
        for (Tab tab : tabs) {
            tab.setText(Lang.get(tab.getText()));
        }
        customerLabel.setText(Lang.get(customerLabel.getText()));
        customerComboBox.setPromptText(Lang.get(customerComboBox.getPromptText()));

        choosenDataLabel.setText(Lang.get(choosenDataLabel.getText()));
        nameLabel.setText(Lang.get(nameLabel.getText()));
        typeLabel.setText(Lang.get(typeLabel.getText()));
        taxLabel.setText(Lang.get(taxLabel.getText()));
        taxTypeLabel.setText(Lang.get(taxTypeLabel.getText()));
        annotationLabel.setText(Lang.get(annotationLabel.getText()));
        imageLabel.setText(Lang.get(imageLabel.getText()));

        buttonBack.setText(Lang.get(buttonBack.getText()));
        buttonNext.setText(Lang.get(buttonNext.getText()));
        buttonDone.setText(Lang.get(buttonDone.getText()));
        buttonCancel.setText(Lang.get(buttonCancel.getText()));

        stockLabel.setText(Lang.get(stockLabel.getText()));

        saleLabel1.setText(Lang.get(saleLabel1.getText()));
        filterLabel.setText(Lang.get(filterLabel.getText()));
        filter2Label.setText(Lang.get(filter2Label.getText()));
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
        totalPriceLabel1.setText(Lang.get(totalPriceLabel1.getText()));
        totalPriceValueLabel.setText(Lang.get(totalPriceValueLabel.getText()));
        searchProductStock.setPromptText(Lang.get(searchProductStock.getPromptText()));
        searchProductSale1.setPromptText(Lang.get(searchProductSale1.getPromptText()));

        issueLabel.setText(Lang.get(issueLabel.getText()));
        shippingLabel.setText(Lang.get(shippingLabel.getText()));
        deliveryLabel.setText(Lang.get(deliveryLabel.getText()));
        paymentLabel.setText(Lang.get(paymentLabel.getText()));
        annotationLabel2.setText(Lang.get(annotationLabel2.getText()));
        addressTitle.setText(Lang.get(addressTitle.getText()));
        addressLabel.setText(Lang.get(addressLabel.getText()));
        useCustomerAddressCheckBox.setText(Lang.get(useCustomerAddressCheckBox.getText()));
        addressLine1Label.setText(Lang.get(addressLine1Label.getText()));
        addressLine2Label.setText(Lang.get(addressLine2Label.getText()));
        postalCodeLabel.setText(Lang.get(postalCodeLabel.getText()));
        countryLabel.setText(Lang.get(countryLabel.getText()));
        stateLabel.setText(Lang.get(stateLabel.getText()));
        cityLabel.setText(Lang.get(cityLabel.getText()));
        saleLabel2.setText(Lang.get(saleLabel2.getText()));
        filter3Label.setText(Lang.get(filter3Label.getText()));
        totalPriceLabel2.setText(Lang.get(totalPriceLabel2.getText()));
        totalPriceValueLabel2.setText(Lang.get(totalPriceValueLabel2.getText()));
        searchProductSale2.setPromptText(Lang.get(searchProductSale2.getPromptText()));

        succesfulLabel.setText(Lang.get(succesfulLabel.getText()));
        infoPriceLabel.setText(Lang.get(infoPriceLabel.getText()));
    }

}
