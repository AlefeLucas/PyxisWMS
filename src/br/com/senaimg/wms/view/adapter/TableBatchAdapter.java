/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.adapter;

import br.com.senaimg.wms.model.warehouse.stock.item.Batch;
import br.com.senaimg.wms.model.warehouse.stock.item.Item;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author ÁlefeLucas
 */
public class TableBatchAdapter {

    private Batch batch;
    private String item;
    private String number;
    private String manu;
    private String exp;
    private String quantity;

    public TableBatchAdapter(Batch batch) {
        setBatch(batch);
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;

        this.item = batch.getMetaItem().getMnemonic();
        this.number = batch.getNumber() + "";
        if (batch.getManufacturing() == null) {
            this.manu = "";
        } else {
            this.manu = new SimpleDateFormat("dd/MM/yyyy").format(batch.getManufacturing());
        }
        if(batch.getExpiration() == null){
        this.exp = "";
        } else {
        this.exp = new SimpleDateFormat("dd/MM/yyyy").format(batch.getExpiration());
        }
        List<Item> items = batch.getItems();
        
        
        
        this.quantity = items.size() + "";
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getManu() {
        return manu;
    }

    public void setManu(String manu) {
        this.manu = manu;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
