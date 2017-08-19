/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.adapter;

import br.com.senaimg.wms.model.warehouse.process.LoanHasMetaItem;

/**
 *
 * @author ÁlefeLucas
 */
public class TableLoanHasMetaItemAdapter {

    private LoanHasMetaItem loanHasMetaItem;
    private String mnemonic;
    private int quantity;

    public TableLoanHasMetaItemAdapter(LoanHasMetaItem loanHasMetaItem) {
        setLoanHasMetaItem(loanHasMetaItem);        
    }

    public LoanHasMetaItem getLoanHasMetaItem() {
        return loanHasMetaItem;
    }

    public void setLoanHasMetaItem(LoanHasMetaItem loanHasMetaItem) {
        this.loanHasMetaItem = loanHasMetaItem;
        
        this.mnemonic = loanHasMetaItem.getMetaItem().getMnemonic();
        this.quantity = loanHasMetaItem.getQuantity();
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
}
