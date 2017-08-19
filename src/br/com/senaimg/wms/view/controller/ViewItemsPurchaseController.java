/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.sistema.ImageFile;
import br.com.senaimg.wms.model.sistema.Settings;
import br.com.senaimg.wms.model.sistema.enums.CssTheme;
import br.com.senaimg.wms.model.warehouse.process.Purchase;
import br.com.senaimg.wms.model.warehouse.process.PurchaseHasMetaItem;
import br.com.senaimg.wms.util.SystemImageUtil;
import br.com.senaimg.wms.view.adapter.TablePurchaseHasMetaItemAdapter;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * FXML Controller class
 *
 * @author ÁlefeLucas
 */
public class ViewItemsPurchaseController implements Initializable {

    @FXML
    private GridPane formGridPaneSupplier;
    @FXML
    private Label supplierLabel;
    @FXML
    private Label supplierValueLabel;
    @FXML
    private Label purchaseLabel;
    @FXML
    private Label filterLabel;
    @FXML
    private TextField searchProductAdded;
    @FXML
    private TableView<TablePurchaseHasMetaItemAdapter> addedTableView;
    @FXML
    private VBox rightPane;
    @FXML
    private Label imageLabel;
    @FXML
    private Rectangle imageSquare;
   
    @FXML
    private TableColumn<TablePurchaseHasMetaItemAdapter, String> mnemonicColumn;
    @FXML
    private TableColumn<TablePurchaseHasMetaItemAdapter, String> quantityColumn;
    @FXML
    private TableColumn<TablePurchaseHasMetaItemAdapter, String> totalPriceColumn;
    private Purchase purchase;
    private ObservableList<TablePurchaseHasMetaItemAdapter> masterData = FXCollections.observableArrayList();
    private TablePurchaseHasMetaItemAdapter selectedPurchased;
    @FXML
    private StackPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTexts();
        setTheme();
        cleanImage(imageSquare);
     
        setTableColumns();
        setTable();
    }
    
    
    private void setTexts() {

        supplierLabel.setText(Lang.get(supplierLabel.getText()));
        purchaseLabel.setText(Lang.get(purchaseLabel.getText()));
        filterLabel.setText(Lang.get(filterLabel.getText()));
        imageLabel.setText(Lang.get(imageLabel.getText()));
        
        searchProductAdded.setPromptText(Lang.get(searchProductAdded.getPromptText()));
        ObservableList<TableColumn<TablePurchaseHasMetaItemAdapter, ?>> columns = addedTableView.getColumns();
        for (TableColumn<TablePurchaseHasMetaItemAdapter, ?> column : columns) {
            column.setText(Lang.get(column.getText()));
        }
    }
    
   private void setTheme() {
        String path = "br/com/senaimg/wms/view/";
        String css = "viewitemspurchase.css";
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
    
    

    
    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;

        supplierValueLabel.setText(purchase.getSupplier().getMnemonic());
        ImageFile image = purchase.getSupplier().getImage();
        if (image != null) {
            setImage(imageSquare,image.getImage());
        } else {
            cleanImage(imageSquare);
        }

        List<PurchaseHasMetaItem> phis = purchase.getPurchaseHasMetaItems();
        for(PurchaseHasMetaItem phi : phis){
            masterData.add(new TablePurchaseHasMetaItemAdapter(phi));
        }
        
    }

    private void cleanImage(Shape shape) {
        setImage(imageSquare, SystemImageUtil.getImage(SystemImageUtil.DEFAULT_USER));
    }

    private void setTable() {
        masterData.clear();
        addedTableView.setItems(setSearch());

    }

    private void setImage(Shape shape, Image image) {
        ImagePattern pattern = new ImagePattern(image);
        shape.setFill(pattern);
    }

    private void setTableColumns() {
        mnemonicColumn.setCellValueFactory(new PropertyValueFactory<>("mnemonic"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private ObservableList<TablePurchaseHasMetaItemAdapter> setSearch() {
        FilteredList<TablePurchaseHasMetaItemAdapter> filteredData = new FilteredList<>(masterData, p -> true);
        searchProductAdded.textProperty().addListener((obs, oldV, newV) -> {
            filterAdded(filteredData, newV);
        });

        SortedList<TablePurchaseHasMetaItemAdapter> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(addedTableView.comparatorProperty());
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
}
