/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.sistema.Settings;
import br.com.senaimg.wms.model.sistema.enums.CssTheme;
import br.com.senaimg.wms.model.warehouse.agent.Catalogue;
import br.com.senaimg.wms.model.warehouse.agent.Supplier;
import br.com.senaimg.wms.model.warehouse.process.InProcess;
import br.com.senaimg.wms.model.warehouse.process.ProcessStatus;
import br.com.senaimg.wms.model.warehouse.process.Purchase;
import br.com.senaimg.wms.model.warehouse.process.PurchaseHasMetaItem;
import br.com.senaimg.wms.model.warehouse.stock.item.MetaItem;
import br.com.senaimg.wms.util.DateUtil;
import br.com.senaimg.wms.util.SystemImageUtil;
import br.com.senaimg.wms.util.ValidateFieldUtil;
import br.com.senaimg.wms.view.adapter.TableCatalogueAdapter;
import br.com.senaimg.wms.view.adapter.TablePurchaseHasMetaItemAdapter;
import br.com.senaimg.wms.view.layout.Wizard;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author ÁlefeLucas
 */
public class InsertPurchaseController implements Initializable {

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
    private GridPane formGridPaneSupplier;
    @FXML
    private Label supplierLabel;
    @FXML
    private ComboBox<Supplier> supplierComboBox;
    @FXML
    private Label contactTitle;
    @FXML
    private Label filter1Label;
    @FXML
    private TextField searchProductCatalogue1;
    @FXML
    private TableView<TableCatalogueAdapter> catalogue1TableView;
    @FXML
    private VBox rightPane;
    @FXML
    private Label imageLabel;
    @FXML
    private Rectangle imageSquare;
    @FXML
    private Tab tab2;
    @FXML
    private Label catelogue2Label;
    @FXML
    private Label filter2Label;
    @FXML
    private TextField searchProductCatalogue2;
    @FXML
    private TableView<TableCatalogueAdapter> catalogue2TableView;
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
    private Label purchaseLabel1;
    @FXML
    private Label filter3Label;
    @FXML
    private TextField searchProductAdded1;
    @FXML
    private TableView<TablePurchaseHasMetaItemAdapter> addedTableView1;
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
    private Label totalPriceValueLabel1;
    @FXML
    private Tab tab3;
    @FXML
    private GridPane formGridPaneSupplier11;
    @FXML
    private Label expLabel;
    @FXML
    private Label orderLabel;
    @FXML
    private Label paymentLabel;
    @FXML
    private Label annotationLabel;
    @FXML
    private TextArea annotationTextArea;
    @FXML
    private DatePicker orderDatePicker;
    @FXML
    private DatePicker expDatePicker;
    @FXML
    private TextArea paymentTextArea;

    @FXML
    private Label purchaseLabel2;
    @FXML
    private Label filter4Label;
    @FXML
    private TextField searchProductAdded2;
    @FXML
    private TableView<TablePurchaseHasMetaItemAdapter> addedTableView2;
    @FXML
    private Label totalPriceLabel2;
    @FXML
    private Label totalPriceValueLabel2;
    @FXML
    private Tab tab4;
    @FXML
    private Label succesfulLabel;
    @FXML
    private Label infoLabel;
    @FXML
    private TableColumn<TableCatalogueAdapter, String> mnemonicColumn;
    @FXML
    private TableColumn<TableCatalogueAdapter, String> descriptionColumn;
    @FXML
    private TableColumn<TableCatalogueAdapter, String> priceColumn;
    @FXML
    private TableColumn<TableCatalogueAdapter, String> rankingColumn;
    @FXML
    private TableColumn<TableCatalogueAdapter, String> mnemonicColumn2;
    @FXML
    private TableColumn<TableCatalogueAdapter, String> descriptionColumn2;
    @FXML
    private TableColumn<TableCatalogueAdapter, String> priceColumn2;
    @FXML
    private TableColumn<TableCatalogueAdapter, String> rankingColumn2;
    @FXML
    private TableColumn<TablePurchaseHasMetaItemAdapter, String> mnemonicColumn3;
    @FXML
    private TableColumn<TablePurchaseHasMetaItemAdapter, String> quantityColumn;
    @FXML
    private TableColumn<TablePurchaseHasMetaItemAdapter, String> totalPriceColumn;
    private TablePurchaseHasMetaItemAdapter selectedPurchased;
    @FXML
    private TableColumn<TablePurchaseHasMetaItemAdapter, String> mnemonicColumn4;
    @FXML
    private TableColumn<TablePurchaseHasMetaItemAdapter, String> quantityColumn2;
    @FXML
    private TableColumn<TablePurchaseHasMetaItemAdapter, String> totalPriceColumn2;

    public BooleanProperty inserted = new SimpleBooleanProperty(false);

    private ObservableList<TableCatalogueAdapter> masterDataCatalogue1 = FXCollections.observableArrayList();
    private ObservableList<TableCatalogueAdapter> masterDataCatalogue2 = FXCollections.observableArrayList();
    private ObservableList<TablePurchaseHasMetaItemAdapter> masterDataAdded1 = FXCollections.observableArrayList();
    private Supplier selectedSupplier;
    private TableCatalogueAdapter selectedCatalogue;
    private Wizard wizard;
    @FXML
    private Tooltip annotationToolTip;
    @FXML
    private Tooltip paymentToolTip;
    @FXML
    private Label removeLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTexts();
        setTheme();
        addSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1, 1));
        removeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1, 1));
        setCatalogue1TableColumns();
        setCatalogue2TableColumns();
        setAdded1TableColumns();
        setAdded2TableColumns();
        setCatalogue2Clicked();
        setAdded1();
        setAdded1Clicked();
        setAfterAdded();
        setPreferencesListeners();
        wizard = new Wizard(buttonNext, buttonBack, buttonDone, buttonCancel, tabPane, tab1, tab2, tab3, tab4);
        setTabListener();
        setSuppliers();
    }

    private void setTheme() {
        String path = "br/com/senaimg/wms/view/";
        String css = "insertpurchase.css";
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

    private void setAfterAdded() {
        masterDataAdded1.addListener((ListChangeListener.Change<? extends TablePurchaseHasMetaItemAdapter> c) -> {
            if (c.getList().size() > 0) {
                wizard.stepDone();
            } else {
                wizard.stepUndone();
            }
        });
    }

    private void setTabListener() {
        tab1.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                setSuppliers();
                masterDataAdded1.clear();
                masterDataCatalogue2.clear();

            }
        });
        tab2.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && !masterDataAdded1.isEmpty()) {
                wizard.stepDone();
            }
        });
        tab4.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                buttonBack.setDisable(true);

                Purchase purchase = new Purchase(selectedSupplier, DateUtil.toDate(
                        orderDatePicker.getValue()),
                        DateUtil.toDate(expDatePicker.getValue()),
                        paymentTextArea.getText(),
                        annotationTextArea.getText(),
                        null,
                        ProcessStatus.UNBEGUN,
                        InProcess.PURCHASE);
                ArrayList<PurchaseHasMetaItem> phis = new ArrayList<>();
                for (TablePurchaseHasMetaItemAdapter tphi : masterDataAdded1) {
                    PurchaseHasMetaItem phi = tphi.getpHI();
                    phi.setPurchase(purchase);
                    phis.add(phi);
                }

                purchase.insert();
                for (PurchaseHasMetaItem phi : phis) {
                    phi.insert();
                }
                inserted.setValue(Boolean.FALSE);
                inserted.setValue(Boolean.TRUE);
            }
        });
    }

    private void setSuppliers() {
        List<Supplier> suppliers = Supplier.list();
        ObservableList<Supplier> items = supplierComboBox.getItems();
        items.clear();
        items.addAll(suppliers);

        supplierComboBox.valueProperty().addListener((obs, oldV, newV) -> {

            if (newV != null) {
                if (newV.getImage() == null) {
                    cleanImage();

                } else {
                    Image img = newV.getImage().getImage();
                    setImage(img);

                }
                selectedSupplier = newV;
                setCatalogue1(newV);
                setCatalogue2(newV);
                wizard.stepDone();
            } else {
                selectedSupplier = null;
                cleanImage();
            }

        });
        if (!items.isEmpty()) {
            wizard.stepDone();
            supplierComboBox.setValue(items.get(0));
        } else {
            selectedSupplier = null;
            wizard.stepUndone();
        }
    }

    private void setAdded1() {
        masterDataAdded1.clear();
        addedTableView1.setItems(setSearchAdded1());

        addedTableView2.setItems(setSearchAdded2());
    }

    private SortedList<TablePurchaseHasMetaItemAdapter> setSearchAdded1() {
        FilteredList<TablePurchaseHasMetaItemAdapter> filteredData = new FilteredList<>(masterDataAdded1, p -> true);
        searchProductAdded1.textProperty().addListener((obs, oldV, newV) -> {
            filterAdded(filteredData, newV);
        });

        SortedList<TablePurchaseHasMetaItemAdapter> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(addedTableView1.comparatorProperty());
        return sortedData;
    }

    private SortedList<TablePurchaseHasMetaItemAdapter> setSearchAdded2() {
        FilteredList<TablePurchaseHasMetaItemAdapter> filteredData = new FilteredList<>(masterDataAdded1, p -> true);
        searchProductAdded2.textProperty().addListener((obs, oldV, newV) -> {
            filterAdded(filteredData, newV);
        });

        SortedList<TablePurchaseHasMetaItemAdapter> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(addedTableView2.comparatorProperty());
        return sortedData;
    }

    private void setCatalogue1(Supplier newV) {
        masterDataCatalogue1.clear();
        for (Catalogue c : newV.getCatalogues()) {
            masterDataCatalogue1.add(new TableCatalogueAdapter(c));
        }

        SortedList<TableCatalogueAdapter> sortedData = setSearchCatalogue1();
        catalogue1TableView.setItems(sortedData);
        String text = searchProductCatalogue1.getText();
        searchProductCatalogue1.setText("");
        searchProductCatalogue1.setText(text);
    }

    private SortedList<TableCatalogueAdapter> setSearchCatalogue1() {
        FilteredList<TableCatalogueAdapter> filteredData = new FilteredList<>(masterDataCatalogue1, p -> true);
        searchProductCatalogue1.textProperty().addListener((obs, oldV, newV) -> {
            filterCatalogue(filteredData, newV);
        });

        SortedList<TableCatalogueAdapter> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(catalogue1TableView.comparatorProperty());
        return sortedData;
    }

    private void setCatalogue2(Supplier newV) {
        masterDataCatalogue2.clear();
        for (Catalogue c : newV.getCatalogues()) {
            masterDataCatalogue2.add(new TableCatalogueAdapter(c));
        }
        SortedList<TableCatalogueAdapter> sortedData = setSearchCatalogue2();
        catalogue2TableView.setItems(sortedData);
        String text = searchProductCatalogue2.getText();
        searchProductCatalogue2.setText("");
        searchProductCatalogue2.setText(text);
    }

    private void cleanImage() {
        setImage(SystemImageUtil.getImage(SystemImageUtil.DEFAULT_USER));
    }

    private void setImage(Image image) {
        ImagePattern pattern = new ImagePattern(image);
        imageSquare.setFill(pattern);
    }

    @FXML
    private void backHandle(ActionEvent event) {
        wizard.back();
    }

    @FXML
    private void nextHandle(ActionEvent event) {
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

    /**
     *
     */
    public BooleanProperty closed = new SimpleBooleanProperty(false);

    private void setCatalogue1TableColumns() {
        mnemonicColumn.setCellValueFactory(new PropertyValueFactory<>("mnemonic"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        rankingColumn.setCellValueFactory(new PropertyValueFactory<>("ranking"));
    }

    private void setCatalogue2TableColumns() {
        mnemonicColumn2.setCellValueFactory(new PropertyValueFactory<>("mnemonic"));
        descriptionColumn2.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceColumn2.setCellValueFactory(new PropertyValueFactory<>("price"));
        rankingColumn2.setCellValueFactory(new PropertyValueFactory<>("ranking"));
    }

    private SortedList<TableCatalogueAdapter> setSearchCatalogue2() {
        FilteredList<TableCatalogueAdapter> filteredData = new FilteredList<>(masterDataCatalogue2, p -> true);
        searchProductCatalogue2.textProperty().addListener((obs, oldV, newV) -> {
            filterCatalogue(filteredData, newV);
        });

        SortedList<TableCatalogueAdapter> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(catalogue2TableView.comparatorProperty());
        return sortedData;
    }

    private void filterAdded(FilteredList<TablePurchaseHasMetaItemAdapter> filteredData, String newV) {
        filteredData.setPredicate((TablePurchaseHasMetaItemAdapter t) -> {
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

    private void filterCatalogue(FilteredList<TableCatalogueAdapter> filteredData, String newV) {
        filteredData.setPredicate((TableCatalogueAdapter t) -> {
            if (newV == null || newV.isEmpty()) {
                return true;
            }

            String filter = newV.toLowerCase();

            if (t.getMnemonic().toLowerCase().contains(filter)) {
                return true;
            }

            if (t.getDescription().toLowerCase().contains(filter)) {
                return true;
            }

            return false;
        });
    }

    private void setAdded1Clicked() {
        addedTableView1.getSelectionModel().selectedItemProperty().addListener((obs, oldV, selectedPurchased) -> {
            if (selectedPurchased == null) {
                this.selectedPurchased = null;
                removeSelectedItemValueLabel.setText(Lang.get("No Item Selected"));
                removeButton.setDisable(true);
            } else {
                this.selectedPurchased = selectedPurchased;
                removeSelectedItemValueLabel.setText(selectedPurchased.getMnemonic());
                removeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, selectedPurchased.getpHI().getQuantity(), 1, 1));
                removeButton.setDisable(false);
            }
        });
    }

    private void setCatalogue2Clicked() {
        catalogue2TableView.getSelectionModel().selectedItemProperty().addListener((obs, oldV, selectedCatalogue) -> {
            if (selectedCatalogue == null) {
                this.selectedCatalogue = null;
                addSelectedItemValueLabel.setText(Lang.get("No Item Selected"));
                addButton.setDisable(true);
            } else {
                this.selectedCatalogue = selectedCatalogue;
                addSelectedItemValueLabel.setText(selectedCatalogue.getMnemonic());
                addButton.setDisable(false);
            }
        });
    }

    @FXML
    private void addHandle(ActionEvent event) {
        int quantity = addSpinner.getValue();
        double price = selectedCatalogue.getCalogue().getPrice();
        MetaItem metaItem = selectedCatalogue.getCalogue().getMetaItem();
        Supplier supplier = selectedCatalogue.getCalogue().getSupplier();

        PurchaseHasMetaItem pHI = new PurchaseHasMetaItem(null, metaItem, quantity, price);

        TablePurchaseHasMetaItemAdapter tphiaInsert = new TablePurchaseHasMetaItemAdapter(pHI);
        TablePurchaseHasMetaItemAdapter tphiaOld = null;
        boolean alreadyAdded = false;
        for (TablePurchaseHasMetaItemAdapter tphia : masterDataAdded1) {
            if (tphia.getpHI().getMetaItem() == tphiaInsert.getpHI().getMetaItem()) {
                alreadyAdded = true;
                tphiaOld = tphia;
                break;
            }
        }

        if (!alreadyAdded) {
            masterDataAdded1.add(tphiaInsert);

        } else {
            masterDataAdded1.remove(tphiaOld);
            PurchaseHasMetaItem phiInsert = tphiaInsert.getpHI();
            PurchaseHasMetaItem phi = tphiaOld.getpHI();

            phi.setQuantity(phi.getQuantity() + phiInsert.getQuantity());
            tphiaOld.setpHI(phi);
            masterDataAdded1.add(tphiaOld);

        }
        refreshSorted();
        calcTotal();
    }

    private void refreshSorted() {
        String text = searchProductAdded1.getText();
        searchProductAdded1.setText("" + Math.random());
        searchProductAdded1.setText(text);
        searchProductAdded2.setText("" + Math.random());
        searchProductAdded2.setText("");
    }

    @FXML
    private void removeHandle(ActionEvent event) {
        int quantity = removeSpinner.getValue();
        TablePurchaseHasMetaItemAdapter s = selectedPurchased;
        PurchaseHasMetaItem phi = s.getpHI();

        masterDataAdded1.remove(s);

        phi.setQuantity(phi.getQuantity() - quantity);
        s.setpHI(phi);

        if (phi.getQuantity() > 0) {
            masterDataAdded1.add(s);

        }
        refreshSorted();
        calcTotal();
    }

    private void setAdded1TableColumns() {
        mnemonicColumn3.setCellValueFactory(new PropertyValueFactory<>("mnemonic"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private void setAdded2TableColumns() {
        mnemonicColumn4.setCellValueFactory(new PropertyValueFactory<>("mnemonic"));
        quantityColumn2.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalPriceColumn2.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private void calcTotal() {
        double total = 0;
        for (TablePurchaseHasMetaItemAdapter tphi : masterDataAdded1) {
            PurchaseHasMetaItem phi = tphi.getpHI();
            total += (phi.getInPrice() * phi.getQuantity());
        }

        totalPriceValueLabel1.setText(String.format("R$ %.2f", total));
        totalPriceValueLabel2.setText(String.format("R$ %.2f", total));
        infoLabel.setText(Lang.get("Price") + ": " + String.format("R$ %.2f", total));
    }

    private void setPreferencesListeners() {
        orderDatePicker.valueProperty().addListener((obs, oldV, value) -> {
            orderValid.setValue(!orderValid.getValue());
            if (value == null) {
                orderValid.setValue(false);
            } else if (!DateUtil.isPast(value)) {
                orderValid.setValue(true);
            } else {
                orderValid.setValue(false);
            }

        });

        expDatePicker.valueProperty().addListener((obs, oldV, value) -> {
            expValid.setValue(!expValid.getValue());
            if (value == null) {
                expValid.setValue(false);
            } else if (!DateUtil.isPast(value)) {
                expValid.setValue(true);
            } else {
                expValid.setValue(false);
            }

        });

        ChangeListener<Boolean> change = (obs, oldV, newV) -> {
            if (orderValid.getValue() && expValid.getValue()) {
                LocalDate exp = expDatePicker.getValue();
                LocalDate order = orderDatePicker.getValue();

                Date dateOrder = DateUtil.toDate(order);
                Date dateExp = DateUtil.toDate(exp);
                datesValid.setValue(!datesValid.getValue());
                if (!dateExp.before(dateOrder)) {
                    datesValid.setValue(true);
                } else {
                    datesValid.setValue(false);
                }
            } else {
                datesValid.setValue(false);
            }
        };
        orderValid.addListener(change);
        expValid.addListener(change);

        datesValid.addListener((obs, oldV, newV) -> {
            verifyValid();
        });

        payValid.addListener((obs, oldV, newV) -> {
            verifyValid();
        });

        annoValid.addListener((obs, oldV, newV) -> {
            verifyValid();
        });
        annotationTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean valid = !ValidateFieldUtil.biggerThan(newValue, 1023);

            annoValid.setValue(valid);
            if (!valid) {
                annotationToolTip.setText(Lang.get("Annotation cannot be bigger than 1023 characters"));
                showToolTip(annotationToolTip, annotationTextArea);
            } else {
                annotationToolTip.setText("");
                annotationToolTip.hide();
            }
        });

        paymentTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean valid = !ValidateFieldUtil.biggerThan(newValue, 1023);

            payValid.setValue(valid);
            if (!valid) {
                paymentToolTip.setText(Lang.get("Payment conditions cannot be bigger than 1023 characters"));
                showToolTip(paymentToolTip, paymentTextArea);
            } else {
                paymentToolTip.setText("");
                paymentToolTip.hide();
            }
        });

    }

    private void showToolTip(Tooltip tooltip, Node node) {
        Window window = rootPane.getScene().getWindow();
        Bounds bounds = node.localToScreen(node.getBoundsInLocal());
        double x = bounds.getMinX();
        double y = bounds.getMaxY();
        tooltip.show(window, x, y);
    }

    private BooleanProperty orderValid = new SimpleBooleanProperty(false);
    private BooleanProperty expValid = new SimpleBooleanProperty(false);
    private BooleanProperty datesValid = new SimpleBooleanProperty(false);

    private BooleanProperty annoValid = new SimpleBooleanProperty(true);
    private BooleanProperty payValid = new SimpleBooleanProperty(true);

    private void verifyValid() {
        if (annoValid.getValue() && payValid.getValue() && datesValid.getValue()) {
            wizard.stepDone();
        } else {
            wizard.stepUndone();
        }
    }

    private void setTexts() {
        titleLabel.setText(Lang.get(titleLabel.getText()));
        ObservableList<Tab> tabs = tabPane.getTabs();
        for (Tab tab : tabs) {
            tab.setText(Lang.get(tab.getText()));
        }

        ObservableList<TableColumn<TableCatalogueAdapter, ?>> columns1 = catalogue1TableView.getColumns();
        for (TableColumn<TableCatalogueAdapter, ?> column : columns1) {
            column.setText(Lang.get(column.getText()));
        }
        ObservableList<TableColumn<TableCatalogueAdapter, ?>> columns2 = catalogue2TableView.getColumns();
        for (TableColumn<TableCatalogueAdapter, ?> column : columns2) {
            column.setText(Lang.get(column.getText()));
        }
        
        ObservableList<TableColumn<TablePurchaseHasMetaItemAdapter, ?>> columns3 = addedTableView1.getColumns();
        for (TableColumn<TablePurchaseHasMetaItemAdapter, ?> column : columns3) {
            column.setText(Lang.get(column.getText()));
        }
        
        ObservableList<TableColumn<TablePurchaseHasMetaItemAdapter, ?>> columns4 = addedTableView2.getColumns();
        for (TableColumn<TablePurchaseHasMetaItemAdapter, ?> column : columns4) {
            column.setText(Lang.get(column.getText()));
        }

        supplierLabel.setText(Lang.get(supplierLabel.getText()));
        supplierComboBox.setPromptText(Lang.get(supplierComboBox.getPromptText()));
        contactTitle.setText(Lang.get(contactTitle.getText()));
        imageLabel.setText(Lang.get(imageLabel.getText()));
        filter1Label.setText(Lang.get(filter1Label.getText()));
        searchProductCatalogue1.setPromptText(Lang.get(searchProductCatalogue1.getPromptText()));
        buttonBack.setText(Lang.get(buttonBack.getText()));
        buttonNext.setText(Lang.get(buttonNext.getText()));
        buttonDone.setText(Lang.get(buttonDone.getText()));
        buttonCancel.setText(Lang.get(buttonCancel.getText()));
        catelogue2Label.setText(Lang.get(catelogue2Label.getText()));
        purchaseLabel1.setText(Lang.get(purchaseLabel1.getText()));
        filter2Label.setText(Lang.get(filter2Label.getText()));
        searchProductCatalogue2.setPromptText(Lang.get(searchProductCatalogue2.getPromptText()));
        filter3Label.setText(Lang.get(filter3Label.getText()));
        searchProductAdded1.setPromptText(Lang.get(searchProductAdded1.getPromptText()));
        addLabel.setText(Lang.get(addLabel.getText()));
        removeLabel.setText(Lang.get(removeLabel.getText()));
        removeSelectedItemLabel.setText(Lang.get(removeSelectedItemLabel.getText()));
        removeButton.setText(Lang.get(removeButton.getText()));
        removeQuantityLabel.setText(Lang.get(removeQuantityLabel.getText()));
        removeSelectedItemValueLabel.setText(Lang.get(removeSelectedItemValueLabel.getText()));
        addSelectedItemLabel.setText(Lang.get(addSelectedItemLabel.getText()));
        addSelectedItemValueLabel.setText(Lang.get(addSelectedItemValueLabel.getText()));
        addQuantityLabel.setText(Lang.get(addQuantityLabel.getText()));
        addButton.setText(Lang.get(addButton.getText()));
        totalPriceLabel1.setText(Lang.get(totalPriceLabel1.getText()));
        totalPriceValueLabel1.setText(Lang.get(totalPriceValueLabel1.getText()));
        orderLabel.setText(Lang.get(orderLabel.getText()));

        expLabel.setText(Lang.get(expLabel.getText()));
        paymentLabel.setText(Lang.get(paymentLabel.getText()));
        annotationLabel.setText(Lang.get(annotationLabel.getText()));
        purchaseLabel2.setText(Lang.get(purchaseLabel2.getText()));
        filter4Label.setText(Lang.get(filter4Label.getText()));
        searchProductAdded2.setPromptText(Lang.get(searchProductAdded2.getPromptText()));
        totalPriceLabel2.setText(Lang.get(totalPriceLabel2.getText()));
        totalPriceValueLabel2.setText(Lang.get(totalPriceValueLabel2.getText()));

        succesfulLabel.setText(Lang.get(succesfulLabel.getText()));
        infoLabel.setText(Lang.get(infoLabel.getText()));

    }

}
