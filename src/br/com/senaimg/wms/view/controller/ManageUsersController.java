/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.controller;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.sistema.Profile;
import br.com.senaimg.wms.model.sistema.User;
import br.com.senaimg.wms.view.adapter.TableUsersAdapter;
import br.com.senaimg.wms.view.layout.AlertCustom;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class ManageUsersController extends TabController implements Initializable {

    @FXML
    private StackPane rootTabPane;
    @FXML
    private Button insertButton;
    @FXML
    private Button modifyButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField searchTextField;
    @FXML
    private TableView<TableUsersAdapter> userTableView;
    @FXML
    private TableColumn<TableUsersAdapter, String> statusColumn;
    @FXML
    private TableColumn<TableUsersAdapter, String> usernameColumn;
    @FXML
    private TableColumn<TableUsersAdapter, String> nameColumn;
    @FXML
    private TableColumn<TableUsersAdapter, String> profileColumn;
    @FXML
    private TableColumn<TableUsersAdapter, String> emailColumn;
    @FXML
    private TableColumn<TableUsersAdapter, String> birthdateColumn;
    @FXML
    private TableColumn<TableUsersAdapter, String> genderColumn;
    @FXML
    private ComboBox<String> filterComboBox;

    private ObservableList<TableUsersAdapter> masterData = FXCollections.observableArrayList();

    private TableUsersAdapter selectedUser;
    private List<User> users;
    @FXML
    private Label label1;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setTableViewClickedHandle();

        setTableViewContextMenu();

        setFilterComboBox();

        setTableColumns();

        loadUsers();
setTexts();

    }
 private void setTexts() {
        insertButton.setText(Lang.get(insertButton.getText()));
        modifyButton.setText(Lang.get(modifyButton.getText()));
        deleteButton.setText(Lang.get(deleteButton.getText()));
        label1.setText(Lang.get(label1.getText()));
        searchTextField.setPromptText(Lang.get(searchTextField.getPromptText()));
        ObservableList<TableColumn<TableUsersAdapter, ?>> columns = userTableView.getColumns();
        for (TableColumn<TableUsersAdapter, ?> column : columns) {
            column.setText(Lang.get(column.getText()));
        }
        
        
    }
    /**
     *
     */
    public void setTableViewClickedHandle() {
        userTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedItem) -> {
            if (selectedItem == null) {
                modifyButton.setDisable(true);
                deleteButton.setDisable(true);
                selectedUser = null;
            } else {
                selectedUser = selectedItem;
                modifyButton.setDisable(false);
                deleteButton.setDisable(false);
            }
        });
    }

    private void setTableColumns() {
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        profileColumn.setCellValueFactory(new PropertyValueFactory<>("profile"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        birthdateColumn.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }

    private void setFilterComboBox() {
        filterComboBox.getItems().addAll(
                Lang.get("All fields"),
                Lang.get("Username"),
                Lang.get("Name"),
                Lang.get("Profile"),
                Lang.get("Email"),
                Lang.get("Birth Date"),
                Lang.get("Gender")
        );

        filterComboBox.getSelectionModel().select(0);
    }

    private void loadUsers() {
        masterData.clear();
        users = User.list();
        for (User user : users) {
            masterData.add(new TableUsersAdapter(user));
        }

        SortedList<TableUsersAdapter> sortedData = setSearch();

        userTableView.setItems(sortedData);
    }

    private SortedList<TableUsersAdapter> setSearch() {
        FilteredList<TableUsersAdapter> filteredData = new FilteredList<>(masterData, p -> true);
        searchTextField.textProperty().addListener((obs, oldV, newV) -> {
            filter(filteredData, newV);
        });
        filterComboBox.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filter(filteredData, searchTextField.getText());
        });

        SortedList<TableUsersAdapter> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(userTableView.comparatorProperty());
        return sortedData;
    }

    private void filter(FilteredList<TableUsersAdapter> filteredData, String newV) {
        filteredData.setPredicate((TableUsersAdapter t) -> {
            if (newV == null || newV.isEmpty()) {
                return true;
            }

            String filter = newV.toLowerCase();

            String fieldFilter = filterComboBox.getValue();

            if (fieldFilter.equals("Username")) {

                if (t.getUsername().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals("Name")) {
                if (t.getName().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals("Profile")) {
                if (t.getProfile().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals("Email")) {
                if (t.getEmail().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals("Birth Date")) {
                if (t.getBirthdate().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals("Gender")) {
                if (t.getGender().toLowerCase().contains(filter)) {
                    return true;
                }
            } else if (fieldFilter.equals("All fields")) {
                if (t.getGender().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getBirthdate().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getEmail().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getProfile().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getName().toLowerCase().contains(filter)) {
                    return true;
                } else if (t.getUsername().toLowerCase().contains(filter)) {
                    return true;
                }
            }

            return false;
        });
    }

    @FXML
    private void insertButtonHandle(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/InsertUser.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("Insert User"));
            stage.setResizable(false);
            stage.show();

            InsertUserController controller = fxmlLoader.<InsertUserController>getController();
            controller.inserted.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (newValue) {
                    loadUsers();

                }
            });

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    @FXML
    private void modifyButtonHandle(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/senaimg/wms/view/fxml/ModifyUser.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(Lang.get("Modify User"));
            stage.setResizable(false);
            stage.show();

            ModifyUserController controller = fxmlLoader.<ModifyUserController>getController();
            controller.setUser(selectedUser.getUser());
            controller.inserted.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (newValue) {
                    loadUsers();
                    getHome().relog();
                }
            });

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    @FXML
    private void deleteButtonHandle(ActionEvent event) {
        Profile profile = selectedUser.getUser().getProfile();

        int admins = 0;
        for (User u : users) {
            Profile p = u.getProfile();
            if (p != null && p.getName().equals("Admin")) {
                admins++;
            }
        }

        if (profile != null && profile.getName().equals("Admin") && admins == 1) {
            Alert alert = new AlertCustom(Alert.AlertType.ERROR, Lang.get("Cannot delete last admin"), ButtonType.OK);
            alert.setTitle("Error");
            
            alert.show();
        } else {

            Alert alert = new AlertCustom(Alert.AlertType.CONFIRMATION, Lang.get("Are you sure you want to delete ") + selectedUser.getUsername() + "?", ButtonType.YES, ButtonType.NO);
            alert.setTitle(Lang.get("Delete User"));

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.YES) {
                selectedUser.getUser().delete();
                loadUsers();
                getHome().relog();
            }
        }
    }

    private void setTableViewContextMenu() {
        userTableView.setRowFactory(tableView -> {
            final TableRow<TableUsersAdapter> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem deleteMenuItem = new MenuItem(Lang.get("Delete"));
            final MenuItem modifyMenuItem = new MenuItem(Lang.get("Edit"));
            modifyMenuItem.setOnAction(event -> modifyButtonHandle(event));
            deleteMenuItem.setOnAction(event -> deleteButtonHandle(event));
            contextMenu.getItems().addAll(modifyMenuItem, deleteMenuItem);
            row.contextMenuProperty().bind(Bindings.when(row.emptyProperty()).then((ContextMenu) null).otherwise(contextMenu));

            return row;
        });
    }

}
