/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.adapter;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.contact.Contact;
import br.com.senaimg.wms.model.warehouse.agent.Supplier;
import br.com.senaimg.wms.model.warehouse.process.Purchase;
import br.com.senaimg.wms.model.warehouse.process.PurchaseHasMetaItem;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ÁlefeLucas
 */
public class TablePurchaseAdapter {

    private Purchase purchase;
    private String situation;
    private String process;
    private String supplier;
    private String tax;
    private String taxType;
    private String phone;
    private String email;
    private String order;
    private String expDelivery;
    private String delivery;
    private String payment;
    private String price;
    private String annotations;

    public TablePurchaseAdapter(Purchase purchase) {
        setPurchase(purchase);
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;

        this.situation = purchase.getProcessStatus().toString();
        this.process = purchase.getInProcess().toString();
        Supplier supp = purchase.getSupplier();
        this.supplier = supp.getMnemonic();
        this.tax = supp.getTaxNumber();
        this.taxType = supp.getTaxNumberType();
        Contact contact = supp.getContact();

        this.phone = contact.getPhone();
        this.email = contact.getEmail();
        this.order = new SimpleDateFormat("dd/MM/yyyy").format(purchase.getOrderDate());
        this.expDelivery = new SimpleDateFormat("dd/MM/yyyy").format(purchase.getExpDelivery());

        Date d = purchase.getDelivery();
        if (d == null) {
            this.delivery = "";
        } else {
            this.delivery = new SimpleDateFormat("dd/MM/yyyy").format(d);
        }

        this.payment = purchase.getPaymentConditions();

        List<PurchaseHasMetaItem> phis = purchase.getPurchaseHasMetaItems();
        double total = 0;
        for (PurchaseHasMetaItem phi : phis) {
            total += phi.getInPrice() * phi.getQuantity();
        }

        this.price = String.format(Lang.get("R$") + " %.2f", total);

        this.annotations = purchase.getAnnotations();
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

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
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

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getExpDelivery() {
        return expDelivery;
    }

    public void setExpDelivery(String expDelivery) {
        this.expDelivery = expDelivery;
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

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String annotations) {
        this.annotations = annotations;
    }
}
