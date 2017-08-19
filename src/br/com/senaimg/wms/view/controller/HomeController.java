/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.exception.UserDeletedException;
import br.com.senaimg.wms.view.layout.DashSize;
import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.sistema.Functionality;
import br.com.senaimg.wms.model.sistema.FunctionalityGroup;
import br.com.senaimg.wms.model.sistema.ImageFile;
import br.com.senaimg.wms.model.sistema.Permission;
import br.com.senaimg.wms.model.sistema.Profile;
import br.com.senaimg.wms.model.sistema.Settings;
import br.com.senaimg.wms.model.sistema.User;
import br.com.senaimg.wms.model.sistema.enums.CssTheme;
import br.com.senaimg.wms.model.sistema.enums.UserStatus;
import br.com.senaimg.wms.util.SystemImageUtil;
import br.com.senaimg.wms.view.layout.Dashboard;
import br.com.senaimg.wms.view.layout.DashboardItem;
import br.com.senaimg.wms.view.layout.MenuItemFunc;
import com.sun.javafx.stage.StageHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Alefe Lucas
 */
public class HomeController implements Initializable {

    @FXML
    private BorderPane rootPane;
    @FXML
    private MenuBar menuBar;
    @FXML
    private ToolBar toolBar;
    @FXML
    private Label userLabel;
    @FXML
    private Circle userImageCircle;
    @FXML
    private HBox footerPane;
    @FXML
    private Label copyrightLabel;
    @FXML
    private Label copyrightLabel1;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab dashboardTab;
    @FXML
    private HBox hBoxDashGroups;
    @FXML
    private MenuButton userMenuButton;
    @FXML
    private MenuItem userAccountItem;
    @FXML
    private MenuItem logOutItem;
    @FXML
    private Menu menuTab;

    private List<FunctionalityGroup> groups;
    private User user;
    @FXML
    private MenuItem exitMenuItem;
    @FXML
    private ScrollPane scrollPane;

    private double pos = 0;
    private double minPos = 0;
    private double maxPos = 100;
    @FXML
    private MenuItem settingsMenuItem;
    @FXML
    private Menu fileMenu;
    @FXML
    private ImageView pyxisLogo;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setTheme();
        setScrollPane();
    }

    private void setTexts() {
        ObservableList<Menu> menus = menuBar.getMenus();
        for (Menu menu : menus) {
            menu.setText(Lang.get(menu.getText()));

            ObservableList<MenuItem> items = menu.getItems();

            for (MenuItem item : items) {
                item.setText(Lang.get(item.getText()));

            }

        }

        dashboardTab.setText(Lang.get(dashboardTab.getText()));
        userAccountItem.setText(Lang.get(userAccountItem.getText()));
        logOutItem.setText(Lang.get(logOutItem.getText()));
    }

    private void setTheme() {
        String path = "br/com/senaimg/wms/view/";
        String css = "home.css";
        String theme;

        Settings s = Settings.getLast();
        if (s.getTheme() == CssTheme.DARK) {
            pyxisLogo.setImage(SystemImageUtil.getImage(SystemImageUtil.PYXIS_128));
            theme = "stylesheet";
        } else {
            pyxisLogo.setImage(SystemImageUtil.getImage(SystemImageUtil.PYXIS_128_BLACK));
            theme = "stylesheetLight";
        }

        ObservableList<String> stylesheets = rootPane.getStylesheets();
        stylesheets.clear();
        stylesheets.add(path + theme + "/" + css);
    }

    private void setScrollPane() {
        // TODO

        maxPos = hBoxDashGroups.getWidth();
        scrollPane.setHmin(minPos);
        scrollPane.setHmax(maxPos);

        hBoxDashGroups.widthProperty().addListener((observable, oldValue, newValue) -> {
            maxPos = newValue.doubleValue();
            scrollPane.setHmax(maxPos);
            System.out.println("MAX POS: " + maxPos);
        });

        hBoxDashGroups.setOnScroll(scrollEvent -> {

            double deltaY = scrollEvent.getDeltaY();
            double scrollAmount = 90;
            if (deltaY > 0) {

                if (pos <= minPos) {
                    pos = minPos;
                } else {
                    pos -= scrollAmount;
                }
                scrollPane.setHvalue(pos);

            } else {
                if (pos >= maxPos) {
                    pos = maxPos;
                } else {
                    pos += scrollAmount;
                }
                scrollPane.setHvalue(pos);
            }
        });
    }

    private void initAllDash() {
        clearDash();

        groups = FunctionalityGroup.list();
        for (FunctionalityGroup group : groups) {

            List<Functionality> funcs = group.getFunctionalities();
            Dashboard dashboard = new Dashboard();

            Menu menuGroup = new Menu(Lang.get(group.getTitle()));

            for (Functionality func : funcs) {
                boolean userHasFunc = false;
                List<Permission> permissions = func.getPermissions();
                for (Permission permission : permissions) {
                    Profile profile = permission.getProfile();

                    if (user.getProfile().getId().equals(profile.getId())) {
                        userHasFunc = true;

                    }
                }

                if (userHasFunc) {
                    MenuItem menuItem = new MenuItemFunc(func, tabPane, this);
                    menuGroup.getItems().add(menuItem);

                    DashSize size;

                    if (func.getTabfxml().contains("MyAccount") || func.getTabfxml().contains("Loan") || func.getTabfxml().contains("Purchase") || func.getTabfxml().contains("Sale")) {
                        size = DashSize.LARGE;
                    } else if (func.getTabfxml().contains("Users") || func.getTabfxml().contains("ManagePermissions") || func.getTabfxml().contains("Suppliers") || func.getTabfxml().contains("Catalogue")) {
                        size = DashSize.MEDIUM;
                    } else {
                        size = DashSize.BIG;
                    }

                    DashboardItem item = new DashboardItem(size, func, this);

                    dashboard.add(item);
                } else {
                    ObservableList<Tab> tabs = tabPane.getTabs();
                    Tab tabRemove = null;
                    for (Tab tab : tabs) {
                        if (tab.getText().equalsIgnoreCase(func.getTitle())) {
                            tabRemove = tab;
                        }
                    }
                    if (tabRemove != null) {
                        tabs.remove(tabRemove);
                    }

                }
            }

            if (!dashboard.isEmpty()) {
                menuTab.getItems().add(menuGroup);
                addDashboardToHome(dashboard);
            }

        }
    }

    private void clearDash() {
        hBoxDashGroups.getChildren().clear();
        menuTab.getItems().clear();
    }

    private void addDashboardToHome(Dashboard dashboard) {
        StackPane st = new StackPane(dashboard);
        st.setPadding(new Insets(10));
        StackPane stackDash = new StackPane(dashboard);
        HBox.setMargin(stackDash, new Insets(15));
        hBoxDashGroups.getChildren().add(stackDash);
    }

    /**
     *
     * @param user
     */
    public void logUser(User user) {
        setTexts();
        if (user.getStatus() != UserStatus.ON) {
            logOut();
        } else {
            this.user = user;

            userLabel.setText(user.getName());
            setProfileImage(user.getProfilePic());
            initAllDash();
            if (!user.getProfile().getName().equalsIgnoreCase("Admin")) {
                fileMenu.getItems().remove(settingsMenuItem);
            }
            Settings last = Settings.getLast();
            if (last.isShowTipOnStartup()) {
                showUserManual();
            }
        }
    }

    /**
     *
     */
    public void relog() {
        try {
            user.refresh();
            logUser(user);
        } catch (UserDeletedException ex) {
            logOut();
        }

    }

    private <T> List<T> cloneList(List<T> list) {
        List<T> returnList = new ArrayList<>();
        for (T t : list) {
            returnList.add(t);
        }

        return returnList;
    }

    private void setProfileImage(ImageFile imageFile) {
        Image image;
        if (imageFile == null) {
            image = SystemImageUtil.getImage(SystemImageUtil.DEFAULT_USER);
        } else {
            image = imageFile.getImage();
        }
        ImagePattern pattern = new ImagePattern(image);
        userImageCircle.setFill(pattern);
    }

    private int x;

    private void newSampleTab(ActionEvent event) {
        FXMLLoader tabLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/SampleTab.fxml"));
        StackPane rootTab = null;
        try {
            rootTab = tabLoader.load();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error loading tab", "Fatal Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            System.exit(1);
        }

        x++;
        Tab tab = new Tab("Sample Tab " + x, rootTab);
        tab.setClosable(true);
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);
    }

    @FXML
    private void myAccountHandle(ActionEvent event) {
        FXMLLoader tabLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/MyAccount.fxml"));
        StackPane rootTab = null;
        Tab tab = null;
        try {
            rootTab = tabLoader.load();

            tab = new Tab(Lang.get("My Account"), rootTab);

            TabController controller = tabLoader.<TabController>getController();

            controller.setTab(tab);
            controller.setHome(this);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error loading tab", "Fatal Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            System.exit(1);
        }

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
        }
        tabPane.getSelectionModel().select(tab);
    }

    @FXML
    private void logOutHandle(ActionEvent event) {

        logOut();

    }

    private void logOut() {
        loggedOut.setValue(!loggedOut.getValue());
    }

    /**
     *
     */
    public BooleanProperty loggedOut = new SimpleBooleanProperty(false);

    @FXML
    private void exit(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        SessionHibernateUtil.getSessionFactory().close();
        stage.close();
    }

    /**
     *
     * @return
     */
    public TabPane getTabPane() {
        return tabPane;
    }

    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    @FXML
    private void aboutItemHandle(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/About.fxml"));
            Parent root;
            root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.getIcons().add(SystemImageUtil.getImage(SystemImageUtil.WMSICON_128));
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.initStyle(StageStyle.DECORATED);
            stage.setResizable(true);
            stage.setTitle("About Pyxis WMS");

            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    @FXML
    private void settingsHandle(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/Settings.fxml"));
            Parent root;
            root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.getIcons().add(SystemImageUtil.getImage(SystemImageUtil.WMSICON_128));
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.initStyle(StageStyle.DECORATED);
            stage.setResizable(false);
            stage.setTitle("Settings");

            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    @FXML
    private void manualHandle(ActionEvent event) {
        showUserManual();
    }

    private void showUserManual() {
        ObservableList<Stage> stages = StageHelper.getStages();
        boolean open = false;
        for (Stage s : stages) {
            if (s.getTitle().equalsIgnoreCase(Lang.get("User Manual"))) {
                open = true;
                s.toFront();
            }
        }
        if (!open) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/UserManual.fxml"));
                Parent root;
                root = fxmlLoader.load();

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.getIcons().add(SystemImageUtil.getImage(SystemImageUtil.WMSICON_128));
                stage.setScene(scene);
                stage.initModality(Modality.NONE);

                stage.initStyle(StageStyle.DECORATED);
                stage.setResizable(false);
                stage.setTitle(Lang.get("User Manual"));
                UserManualController controller = fxmlLoader.<UserManualController>getController();
                stage.setAlwaysOnTop(true);
                stage.setOnCloseRequest(ev -> {
                    Settings last = Settings.getLast();
                    last.setShowTipOnStartup(controller.showTipOnStartup());
                    last.merge();
                });
                stage.show();

            } catch (IOException ex) {
                ex.printStackTrace();
                System.exit(1);
            }
        }
    }

}
