/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.layout;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.sistema.Functionality;
import br.com.senaimg.wms.model.sistema.Settings;
import br.com.senaimg.wms.model.sistema.enums.CssTheme;
import br.com.senaimg.wms.view.controller.HomeController;
import br.com.senaimg.wms.view.controller.TabController;
import java.io.IOException;
import javafx.animation.ScaleTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 *
 * @author Alefe Lucas
 */
public class DashboardItem extends StackPane {

    private static Settings s;
    
    private StackPane rootPane;
    private Label titleLabel;
    private Image image;
    private Label contextLabel;
    private BorderPane borderPane;
    private StackPane centerPane;
    private HBox titlePane;
    private ContextMenu contextMenu;
    private MenuItem menuItemBackward;
    private MenuItem menuItemForward;
    private SeparatorMenuItem separator;
    private Menu sizeMenu;
    private ToggleGroup sizeGroup;
    private RadioMenuItem radioMenuBig;
    private RadioMenuItem radioMenuLarge;
    private RadioMenuItem radioMenuMedium;
    private RadioMenuItem radioMenuSmall;
    private ImageView imageView;
    private DashSize size;
    
    private static final int EXPAND = 10;
    private static final int IMAGEFIT = 70;
    private static final int IMAGEFIT_SMALL = 35;
    
    private ScaleTransition scale;
    
    private Functionality func;
    private TabPane tabPane;
    private HomeController home;

   
    
    public DashboardItem(DashSize size, Functionality func, HomeController home) {
        super();
        
        this.func = func;
        this.image = func.getImage();
        this.tabPane = home.getTabPane();
        this.home = home;
        
        rootPane = new StackPane();
        s = Settings.getLast();
   
        if(s.getTheme() == CssTheme.DARK){
        rootPane.setStyle("-fx-background-color:  linear-gradient(to right, #f29b12, #d4b020); -fx-base: #323232");
        } else {
            rootPane.setStyle("-fx-background-color:  linear-gradient(to right, #0C85AD, #0A96C4); -fx-base: lightgrey;");
        }
        scale = new ScaleTransition(Duration.seconds(1.0 / 8.0), rootPane);
        rootPane.setOnMouseEntered(e -> mouseEntered(e));
        rootPane.setOnMouseExited(e -> mouseExited(e));
        
        borderPane = new BorderPane();
        
        centerPane = new StackPane();
        imageView = new ImageView(this.image);
        imageView.setFitHeight(IMAGEFIT);
        imageView.setFitWidth(IMAGEFIT);
        centerPane.getChildren().add(imageView);
        borderPane.setCenter(centerPane);
        
        titlePane = new HBox();
        titleLabel = new Label(func.getTitle());
        HBox.setMargin(titleLabel, new Insets(0, 15, 4, 15));
        
        titleLabel.setTextFill(Color.WHITE);
        borderPane.setBottom(titlePane);
        
        contextLabel = new Label();
        contextLabel.setMaxHeight(Double.MAX_VALUE);
        contextLabel.setMaxWidth(Double.MAX_VALUE);
        
        contextMenu = new ContextMenu();
        menuItemBackward = new MenuItem(Lang.get("Move Backward"));
        menuItemForward = new MenuItem(Lang.get("Move Forward"));
        separator = new SeparatorMenuItem();
        sizeMenu = new Menu(Lang.get("Size"));
        
        sizeGroup = new ToggleGroup();
        radioMenuBig = new RadioMenuItem(Lang.get("Big"));
        radioMenuLarge = new RadioMenuItem(Lang.get("Large"));
        radioMenuMedium = new RadioMenuItem(Lang.get("Medium"));
        radioMenuSmall = new RadioMenuItem(Lang.get("Small"));
        
        radioMenuBig.setToggleGroup(sizeGroup);
        radioMenuLarge.setToggleGroup(sizeGroup);
        radioMenuMedium.setToggleGroup(sizeGroup);
        radioMenuSmall.setToggleGroup(sizeGroup);
        
        rootPane.setOnMouseReleased(e -> clicked(e));
        
        rootPane.getChildren().addAll(borderPane, contextLabel);
        
        sizeMenu.getItems().addAll(radioMenuBig, radioMenuLarge, radioMenuMedium, radioMenuSmall);
        
        contextMenu.getItems().addAll(menuItemBackward, menuItemForward, separator, sizeMenu);
        
        contextLabel.setContextMenu(contextMenu);
        
        this.getChildren().add(rootPane);
        
        setSize(size);
        
        setFirstRadioSelected();
        
    }
    
    private void setRootSize(DashSize size) {
        double width = size.getSpans().getWidth();
        double height = size.getSpans().getHeight();
        this.setPrefHeight(height + EXPAND);
        this.setPrefWidth(width + EXPAND);
    }

    /**
     *
     * @param size
     */
    public void setSize(DashSize size) {
        if (size.equals(DashSize.SMALL)) {
            adaptToSmall();
        } else {
            adaptToBig();
        }
        
        this.size = size;
        Dimension2D dim = size.getSpans();
        rootPane.setPrefHeight(dim.getHeight());
        rootPane.setPrefWidth(dim.getWidth());
        rootPane.setMaxHeight(dim.getHeight());
        rootPane.setMaxWidth(dim.getWidth());
        
        setRootSize(this.size);
        
        setScaleEffectProportion(this.size);
        
    }
    
    private void setScaleEffectProportion(DashSize size) {
        double width = size.getSpans().getWidth();
        double height = size.getSpans().getHeight();
        double x = (width + EXPAND) / width;
        double y = (height + EXPAND) / height;
        scale.setFromX(width / width);
        scale.setFromY(height / height);
        scale.setToX(x);
        scale.setToY(y);
    }
    
    private void adaptToSmall() {
        imageView.setFitHeight(IMAGEFIT_SMALL);
        imageView.setFitWidth(IMAGEFIT_SMALL);
        if (titlePane.getChildren().contains(titleLabel)) {
            titlePane.getChildren().remove(titleLabel);
        }
    }
    
    private void adaptToBig() {
        imageView.setFitHeight(IMAGEFIT);
        imageView.setFitWidth(IMAGEFIT);
        if (!titlePane.getChildren().contains(titleLabel)) {
            titlePane.getChildren().add(titleLabel);
        }
    }
    
    private void setFirstRadioSelected() {
        switch (this.size) {
            case BIG:
                radioMenuBig.setSelected(true);
                break;
            case LARGE:
                radioMenuLarge.setSelected(true);
                break;
            case MEDIUM:
                radioMenuMedium.setSelected(true);
                break;
            case SMALL:
                radioMenuSmall.setSelected(true);
                break;
        }
    }
    
    private void mouseExited(MouseEvent event) {
        scale.setRate(-1);
        scale.play();
    }
    
    private void mouseEntered(MouseEvent event) {
        scale.setRate(1);
        scale.play();
    }
    
   
    /**
     *
     * @return
     */
    public Image getImage() {
        return image;
    }

    /**
     *
     * @param image
     */
    public void setImage(Image image) {
        this.image = image;
        imageView.setImage(image);
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return titleLabel.getText();
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.titleLabel.setText(title);
    }

    /**
     *
     * @return
     */
    public DashSize getSize() {
        return size;
    }

    /**
     *
     * @return
     */
    public MenuItem getMenuItemBackward() {
        return menuItemBackward;
    }

    /**
     *
     * @return
     */
    public MenuItem getMenuItemForward() {
        return menuItemForward;
    }

    /**
     *
     * @return
     */
    public RadioMenuItem getRadioMenuBig() {
        return radioMenuBig;
    }

    /**
     *
     * @return
     */
    public RadioMenuItem getRadioMenuLarge() {
        return radioMenuLarge;
    }

    /**
     *
     * @return
     */
    public RadioMenuItem getRadioMenuMedium() {
        return radioMenuMedium;
    }

    /**
     *
     * @return
     */
    public RadioMenuItem getRadioMenuSmall() {
        return radioMenuSmall;
    }

    /**
     *
     * @return
     */
    public ToggleGroup getSizeGroup() {
        return sizeGroup;
    }
    
    private void clicked(MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY) {
            FXMLLoader tabLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/" + func.getTabfxml()));
            StackPane rootTab = null;
            Tab tab = null;
            try {
                
                
                
                rootTab = tabLoader.load();
                
                tab = new Tab(func.getTitle(), rootTab);
                
                TabController controller = tabLoader.<TabController>getController();
                
                controller.setTab(tab);
                controller.setHome(home);
                
                
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
    }
    
}
