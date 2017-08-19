/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.location.City;
import br.com.senaimg.wms.model.location.Country;
import br.com.senaimg.wms.model.location.State;
import br.com.senaimg.wms.util.ValidateFieldUtil;
import br.com.senaimg.wms.view.layout.AlertCustom;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author ÁlefeLucas
 */
public class PlacesController extends TabController implements Initializable {

    private List<Country> countries;
    private List<State> states;
    private List<City> cities;

    @FXML
    private Accordion accordion;
    @FXML
    private TitledPane countriesPane;
    @FXML
    private TextField nameCountryEdit;
    @FXML
    private TextField isoCountryEdit;
    @FXML
    private ComboBox<Country> comboBoxCountryEdit;
    @FXML
    private Button updateCountryButton;
    @FXML
    private Button deleteCountryButton;
    @FXML
    private ListView<State> listViewStatesWithinCountry;
    @FXML
    private TextField nameCountryNew;
    @FXML
    private TextField isoCountryNew;
    @FXML
    private Button newCountryButton;
    @FXML
    private TitledPane statesPane;
    @FXML
    private TitledPane citiesPane;
    @FXML
    private TableView<Country> countryTable;
    @FXML
    private TableColumn<Country, String> nameCountryColumn;
    @FXML
    private TableColumn<Country, String> isoCountryColumn;
//

    @FXML
    private ComboBox<Country> comboBoxCountrySelectState;
    @FXML
    private TextField nameStateEdit;
    @FXML
    private TextField isoStateEdit;
    @FXML
    private ComboBox<State> comboBoxStateEdit;
    @FXML
    private Button updateStateButton;
    @FXML
    private Button deleteStateButton;
    @FXML
    private ListView<City> listViewCitiesWithinState;
    @FXML
    private TextField nameStateNew;
    @FXML
    private TextField isoStateNew;
    @FXML
    private ComboBox<Country> comboBoxCountryNewState;
    @FXML
    private Button newStateButton;
    @FXML
    private TableView<State> stateTable;
    @FXML
    private TableColumn<State, String> nameStateColumn;
    @FXML
    private TableColumn<State, String> isoStateColumn;
    @FXML
    private ComboBox<Country> comboBoxCountrySelectCity;
    @FXML
    private TextField nameCityEdit;
    @FXML
    private ComboBox<State> comboBoxStateSelectCity;
    @FXML
    private ComboBox<City> comboBoxCityEdit;
    @FXML
    private Button updateCityButton;
    @FXML
    private Button deleteCityButton;
    @FXML
    private TextField nameCityNew;
    @FXML
    private ComboBox<Country> comboBoxCountryNewCity;
    @FXML
    private ComboBox<State> comboBoxStateNewCity;
    @FXML
    private Button newCityButton;
    @FXML
    private TableView<City> cityTable;
    @FXML
    private TableColumn<City, String> nameCityColumn;
    @FXML
    private StackPane rootTabPane;
    @FXML
    private Label editLabel;
    @FXML
    private Label countryLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label isoLabel;
    @FXML
    private Label newLabel;
    @FXML
    private Label nameLabelN;
    @FXML
    private Label EditLabel2;
    @FXML
    private Label countryLabel2;
    @FXML
    private Label nameLabel2;
    @FXML
    private Label stateLabel;
    @FXML
    private Label newLabel2;
    @FXML
    private Label countryLabelN;
    @FXML
    private Label editLabel3;
    @FXML
    private Label countryLabel3;
    @FXML
    private Label nameLabel3;
    @FXML
    private Label cityLabel;
    @FXML
    private Label newLabelC;
    @FXML
    private Label nameLabelC;
    @FXML
    private Label countryLabelC;
    @FXML
    private Label stateLabelC;

    //
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTexts();
        load();
    }
    
     private void setTexts() {
        editLabel.setText(Lang.get(editLabel.getText()));
        countriesPane.setText(Lang.get(countriesPane.getText()));
        countryLabel.setText(Lang.get(countryLabel.getText()));
        nameLabel.setText(Lang.get(nameLabel.getText()));
        deleteCountryButton.setText(Lang.get(deleteCountryButton.getText()));
        updateCountryButton.setText(Lang.get(updateCountryButton.getText()));
        newLabel.setText(Lang.get(newLabel.getText()));
        nameLabelN.setText(Lang.get(nameLabelN.getText()));
        newCountryButton.setText(Lang.get(newCountryButton.getText()));
        ObservableList<TableColumn<Country, ?>> columns = countryTable.getColumns();
        for (TableColumn<Country, ?> column : columns) {
            column.setText(Lang.get(column.getText()));
        }
        statesPane.setText(Lang.get(statesPane.getText()));
        EditLabel2.setText(Lang.get(EditLabel2.getText()));
        countryLabel2.setText(Lang.get(countryLabel2.getText()));
        nameLabel2.setText(Lang.get(nameLabel2.getText()));
        stateLabel.setText(Lang.get(stateLabel.getText()));
        deleteStateButton.setText(Lang.get(deleteStateButton.getText()));
        updateStateButton.setText(Lang.get(updateStateButton.getText()));
        newLabel2.setText(Lang.get(newLabel2.getText()));
        countryLabelN.setText(Lang.get(countryLabelN.getText()));

        newStateButton.setText(Lang.get(newStateButton.getText()));
        newCityButton.setText(Lang.get(newCityButton.getText()));
        ObservableList<TableColumn<State, ?>> columnsS = stateTable.getColumns();
        for (TableColumn<State, ?> column : columnsS) {
            column.setText(Lang.get(column.getText()));
        }
        citiesPane.setText(Lang.get(citiesPane.getText()));
        countryLabel3.setText(Lang.get(countryLabel3.getText()));

        cityLabel.setText(Lang.get(cityLabel.getText()));
        nameLabel3.setText(Lang.get(nameLabel3.getText()));
        deleteCityButton.setText(Lang.get(deleteCityButton.getText()));
        updateCityButton.setText(Lang.get(updateCityButton.getText()));
        newLabelC.setText(Lang.get(newLabelC.getText()));
        countryLabelC.setText(Lang.get(countryLabelC.getText()));
        stateLabelC.setText(Lang.get(stateLabelC.getText()));
        nameLabelC.setText(Lang.get(nameLabelC.getText()));
        ObservableList<TableColumn<City, ?>> columnsC = cityTable.getColumns();
        for (TableColumn<City, ?> column : columnsC) {
            column.setText(Lang.get(column.getText()));
        }
    }

    private void load() {
        countries = Country.list();
        states = State.list();
        statesPane.setCollapsible(!countries.isEmpty());

        cities = City.list();
        citiesPane.setCollapsible(!states.isEmpty());

        clean();

        loadCountries();
        loadStates();
        loadCities();
    }

    private void loadCountries() {
        loadComboBoxCountryEdit();
        setTableCountry();

    }

    private void setTableCountry() {
        setCountryTableColumns();
        setTableCountryItems();
    }

    private void setTableCountryItems() {
        ObservableList<Country> items = countryTable.getItems();
        items.clear();
        for (Country country : countries) {
            items.add(country);
        }
    }

    private void loadComboBoxCountryEdit() {
        ObservableList<Country> items = comboBoxCountryEdit.getItems();
        items.clear();
        items.addAll(countries);
        setComboBoxCountryEditListener();
        if (!items.isEmpty()) {
            comboBoxCountryEdit.setValue(items.get(0));
            comboBoxCountryEdit.setDisable(false);
            updateCountryButton.setDisable(false);
            deleteCountryButton.setDisable(false);
        } else {
            comboBoxCountryEdit.setDisable(true);
            updateCountryButton.setDisable(true);
            deleteCountryButton.setDisable(true);
        }

    }

    private void loadComboBoxCountrySelectState() {
        ObservableList<Country> items = comboBoxCountrySelectState.getItems();
        items.clear();
        items.addAll(countries);
        setComboBoxCountryEditListener();
        if (!items.isEmpty()) {
            comboBoxCountrySelectState.setValue(items.get(0));
            comboBoxCountrySelectState.setDisable(false);

        } else {
            comboBoxCountrySelectState.setDisable(true);

        }

    }

    private void loadComboBoxCountryNewState() {
        ObservableList<Country> items = comboBoxCountryNewState.getItems();
        items.clear();
        items.addAll(countries);
        setComboBoxCountryEditListener();
        if (!items.isEmpty()) {
            comboBoxCountryNewState.setValue(items.get(0));
            comboBoxCountryNewState.setDisable(false);
            newStateButton.setDisable(false);

        } else {
            comboBoxCountryNewState.setDisable(true);
            newStateButton.setDisable(true);
        }

    }

    private void setComboBoxCountryEditListener() {
        comboBoxCountryEdit.valueProperty().addListener(e -> {
            ObservableList<State> statesList = listViewStatesWithinCountry.getItems();
            statesList.clear();

            Country value = comboBoxCountryEdit.getValue();
            if (value != null) {
                if (value.getStates() != null && !value.getStates().isEmpty()) {
                    statesList.addAll(value.getStates());
                }
                nameCountryEdit.setText(value.getName());
                isoCountryEdit.setText(value.getIso());
            } else {
                nameCountryEdit.setText("");
                isoCountryEdit.setText("");
            }

        });
    }

    private void setCountryTableColumns() {
        nameCountryColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        isoCountryColumn.setCellValueFactory(new PropertyValueFactory<>("iso"));
    }

    private void setStateTableColumns() {
        nameStateColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        isoStateColumn.setCellValueFactory(new PropertyValueFactory<>("iso"));
    }

    private void setCityTableColumns() {
        nameCityColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

    }

    private void loadStates() {
        setStateTableColumns();
        setComboBoxStateEditListener();
        setComboBoxCountrySelectStateListener();
        loadComboBoxCountrySelectState();
        loadComboBoxCountryNewState();

    }

    private void setComboBoxCountrySelectStateListener() {
        comboBoxCountrySelectState.valueProperty().addListener(e -> {
            Country value = comboBoxCountrySelectState.getValue();
            ObservableList<State> items = comboBoxStateEdit.getItems();
            items.clear();

            ObservableList<State> itemsTable = stateTable.getItems();
            itemsTable.clear();

            if (value != null && value.getStates() != null && !value.getStates().isEmpty()) {
                items.addAll(value.getStates());
                itemsTable.addAll(value.getStates());

            }
            if (!items.isEmpty()) {
                comboBoxStateEdit.setValue(items.get(0));
            }

        });
    }

    private void setComboBoxStateEditListener() {
        comboBoxStateEdit.valueProperty().addListener(e -> {
            ObservableList<City> citiesList = listViewCitiesWithinState.getItems();
            citiesList.clear();
            State state = comboBoxStateEdit.getValue();

            if (state != null) {

                citiesList.addAll(state.getCities());

                nameStateEdit.setText(state.getName());
                isoStateEdit.setText(state.getIso());
                comboBoxStateEdit.setDisable(false);
                updateStateButton.setDisable(false);
                deleteStateButton.setDisable(false);

            } else {
                nameStateEdit.setText("");
                isoStateEdit.setText("");
                comboBoxStateEdit.setDisable(true);
                updateStateButton.setDisable(true);
                deleteStateButton.setDisable(true);

            }
        });
    }

    private void loadCities() {

        setCityTableColumns();

        setComboBoxCityEditListener();

        setComboBoxStateSelectCityListener();

        setComboBoxCountrySelectCityListener();

        loadComboBoxCountrySelectCity();

        setComboBoxCountryNewCityListener();

        loadComboBoxCountryNewCity();

    }

    private void setComboBoxCountryNewCityListener() {
        comboBoxCountryNewCity.valueProperty().addListener(e -> {
            Country value = comboBoxCountryNewCity.getValue();
            ObservableList<State> items = comboBoxStateNewCity.getItems();
            items.clear();
            if (value != null && value.getStates() != null && !value.getStates().isEmpty()) {
                items.addAll(value.getStates());
                comboBoxStateNewCity.setDisable(false);
                comboBoxStateNewCity.setValue(items.get(0));
            } else {
                comboBoxStateNewCity.setDisable(true);
            }
        });
    }

    private void setComboBoxCityEditListener() {
        comboBoxCityEdit.valueProperty().addListener(e -> {
            City city = comboBoxCityEdit.getValue();
            if (city != null) {
                nameCityEdit.setText(city.getName());
            } else {
                nameCityEdit.setText("");
            }
        });
    }

    private void setComboBoxStateSelectCityListener() {
        comboBoxStateSelectCity.valueProperty().addListener(e -> {
            State value = comboBoxStateSelectCity.getValue();
            ObservableList<City> items = comboBoxCityEdit.getItems();
            items.clear();

            ObservableList<City> items1 = cityTable.getItems();
            items1.clear();
            if (value != null) {
                List<City> cities1 = value.getCities();
                if (cities1 != null && !cities1.isEmpty()) {
                    items.addAll(cities1);
                    items1.addAll(cities1);
                    comboBoxCityEdit.setValue(items.get(0));
                    comboBoxCityEdit.setDisable(false);
                    updateCityButton.setDisable(false);
                    deleteCityButton.setDisable(false);
                    System.out.println("FALSE");
                } else {
                    comboBoxCityEdit.setDisable(true);
                    updateCityButton.setDisable(true);
                    deleteCityButton.setDisable(true);
                    System.out.println("TRUE");
                }
            }
        });
    }

    private void setComboBoxCountrySelectCityListener() {
        comboBoxCountrySelectCity.valueProperty().addListener(e -> {
            Country value = comboBoxCountrySelectCity.getValue();
            ObservableList<State> items = comboBoxStateSelectCity.getItems();
            items.clear();
            if (value != null && value.getStates() != null && !value.getStates().isEmpty()) {
                items.addAll(value.getStates());
                comboBoxStateSelectCity.setDisable(false);
                comboBoxStateSelectCity.setValue(items.get(0));

            } else {
                comboBoxStateSelectCity.setDisable(true);
            }
        });
    }

    private void loadComboBoxCountrySelectCity() {
        ObservableList<Country> items = comboBoxCountrySelectCity.getItems();
        items.clear();
        items.addAll(countries);

        if (!items.isEmpty()) {
            comboBoxCountrySelectCity.setValue(items.get(0));

            comboBoxCountrySelectCity.setDisable(false);
            // comboBoxStateSelectCity.setDisable(false);

        } else {
            comboBoxCountrySelectCity.setDisable(true);
            comboBoxStateSelectCity.setDisable(true);

        }
    }

    private void loadComboBoxCountryNewCity() {
        ObservableList<Country> items = comboBoxCountryNewCity.getItems();
        items.clear();
        items.addAll(countries);

        if (!items.isEmpty()) {
            comboBoxCountryNewCity.setValue(items.get(0));

            comboBoxCountryNewCity.setDisable(false);
            //comboBoxStateNewCity.setDisable(false);

            newCityButton.setDisable(false);
        } else {
            comboBoxCountryNewCity.setDisable(true);
            comboBoxStateNewCity.setDisable(true);

            newCityButton.setDisable(true);
        }
    }

    @FXML
    private void newCountry(ActionEvent event) {
        String name = nameCountryNew.getText().trim();
        nameCountryNew.setText(name);
        String iso = isoCountryNew.getText().trim().toUpperCase();
        isoCountryNew.setText(iso);

        boolean exists = false;

        for (Country country : countries) {
            if (country.getName().equalsIgnoreCase(name) || country.getIso().equalsIgnoreCase(iso)) {
                exists = true;
                break;
            }
        }

        if (ValidateFieldUtil.isValidName(name) && ValidateFieldUtil.isValidIso(iso) && !exists) {
            Optional<ButtonType> result = showAlertSure(Lang.get("Are you sure you want to insert new Country?"));
            if (result.get() == ButtonType.YES) {
                Country country = new Country(name, iso);
                country.insert();
                load();
            }
        } else if (exists) {
            showAlertError(Lang.get("Country name and iso must be unique"));
        } else {
            showAlertError(Lang.get("Fill the data correctly"));
        }
    }

    @FXML
    private void newState(ActionEvent event) {
        Country country = comboBoxCountryNewState.getValue();
        if (country != null) {
            List<State> states = country.getStates();

            String name = nameStateNew.getText().trim();
            nameStateNew.setText(name);
            String iso = isoStateNew.getText().trim().toUpperCase();
            isoStateNew.setText(iso);

            boolean exists = false;

            for (State state : states) {
                if (state.getName().equalsIgnoreCase(name) || state.getIso().equalsIgnoreCase(iso)) {
                    exists = true;
                    break;
                }
            }

            if (ValidateFieldUtil.isValidName(name) && ValidateFieldUtil.isValidIso(iso) && !exists) {
                Optional<ButtonType> result = showAlertSure(Lang.get("Are you sure you want to insert new State?"));
                if (result.get() == ButtonType.YES) {
                    State state = new State(name, iso, country);
                    state.insert();
                    load();
                }
            } else if (exists) {
                showAlertError(Lang.get("State name and iso must be unique within a country"));
            } else {
                showAlertError(Lang.get("Fill the data correctly"));
            }

        } else {
            showAlertError(Lang.get("No country inserted"));
        }
    }

    @FXML
    private void newCity(ActionEvent event) {
        State state = comboBoxStateNewCity.getValue();
        if (state != null) {
            List<City> cities = state.getCities();

            String name = nameCityNew.getText().trim();
            nameCityNew.setText(name);

            boolean exists = false;

            for (City city : cities) {
                if (city.getName().equalsIgnoreCase(name)) {
                    exists = true;
                    break;
                }
            }

            if (ValidateFieldUtil.isValidName(name) && !exists) {
                Optional<ButtonType> result = showAlertSure(Lang.get("Are you sure you want to insert new City?"));
                if (result.get() == ButtonType.YES) {
                    City city = new City(name, state);
                    city.insert();
                    load();
                }
            } else if (exists) {
                showAlertError(Lang.get("City name must be unique within a state"));
            } else {
                showAlertError(Lang.get("Fill the data correctly"));
            }

        } else {
            showAlertError(Lang.get("No state inserted"));
        }
    }

    @FXML
    private void deleteCountry(ActionEvent event) {
        Country country = comboBoxCountryEdit.getValue();
        if (country != null) {
            Optional<ButtonType> result = showAlertSure(Lang.get("Are you sure you want to delete ") + country.getName() + "?");
            if (result.get() == ButtonType.YES) {
                country.delete();
                load();
            }
        } else {
            showAlertError(Lang.get("No country selected"));
        }
    }

    @FXML
    private void deleteState(ActionEvent event) {
        State state = comboBoxStateEdit.getValue();
        if (state != null) {
            Optional<ButtonType> result = showAlertSure(Lang.get("Are you sure you want to delete ") + state.getName() + "?");
            if (result.get() == ButtonType.YES) {
                state.delete();
                load();
            }
        } else {
            showAlertError(Lang.get("No country selected"));
        }
    }

    @FXML
    private void deleteCity(ActionEvent event) {
        City city = comboBoxCityEdit.getValue();
        if (city != null) {
            Optional<ButtonType> result = showAlertSure(Lang.get("Are you sure you want to delete ") + city.getName() + "?");
            if (result.get() == ButtonType.YES) {
                city.delete();
                load();
            }
        } else {
            showAlertError(Lang.get("No country selected"));
        }
    }

    @FXML
    private void updateCountry(ActionEvent event) {
        Country countryUpdate = comboBoxCountryEdit.getValue();
        if (countryUpdate != null) {
            String name = nameCountryEdit.getText().trim();
            nameCountryEdit.setText(name);
            String iso = isoCountryEdit.getText().trim().toUpperCase();
            isoCountryEdit.setText(iso);

            boolean exists = false;

            for (Country country : countries) {
                if (country == countryUpdate) {
                    continue;
                }
                if (country.getName().equalsIgnoreCase(name) || country.getIso().equalsIgnoreCase(iso)) {
                    exists = true;
                    break;
                }
            }

            if (ValidateFieldUtil.isValidName(name) && ValidateFieldUtil.isValidIso(iso) && !exists) {
                Optional<ButtonType> result = showAlertSure (Lang.get("Are you sure you want to update ") + countryUpdate.getName() + "?");
                if (result.get() == ButtonType.YES) {
                    countryUpdate.setName(name);
                    countryUpdate.setIso(iso);
                    countryUpdate.update();
                    load();
                }
            } else if (exists) {
                showAlertError(Lang.get("Country name and iso must be unique"));
            } else {
                showAlertError(Lang.get("Fill the data correctly"));
            }
        } else {
            showAlertError(Lang.get("No country selected"));
        }
    }

    @FXML
    private void updateState(ActionEvent event) {
        State stateUpdate = comboBoxStateEdit.getValue();
        Country country = comboBoxCountryNewState.getValue();
        if (stateUpdate != null && country != null) {

            List<State> states = country.getStates();

            String name = nameStateEdit.getText().trim();
            nameStateEdit.setText(name);
            String iso = isoStateEdit.getText().trim().toUpperCase();
            isoStateEdit.setText(iso);

            boolean exists = false;

            for (State state : states) {
                if (state == stateUpdate) {
                    continue;

                }
                if (state.getName().equalsIgnoreCase(name) || state.getIso().equalsIgnoreCase(iso)) {
                    exists = true;
                    break;
                }
            }

            if (ValidateFieldUtil.isValidName(name) && ValidateFieldUtil.isValidIso(iso) && !exists) {
                Optional<ButtonType> result = showAlertSure(Lang.get("Are you sure you want to update ") + stateUpdate.getName() + "?");
                if (result.get() == ButtonType.YES) {

                    stateUpdate.setName(name);
                    stateUpdate.setIso(iso);
                    stateUpdate.update();
                    load();
                }
            } else if (exists) {
                showAlertError(Lang.get("State name and iso must be unique within a country"));
            } else {
                showAlertError(Lang.get("Fill the data correctly"));
            }

        } else {
            showAlertError(Lang.get("No country selected"));

        }
    }

    @FXML
    private void updateCity(ActionEvent event) {

        City cityUpdate = comboBoxCityEdit.getValue();
        State state = comboBoxStateSelectCity.getValue();
        if (state != null && cityUpdate != null) {
            List<City> cities = state.getCities();

            String name = nameCityEdit.getText().trim();
            nameCityEdit.setText(name);

            boolean exists = false;

            for (City city : cities) {
                if (city == cityUpdate) {
                    continue;
                }
                if (city.getName().equalsIgnoreCase(name)) {
                    exists = true;
                    break;
                }
            }

            if (ValidateFieldUtil.isValidName(name) && !exists) {
                Optional<ButtonType> result = showAlertSure(Lang.get("Are you sure you want to update ") + cityUpdate.getName() + "?");
                if (result.get() == ButtonType.YES) {
                    cityUpdate.setName(name);
                    cityUpdate.update();
                    load();
                }
            } else if (exists) {
                 showAlertError(Lang.get("City name must be unique within a state"));
            } else {
                showAlertError(Lang.get("Fill the data correctly"));
            }

        } else {
            showAlertError(Lang.get("No state inserted"));
        }

    }

    private Optional<ButtonType> showAlertSure(String message) {
        Alert alertModify = new AlertCustom(Alert.AlertType.CONFIRMATION, message, ButtonType.YES, ButtonType.NO);
        alertModify.setTitle(Lang.get("Confirmation"));
        Optional<ButtonType> result = alertModify.showAndWait();
        return result;
    }

    private void showAlertError(String message) {
        Alert alertInvalid = new AlertCustom(Alert.AlertType.ERROR, message, ButtonType.OK);
        alertInvalid.setTitle(Lang.get("Error"));
        alertInvalid.show();
    }

    private void clean() {

        cleanComboBox(comboBoxCountryEdit);

        cleanComboBox(comboBoxCountrySelectState);
        cleanComboBox(comboBoxStateEdit);
        cleanComboBox(comboBoxCountryNewState);

        cleanComboBox(comboBoxCountrySelectCity);
        cleanComboBox(comboBoxStateSelectCity);
        cleanComboBox(comboBoxCityEdit);
        cleanComboBox(comboBoxCountryNewCity);
        cleanComboBox(comboBoxStateNewCity);

        cleanTable(countryTable);
        cleanTable(stateTable);
        cleanTable(cityTable);

    }

    private void cleanComboBox(ComboBox cb) {
        ObservableList items = cb.getItems();
        if (items != null) {
            items.clear();
        }
    }

    private void cleanTable(TableView table) {
        ObservableList items = table.getItems();
        if (items != null) {
            items.clear();
        }
    }
}
