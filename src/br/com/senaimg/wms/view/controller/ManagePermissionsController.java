/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.view.adapter.TablePermissionsAdapter;
import br.com.senaimg.wms.model.sistema.Functionality;
import br.com.senaimg.wms.model.sistema.Permission;
import br.com.senaimg.wms.model.sistema.Profile;
import br.com.senaimg.wms.model.sistema.User;
import br.com.senaimg.wms.view.layout.AlertCustom;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Alefe Lucas
 */
public class ManagePermissionsController extends TabController implements Initializable {

    @FXML
    private StackPane rootTabPane;
    @FXML
    private BorderPane borderPane;
    @FXML
    private VBox leftVBoxPane;
    @FXML
    private StackPane centerStackPane;
    @FXML
    private TableView<TablePermissionsAdapter> tableViewPermissions;
    @FXML
    private TableColumn<TablePermissionsAdapter, String> tableColumnGroup;
    @FXML
    private TableColumn<TablePermissionsAdapter, String> tableColumnFunction;
    @FXML
    private TableColumn<TablePermissionsAdapter, CheckBox> tableColumnVisible;
    @FXML
    private ComboBox<Profile> profileComboBox;
    @FXML
    private Button newProfileButton;
    @FXML
    private ListView<User> usersListView;
    @FXML
    private TextField profileTextField;

    @FXML
    private Button modifyButton;
    @FXML
    private Button deleteButton;

    List<TablePermissionsAdapter> adapters;
    @FXML
    private TextField profileNameTextField;
    @FXML
    private Label label2;
    @FXML
    private Tooltip renameToolTip;
    @FXML
    private Label label1;
    @FXML
    private Label label3;
    @FXML
    private Tooltip newProfileToolTip;
    @FXML
    private Label label4;

    private void setTexts() {
        label1.setText(Lang.get(label1.getText()));
        label2.setText(Lang.get(label2.getText()));
        profileComboBox.setPromptText(Lang.get(profileComboBox.getPromptText()));
        label3.setText(Lang.get(label3.getText()));
        newProfileButton.setText(Lang.get(newProfileButton.getText()));
        label4.setText(Lang.get(label4.getText()));
        ObservableList<TableColumn<TablePermissionsAdapter, ?>> columns = tableViewPermissions.getColumns();
        for (TableColumn<TablePermissionsAdapter, ?> column : columns) {
            column.setText(Lang.get(column.getText()));
        }
        deleteButton.setText(Lang.get(deleteButton.getText()));
        modifyButton.setText(Lang.get(modifyButton.getText()));
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTexts();
        setTableColumns();

        profileComboBox.getItems().addAll(Profile.list());

        modifyButton.setDisable(true);
        deleteButton.setDisable(true);

        profileComboBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Profile> observable, Profile oldValue, Profile newValue) -> {
            if (newValue == null) {
                usersListView.getItems().clear();
                tableViewPermissions.getItems().clear();
            } else {
                profileNameTextField.setText(newValue.getName());
                usersListView.getItems().clear();
                usersListView.getItems().addAll(newValue.getUsers());

                List<Functionality> allFunctions = Functionality.list();
                Set<Permission> permissions = newValue.getPermissions();
                adapters = new ArrayList<>();

                tableViewPermissions.getItems().clear();
                modifyButton.setDisable(false);
                deleteButton.setDisable(false);

                for (Functionality function : allFunctions) {
                    TablePermissionsAdapter adapter = new TablePermissionsAdapter(function.getFunctionalityGroup().getTitle(), function.getTitle(), function);

                    for (Permission permission : permissions) {
                        if (permission.getFunction().getId().equals(function.getId())) {
                            adapter.setOn(true);

                            if (newValue.getName().equalsIgnoreCase("admin")) {
                                adapter.setDisable(true);
                                modifyButton.setDisable(true);
                                deleteButton.setDisable(true);
                            }
                        }
                    }
                    adapters.add(adapter);

                    tableViewPermissions.getItems().add(adapter);
                }
            }
        });

        tableViewPermissions.placeholderProperty().setValue(new Label("Empty"));
    }

    private void setTableColumns() {
        tableColumnGroup.setCellValueFactory(new PropertyValueFactory<>("group"));
        tableColumnFunction.setCellValueFactory(new PropertyValueFactory<>("function"));
        tableColumnVisible.setCellValueFactory(new PropertyValueFactory<>("onPane"));
    }

    @FXML
    private void profileComboBoxHandle(ActionEvent event) {

    }

    @FXML
    private void newProfileButtonHandle(ActionEvent event) {

        String newProfileName = profileTextField.getText().trim();
        profileTextField.setText(newProfileName);
        Profile profile = new Profile(newProfileName);
        boolean exists = false;

        ObservableList<Profile> profiles = profileComboBox.getItems();
        for (Profile prof : profiles) {
            if (prof.getName().equalsIgnoreCase(profile.getName())) {
                exists = true;
            }
        }
        if (!exists) {
            Alert alertModify = new AlertCustom(Alert.AlertType.CONFIRMATION, Lang.get("Are you sure you want to create a new profile?"), ButtonType.YES, ButtonType.NO);
            alertModify.setTitle(Lang.get("Create New Profile"));
            Optional<ButtonType> result = alertModify.showAndWait();
            if (result.get() == ButtonType.YES) {
                profile.insert();
                profileComboBox.getItems().add(profile);
            }
        } else {
            showProfileAlreadyExists();

        }
    }

    @FXML
    private void deleteButtonHandle(ActionEvent event) {
        Profile profile = profileComboBox.getValue();
        Alert alertModify = new AlertCustom(Alert.AlertType.CONFIRMATION, Lang.get("Are you sure you want to delete ") + profile.getName() + "?", ButtonType.YES, ButtonType.NO);
        alertModify.setTitle(Lang.get("Delete Profile"));
        Optional<ButtonType> result = alertModify.showAndWait();
        if (result.get() == ButtonType.YES) {
            profileComboBox.getItems().remove(profile);
            profile.delete();
            getHome().relog();
        }
    }

    @FXML
    private void modifyButtonHandle(ActionEvent event) {
        Profile profile = profileComboBox.getValue();
        String newName = profileNameTextField.getText().trim();
        profileNameTextField.setText(newName);

        boolean profileExists = false;
        for (Profile p : profileComboBox.getItems()) {
            if (p.getName().equals(newName) && !p.getId().equals(profile.getId())) {
                profileExists = true;
            }
        }
        if (!profileExists) {

            Alert alertModify = new AlertCustom(Alert.AlertType.CONFIRMATION, Lang.get("Are you sure you want to modify ") + profile.getName() + "?", ButtonType.YES, ButtonType.NO);
            alertModify.setTitle(Lang.get("Modify Profile"));
            Optional<ButtonType> result = alertModify.showAndWait();
            if (result.get() == ButtonType.YES) {
                Set<Permission> permissions = profile.getPermissions();

                for (Permission permission : permissions) {
                    permission.delete();
                }

                for (TablePermissionsAdapter adapter : adapters) {
                    if (adapter.isOn()) {
                        Functionality functionality = adapter.getFunctionality();

                        Permission permission = new Permission(profile, functionality);
                        permission.insert();
                    }
                }

                profile.setName(newName);

                profile.update();
                profileComboBox.getItems().clear();
                profileComboBox.getItems().addAll(Profile.list());
                profileNameTextField.setText("");
                modifyButton.setDisable(false);
                deleteButton.setDisable(false);
                getHome().relog();
            }

        } else {
            showProfileAlreadyExists();
        }
    }

    private void showProfileAlreadyExists() {
        Alert alertInvalid = new AlertCustom(Alert.AlertType.ERROR, Lang.get("Profile already exists"), ButtonType.OK);
        alertInvalid.setTitle(Lang.get("Error"));
        alertInvalid.show();
    }
}
