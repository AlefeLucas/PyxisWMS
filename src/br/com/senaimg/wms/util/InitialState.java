/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.util;

import br.com.senaimg.wms.dao.UserDAO;
import br.com.senaimg.wms.model.sistema.Functionality;
import br.com.senaimg.wms.model.sistema.FunctionalityGroup;
import br.com.senaimg.wms.model.sistema.Permission;
import br.com.senaimg.wms.model.sistema.Profile;
import br.com.senaimg.wms.model.sistema.User;
import br.com.senaimg.wms.model.warehouse.stock.item.ItemGroup;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alefe Lucas
 */
public class InitialState {

    Profile admin;
//    Profile operator;
//    Profile visualizer;

//    User bill;
//    User catarina;
//    User manuel;
//    User jose;
//    User claudia;
//    User rafael;

    FunctionalityGroup system;

    FunctionalityGroup warehouse;

    Functionality supplier;
    Functionality catalogue;
    Functionality customer;
    Functionality metaItem;
    Functionality stockMap;
    Functionality stock;
    Functionality loan;
    Functionality purchase;
    Functionality sale;
    Functionality analytics;

    Functionality places;

    Functionality profiles;
    Functionality usersFunc;
    Functionality accountFunc;

    ArrayList<Functionality> systemList;

    ArrayList<Functionality> warehouseList;

    public void set() {

        cleanAll();

        profiles = new Functionality("Profiles", true, "ManagePermissions.fxml", "funcProfile.png");
        usersFunc = new Functionality("Users", true, "ManageUsers.fxml", "funcManageUsers.png");
        accountFunc = new Functionality("My Account", true, "MyAccount.fxml", "funcMyAccount.png");

        systemList = new ArrayList<>();
        systemList.add(accountFunc);
        systemList.add(usersFunc);
        systemList.add(profiles);

        system = new FunctionalityGroup(systemList, "System");

        profiles.setFunctionalityGroup(system);
        usersFunc.setFunctionalityGroup(system);
        accountFunc.setFunctionalityGroup(system);

        system.insert();

        places = new Functionality("Places", true, "Places.fxml", "funcPlaces.png");
        analytics = new Functionality("Analytics", true, "Analytics.fxml", "funcAnalytics.png");
        catalogue = new Functionality("Catalogue", true, "Catalogue.fxml", "funcCatalogue.png");
        metaItem = new Functionality("Item", true, "Item.fxml", "funcItem.png");
        supplier = new Functionality("Suppliers", true, "Suppliers.fxml", "funcSupplier.png");
        stockMap = new Functionality("Stock Map", true, "StockMap.fxml", "funcStockMap.png");
        stock = new Functionality("Stock", true, "Stock.fxml", "funcStock.png");
        loan = new Functionality("Loan", true, "Loan.fxml", "funcLoan.png");
        sale = new Functionality("Sale", true, "Sale.fxml", "funcSales.png");
        purchase = new Functionality("Purchase", true, "Purchase.fxml", "funcPurchase.png");
        customer = new Functionality("Customer", true, "Customer.fxml", "funcCustomer.png");

        warehouseList = new ArrayList<>();
        warehouseList.add(loan);
        warehouseList.add(purchase);
        warehouseList.add(sale);
        warehouseList.add(supplier);
        warehouseList.add(catalogue);
        warehouseList.add(customer);
        warehouseList.add(metaItem);
        warehouseList.add(stock);
        warehouseList.add(stockMap);
        warehouseList.add(places);
        warehouseList.add(analytics);

        warehouse = new FunctionalityGroup(warehouseList, "Warehouse");

        analytics.setFunctionalityGroup(warehouse);
        customer.setFunctionalityGroup(warehouse);
        catalogue.setFunctionalityGroup(warehouse);
        supplier.setFunctionalityGroup(warehouse);
        stockMap.setFunctionalityGroup(warehouse);
        stock.setFunctionalityGroup(warehouse);
        loan.setFunctionalityGroup(warehouse);
        purchase.setFunctionalityGroup(warehouse);
        sale.setFunctionalityGroup(warehouse);
        metaItem.setFunctionalityGroup(warehouse);
        places.setFunctionalityGroup(warehouse);

        warehouse.insert();
//
//        bill = UserGenerator.setUser("bill");
//        catarina = UserGenerator.setUser("catarina");
//
//        manuel = UserGenerator.setUser("manuel");
//        jose = UserGenerator.setUser("jose");
//        claudia = UserGenerator.setUser("claudia");
//        rafael = UserGenerator.setUser("rafael");

        admin = new Profile("Admin");
//        operator = new Profile("Operator");
//        visualizer = new Profile("Visualizer");
//
//        bill.setProfile(admin);
//        catarina.setProfile(admin);

//        jose.setProfile(operator);
//        manuel.setProfile(operator);
//
//        rafael.setProfile(visualizer);
//        claudia.setProfile(visualizer);
//
//        admin.addUser(bill);
//        admin.addUser(catarina);
//
//        operator.addUser(jose);
//        operator.addUser(manuel);
//
//        visualizer.addUser(rafael);
//        visualizer.addUser(claudia);

        Permission pAdmProfiles = new Permission(admin, profiles);
        Permission pAdmAnalytics = new Permission(admin, analytics);
        Permission pAdmSupplier = new Permission(admin, supplier);
        Permission pAdmCatalogue = new Permission(admin, catalogue);
        Permission pAdmStockMap = new Permission(admin, stockMap);
        Permission pAdmStock = new Permission(admin, stock);
        Permission pAdmLoan = new Permission(admin, loan);
        Permission pAdmPurchase = new Permission(admin, purchase);
        Permission pAdmSale = new Permission(admin, sale);
        Permission pAdmCustomer = new Permission(admin, customer);
        Permission pAdmMetaItem = new Permission(admin, metaItem);
        Permission pAdmPlaces = new Permission(admin, places);
        Permission pAdmUsers = new Permission(admin, usersFunc);
        Permission pAdmAcc = new Permission(admin, accountFunc);
//        Permission pOpAcc = new Permission(operator, accountFunc);
//        Permission pOpUsers = new Permission(operator, usersFunc);
//        Permission pVisAcc = new Permission(visualizer, accountFunc);

        admin.addPermission(pAdmSupplier);
        admin.addPermission(pAdmCatalogue);
        admin.addPermission(pAdmCustomer);
        admin.addPermission(pAdmStockMap);
        admin.addPermission(pAdmStock);
        admin.addPermission(pAdmLoan);
        admin.addPermission(pAdmSale);
        admin.addPermission(pAdmPurchase);
        admin.addPermission(pAdmMetaItem);
        admin.addPermission(pAdmAcc);
        admin.addPermission(pAdmProfiles);
        admin.addPermission(pAdmUsers);
        admin.addPermission(pAdmPlaces);
        admin.addPermission(pAdmAnalytics);

//        operator.addPermission(pOpAcc);
//        operator.addPermission(pOpUsers);
//
//        visualizer.addPermission(pVisAcc);

        admin.insert();
//        operator.insert();
//        visualizer.insert();

        pAdmAnalytics.insert();
        pAdmAcc.insert();
        pAdmCatalogue.insert();
        pAdmProfiles.insert();
        pAdmUsers.insert();
        pAdmPlaces.insert();
        pAdmSupplier.insert();
        pAdmStockMap.insert();
        pAdmStock.insert();
        pAdmLoan.insert();
        pAdmSale.insert();
        pAdmPurchase.insert();
        pAdmCustomer.insert();
        pAdmMetaItem.insert();

//        pOpAcc.insert();
//        pOpUsers.insert();
//        pVisAcc.insert();
//
//        bill.insert();
//        catarina.insert();
//        manuel.insert();
//        jose.insert();
//        rafael.insert();
//        claudia.insert();

        List<ItemGroup> itemGroup = ItemGroup.list();
        if (itemGroup.isEmpty()) {
            ItemGroup group = new ItemGroup("All", "", null);
            group.insert();
        }
//
//        List<City> cities = City.list();
//        if (cities.isEmpty()) {
//            for (Country c : Country.list()) {
//                c.delete();
//            }
//            Country country = new Country("Brazil");
//            State state = new State("Minas Gerais", country);
//            City city = new City("Betim", state);
//            country.insert();
//            state.insert();
//            city.insert();
//
//        }

    }

    private void cleanAll() {

        List<User> users = User.list();

        UserDAO.deleteUsers(users);

        List<Permission> permissions = Permission.list();

        for (Permission permission : permissions) {
            permission.delete();
        }

        List<Profile> profiles = Profile.list();

        for (Profile profile : profiles) {
            profile.delete();
        }

        List<Functionality> functions = Functionality.list();

        for (Functionality function : functions) {
            function.delete();
        }

        List<FunctionalityGroup> groups = FunctionalityGroup.list();

        for (FunctionalityGroup group : groups) {
            group.delete();
        }

    }

    private FunctionalityGroup createGroup(String s) {
        FunctionalityGroup group = new FunctionalityGroup(s);
        group.insert();
        return group;
    }

}
