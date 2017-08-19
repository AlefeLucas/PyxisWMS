/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.layout;

import br.com.senaimg.wms.model.sistema.Functionality;
import br.com.senaimg.wms.view.controller.HomeController;
import br.com.senaimg.wms.view.controller.TabController;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javax.swing.JOptionPane;

/**
 *
 * @author ÁlefeLucas
 */
public class MenuItemFunc extends MenuItem{

    private Functionality func;
    private TabPane tabPane;
    private final HomeController home;
    
    /**
     *
     * @param func
     * @param tabPane
     */
    public MenuItemFunc(Functionality func, TabPane tabPane, HomeController home) {
        super(func.getTitle());
        this.func = func;
        this.tabPane = tabPane;
        this.setOnAction(e -> clicked());
        this.home = home;
    }

   

    private void clicked() {
        FXMLLoader tabLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/" + func.getTabfxml()));
            StackPane rootTab = null;
            try {
                rootTab = tabLoader.load();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error loading tab", "Fatal Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
                System.exit(1);
            }

            Tab tab = new Tab(func.getTitle(), rootTab);
            tab.setClosable(true);

            ObservableList<Tab> tabs = tabPane.getTabs();

            boolean tabExists = false;

            for (Tab t : tabs) {
                if (t.getText().equals(tab.getText())) {
                    tabExists = true;
                    tab = t;
                }
            }

            if (!tabExists) {
                tabPane.getTabs().add(tab);
                TabController controller = tabLoader.<TabController>getController();

            controller.setTab(tab);
            controller.setHome(home);
            }
            tabPane.getSelectionModel().select(tab);
    }
    
    
    
}
