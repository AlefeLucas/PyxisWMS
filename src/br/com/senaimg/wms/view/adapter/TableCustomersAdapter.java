/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.adapter;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.contact.Contact;
import br.com.senaimg.wms.model.location.Address;
import br.com.senaimg.wms.model.warehouse.agent.Customer;

/**
 *
 * @author ÁlefeLucas
 */
public class TableCustomersAdapter {

    private String status;
    private String name;
    private String type;
    private String isEmployee;
    private String tax;
    private String taxType;
    private String annotation;
    
    
    private String address1;
    private String address2;
    private String postalCode;
    private String city;
    private String state;
    private String country;
    private String countryCode;
    private String phone;
    private String phone2;
    private String fax;
    private String email;
    private String webPage;

    private Customer customer;

    public TableCustomersAdapter(Customer customer) {
        setCustomer(customer);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsEmployee() {
        return isEmployee;
    }

    public void setIsEmployee(String isEmployee) {
        this.isEmployee = isEmployee;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    public Customer getCustomer() {
        return customer;
    }

    

    private void setCustomer(Customer customer) {
        this.customer = customer;

        this.type = customer.getType().toString();
        this.isEmployee = (customer.isEmployee() ? Lang.get("Yes") : Lang.get("No"));
        this.status = Lang.get((this.customer.isDisabled() ? Lang.get("Off") : Lang.get("On")));
        this.name = customer.getName();
        this.tax = customer.getTaxNumber();
        this.taxType = customer.getTaxNumberType();
        this.annotation = customer.getAnnotation();

        Address address = customer.getAddress();
        this.address1 = address.getAddressLine1();
        this.address2 = address.getAddressLine2();
        this.postalCode = address.getPostalCode() + "";
        if (address.getCity() != null) {
            this.city = address.getCity().getName();
            this.state = address.getCity().getState().getName();
            this.country = address.getCity().getState().getCountry().getName();
            this.countryCode = address.getCity().getState().getCountry().getIso();
        } else {
            this.city = Lang.get("Not set");
            this.state = Lang.get("Not set");
            this.country = Lang.get("Not set");
            this.countryCode = Lang.get("Not set");
        }
        Contact contact = customer.getContact();
        this.phone = contact.getPhone();
        this.phone2 = contact.getPhoneAlt();
        this.fax = contact.getFax();
        this.email = contact.getEmail();
        this.webPage = contact.getWebPage();
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

}
