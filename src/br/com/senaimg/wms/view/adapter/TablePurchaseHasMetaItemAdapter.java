/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.adapter;

import br.com.senaimg.wms.model.warehouse.process.PurchaseHasMetaItem;

/**
 *
 * @author Aluno
 */
public class TablePurchaseHasMetaItemAdapter {
    private PurchaseHasMetaItem pHI;
    private String mnemonic;
    private String price;
    private String quantity;

    public PurchaseHasMetaItem getpHI() {
        return pHI;
    }

    public void setpHI(PurchaseHasMetaItem pHI) {
        this.pHI = pHI;
        
        
        this.mnemonic = pHI.getMetaItem().getMnemonic();
        this.quantity = pHI.getQuantity() + "";
        double total = (pHI.getInPrice() * pHI.getQuantity());
        this.price = String.format("R$ %.2f", total);
    
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

   

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public TablePurchaseHasMetaItemAdapter(PurchaseHasMetaItem pHI) {
        setpHI(pHI);
                
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
    
}
