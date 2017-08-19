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
import br.com.senaimg.wms.model.warehouse.process.Sale;
import br.com.senaimg.wms.model.warehouse.process.SaleHasMetaItem;
import br.com.senaimg.wms.util.SystemImageUtil;
import br.com.senaimg.wms.view.adapter.TableSaleHasMetaItemAdapter;
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
public class ViewItemsSaleController implements Initializable {

    @FXML
    private GridPane formGridPaneCustomer;
    @FXML
    private Label customerLabel;
    @FXML
    private Label customerValueLabel;
    @FXML
    private Label saleLabel;
    @FXML
    private Label filterLabel;
    @FXML
    private TextField searchProductAdded;
    @FXML
    private TableView<TableSaleHasMetaItemAdapter> addedTableView;
    @FXML
    private VBox rightPane;
    @FXML
    private Label imageLabel;
    @FXML
    private Rectangle imageSquare;
    @FXML
    private StackPane rootPane;
    @FXML
    private TableColumn<TableSaleHasMetaItemAdapter, String> mnemonicColumn;
    @FXML
    private TableColumn<TableSaleHasMetaItemAdapter, String> quantityColumn;
    @FXML
    private TableColumn<TableSaleHasMetaItemAdapter, String> totalPriceColumn;
    private Sale sale;
    private ObservableList<TableSaleHasMetaItemAdapter> masterData = FXCollections.observableArrayList();
    private TableSaleHasMetaItemAdapter selectedPurchased;
   

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
        customerLabel.setText(Lang.get(customerLabel.getText()));
        searchProductAdded.setPromptText(Lang.get(searchProductAdded.getPromptText()));
        ObservableList<TableColumn<TableSaleHasMetaItemAdapter, ?>> columns = addedTableView.getColumns();
        for(TableColumn<TableSaleHasMetaItemAdapter, ?> column : columns){
            column.setText(Lang.get(column.getText()));
        }
        saleLabel.setText(Lang.get(saleLabel.getText()));
        filterLabel.setText(Lang.get(filterLabel.getText()));
        imageLabel.setText(Lang.get(imageLabel.getText()));        
    }
    
   private void setTheme() {
        String path = "br/com/senaimg/wms/view/";
        String css = "viewitemssale.css";
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
    
   

    
    public void setSale(Sale sale) {
        this.sale = sale;

        customerValueLabel.setText(sale.getCustomer().getName());
        ImageFile image = sale.getCustomer().getImage();
        if (image != null) {
            setImage(imageSquare,image.getImage());
        } else {
            cleanImage(imageSquare);
        }

        List<SaleHasMetaItem> phis = sale.getSaleHasMetaItems();
        for(SaleHasMetaItem phi : phis){
            masterData.add(new TableSaleHasMetaItemAdapter(phi));
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
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    }

    private ObservableList<TableSaleHasMetaItemAdapter> setSearch() {
        FilteredList<TableSaleHasMetaItemAdapter> filteredData = new FilteredList<>(masterData, p -> true);
        searchProductAdded.textProperty().addListener((obs, oldV, newV) -> {
            filterAdded(filteredData, newV);
        });

        SortedList<TableSaleHasMetaItemAdapter> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(addedTableView.comparatorProperty());
        return sortedData;
    }

    private void filterAdded(FilteredList<TableSaleHasMetaItemAdapter> filteredData, String newV) {
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
}
