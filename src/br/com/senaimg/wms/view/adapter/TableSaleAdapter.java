/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.adapter;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.contact.Contact;
import br.com.senaimg.wms.model.location.Address;
import br.com.senaimg.wms.model.location.City;
import br.com.senaimg.wms.model.location.Country;
import br.com.senaimg.wms.model.location.State;
import br.com.senaimg.wms.model.warehouse.agent.Customer;
import br.com.senaimg.wms.model.warehouse.process.Sale;
import br.com.senaimg.wms.model.warehouse.process.SaleHasItem;
import br.com.senaimg.wms.model.warehouse.process.SaleHasMetaItem;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author ÁlefeLucas
 */
public class TableSaleAdapter {

    private Sale sale;
    private String situation;
    private String process;
    private String customer;
    private String customerType;
    private String tax;
    private String taxType;
    private String phone;
    private String email;
    private String issue;
    private String shipping;
    private String delivery;
    private String payment;
    private String price;
    private String profitMargin;
    private String totalProfit;
    private String address1;
    private String address2;
    private String postalCode;
    private String city;
    private String state;
    private String country;
    private String annotation;

    public TableSaleAdapter(Sale sale) {
        setSale(sale);
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;

        if (sale != null) {
            setSaleColumns(sale);

            Customer customer = setCustomer(sale);

            setContact(customer);

            setAddress(sale);
        }

    }

    private void setSaleColumns(Sale sale1) {
        this.situation = sale1.getProcessStatus().toString();
        this.process = sale1.getOutProcess().toString();
        this.annotation = sale1.getAnnotations();
        if (sale1.getIssueDate() != null) {
            this.issue = new SimpleDateFormat("dd/MM/yyyy").format(sale1.getIssueDate());
        } else {
            this.issue = "";
        }
        if (sale1.getShippingDate() != null) {
            this.shipping = new SimpleDateFormat("dd/MM/yyyy").format(sale1.getShippingDate());
        } else {
            this.shipping = "";
        }
        if (sale1.getDeliveryDate() != null) {
            this.delivery = new SimpleDateFormat("dd/MM/yyyy").format(sale1.getDeliveryDate());
        } else {
            this.delivery = "";
        }
        List<SaleHasMetaItem> saleHasMetaItems = sale1.getSaleHasMetaItems();
        double totalSellingPrice = 0;
        for (SaleHasMetaItem shmi : saleHasMetaItems) {
            totalSellingPrice += shmi.getSoldPrice();
        }
        List<SaleHasItem> saleHasItems = sale1.getSaleHasItems();
        double totalBuyingPrice = 0;
        for (SaleHasItem shi : saleHasItems) {
            totalBuyingPrice += shi.getItem().getBuyingPrice();
        }
        double profitMargin = (totalSellingPrice * 100 / totalBuyingPrice) - 100;

        double totalProfit = totalSellingPrice - totalBuyingPrice;
        this.price = String.format(Lang.get("R$") + " %.2f", totalSellingPrice);
        if (Double.isInfinite(profitMargin)) {
            this.profitMargin = "-/-";
        } else {
            this.profitMargin = String.format("%.2f", (Double.isNaN(profitMargin) ? 0 : profitMargin)) + "%";
        }
        if (saleHasItems.isEmpty()) {
            this.totalProfit = "-/-";
        } else {
            this.totalProfit = String.format(Lang.get("R$") + " %.2f", totalProfit);
        }
    }

    private Customer setCustomer(Sale sale1) {
        Customer customer = sale1.getCustomer();
        this.customer = customer.getName();
        this.customerType = customer.getType().toString();
        this.tax = customer.getTaxNumber();
        this.taxType = customer.getTaxNumberType();
        return customer;
    }

    private void setContact(Customer customer1) {
        Contact contact = customer1.getContact();
        this.phone = contact.getPhone();
        this.email = contact.getEmail();
    }

    private void setAddress(Sale sale1) {
        Address address = sale1.getDeliver();
        this.address1 = address.getAddressLine1();
        this.address2 = address.getAddressLine2();
        this.postalCode = address.getPostalCode() + "";
        City city = address.getCity();
        State state = city.getState();
        Country country = state.getCountry();
        this.city = city.getName();
        this.state = state.getName();
        this.country = country.getName();
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(String profitMargin) {
        this.profitMargin = profitMargin;
    }

    public String getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
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

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

}
