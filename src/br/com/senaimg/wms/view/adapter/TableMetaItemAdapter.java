/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.adapter;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.stock.item.ItemDimension;
import br.com.senaimg.wms.model.warehouse.stock.item.ItemType;
import br.com.senaimg.wms.model.warehouse.stock.item.MetaItem;

/**
 *
 * @author ÁlefeLucas
 */
public class TableMetaItemAdapter {

    private MetaItem item;
    private String mnemonic;
    private String group;
    private String brand;
    private String description;
    private String ean13;
    private String sellingPrice;
    private String minQuantity;
    private String maxQuantity;
    private String operation;
    private String type;
    private String caution;
    private String height;
    private String width;
    private String length;
    private String weight;

    public TableMetaItemAdapter(MetaItem item) {
        this.item = item;

        mnemonic = item.getMnemonic();
        group = item.getGroup().getName();
        brand = item.getBrand().getName();
        description = item.getDescription();
        ean13 = item.getEan13();
        minQuantity = item.getMinQuantity() + "";
        maxQuantity = item.getMaxQuantity() + "";
        operation = item.getOperation().getName();
        ItemType itemType = item.getItemType();
        if (itemType == null) {
            type = Lang.get("Unspecified");
        } else {
            type = itemType.getName();
        }
        caution = item.getCaution();
        sellingPrice = String.format("R$ %.2f", item.getSellingPrice());

        ItemDimension dimension = item.getDimension();
        height = dimension.getHeight() + "";
        width = dimension.getWidth() + "";
        length = dimension.getLength() + "";
        weight = dimension.getWeight() + "";
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEan13() {
        return ean13;
    }

    public void setEan13(String ean13) {
        this.ean13 = ean13;
    }

    public String getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(String minQuantity) {
        this.minQuantity = minQuantity;
    }

    public String getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(String maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCaution() {
        return caution;
    }

    public void setCaution(String caution) {
        this.caution = caution;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public MetaItem getMetaItem() {
        return item;
    }

    public void setMetaItem(MetaItem item) {
        this.item = item;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

}
