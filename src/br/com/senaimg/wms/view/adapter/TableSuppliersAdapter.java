/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.adapter;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.contact.Contact;
import br.com.senaimg.wms.model.location.Address;
import br.com.senaimg.wms.model.warehouse.agent.Supplier;

/**
 *
 * @author ÁlefeLucas
 */
public class TableSuppliersAdapter {

    private String status;
    private String mnemonic;
    private String companyCode;
    private String name;
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
    private Supplier supplier;

    /**
     *
     * @param supplier
     */
    public TableSuppliersAdapter(Supplier supplier) {
        setSupplier(supplier);
    }

    /**
     *
     * @return
     */
    public String getMnemonic() {
        return mnemonic;
    }

    /**
     *
     * @param mnemonic
     */
    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getTax() {
        return tax;
    }

    /**
     *
     * @param tax
     */
    public void setTax(String tax) {
        this.tax = tax;
    }

    /**
     *
     * @return
     */
    public String getTaxType() {
        return taxType;
    }

    /**
     *
     * @param taxType
     */
    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    /**
     *
     * @return
     */
    public String getAddress1() {
        return address1;
    }

    /**
     *
     * @param address1
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     *
     * @return
     */
    public String getAddress2() {
        return address2;
    }

    /**
     *
     * @param address2
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     *
     * @return
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     *
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     *
     * @param countryCode
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     *
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     */
    public String getPhone2() {
        return phone2;
    }

    /**
     *
     * @param phone2
     */
    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    /**
     *
     * @return
     */
    public String getFax() {
        return fax;
    }

    /**
     *
     * @param fax
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     *
     * @param supplier
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;

        this.status = Lang.get((this.supplier.isDisabled() ? Lang.get("Off") : Lang.get("On")));
        this.companyCode = supplier.getCompanyCode();
        this.mnemonic = supplier.getMnemonic();
        this.name = supplier.getName();
        this.tax = supplier.getTaxNumber();
        this.taxType = supplier.getTaxNumberType();
        this.annotation = supplier.getAnnotation();

        Address address = supplier.getAddress();
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
        Contact contact = supplier.getContact();
        this.phone = contact.getPhone();
        this.phone2 = contact.getPhoneAlt();
        this.fax = contact.getFax();
        this.email = contact.getEmail();
        this.webPage = contact.getWebPage();
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

}
