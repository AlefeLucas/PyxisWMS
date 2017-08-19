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
import br.com.senaimg.wms.model.sistema.enums.ImageCategory;
import br.com.senaimg.wms.model.warehouse.stock.item.Brand;
import br.com.senaimg.wms.model.warehouse.stock.item.ItemDimension;
import br.com.senaimg.wms.model.warehouse.stock.item.ItemGroup;
import br.com.senaimg.wms.model.warehouse.stock.item.ItemType;
import br.com.senaimg.wms.model.warehouse.stock.item.MetaItem;
import br.com.senaimg.wms.model.warehouse.stock.item.OperationType;
import br.com.senaimg.wms.util.SystemImageUtil;
import br.com.senaimg.wms.util.ValidateFieldUtil;
import br.com.senaimg.wms.view.layout.AlertCustom;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author ÁlefeLucas
 */
public class EditItemController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private StackPane titlePane;
    @FXML
    private Label titleLabel;
    @FXML
    private VBox centerPane;
    @FXML
    private GridPane formGridPaneItem;
    @FXML
    private Label mnemonicLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label brandLabel;
    @FXML
    private Label ean13Label;
    @FXML
    private Label minimumQuantityLabel;
    @FXML
    private Label itemTypeLabel;
    @FXML
    private Label cautionLabel;
    @FXML
    private TextField mnemonicTextField;
    @FXML
    private Tooltip mnemonicToolTip;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private Tooltip descriptionToolTip;
    @FXML
    private ComboBox<Brand> brandComboBox;
    @FXML
    private TextField ean13TextField;
    @FXML
    private Tooltip ean13ToolTip;
    @FXML
    private Spinner<Integer> minimumQuantitySpinner;
    @FXML
    private ComboBox<ItemType> itemTypeComboBox;
    @FXML
    private TextArea cautionTextArea;
    @FXML
    private Tooltip cautionToolTip;
    @FXML
    private GridPane formGridPaneDimensions;
    @FXML
    private Label heightLabel;
    @FXML
    private Label widthLabel;
    @FXML
    private Label lengthLabel;
    @FXML
    private Label weightLabel;
    @FXML
    private TextField heightTextField;
    @FXML
    private Tooltip heightToolTip;
    @FXML
    private TextField widthTextField;
    @FXML
    private Tooltip widthToolTip;
    @FXML
    private TextField lengthTextField;
    @FXML
    private Tooltip lengthToolTip;
    @FXML
    private TextField weightTextField;
    @FXML
    private Tooltip weightToolTip;
    @FXML
    private VBox rightPane;
    @FXML
    private Label imageLabel;
    @FXML
    private Rectangle squareImage;
    @FXML
    private Button selectImageButton;
    @FXML
    private HBox footPane;
    @FXML
    private Button buttonClean;
    @FXML
    private Button buttonSaveItem;
    @FXML
    private ComboBox<OperationType> operationTypeComboBox;
    @FXML
    private Label operationTypeLabel;

    public BooleanProperty inserted = new SimpleBooleanProperty(false);

    private MetaItem selectedMetaItem;
    private ImageFile image;

    private List<MetaItem> metaItems;
    @FXML
    private ComboBox<ItemGroup> groupComboBox;
    @FXML
    private Label groupLabel;
    @FXML
    private Spinner<Integer> maximumQuantitySpinner;
    @FXML
    private Label maximumQuantityLabel;
    @FXML
    private TextField sellingTextField;
    @FXML
    private Tooltip sellingToolTip;
    @FXML
    private Label sellingLabel;
    @FXML
    private Button unspecifyButton;
    @FXML
    private Label dimensionTitle;

    private Optional<ButtonType> showAlertSure(String title, String message) {
        Alert alertModify = new AlertCustom(Alert.AlertType.CONFIRMATION, Lang.get(message), ButtonType.YES, ButtonType.NO);
        alertModify.setTitle(Lang.get(title));
        Optional<ButtonType> result = alertModify.showAndWait();
        return result;
    }

    private void showAlertError(String title, String message) {
        Alert alertInvalid = new AlertCustom(Alert.AlertType.ERROR, Lang.get(message), ButtonType.OK);
        alertInvalid.setTitle(Lang.get(title));
        alertInvalid.show();
    }

       private void setTheme() {
        String path = "br/com/senaimg/wms/view/";
        String css = "edititem.css";
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTheme();
        setTexts();

        setSpinners();

        setBrands();
        setGroups();
        setOperations();
        setTypes();

        cleanImage();
        loadMetaItems();

        setFieldListeners();
    }

    private void setSpinners() {
        minimumQuantitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 1, 5));
        maximumQuantitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, Integer.MAX_VALUE, 5));
        minimumQuantitySpinner.valueProperty().addListener((obs, oldV, newV) -> {
            if (newV > maximumQuantitySpinner.getValue()) {
                maximumQuantitySpinner.getValueFactory().setValue(newV);
            }
        });

        maximumQuantitySpinner.valueProperty().addListener((obs, oldV, newV) -> {
            if (newV < minimumQuantitySpinner.getValue()) {
                minimumQuantitySpinner.getValueFactory().setValue(newV);
            }
        });
    }

    @FXML
    private void selectImageHandle(ActionEvent event) {
        File file = chooseFile();

        if (file != null) {
            try {
                setImageFromFile(file);

            } catch (IOException ex) {
                ex.printStackTrace();

            }

        }
    }

    @FXML
    private void cleanHandle(ActionEvent event) {
    }

    @FXML
    private void saveItemHandle(ActionEvent event) {
        boolean valid = areFieldsOk();

        if (valid) {
            Optional<ButtonType> result = showAlertSure("New Item", "Are you sure you want to save changes?");
            if (result.get() == ButtonType.YES) {
                String mnemonic = mnemonicTextField.getText();
                String description = descriptionTextArea.getText();
                Brand brand = brandComboBox.getValue();
                String ean13 = ean13TextField.getText();
                int minQt = minimumQuantitySpinner.getValue();
                int maxQt = maximumQuantitySpinner.getValue();
                OperationType operation = operationTypeComboBox.getValue();
                ItemGroup group = groupComboBox.getValue();
                ItemType type = itemTypeComboBox.getValue();
                String caution = cautionTextArea.getText();

                String strPrice = sellingTextField.getText();

                double price = Double.parseDouble(strPrice);

                String strHeight = heightTextField.getText();
                String strWidth = widthTextField.getText();
                String strLength = lengthTextField.getText();
                String strWeight = weightTextField.getText();

                double height = Double.parseDouble(strHeight);
                double width = Double.parseDouble(strWidth);
                double length = Double.parseDouble(strLength);
                double weight = Double.parseDouble(strWeight);

                ItemDimension dim = selectedMetaItem.getDimension();
                dim.setFields(weight, height, width, length);

                selectedMetaItem.setFields(caution, description, ean13, minQt, maxQt, mnemonic, price, brand, dim, group, operation, type);

                selectedMetaItem.update();

                inserted.setValue(Boolean.TRUE);
                inserted.setValue(Boolean.FALSE);

            }
        } else {
            showAlertError("Error", "Fill the data correctly");
        }

    }

    public void setItem(MetaItem meta) {
        selectedMetaItem = meta;

        mnemonicTextField.setText(meta.getMnemonic());
        descriptionTextArea.setText(meta.getDescription());

        brandComboBox.setValue(meta.getBrand());
        groupComboBox.setValue(meta.getGroup());

        ean13TextField.setText(meta.getEan13());
        minimumQuantitySpinner.getValueFactory().setValue(meta.getMinQuantity());
        maximumQuantitySpinner.getValueFactory().setValue(meta.getMaxQuantity());
        operationTypeComboBox.setValue(meta.getOperation());
        itemTypeComboBox.setValue(meta.getItemType());
        cautionTextArea.setText(meta.getCaution());

        sellingTextField.setText(meta.getSellingPrice() + "");

        ItemDimension dimension = meta.getDimension();

        heightTextField.setText(dimension.getHeight() + "");
        widthTextField.setText(dimension.getWidth() + "");
        lengthTextField.setText(dimension.getLength() + "");
        weightTextField.setText(dimension.getWeight() + "");

    }

    private void cleanImage() {
        image = null;
        setImage(SystemImageUtil.getImage(SystemImageUtil.DEFAULT_USER));
    }

    private void setImage(Image image) {
        ImagePattern pattern = new ImagePattern(image);
        squareImage.setFill(pattern);
    }

    private void setTexts() {
        titleLabel.setText(Lang.get(titleLabel.getText()));
        mnemonicLabel.setText(Lang.get(mnemonicLabel.getText()));
        
        
        descriptionLabel.setText(Lang.get(descriptionLabel.getText()));
        brandLabel.setText(Lang.get(brandLabel.getText()));
        groupLabel.setText(Lang.get(groupLabel.getText()));
        sellingLabel.setText(Lang.get(sellingLabel.getText()));
        ean13Label.setText(Lang.get(ean13Label.getText()));
        minimumQuantityLabel.setText(Lang.get(minimumQuantityLabel.getText()));
        maximumQuantityLabel.setText(Lang.get(maximumQuantityLabel.getText()));
        operationTypeLabel.setText(Lang.get(operationTypeLabel.getText()));
        itemTypeLabel.setText(Lang.get(itemTypeLabel.getText()));
        cautionLabel.setText(Lang.get(cautionLabel.getText()));
        unspecifyButton.setText(Lang.get(unspecifyButton.getText()));
        imageLabel.setText(Lang.get(imageLabel.getText()));
        selectImageButton.setText(Lang.get(selectImageButton.getText()));
        buttonClean.setText(Lang.get(buttonClean.getText()));
        buttonSaveItem.setText(Lang.get(buttonSaveItem.getText()));
        
        
        dimensionTitle.setText(Lang.get(dimensionTitle.getText()));
        heightLabel.setText(Lang.get(heightLabel.getText()));
        widthLabel.setText(Lang.get(widthLabel.getText()));
        lengthLabel.setText(Lang.get(lengthLabel.getText()));
        weightLabel.setText(Lang.get(weightLabel.getText()));
        itemTypeComboBox.setPromptText(Lang.get(itemTypeComboBox.getPromptText()));
    }

    private void loadMetaItems() {
        metaItems = MetaItem.list();
    }

    private File chooseFile() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(Lang.get(" All image files "), "*.png", "*.jpg", "*.jpeg", "*.jpe", "*.jfif", "*.gif", "*.tif", "*.tiff", "*.ico"),
                new FileChooser.ExtensionFilter(" PNG ", "*.png"),
                new FileChooser.ExtensionFilter(" JPEG ", "*.jpg", "*.jpeg", "*.jpe", "*.jfif"),
                new FileChooser.ExtensionFilter(" GIF ", "*.gif"),
                new FileChooser.ExtensionFilter(" TIFF ", "*.tif", "*.tiff"),
                new FileChooser.ExtensionFilter(" ICO ", "*.ico")
        );
        File file = fc.showOpenDialog(null);
        return file;
    }

    private void setImageFromFile(File file) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        String fileName = file.getName();

        ImageFile imageFile = new ImageFile(fileName, bytes, ImageCategory.USER_PROF);
        image = imageFile;

        Image img = image.getImage();
        if (img == null) {
            cleanImage();
        } else {
            setImage(img);
        }
    }

    private void showToolTip(Tooltip tooltip, Node node) {
        Window window = rootPane.getScene().getWindow();
        Bounds bounds = node.localToScreen(node.getBoundsInLocal());
        double x = bounds.getMinX();
        double y = bounds.getMaxY();
        tooltip.show(window, x, y);
    }

    private boolean areFieldsOk() {
        //boolean hasItem = hasValue(itemTypeComboBox);
        boolean hasGroup = hasValue(groupComboBox);
        boolean hasBrand = hasValue(brandComboBox);
        boolean hasOperation = hasValue(operationTypeComboBox);
        resetTextArea(descriptionTextArea);
        resetTextArea(cautionTextArea);
        boolean valid = validCaution && validDescription && validEan13 && validHeight && validLength && validMnemonic && validSelling && validWeight && validWidth && hasGroup && hasBrand && hasOperation;
        return valid;
    }

    private void resetTextArea(TextArea ta) {
        String text = ta.getText();
        ta.setText(Math.random() + "");
        ta.setText(text);
    }
    public <T> boolean hasValue(ComboBox<T> cb) {
        T value = cb.getValue();
        return value != null;
    }

    private boolean validMnemonic;
    private boolean validDescription;
    private boolean validSelling;
    private boolean validEan13;
    private boolean validCaution;
    private boolean validHeight;
    private boolean validWidth;
    private boolean validLength;
    private boolean validWeight;

    private void setFieldListeners() {
        setMnemonicListener();
        setDescriptionListener();
        setSellingListener();
        setEan13Listener();
        setCautionListener();
        setHeightListener();
        setWidthListener();
        setLengthListener();
        setWeightListener();
    }

    private void setWeightListener() {
        weightTextField.textProperty().addListener((observable, oldValue, newValue) -> {

            validWeight = ValidateFieldUtil.isValidDouble(newValue);

            if (!validWeight && !newValue.isEmpty()) {
                weightToolTip.setText(Lang.get("Invalid dimension"));
                showToolTip(weightToolTip, weightTextField);
            } else {
                weightToolTip.setText("");
                weightToolTip.hide();
            }
        });
    }

    private void setLengthListener() {
        lengthTextField.textProperty().addListener((observable, oldValue, newValue) -> {

            validLength = ValidateFieldUtil.isValidDouble(newValue);

            if (!validLength && !newValue.isEmpty()) {
                lengthToolTip.setText(Lang.get("Invalid dimension"));
                showToolTip(lengthToolTip, lengthTextField);
            } else {
                lengthToolTip.setText("");
                lengthToolTip.hide();
            }
        });
    }

    private void setWidthListener() {
        widthTextField.textProperty().addListener((observable, oldValue, newValue) -> {

            validWidth = ValidateFieldUtil.isValidDouble(newValue);

            if (!validWidth && !newValue.isEmpty()) {
                widthToolTip.setText(Lang.get("Invalid dimension"));
                showToolTip(widthToolTip, widthTextField);
            } else {
                widthToolTip.setText("");
                widthToolTip.hide();
            }
        });
    }

    private void setHeightListener() {
        heightTextField.textProperty().addListener((observable, oldValue, newValue) -> {

            validHeight = ValidateFieldUtil.isValidDouble(newValue);

            if (!validHeight && !newValue.isEmpty()) {
                heightToolTip.setText(Lang.get("Invalid dimension"));
                showToolTip(heightToolTip, heightTextField);
            } else {
                heightToolTip.setText("");
                heightToolTip.hide();
            }
        });
    }

    private void setCautionListener() {
        cautionTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            validCaution = !ValidateFieldUtil.biggerThan(newValue, 1023);

            if (!validCaution) {
                cautionToolTip.setText(Lang.get("Caution cannot be bigger than 1023 characters"));
                showToolTip(cautionToolTip, cautionTextArea);
            } else {
                cautionToolTip.setText("");
                cautionToolTip.hide();
            }
        });
    }

    private void setEan13Listener() {
        ean13TextField.textProperty().addListener((observable, oldValue, newValue) -> {
            validEan13 = ValidateFieldUtil.isValidEAN13(newValue);

            if (!validEan13 && !newValue.isEmpty()) {
                ean13ToolTip.setText(Lang.get("Invalid EAN13 barcode"));
                showToolTip(ean13ToolTip, ean13TextField);
            } else {
                ean13ToolTip.setText("");
                ean13ToolTip.hide();
            }
        });
    }

    private void setSellingListener() {
        sellingTextField.textProperty().addListener((observable, oldValue, newValue) -> {

            validSelling = ValidateFieldUtil.isValidDouble(newValue);

            if (!validSelling && !newValue.isEmpty()) {
                sellingToolTip.setText(Lang.get("Invalid price"));
                showToolTip(sellingToolTip, sellingTextField);
            } else {
                sellingToolTip.setText("");
                sellingToolTip.hide();
            }
        });
    }

    private void setDescriptionListener() {
        descriptionTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            validDescription = !ValidateFieldUtil.biggerThan(newValue, 1023);

            if (!validDescription) {
                descriptionToolTip.setText(Lang.get("Description cannot be bigger than 1023 characters"));
                showToolTip(descriptionToolTip, descriptionTextArea);
            } else {
                descriptionToolTip.setText("");
                descriptionToolTip.hide();
            }
        });
    }

    private void setMnemonicListener() {
        mnemonicTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            validMnemonic = !ValidateFieldUtil.biggerThan(newValue, 20);

            if (!validMnemonic) {
                mnemonicToolTip.setText(Lang.get("Mnemonic cannot be bigger than 20 characters"));
                showToolTip(mnemonicToolTip, mnemonicTextField);
            } else {
                mnemonicToolTip.setText("");
                mnemonicToolTip.hide();
            }
        });
    }

    private void setBrands() {
        List<Brand> brands = Brand.list();

        if (!brands.isEmpty()) {
            ObservableList<Brand> items = brandComboBox.getItems();

            items.addAll(brands);
            brandComboBox.setValue(items.get(0));
        }

    }

    private void setOperations() {
        List<OperationType> ops = OperationType.list();

        if (!ops.isEmpty()) {
            ObservableList<OperationType> items = operationTypeComboBox.getItems();

            items.addAll(ops);
            operationTypeComboBox.setValue(items.get(0));
        }
    }

    private void setTypes() {
        List<ItemType> types = ItemType.list();

        if (!types.isEmpty()) {
            ObservableList<ItemType> items = itemTypeComboBox.getItems();

            items.addAll(types);
            itemTypeComboBox.setValue(items.get(0));
        }
    }

    private void setGroups() {
        List<ItemGroup> types = ItemGroup.list();

        if (!types.isEmpty()) {
            ObservableList<ItemGroup> items = groupComboBox.getItems();

            items.addAll(types);
            groupComboBox.setValue(items.get(0));
        }
    }

    @FXML
    private void unspecifyHandle(ActionEvent event) {
        itemTypeComboBox.setValue(null);
    }

}
