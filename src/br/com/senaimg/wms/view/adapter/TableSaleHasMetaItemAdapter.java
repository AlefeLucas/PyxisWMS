/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.adapter;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.process.SaleHasMetaItem;

/**
 *
 * @author ÁlefeLucas
 */
public class TableSaleHasMetaItemAdapter {

    private SaleHasMetaItem saleHasMetaItem;
    private String mnemonic;
    private int quantity;
    private String totalPrice;

    public TableSaleHasMetaItemAdapter(SaleHasMetaItem saleHasMetaItem) {
        setSaleHasMetaItem(saleHasMetaItem);
    }

    public SaleHasMetaItem getSaleHasMetaItem() {
        return saleHasMetaItem;
    }

    public void setSaleHasMetaItem(SaleHasMetaItem saleHasMetaItem) {
        this.saleHasMetaItem = saleHasMetaItem;

        this.mnemonic = saleHasMetaItem.getMetaItem().getMnemonic();
        this.quantity = saleHasMetaItem.getQuantity() ;
        this.totalPrice = String.format(Lang.get("R$") + " %.2f", saleHasMetaItem.getSoldPrice());
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

}
