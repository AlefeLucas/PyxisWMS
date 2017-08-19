/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.util.JReportsUtil;
import br.com.senaimg.wms.util.SystemImageUtil;
import br.com.senaimg.wms.view.adapter.factory.CustomerFactory;
import br.com.senaimg.wms.view.adapter.factory.StockFactory;
import br.com.senaimg.wms.view.adapter.factory.SupplierFactory;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import net.sf.jasperreports.engine.JRDataSource;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author ÁlefeLucas
 */
public class AnalyticsController extends TabController implements Initializable {

    @FXML
    private StackPane rootTabPane;
    @FXML
    private Button stockReport;
    @FXML
    private Button minimumStockReport;
    @FXML
    private Button supplierReport;
    @FXML
    private Button customerReport;

    private Service<Void> service;
    private Stage loadingStage;

    @FXML
    private void stockReportHandle(ActionEvent event) {
        report("StockReport", StockFactory.getAllDataSource());
    }

    private void callReport(String fileName, File out, JRDataSource dataSource) throws FileNotFoundException {
        //File f = new File("C:\\Users\\ÁlefeLucas\\Desktop\\err.txt");
        //PrintStream p = new PrintStream(f);
        //PrintStream err = System.err;
        //System.setErr(p);
        
        JReportsUtil.exportReport(fileName, out, dataSource);
        //System.setErr(err);
    }

    @FXML
    private void customerReportHandle(ActionEvent event) {
        report("CustomersReport", CustomerFactory.getActiveDataSource());
    }

    @FXML
    private void minimumStockReportHandle(ActionEvent event) {
        report("MinimumStockReport", StockFactory.getMinimumDataSource());
    }

    @FXML
    private void supplierReportHandle(ActionEvent event) {
        report("SuppliersReport", SupplierFactory.getActiveDataSource());
    }

    private static File getDirectory(Window window) {
        FileChooser fc = new FileChooser();

        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(" PDF ", "*.pdf"));
        return fc.showSaveDialog(window);

    }

    private void report(String fileName, JRDataSource dataSource) {
        File out = getDirectory(rootTabPane.getScene().getWindow());
        if (out != null) {
            try {
                service = new Service<Void>() {
                    @Override
                    protected Task<Void> createTask() {
                        return new Task<Void>() {

                            @Override
                            protected Void call() throws Exception {
                                updateMessage(Lang.get("Loading") + "...");
                                callReport(fileName, out, dataSource);
                                return null;
                            }
                        };
                    }
                };

                FXMLLoader fxmlLoader = new FXMLLoader(
                        getClass().getResource("/br/com/senaimg/wms/view/fxml/Loading.fxml"));
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
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stockReport.setText(Lang.get(stockReport.getText()));
        minimumStockReport.setText(Lang.get(minimumStockReport.getText()));
        supplierReport.setText(Lang.get(supplierReport.getText()));
        customerReport.setText(Lang.get(customerReport.getText()));
    }

}
